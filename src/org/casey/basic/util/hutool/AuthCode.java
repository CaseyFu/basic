package org.casey.basic.util.hutool;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.captcha.LineCaptcha;
import org.junit.Test;

/**
 * @ClassName AuthCode
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 使用hutool生成验证码
 * @Date 2020/7/24
 */

public class AuthCode {
    /**
     * 线段干扰验证码
     */
    @Test
    public void lineCaptcha(){
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 6, 100);
        lineCaptcha.write("F:\\repository\\java\\basic\\src\\org\\casey\\basic\\hutool\\img\\lineCaptcha.png");
        System.out.println(lineCaptcha.getCode());
    }

    // @Test
    // public void verify(){
    //     ICaptcha captcha =
    // }
}
