package com.eric.a23;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class MyDateFormatter implements Formatter<Date>
{
    private final String desc;
    private final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy|MM|dd");

    public MyDateFormatter(String desc)
    {
        this.desc = desc;
    }

    @Override
    public String print(Date date, Locale locale)
    {
        log.debug("print >>>>>> 进入了: {}", desc);
        return SIMPLE_DATE_FORMAT.format(date);
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException
    {
        log.debug("parse >>>>>> 进入了: {}", desc);
        return SIMPLE_DATE_FORMAT.parse(text);
    }
}
