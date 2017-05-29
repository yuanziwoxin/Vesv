package com.example.annotation;
/**
 * 自定义注解
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解
@Retention(RetentionPolicy.RUNTIME)//运行时需要的注解
@Target(ElementType.METHOD)//表示用于修饰方法的注解（标注在方法上）
//@Inherited //被它修饰的Annotation将具有继承性，表示某个类使用了被@inherited修饰的Annotation,该子类将自动具有该注解;
public @interface Tran {
     
}
