package basic.classloader;



/**
 * 在%JAVA_HOME%/jre/lib 之前添加
 * -Xbootclasspath/p:D:\before;D\before2
 * 在%JAVA_HOME%/lib/lib 后添加
 * -Xbootclasspath/a:D:\after;D\after2
 * 修改ExtClassLoader path
 * -Djava.ext.dirs=d:\java;d:\java2
 */
public class Test {
	public static void main(String[] args) {
		System.out.println("classloder:"+String.class.getClassLoader());
		System.out.println("ExtClassLoader"+Test.class.getClassLoader().getParent());
		System.out.println("AppClassLoader"+Test.class.getClassLoader());
		String[] bootPath = System.getProperty("sun.boot.class.path").split(";");
		System.out.println("==========BootStrapClassLoader path==========");
		for(String path:bootPath){
			System.out.println(path.toString());
		}
		System.out.println("==========ExtClassLoader path===========");
		String[] extPath = System.getProperty("java.ext.dirs").split(";");
		for(String path:extPath){
			System.out.println(path);
		}
		System.out.println("=========AppClassLoader path============");
		String[] appPath = System.getProperty("java.class.path").split(";");
		for(String path:appPath){
			System.out.println(path);
		}
		Test.class.getClassLoader();
	}
}
