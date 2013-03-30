package doge.minical;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
	
	int changeTypeChose,changeFromChose,changeToChose,point = 0;
	double changeFromNum,changeResult,k = 1;

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
		if(Build.VERSION.SDK_INT > 10) {
			findViewById(R.id.topbar).setVisibility(View.GONE);
		}
		findview();
		setSpinner();
		for(int i = 0; i < 10; i++) {
			changefrom[i].setOnClickListener(new ChangeFromNumListener());
		}
		changefrompoint.setOnClickListener(new ChangeFromPointListener());
		changefromac.setOnClickListener(new ChangeFromACListener());
		changeType.setOnItemSelectedListener(new ChangeTypeListener());
		changeFrom.setOnItemSelectedListener(new ChangeFromListener());
		changeTo.setOnItemSelectedListener(new ChangeToListener());
	}
	
	private void displayNew() {
		changefromdisplay.setText("" + changeFromNum);
		changetodisplay.setText("" + changeResult);
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
	
	class ChangeTypeListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			// TODO Auto-generated method stub
			changeTypeChose = arg0.getSelectedItemPosition();
			setSpinnerFromTo();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ChangeFromListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			// TODO Auto-generated method stub
			changeFromChose = arg0.getSelectedItemPosition();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ChangeToListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			// TODO Auto-generated method stub
			changeToChose = arg0.getSelectedItemPosition();
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
			point = 1;
		}
		
	}
	
	private void changefromlistener(int choice) {
		if(point == 0) {
			changeFromNum = changeFromNum * 10 + choice;
		}
		else {
			k = k * 10;
			changeFromNum = changeFromNum + choice / k;
		}
		displayNew();
	}
	
	class ChangeFromACListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			changeFromNum = 0;
			changeResult = 0;
			k = 0;
			displayNew();
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
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_main, menu);
		menu.add(0 , 1 , 0 ,getString(R.string.menu_minicalback));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.menu_exit : finish();break;
		case R.id.menu_about : openAbout(MiniCalChange.this);break;
		case R.id.menu_musicshare : MusicShare(MiniCalChange.this);break;
		case R.id.menu_help : openHelp(MiniCalChange.this);break;
		case 1 : goBack();break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void goBack() {
			Intent intent = new Intent();
			intent.setClass(MiniCalChange.this, MiniCalMain.class);
			MiniCalChange.this.startActivity(intent);
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
