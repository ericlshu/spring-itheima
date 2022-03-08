package com.eric.controller;

import com.eric.domain.User;
import com.eric.domain.VO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
     * 1.1 返回字符串形式
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
     * 1.2 返回ModelAndView对象
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
     * 1.3
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
     * 1.4 携带模型参数并返回字符串视图
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
     * 1.5 向request域存储数据
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
     * 1.6 返回字符串 - 通过response域对象
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
     * 1.7 返回字符串 - 直接返回
     */
    @RequestMapping("/save7")
    @ResponseBody
    public String save7()
    {
        System.out.println("7. save user in controller ...");
        return "Hello springMVC!!!";
    }

    /**
     * 1.8 返回字符串 - json格式字符串
     */
    @RequestMapping("/save8")
    @ResponseBody
    public String save8()
    {
        System.out.println("8. save user in controller ...");
        return "{\"name\":\"ZhangSan\",\"age\":18}";
    }

    /**
     * 1.9 返回字符串 - 对象转json字符串
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
     * 1.10 返回对象或集合
     * 期望SpringMVC自动帮忙将对象转成json字符串
     *
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

    /**
     * 2.1 获得基本类型参数
     *
     * @param username username
     * @param age      age
     */
    @RequestMapping("/save11")
    public String save11(String username, int age)
    {
        System.out.println("11. save user in controller ...");
        User user = new User(username, age);
        System.out.println("user = " + user);
        return "success";
    }

    /**
     * 2.2 获得POJO类型参数
     *
     * @param user user
     * @return result view
     */
    @RequestMapping("/save12")
    public String save12(User user)
    {
        System.out.println("12. save user in controller ...");
        System.out.println("user = " + user);
        return "success";
    }

    /**
     * 2.3 获得数组类型参数
     *
     * @param strList 数组类型参数
     */
    @RequestMapping("/save13")
    @ResponseBody
    public void save13(String[] strList)
    {
        System.out.println("13 save user in controller ...");
        System.out.println(Arrays.asList(strList));
    }

    /**
     * 2.4 获得集合类型参数
     */
    @RequestMapping("/save14")
    @ResponseBody
    public void save14(VO vo)
    {
        System.out.println("14 save user in controller ...");
        System.out.println("vo = " + vo);
    }

    /**
     * 2.5 获得集合类型参数
     */
    @RequestMapping("/save15")
    @ResponseBody
    public void save15(@RequestBody List<User> userList)
    {
        System.out.println("15 save user in controller ...");
        System.out.println("userList = " + userList);
    }

    /**
     * 2.6 参数绑定注解 @RequestParam
     */
    @RequestMapping("/save16")
    @ResponseBody
    public void save16(@RequestParam(value = "name", required = false, defaultValue = "eric") String username)
    {
        System.out.println("16 save user in controller ...");
        System.out.println("username = " + username);
    }

    /**
     * 2.7 获得Restful风格的参数
     * GET    ：用于获取资源
     * POST   ：用于新建资源
     * PUT    ：用于更新资源
     * DELETE ：用于删除资源
     * PathVariable注解进行占位符的匹配获取工作
     */
    @RequestMapping("/save17/{name}")
    @ResponseBody
    public void save17(@PathVariable("name") String name)
    {
        System.out.println("17 save user in controller ...");
        System.out.println("name = " + name);
    }

    /**
     * 2.8 自定义类型转换器
     */
    @RequestMapping("/save18")
    @ResponseBody
    public void save18(Date date)
    {
        System.out.println("18 save user in controller ...");
        System.out.println("date = " + date);
    }

    /**
     * 2.9 获得Servlet相关API
     */
    @RequestMapping("/save19")
    @ResponseBody
    public void save19(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        System.out.println("19 save user in controller ...");
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        System.out.println("session = " + session);
    }

    /**
     * 2.10 获得请求头 RequestHeader
     *
     * @param userAgent user_agent
     */
    @RequestMapping("/save20")
    @ResponseBody
    public void save20(@RequestHeader(value = "User-Agent", required = false) String userAgent)
    {
        System.out.println("20 save user in controller ...");
        System.out.println("user_agent = " + userAgent);
    }

    /**
     * 2.11 获得请求头 CookieValue
     *
     * @param jSessionId JSESSIONID
     */
    @RequestMapping("/save21")
    @ResponseBody
    public void save21(@CookieValue(value = "JSESSIONID", required = false) String jSessionId)
    {
        System.out.println("21 save user in controller ...");
        System.out.println("jSessionId = " + jSessionId);
    }

    /**
     * 2.12 单文件上传
     * 表单项type=“file”
     * 表单的提交方式是post
     * 表单的enctype属性是多部分表单形式，及enctype=“multipart/form-data”
     */
    @RequestMapping("/save22")
    @ResponseBody
    public void save22(String name, MultipartFile file) throws IOException
    {
        System.out.println("22 save user in controller ...");
        System.out.println("name = " + name);
        System.out.println("file = " + file);
        //获得文件名称
        String originalFilename = file.getOriginalFilename();
        //保存文件
        file.transferTo(new File("D:\\upload\\" + originalFilename));
    }

    /**
     * 2.13 多文件上传逐个接收
     */
    @RequestMapping("/save23")
    @ResponseBody
    public void save23(String name1, MultipartFile file1, String name2, MultipartFile file2) throws IOException
    {
        System.out.println("23 save user in controller ...");
        System.out.println("name1 = " + name1);
        System.out.println("file1 = " + file1);
        System.out.println("name2 = " + name2);
        System.out.println("file2 = " + file2);
        //获得文件名称
        String originalFilename1 = file1.getOriginalFilename();
        String originalFilename2 = file2.getOriginalFilename();
        //保存文件
        file1.transferTo(new File("D:\\upload\\" + originalFilename1));
        file2.transferTo(new File("D:\\upload\\" + originalFilename2));
    }

    /**
     * 2.14 多文件上数组接收
     */
    @RequestMapping("/save24")
    @ResponseBody
    public void save24(String name, MultipartFile[] files) throws IOException
    {
        System.out.println("24 save user in controller ...");
        System.out.println("name = " + name);
        for (MultipartFile file : files)
        {
            String originalFilename = file.getOriginalFilename();
            System.out.println("originalFilename = " + originalFilename);
            file.transferTo(new File("D:\\upload\\" + originalFilename));
        }
    }
}
