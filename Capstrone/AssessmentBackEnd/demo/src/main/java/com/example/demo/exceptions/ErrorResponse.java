package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    /**
     * error Code.
     */
    private HttpStatus errorCode;

    /**
     * error Message.
     */
    private String errorMessage;

    /**
     * @param code    errorCode
     * @param message errorMessage constructor using fields.
     */
    public ErrorResponse(final HttpStatus code, final String message) {
        super();
        this.errorCode = code;
        this.errorMessage = message;
    }

    /**
     * constructor.
     */
    public ErrorResponse() {
        super();
    }

    /**
     * @return errorCode.
     */
    public HttpStatus getErrorCode() {
        return errorCode;
    }

    /**
     * @param code
     * setter for errorCode.
     */
    public void setErrorCode(final HttpStatus code) {
        this.errorCode = code;
    }

    /**
     * @return message
     * getter for errorMessage.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param message
     * errorMessage setter for errorMessage.
     */
    public void setErrorMessage(final String message) {
        this.errorMessage = message;
    }
}
