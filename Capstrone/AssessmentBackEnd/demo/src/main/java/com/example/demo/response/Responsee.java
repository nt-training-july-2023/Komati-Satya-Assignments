package com.example.demo.response;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * response class.
 */
public final class Responsee {
    /**
     * default constructor.
     */
    private Responsee() {
    }
    /**
     * generateResponce class.
     * @param message message
     * @param status  status
     * @param obj object variable
     * @param res  response
     * @return response
     */
    public static ResponseEntity<Object> generateResponce(
            final String message, final HttpStatus status, final String obj,
            final Object res) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put(obj, res);
        m.put("message", message);
        m.put("status", status.value());
        return new ResponseEntity<Object>(m, status);
    }
}
