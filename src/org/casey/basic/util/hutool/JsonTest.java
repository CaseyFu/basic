package org.casey.basic.util.hutool;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JsonTest
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description hutool测试json
 * @Date 2020/8/2
 */

public class JsonTest {
    @Test
    public void test1() {
        String str = "1234";
        System.out.println(JSONUtil.parse(str).toStringPretty());
    }




















    @Test
    public void test2() {
        String str = "1234";
        Dict dict = Dict.create();
        dict.put("data", str);
        System.out.println(JSONUtil.parse(dict).toStringPretty());
    }

}
