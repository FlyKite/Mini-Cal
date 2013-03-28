package doge.minical;

public class MiniCalStack {
	StringBuffer numberStack[] = new StringBuffer[32];
	StringBuffer operatorStack[] = new StringBuffer[32];
	int topOfNumber, topOfOperator;
	
	public void pushToStack(String num, int stack) {
		if(stack == 0) {
			numberStack[topOfNumber++].append(num);
		}
		else {
			operatorStack[topOfOperator++].append(num);
		}
	}
	
	public StringBuffer popFromStack(int stack) {
		StringBuffer pop = null;
		if(stack == 0) {
			pop = numberStack[topOfNumber];
			numberStack[topOfNumber--] = new StringBuffer();
		}
		else {
			pop = operatorStack[topOfOperator];
			operatorStack[topOfOperator--] = new StringBuffer();
		}
		return pop;
	}
	
	public void clearStackFrom(int from, int stack) {
		if(stack == 0) {
			for(int i = from; i < 32; i++) {
				numberStack[i] = new StringBuffer();
			}
		}
		else {
			for(int i = from; i < 32; i++) {
				operatorStack[i] = new StringBuffer();
			}
		}
	}
}
