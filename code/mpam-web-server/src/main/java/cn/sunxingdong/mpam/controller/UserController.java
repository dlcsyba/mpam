package cn.sunxingdong.mpam.controller;

import cn.sunxingdong.mpam.biz.intf.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ModelMap model) {
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUser() {
        return "";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String modUser() {
        return "";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUserInfo() {
        return "";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String delUser(@PathVariable("id") Long id) {
        return "";
    }

}
