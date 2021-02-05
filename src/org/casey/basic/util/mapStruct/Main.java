package org.casey.basic.util.mapStruct;

import org.junit.Test;

import java.util.Date;

/**
 * @ClassName Main
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 启动类
 * @Date 2020/8/12
 */

public class Main {


    @Test
    public void run() {
        PersonDo personDo = PersonDo.builder()
                .id(1L)
                .name("kk")
                .age(19)
                .birthday(new Date())
                .gender("true")
                .homeAddress(new HomeAddress("回兴大道", "兰馨大道"))
                .build();
        PersonDto personDto = Converter.converter.personDo2personDto(personDo);
        System.out.println(personDto);
    }
}
