package org.casey.basic.util.mapStruct;

import lombok.*;

import java.util.Date;

/**
 * @ClassName PersonDo
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description PersonDo
 * @Date 2020/8/12
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PersonDo {
    private Long id;
    private String name;
    private Integer age;
    private Date birthday;
    private String gender;
    private HomeAddress homeAddress;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
class HomeAddress {
    private String avenue;
    private String street;
}
