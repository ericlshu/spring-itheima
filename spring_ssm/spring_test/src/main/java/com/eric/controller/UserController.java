package com.eric.controller;

import com.eric.domain.Role;
import com.eric.domain.User;
import com.eric.service.RoleService;
import com.eric.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-09 16:36
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @RequestMapping("/list")
    public ModelAndView list()
    {
        List<User> userList = userService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }


    @RequestMapping("/saveUI")
    public ModelAndView saveUI()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }


    @RequestMapping("/save")
    public String save(User user, Long[] roleIds)
    {
        System.err.println("user = " + user);
        System.err.println("roleIds = " + Arrays.toString(roleIds));
        userService.save(user, roleIds);
        return "redirect:/user/list";
    }

    @RequestMapping("/delete/{userId}")
    public String delete(@PathVariable Long userId)
    {
        userService.delete(userId);
        return "redirect:/user/list";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session)
    {
        User user = userService.login(username, password);
        if (user != null)
        {
            session.setAttribute("user", user);
            return "redirect:/index.jsp";
        }
        return "redirect:/login.jsp";
    }
}
