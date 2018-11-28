package top.deramertn9527.center.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import top.deramertn9527.center.api.exception.WhzCenterApiException;
import top.deramertn9527.center.common.exception.WhzServerException;

/**
 * Api service 接口 aop
 */
@Aspect
@Configuration
@Slf4j
public class WhzCenterApiAop {

    /**
     * api service 切点
     */
    @Pointcut("execution(public * top.deramertn9527.center.gateway.*.*.*(..))")
    public void pointcut() {
    }

    /**
     * 统一处理异常
     *
     * @param e 捕获的异常
     * @throws WhzServerException
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void handleThrowing(Exception e) throws WhzCenterApiException {
        log.error("error: ", e);
        if (e instanceof WhzServerException) {
            WhzServerException exception = (WhzServerException) e;
            throw new WhzCenterApiException(exception.getCode(), exception.getMess());
        } else {
            throw new WhzCenterApiException(e.getMessage());
        }
    }

    /**
     * 打印日志
     *
     * @param pjp 过程对象
     * @return Object
     * @throws Throwable 异常
     */
    @Around(value = "pointcut()")
    public Object handleAround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("{}.{}, param: {}", pjp.getTarget(), pjp.getSignature(), pjp.getArgs());
        Object retVal = pjp.proceed(pjp.getArgs());
        log.info("result: {}", retVal);
        return retVal;
    }
}