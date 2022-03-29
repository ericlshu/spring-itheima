package com.eric.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 13:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    private Integer id;
    private String type;
    private String name;
    private String description;
}
