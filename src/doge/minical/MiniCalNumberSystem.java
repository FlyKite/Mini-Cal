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

public class MiniCalNumberSystem extends Activity {
	private TextView changeFromDisplay;
	private TextView changeDisplay;
	private Button changeButtonAC;
	private Button changeNumber[] = new Button[16];
	private Button changeButtonBin;
	private Button changeButtonOct;
	private Button changeButtonDec;
	private Button changeButtonHex;
	private TextView changeResult1;
	private TextView changeResult2;
	private TextView changeResult3;
	private TextView changeNumLongDis;
	private TextView changeFromcniDis;
	private TextView changeShowNum;
	
	char changeNum[] = new char[15];
	char changeResult16[] = new char[15];
	String changeFromD[] = new String[17];
	int changeFrom=10,cni=0,showNum = -1;
	long changeNumLong,changeResult[] = new long[3];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			setContentView(R.layout.minicalnumbersystemlandscape);
		}
		else {
			setContentView(R.layout.minicalnumbersystem);
		}
		findView();
		if(changeNum[0] != 0) {
			displayNew();
		}
		changeFromD[2] = "Bin";
		changeFromD[8] = "Oct";
		changeFromD[10] = "Dec";
		changeFromD[16] = "Hex";
		changeButtonAC.setOnClickListener(new ChangeButtonACListener());
		for(int i = 0; i < 16; i++) {
			changeNumber[i].setOnClickListener(new ChangeNumberListener());
		}
		changeButtonBin.setOnClickListener(new ChangeButtonListener());
		changeButtonOct.setOnClickListener(new ChangeButtonListener());
		changeButtonDec.setOnClickListener(new ChangeButtonListener());
		changeButtonHex.setOnClickListener(new ChangeButtonListener());
	}
	
	public void findView(){
		changeFromDisplay = (TextView)findViewById(R.id.changefromdisplay);
		changeDisplay = (TextView)findViewById(R.id.changedisplay);
		changeButtonAC = (Button)findViewById(R.id.changebuttonac);
		changeNumber[0] = (Button)findViewById(R.id.changenumber0);
		changeNumber[1] = (Button)findViewById(R.id.changenumber1);
		changeNumber[2] = (Button)findViewById(R.id.changenumber2);
		changeNumber[3] = (Button)findViewById(R.id.changenumber3);
		changeNumber[4] = (Button)findViewById(R.id.changenumber4);
		changeNumber[5] = (Button)findViewById(R.id.changenumber5);
		changeNumber[6] = (Button)findViewById(R.id.changenumber6);
		changeNumber[7] = (Button)findViewById(R.id.changenumber7);
		changeNumber[8] = (Button)findViewById(R.id.changenumber8);
		changeNumber[9] = (Button)findViewById(R.id.changenumber9);
		changeNumber[10] = (Button)findViewById(R.id.changenumbera);
		changeNumber[11] = (Button)findViewById(R.id.changenumberb);
		changeNumber[12] = (Button)findViewById(R.id.changenumberc);
		changeNumber[13] = (Button)findViewById(R.id.changenumberd);
		changeNumber[14] = (Button)findViewById(R.id.changenumbere);
		changeNumber[15] = (Button)findViewById(R.id.changenumberf);
		changeButtonBin = (Button)findViewById(R.id.changebuttonbin);
		changeButtonOct = (Button)findViewById(R.id.changebuttonoct);
		changeButtonDec = (Button)findViewById(R.id.changebuttondec);
		changeButtonHex = (Button)findViewById(R.id.changebuttonhex);
		changeResult1 = (TextView)findViewById(R.id.changeresult1);
		changeResult2 = (TextView)findViewById(R.id.changeresult2);
		changeResult3 = (TextView)findViewById(R.id.changeresult3);
		changeNumLongDis = (TextView)findViewById(R.id.changenumlongdis);
		changeFromcniDis = (TextView)findViewById(R.id.changefromcnidis);
		changeShowNum = (TextView)findViewById(R.id.changeshownum);
	}
	
	private void changeError(int n) {
		Toast.makeText(MiniCalNumberSystem.this, getString(R.string.changenotfrom16error), Toast.LENGTH_LONG).show();
	}
	
	private void displayNew() {
		changeDisplay.setText("" + changeNum[0] + changeNum[1] + changeNum[2] + changeNum[3] + changeNum[4] + changeNum[5] + changeNum[6] + changeNum[7] + changeNum[8] + changeNum[9] + changeNum[10] + changeNum[11] + changeNum[12] + changeNum[13] + changeNum[14]);
		change();
		switch(changeFrom) {
		case 2 : changeFrom2();break;
		case 8 : changeFrom8();break;
		case 10 : changeFrom10();break;
		case 16 : changeFrom16();break;
		}
		showNumNow();
	}
	
	private void showNumNow() {
		if(showNum > 0) {
			boolean shownumb = true;
			changeNumLongDis.setText("changenumlong = " + changeNumLong);
			changeFromcniDis.setText("changefrom = " + changeFrom + "      cni = " + cni);
			changeShowNum.setText("shownum = " + shownumb);
		}
		else {
			changeNumLongDis.setText("");
			changeFromcniDis.setText("");
			changeShowNum.setText("");			
		}
		
	}
	
	private void changeFrom2() {
		changeResult1.setText(getString(R.string.changeresult8) + changeResult[0]);
		changeResult2.setText(getString(R.string.changeresult10) + changeResult[1]);
		changeResult3.setText(getString(R.string.changeresult16) + changeResult16[0] + changeResult16[1] + changeResult16[2] + changeResult16[3] + changeResult16[4] + changeResult16[5] + changeResult16[6] + changeResult16[7] + changeResult16[8] + changeResult16[9] + changeResult16[10] + changeResult16[11] + changeResult16[12] + changeResult16[13] + changeResult16[14]);
	}
	
	private void changeFrom8() {
		if(changeNumLong < 1777778) {
			changeResult1.setText(getString(R.string.changeresult2) + changeResult[0]);
		}
		else {
			changeResult1.setText(getString(R.string.changeresult2) + getString(R.string.SpillOverFrom8));
		}
		changeResult2.setText(getString(R.string.changeresult10) + changeResult[1]);
		changeResult3.setText(getString(R.string.changeresult16) + changeResult16[0] + changeResult16[1] + changeResult16[2] + changeResult16[3] + changeResult16[4] + changeResult16[5] + changeResult16[6] + changeResult16[7] + changeResult16[8] + changeResult16[9] + changeResult16[10] + changeResult16[11] + changeResult16[12] + changeResult16[13] + changeResult16[14]);
	}
	
	private void changeFrom10() {
		if(changeNumLong < 524288) {
			changeResult1.setText(getString(R.string.changeresult2) + changeResult[0]);
		}
		else {
			changeResult1.setText(getString(R.string.changeresult2) + getString(R.string.SpillOverFrom10));
		}
		changeResult2.setText(getString(R.string.changeresult8) + changeResult[1]);
		changeResult3.setText(getString(R.string.changeresult16) + changeResult16[0] + changeResult16[1] + changeResult16[2] + changeResult16[3] + changeResult16[4] + changeResult16[5] + changeResult16[6] + changeResult16[7] + changeResult16[8] + changeResult16[9] + changeResult16[10] + changeResult16[11] + changeResult16[12] + changeResult16[13] + changeResult16[14]);
	}
	
	private void changeFrom16() {
		if(changeResult[2] < 524288) {
			changeResult1.setText(getString(R.string.changeresult2) + changeResult[0]);
		}
		else {
			changeResult1.setText(getString(R.string.changeresult2) + getString(R.string.SpillOverFrom16));
		}
		changeResult2.setText(getString(R.string.changeresult8) + changeResult[1]);
		changeResult3.setText(getString(R.string.changeresult10) + changeResult[2]);
	}
	
	private void change() {
		if(changeFrom <= 10) {
			chartolong();
		}
		switch(changeFrom) {
		case 2 : changeResult[0] = f10t8(f2t10(changeNumLong));changeResult[1] = f2t10(changeNumLong);f10t16(f2t10(changeNumLong));break;
		case 8 : changeResult[0] = f10t2(f8t10(changeNumLong));changeResult[1] = f8t10(changeNumLong);f10t16(f8t10(changeNumLong));break;
		case 10 : changeResult[0] = f10t2(changeNumLong);changeResult[1] = f10t8(changeNumLong);f10t16(changeNumLong);break;
		case 16 : changeResult[0] = f10t2(f16t10());changeResult[1] = f10t8(f16t10());changeResult[2] = f16t10();break;
		}
	}
	
	//------------以下代码来自我之前用C语言写的进制转换，做了少许改动------------
	
	private void chartolong() {
		changeNumLong = 0;
		int i;
		long m;
		for(i = 0;i < 15 && changeNum[i] != 0; i++) {
			if(changeNum[i] >= 48 + changeFrom) {
				changeError(1);
				cni = 16;
				break;
			}
		}
		for(m = 1, i--; i >= 0 && i < 15 && cni < 16; i--)
		{
			changeNumLong = changeNumLong + (changeNum[i] - '0') * m;
			m = m * 10;
		}
		if(cni > 15) {
			changeNumLong = 0;
		}
	}
	
	private long f2t10(long num)
	{
		long m=1,s=0;
		while(num!=0)
		{
			if(num%10>1) changeError(2);
			s=s+num%2*m;
			num=num/10;
			m=m*2;
		}
		return s;
	}
	
	private long f8t10(long num)
	{
		long m=1,s=0;
		while(num!=0)
		{
			if(num%10>7) changeError(8);
			s=s+num%10*m;
			num=num/10;
			m=m*8;
		}
		return s;
	}
	
	private long f16t10()
	{
		int i;
		long m,s=0;
		for(i=0;i<15&&changeNum[i]!=0;i++);
		for(m=1,i--;i>=0;i--)
		{
			if(changeNum[i]>=65&&changeNum[i]<=90) s=s+(changeNum[i]-55)*m;
			else s=s+(changeNum[i]-48)*m;
			m=m*16;
		}
		return s;
	}
	
	private long f10t2(long num)
	{
		long s=0,m=1;
		while(num!=0)
		{
			s=s+num%2*m;
			num=num/2;
			m=m*10;
		}
		return s;
	}
	
	private long f10t8(long num)
	{
		long s=0,m=1;
		while(num!=0)
		{
			s=s+num%8*m;
			num=num/8;
			m=m*10;
		}
		return s;
	}
	
	private void f10t16(long num)
	{
		int i;
		long c=num;
		for(i=0;c!=0;i++)
			c=c/16;
		if(i==0) {
			changeResult16[i--]='0';
		}
		else {
			changeResult16[i--]=0;
		}
		for(;i>=0;i--)
		{
			if(num%16>9) changeResult16[i]=(char) ('A'+num%16-10);
			else changeResult16[i]=(char) ('0'+num%16);
			num=num/16;
		}
	}
	
	//------------以上代码------------
	
	class ChangeButtonACListener implements OnClickListener {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			changeNum[0] = '0';
			for(int i = 1; i < 15; i++) {
				changeNum[i] = 0;
				changeResult16[i] = 0;
			}
			cni = 0;
			changeNumLong = 0;
			displayNew();
		}
		
	}
	
	class ChangeNumberListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()) {
			case R.id.changenumber0 : changeNumberListener(0);break;
			case R.id.changenumber1 : changeNumberListener(1);break;
			case R.id.changenumber2 : changeNumberListener(2);break;
			case R.id.changenumber3 : changeNumberListener(3);break;
			case R.id.changenumber4 : changeNumberListener(4);break;
			case R.id.changenumber5 : changeNumberListener(5);break;
			case R.id.changenumber6 : changeNumberListener(6);break;
			case R.id.changenumber7 : changeNumberListener(7);break;
			case R.id.changenumber8 : changeNumberListener(8);break;
			case R.id.changenumber9 : changeNumberListener(9);break;
			case R.id.changenumbera : changeNumberListener(17);break;
			case R.id.changenumberb : changeNumberListener(18);break;
			case R.id.changenumberc : changeNumberListener(19);break;
			case R.id.changenumberd : changeNumberListener(20);break;
			case R.id.changenumbere : changeNumberListener(21);break;
			case R.id.changenumberf : changeNumberListener(22);break;
			}
		}
		
	}

	private void changeNumberListener(int n) {
		if(cni < 2 && changeNum[0] == '0') {
			changeNum[0] = 0;
			cni = 0;
		}
		if(cni < 15) {
			if(changeFrom == 16) {
				changeNum[cni++] = (char) (48 + n);
			}
			else if(changeFrom == 10 && n < 10) {
				changeNum[cni++] = (char) (48 + n);
			}
			else if(changeFrom == 8 && n < 8) {
				changeNum[cni++] = (char) (48 + n);
			}
			else if(changeFrom == 2 && n < 2) {
				changeNum[cni++] = (char) (48 + n);
			}
			displayNew();
		}
	}
	
	class ChangeButtonListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String choose = new String();
			switch(v.getId()) {
			case R.id.changebuttonbin : changefromListener(2);choose = "您选择了输入二进制数";break;
			case R.id.changebuttonoct : changefromListener(8);choose = "您选择了输入八进制数";break;
			case R.id.changebuttondec : changefromListener(10);choose = "您选择了输入十进制数";break;
			case R.id.changebuttonhex : changefromListener(16);choose = "您选择了输入十六进制数";break;
			}
			Toast.makeText(MiniCalNumberSystem.this, choose, Toast.LENGTH_SHORT).show();
		}
		
	}
	
	private void changefromListener(int n) {
		changeFrom = n;
		changeFromDisplay.setText("" + changeFromD[changeFrom]);
		displayNew();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		/**if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			setContentView(R.layout.minicalnumbersystemlandscape);
		}
		else {
			setContentView(R.layout.minicalnumbersystem);
		}*/
		onCreate(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		menu.add(0 , 1 , 0 ,getString(R.string.menu_minicalback));
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
		case 1 : goBack();break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void openAbout() {
		new AlertDialog.Builder(MiniCalNumberSystem.this)
		.setTitle(R.string.AboutTitle)
		.setMessage(R.string.AboutMsg)
		.setPositiveButton(R.string.AboutConfirm, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MiniCalNumberSystem.this, R.string.AboutConfirmMsg, Toast.LENGTH_LONG).show();
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
	
	public void openHelp() {
		new AlertDialog.Builder(MiniCalNumberSystem.this)
		.setTitle(R.string.HelpTitle)
		.setMessage(R.string.ChangeHelpMsg)
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
	
	public void MusicShare() {
		new AlertDialog.Builder(MiniCalNumberSystem.this)
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
	
	public void goBack() {
			Intent intent = new Intent();
			intent.setClass(MiniCalNumberSystem.this, MiniCalMain.class);
			MiniCalNumberSystem.this.startActivity(intent);
			MiniCalMain.getTempSave();
			finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		goBack();
		super.onBackPressed();
	}
	
}
