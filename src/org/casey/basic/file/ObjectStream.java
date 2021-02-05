package org.casey.basic.file;


import java.io.*;
import org.casey.basic.common.*;

//对象流，将对象以流的方式传输给其他介质，例如硬盘

//对象以流的方式进行传输叫做序列化。该对象所在的类必须由Serializable实现，必须进口java.io.Serializable;

public class ObjectStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student s = new Student("fk", 18, 186.3);
        Work w = new Work("xfk", 19, 201774114);
        //ObjectStream.ObjectStream_Out(s1);
        ObjectStream.ObjectStream_In();
        //ObjectStream.Exercise();
    }

    public static void ObjectStream_Out(Student x) throws IOException {
        //创建输出流，建立对象流文件夹，序列化对象Strudents
        File file = new File("f:/test.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "uft-8"));

        ObjectOutputStream Objectout = new ObjectOutputStream(new FileOutputStream(file));
        Objectout.writeObject(x);
        Objectout.close();
        System.out.println("写入成功");
    }

    public static void ObjectStream_In() throws IOException, ClassNotFoundException {
        //创建输入流，读取对象流文件，也就是序列化
        File f0 = new File("f:/test.txt");
        FileInputStream in = new FileInputStream(f0);
        ObjectInputStream Objectin = new ObjectInputStream(in);
        Student s = (Student) Objectin.readObject();
        Objectin.close();
        in.close();
        System.out.println("姓名:" + s.name + "年龄:" + s.age + "身高:" + s.height);

    }

    public static void Test(Student s1, Work w1) throws IOException, ClassNotFoundException {
        //用对象流写入多个对象，再读取时按不同顺序读取
        //经实验发现，怎么写怎么读，顺序一定不能错，
        File f0 = new File("G:/FFF/KKK/Test.txt");
        FileOutputStream out = new FileOutputStream(f0);
        ObjectOutputStream Objectout = new ObjectOutputStream(out);
        Objectout.writeObject(s1);
        Objectout.writeObject(w1);
        Objectout.close();
        out.close();
        System.out.println("写入成功");

        //先写入的是学生，那就先实验先读工人
        FileInputStream in = new FileInputStream(f0);
        ObjectInputStream Objectin = new ObjectInputStream(in);
        Work w2 = new Work();

        Student s2 = new Student();
        s2 = (Student) Objectin.readObject();
        w2 = (Work) Objectin.readObject();
        System.out.println("读取的信息为:");
        System.out.println(s2);
        System.out.println(w2);
        Objectin.close();
        in.close();
    }

    public static void Exercise() throws IOException, ClassNotFoundException {
        //创建一个对象数组，序列化入文件，再从文件中序列化出数据,
        //顺序写顺序读，参数中无对象数组，只有对象个体
        Student[] s = new Student[10];
        for (int i = 0; i < 10; i++) {
            s[i] = new Student();
            s[i].name = "Fk-" + i + "号";
            s[i].age = (int) (Math.random() * 6 + 15);
            s[i].height = (double) (Math.random() * 11 + 170);
        }
        File f0 = new File("G:/FFF/KKK/ObjectStream.txt");
        FileOutputStream out = new FileOutputStream(f0);
        ObjectOutputStream Objectout = new ObjectOutputStream(out);
        for (int i = 0; i < 10; i++) {
            Objectout.writeObject(s[i]);
        }

        Objectout.close();
        out.close();
        for (Student s1 : s) {
            System.out.format("姓名:%s年龄:%d身高:%.2fcm%n", s1.name, s1.age, s1.height);
        }
        System.out.println("写入成功");

        FileInputStream in = new FileInputStream(f0);
        ObjectInputStream Objectin = new ObjectInputStream(in);

        Student[] s0 = new Student[10];
        for (int i = 0; i < 10; i++) {
            s0[i] = new Student();
            s0[i] = (Student) Objectin.readObject();
        }
        Objectin.close();
        in.close();
        System.out.println("读取的数据为:");
        for (Student s1 : s0) {
            System.out.printf("姓名:%s年龄:%d身高:%.2fcm%n", s1.name, s1.age, s1.height);
        }
    }
}
