package com.bon.subanen.dictionary;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;




public class DaywordActivity extends  Activity { 
	
	
	private HashMap<String, Object> mapme = new HashMap<>();
        
	private LinearLayout linear1;
	private LinearLayout linear12;
	private ScrollView vscroll1;
	private LinearLayout adContainerView;
	private LinearLayout linear2;
	private LinearLayout linear13;
	private ImageView imageview1;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private ImageView save;
	private TextView textview5;
	private LinearLayout linear3;
	private TextView textview4;
	private TextView textview1;
	private TextView textview3;
	private TextView textview2;
	private LinearLayout linear6;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private ImageView imageview3;
	private Button button1;
	private ImageView imageview4;
	private Button button2;
	private ImageView imageview2;
	private Button button3;
	

    
	private TextToSpeech tts;
	private Intent back_intent = new Intent();
	private SharedPreferences database;
	private Intent main_activity = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.dayword);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		adContainerView = (LinearLayout) findViewById(R.id.adContainerView);
		if (adContainerView != null) {
			adContainerView.setVisibility(View.GONE);
		}
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		save = (ImageView) findViewById(R.id.save);
		textview5 = (TextView) findViewById(R.id.textview5);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview3 = (TextView) findViewById(R.id.textview3);
		textview2 = (TextView) findViewById(R.id.textview2);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		button1 = (Button) findViewById(R.id.button1);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		button2 = (Button) findViewById(R.id.button2);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		button3 = (Button) findViewById(R.id.button3);
		tts = new TextToSpeech(getApplicationContext(), null);
		database = getSharedPreferences("files", Activity.MODE_PRIVATE);
                
                
                
                
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				tts.stop();
				finish();
			}
		});
		
		linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mapme = new HashMap<>();
				mapme.put("bookmarkwordview", textview1.getText().toString());
				mapme.put("bookmarksynonymview", textview2.getText().toString());
				if ("".contains(database.getString("bookmarkdata", ""))) {
					database.edit().putString("bookmarkdata", new Gson().toJson(mapme)).commit();
				}
				else {
					database.edit().putString("bookmarkdata", database.getString("bookmarkdata", "").concat(",".concat(new Gson().toJson(mapme)))).commit();
				}
				mapme.clear();
				SketchwareUtil.showMessage(getApplicationContext(), "Successfully Saved");
			}
		});
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mapme = new HashMap<>();
				mapme.put("bookmarkwordview", textview1.getText().toString());
				mapme.put("bookmarksynonymview", textview2.getText().toString());
				if ("".contains(database.getString("bookmarkdata", ""))) {
					database.edit().putString("bookmarkdata", new Gson().toJson(mapme)).commit();
				}
				else {
					database.edit().putString("bookmarkdata", database.getString("bookmarkdata", "").concat(",".concat(new Gson().toJson(mapme)))).commit();
				}
				mapme.clear();
				SketchwareUtil.showMessage(getApplicationContext(), "Successfully Saved");
			}
		});
		
		textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mapme = new HashMap<>();
				mapme.put("bookmarkwordview", textview1.getText().toString());
				mapme.put("bookmarksynonymview", textview2.getText().toString());
				if ("".contains(database.getString("bookmarkdata", ""))) {
					database.edit().putString("bookmarkdata", new Gson().toJson(mapme)).commit();
				}
				else {
					database.edit().putString("bookmarkdata", database.getString("bookmarkdata", "").concat(",".concat(new Gson().toJson(mapme)))).commit();
				}
				mapme.clear();
				SketchwareUtil.showMessage(getApplicationContext(), "Successfully Saved");
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				tts.speak(textview1.getText().toString().concat("\nEnglish Synonym:\n".concat(textview2.getText().toString())), TextToSpeech.QUEUE_ADD, null);
			}
		});
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview1.getText().toString().concat("\nEnglish Synonym:\n".concat(textview2.getText().toString()))));
				SketchwareUtil.showMessage(getApplicationContext(), "Copied to clipboard.");
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent intent = new Intent(android.content.Intent.ACTION_SEND);intent.setType("text/plain"); intent.putExtra(android.content.Intent.EXTRA_TEXT,textview1.getText().toString().concat("\nEnglish Synonym:\n".concat(textview2.getText().toString()))); startActivity(Intent.createChooser(intent,"share using"));
				
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				tts.speak(textview1.getText().toString().concat("\nEnglish Synonym:\n".concat(textview2.getText().toString())), TextToSpeech.QUEUE_ADD, null);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				tts.speak(textview1.getText().toString().concat("\nEnglish Synonym:\n".concat(textview2.getText().toString())), TextToSpeech.QUEUE_ADD, null);
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview1.getText().toString().concat("\nEnglish Synonym:\n".concat(textview2.getText().toString()))));
				SketchwareUtil.showMessage(getApplicationContext(), "Copied to clipboard.");
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview1.getText().toString().concat("\nEnglish Synonym:\n".concat(textview2.getText().toString()))));
				SketchwareUtil.showMessage(getApplicationContext(), "Copied to clipboard.");
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent intent = new Intent(android.content.Intent.ACTION_SEND);intent.setType("text/plain"); intent.putExtra(android.content.Intent.EXTRA_TEXT,textview1.getText().toString().concat("\nEnglish Synonym:\n".concat(textview2.getText().toString()))); startActivity(Intent.createChooser(intent,"share using"));
				
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent intent = new Intent(android.content.Intent.ACTION_SEND);intent.setType("text/plain"); intent.putExtra(android.content.Intent.EXTRA_TEXT,textview1.getText().toString().concat("\nEnglish Synonym:\n".concat(textview2.getText().toString()))); startActivity(Intent.createChooser(intent,"share using"));
				
			}
		});
	}
	
	private void initializeLogic() {
		textview1.setText(database.getString("daywordview", ""));
		textview2.setText(database.getString("daysynonymview", ""));
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		if ("0".equals(database.getString("fontsize", ""))) {
			textview4.setTextSize((int)9);
			textview1.setTextSize((int)8);
			textview3.setTextSize((int)9);
			textview2.setTextSize((int)8);
			button1.setTextSize((int)7);
			button2.setTextSize((int)7);
			button3.setTextSize((int)7);
			textview5.setTextSize((int)7);
		}
		if ("1".equals(database.getString("fontsize", ""))) {
			textview4.setTextSize((int)14);
			textview1.setTextSize((int)13);
			textview3.setTextSize((int)14);
			textview2.setTextSize((int)13);
			button1.setTextSize((int)12);
			button2.setTextSize((int)12);
			button3.setTextSize((int)12);
			textview5.setTextSize((int)12);
		}
		if ("2".equals(database.getString("fontsize", ""))) {
			textview4.setTextSize((int)18);
			textview1.setTextSize((int)16);
			textview3.setTextSize((int)18);
			textview2.setTextSize((int)16);
			button1.setTextSize((int)14);
			button2.setTextSize((int)14);
			button3.setTextSize((int)14);
			textview5.setTextSize((int)14);
		}
		if ("3".equals(database.getString("fontsize", ""))) {
			textview4.setTextSize((int)27);
			textview1.setTextSize((int)24);
			textview3.setTextSize((int)27);
			textview2.setTextSize((int)24);
			button1.setTextSize((int)21);
			button2.setTextSize((int)21);
			button3.setTextSize((int)21);
			textview5.setTextSize((int)21);
		}
		if ("4".equals(database.getString("fontsize", ""))) {
			textview4.setTextSize((int)36);
			textview1.setTextSize((int)32);
			textview3.setTextSize((int)36);
			textview2.setTextSize((int)32);
			button1.setTextSize((int)28);
			button2.setTextSize((int)28);
			button3.setTextSize((int)28);
			textview5.setTextSize((int)28);
                        
                        
                     
		}
                
            mapme = new HashMap<>();
            mapme.put("historywordview", textview1.getText());
            mapme.put("historysynonymview", textview2.getText());
            if ("".contains(database.getString("historydata", ""))) {
                database.edit().putString("historydata", new Gson().toJson(mapme)).commit();
            }
            else {
                database.edit().putString("historydata", database.getString("historydata", "").concat(",".concat(new Gson().toJson(mapme)))).commit();
            }
            mapme.clear();
	}
	@Override
	public void onBackPressed() {
		tts.stop();
		finish();
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
