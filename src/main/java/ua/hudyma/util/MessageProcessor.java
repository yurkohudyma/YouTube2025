package ua.hudyma.util;

import lombok.extern.log4j.Log4j2;
import ua.hudyma.domain.BaseEntity;

import java.util.List;

@Log4j2
public class MessageProcessor {
    public static <T> String getReturnMessage(T t,
                                              String fieldName)
            throws Exception {
        var getterName = "get" + Character.toUpperCase(fieldName.charAt(0))
                + fieldName.substring(1);
        Object fieldValue = null;
        String className = null;
        if (t instanceof BaseEntity) {
            var getFieldMethod = t.getClass().getMethod(getterName);
            className = t.getClass().getSimpleName();
            fieldValue = getFieldMethod.invoke(t);
        } else if (t instanceof List<?> && ((List<?>) t).size() > 0) {
            className = ((List<?>) t).get(0).getClass().getSimpleName();
            fieldValue = ((List<?>) t).size();
        }
        var msg = String.format("%s [%s] додано до бази",
                className,
                fieldValue);
        log.info(msg);
        return msg;
    }
}
