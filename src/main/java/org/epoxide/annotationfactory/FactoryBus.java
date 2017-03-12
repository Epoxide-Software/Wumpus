package org.epoxide.annotationfactory;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FactoryBus {

    private static boolean SEARCHED = false;
    private static List<IAnnotationFactory> factoryList = new ArrayList<>();
    private static Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setUrls(ClasspathHelper.forPackage(""))
            .setScanners(new FieldAnnotationsScanner(), new MethodAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner()));

    public static void autoSearch() {
        if (SEARCHED)
            return;
        SEARCHED = true;
        List<Class<? extends Annotation>> annotationList = new ArrayList<>();

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
        for (Class clazz : reflections.getTypesAnnotatedWith(annotation))
            for (IAnnotationFactory annotationFactory : factoryList)
                if (annotationFactory.getTargets().contains(AnnotationTarget.CLASS))
                    if (clazz.isAnnotationPresent(annotationFactory.getAnnotation()))
                        annotationFactory.handleClass(clazz);

        for (Method method : reflections.getMethodsAnnotatedWith(annotation))
            for (IAnnotationFactory annotationFactory : factoryList)
                if (annotationFactory.getTargets().contains(AnnotationTarget.METHOD))
                    if (method.isAnnotationPresent(annotationFactory.getAnnotation()))
                        annotationFactory.handleMethod(method);

        for (Field field : reflections.getFieldsAnnotatedWith(annotation))
            for (IAnnotationFactory annotationFactory : factoryList)
                if (annotationFactory.getTargets().contains(AnnotationTarget.FIELD))
                    if (field.isAnnotationPresent(annotationFactory.getAnnotation()))
                        annotationFactory.handleField(field);

    }

    public static Annotation getAnnotationFromArray(Annotation[] annotations, IAnnotationFactory factory) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == factory.getAnnotation()) {
                return annotation;
            }
        }
        return null;
    }

    public static Annotation getAnnotationFromArray(Annotation[] annotations, Class<? extends Annotation> a) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == a) {
                return annotation;
            }
        }
        return null;
    }
}
