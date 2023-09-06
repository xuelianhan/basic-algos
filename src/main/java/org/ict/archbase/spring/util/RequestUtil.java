package org.ict.archbase.spring.util;

import java.util.UUID;

/**
 * @author sniper
 * @date 19 Jul 2023
 */
public class RequestUtil {

    public static final String REQ_ID_HEADER = "Request-Id";

    private static final ThreadLocal<String> reqIdThreadLocal = new ThreadLocal<>();

    public static void setRequestId(String requestId) {
        reqIdThreadLocal.set(requestId);
    }

    public static String getRequestId(){
        String requestId = reqIdThreadLocal.get();
        if(requestId == null) {
            requestId = UUID.randomUUID().toString();
            reqIdThreadLocal.set(requestId);
        }
        return requestId;
    }

    public static void removeRequestId() {
        reqIdThreadLocal.remove();
    }
}
