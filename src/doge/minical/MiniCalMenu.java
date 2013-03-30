package doge.minical;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class MiniCalMenu extends Activity {
	
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
		if(from.getTitle().equals("简洁计算器")) {
			return getString(R.string.MainHelpMsg);
		}
		else if(from.getTitle().equals("进制转换")) {
			return getString(R.string.ChangeHelpMsg);
		}
		else {
			return getString(R.string.ChangeHelpMsg);
		}
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
	
}
