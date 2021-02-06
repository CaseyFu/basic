package org.casey.basic.guava;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @ClassName AvoidNullTest
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/2/6 21:20
 */

public class AvoidNullTest {
    @Test
    public void optionalTest() {
        // Optional<Integer> i = Optional.empty();
        Optional<Integer> i = Optional.of(1);
        i.ifPresent(e -> {
            System.out.println(i.get());
        });
    }

    @Test
    public void preconditionsTest() {
        // 为false时触发
        // Preconditions.checkNotNull(null, "this is null!");
        Preconditions.checkArgument(1 < 0, "1<0!");
    }
}
