package org.epoxide.wumpus.factory;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FactoryBus {

    private static List<IAnnotationFactory> factoryList = new ArrayList<>();

    public static void registerFactory(IAnnotationFactory factory) {
        //TODO Error
        //TODO Check collision
        factoryList.add(factory);
    }

    public static void autoSearch() {

        List<Class<? extends Annotation>> annotationList = new ArrayList<>();
        Reflections reflections = new Reflections("");

        Set<Class<?>> clazzes = reflections.getTypesAnnotatedWith(AnnotationFactory.class);
        for (Class<?> clazz : clazzes) {
            try {
                boolean isFactory = false;
                for (Class<?> c : clazz.getInterfaces()) {
                    if (c == IAnnotationFactory.class) {
                        isFactory = true;
                        break;
                    }
                }
                if (!isFactory) {
                    try {
                        throw new Exception("Class does not implement IAnnotationFactory " + clazz);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                Method method = clazz.getDeclaredMethod("getAnnotation");
                IAnnotationFactory factory = (IAnnotationFactory) clazz.newInstance();
                Class<? extends Annotation> annotation = (Class<? extends Annotation>) method.invoke(factory);
                annotationList.add(annotation);
                factoryList.add(factory);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        for (Class<? extends Annotation> annotation : annotationList) {
            searchAnnotation(annotation);
        }
    }

    public static void searchAnnotation(Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections("");

        Set<Class<?>> clazzes = reflections.getTypesAnnotatedWith(annotation);
        for (Class clazz : clazzes) {
            for (IAnnotationFactory annotationFactory : factoryList)
                if (annotationFactory.getTargets().contains(AnnotationTarget.CLASS))
                    if (clazz.isAnnotationPresent(annotationFactory.getAnnotation()))
                        annotationFactory.handleClass(clazz);


            for (IAnnotationFactory annotationFactory : factoryList)
                if (annotationFactory.getTargets().contains(AnnotationTarget.METHOD))
                    for (Method method : clazz.getDeclaredMethods())
                        if (method.isAnnotationPresent(annotationFactory.getAnnotation()))
                            annotationFactory.handleMethod(method);

            for (IAnnotationFactory annotationFactory : factoryList)
                if (annotationFactory.getTargets().contains(AnnotationTarget.FIELD))
                    for (Field field : clazz.getDeclaredFields())
                        if (field.isAnnotationPresent(annotationFactory.getAnnotation()))
                            annotationFactory.handleField(field);
        }
    }

    public static Annotation getAnnoationFromArray(Annotation[] annotations, IAnnotationFactory factory) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == factory.getAnnotation()) {
                return annotation;
            }
        }
        return null;
    }
}
