package com.eric.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-08 22:05
 * @since jdk-11.0.14
 */
@ConfigurationProperties(prefix = "tools.ip")
public class IpProperties
{
    /**
     * 日志显示周期，默认为10s
     */
    private Long interval = 10L;

    /**
     * 是否周期内重置数据，默认不重置
     */
    private Boolean resetFlag = false;

    /**
     * 日志输出模式
     * detail：详细模式
     * simple：极简模式
     */
    private String logMode = LogMode.DETAIL.value;

    public enum LogMode
    {
        DETAIL("detail"),
        SIMPLE("simple");

        private final String value;

        LogMode(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }
    }

    public Long getInterval()
    {
        return interval;
    }

    public void setInterval(Long interval)
    {
        this.interval = interval;
    }

    public Boolean getResetFlag()
    {
        return resetFlag;
    }

    public void setResetFlag(Boolean resetFlag)
    {
        this.resetFlag = resetFlag;
    }

    public String getLogMode()
    {
        return logMode;
    }

    public void setLogMode(String logMode)
    {
        this.logMode = logMode;
    }
}
