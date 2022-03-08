package com.eric.controller;

import com.eric.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-08 13:33
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 返回字符串形式
     * <p>
     * 此种方式会将返回的字符串与视图解析器的前后缀拼接后跳转
     * forward:/WEB-INF/views/index.jsp
     * redirect:/index.jsp
     *
     * @return success.jsp
     */
    @RequestMapping("/save1")
    public String save1()
    {
        System.out.println("1. save user in controller ...");
        return "success";
    }

    /**
     * 返回ModelAndView对象
     *
     * @return ModelAndView
     */
    @RequestMapping("/save2")
    public ModelAndView save2()
    {
        System.out.println("2. save user in controller ...");

        /*
         * Model: 模型 封装数据
         * View : 视图 展示数据
         */
        ModelAndView modelAndView = new ModelAndView();
        // 添加模型
        modelAndView.addObject("username", "Eric2");
        // 设置视图
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * 携带ModelAndView参数
     * 返回ModelAndView对象
     *
     * @param modelAndView 传入参数
     * @return 模型和视图对象
     */
    @RequestMapping("/save3")
    public ModelAndView save3(ModelAndView modelAndView)
    {
        System.out.println("3. save user in controller ...");
        modelAndView.addObject("username", "Eric3");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * 携带模型参数并返回字符串视图
     *
     * @param model 传入模型参数
     * @return 字符串视图
     */
    @RequestMapping("/save4")
    public String save4(Model model)
    {
        System.out.println("4. save user in controller ...");
        model.addAttribute("username", "Eric4");
        return "success";
    }

    /**
     * 向request域存储数据
     *
     * @param request request请求域对象
     * @return 字符串视图
     */
    @RequestMapping("/save5")
    public String save5(HttpServletRequest request)
    {
        System.out.println("5. save user in controller ...");
        request.setAttribute("username", "Eric5");
        return "success";
    }

    /**
     * 返回字符串 - 通过response域对象
     *
     * @param response response响应域对象
     * @throws IOException 读写异常
     */
    @RequestMapping("/save6")
    public void save6(HttpServletResponse response) throws IOException
    {
        System.out.println("6. save user in controller ...");
        response.getWriter().write("Hello Eric6");
    }

    /**
     * 返回字符串 - 直接返回
     */
    @RequestMapping("/save7")
    @ResponseBody
    public String save7()
    {
        System.out.println("7. save user in controller ...");
        return "Hello springMVC!!!";
    }

    /**
     * 返回字符串 - json格式字符串
     */
    @RequestMapping("/save8")
    @ResponseBody
    public String save8()
    {
        System.out.println("8. save user in controller ...");
        return "{\"name\":\"ZhangSan\",\"age\":18}";
    }

    /**
     * 返回字符串 - 对象转json字符串
     */
    @RequestMapping("/save9")
    @ResponseBody
    public String save9() throws JsonProcessingException
    {
        User user = new User("eric", 28);
        System.out.println("user = " + user);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        System.out.println("9. save user in controller ...");
        return json;
    }

    /**
     * 返回对象或集合
     * 期望SpringMVC自动帮忙将对象转成json字符串
     * @return user对象
     */
    @RequestMapping("/save10")
    @ResponseBody
    public User save10()
    {
        System.out.println("10. save user in controller ...");
        User user = new User("eric", 28);
        System.out.println("user = " + user);
        return user;
    }
}
