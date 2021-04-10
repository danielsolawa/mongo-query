package com.danielsolawa.mongoquery.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtil {


    public static String SET = "set";
    public static String GET = "get";

    public static void callSetter(Object o, String fieldName, Object value){
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, o.getClass());
            propertyDescriptor.getWriteMethod().invoke(o, value);
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    public static Object callGetter(Object o, String fieldName){
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, o.getClass());
            return propertyDescriptor.getReadMethod().invoke(o);

        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    public static String fieldToGetter(String fieldName){
        return GET +  fieldName.toLowerCase();
    }

    public static String fieldToSetter(String fieldName){
        return SET + fieldName.toLowerCase();
    }



}
