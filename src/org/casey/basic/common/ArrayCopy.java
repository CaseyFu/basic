package org.casey.basic.common;

import java.util.Arrays;

/**
 * @ClassName ArrayCopy
 * @Author Casey Fu
 * @Description 数组的copy两种方法
 * @Date 2020/7/6 15:41
 */

public class ArrayCopy {

    public void copy1(int[] arr) {
        // 两个数组之间复制
        System.arraycopy(arr, 3, arr, 0, arr.length - 3);
        for (int value : arr) {
            System.out.print(value);
        }
    }

    public void copy2(int[] arr) {
        // 复制,返回一个范围的数组
        int[] temp = Arrays.copyOfRange(arr, 1, 4);
        for (int value : temp) {
            System.out.print(value);
        }
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        new ArrayCopy().copy1(arr);
        System.out.println();
        new ArrayCopy().copy2(arr);
    }
}
