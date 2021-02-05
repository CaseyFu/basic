package org.casey.basic.util.hutool;

import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

/**
 * @ClassName Uuid
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description todo
 * @Date 2020/7/27
 */

public class Uuid {
    @Test
    public void test(){
        System.err.println(RandomUtil.randomLong());
    }

}
