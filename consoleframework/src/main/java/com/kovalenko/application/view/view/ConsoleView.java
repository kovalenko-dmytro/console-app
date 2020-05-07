package com.kovalenko.application.view.view;

public abstract class ConsoleView<T> implements View {

    private ResponseStatus responseStatus;
    private String errorMessage;
    private T body;

    public ConsoleView() {
    }

    public ConsoleView(ResponseStatus responseStatus, String errorMessage, T body) {
        this.responseStatus = responseStatus;
        this.errorMessage = errorMessage;
        this.body = body;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
