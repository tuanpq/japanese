package io.github.tuanpq.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAspect {

    private static final String TAG = LogAspect.class.getSimpleName();

    @Around("execution(* io.github.tuanpq..*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();

        long start = System.currentTimeMillis();
        Log.d(TAG, "➡️ Enter: " + method);

        Object result = joinPoint.proceed();

        long duration = System.currentTimeMillis() - start;
        Log.d(TAG, "⬅️ Exit: " + method + " (" + duration + "ms)");

        return result;
    }

}
