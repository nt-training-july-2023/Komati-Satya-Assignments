package com.example.demo.exceptions;



public class ErrorResponse<T> {
    /**
     * error Code.
     */
    private Integer code;
    /**
     * error Message.
     */
    private String message;
   

   

    /**
     * @param code    errorCode
     * @param message errorMessage constructor using fields.
     */
    public ErrorResponse(final Integer code, final String message) {
        super();
        this.code = code;
        this.message = message;
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
    public Integer getCode() {
        return code;
    }

    /**
     * @param code
     * setter for errorCode.
     */
    public void setCode(final Integer code) {
        this.code = code;
    }

    /**
     * @return message
     * getter for errorMessage.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     * errorMessage setter for errorMessage.
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}

