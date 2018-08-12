package top.deramertn9527.center.common.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import top.deramertn9527.center.common.annotation.ServerDaoException;
import top.deramertn9527.center.common.exception.WhzServerException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Configuration
@Slf4j
public class DaoExceptionInterceptor {

    /**
     * dao exception 切点
     */
    @Pointcut("@annotation(top.deramertn9527.center.common.annotation.ServerDaoException)")
    public void pointcut() {
    }

    /**
     * 统一处理异常
     *
     * @param e 捕获的异常
     * @throws WhzServerException 标签中台异常类
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Throwable e) {
        log.error("DaoExceptionInterceptor[doAfterThrowing] error : ", e);
        // 获取 JoinPoint Signature对象
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 得到调用的方法
        Method method = signature.getMethod();
        // 判断有没有 满足抛出异常的注解
        Annotation[] annotations = method.getAnnotations();
        if (!ObjectUtils.isEmpty(annotations)) {
            throwServerException(annotations);
        }

        try {
            Class clz = point.getTarget().getClass();
            // 得到实现类上的方法
            Method methodImpl = clz.getMethod(method.getName(), method.getParameterTypes());
            annotations = methodImpl.getAnnotations();
        } catch (Exception e1) {
            log.error("DaoExceptionInterceptor[doAfterThrowing] 获取实现类上面注解异常 : ", e);
            throw new WhzServerException(e1);
        }

        // 判断有没有 满足抛出异常的注解
        if (!ObjectUtils.isEmpty(annotations)) {
            throwServerException(annotations);
        }
    }

    /**
     * 注解集合抛出异常
     *
     * @param annotations 方法上的注解集合
     */
    private void throwServerException(Annotation[] annotations) {
        // 遍历注解集合
        for (Annotation annotation : annotations) {
            if (annotation != null) {
                // 判断是否为  注解
                if (annotation.annotationType().isAssignableFrom(ServerDaoException.class)) {
                    ServerDaoException tagDaoException = (ServerDaoException) annotation;
                    throw new WhzServerException(tagDaoException.value());
                }
            }
        }
    }
}