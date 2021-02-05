package org.casey.basic.common;

/**
 * @ClassName ArrayCopy
 * @Author Casey Fu
 * @Description 检验jvm内存大小
 * @Date 2020/7/6 15:41
 */

public class Memory {

    public void memory() {
        Runtime r = Runtime.getRuntime();
        int free = (int) r.freeMemory() / 1024 / 1024;
        int total = (int) r.totalMemory() / 1024 / 1024;
        int max = (int) r.maxMemory() / 1024 / 1024;
        System.out.println("free" + free + "M;total" + total + "M;max" + max + "M");
    }

    public static void main(String[] args) {
        new Memory().memory();
    }
}
