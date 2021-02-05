package org.casey.basic.file;
//复制源文件夹下的文件到目标文件
//父子文件路径必须是 父文件夹接子文件名
//File的模式String单路径模式、String和String父路径子路径模式、File和String父文件夹和子文件名模式
//递归技巧:关注第一次的过程,后面的过程再深入


import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        CopyFile.Copy1("e:/zouchenglin.txt","d:/");
    }
    public static void Copy1(String src, String des){
        String regx = ":/(.*?)\\.txt";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(src);
        if (matcher.find()) {
            des += matcher.group(1) + "New.txt";
        }
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(new File(src));
            output = new FileOutputStream(new File(des));
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (input != null) input.close();
                if (output != null) output.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("复制完成");
    }


    public static void Copy(String src, String des) throws IOException {
        String regx = ":/(.*?)\\.txt";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(src);
        if (matcher.find()) {
            des += matcher.group(1) + "New.txt";
        }
        FileReader r = new FileReader(new File(src));
        PrintWriter w = new PrintWriter(new File(des));
        BufferedReader edr = new BufferedReader(r);
        String line = "";
        while ((line = edr.readLine()) != null) {
            w.println(line);
        }
        edr.close();
        r.close();
        w.close();
        System.out.println("复制完成");
    }

    public static void DeleteAll(File f)    //递归的经典例子
    {
        for (File f0 : f.listFiles()) {
            if (f0.isFile()) f0.delete();
            if (f0.isDirectory()) {
                DeleteAll(f0);    //文件夹中有文件不能直接删除文件夹,如果文件夹中有文件就不能够删除文件夹
                f0.delete();
            }
        }
        f.delete();    //最后删除f总文件
    }

//    public static void CopyDir(String src, String des) throws IOException {
//        File f0 = new File(src);
//        File f1 = new File(des);
//        if (f1.exists())        //目标文件如果存在必须清理干净
//            DeleteAll(f1);
//        else
//            f1.mkdirs();
//
//        if (f0.exists())        //源文件如果不存在就没有意义
//            CopyAll(f0, f1);
//        else
//            return;
//    }

//    public static void CopyAll(File src, File des) throws IOException {
//        for (File f0 : src.listFiles()) {
//            if (f0.isDirectory()) {
//                //File的文件夹字符串模式,在文件夹下创建文件
//                File newFolder = new File(des, f0.getName());
//                newFolder.mkdirs();
//                CopyAll(f0, newFolder);    //递归子文件夹
//            }
//            if (f0.isFile()) {
//                File newFile = new File(des, f0.getName());
//                newFile.createNewFile();
//                Copy(f0, newFile);        //把源文件内容复制到目标文件
//            }
//        }
//    }
}
