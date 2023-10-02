package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



    @AllArgsConstructor
    @Setter
    @Getter
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class Response<T> {
        /**
         *statuscode attribute.
        */
        private int code;
        /**
         *message attribute.
        */
        private String message;
        /**
         *responseDate entity.
        */
        private T data;
        /**
         *parameter constructor.
         *@param statusCode statusCodeParam
         *@param messageParam messageParam
        */
        public Response(final int statusCode,
                final String messageParam) {
            super();
            this.code = statusCode;
            this.message = messageParam;
        }
}

