package doge.minical;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;



public class MiniCalChange extends MiniCalMenu {
	private TextView changefromdisplay;
	private TextView changetodisplay;
	private Button changefrom[] = new Button[10];
	private Button changefrompoint;
	private Button changefromac;
	private Spinner changeType;
	private Spinner changeFrom;
	private Spinner changeTo;
	
	int changeTypeChose,changeFromChose,changeToChose;
	StringBuffer changeResult = new StringBuffer("0");
	StringBuffer changeFromNum = new StringBuffer("0");
	boolean point = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			setContentView(R.layout.minicalchangelandscape);
		}
		else {
			setContentView(R.layout.minicalchange);
		}
		setTitlePadding();
		setBackGroundColor(MiniCalChange.this);
		findview();
		TextSize();
		setSpinner();
		for(int i = 0; i < 10; i++) {
			changefrom[i].setOnClickListener(new ChangeFromNumListener());
		}
		changefrompoint.setOnClickListener(new ChangeFromPointListener());
		changefromac.setOnClickListener(new ChangeFromACListener());
		changeType.setOnItemSelectedListener(new ChangeListener());
		changeFrom.setOnItemSelectedListener(new ChangeListener());
		changeTo.setOnItemSelectedListener(new ChangeListener());
	}
	
	private void displayNew() {
		change();
		changefromdisplay.setText("" + changeFromNum);
		changetodisplay.setText("" + changeResult);
	}
	
	private void change() {
		double from, to;
		from = Double.parseDouble(changeFromNum.toString());
		to = from * fromTo();
		changeResult = new StringBuffer("");
		changeResult.append(to);
		if(changeResult.substring(changeResult.length() - 2, changeResult.length()).equals(".0")) {
			changeResult.delete(changeResult.length() - 2, changeResult.length());
		}
	}
	
	private double fromTo() {
		if(changeFromChose == changeToChose) {
			return 1;
		}
		switch(changeTypeChose * 100 + changeFromChose * 10 + changeToChose) {
		case 67 : return 0.001;
		case 1 : break;
		case 2 : break;
		}
		return 1;
	}
	
	private void findview() {
		changefromdisplay = (TextView)findViewById(R.id.changefromdisplay);
		changetodisplay = (TextView)findViewById(R.id.changetodisplay);
		changefrom[0] = (Button)findViewById(R.id.changefrom0);
		changefrom[1] = (Button)findViewById(R.id.changefrom1);
		changefrom[2] = (Button)findViewById(R.id.changefrom2);
		changefrom[3] = (Button)findViewById(R.id.changefrom3);
		changefrom[4] = (Button)findViewById(R.id.changefrom4);
		changefrom[5] = (Button)findViewById(R.id.changefrom5);
		changefrom[6] = (Button)findViewById(R.id.changefrom6);
		changefrom[7] = (Button)findViewById(R.id.changefrom7);
		changefrom[8] = (Button)findViewById(R.id.changefrom8);
		changefrom[9] = (Button)findViewById(R.id.changefrom9);
		changefrompoint = (Button)findViewById(R.id.changefrompoint);
		changefromac = (Button)findViewById(R.id.changefromAC);
		changeType = (Spinner)findViewById(R.id.changespinner);
		changeFrom = (Spinner)findViewById(R.id.fromspinner);
		changeTo = (Spinner)findViewById(R.id.tospinner);
	}
	
	private void setSpinner() {
		ArrayAdapter<CharSequence> adp_changeType = ArrayAdapter.createFromResource(this, R.array.changetype, android.R.layout.simple_spinner_item);
		adp_changeType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		changeType.setAdapter(adp_changeType);
	}
	
	private void setSpinnerFromTo() {
		ArrayAdapter<CharSequence> adp_changeFrom = null;
		switch(changeTypeChose) {
		case 0 : adp_changeFrom = ArrayAdapter.createFromResource(this, R.array.changelength, android.R.layout.simple_spinner_item);break;
		case 1 : adp_changeFrom = ArrayAdapter.createFromResource(this, R.array.changearea, android.R.layout.simple_spinner_item);break;
		case 2 : adp_changeFrom = ArrayAdapter.createFromResource(this, R.array.changevolume, android.R.layout.simple_spinner_item);break;
		}
		adp_changeFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		changeFrom.setAdapter(adp_changeFrom);
		changeTo.setAdapter(adp_changeFrom);
	}
	
	class ChangeListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			// TODO Auto-generated method stub
			switch(arg0.getId()) {
			case R.id.changespinner : changeTypeChose = arg0.getSelectedItemPosition();setSpinnerFromTo();break;
			case R.id.fromspinner : changeFromChose = arg0.getSelectedItemPosition();break;
			case R.id.tospinner : changeToChose = arg0.getSelectedItemPosition();break;
			}
			displayNew();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ChangeFromNumListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int choice = 0;
			switch(v.getId()) {
			case R.id.changefrom0 : choice = 0;break;
			case R.id.changefrom1 : choice = 1;break;
			case R.id.changefrom2 : choice = 2;break;
			case R.id.changefrom3 : choice = 3;break;
			case R.id.changefrom4 : choice = 4;break;
			case R.id.changefrom5 : choice = 5;break;
			case R.id.changefrom6 : choice = 6;break;
			case R.id.changefrom7 : choice = 7;break;
			case R.id.changefrom8 : choice = 8;break;
			case R.id.changefrom9 : choice = 9;break;
			}
			changefromlistener(choice);
		}
		
	}
	
	class ChangeFromPointListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(point) {
				changeFromNum.append(".");
				point = false;
				displayNew();
			}
		}
		
	}
	
	private void changefromlistener(int choice) {
		if(changeFromNum.charAt(0) == '0' && point) {
			changeFromNum.deleteCharAt(changeFromNum.length() - 1);
			changeFromNum.append(choice);
		}
		else {
			changeFromNum.append(choice);
		}
		displayNew();
	}
	
	class ChangeFromACListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			changeFromNum = new StringBuffer("0");
			changeResult = new StringBuffer("0");
			point = true;
			displayNew();
		}
		
	}
	
	private void TextSize() {
		SharedPreferences tSize = getSharedPreferences("cTextSize", 0);
		int btn_size, dis_size;
		btn_size = tSize.getInt("btn_size", 15) + 10;
		dis_size = tSize.getInt("dis_size", 18) + 20;
		changefromdisplay.setTextSize(dis_size);
		changetodisplay.setTextSize(dis_size);
		for(int i = 0; i < 10; i++) {
			changefrom[i].setTextSize(btn_size);
		}
		changefrompoint.setTextSize(btn_size);
		changefromac.setTextSize(btn_size);
//		private Spinner changeType;
//		private Spinner changeFrom;
//		private Spinner changeTo;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		onCreate(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_main, menu);
		menu.add(0 , 1 , 0 ,getString(R.string.menu_minicalback));
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
		case R.id.menu_about : openAbout(MiniCalChange.this);break;
		case R.id.menu_musicshare : MusicShare(MiniCalChange.this);break;
		case R.id.menu_motto : showMotto(MiniCalChange.this);break;
//		case R.id.menu_help : openHelp(MiniCalChange.this);break;
		case 1 : goBack();break;
		case 4 : BackGroundColor(MiniCalChange.this);break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void goBack() {
			Intent intent = new Intent();
			intent.setClass(MiniCalChange.this, MiniCalMain.class);
			MiniCalChange.this.startActivity(intent);
			finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		goBack();
		super.onBackPressed();
	}

}
