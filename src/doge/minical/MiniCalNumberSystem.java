package doge.minical;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MiniCalNumberSystem extends MiniCalMenu {
	private TextView changeFromDisplay;
	private EditText changeDisplay;
	private Button changeButtonAC;
	private Button changeNumber[] = new Button[16];
	private Button changeButtonBin;
	private Button changeButtonOct;
	private Button changeButtonDec;
	private Button changeButtonHex;
	private TextView changeResultDis[] = new TextView[17];
	
	StringBuffer changeNum = new StringBuffer();
	StringBuffer changeResult[] = new StringBuffer[17];
	String changeFromD[] = new String[17];
	int changeFrom = 10;
	
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
		if(Build.VERSION.SDK_INT > 10) {
			findViewById(R.id.topbar).setVisibility(View.GONE);
		}
		findView();
		changeResultDis[changeFrom].setVisibility(View.GONE);
		if(changeNum.length() == 0) {
			changeNum.append('0');
		}
		displayNew();
		changeFromD[2] = "Bin";
		changeFromD[8] = "Oct";
		changeFromD[10] = "Dec";
		changeFromD[16] = "Hex";
		changeFromDisplay.setText("" + changeFromD[changeFrom]);
		changeButtonAC.setOnClickListener(new ChangeButtonACListener());
		for(int i = 0; i < 16; i++) {
			changeNumber[i].setOnClickListener(new ChangeNumberListener());
		}
		setChangeButtonAvailable(changeFrom);
		changeButtonBin.setOnClickListener(new ChangeButtonListener());
		changeButtonOct.setOnClickListener(new ChangeButtonListener());
		changeButtonDec.setOnClickListener(new ChangeButtonListener());
		changeButtonHex.setOnClickListener(new ChangeButtonListener());
	}
	
	public void findView(){
		changeFromDisplay = (TextView)findViewById(R.id.changefromdisplay);
		changeDisplay = (EditText)findViewById(R.id.changedisplay);
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
		changeResultDis[2] = (TextView)findViewById(R.id.changeresult2);
		changeResultDis[8] = (TextView)findViewById(R.id.changeresult8);
		changeResultDis[10] = (TextView)findViewById(R.id.changeresult10);
		changeResultDis[16] = (TextView)findViewById(R.id.changeresult16);
	}
	
	private void displayNew() {
		changeDisplay.setText("" + changeNum);
		changeDisplay.setSelection(changeDisplay.length());
		change();
		changeResultDis[2].setText(getString(R.string.changeresult2) + changeResult[2]);
		changeResultDis[8].setText(getString(R.string.changeresult8) + changeResult[8]);
		changeResultDis[10].setText(getString(R.string.changeresult10) + changeResult[10]);
		changeResultDis[16].setText(getString(R.string.changeresult16) + changeResult[16]);
	}
	
	private void change() {
		if(fromTo10(changeNum, changeFrom) == true) {
			from10To(changeResult[10], 2);
			from10To(changeResult[10], 8);
			from10To(changeResult[10], 16);
		}
	}
	
	public void from10To(StringBuffer changeFN, int to) {
		StringBuffer changeFromNum = new StringBuffer();
		changeFromNum.append(changeFN.toString());
		long changeFromLong = 0;
		changeFromLong = Long.parseLong(changeFromNum.toString());
		changeResult[to] = new StringBuffer("");
		if(changeFromLong == 0) {
			changeResult[to].append(0);
		}
		while(changeFromLong != 0) {
			int qiuyu;
			qiuyu = (int) (changeFromLong % to);
			if(qiuyu > 9) {
				qiuyu += 7;
			}
			changeResult[to].insert(0, (char)(qiuyu + 48));
			changeFromLong /= to;
		}
	}
	
	public boolean fromTo10(StringBuffer changeFN, int from) {
		StringBuffer changeFromNum = new StringBuffer();
		changeFromNum.append(changeFN.toString());
		long changeResult10 = 0, k = 1;
		char lastChar;
		changeResult[10] = new StringBuffer("");
		while(changeFromNum.length() != 0) {
			lastChar = changeFromNum.charAt(changeFromNum.length() - 1);
			int lastNum;
			if((int)(lastChar) > 58) {
				lastNum = (int)lastChar - 55;
			}
			else {
				lastNum = (int)lastChar - 48;
			}
			if(lastNum >= from) {
				Toast.makeText(MiniCalNumberSystem.this, getString(R.string.changenotfromerror), Toast.LENGTH_LONG).show();
				returnFalse();
				return false;
			}
			changeResult10 += lastNum * k;
			k = k * from;
			changeFromNum.deleteCharAt(changeFromNum.length() - 1);
		}
		if(changeResult10 < 0) {
			Toast.makeText(MiniCalNumberSystem.this, getString(R.string.changeoverflowerror), Toast.LENGTH_LONG).show();
			returnFalse();
			return false;
		}
		changeResult[10].append(changeResult10);
		return true;
	}
	
	private void returnFalse() {
		setChangeButtonAvailable(0);
		changeResult[2] = new StringBuffer("");
		changeResult[8] = new StringBuffer("");
		changeResult[10] = new StringBuffer("");
		changeResult[16] = new StringBuffer("");
	}
	
	class ChangeButtonACListener implements OnClickListener {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			changeNum = new StringBuffer("");
			changeNum.append(0);
			changeResult[2] = new StringBuffer("");
			changeResult[8] = new StringBuffer("");
			changeResult[10] = new StringBuffer("");
			changeResult[16] = new StringBuffer("");
			setChangeButtonAvailable(changeFrom);
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
		if(changeNum.length() < 2 && (int)changeNum.charAt(0) == 48) {
			changeNum.deleteCharAt(0);
		}
		changeNum.append((char)(48 + n));
		displayNew();
	}
	
	class ChangeButtonListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String choose = new String();
			switch(v.getId()) {
			case R.id.changebuttonbin : changefromListener(2);choose = getText(R.string.changechoose2).toString();break;
			case R.id.changebuttonoct : changefromListener(8);choose = getText(R.string.changechoose8).toString();break;
			case R.id.changebuttondec : changefromListener(10);choose = getText(R.string.changechoose10).toString();break;
			case R.id.changebuttonhex : changefromListener(16);choose = getText(R.string.changechoose16).toString();break;
			}
			Toast.makeText(MiniCalNumberSystem.this, choose, Toast.LENGTH_SHORT).show();
		}
		
	}
	
	private void changefromListener(int n) {
		changeResultDis[changeFrom].setVisibility(View.VISIBLE);
		changeFrom = n;
		setChangeButtonAvailable(changeFrom);
		changeResultDis[changeFrom].setVisibility(View.GONE);
		changeFromDisplay.setText("" + changeFromD[changeFrom]);
		displayNew();
	}
	
	private void setChangeButtonAvailable(int to) {
		for(int i = 0; i < 16; i++) {
			changeNumber[i].setClickable(false);
		}
		for(int i = 0; i < to; i++) {
			changeNumber[i].setClickable(true);
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
		menu.add(0 , 1 , 0 ,getString(R.string.menu_minicalback));
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.menu_exit : finish();break;
		case R.id.menu_about : openAbout(MiniCalNumberSystem.this);break;
		case R.id.menu_musicshare : MusicShare(MiniCalNumberSystem.this);break;
		case R.id.menu_help : openHelp(MiniCalNumberSystem.this);break;
		case 1 : goBack();break;
		}
		return super.onOptionsItemSelected(item);
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
