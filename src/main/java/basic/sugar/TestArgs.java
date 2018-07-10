package basic.sugar;

public class TestArgs {
	public static void manyString(String ...someString ){
		for(String s : someString){
			System.out.println(s);
		}
	}
	
	public static void main(String[] args) {
		manyString("aa","bb","ccc");
	}
}
