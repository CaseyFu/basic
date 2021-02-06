package org.casey.basic.benchmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.casey.basic.benchmark.entity.User;
import org.junit.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @ClassName StringBenchmark
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/4 10:52
 */

public class JacksonTest {

    private User user;

    @Test
    public void singletonObjectMapper() throws JsonProcessingException {
        User user = new User();
        user.setId(1L);
        user.setName("Fu Kai");
        user.setAge(21);
        user.setBirthday(LocalDateTime.now());
        this.user = user;

        ObjectMapper objectMapper = new ObjectMapper();
        // String userString = objectMapper.writeValueAsString(user);
        // System.out.println(userString);
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                System.out.println("hhh");
                try {
                    String userString = objectMapper.writeValueAsString(user);
                    System.out.println(userString);
                } catch (JsonProcessingException e) {
                    System.out.println("JsonProcessingException: " + e.getMessage());
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    @Test
    public void propertyObjectMapper() {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String userString = objectMapper.writeValueAsString(user);
                    System.out.println(userString);
                } catch (JsonProcessingException e) {
                    System.out.println("JsonProcessingException: " + e.getMessage());
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    @Test
    public void localDateTest() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
    }

}
