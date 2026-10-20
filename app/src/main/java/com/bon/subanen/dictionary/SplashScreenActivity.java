package com.bon.subanen.dictionary;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.net.Uri;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class SplashScreenActivity extends  Activity { 
	
	private Timer _timer = new Timer();
	
	private ArrayList<String> liststring = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ImageView splash_image;
	private TextView tittle_text;
	private TextView sub_tittle_text;
	private TextView textview1;
	
	private TimerTask timer_splash;
	private Intent mainactivity_intent = new Intent();
	private SharedPreferences database;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.splash_screen);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		splash_image = (ImageView) findViewById(R.id.splash_image);
		tittle_text = (TextView) findViewById(R.id.tittle_text);
		sub_tittle_text = (TextView) findViewById(R.id.sub_tittle_text);
		textview1 = (TextView) findViewById(R.id.textview1);
		database = getSharedPreferences("files", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		liststring.add("You can set the font size in settings");
		liststring.add("You can Optimize the Background Notification service by allowing to ignore battery in settings.");
		liststring.add("To accurately search add a semicolon after a keyword.");
		liststring.add("You can Watch Video Ads in the settings to support the developer by clicking the Media Icon.");
		liststring.add("This app is free.");
		liststring.add("You can long press the about button to see overall wordlist data length.");
		liststring.add("Please like our Facebook Page just click the Facebook Icon at the Center Bottom.");
		liststring.add("This app can be use in Android 4 Above only.");
		liststring.add("You can support the developer by navigating to settings and click the coffee icon, any amount is appreciated ❤️");
		liststring.add("You can set a Notification Frequency in settings.");
		liststring.add("Have a nice day");
		liststring.add("Thank you for Installing my app");
		liststring.add("You can see your recent opened word definition In settings.");
		liststring.add("You can clear the history of you recently opened words.");
		liststring.add("You can clear the word you saved by going to settings.");
		liststring.add("You can save a word definition by clicking the star icon.");
		liststring.add("You can copy a word by clicking the Copy Icon.");
		liststring.add("You can directly share a word to a person or apps.");
		liststring.add("You can Hear a word by clicking the Speaker icon.");
		liststring.add("The Speaker Icon won't work if you don't have a Text to Speech app by Google installed in your phone.");
		liststring.add("This app is supported in landscape or portrait mode.");
		liststring.add("Subanen is originated in Zamboanga Peninsula.");
		liststring.add(" To experience bugs please report immediately by chatting in facebook page.");
		liststring.add("You can suggest, recommend a features by chatting the Facebook Page of this app.");
		liststring.add("The data of this app is from SIL Philippines.");
		liststring.add("Icons size is not part of font resizing.");
		liststring.add("Some low devices will slow down while searching.");
		liststring.add("This app uses Internet Permission to serve ads.");
		liststring.add("The text of this app cannot be highlighted.");
		liststring.add("The data of this app is still in development according to SIL Philippines.");
		liststring.add("This app can work offline.");
		liststring.add("This is only a Translation From Subanen to English.");
		liststring.add("There is a banner at the top of the settings tab to broadcast a message if there is an update or message.");
		liststring.add("You can directly share the APK of this app to user or apps.");
		liststring.add("Font size settings can be affected all fonts inside this app except the banner text in settings tab and pop-up dialog.");
		//String app_name = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
		timer_splash = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mainactivity_intent.setClass(getApplicationContext(), MainActivity.class);
						startActivity(mainactivity_intent);
						finish();
					}
				});
			}
		};
		_timer.schedule(timer_splash, (int)(2000));
		splash_image.setImageResource(R.drawable.app_icon);
		tittle_text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 1);
		tittle_text.setText("Subanen Central Dictionary English Translation");
		sub_tittle_text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/century_gothic.ttf"), 2);
		sub_tittle_text.setText("V1.0 Stable");
		if (!liststring.isEmpty()) {
			textview1.setText(liststring.get(SketchwareUtil.getRandom(0, liststring.size() - 1)));
		}
		if ("0".equals(database.getString("fontsize", ""))) {
			tittle_text.setTextSize((int)11);
			sub_tittle_text.setTextSize((int)9);
			textview1.setTextSize((int)10);
		}
		if ("1".equals(database.getString("fontsize", ""))) {
			tittle_text.setTextSize((int)13);
			sub_tittle_text.setTextSize((int)10);
			textview1.setTextSize((int)12);
		}
		if ("2".equals(database.getString("fontsize", ""))) {
			tittle_text.setTextSize((int)20);
			sub_tittle_text.setTextSize((int)14);
			textview1.setTextSize((int)18);
		}
		if ("3".equals(database.getString("fontsize", ""))) {
			tittle_text.setTextSize((int)30);
			sub_tittle_text.setTextSize((int)21);
			textview1.setTextSize((int)27);
		}
		if ("4".equals(database.getString("fontsize", ""))) {
			tittle_text.setTextSize((int)40);
			sub_tittle_text.setTextSize((int)28);
			textview1.setTextSize((int)36);
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
