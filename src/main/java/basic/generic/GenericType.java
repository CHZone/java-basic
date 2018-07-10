package basic.generic;

import java.util.HashMap;
import java.util.Map;

public class GenericType {
	public static void main(String[] args){
		Map<String, Integer> map = new HashMap<>();
		map.put("value1", 1);
		map.put("value2", 2);
		System.out.println(map.get("value1"));
		System.out.println(map.get("value2"));
	}
}
