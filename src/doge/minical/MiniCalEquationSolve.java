package doge.minical;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MiniCalEquationSolve extends MiniCalMenu {

	EditText textA, textB, textC;
	TextView textResult;
	final String TAG = "Equation Solve";
	String equation, txtResult;
	Button solve, reset;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			setContentView(R.layout.minicalequationsolveland);
		}
		else
		{
			setContentView(R.layout.minicalequationsolve);
		}
		setTitlePadding();
		setBackGroundColor(MiniCalEquationSolve.this);
		textA = (EditText) findViewById(R.id.num_a);
		textB = (EditText) findViewById(R.id.num_b);
		textC = (EditText) findViewById(R.id.num_c);
		textResult = (TextView) findViewById(R.id.text_show_result);
		solve = (Button)findViewById(R.id.button_solve);
		reset = (Button)findViewById(R.id.button_reset);
		//extSize();
	}

	public void solve(View view) {
		if (textA.getText().toString().equals("")) {
			int b = Integer
					.parseInt((textB.getText().toString().equals("")) ? "0"
							: textB.getText().toString());
			int c = Integer
					.parseInt((textC.getText().toString().equals("")) ? "0"
							: textC.getText().toString());
			oneTime(b, c);

		} else {
			int a = Integer.parseInt(textA.getText().toString());
			int b = Integer
					.parseInt((textB.getText().toString().equals("")) ? "0"
							: textB.getText().toString());
			int c = Integer
					.parseInt((textC.getText().toString().equals("")) ? "0"
							: textC.getText().toString());
			if (a == 0) {
				oneTime(b, c);
			} else {
				twoTimes(a, b, c);
			}

		}
	}

	public void oneTime(int b, int c) {
		equation = ((Math.abs(b) == 1) ? ((b > 0) ? "" : "-") : b) + "x"
				+ (c > 0 ? "+" + c : "") + ((c < 0) ? c : "") + "=0";
		if (b == 0) {
			Toast.makeText(this, R.string.b_error, Toast.LENGTH_LONG).show();
			textResult.setText(equation);
			return;
		}
		double result = -(double) c / b;
		txtResult = "有一个根:x=-" + c + "/" + b + "≈" + result;
		textResult.setText(equation + "\n" + txtResult);
	}

	public void twoTimes(int a, int b, int c) {

		equation = ((Math.abs(a) == 1) ? ((a > 0) ? "" : "-") : a)
				+ "x²"
				+ ((b == 0) ? "" : ((Math.abs(b) == 1) ? ((b > 0) ? "+" : "-")
						: (b < 0) ? b : "+" + b) + "x")
				+ (c > 0 ? "+" + c : "") + ((c < 0) ? c : "") + "=0";
		int delta = b * b - 4 * a * c;
		if (delta < 0) {
			Toast.makeText(this, R.string.delta_error, Toast.LENGTH_LONG)
					.show();
			textResult.setText(equation);
			return;
		} else if (delta == 0) {
			double x = (-b) / (2 * a);
			txtResult = "有两个相等的实数根:x1=x2=" + x;
		} else {
			double sqrt = Math.sqrt(delta);
			double x1 = (-b + sqrt) / (2 * a), x2 = (-b - sqrt) / (2 * a);
			txtResult = "有两个不相等的实数根:\nx1=" + "(" + (-b) + "+√" + delta + ")"
					+ "/" + ((a < 0) ? "(" + a * 2 + ")" : a * 2) + "≈" + x1
					+ "\nx2=" + "(" + (-b) + "-√" + delta + ")" + "/"
					+ ((a < 0) ? "(" + a * 2 + ")" : a * 2) + "≈" + x2;

		}
		textResult.setText(equation + "\n" + txtResult);
	}

	public void reset(View view) {
		textA.setText("");
		textB.setText("");
		textC.setText("");
		textResult.setText(R.string.to_show_result);
	}
	
	private void TextSize() {
		SharedPreferences tSize = getSharedPreferences("esTextSize", 0);
		int btn_size, dis_size;
		btn_size = tSize.getInt("btn_size", 15) + 10;
		dis_size = tSize.getInt("dis_size", 18) + 20;
		textA.setTextSize(dis_size);
		textB.setTextSize(dis_size);
		textC.setTextSize(dis_size);
		textResult.setTextSize(dis_size);;
		solve.setTextSize(btn_size);
		reset.setTextSize(btn_size);
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
	    SubMenu customize = menu.addSubMenu(0, 0, 0, getString(R.string.menu_customize));
	    customize.add(0, 4, 0, getString(R.string.menu_backgroundcolor));
	    //customize.add(0, 5, 0, getString(R.string.menu_textsize));
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.menu_exit : finish();break;
		case R.id.menu_about : openAbout(MiniCalEquationSolve.this);break;
		case R.id.menu_musicshare : MusicShare(MiniCalEquationSolve.this);break;
		case R.id.menu_help : openHelp(MiniCalEquationSolve.this);break;
		case 1 : goBack();break;
		case 4 : BackGroundColor(MiniCalEquationSolve.this);break;
		case 5 : TextSize(MiniCalEquationSolve.this);break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		goBack();
		super.onBackPressed();
	}
	
	public void goBack() {
		Intent intent = new Intent();
		intent.setClass(MiniCalEquationSolve.this, MiniCalMain.class);
		MiniCalEquationSolve.this.startActivity(intent);
		finish();
}
}
