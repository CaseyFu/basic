package org.casey.basic.file;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

//字节输入流、字节输出流，主要操作字节数组,文件输入流，这个流可以用来把数据从硬盘的文件，读取到JVM(内存)。
//利用字节流创建一个文件，写入数据;再利用字节流读取文件

public class ReadAndWrite {

    /**
     * 写入文件到磁盘
     * @throws IOException
     */
    public void write() throws IOException        //创建文件，写入数据
    {
        File file = new File("f:/des.java");
        if (!file.exists()){
            file.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
        bw.write("写入这一行数据\n这是第二行");
        bw.flush(); // 立即写入
        bw.close();
        System.out.println("成功写入数据!");
    }

    /**
     * 读取文件到内存
     * @throws IOException
     */
    public void readByChar() throws IOException {
        File file = new File("f:/src.java");
        if(!file.exists()){
            System.out.println("文件不存在");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
        String line = null;
        while((line=br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
    }
    public void test() throws IOException{
        // 测试读取对象
        File file = new File("f:/undigraph.txt");
        if(!file.exists()){
            System.out.println("文件不存在");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
        String line = null;
        int s = 0;
        List<Integer> v = new ArrayList<>();
        while((line=br.readLine())!=null){
            if(line.equals("")){
                // 空行
                continue;
            }
            if(line.startsWith("//")){
                continue;
            }

            if(line.startsWith("size")){
                String size = line.substring(line.indexOf("=")+1, line.indexOf(";")).trim();
                s = Integer.parseInt(size);
                System.out.println(s);
            }else{
                // 读取结点和边
                String[] sArr = line.split(" ");
                for(String each:sArr){
                    System.out.println(each);
                }
            }



            System.out.println(line);
//            System.out.println("size:");
//            System.out.println(line);
        }
        br.close();
    }
    public static void main(String[] args) throws IOException {
//        new ReadAndWrite().write();
        new ReadAndWrite().test();
    }
}
