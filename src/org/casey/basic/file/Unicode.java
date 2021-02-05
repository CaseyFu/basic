////线程池的用法,检测文件和网页的编码,复制文件
//package file;
//import java.io.*;
//
////线程池
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.TimeUnit;
//
//
//
////cpdetector,检测文件编码
//import java.net.URL;
//import java.net.MalformedURLException;
//import info.monitorenter.cpdetector.io.ASCIIDetector;
//import info.monitorenter.cpdetector.io.ByteOrderMarkDetector;
//import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
//import info.monitorenter.cpdetector.io.JChardetFacade;
//import info.monitorenter.cpdetector.io.ParsingDetector;
//import info.monitorenter.cpdetector.io.UnicodeDetector;
//public class Unicode {
//
//    LinkedBlockingQueue<Runnable> L = new LinkedBlockingQueue<Runnable>();
//    ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, L);
//    public void transform_unicode() throws IOException
//    {
//        File f0 = new File("F:/xfk");
//        File f1 = new File("F:/xfk1");
//        if(!f1.exists())		//目标文件如果存在必须清理干净
//            f1.mkdirs();
//
//        if(f0.exists())		//源文件如果不存在就没有意义
//            recursion(f0,f1);
//        else
//            return ;
//        System.out.println("转移成功");
//        pool.shutdown();
//    }
//
//    public void recursion(File src,File des)	throws IOException
//    {
//        for(File f0:src.listFiles()) {
//            if(f0.isDirectory()) {
//                //File的文件夹字符串模式,在文件夹下创建文件
//                File newFolder = new File(des,f0.getName());
//                newFolder.mkdirs();
//                recursion(f0,newFolder);	//递归子文件夹
//            }
//            if(f0.isFile()) {
//                File newFile = new File(des,f0.getName());
//                newFile.createNewFile();
//                String code = null;
//                try {
//                    URL url = f0.toURI().toURL();
//                    code = Unicode.getUrlEncode(url);
//                }catch(MalformedURLException e) {
//                    e.printStackTrace();
//                }
//                if(!code.equals("UTF-8")){
//                    System.out.println(f0.getAbsolutePath());
//                }
//                if(code.equals("GB2312")) {
//                    toNewFile(f0,newFile);
//                }
//                else {
//                    copy(f0,newFile);
//                }
//            }
//        }
//    }
//    public void toNewFile(File f0, File f1) {
//        pool.execute(new Runnable(){
//            public void run(){
//                try{
//                    FileInputStream in = new FileInputStream(f0);
//                    FileOutputStream out = new FileOutputStream(f1);
//                    byte []b = new byte[(int)f0.length()];
//                    in.read(b);
//                    out.write(new String(b).getBytes("utf-8"));
//                    in.close();
//                    out.close();
//                    System.out.println("修改"+f0.getAbsolutePath());
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//    public void copy(File f0, File f1) throws IOException{
//        pool.execute(new Runnable(){
//            public void run(){
//                try{
//                    FileInputStream in = new FileInputStream(f0);
//                    FileOutputStream out = new FileOutputStream(f1);
//                    byte []b = new byte[(int)f0.length()];
//                    in.read(b);
//                    out.write(b);
//                    in.close();
//                    out.close();
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//
//
//
//	public static void FileStream()	throws IOException
//	{
//		//先输出流一个文件，写入内容；再将此文件输入流到内存；
//		File file = new File("F:/xfk/1/Component_exercise.java");
//		FileInputStream in = new FileInputStream(file);
//		byte []b1 = new byte[(int)file.length()];
//		in.read(b1);
//		in.close();
//		String s = new String(b1);
//
//		FileOutputStream out = new FileOutputStream(new File("F:/xfk/2/utf8.java"));
//		out.write(s.getBytes("utf-8"));
//		out.close();
//	}
//
//    public static String getUrlEncode(URL url) {
//        /*
//         * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。
//         * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、
//         * JChardetFacade、ASCIIDetector、UnicodeDetector。
//         * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
//         * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar
//         * cpDetector是基于统计学原理的，不保证完全正确。
//         */
//        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
//        /*
//         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
//         * 指示是否显示探测过程的详细信息，为false不显示。
//         */
//        detector.add(new ParsingDetector(false));
//
//        detector.add(new ByteOrderMarkDetector());
//        /*
//         * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
//         * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以
//         * 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
//         * 用到antlr.jar、chardet.jar
//         */
//        detector.add(JChardetFacade.getInstance());
//
//        // ASCIIDetector用于ASCII编码测定
//        detector.add(ASCIIDetector.getInstance());
//
//        // UnicodeDetector用于Unicode家族编码的测定
//        detector.add(UnicodeDetector.getInstance());
//
//        java.nio.charset.Charset charset = null;
//        try {
//            charset = detector.detectCodepage(url);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (charset != null) {
//            return charset.name();
//        }
//        return null;
//    }
//    public static void main(String []args){
//        File file = new File("F:\\xfk\\Log4j.java");
//        Unicode u = new Unicode();
//        URL url = null;
//        try{
//            url = file.toURI().toURL();
//            System.out.println(u.getUrlEncode(url));
//
//        }catch(MalformedURLException e){
//            e.printStackTrace();
//        }
//
//    }
//}
