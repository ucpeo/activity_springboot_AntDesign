package com.ucpeo.web;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
public class WebCtrl {
    @RequestMapping("/*")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
     return modelAndView;
    }
}
