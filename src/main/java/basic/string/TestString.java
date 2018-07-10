package basic.string;

public class TestString {
	public static void main(String[] args){
		String a = "abc";
		String b = new String("abc");
		System.out.println(a==b);
		System.out.println(a==b.intern());
	}
}
