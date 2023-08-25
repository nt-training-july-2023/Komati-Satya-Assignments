package com.example.demo.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Responsee {
	
	public static ResponseEntity<Object> generateResponce(String message,HttpStatus status,String Obj,Object res){
		Map<String,Object> m=new HashMap<String,Object>();
		m.put(Obj, res);
		m.put("message",message);
		m.put("status", status.value());
		return new ResponseEntity<Object>(m,status);
	}
	
	
	

}
