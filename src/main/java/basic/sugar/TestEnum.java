package basic.sugar;

public enum TestEnum {
	one(1,"one"),two(2,"two");
	private Integer value;
	private String name;
	private TestEnum(Integer value, String name){
		this.value = value;
		this.name = name;
		this.name();
	}
	public Integer getValue(){
		return this.value;
	}

	public String getName(){
		return this.name;
	}
}
