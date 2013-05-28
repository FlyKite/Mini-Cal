package doge.minical;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MiniCalMain extends MiniCalMenu {
	private Button number[] = new Button[10];
	private Button number10;
	private Button button[] = new Button[5];
	private Button buttonAC;
	private Button buttonBack;
	private Button buttonLK;
	private Button buttonRK;
	private EditText display;
	
	private Button buttonsin;
	private Button buttoncos;
	private Button buttontan;
	private Button buttonjc;
	private Button buttonln;
	private Button buttonlog;
	private Button buttonx62;
	private Button buttonx6y;
	private Button buttonexp;
	private Button buttonpi;
	private Button buttongh;
	private Button buttonbh;

	static StringBuffer displayNum = new StringBuffer();
	double x6Y;
	static boolean point = true;
	boolean calResult = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//程序开始
		if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			setContentView(R.layout.minicalmainlandscape);
			findView(true);
			TextSize(true);
		}
		else
		{
			setContentView(R.layout.minicalmain);
			findView(false);
			TextSize(false);
		}
		showTips();
		setTitlePadding();
		setBackGroundColor(MiniCalMain.this);
		if(displayNum.length() == 0) {
			displayNum.append(" 0");
		}
		for(int i = 0; i < 10; i++) {
			number[i].setOnClickListener(new NumberListener());
		}
		number10.setOnClickListener(new PointListener());
		buttonAC.setOnClickListener(new ButtonACListener());
		buttonBack.setOnClickListener(new ButtonBackListener());
		buttonLK.setOnClickListener(new ButtonKListener());
		buttonRK.setOnClickListener(new ButtonKListener());
