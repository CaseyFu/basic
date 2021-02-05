package org.casey.basic;

import org.junit.Test;

/**
 * @ClassName BigDecimalTest
 * @Author Casey Fu
 * @Description BigDecimal备忘录
 * @Date 2020/7/6 15:41
 */
public class BigDecimalTest {


    /**
     * 使用浮点数的误差
     */
    @Test
    public void test() {
        float f = 1.0f - 0.9f;
        double d = 1.0f - 0.9f;
        System.out.println("结果本该是0.1, 但结果却是:");
        System.err.println(f);
        System.out.println(d);
    }

    
}
