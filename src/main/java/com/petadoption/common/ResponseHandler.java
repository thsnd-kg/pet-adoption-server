package com.petadoption.common;

import com.petadoption.common.util.DateUtils;
import com.petadoption.common.util.ErrorUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> getResponse(Object content, HttpStatus status){
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("errors", "");
        map.put("timestamp", DateUtils.toString(LocalDateTime.now()));
        map.put("status", status.value());
        map.put("success", true);

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> getResponse(BindingResult errors, HttpStatus status){
        Map<String, Object> map = new HashMap<>();
        map.put("data", "");
        map.put("errors", ErrorUtils.getErrorMessages(errors));
        map.put("timestamp", DateUtils.toString(LocalDateTime.now()));
        map.put("status", status.value());
        map.put("success", false);

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> getResponse(Exception e, HttpStatus status){
        Map<String, Object> map = new HashMap<>();
        map.put("data", "");
        map.put("errors", e.getMessage());
        map.put("timestamp", DateUtils.toString(LocalDateTime.now()));
        map.put("status", status.value());
        map.put("success", false);

        return new ResponseEntity<>(map, status);
    }

    public static Object getResponse(HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", "");
        map.put("errors", "");
        map.put("timestamp", DateUtils.toString(LocalDateTime.now()));
        map.put("status", status.value());

        return new ResponseEntity<Object>(map, status);
    }
}