//		buttonLK.setVisibility(View.GONE);
//		buttonRK.setVisibility(View.GONE);
		for(int i = 0; i < 5; i++) {
			button[i].setOnClickListener(new ButtonListener());
		}
		if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			buttonsin.setOnClickListener(new FunctionButtonListener());
			buttoncos.setOnClickListener(new FunctionButtonListener());
			buttontan.setOnClickListener(new FunctionButtonListener());
			buttonjc.setOnClickListener(new FunctionButtonListener());
			buttonln.setOnClickListener(new FunctionButtonListener());
			buttonlog.setOnClickListener(new FunctionButtonListener());
			buttonx62.setOnClickListener(new FunctionButtonListener());
			buttonx6y.setOnClickListener(new FunctionButtonListener());
			buttonexp.setOnClickListener(new FunctionButtonListener());
			buttonpi.setOnClickListener(new FunctionButtonListener());
			buttongh.setOnClickListener(new FunctionButtonListener());
			buttonbh.setOnClickListener(new FunctionButtonListener());
			buttonsin.setVisibility(View.GONE);
			buttoncos.setVisibility(View.GONE);
			buttontan.setVisibility(View.GONE);
			buttonjc.setVisibility(View.GONE);
			buttonln.setVisibility(View.GONE);
			buttonlog.setVisibility(View.GONE);
			buttonx62.setVisibility(View.GONE);
			buttonx6y.setVisibility(View.GONE);
			buttonexp.setVisibility(View.GONE);
			buttonpi.setVisibility(View.GONE);
			buttongh.setVisibility(View.GONE);
			buttonbh.setVisibility(View.GONE);
		}
		displayNew();
	}

	private void showTips() {
		Time nowTime = new Time();
		nowTime.setToNow();
		int today =  nowTime.yearDay;
		int todayHour = nowTime.hour;
		SharedPreferences day = getSharedPreferences("date", 0);
		int lastToday = day.getInt("today", today - 1);
		boolean otherHour = (todayHour % 3 == 0) && (todayHour != day.getInt("todayHour", todayHour - 1));
		if(otherHour || today != lastToday) {
			ArrayAdapter<CharSequence> sentences = ArrayAdapter.createFromResource(this, R.array.words, lastToday);
			Toast.makeText(this, /**sentences.getCount() + */sentences.getItem(today % (sentences.getCount() - 1)).toString(), Toast.LENGTH_LONG).show();
			day.edit().putInt("today", today).commit();
		}
		day.edit().putInt("todayHour", todayHour).commit();
	}
	
	private void findView(boolean landScape) {
		number[1] = (Button)findViewById(R.id.number1);
		number[2] = (Button)findViewById(R.id.number2);
		number[3] = (Button)findViewById(R.id.number3);
		number[4] = (Button)findViewById(R.id.number4);
		number[5] = (Button)findViewById(R.id.number5);
		number[6] = (Button)findViewById(R.id.number6);
		number[7] = (Button)findViewById(R.id.number7);
		number[8] = (Button)findViewById(R.id.number8);
		number[9] = (Button)findViewById(R.id.number9);
		number[0] = (Button)findViewById(R.id.number0);
		number10 = (Button)findViewById(R.id.number10);
		button[1] = (Button)findViewById(R.id.button1);
		button[2] = (Button)findViewById(R.id.button2);
		button[3] = (Button)findViewById(R.id.button3);
		button[4] = (Button)findViewById(R.id.button4);
		button[0] = (Button)findViewById(R.id.button0);
		buttonAC = (Button)findViewById(R.id.buttonAC);
		buttonBack = (Button)findViewById(R.id.buttonBack);
		buttonLK = (Button)findViewById(R.id.buttonLK);
		buttonRK = (Button)findViewById(R.id.buttonRK);
		display = (EditText)findViewById(R.id.display);
		if(landScape == true)
		{
			buttonsin = (Button)findViewById(R.id.buttonsin);
			buttoncos = (Button)findViewById(R.id.buttoncos);
			buttontan = (Button)findViewById(R.id.buttontan);
			buttonjc = (Button)findViewById(R.id.buttonjc);
			buttonln = (Button)findViewById(R.id.buttonln);
			buttonlog = (Button)findViewById(R.id.buttonlog);
			buttonx62 = (Button)findViewById(R.id.buttonx62);
			buttonx6y = (Button)findViewById(R.id.buttonx6y);
			buttonexp = (Button)findViewById(R.id.buttonexp);
			buttonpi = (Button)findViewById(R.id.buttonpi);
			buttongh = (Button)findViewById(R.id.buttongh);
			buttonbh = (Button)findViewById(R.id.buttonbh);
		}
	}

	private void displayNew() {
		display.setText("" + displayNum);
		display.setSelection(display.length());
		calResult =false;
	}
	
	private void displayResult(String cal) {
		display.setText("" + displayNum + " = " + cal);
		display.setSelection(display.length());
		calResult = true;
	}
	
	class NumberListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()) {
			case R.id.number1 : numberListener(1);break;
			case R.id.number2 : numberListener(2);break;
			case R.id.number3 : numberListener(3);break;
			case R.id.number4 : numberListener(4);break;
			case R.id.number5 : numberListener(5);break;
			case R.id.number6 : numberListener(6);break;
			case R.id.number7 : numberListener(7);break;
			case R.id.number8 : numberListener(8);break;
			case R.id.number9 : numberListener(9);break;
			case R.id.number0 : numberListener(0);break;
			}
		}
		
	}
	
	private void numberListener(int n) {
		if(displayNum.charAt(displayNum.length() - 1) == '0' 
				&& displayNum.charAt(displayNum.length() - 2) == ' '
				&& point) {
			displayNum.deleteCharAt(displayNum.length() - 1);
			displayNum.append(n);
		}
		else {
			displayNum.append(n);
		}
		displayNew();
	}
	
	class PointListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(point) {
				point = false;
				if(displayNum.charAt(displayNum.length() - 1) == ' ') {
					displayNum.append(0);
				}
				displayNum.append('.');
			}
			displayNew();
		}
		
	}
	
	class ButtonACListener implements OnClickListener {
			
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			displayNum = new StringBuffer();
			displayNum.append(" 0");
			point = true;
			displayNew();
		}
		
	}
	
	class ButtonBackListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(calResult == true) {
				displayNew();
				return;
			}
			if(displayNum.length() > 2 && displayNum.charAt(displayNum.length() - 1) == ' ' && displayNum.charAt(displayNum.length() - 3) == ' ') {
				displayNum.delete(displayNum.length() - 2, displayNum.length());
			}
			if(displayNum.charAt(displayNum.length() - 1) == '.') {
				point = true;
			}
			do {
				displayNum.deleteCharAt(displayNum.length() - 1);
			}
			while(displayNum.charAt(displayNum.length() - 1) > 96 && displayNum.charAt(displayNum.length() - 1) < 98+26);
			if(displayNum.length() == 1) {
				displayNum.append(0);
			}
			if(displayNum.indexOf(".", displayNum.lastIndexOf(" ")) != -1) {
				point = false;
			}
			displayNew();
		}
		
	}
	
	class ButtonKListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(displayNum.charAt(displayNum.length() - 1) == ' ') {
				displayNum.deleteCharAt(displayNum.length() - 1);
			}
			switch(v.getId()) {
			case R.id.buttonLK : displayNum.append(" ( ");break;
			case R.id.buttonRK : displayNum.append(" ) ");break;
			}
			displayNew();
		}
		
	}
	
	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()) {
			case R.id.button1 : button1234Listener(0);break;
			case R.id.button2 : button1234Listener(1);break;
			case R.id.button3 : button1234Listener(2);break;
			case R.id.button4 : button1234Listener(3);break;
			case R.id.button0 : displayResult(calStack());break;
			}
			
		}
		private StringBuffer calNum;
		private StringBuffer calStack[] = null;
		private StringBuffer numStack[] = null;
		private int topOfCal, topOfNum;
		private String calStack() {
			calNum = new StringBuffer(displayNum);
			if(calNum.charAt(calNum.length() - 1) != ' ') {
				calNum.append(" ");
			}
			calNum.append("#");
			calStack = new StringBuffer[20];
			numStack = new StringBuffer[20];
			topOfCal = 0;
			topOfNum = 0;
			calNum.deleteCharAt(0);
			int pos = calNum.indexOf(" ");
			if(pos == -1) {
				pos = calNum.length();
			}
			StringBuffer tempElem = new StringBuffer();
			tempElem.append(calNum.substring(0, pos));
			calStack[topOfCal] = new StringBuffer("");
			calStack[topOfCal++].append("#");
			while(tempElem.charAt(0) != '#' || calStack[topOfCal-1].charAt(0) != '#') {
				pos = calNum.indexOf(" ");
				if(pos == -1) {
					pos = calNum.length();
				}
				tempElem = new StringBuffer();
				tempElem.append(calNum.substring(0, pos));
				System.out.println("剩余算式--->" + calNum);
				if(tempElem.charAt(0) > 47 && tempElem.charAt(0) < 58) {
					numStack[topOfNum] = new StringBuffer("");
					numStack[topOfNum++].append(tempElem);
					System.out.println("数字进栈--->"+tempElem);
					calNum.delete(0, pos + 1);
				}
				else {
					switch(	pushOrNot(tempElem.charAt(0))) {
					case '<' : calStack[topOfCal] = new StringBuffer("");
								calStack[topOfCal++].append(tempElem);
								System.out.println("运算符进栈->"+calStack[topOfCal-1]);
								calNum.delete(0, pos + 1);
								break;
					case '>' : double num2 = Double.parseDouble(numStack[topOfNum - 1].toString());
								numStack[--topOfNum] = new StringBuffer("");
								double num1 = Double.parseDouble(numStack[topOfNum - 1].toString());
								numStack[--topOfNum] = new StringBuffer("");
								String calS = calStack[topOfCal-1].toString();
								calStack[--topOfCal] = new StringBuffer("");
								System.out.print("运算并将结果入栈->");
								numStack[topOfNum++].append("" + calc(num1, num2, calS));
								break;
					case '=' : System.out.println("脱括号----->"+calStack[topOfCal-1]);
								calStack[--topOfCal] = new StringBuffer("");
								calNum.delete(0, pos + 1);
								break;
					}
				}
				showStack();
			}
			if(numStack[0].length() > 1 && numStack[0].substring(numStack[0].length() - 2, numStack[0].length()).equals(".0")) {
				numStack[0].delete(numStack[0].length() - 2, numStack[0].length());
			}
			return numStack[0].toString();
		}
		
		private double calc(double num1, double num2, String calS) {
			double result = 0;
			switch(calS.charAt(0)) {
			case '+' : result = num1 + num2;break;
			case '-' : result = num1 - num2;break;
			case '×' : result = num1 * num2;break;
			case '÷' : result = num1 / num2;break;
			}
			System.out.println(num1 + calS + num2 + "=" + result);
			return result;
		}
		
		private void showStack() {
			System.out.println("-----------------------");
			for(int i = 0; i < topOfNum; i++) {
				System.out.print(numStack[i]+",");
			}
			System.out.println("\nNum-----------------Cal");
			for(int i = 0; i < topOfCal; i++) {
				System.out.print(calStack[i]+",");
			}
			System.out.println("\n-----------------------");
		}
		
		private char pushOrNot(char sign) { //判断优先级
			if(topOfCal == 0) {
				if(calNum.length() == 0) {
					return '>';
				}
				else {
					return '<';
				}
			}
			char cal[] = {'+', '-', '×', '÷', '(', ')', '#'};
			char temp[][] = {	{'>', '>', '<', '<', '<', '>', '>'},
								{'>', '>', '<', '<', '<', '>', '>'},
								{'>', '>', '>', '>', '<', '>', '>'},
								{'>', '>', '>', '>', '<', '>', '>'},
								{'<', '<', '<', '<', '<', '=', ' '},
								{'>', '>', '>', '>', ' ', '>', '>'},
								{'<', '<', '<', '<', '<', ' ', '='}
			};
			int nowSign = 0, topSign = 0;
			for(int i = 0; i < 7; i++) {
				if(sign == cal[i]) {
					nowSign = i;
				}
				if(calStack[topOfCal - 1].charAt(0) == cal[i]) {
					topSign = i;
				}
			}
			System.out.println("判断优先级->  " + sign + " " + temp[topSign][nowSign] + " " + calStack[topOfCal-1]);
			return temp[topSign][nowSign];
		}
		
	}
	
	private void button1234Listener(int n) {
		String button1234[] = new String[4];
		button1234[0] = " + ";
		button1234[1] = " - ";
		button1234[2] = " × ";
		button1234[3] = " ÷ ";
		if(displayNum.charAt(displayNum.length() - 1) == ' ' && displayNum.charAt(displayNum.length() - 2) != ')' && displayNum.charAt(displayNum.length() - 2) != '(') {
			displayNum.delete(displayNum.length() - 3, displayNum.length());
		}
		if(displayNum.charAt(displayNum.length() - 1) == ' ') {
			displayNum.deleteCharAt(displayNum.length() - 1);
		}
		if(point == false) {
			point = true;
		}
		displayNum.append(button1234[n]);
		displayNew();
	}
	
	//科学计算器功能键监听器
	class FunctionButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(displayNum.length() == 2 && displayNum.charAt(1) == '0') {
				displayNum.deleteCharAt(1);
			}
			switch(v.getId()) {
			case R.id.buttonsin : displayNum.append("sin(");break;
			case R.id.buttoncos : displayNum.append("cos(");break;
			case R.id.buttontan : displayNum.append("tan(");break;
			case R.id.buttonjc : displayNum.append("!");break;
			case R.id.buttonln : displayNum.append("ln");break;
			case R.id.buttonlog : displayNum.append("log");break;
			case R.id.buttonx62 : displayNum.append("^2");break;
			case R.id.buttonx6y : displayNum.append("^");break;
			case R.id.buttonexp : displayNum.append("e");break;
			case R.id.buttonpi : displayNum.append("π");break;
			case R.id.buttongh : displayNum.append("√");break;
			case R.id.buttonbh : displayNum.append("%");break;
			}
			displayNew();
		}
		
	}
	
