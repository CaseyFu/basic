package org.casey.basic.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName UserComparator
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/2/5 14:51
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserComparator implements Serializable {
    private static final long serialVersionUID = 7417532992725007519L;
    private Long id;
    private String name;
    private Integer age;
    private Boolean enabled;
    private LocalDateTime birthday;
}
