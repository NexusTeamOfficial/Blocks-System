package com.eaglehoster.dragdrop;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrudultora.colorpicker.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;;

public class BlockListEditActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private double n = 0;
	private Gson gson = new Gson();
	private Type type = new TypeToken<Map<String, List<Map<String, String>>>>(){}.getType();
	private Map<String, List<Map<String, String>>> dataMap = new HashMap<>();
	private HashMap<String, Object> map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
	private ListView listview1;
	
	private Intent i = new Intent();
	private SharedPreferences s;
	private TimerTask t;
	private AlertDialog.Builder d;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.block_list_edit);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_fab = findViewById(R.id._fab);
		listview1 = findViewById(R.id.listview1);
		s = getSharedPreferences("s", Activity.MODE_PRIVATE);
		d = new AlertDialog.Builder(this);
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), EditblockdataActivity.class);
				i.putExtra("dataname", getIntent().getStringExtra("dataname"));
				startActivity(i);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		dataMap = gson.fromJson(s.getString("data", ""), type);
		setTitle(getIntent().getStringExtra("dataname"));
		List<Map<String, String>> dataMapList = dataMap.get(getIntent().getStringExtra("dataname"));
		list = new ArrayList<>();
		for (Map<String, String> map : dataMapList) { 
			    HashMap<String, Object> hashMap = new HashMap<>();
			    for (String key : map.keySet()) {
					        String value = map.get(key);
					        hashMap.put(key, value);
					    }
			
			    list.add(hashMap);
		};
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (!list.isEmpty()) {
							listview1.setAdapter(new Listview1Adapter(list));
							((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						}
					}
				});
			}
		};
		_timer.schedule(t, (int)(50));
		if (getIntent().hasExtra("datamap")) {
			map = new Gson().fromJson(getIntent().getStringExtra("datamap"), new TypeToken<HashMap<String, Object>>(){}.getType());
			if (getIntent().hasExtra("pos")) {
				list.set((int)(Double.parseDouble(getIntent().getStringExtra("pos"))), map);
			} else {
				list.add(map);
			}
			dataMap.remove(getIntent().getStringExtra("dataname"));
			List<Map<String, String>> convertedList = new ArrayList<>();
			for (HashMap<String, Object> map : list) {
				    Map<String, String> convertedMap = new HashMap<>();
				    for (Map.Entry<String, Object> entry : map.entrySet()) {
					        convertedMap.put(entry.getKey(), String.valueOf(entry.getValue()));
					    }
				    convertedList.add(convertedMap);
			}
			dataMap.put(getIntent().getStringExtra("dataname"), convertedList);
			s.edit().putString("data", new Gson().toJson(dataMap)).commit();
			i.putExtra("dataname", getIntent().getStringExtra("dataname"));
			i.setClass(getApplicationContext(), BlockListEditActivity.class);
			startActivity(i);
			finish();
		}
	}
	
	
	@Override
	public void onBackPressed() {
		i.setClass(getApplicationContext(), AddblockActivity.class);
		startActivity(i);
		finish();
	}
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.list_blockedit, null);
			}
			
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView name = _view.findViewById(R.id.name);
			final LinearLayout img = _view.findViewById(R.id.img);
			final TextView text = _view.findViewById(R.id.text);
			
			name.setText(list.get((int)_position).get("name").toString());
			text.setText(list.get((int)_position).get("content").toString());
			if (list.get((int)_position).get("type").toString().equals("regular")) {
				img.setBackgroundResource(R.drawable.blockimg);
				String colourValue = "#" + (list.get((int)_position).get("colour").toString());
				int color = Color.parseColor(colourValue);
				                    img.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
			} else {
				if (list.get((int)_position).get("type").toString().equals("if") || list.get((int)_position).get("type").toString().equals("if.e")) {
					img.setBackgroundResource(R.drawable.if_top);
					String colourValue = "#" + (list.get((int)_position).get("colour").toString());
					int color = Color.parseColor(colourValue);
					                    img.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
				} else {
					if (list.get((int)_position).get("type").toString().equals("heading")) {
						img.setBackgroundResource(0);
						img.setBackgroundColor(0xFF000000);
						text.setTextColor(0xFFFFFFFF);
					} else {
						((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", "unknown type"));
					}
				}
			}
			linear2.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					i.putExtra("dataname", getIntent().getStringExtra("dataname"));
					i.putExtra("datablock", new Gson().toJson(_data.get((int)(_position))));
					i.putExtra("pos", String.valueOf((long)(_position)));
					i.setClass(getApplicationContext(), EditblockdataActivity.class);
					startActivity(i);
				}
			});
			linear2.setOnLongClickListener(new View.OnLongClickListener(){
				@Override
				public boolean onLongClick(View _view){
					d.setTitle("⚠️ Warning ⚠️");
					d.setMessage("Are You Sure To Delete ".concat(list.get((int)_position).get("name").toString()));
					d.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							list.remove((int)(_position));
							List<Map<String, String>> convertedList = new ArrayList<>();
							for (HashMap<String, Object> map : list) {
								    Map<String, String> convertedMap = new HashMap<>();
								    for (Map.Entry<String, Object> entry : map.entrySet()) {
									        convertedMap.put(entry.getKey(), String.valueOf(entry.getValue()));
									    }
								    convertedList.add(convertedMap);
							}
							dataMap.put(getIntent().getStringExtra("dataname"), convertedList);
							s.edit().putString("data", new Gson().toJson(dataMap)).commit();
							i.putExtra("dataname", getIntent().getStringExtra("dataname"));
							i.setClass(getApplicationContext(), BlockListEditActivity.class);
							startActivity(i);
							finish();
						}
					});
					d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					return false;
				}
			});
			
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
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}