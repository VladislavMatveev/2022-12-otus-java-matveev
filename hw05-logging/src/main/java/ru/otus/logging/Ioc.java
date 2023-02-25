package ru.otus.logging;

import ru.otus.TestLogging;
import ru.otus.TestLoggingInterface;
import ru.otus.logging.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Ioc {

    private Ioc() {
    }

    public static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new LoggingInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class LoggingInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;

        LoggingInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class<?> clazz = Class.forName(this.myClass.getClass().getName());
            Method objMethod = clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());
            if (objMethod.isAnnotationPresent(Log.class)) {
                System.out.println("invoking method:" + method.getName() + ",params:" + Arrays.toString(args));
            }

            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}