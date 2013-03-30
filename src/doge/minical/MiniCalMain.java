package doge.minical;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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

	int act;
	StringBuffer displayNum = new StringBuffer();
	double x6Y;
	boolean point = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//程序开始
		if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			setContentView(R.layout.minicalmainlandscape);
			findView(true);
		}
		else
		{
			setContentView(R.layout.minicalmain);
			findView(false);
		}
		if(Build.VERSION.SDK_INT > 10) {
			findViewById(R.id.topbar).setVisibility(View.GONE);
		}
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
		}
		displayNew();
	}
	
	public static void getTempSave() {
		
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
	}
	
	private void displayResult() {
		display.setText("" + displayNum + " = ");
		display.setSelection(display.length());
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
				&& point == false) {
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
			if(point == false) {
				point = true;
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
			point = false;
			displayNew();
		}
		
	}
	
	class ButtonBackListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(displayNum.length() > 2 && displayNum.charAt(displayNum.length() - 1) == ' ' && displayNum.charAt(displayNum.length() - 3) == ' ') {
				displayNum.deleteCharAt(displayNum.length() -1);
				displayNum.deleteCharAt(displayNum.length() -1);
			}
			if(displayNum.charAt(displayNum.length() - 1) == '.') {
				point = false;
			}
			do {
				displayNum.deleteCharAt(displayNum.length() - 1);
			}
			while(displayNum.charAt(displayNum.length() - 1) > 96 && displayNum.charAt(displayNum.length() - 1) < 98+26);
			if(displayNum.length() == 1) {
				displayNum.append(0);
			}
			displayNew();
		}
		
	}
	
	class ButtonKListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int LR = 0;
			switch(v.getId()) {
			case R.id.buttonLK : LR = 0;displayNum.append(" ( ");break;
			case R.id.buttonRK : LR = 1;displayNum.append(" ) ");break;
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
			case R.id.button0 : displayResult();break;
			}
			
		}
		
	}
	
	private void button1234Listener(int n) {
		String button1234[] = new String[4];
		button1234[0] = " + ";
		button1234[1] = " - ";
		button1234[2] = " × ";
		button1234[3] = " ÷ ";
		if(displayNum.charAt(displayNum.length() - 1) == ' ') {
			displayNum.deleteCharAt(displayNum.length() - 1);
			displayNum.deleteCharAt(displayNum.length() - 1);
			displayNum.deleteCharAt(displayNum.length() - 1);
		}
		displayNum.append(button1234[n]);
		displayNew();
	}
	
	private void cal() {
		
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
	
	private double functionJC(double num)
	{
		num = gamma(num + 1);
		return num;
	}
	
	private double functionX6Y(double num)
	{
		return 1.0;
	}
	//以下为阶乘代码--------------------------------------------------------------------------------------------------------------------
	private double gamma(double z)
	{
		double pi = 3.1415926535897932384626;
	    double ret = 0.0;
	    ret = (1.000000000190015 + 76.18009172947146/(z+1) + -86.50532032941677/(z+2) + 
	          24.01409824083091/(z+3) + -1.231739572450155/(z+4) + 
	          1.208650973866179e-3/(z+5) + -5.395239384953e-6/(z+6))*(Math.sqrt(2*pi)/z)*
	          Math.pow(z+5.5, z+.5) //(z+5.5)^(z+.5)
	          *Math.exp(-(z+5.5));
	    return ret;
	}
	//以上阶乘代码摘自网络--------------------------------------------------------------------------------------------------------------------

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
		menu.add(0 ,1 ,0 ,getString(R.string.menu_minicalNumberSystem));
		menu.add(0 ,2 ,0 ,getString(R.string.menu_minicalChange));
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.menu_exit : finish();break;
		case R.id.menu_about : openAbout(MiniCalMain.this);break;
		case R.id.menu_musicshare : MusicShare(MiniCalMain.this);break;
		case R.id.menu_help : openHelp(MiniCalMain.this);break;
		case 1 : GoToNumberSystem(MiniCalMain.this);break;
		case 2 : GoToChange(MiniCalMain.this);break;
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	private void saveTemp() {
		TempSave.setNum1(n1);
		TempSave.setNum2(n2);
		TempSave.setK(k);
		TempSave.setResult(result);
		TempSave.setPoint(point);
		TempSave.setAct(act);
		TempSave.setDisplayNum(displayNum);
	}
	*/
}
