package com.eric.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 *
 * @author Eric L SHU
 * @ TableId(type = IdType.AUTO)
 *
 * mybatis-plus:
 *   global-config:
 *     db-config:
 *       id-type: AUTO
 *
 * @date 2022-03-20 13:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    /**
     * @ TableId(type = IdType.AUTO)
     *
     * mybatis-plus:
     *   global-config:
     *     db-config:
     *       id-type: AUTO
     */
    private Integer id;
    private String type;
    private String name;
    private String description;
}
