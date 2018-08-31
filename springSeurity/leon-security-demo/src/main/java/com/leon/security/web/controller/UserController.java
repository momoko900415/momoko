package com.leon.security.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.leon.security.dto.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: chongwang
 * @Date: 2018/4/24 21:14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping()
    public User create(@Valid  @RequestBody  User user, BindingResult errors){
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error-> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getBirthday());
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());
        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid  @RequestBody  User user, BindingResult errors){
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error->
                    {
                        FieldError fieldError = (FieldError)error;
                        String message = fieldError.getField() +"" + error.getDefaultMessage();
                        System.out.println(message);
                    }
            );
        }
        System.out.println(user.getBirthday());
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());
        user.setId("1");
        return user;
    }


    @GetMapping
    @JsonView(User.userNameSimpleView.class)
    @ApiOperation(value = "查询学生信息")
    public List<User> query(@ApiParam(value = "用户id") @RequestParam(name = "userName" ,required = false,defaultValue = "leon") String nickName, Pageable page){
        System.out.println(nickName);
        /*System.out.println(page.getPageSize());
        System.out.println(page.getPageNumber());
        System.out.println(page.getSort());*/
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;

}

    /**
     *
     * @param id  正则
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @JsonView(User.userNameDetailView.class)
    public User getInfo(@PathVariable String id){
       System.out.println(id);
        System.out.println("进入info服务 ");
        User user = new User();
        user.setUserName("leon");
        user.setPassWord("900415");
        return user;
        //throw new UserNotExistException(id);
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println(id);
    }
}
