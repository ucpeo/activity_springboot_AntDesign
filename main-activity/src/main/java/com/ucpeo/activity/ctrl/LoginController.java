package com.ucpeo.activity.ctrl;

import com.ucpeo.activity.bean.Resp;
import com.ucpeo.activity.bean.User;

import com.ucpeo.activity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("user")
public class LoginController {
    @Autowired
    UserService userService;


    @RequestMapping("/login")
    public Resp login(HttpSession session,@RequestBody User user) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            System.out.println(loginUser.getFullName()+":登录成功");
            session.setAttribute("loginUser", loginUser);
            return new Resp();
        }
        return new Resp("密码不正确", 411, null);
    }

    @RequestMapping("/logout")
    public void  logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.removeAttribute("loginUser");
        response.sendRedirect("/login.html");
    }

    @RequestMapping("/loginUser")
    public Resp loginUser(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        Resp resp = new Resp();
        resp.setData(user);
        return  resp ;
    }


}