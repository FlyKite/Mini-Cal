package doge.minical;

public class TempSave {
	private static double num1,num2,k,result;
	private static int point,act,showNum;
	private static StringBuffer displayNum;
	
	public static void setNum1(double n) {
		num1 = n;
	}
	
	public static void setNum2(double n) {
		num2 = n;
	}
	
	public static void setK(double n) {
		k = n;
	}
	
	public static void setResult(double n) {
		result = n;
	}
	
	public static void setPoint(int n) {
		point = n;
	}
	
	public static void setAct(int n) {
		act = n;
	}
	
	public static void setShowNum(int n) {
		showNum = n;
	}
	
	public static void setDisplayNum(StringBuffer n) {
		displayNum = n;
	}
	
	public static double getNum1() {
		return num1;
	}
	
	public static double getNum2() {
		return num2;
	}
	
	public static double getK() {
		return k;
	}
	
	public static double getResult() {
		return result;
	}
	
	public static int getPoint() {
		return point;
	}
	
	public static int getAct() {
		return act;
	}
	
	public static int getShowNum() {
		return showNum;
	}
	
	public static StringBuffer getDisplayNum() {
		return displayNum;
	}

}
