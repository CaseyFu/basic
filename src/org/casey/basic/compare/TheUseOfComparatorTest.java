package org.casey.basic.compare;

import org.junit.Test;
import org.mapstruct.ap.shaded.freemarker.template.utility.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName TheUseOfComparatorTest
 * @Author Fu Kai
 * @Description Comparator的用法
 * @Date 2021/2/5 14:57
 */

public class TheUseOfComparatorTest {
    @Test
    public void compareToTest(){
        Integer i = 1;
        Integer j = 1;
        Integer k = 2;
        System.out.println(i.compareTo(j));
        System.out.println(i.compareTo(k));
        System.out.println(k.compareTo(i));
    }

    @Test
    public void mainTest(){
        UserComparator user0 = new UserComparator(4L, "Fu Kai0", 19, true, LocalDateTime.now());
        UserComparator user1 = new UserComparator(9L, "Fu Kai1", 16, true, LocalDateTime.now());
        UserComparator user2 = new UserComparator(1L, "Fu Kai2", 20, true, LocalDateTime.now());
        UserComparator user3 = new UserComparator(0L, "Fu Kai3", 22, true, LocalDateTime.now());
        UserComparator user4 = new UserComparator(5L, "Fu Kai3", 21, true, LocalDateTime.now());
        List<UserComparator> userList = new ArrayList<>();
        userList.add(user0);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        // 根据id升序
        userList.sort(Comparator.comparing(UserComparator::getId));
        // userList.sort((p1, p2)-> p2.getId().compareTo(p1.getId()));
        System.out.println(userList);

        UserComparator[] userArray = {user0, user1, user2, user3, user4};
        // 根据age降序
        Arrays.sort(userArray, (o1, o2)->o2.getAge().compareTo(o1.getAge()));
        System.out.println(Arrays.toString(userArray));
    }

}
