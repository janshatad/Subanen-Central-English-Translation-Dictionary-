package com.bon.subanen.dictionary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class HistoryMainActivity extends  Activity { 
	
	
	private HashMap<String, Object> map = new HashMap<>();
	private double n = 0;
	private String empty = "";
	private double smooth_scroll = 0;
	
	private ArrayList<HashMap<String, Object>> history_listmap = new ArrayList<>();
	private ArrayList<String> liststring = new ArrayList<>();
	
	private LinearLayout linear1;
	private TextView recent_label;
	private LinearLayout clear_linear;
	private ListView listview2;
	private LinearLayout history_empty_linear;
	private LinearLayout ad_view_container;
	private LinearLayout linear2;
	private ImageView imageview1;
	private TextView clear_all;
	private TextView history_label;
	
        
	private SharedPreferences database;
	private Intent history_view_intent = new Intent();
	private AlertDialog.Builder clear_history;
	private Intent refresh_history_intent = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.history_main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		recent_label = (TextView) findViewById(R.id.recent_label);
		clear_linear = (LinearLayout) findViewById(R.id.clear_linear);
		listview2 = (ListView) findViewById(R.id.listview2);
		history_empty_linear = (LinearLayout) findViewById(R.id.history_empty_linear);
		ad_view_container = (LinearLayout) findViewById(R.id.ad_view_container);
		if (ad_view_container != null) {
			ad_view_container.setVisibility(View.GONE);
		}
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		clear_all = (TextView) findViewById(R.id.clear_all);
		history_label = (TextView) findViewById(R.id.history_label);
		database = getSharedPreferences("files", Activity.MODE_PRIVATE);
		clear_history = new AlertDialog.Builder(this);
		
		listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				database.edit().putString("history_wordview", history_listmap.get((int)_position).get("historywordview").toString()).commit();
				database.edit().putString("history_synonymview", history_listmap.get((int)_position).get("historysynonymview").toString()).commit();
				history_view_intent.setClass(getApplicationContext(), HistoryActivity.class);
				startActivity(history_view_intent);
			}
		});
		
		linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				clear_history.setIcon(R.drawable.ic_delete_grey);
				clear_history.setMessage("Do you want to delete all history?");
				clear_history.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						database.edit().putString("historydata", "").commit();
						refresh_history_intent.setClass(getApplicationContext(), HistoryMainActivity.class);
						startActivity(refresh_history_intent);
						finish();
					}
				});
				clear_history.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				clear_history.create().show();
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				clear_history.setIcon(R.drawable.ic_delete_grey);
				clear_history.setMessage("Do you want to delete all history?");
				clear_history.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						database.edit().putString("historydata", "").commit();
						refresh_history_intent.setClass(getApplicationContext(), HistoryMainActivity.class);
						startActivity(refresh_history_intent);
						finish();
					}
				});
				clear_history.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				clear_history.create().show();
			}
		});
		
		clear_all.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				clear_history.setIcon(R.drawable.ic_delete_grey);
				clear_history.setMessage("Do you want to delete all history?");
				clear_history.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						database.edit().putString("historydata", "").commit();
						refresh_history_intent.setClass(getApplicationContext(), HistoryMainActivity.class);
						startActivity(refresh_history_intent);
						finish();
					}
				});
				clear_history.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				clear_history.create().show();
			}
		});
	}
	
	private void initializeLogic() {
		empty = "";
		if (!empty.equals(database.getString("historydata", ""))) {
			history_empty_linear.setVisibility(View.GONE);
			listview2.setVisibility(View.VISIBLE);
			clear_linear.setVisibility(View.VISIBLE);
			_refresh_list();
		}
		else {
			listview2.setVisibility(View.GONE);
			clear_linear.setVisibility(View.GONE);
			history_empty_linear.setVisibility(View.VISIBLE);
		}
		if ("0".equals(database.getString("fontsize", ""))) {
			recent_label.setTextSize((int)11);
			history_label.setTextSize((int)13);
			clear_all.setTextSize((int)7);
		}
		if ("1".equals(database.getString("fontsize", ""))) {
			recent_label.setTextSize((int)15);
			history_label.setTextSize((int)17);
			clear_all.setTextSize((int)11);
		}
		if ("2".equals(database.getString("fontsize", ""))) {
			recent_label.setTextSize((int)18);
			history_label.setTextSize((int)20);
			clear_all.setTextSize((int)16);
		}
		if ("3".equals(database.getString("fontsize", ""))) {
			recent_label.setTextSize((int)27);
			history_label.setTextSize((int)30);
			clear_all.setTextSize((int)24);
		}
		if ("4".equals(database.getString("fontsize", ""))) {
			recent_label.setTextSize((int)36);
			history_label.setTextSize((int)40);
			clear_all.setTextSize((int)32);
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
	
	public void _refresh_list () {
		history_listmap.clear();
		history_listmap = new Gson().fromJson("[".concat(database.getString("historydata", "").concat("]")), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		listview2.setAdapter(new Listview2Adapter(history_listmap));
		listview2.setStackFromBottom(true);
	}
	
	
	public void _refresh_listview () {
		listview2.setAdapter(new Listview2Adapter(history_listmap));
		((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
	}
	
	
	public class Listview2Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.history_main_child, null);
			}
			
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
			
			textview1.setText(history_listmap.get((int)_position).get("historywordview").toString());
			textview2.setText(history_listmap.get((int)_position).get("historysynonymview").toString());
			if ("0".equals(database.getString("fontsize", ""))) {
				textview1.setTextSize((int)8);
				textview2.setTextSize((int)8);
			}
			if ("1".equals(database.getString("fontsize", ""))) {
				textview1.setTextSize((int)13);
				textview2.setTextSize((int)13);
			}
			if ("2".equals(database.getString("fontsize", ""))) {
				textview1.setTextSize((int)16);
				textview2.setTextSize((int)16);
			}
			if ("3".equals(database.getString("fontsize", ""))) {
				textview1.setTextSize((int)24);
				textview2.setTextSize((int)24);
			}
			if ("4".equals(database.getString("fontsize", ""))) {
				textview1.setTextSize((int)32);
				textview2.setTextSize((int)32);
			}
			
			return _view;
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
