package com.example.demo.response;

import java.util.Objects;

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
        /**
         * hash code.
         */
        @Override
        public int hashCode() {
            return Objects.hash(code, data, message);
        }
        /**
         * equals method.
         */
        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Response other = (Response) obj;
            return code == other.code && Objects.equals(data, other.data)
                    && Objects.equals(message, other.message);
        }
}

