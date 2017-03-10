package org.epoxide.annotationfactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public interface IAnnotationFactory {

    Class<? extends Annotation> getAnnotation();

    default void handleField(Field field) {

    }

    default void handleMethod(Method method) {

    }

    default void handleClass(Class clazz) {

    }

    default List<AnnotationTarget> getTargets() {
        return Arrays.asList(AnnotationTarget.CLASS, AnnotationTarget.METHOD, AnnotationTarget.FIELD);
    }
}
