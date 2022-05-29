package com.eric.controller;

import com.eric.exception.BusinessException;
import com.eric.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description : 统一异常处理
 * <p>
 * 异常的种类及出现异常的原因:
 * - 框架内部抛出的异常：因使用不合规导致
 * - 数据层抛出的异常：因外部服务器故障导致(例如：服务器访问超时)
 * - 业务层抛出的异常：因业务逻辑书写错误导致(例如：遍历业务书写操作，导致索引异常等)
 * - 表现层抛出的异常：因数据收集、校验等规则导致(例如：不匹配的数据类型间导致异常)
 * - 工具类抛出的异常：因工具类书写不严谨不够健壮导致(例如：必要释放的连接长期未释放等)
 * <p>
 * -> @RestControllerAdvice用于标识当前类为REST风格对应的异常处理器
 * -->> 此注解自带@ResponseBody注解与@Component注解，具备对应的功能
 * -> @ExceptionHandler除了自定义的异常处理器，保留对Exception类型的异常处理，用于处理非预期的异常
 * -->> 设置指定异常的处理方案，功能等同于控制器方法，出现异常后终止原始控制器执行,并转入当前方法执行
 * <p>
 * #### 异常解决方案
 * <p>
 * -> 业务异常(BusinessException)
 * -->> 发送对应消息传递给用户，提醒规范操作
 * ---->> 大家常见的就是提示用户名已存在或密码格式不正确等
 * -> 系统异常(SystemException)
 * -->> 发送固定消息传递给用户，安抚用户
 * ---->> 系统繁忙，请稍后再试
 * ---->> 系统正在维护升级，请稍后再试
 * ---->> 系统出问题，请联系系统管理员等
 * -->> 发送特定消息给运维人员，提醒维护
 * ---->> 可以发送短信、邮箱或者是公司内部通信软件
 * -->> 记录日志
 * ---->> 发消息和记录日志对用户来说是不可见的，属于后台程序
 * ->> 其他异常(Exception)
 * -->> 发送固定消息传递给用户，安抚用户
 * -->> 发送特定消息给编程人员，提醒维护(纳入预期范围内)
 * ---->> 一般是程序没有考虑全，比如未做非空校验等
 * -->> 记录日志
 *
 * @author Eric SHU
 */
@RestControllerAdvice
public class ProjectExceptionAdvice
{
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex)
    {
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex)
    {
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    /**
     * 除了自定义的异常处理器，保留对Exception类型的异常处理，用于处理非预期的异常
     */
    @ExceptionHandler(Exception.class)
    public Result doOtherException(Exception ex)
    {
        ex.printStackTrace();
        return new Result(Code.SYSTEM_UNKNOWN_ERR, null, "系统繁忙，请稍后再试！");
    }
}
