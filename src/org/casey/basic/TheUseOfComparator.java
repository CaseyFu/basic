package org.casey.basic;



import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TheUseOfComparator
 * @Author Fu Kai
 * @Description Comparator的使用进行对象的排序
 * @Date 2021/2/5 14:21
 */

public class TheUseOfComparator {

    @Data
    @ToString
    static class User{
        int id;
        String name;

    }
    @Test
    public void main(){
        List<User> userList = new ArrayList<>();
        TheUseOfComparator.User user = new User();
        user.id = 1;
        user.name = "Fu Kai";
        System.out.println(user);
    }
}

