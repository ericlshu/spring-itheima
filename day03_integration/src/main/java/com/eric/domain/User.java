package com.eric.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-19 20:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    Integer id;
    String username;
    String password;
    String name;
    Integer age;
    String email;
}
