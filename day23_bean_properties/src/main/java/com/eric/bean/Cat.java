package com.eric.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-06 21:16
 * @since jdk-11.0.14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat
{
    private String name;
    private Integer age;
}
