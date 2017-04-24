package com.fsl.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 
 * 描述：BeanUtils
 * 
 * @author yangyongchuan 2016年5月23日 下午5:00:11
 * @version 0.1
 * @since 0.1
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }



    /**
     * 
     * 描述：复制对象，过滤null
     * 
     * @param src
     * @param target
     * @author yangyongchuan 2016年5月23日 下午5:00:19
     * @version 0.1
     * @since 0.1
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
