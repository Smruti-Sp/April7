package mar1;

public class Demo1 {
	public static void addition(int a, int b) {
		int c = a + b;
		System.out.println(c);
	}

	public static String verifystrings(String str1, String str2) {
		String res = "";
		if (str1.equals(str2)) {
			res = "Strings are equal";
		} else {
			res = "Strings are not equal";
		}
		return res;
	}

	public static boolean compareValues(int x, int y) {
		if (x == y) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		Demo1.addition(8765,654);;
		String result=Demo1.verifystrings("india"," Hello");
		System.out.println(result);
		boolean res=Demo1.compareValues(879, 879);
		System.out.print(res);
				

	}

}
