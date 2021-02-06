package org.casey.basic.benchmark.other;

/**
 * @ClassName LazySingleton
 * @Author Fu Kai
 * @Description 懒汉式的单例 非线程安全
 * @Date 2021/1/11 10:13
 */

public class LazySingleton {

    /**
     * 指向自己实例的私有静态引用
     */
    private static LazySingleton lazySingleton;

    /**
     * 私有的构造方法
     */
    private LazySingleton() {
    }

    /**
     * 以自己实例为返回值的静态的公有方法，静态工厂方法return
     */
    public static LazySingleton getLazySingleton() {
        // 被动创建，在真正需要使用时才去创建
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
