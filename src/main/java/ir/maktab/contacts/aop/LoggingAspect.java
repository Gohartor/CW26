package ir.maktab.contacts.aop;

import ir.maktab.contacts.dto.NewContactDTO;
import ir.maktab.contacts.dto.UpdateContactDTO;
import ir.maktab.contacts.entity.Contact;
import ir.maktab.contacts.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Marker;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {


    private final ThreadLocal<String> actionHolder = new ThreadLocal<>();
    private final ThreadLocal<Long> actionIdHolder = new ThreadLocal<>();
    private final ThreadLocal<Long> userIdHolder = new ThreadLocal<>();

//    Logger logger = LoggerFactory.getLogger(LoggingController.class);


    @Pointcut("@annotation(logContactAction)")
    public void allServiceMethod(LogContactAction logContactAction) {
    }


    @Before("allServiceMethod(logContactAction)")
    public void logBefore(JoinPoint joinPoint, LogContactAction logContactAction) {
//        System.out.println("Before method: " + joinPoint.getSignature().getName());

        String action = logContactAction.action();
        Long actionId = extractActionId(joinPoint.getArgs());
        Long userId = extractUserId();

        actionHolder.set(action);
        actionIdHolder.set(actionId);
        userIdHolder.set(userId);

        log.info("user {} – request for {} with id {}", userId, action, actionId);

//        Object[] args = joinPoint.getArgs();
//        Object target = joinPoint.getTarget();
//        User find = (User) target;
//        Long id = find.getId();
//
//        String actionName = joinPoint.getSignature().getName();
//
//        log.info("user id: " + id +
//                " request for: " + actionName +
//                " with action id: ");
    }


    private Long extractActionId(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof Long id) {
                return id;
            }
            if (arg instanceof UpdateContactDTO contactDto && contactDto.getId() != null) {
                return contactDto.getId();
            }
        }
        return 0L;
    }


    private Long extractUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User u) {
            return u.getId();
        }
        return 0L;
    }

//    @Around()


    @AfterReturning(pointcut = "allServiceMethod(logContactAction)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint,
                                  LogContactAction logContactAction,
                                  Object result) {


//        System.out.println("After method: " + joinPoint.getSignature().getName());

        log.info("user {} – {} with id {} (done)", userIdHolder.get(), actionHolder.get(), actionIdHolder.get());
        clearThreadLocal();
    }

    private void clearThreadLocal() {
        actionHolder.remove();
        actionIdHolder.remove();
        userIdHolder.remove();
    }


    @AfterThrowing(pointcut = "allServiceMethod(logContactAction)", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint,
                              Exception error,
                              LogContactAction logContactAction) {
//        System.out.println("After method: " + joinPoint.getSignature().getName() + " error: " + error.getMessage());

        log.info("user {} – {} with id {} (failed)", userIdHolder.get(), actionHolder.get(), actionIdHolder.get());
        clearThreadLocal();
    }
}
