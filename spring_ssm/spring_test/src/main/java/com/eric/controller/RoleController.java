package com.eric.controller;

import com.eric.domain.Role;
import com.eric.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-09 14:51
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @RequestMapping("/list")
    public ModelAndView list()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/list";
    }
}
