package top.boking.spring.factory;

import top.boking.spring.annotation.*;
import top.boking.spring.definition.BeanDefinition;
import top.boking.spring.definition.DefalutBeanDefinition;
import top.boking.spring.utils.GetClasses;

import java.beans.Introspector;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShxlApplicationContext implements BeanFacotry {

    public Object createBean(String beanName, BeanDefinition definition) {
        Object o = null;
        try {
            Class clazz = definition.getClazz();
            Constructor constructor = clazz.getConstructor();
             o = constructor.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(AutoWired.class)) {
                    String value = field.getAnnotation(AutoWired.class).value();

                    field.setAccessible(true);
                    field.set(o,getBean(value));
                }
            }
        } catch (NoSuchMethodException e) {


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return o;
    }

    public ShxlApplicationContext() {
        Object bean1 = null;
        List<String> allClasses = GetClasses.getComponentClass();
        for (String allClass : allClasses) {
            try {
                Class beanClass = Class.forName(allClass);
                Component annotation = (Component) beanClass.getAnnotation(Component.class);
                if (annotation != null) {
                    //                   String value = annotation.value();



                            String valueForBean = annotation.value();

                            /*Object beanObj = beanClass.newInstance();
                            declaredMethod.setAccessible(true);
                            Object invoke = declaredMethod.invoke(beanObj, null);*/
                            DefalutBeanDefinition definition = new DefalutBeanDefinition();
                            if (beanClass.isAnnotationPresent(Scope.class)) {
                                Scope scope = (Scope) beanClass.getAnnotation(Scope.class);
                                definition.setScope(scope.value());
                            } else {
                                definition.setScope(ElementForScope.SINGLETON);
                            }
                            definition.setClazz(beanClass);
                            String s = !"".equals(valueForBean) ? valueForBean: Introspector.decapitalize(beanClass.getSimpleName());
                            beanDefinitionMap.put(s,definition);
                            if (definition.getScope() == ElementForScope.SINGLETON) {
                                bean1 = createBean(s, definition);
                                beanMap.put(s,bean1);
                            }




                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    public static void main(String[] args) {
        ShxlApplicationContext shxlApplicationContext = new ShxlApplicationContext();
        System.out.println(shxlApplicationContext.getBean("dede"));
        System.out.println(shxlApplicationContext.getBean("shiroConfig"));
        System.out.println(shxlApplicationContext.getBean("shxl"));
        System.out.println(shxlApplicationContext.getBean("dede"));

    }
    BeanDefinition definition = new DefalutBeanDefinition();
    ConcurrentHashMap<String,Object> beanMap = new ConcurrentHashMap<>();
    ConcurrentHashMap<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String beanName) {
        //1.获取bean定义信息
        Object o = beanMap.get(beanName);
        if (o == null) {
            BeanDefinition definition = beanDefinitionMap.get(beanName);
            if (definition == null) {
                return null;
            }
            Class clazz = definition.getClazz();

            o = createBean(beanName, definition);

        }

        return o;
    }








    @Override
    public Map getBean() {
        Object bean1 = null;

        return  null;
    }
    public Map<String,Object> getAutoWired() {
        List<String> componentClass = GetClasses.getComponentClass();
        /*for (String aClass : componentClass) {
            try {
                Class<?> classByUser = Class.forName(aClass);
                Config annotation = classByUser.getAnnotation(Config.class);
                if (annotation != null) {
                    Field[] declaredFields = classByUser.getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        AutoWired autoWired = declaredField.getAnnotation(AutoWired.class);
                        if (autoWired != null) {
                            String value = autoWired.value();
                            if (value != "") {
                                Object os = beans.get(value);
                                declaredField.setAccessible(true);
                                declaredField.set(o, os);//这个语句的含义是给o对象的declaredField属性赋值成os
                            } else {
                                //根据对象名赋值
                            }
                        }

                    }
                }


            } catch (ClassNotFoundException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }*/
        return null;
    }
}
