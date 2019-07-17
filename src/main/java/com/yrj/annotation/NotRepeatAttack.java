package com.yrj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @ClassName: NotRepeatAttack
 * @Description: 防止重复提交注解
 * @author: huchenghao
 * @date: 2018年9月18日 下午5:39:14
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)//运行时通过反射获取到
public @interface NotRepeatAttack {

}
