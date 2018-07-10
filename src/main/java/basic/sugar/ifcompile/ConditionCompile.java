package basic.sugar.ifcompile;

public class ConditionCompile {
	public static void main(String[] args){
		if(true){
			System.out.println(true);
			// 下面的代码将被编译器干掉
		}else{
			System.out.println(false);
		}
	}
}
