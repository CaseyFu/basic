package org.casey.basic.benchmark.other;

/**
 * @ClassName StarvedSingleton
 * @Author Fu Kai
 * @Description 饿汉式的单例 线程安全
 * @Date 2021/1/11 10:12
 */

public class StarvedSingleton {
    /**
     * 指向自己实例的私有静态引用，主动创建
     */
    private static final StarvedSingleton STARVED_SINGLETON = new StarvedSingleton();

    /**
     * 私有的构造方法
     */
    private StarvedSingleton() {
    }

    /**
     * 以自己实例为返回值的静态的公有方法，静态工厂方法
     */
    public static StarvedSingleton getStarvedSingleton() {
        return STARVED_SINGLETON;
    }
}
