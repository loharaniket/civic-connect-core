package com.civic_connect_core.app.exceptions;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String msg) {
        super(msg);
    }
}
