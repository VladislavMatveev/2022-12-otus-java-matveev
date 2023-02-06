package ru.otus.unitTest;

import ru.otus.unitTest.enums.MethodsType;
import ru.otus.unitTest.unils.TestUtil;

import java.util.List;
import java.util.Map;

public class UnitTestApplication {

    public void start(String className) {

        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Map<MethodsType, List<String>> methodsMap = TestUtil.getEmptyMethodsMap();
        TestUtil.mapInit(methodsMap, clazz);

        print("Starting tests...");
        int successTests = 0;
        int failedTests = 0;
        int countTest = 0;

        for (String testMethodName : methodsMap.get(MethodsType.TEST)) {
            countTest++;
            print("Start test - " + testMethodName);
            try {
                startTestInstance(clazz, testMethodName, methodsMap);
                print(testMethodName + " success!");
                successTests++;
            } catch (RuntimeException e) {
                print(testMethodName + " failed!");
                failedTests++;
            }
            print("==============================");
        }
        print("Statistics:");
        print("Test count: " + countTest);
        print("Success: " + successTests);
        print("Failed: " + failedTests);
    }

    private void startTestInstance(Class<?> clazz, String testMethodName, Map<MethodsType, List<String>> methodsMap) {
        Object classObject;
        boolean noErrorOccurred = true;

        // Create instance
        try {
            classObject = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // "Before" methods
        for (String methodName : methodsMap.get(MethodsType.BEFORE)) {
            try {
                TestUtil.invokeMethod(classObject, methodName);
            } catch (RuntimeException e) {
                noErrorOccurred = false;
            }
        }

        // Test
        if (noErrorOccurred) {
            try {
                TestUtil.invokeMethod(classObject, testMethodName);
            } catch (RuntimeException e) {
                noErrorOccurred = false;
            }
        }

        // "After" methods
        for (String methodName : methodsMap.get(MethodsType.AFTER)) {
            TestUtil.invokeMethod(classObject, methodName);
        }

        if (!noErrorOccurred) {
            throw new RuntimeException();
        }
    }

    private void print(String message) {
        System.out.println(message);
    }
}
