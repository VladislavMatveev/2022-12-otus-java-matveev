package ru.otus.logging;

import ru.otus.TestLoggingInterface;
import ru.otus.logging.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

public class Ioc {

    private Ioc() {
    }

    public static TestLoggingInterface createMyClass(TestLoggingInterface clazzImpl) {
        List<String> loggingMethods = getLoggingMethods(clazzImpl.getClass());
        InvocationHandler handler = new LoggingInvocationHandler(clazzImpl, loggingMethods);
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    private static List<String> getLoggingMethods(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Log.class))
                .map(method -> method.getName() + Arrays.toString(method.getParameterTypes()))
                .toList();
    }

    static class LoggingInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface loggingInterfaceImpl;
        private final List<String> loggingMethods;

        LoggingInvocationHandler(TestLoggingInterface loggingInterfaceImpl, List<String> loggingMethods) {
            this.loggingInterfaceImpl = loggingInterfaceImpl;
            this.loggingMethods = loggingMethods;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (loggingMethods.contains(method.getName() + Arrays.toString(method.getParameterTypes()))) {
                System.out.println("invoking method:" + method.getName() + ",params:" + Arrays.toString(args));
            }

            return method.invoke(loggingInterfaceImpl, args);
        }

        @Override
        public String toString() {
            return "LoggingInvocationHandler{" +
                    "myClass=" + loggingInterfaceImpl +
                    '}';
        }
    }
}