//	private double functionJC(double num)
//	{
//		num = gamma(num + 1);
//		return num;
//	}
//	//以下为阶乘代码--------------------------------------------------------------------------------------------------------------------
//	private double gamma(double z)
//	{
//		double pi = 3.1415926535897932384626;
//	    double ret = 0.0;
//	    ret = (1.000000000190015 + 76.18009172947146/(z+1) + -86.50532032941677/(z+2) + 
//	          24.01409824083091/(z+3) + -1.231739572450155/(z+4) + 
//	          1.208650973866179e-3/(z+5) + -5.395239384953e-6/(z+6))*(Math.sqrt(2*pi)/z)*
//	          Math.pow(z+5.5, z+.5) //(z+5.5)^(z+.5)
//	          *Math.exp(-(z+5.5));
//	    return ret;
//	}
//	//以上阶乘代码摘自网络--------------------------------------------------------------------------------------------------------------------
	
	public void TextSize(boolean land) {
		SharedPreferences tSize = getSharedPreferences("mainTextSize", 0);
		int btn_size, dis_size;
		btn_size = tSize.getInt("btn_size", 15) + 10;
		dis_size = tSize.getInt("dis_size", 18) + 20;
		for(int i = 0; i < 10; i++) {
			number[i].setTextSize(btn_size);
		}
		number10.setTextSize(btn_size);
		for(int i = 0; i < 5; i++) {
			button[i].setTextSize(btn_size);
		}
		buttonAC.setTextSize(btn_size);
		buttonBack.setTextSize(btn_size);
		buttonLK.setTextSize(btn_size);
		buttonRK.setTextSize(btn_size);
		display.setTextSize(dis_size);
		if(land) {
			buttonsin.setTextSize(btn_size);
			buttoncos.setTextSize(btn_size);
			buttontan.setTextSize(btn_size);
			buttonjc.setTextSize(btn_size);
			buttonln.setTextSize(btn_size);
			buttonlog.setTextSize(btn_size);
			buttonx62.setTextSize(btn_size);
			buttonx6y.setTextSize(btn_size);
			buttonexp.setTextSize(btn_size);
			buttonpi.setTextSize(btn_size);
			buttongh.setTextSize(btn_size);
			buttonbh.setTextSize(btn_size);
		}
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		onCreate(null);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
	    SubMenu moreFunction = menu.addSubMenu(0, 0, 0, getString(R.string.menu_moreFunction));
	    moreFunction.add(0, 1, 0, getString(R.string.menu_minicalNumberSystem));
	    //moreFunction.add(0, 2, 0, getString(R.string.menu_minicalChange));
	    moreFunction.add(0, 3, 0, getString(R.string.menu_minicalEquationSolve));
	    SubMenu customize = menu.addSubMenu(0, 0, 0, getString(R.string.menu_customize));
	    customize.add(0, 4, 0, getString(R.string.menu_backgroundcolor));
	    customize.add(0, 5, 0, getString(R.string.menu_textsize));
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.menu_exit : finish();break;
		case R.id.menu_about : openAbout(MiniCalMain.this);break;
		case R.id.menu_musicshare : MusicShare(MiniCalMain.this);break;
		case R.id.menu_motto : showMotto(MiniCalMain.this);break;
		//case R.id.menu_help : openHelp(MiniCalMain.this);break;
		case 1 : GoToNumberSystem(MiniCalMain.this);break;
		case 2 : GoToChange(MiniCalMain.this);break;
		case 3 : GoToQJ(MiniCalMain.this);break;
		case 4 : BackGroundColor(MiniCalMain.this);break;
		case 5 : TextSize(MiniCalMain.this);break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		TempSave.setDisplayNum(displayNum);
		TempSave.setPoint(point);
	}
	
}
