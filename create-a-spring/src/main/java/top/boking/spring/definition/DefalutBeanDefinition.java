package top.boking.spring.definition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.boking.spring.annotation.Bean;
import top.boking.spring.annotation.Component;
import top.boking.spring.annotation.ElementForScope;
import top.boking.spring.utils.GetClasses;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefalutBeanDefinition implements BeanDefinition {

    private ElementForScope scope;
    private Class clazz;


    @Override
    public Map initBean() {
        HashMap<String, Object> beanMap = new HashMap<>();

        List<String> allClasses = GetClasses.getComponentClass();
        for (String allClass : allClasses) {
            try {
                Class beanClass = Class.forName(allClass);
                Component annotation = (Component) beanClass.getAnnotation(Component.class);
                if (annotation != null) {
 //                   String value = annotation.value();

                    Method[] declaredMethods = beanClass.getDeclaredMethods();
                    for (Method declaredMethod : declaredMethods) {
                        Bean bean = declaredMethod.getAnnotation(Bean.class);
                        if (bean != null) {
                            String valueForBean = bean.value();
                            Object beanObj = beanClass.newInstance();
                            declaredMethod.setAccessible(true);
                            Object invoke = declaredMethod.invoke(beanObj, null);
                            beanMap.put(valueForBean != ""?valueForBean:allClass,invoke);
                        }

                    }

                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


        }


        return beanMap;
    }
}
