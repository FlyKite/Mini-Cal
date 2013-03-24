package doge.minical;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MiniCalMain extends Activity {
	private Button number[] = new Button[10];
	private Button number10;
	private Button button[] = new Button[5];
	private Button buttonAC;
	private Button buttonC;
	private TextView display;
	private TextView num1T;
	private TextView displayNumT;
	private TextView num2T;
	private TextView resultT;
	private TextView actPointKT;
	private TextView showNumT;
	
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

	static double n1,n2,k=1,result;
	static int point=0,act,showNum=-1;
	static StringBuffer displayNum = new StringBuffer("0");
	static boolean getTemp = false;
	
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
		for(int i = 0; i < 10; i++) {
			number[i].setOnClickListener(new NumberListener());
		}
		number10.setOnClickListener(new Number10Listener());
		buttonAC.setOnClickListener(new ButtonACListener());
		buttonC.setOnClickListener(new ButtonCListener());
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
		if(getTemp == true) {
			if(n1 == 0 && result != 0) {
				displayResult();
			}
			else {
			displayNew();	
			}
		}
	}
	
	public static void getTempSave() {
		n1 = TempSave.getNum1();
		n2 = TempSave.getNum2();
		k = TempSave.getK();
		result = TempSave.getResult();
		point = TempSave.getPoint();
		act = TempSave.getAct();
		showNum = TempSave.getShowNum();
		displayNum = TempSave.getDisplayNum();
		getTemp = true;
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
		buttonC = (Button)findViewById(R.id.buttonC);
		display = (TextView)findViewById(R.id.display);
		num1T = (TextView)findViewById(R.id.num1);
		displayNumT = (TextView)findViewById(R.id.displaynum);
		num2T = (TextView)findViewById(R.id.num2);
		resultT = (TextView)findViewById(R.id.result);
		actPointKT = (TextView)findViewById(R.id.actpointk);
		showNumT = (TextView)findViewById(R.id.shownum);
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
		display.setText(displayNum);
			showNumNow();
	}
	
	private void displayResult() {
		display.setText("" + result);
		showNumNow();
	}
	
	private void showNumNow() {
		if(showNum > 0) {
			char actact = ' ';
			switch(act) {
			case 1 :actact = '+';break;
			case 2 :actact = '-';break;
			case 3 :actact = '×';break;
			case 4 :actact = '÷';break;
			}
			boolean pointb = false,shownumb = true;
			if(point == 1) {
				pointb = true;
			}
			else {
				pointb = false;
			}
			num1T.setText("num1 = " + n1);
			displayNumT.setText("displaynum = " + displayNum);
			num2T.setText("num2 = " + n2);
			resultT.setText("result = " + result);
			actPointKT.setText("act = " + actact + "      point = " + pointb + "      k = " + k);
			showNumT.setText("shownum = " + shownumb);
		}
		else {
			num1T.setText("");
			displayNumT.setText("");
			num2T.setText("");
			resultT.setText("");
			actPointKT.setText("");
			showNumT.setText("");			
		}
		
	}
	
	class NumberListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(displayNum.length() < 15) {
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
				case R.id.number0 : if((displayNum.length() != 0 && act == 0) || ((displayNum.length() == 0 || n1 > 0 || point == 1) && act != 0)) {numberListener(0);};break;
				}
			}
		}
		
	}
	
	private void numberListener(int n) {
		if(n1 == 0 && displayNum.length() == 1) {
			displayNum = new StringBuffer("");
		}
		if(act == 0 && n2 != 0) {
			clear(1);
		}
		if(point == 0) {
			n1 = n1 * 10 + n;
		}
		else {
			n1 = n1 * k;
			n1 = n1 * 10 + n;
			k = k * 10.0;
			n1 = n1 / k;
		}
		displayNum.append(n);
		if(point == 0 && act != 0 && displayNum.length() == 2 && n1 < 10 && n != 0) {
			displayNum = new StringBuffer("");
			displayNum.append(n);
		}
		displayNew();
	}
	
	class Number10Listener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(point == 0) {
			if(displayNum.length() == 0) {
				displayNum.append("0");
			}
			displayNum.append(".");
			point = 1;
			displayNew();
			}
		}
		
	}
	
	class ButtonACListener implements OnClickListener {
			
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			clear(1);
		}
		
	}
		
	class ButtonCListener implements OnClickListener {
			
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(n1 == 0 || (n1 != 0 && n2 != 0 && result != 0 && act != 0)) {
				clear(1);
			}
			else {
				clear(0);
			}
			
		}
		
	}
	
	private void clear(int n) {
		n1 = 0;
		k = 1;
		point = 0;
		displayNum = new StringBuffer("0");
		if(n == 1) {
			n2 = 0;
			act = 0;
			result = 0;
		}
		showNumNow();
		displayNew();
	}
	
	class ButtonListener implements OnClickListener {
			
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()) {
			case R.id.button1 : button1234Listener(1);break;
			case R.id.button2 : button1234Listener(2);break;
			case R.id.button3 : button1234Listener(3);break;
			case R.id.button4 : button1234Listener(4);break;
			case R.id.button0 : cal();act = 0;clear(0);displayResult();n2 = result;break;
			}
		}
		
	}
	
	private void button1234Listener(int n) {
		if(n2 == 0) {
			n2 = n1;
			n1 = 0;
			point = 0;
			k = 1;
			act = n;
			displayNum = new StringBuffer("");
		}
		else if(n1 != 0) {
			cal();
			n2 = result;
			n1 = 0;
			point = 0;
			k = 1;
			act = n;
			displayNum = new StringBuffer("");
		}
		else {
			act = n;
		}
		showNumNow();
	}
	
	private void cal() {
		switch(act)
		{
		case 1 : result = n2 + n1;displayResult();break;
		case 2 : result = n2 - n1;displayResult();break;
		case 3 : result = n2 * n1;displayResult();break;
		case 4 : result = n2 / n1;displayResult();break;
		case 0 : displayNew();break;
		}
	}
	
	//科学计算器功能键监听器
	class FunctionButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			double pi = 3.1415926535897935384626;
			switch(v.getId()) {
			case R.id.buttonsin : n1 = Math.sin(n1 / 180 * pi);break;
			case R.id.buttoncos : n1 = Math.cos(n1 / 180 * pi);break;
			case R.id.buttontan : n1 = Math.tan(n1 / 180 * pi);break;
			case R.id.buttonjc : n1 = functionJC(n1);break;
			case R.id.buttonln : n1 = Math.log(n1);break;
			case R.id.buttonlog : n1 = Math.log10(n1);break;
			case R.id.buttonx62 : n1 = Math.pow(n1,2);break;
			case R.id.buttonx6y : n1 = functionX6Y(n1);break;
			case R.id.buttonexp : n1 = Math.exp(n1);break;
			case R.id.buttonpi : n1 = pi;break;
			case R.id.buttongh : n1 = Math.sqrt(n1);break;
			case R.id.buttonbh : n1 = n1 / 100;break;
			}
			displayNum = new StringBuffer("");
			displayNum.append(n1);
			displayNew();
		}
		
	}
	
	private double functionJC(double num)
	{
		return num;
	}
	
	private double functionX6Y(double num)
	{
		return num;
	}
	
	//以下为阶乘代码--------------------------------------------------------------------------------------------------------------------
	double gamma(double z)
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
	static int nni[] =
	{
	    0, 1, 2, 3, 4, 5, 6,100
	};
	static double nnx[] =
	{
	    0.1, 1.2, 2.5, 3.4, 4.0001, 5.99999999, 6.2222222, 3.5555, 5
	};
	void JCmain()
	{
	    double n = 5;
	    double ret = 0;
	 
	    for (int i=0; i < 8; ++i)
	    {
	        n = nni[i];
	        ret = gamma(n + 1);
	        System.out.println(ret);//printf("%g! :%.20g /n", n, ret);
	    }
	     
	    for (int i=0; i < 9; ++i)
	    {
	        n = nnx[i];
	        ret = gamma(n + 1);
	        //printf("%g! :%.20g /n", n, ret);
	    }
	    
	    for (int i=0; i < 9; ++i)
	    {
	        n = -nnx[i];
	        ret = gamma(n + 1);
	        //printf("%g! :%.20g /n", n, ret);
	    }
	}
	//以下为阶乘代码--------------------------------------------------------------------------------------------------------------------

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		onCreate(null);
		displayNew();
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
		case R.id.menu_about : openAbout();break;
		case R.id.menu_shownum : showNum = -showNum;showNumNow();if(showNum > 0 ) {item.setTitle(R.string.menu_shownumoff);}else {item.setTitle(R.string.menu_shownumon);}break;
		case R.id.menu_musicshare : MusicShare();break;
		case R.id.menu_help : openHelp();break;
		case 1 : GoToNumberSystem();break;
		case 2 : GoToChange();break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void openAbout() {
		new AlertDialog.Builder(MiniCalMain.this)
		.setTitle(R.string.AboutTitle)
		.setMessage(R.string.AboutMsg)
		.setPositiveButton(R.string.AboutConfirm, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MiniCalMain.this, R.string.AboutConfirmMsg, Toast.LENGTH_LONG).show();
			}
		})
		.setNegativeButton(R.string.AboutHome, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse(getString(R.string.AboutHomeUri));
				Intent intent =  new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		})
		.show();
	}
	
	private void openHelp() {
		new AlertDialog.Builder(MiniCalMain.this)
		.setTitle(R.string.HelpTitle)
		.setMessage(R.string.MainHelpMsg)
		.setPositiveButton(R.string.AboutConfirm, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
		.setNegativeButton(R.string.HelpHome, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse(getString(R.string.AboutHomeUri));
				Intent intent =  new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		})
		.show();
	}
	
	private void MusicShare() {
		new AlertDialog.Builder(MiniCalMain.this)
		.setTitle(R.string.menu_musicshare)
		.setMessage(R.string.MusicShareMsg)
		.setPositiveButton(R.string.AboutConfirm, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
		.setNegativeButton(R.string.openMusicShare, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse(getString(R.string.MusicShareUri));
				Intent intent =  new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		})
		.show();
	}
	
	private void GoToNumberSystem() {
		Intent intent = new Intent();
		intent.setClass(MiniCalMain.this, MiniCalNumberSystem.class);
		MiniCalMain.this.startActivity(intent);
		saveTemp();
		finish();
	}
	
	private void GoToChange() {
		Intent intent = new Intent();
		intent.setClass(MiniCalMain.this, MiniCalChange.class);
		MiniCalMain.this.startActivity(intent);
		saveTemp();
		finish();
	}
	
	private void saveTemp() {
		TempSave.setNum1(n1);
		TempSave.setNum2(n2);
		TempSave.setK(k);
		TempSave.setResult(result);
		TempSave.setPoint(point);
		TempSave.setAct(act);
		TempSave.setShowNum(showNum);
		TempSave.setDisplayNum(displayNum);
	}

}

/**---------------------------------What's new---------------------------------
 * 0.7.4-----------------------------------------------------------------------
 * 添加横屏布局
 * 0.7.3-----------------------------------------------------------------------
 * 修复若干BUG
 * 增加TempSave来储存数据
 * 覆写进制转换返回键
 * 0.7.2-----------------------------------------------------------------------
 * 重构程序减少Listener
 * 修复若干BUG
 * 进制转换增加按键提示
 * 0.7.1-----------------------------------------------------------------------
 * 重构程序，大量减少Listener
 * 添加ScrollView以支持较低分辨率的手机
 * 修复若干BUG： {
 * 计算器中输入数字并按了某个符号之后再按另一个符号而没作用的BUG
 * 按下等于号后num1不清零的BUG（按下等于号后直接按数字会在原num1基础上加上一位数）
 * }
 * 0.7.0-----------------------------------------------------------------------
 * 新增进制转换功能
 * 0.6.1-----------------------------------------------------------------------
 * 完善计算器功能及添加计算器菜单
 * 修复若干BUG
 ------------------------------------------------------------------------------*/
