package top.boking.spring.definition;

import java.util.Map;

public interface BeanDefinition {
    Map initBean();
    Class getClazz();
}
