package vn.whoever.test;

public class TestObject {
	
	public static void main(String[] args) {
		Test test = new Test(3,4);
		Object obj = test;
		
		Test tt = (Test) obj;
		
		System.out.println(tt.getSum());
	}

}
