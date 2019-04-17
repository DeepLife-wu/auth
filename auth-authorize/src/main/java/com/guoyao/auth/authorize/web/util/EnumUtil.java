/**
 * 
 */
package com.guoyao.auth.authorize.web.util;

import com.guoyao.auth.authorize.model.enums.CodeEnum;

/**
 * @author wuchao
 * @Date 【2019年1月22日:上午11:46:57】
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass)
    {
        for(T each : enumClass.getEnumConstants()) {
            if(code.equals(each.getCode()))
                return each;
        }
        return null;
    }
}

