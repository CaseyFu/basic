
//遍历文件夹,找出文件中含有Magic的java文件,如[找到了一个java文件就创建一个线程]去搜索内容
package org.casey.basic.threadTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
public class SearchFile implements Runnable{
	private File file;
	public SearchFile(File file) {
		this.file = file;
	}
	public void run() {
		byte []b = new byte[(int)this.file.length()];
		FileInputStream in = null;
		try {
			in = new FileInputStream(this.file);
			in.read(b);
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		String s = new String(b);
		if(s.contains("Magic")) {
			System.out.println("找到包含Magic的java文件,位置在:"+this.file.getPath());
		}
	}
	
	public static void Traverse(File file) {
		if(file.isDirectory()) {
			File []f = file.listFiles();
			for(File f0:f) {
				Traverse(f0);
			}
		}
		if(file.isFile()) {
			if(file.getName().endsWith(".java")) {
				new Thread(new SearchFile(file)).start();//把SearchFile类的构造方法变为File方便传递参数
				//找到了是java的文件就创建一个线程去执行搜索任务
			}
		}
	}
	public static void main(String []args) {
		File f = new File("G:/java/ggg");
		SearchFile.Traverse(f);
	}
}
