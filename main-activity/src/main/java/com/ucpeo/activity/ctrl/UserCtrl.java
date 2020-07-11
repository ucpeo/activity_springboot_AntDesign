package com.ucpeo.activity.ctrl;

import com.ucpeo.activity.bean.Resp;
import com.ucpeo.activity.bean.User;
import com.ucpeo.activity.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
public class UserCtrl {
    @Resource
    UserService userService;

    @PostMapping("reg")
    public Resp<User> create(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUsername())||StringUtils.isEmpty(user.getFullName())){
            return  new Resp<>("请正确填写信息",409,null);
        }
        if (userService.getByUsername(user.getUsername())!=null){
            return  new Resp<>("用户已存在",409,null);
        }
        userService.create(user);
        return new Resp<>("注册成功", 0, user);
    }


    @GetMapping
    public Resp<User> myInfo(@SessionAttribute("loginUser") User user) {
        return new Resp<>("suceess", 0, userService.get(user.getId()));
    }

    @PostMapping("changePassword")
    public Resp changePassword(@SessionAttribute("loginUser") User user, HttpSession session, String old, String psw) {
        User user1 = userService.get(user.getId());
        //System.out.println(user1.getPassword()+":"+old+"\tnew :" + psw);
        if (user1.getPassword().equals(old)) {
            if (StringUtils.isEmpty(psw) || (psw.length() < 6) || (psw.length() > 16))
                return new Resp("密码要求6-16位", 409, null);

            user1.setPassword(psw);
            userService.update(user1);
            session.removeAttribute("loginUser");
            return new Resp();
        }
        return new Resp("密码验证失败", 409, null);
    }

}
