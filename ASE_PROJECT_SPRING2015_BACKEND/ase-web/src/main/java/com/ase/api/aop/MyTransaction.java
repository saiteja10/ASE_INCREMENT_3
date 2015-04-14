package com.ase.api.aop;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vivek
 * Date: 11/19/13
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface MyTransaction {
}
