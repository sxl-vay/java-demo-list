package top.boking.aop.demo.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.jar.JarEntry;

/**
 * @Author shxl
 * @Date 2024/5/18 21:17
 * @Version 1.0
 */
@Aspect
//@Component
public class CacheableAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheableAspect.class);
    @Around("@annotation(top.boking.aop.Cacheable)")
    public Object cache(ProceedingJoinPoint pjp) throws Throwable {

        Object proceed = pjp.proceed();

        return proceed;
    }
}
