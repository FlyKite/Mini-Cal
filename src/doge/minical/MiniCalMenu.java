package doge.minical;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MiniCalMenu extends Activity {
	
	protected void setTitlePadding() {
		int paddingLeft, paddingTop;
        SharedPreferences barPadding = getSharedPreferences("padding", 0);
        if(barPadding.getBoolean("firstStart", true)) {
        	DisplayMetrics metric = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metric);
            int width;
            if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            	width = metric.heightPixels;
            }
            else
            {
            	width = metric.widthPixels;
            }
            if(width < 720) {
            	if(width < 320){
            		paddingLeft = 40;
            		paddingTop = 33;
            	}
            	else if(width < 480) {
            		paddingLeft = 52;
            		paddingTop = 44;
            	}
            	else {
            		paddingLeft = 60;
            		paddingTop = 48;
            	}
            }
            else {
            	paddingLeft = 85;
            	paddingTop = 70;
            }
            barPadding.edit().putInt("paddingLeft", paddingLeft).commit();
            barPadding.edit().putInt("paddingTop", paddingTop).commit();
            barPadding.edit().putBoolean("firstStart", false).commit();
        }
        else {
        	paddingLeft = barPadding.getInt("paddingLeft", 0);
        	paddingTop = barPadding.getInt("paddingTop", 0);
        }
		TextView t = (TextView)findViewById(R.id.bartitle);
		if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			t.setPadding(0, paddingTop, 0, 0);
		}
		else {
			t.setPadding(paddingLeft, 0, 0, 0);
		}
        
	}
	
	protected void openAbout(final Activity from) {
		new AlertDialog.Builder(from)
		.setTitle(R.string.AboutTitle)
		.setMessage(getString(R.string.AboutMsg) + getString(R.string.VersionName))
		.setPositiveButton(R.string.AboutConfirm, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(from, R.string.AboutConfirmMsg, Toast.LENGTH_LONG).show();
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
	
	private String getStringFrom(Activity from) {
		if(from.getTitle().equals(getString(R.string.app_name))) {
			return getString(R.string.MainHelpMsg);
		}
		else if(from.getTitle().equals(getString(R.string.menu_minicalNumberSystem))) {
			return getString(R.string.ChangeHelpMsg);
		}
		else if(from.getTitle().equals(getString(R.string.menu_minicalEquationSolve))){
			return getString(R.string.EquationSolveHelpMsg);
		}
		else if(from.getTitle().equals(getString(R.string.menu_minicalChange))) {
			return getString(R.string.ChangeHelpMsg);
		}
		else return null;
	}
	
	protected void openHelp(Activity from) {
		new AlertDialog.Builder(from)
		.setTitle(R.string.HelpTitle)
		.setMessage(getStringFrom(from))
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
	
	protected void MusicShare(Activity from) {
		new AlertDialog.Builder(from)
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
	
	protected void setBackGroundColor(Activity from) {
		final LinearLayout backGround = (LinearLayout)from.findViewById(R.id.bglayout);
		final SharedPreferences backGroundColor = getSharedPreferences("backgroundcolor", 0);
		String nowColor = backGroundColor.getString("backgroundcolor", "#5baaff");
		backGround.setBackgroundColor(android.graphics.Color.parseColor(nowColor));
	}

	private AlertDialog colorDialog;
	protected void BackGroundColor(Activity from) {
		final SharedPreferences backGroundColor = getSharedPreferences("backgroundcolor", 0);
		View colorChooser = View.inflate(from, R.layout.color_chooser, null);
		final TextView showColor = (TextView)colorChooser.findViewById(R.id.text_show_color);
		final SeekBar seekColor[] = new SeekBar[3];
		seekColor[0] = (SeekBar)colorChooser.findViewById(R.id.seek_red);
		seekColor[1] = (SeekBar)colorChooser.findViewById(R.id.seek_green);
		seekColor[2] = (SeekBar)colorChooser.findViewById(R.id.seek_blue);
		final TextView colorText[] = new TextView[3];
		colorText[0] = (TextView)colorChooser.findViewById(R.id.text_red);
		colorText[1] = (TextView)colorChooser.findViewById(R.id.text_green);
		colorText[2] = (TextView)colorChooser.findViewById(R.id.text_blue);
		final LinearLayout backGround = (LinearLayout)from.findViewById(R.id.bglayout);
		Button resetColor = (Button)colorChooser.findViewById(R.id.default_color);
		
		OnClickListener resetColorListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				seekColor[0].setProgress(91);
				seekColor[1].setProgress(170);
				seekColor[2].setProgress(255);
			}
		};
		
		resetColor.setOnClickListener(resetColorListener);
		
		
		OnSeekBarChangeListener colorChangeListener =  new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				switch(seekBar.getId()) {
				case R.id.seek_red : colorText[0].setText(getString(R.string.red) + ":" + progress);break;
				case R.id.seek_green : colorText[1].setText(getString(R.string.green) + ":" + progress);break;
				case R.id.seek_blue : colorText[2].setText(getString(R.string.blue) + ":" + progress);break;
				}
				changeShowColor();
			}

			private void changeShowColor() {
				// TODO Auto-generated method stub
				String colorT[] = new String[4];
				colorT[0] = Integer.toHexString(seekColor[0].getProgress());
				colorT[1] = Integer.toHexString(seekColor[1].getProgress());
				colorT[2] = Integer.toHexString(seekColor[2].getProgress());
				for (int i = 0; i < 3; i++) {
					if (colorT[i].length() == 1) {
						colorT[i] = "0" + colorT[i];
					}
				}
				String hexColor = ("#" + colorT[0] + colorT[1] + colorT[2]);
				showColor.setBackgroundColor(Color.parseColor(hexColor));
			}
		};
		seekColor[0].setOnSeekBarChangeListener(colorChangeListener);
		seekColor[1].setOnSeekBarChangeListener(colorChangeListener);
		seekColor[2].setOnSeekBarChangeListener(colorChangeListener);
		
		final String nowColor;
		nowColor = backGroundColor.getString("backgroundcolor", "#5baaff");
		int colorInt[] = new int[4];
		colorInt[0] = Integer.valueOf(
				nowColor.substring(1, 3), 16);
		colorInt[1] = Integer.valueOf(
				nowColor.substring(3, 5), 16);
		colorInt[2] = Integer.valueOf(
				nowColor.substring(5, 7), 16);
		for (int i = 0; i < 3; i++) {
			seekColor[i].setProgress(colorInt[i]);
		}
		
		DialogInterface.OnClickListener colorButtonListener = new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					String colorT[] = new String[4];
					colorT[0] = Integer.toHexString(seekColor[0].getProgress());
					colorT[1] = Integer.toHexString(seekColor[1].getProgress());
					colorT[2] = Integer.toHexString(seekColor[2].getProgress());
					for (int i = 0; i < 3; i++) {
						if (colorT[i].length() == 1) {
							colorT[i] = "0" + colorT[i];
						}
					}
					String hexColor = ("#" + colorT[0] + colorT[1] + colorT[2]);
					if (hexColor.contains("#")) {
						backGroundColor
								.edit()
								.putString("backgroundcolor",
										hexColor).commit();
						backGround
								.setBackgroundColor(android.graphics.Color
										.parseColor(hexColor));
					}
					break;
				case DialogInterface.BUTTON_NEGATIVE:
					colorDialog.cancel();
					break;
				case DialogInterface.BUTTON_NEUTRAL:
					backGroundColor
							.edit()
							.putString("backgroundcolor",
									"#5baaff").commit();
					backGround.setBackgroundColor(android.graphics.Color
							.parseColor("#5baaff"));
					colorDialog.cancel();
					break;
				}
			}
		};
		
		
		colorDialog = new AlertDialog.Builder(from)
			.setView(colorChooser)
			.setIcon(android.R.drawable.ic_dialog_info)
			.setTitle(R.string.menu_backgroundcolor)
			.setPositiveButton(android.R.string.ok, colorButtonListener)
			.setNegativeButton(android.R.string.cancel, colorButtonListener)
			//.setNeutralButton(R.string.bg_pic, colorButtonListener)
			.show();
	}
	
	protected void TextSize(final Activity from) {
		String ts;
		final int default_dis_size;
		final int default_btn_size;
		final int default_dis_add;
		final int default_btn_add;
		if(from.getTitle().equals(getString(R.string.app_name))) {
			ts = "mainTextSize";
			default_dis_size = 18;
			default_btn_size = 15;
			default_dis_add = 20;
			default_btn_add = 10;
		}
		else if(from.getTitle().equals(getString(R.string.menu_minicalNumberSystem))) {
			ts = "nsTextSize";
			default_dis_size = 15;
			default_btn_size = 15;
			default_dis_add = 15;
			default_btn_add = 10;
		}
		else if(from.getTitle().equals(getString(R.string.menu_minicalEquationSolve))) {
			ts = "esTextSize";
			default_dis_size = 18;
			default_btn_size = 15;
			default_dis_add = 20;
			default_btn_add = 10;
		}
		else if(from.getTitle().equals(getString(R.string.app_name))) {
			ts = "cTextSize";
			default_dis_size = 18;
			default_btn_size = 15;
			default_dis_add = 20;
			default_btn_add = 10;
		}
		else {
			ts = null;
			default_dis_size = 18;
			default_btn_size = 15;
			default_dis_add = 20;
			default_btn_add = 10;
		}
		final SharedPreferences TextSize = getSharedPreferences(ts, 0);
		View textSize = View.inflate(from, R.layout.text_size, null);
		final TextView ts_btn = (TextView)textSize.findViewById(R.id.button_textsize);
		final TextView ts_dis = (TextView)textSize.findViewById(R.id.display_textsize);
		final SeekBar size_btn = (SeekBar)textSize.findViewById(R.id.size_btn);
		final SeekBar size_dis = (SeekBar)textSize.findViewById(R.id.size_dis);
		Button resetSize = (Button)textSize.findViewById(R.id.default_size);
		
		OnClickListener resetSizeListener = new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				size_btn.setProgress(default_btn_size);
				size_dis.setProgress(default_dis_size);
			}
		};
		
		resetSize.setOnClickListener(resetSizeListener);
		
		OnSeekBarChangeListener sizeChangeListener = new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				switch(seekBar.getId()) {
				case R.id.size_btn : ts_btn.setText(getString(R.string.ts_btn) + ":" + (progress + default_btn_add) + "sp");break;
				case R.id.size_dis : ts_dis.setText(getString(R.string.ts_dis) + ":" + (progress + default_dis_add) + "sp");break;
				}
				changeShowSize();
			}

			private void changeShowSize() {
				// TODO Auto-generated method stub
				ts_btn.setTextSize((float)size_btn.getProgress() + default_btn_add);
				ts_dis.setTextSize((float)size_dis.getProgress() + default_dis_add);
			}
		};

		size_btn.setOnSeekBarChangeListener(sizeChangeListener);
		size_dis.setOnSeekBarChangeListener(sizeChangeListener);
		
		final int now_btn_size;
		final int now_dis_size;
		now_btn_size = TextSize.getInt("btn_size", default_btn_size);
		now_dis_size = TextSize.getInt("dis_size", default_dis_size);
		size_btn.setProgress(now_btn_size);
		size_dis.setProgress(now_dis_size);
		
		DialogInterface.OnClickListener sizeButtonListener = new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch(which) {
				case DialogInterface.BUTTON_POSITIVE : 
					TextSize.edit().putInt("btn_size", size_btn.getProgress()).commit();
					TextSize.edit().putInt("dis_size", size_dis.getProgress()).commit();
					from.finish();
					Intent intent = new Intent();
					intent.setClass(from, from.getClass());
					startActivity(intent);
					break;
				case DialogInterface.BUTTON_NEGATIVE : break;
				}
			}
		};
		
		new AlertDialog.Builder(from)
			.setView(textSize)
			.setIcon(android.R.drawable.ic_dialog_info)
			.setTitle(R.string.menu_textsize)
			.setPositiveButton(android.R.string.ok, sizeButtonListener)
			.setNegativeButton(android.R.string.cancel, sizeButtonListener)
			.show();
	}
	
	protected void showMotto(Activity from) {
		Time nowTime = new Time();
		nowTime.setToNow();
		int today =  nowTime.yearDay;
		ArrayAdapter<CharSequence> sentences = ArrayAdapter.createFromResource(this, R.array.words, today);
		new AlertDialog.Builder(from)
			.setTitle(R.string.menu_motto)
			.setMessage(sentences.getItem(today % (sentences.getCount() - 1)).toString())
			.show();
	}
	
	protected void GoToNumberSystem(Activity from) {
		Intent intent = new Intent();
		intent.setClass(from, MiniCalNumberSystem.class);
		from.startActivity(intent);
		//saveTemp();
		finish();
	}
	
	protected void GoToChange(Activity from) {
		Intent intent = new Intent();
		intent.setClass(from, MiniCalChange.class);
		from.startActivity(intent);
		//saveTemp();
		finish();
	}
	
	protected void GoToQJ(Activity from) {
		Intent intent = new Intent();
		intent.setClass(from, MiniCalEquationSolve.class);
		from.startActivity(intent);
		//saveTemp();
		finish();
	}
	
}
