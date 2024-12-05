package org.river.article.utils.springContext;

public class BaseContext {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setContext(String context) {
        threadLocal.set(context);
    }
    public static String getContext() {
        return threadLocal.get();
    }
    public static void removeContext() {
        threadLocal.remove();
    }
}
