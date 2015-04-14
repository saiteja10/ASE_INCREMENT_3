package com.ase.api.aop;

/**
 * Created with IntelliJ IDEA.
 * User: Vivek
 * Date: 11/27/13
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyThreadLocal {
    public static final ThreadLocal<CurrentUser> threadLocal = new ThreadLocal<CurrentUser>();

    public static void set(CurrentUser currentUser) {
        threadLocal.set(currentUser);
    }

    public static void unset() {
        threadLocal.remove();
    }

    public static CurrentUser get() {
        return threadLocal.get();
    }
}
