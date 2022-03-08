package com.eric.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-08 21:28
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source)
    {
        System.out.println("Converting date in com.eric.converter.DateConverter ...");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try
        {
            date = sdf.parse(source);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }
}
