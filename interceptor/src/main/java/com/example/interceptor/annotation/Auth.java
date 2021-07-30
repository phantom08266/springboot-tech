package com.example.interceptor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    RetentionPolicy에는 3개의 enum값이 존재합니다.
    source  : 코드에서만 유지한다는 말로 주석하고 다를빠 없습니다.
    class   : 컴파일할때만 유지되며 실제 JVM에서 구동될때에는 사용할 수 없습니다.
    runtime : 실제 JVM위에서 구동할때도 유지되는 설정값 입니다.

 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Auth {

}
