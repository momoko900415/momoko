package com.leon.security.exception;

/**
 * @Auther: chongwang
 * @Date: 2018/5/6 13:52
 */
public class UserNotExistException extends RuntimeException {

    private String id;

    public UserNotExistException(String id){
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
