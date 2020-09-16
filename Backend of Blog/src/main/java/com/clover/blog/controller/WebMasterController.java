package com.clover.blog.controller;

import com.clover.blog.entity.Result;
import com.clover.blog.entity.User;
import com.clover.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("webmaster")
@CrossOrigin
@Slf4j
public class WebMasterController {

    private final UserService userService;

    public WebMasterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("info")
    @ResponseBody
    public Result<User> info(String id){
        if (id == null)
            log.info("获取站长信息");
        else
            log.info("获取用户"+id+"信息");
        try {
            if (id != null) {
                User userDB = userService.findUserById(id);
                if (userDB != null) {
                    userDB.setPassword("");
                    userService.addView(userDB.getId());
                    return Result.success("用户"+id+"信息", userDB);
                }
            }
            User webMaster = userService.findUserById("0");
            webMaster.setPassword("");
            userService.addView(webMaster.getId());
            return Result.success("站长信息", webMaster);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("allUser")
    @ResponseBody
    public Result<User[]> allUser(){
        log.info("获取用户列表");
        try {
            User[] users = userService.allUser();
            return Result.success("全部用户信息", users);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
}
