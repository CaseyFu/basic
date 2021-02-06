package org.casey.basic.benchmark.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName User
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/11 8:54
 */

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Long id;
    private String name;
    private Integer age;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime birthday;
}
