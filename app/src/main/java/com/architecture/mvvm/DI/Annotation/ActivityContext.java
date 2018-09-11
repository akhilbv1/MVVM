package com.architecture.mvvm.DI.Annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/*
 * Created by akhil on 23/7/18.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
