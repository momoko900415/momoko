package com.leon.security.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.leon.security.validator.MyConsraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Auther: chongwang
 * @Date: 2018/4/24 21:17
 */
public class User {
    public interface userNameSimpleView{};
    public interface userNameDetailView extends userNameSimpleView{};

    private String id;

    @MyConsraint(message = "这是一个测试")
    private String userName;

    @NotBlank
    private String passWord;

    @Past(message = "生日必须是过去的时间")
    private Date birthday;

    public User() {
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(userNameSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonView(userNameDetailView.class)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
