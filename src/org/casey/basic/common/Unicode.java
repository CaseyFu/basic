package org.casey.basic.common;


import java.io.*;

import java.nio.charset.Charset;

/**
 * @ClassName ArrayCopy
 * @Author Casey Fu
 * @Description 用字节流和字符流转换编码, 字符流需注意不能用FileWriter和FileReader, 不能改变编码格式
 * 用InputStreamWriter InputStreamReader OutputStreamWriter OutputStreamWriter
 * 十六进制的前缀0x 后缀H
 * 5&0x5的意思是二进制的5与十六进制的5按位进行与运算, 运算的结果是一个二进制
 * @Date 2020/7/6 15:41
 */

public class Unicode {
    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        //Unicode.Tochinese("utf-8");//十六进制转换为各种类型的符号，参数为各种编码类型
        //Unicode.FileRandW("中");
        //Unicode.FileStream("中");//字符流形式写入与读取数据
        //Unicode.ChinesetoHexadecimal();//中文的各种十六进制编码
        Unicode.ToHexadecimal(16);//十进制->十六进制
        Unicode.Bom(true);    //根据需求是否保留bom

    }

    public static void Bom(boolean f) throws IOException {
        //给出一个参数，是否清除bom
        //先读取带bom的文件，观察表示形式;然后转为十六进制，观察形式;
        File f0 = new File("G:/FFF/KKK/temp.txt");
        byte[] b0 = new byte[(int) f0.length()];
        FileInputStream in = new FileInputStream(f0);
        in.read(b0);
        in.close();
        System.out.println(new String(b0, "utf-8"));
        if (f == true)        //去除bom
        {
            //清楚bom，清除字节数组的前3个字节
            System.arraycopy(b0, 3, b0, 0, b0.length);
            //b0 = Arrays.copyOfRange(b0, 3, b0.length);
            String s1 = "";
            for (byte b2 : b0) {
                int i = b2 & 0xff;
                s1 += Integer.toHexString(i) + " ";
            }
            System.out.println("去除bom后的十六进制为:" + s1.toUpperCase());
        } else                //保留bom
        {
            String s0 = "";
            for (byte b1 : b0) {
                int i = b1 & 0xff;
                s0 += Integer.toHexString(i) + " ";
            }
            System.out.println("未去除bom时的十六进制为:" + s0.toUpperCase());
        }
    }

    public static void Tochinese(String unicode) throws UnsupportedEncodingException//十六进制转换为汉字
    {
        String[] s0 = {"E5", "B1", "8C", "E4", "BB", "98"};
        byte[] b0 = new byte[s0.length];
        for (int i = 0; i < s0.length; i++) {
            b0[i] = (byte) Integer.parseInt(s0[i], 16);
        }
        String s1 = new String(b0, unicode);
        System.out.println(s1);
    }

    public static void ToHexadecimal(int i)//将一个十进制数转换为十六进制
    {
        int j = i & 0xffffffff;    //一个f占4位，2个占一个字节
        String s = Integer.toHexString(j);
        System.out.println("十进制数" + i + "的十六进制是:" + s.toUpperCase());
    }

    public static void ChinesetoHexadecimal() throws IOException {
        File f0 = new File("G:/FFF/KKK/temp.txt");
        FileInputStream in = new FileInputStream(f0);
        byte[] b0 = new byte[(int) f0.length()];
        in.read(b0);
        in.close();
        String s = new String(b0);
        String[] s0 = {"BIG5", "GBK", "GB2312", "UTF-8", "UTF-16", "UTF-32"};
        for (String s1 : s0)
            ChinesetoHexadecimal(s, s1);
    }

    public static void ChinesetoHexadecimal(String s, String unicode) throws UnsupportedEncodingException {
        byte[] b0 = s.getBytes(unicode);
        String s0 = "";
        for (byte b1 : b0) {
            int j = b1 & 0xff;//ffffff,因为是字节数组，每个元素都是一个字节，所以只用2个f
            s0 += Integer.toHexString(j) + " ";
        }
        System.out.println(s + "以" + unicode + "方式编码的16进制是:" + s0.toUpperCase());
    }

    public static void FileStream(String s) throws IOException {
        //先输出流一个文件，写入内容；再将此文件输入流到内存；
        File f0 = new File("G:/FFF/KKK/FileStream.txt");
        FileOutputStream out = new FileOutputStream(f0);
        System.out.printf("第一部分:%n输出流向文件输入数据:%n");
        byte[] b0 = s.getBytes("gb2312");
        out.write(b0);
        out.close();
        System.out.println("第一部分写入文件成功!");

        FileInputStream in = new FileInputStream(f0);
        System.out.printf("第二部分:%n输入流从文件读取数据:%n");
        byte[] b1 = new byte[(int) f0.length()];
        in.read(b1);
        in.close();
        System.out.println("读取的文件内容为:" + new String(b1));

    }

    public static void FileRandW(String s) throws IOException {
        //以字符流形式输入与读取数据
        File f0 = new File("G:/FFF/KKK/FileRandW.txt");
		/*OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(f0),Charset.forName("gb2312"));
		
		w.write(s);
		w.close();
		System.out.println("已写入!");*/

        System.out.println("读取文件的内容为：");
        InputStreamReader r = new InputStreamReader(new FileInputStream(f0), Charset.forName("utf-8"));
        char[] c0 = new char[(int) f0.length()];
        r.read(c0);
        r.close();

        System.out.println(new String(c0));

    }
}
