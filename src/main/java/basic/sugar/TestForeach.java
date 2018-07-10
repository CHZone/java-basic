package basic.sugar;

import java.util.ArrayList;
import java.util.List;

public class TestForeach {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		for(String s: list){
			System.out.println(s);
		}
	}
}
