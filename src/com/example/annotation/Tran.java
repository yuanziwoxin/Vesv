package com.example.annotation;
/**
 * �Զ���ע��
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//�Զ���ע��
@Retention(RetentionPolicy.RUNTIME)//����ʱ��Ҫ��ע��
@Target(ElementType.METHOD)//��ʾ�������η�����ע�⣨��ע�ڷ����ϣ�
//@Inherited //�������ε�Annotation�����м̳��ԣ���ʾĳ����ʹ���˱�@inherited���ε�Annotation,�����ཫ�Զ����и�ע��;
public @interface Tran {
     
}
