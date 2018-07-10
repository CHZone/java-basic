
public class OutClass {
	private int menber_a;
	class innerClass_menber {
		public innerClass_menber() {
			menber_a = 1;
		}
	}
	
	public void menberFunc(){
		final int local_b = 2;
		class InnerClass_local{
			public InnerClass_local() {
				menber_a = local_b;
			}
		}
	}
}
