package com.eric.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Description : 工作流程解析
 * <p>
 * 启动服务器初始化过程
 * 1. 服务器启动，执行ServletContainersInitConfig类，初始化web容器
 * -->> 功能类似于以前的web.xml
 * 2. 执行createServletApplicationContext方法，创建了WebApplicationContext对象
 * -->> 该方法加载SpringMVC的配置类SpringMvcConfig来初始化SpringMVC的容器
 * 3. 加载SpringMvcConfig配置类
 * 4. 执行@ComponentScan加载对应的bean
 * -->> 扫描指定包及其子包下所有类上的注解，如Controller类上的@Controller注解
 * 5. 加载UserController，每个@RequestMapping的名称对应一个具体的方法
 * -->> 此时就建立了 `/save` 和 save方法的对应关系
 * 6. 执行getServletMappings方法，设定SpringMVC拦截请求的路径规则
 * -->> `/`代表所拦截请求的路径规则，只有被拦截后才能交给SpringMVC来处理请求
 * <p>
 * 单次请求过程
 * 1. 发送请求`http://localhost/save`
 * 2. web容器发现该请求满足SpringMVC拦截规则，将请求交给SpringMVC处理
 * 3. 解析请求路径/save
 * 4. 由/save匹配执行对应的方法save(）
 * -->> 上面的第五步已经将请求路径和方法建立了对应关系，通过/save就能找到对应的save方法
 * 5. 执行save()
 * 6. 检测到有@ResponseBody直接将save()方法的返回值作为响应体返回给请求方
 *
 * @author Eric SHU
 */
@Configuration
@ComponentScan("com.eric.controller")
public class SpringMVCConfig
{
}
