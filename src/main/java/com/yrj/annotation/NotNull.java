package com.yrj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: NotNull
 * @Description: 标注参数、属性、方法不可为 Null 
 * @author: huchenghao
 * @date: 2018年7月31日 下午12:09:20
 */
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
public @interface NotNull {

}
