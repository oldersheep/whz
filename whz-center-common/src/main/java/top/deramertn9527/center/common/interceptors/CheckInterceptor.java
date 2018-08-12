package top.deramertn9527.center.common.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import top.deramertn9527.center.common.annotation.NotNull;
import top.deramertn9527.center.common.cache.CheckUtil;
import top.deramertn9527.center.common.exception.WhzServerException;
import top.deramertn9527.center.common.exception.enums.ExceptionEnum;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


@Aspect
@Configuration
@Slf4j
public class CheckInterceptor {

    /**
     * 中台统一切点
     */
    @Pointcut("execution(* top.deramertn9527..*(..))")
    public void pointcut() {
    }

    /**
     * 统一拦截切点，校验注解信息
     *
     * @param joinPoint 连接点
     */
    @Before(value = "pointcut()")
    public void handleBefore(JoinPoint joinPoint) {

        /*
            通过连接点，得到真实的对象
            原因：连接点是接口对象，由于形参配置的注解在实现类上，所以需要得到真实的对象
         */
        Class clz = joinPoint.getTarget().getClass();

        // 方法内形参的注解集合
        Annotation[][] methodAnnotations;
        // 方法内形参的类集合
        Class<?>[] clazzs;

        try {
            // 通过连接点得到方法信息
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 得到真实的方法
            Method method = signature.getMethod();
            // 得到实现类的方法
            Method methodImpl = clz.getMethod(method.getName(), method.getParameterTypes());
            // 得到实现类上方法形参的注解集合
            methodAnnotations = methodImpl.getParameterAnnotations();
            // 得到实现类上方法形参的类集合
            clazzs = methodImpl.getParameterTypes();
        } catch (NoSuchMethodException e) {
            throw new WhzServerException(e);
        }

        // 得到实参集合
        Object[] args = joinPoint.getArgs();
        // 注解集合
        Annotation[] annotations;
        // 校验实参的值
        Object o;
        // 校验实参的类型
        Class clazz;
        // 标签中台自定义非空注解
        NotNull tagNotNull;
        // 异常枚举类
        ExceptionEnum e;

        for (int i = 0; i < methodAnnotations.length; i++) {
            annotations = methodAnnotations[i];
            o = args[i];
            clazz = clazzs[i];
            for (Annotation annotation : annotations) {
                // 当前注解为自定义非空注解时
                if (annotation.annotationType().getName().equals(NotNull.class.getName())) {
                    tagNotNull = (NotNull) annotation;
                    e = tagNotNull.e();
                    CheckUtil.checkAnnotation(clazz, o, e);
                }
            }
        }
    }
}