package org.casey.basic.util.mapStruct;

import lombok.*;

import java.util.Date;

/**
 * @ClassName PersonDto
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description PersonDto
 * @Date 2020/8/12
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PersonDto {
    private String username;
    private Integer age;
    private String birthday;
    private Boolean sex;
    private String address;
    private Integer weight; // 赋值常量
}

