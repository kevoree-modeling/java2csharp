
package org.kevoree.modeling.java2csharp;

import com.google.common.collect.ImmutableSet;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class TypeHelper {

    public static String printType(PsiType element, TranslationContext ctx) {
        return printType(element, ctx, true, false, false);
    }

    public static String protect(String name) {
        if (keywords.contains(name)) {
            return "@" + name;
        } else {
            return name;
        }
    }

    public static String printType(PsiType element, TranslationContext ctx, boolean withGenericParams, boolean explicitType, boolean avoidNativeOptim) {
        String result = element.getPresentableText();
        if (result.equals("Throwable") || result.equals("Exception") || result.equals("RuntimeException") || result.equals("IndexOutOfBoundsException")) {
            return "Error";
        }
        if (objects.contains(result)) {
            return "object";
        }
        if (strings.contains(result)) {
            return "string";
        }
        if (booleans.contains(result)) {
            return "bool";
        }
        if (integers.contains(result)) {
            return "int";
        }
        if (longs.contains(result)) {
            return "long";
        }
        if (doubles.contains(result)) {
            return "double";
        }
        if (element instanceof PsiPrimitiveType) {
            if (result == null || result.equals("null")) {
                System.err.println("TypeHelper::printType -> Result null with elem:" + element.toString());
            }
            return result;
        } else if (element instanceof PsiArrayType) {
            PsiArrayType typedElement = (PsiArrayType) element;
            String partialResult = printType(typedElement.getComponentType(), ctx, true, false, true);
            /*
            if (typedElement.getComponentType() instanceof PsiClassReferenceType) {
                PsiClass resolvedClass = ((PsiClassReferenceType) typedElement.getComponentType()).resolve();
                if (resolvedClass != null) {
                    if (isCallbackClass(resolvedClass) && !explicitType) {
                        //'{ (p: KEvent): void; }[]' is not assignable to type '(p: KEvent) => void[]'.
                        result = "{" + partialResult.replace("=>", ":") + ";}[]";
                        return result;
                    }
                }
            }*/
            result = partialResult + "[]";
            return result;
        } else if (element instanceof PsiClassReferenceType) {
            PsiClass resolvedClass = ((PsiClassReferenceType) element).resolve();
            if (resolvedClass != null) {
                String qualifiedName = resolvedClass.getQualifiedName();
                if (qualifiedName != null) {
                    String[] parts = qualifiedName.split("\\.");
                    String assembled = "";
                    for (String p : parts) {
                        if (!assembled.isEmpty()) {
                            assembled += ".";
                        }
                        assembled += protect(p);
                    }
                    //qualifiedName = assembled;

                    result = assembled;
                }
                if (resolvedClass.getTypeParameters().length > 0) {
                    String[] generics = new String[resolvedClass.getTypeParameters().length];
                    Arrays.fill(generics, "object");
                    if (withGenericParams) {
                        result += "<" + String.join(", ", generics) + ">";
                    }
                }
            } else {
                String tryJavaUtil = javaTypes.get(((PsiClassReferenceType) element).getClassName());
                if (tryJavaUtil != null) {
                    result = tryJavaUtil;
                } else {
                    result = ((PsiClassReferenceType) element).getReference().getQualifiedName();
                }
                if (((PsiClassReferenceType) element).getParameterCount() > 0) {
                    String[] generics = new String[((PsiClassReferenceType) element).getParameterCount()];
                    PsiType[] genericTypes = ((PsiClassReferenceType) element).getParameters();
                    for (int i = 0; i < genericTypes.length; i++) {
                        generics[i] = printType(genericTypes[i], ctx);
                    }
                    if (withGenericParams) {
                        result += "<" + String.join(", ", generics) + ">";
                    }
                }
            }
        } else {
            System.err.println("TypeHelper: InstanceOf:" + element.getClass());
        }

        if (result == null || result.equals("null")) {
            System.err.println("TypeHelper::printType -> Result null with elem:" + element.toString());
        }

        return result;
    }

    public static boolean isCallbackClass(PsiClass clazz) {
        if (clazz == null) {
            return false;
        }
        return clazz.isInterface() && clazz.getAllFields().length == 0 && clazz.getAllMethods().length == 1;
    }

    public static String primitiveStaticCall(String clazz) {
        if (clazz.equals("String")) {
            return "StringUtils";
        }
        String result = javaTypes.get(clazz);
        if (result != null) {
            return result;
        }
        return clazz;
    }


    public static final HashMap<String, String> javaTypes = new HashMap<String, String>();

    static {

        javaTypes.put("System", "java.lang.System");

        javaTypes.put("AtomicInteger", "java.util.concurrent.atomic.AtomicInteger");
        javaTypes.put("AtomicLong", "java.util.concurrent.atomic.AtomicLong");
        javaTypes.put("AtomicReference", "java.util.concurrent.atomic.AtomicReference");
        javaTypes.put("AtomicIntegerArray", "java.util.concurrent.atomic.AtomicIntegerArray");

        javaTypes.put("Arrays", "java.util.Arrays");
        javaTypes.put("Collections", "java.util.Collections");
        javaTypes.put("Map", "java.util.Map");
        javaTypes.put("Stack", "java.util.Stack");
        javaTypes.put("HashMap", "java.util.HashMap");
        javaTypes.put("List", "java.util.List");
        javaTypes.put("Set", "java.util.Set");
        javaTypes.put("HashSet", "java.util.HashSet");
        javaTypes.put("ArrayList", "java.util.ArrayList");
        javaTypes.put("LinkedList", "java.util.LinkedList");
        javaTypes.put("Random", "java.util.Random");

        javaTypes.put("Long", "java.lang.Long");
        javaTypes.put("Double", "java.lang.Double");
        javaTypes.put("Float", "java.lang.Float");
        javaTypes.put("Integer", "java.lang.Integer");
        javaTypes.put("Short", "java.lang.Short");
        javaTypes.put("Boolean", "java.lang.Boolean");
        javaTypes.put("StringBuilder", "java.lang.StringBuilder");

        javaTypes.put("Throwable", "java.lang.Throwable");
        javaTypes.put("Exception", "java.lang.Exception");
        javaTypes.put("Runnable", "java.lang.Runnable");
        javaTypes.put("RuntimeException", "java.lang.RuntimeException");
        javaTypes.put("IndexOutOfBoundsException", "java.lang.IndexOutOfBoundsException");
        javaTypes.put("WeakReference", "java.lang.ref.WeakReference");
    }

    public static final Set<String> primitiveNumbers = ImmutableSet.of("byte", "short", "int", "long", "float", "double");

    public static final Set<String> integers = ImmutableSet.of(
            "int",
            Integer.class.getName(),
            Integer.class.getSimpleName()
    );

    public static final Set<String> longs = ImmutableSet.of(
            "long",
            Long.class.getName(),
            Long.class.getSimpleName()
    );

    public static final Set<String> doubles = ImmutableSet.of(
            "double",
            Double.class.getName(),
            Double.class.getSimpleName()
    );


    public static final Set<String> objectNumbers = ImmutableSet.of(
            Byte.class.getName(),
            Byte.class.getSimpleName(),
            Short.class.getName(),
            Short.class.getSimpleName(),
            Integer.class.getName(),
            Integer.class.getSimpleName(),
            Long.class.getName(),
            Long.class.getSimpleName(),
            Float.class.getName(),
            Float.class.getSimpleName(),
            Double.class.getName(),
            Double.class.getSimpleName(),
            BigInteger.class.getName(),
            BigInteger.class.getSimpleName(),
            BigDecimal.class.getName(),
            BigDecimal.class.getSimpleName()
    );

    public static final Set<String> strings = ImmutableSet.of(
            "char",
            Character.class.getName(),
            Character.class.getSimpleName(),
            String.class.getName(),
            String.class.getSimpleName()
    );

    public static final Set<String> booleans = ImmutableSet.of(
            "boolean",
            Boolean.class.getName(),
            Boolean.class.getSimpleName()
    );

    public static final Set<String> objects = ImmutableSet.of(
            Object.class.getName(),
            Object.class.getSimpleName()
    );

    public static final Set<String> keywords = ImmutableSet.of(
            "params",
            "object",
            "internal"
    );

}
