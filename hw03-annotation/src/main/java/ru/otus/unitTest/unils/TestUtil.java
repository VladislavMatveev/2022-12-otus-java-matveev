package ru.otus.unitTest.unils;

import ru.otus.unitTest.annotations.After;
import ru.otus.unitTest.annotations.Before;
import ru.otus.unitTest.annotations.Test;
import ru.otus.unitTest.enums.MethodsType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtil {
    private TestUtil() {
    }

    public static void mapInit(Map<MethodsType, List<String>> methodsMap, Class<?> clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method method : declaredMethods) {
            String methodName = method.getName();

            var hasAnnotationBefore = method.isAnnotationPresent(Before.class);
            if (hasAnnotationBefore) {
                methodsMap.get(MethodsType.BEFORE).add(methodName);
            }

            var hasAnnotationTest = method.isAnnotationPresent(Test.class);
            if (hasAnnotationTest) {
                methodsMap.get(MethodsType.TEST).add(methodName);
            }

            var hasAnnotationAfter = method.isAnnotationPresent(After.class);
            if (hasAnnotationAfter) {
                methodsMap.get(MethodsType.AFTER).add(methodName);
            }
        }
    }

    public static Map<MethodsType, List<String>> getEmptyMethodsMap() {

        var resultMap = new HashMap<MethodsType, List<String>>();

        resultMap.put(MethodsType.BEFORE, new ArrayList<>());
        resultMap.put(MethodsType.TEST, new ArrayList<>());
        resultMap.put(MethodsType.AFTER, new ArrayList<>());

        return resultMap;
    }

    public static Object invokeMethod(Object object, String name) {
        try {
            var method = object.getClass().getMethod(name);
            return method.invoke(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
