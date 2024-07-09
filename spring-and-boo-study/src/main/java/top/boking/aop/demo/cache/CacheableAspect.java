package top.boking.aop.demo.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.boking.aop.Cacheable;

import java.lang.reflect.Method;
import java.util.jar.JarEntry;

/**
 * @Author shxl
 * @Date 2024/5/18 21:17
 * @Version 1.0
 */
@Aspect
@Component
public class CacheableAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheableAspect.class);
    @Around("@annotation(top.boking.aop.Cacheable)")
    public Object cache(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Cacheable annotation = method.getAnnotation(Cacheable.class);
        int i = annotation.expireTime();
        String s = annotation.keyName();
        Object proceed = pjp.proceed();
        Object target = pjp.getTarget();
        return proceed;
    }
}
