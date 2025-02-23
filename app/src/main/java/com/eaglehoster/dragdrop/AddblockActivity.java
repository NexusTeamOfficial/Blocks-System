package com.eaglehoster.dragdrop;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.mrudultora.colorpicker.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;;



public class AddblockActivity extends AppCompatActivity {
	
	public final int REQ_CD_FILEP = 101;
	
	private FloatingActionButton _fab;
	private String datareceived = "";
	private Map<String, List<Map<String, String>>> dataMap = new HashMap<>();
	private Type type = new TypeToken<Map<String, List<Map<String, String>>>>(){}.getType();
	private Gson gson = new Gson();
	
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listm = new ArrayList<>();
	
	private LinearLayout linear2;
	private ListView listview1;
	private LinearLayout linear1;
	private Button Imports;
	private Button exports;
	private Button button1;
	
	private SharedPreferences s;
	private Intent i = new Intent();
	private AlertDialog.Builder d;
	private Intent filep = new Intent(Intent.ACTION_GET_CONTENT);
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.addblock);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		linear2 = findViewById(R.id.linear2);
		listview1 = findViewById(R.id.listview1);
		linear1 = findViewById(R.id.linear1);
		Imports = findViewById(R.id.Imports);
		exports = findViewById(R.id.exports);
		button1 = findViewById(R.id.button1);
		s = getSharedPreferences("s", Activity.MODE_PRIVATE);
		d = new AlertDialog.Builder(this);
		filep.setType("application/json");
		filep.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		Imports.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (Build.VERSION.SDK_INT >= 23) {
								if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED) {
										requestPermissions(new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1000);
								}
								else {
						if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/custom_block_System/block.json"))) {
							s.edit().putString("data", FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/custom_block_System/block.json"))).commit();
							SketchwareUtil.showMessage(getApplicationContext(), "Import done ✅");
						} else {
							SketchwareUtil.showMessage(getApplicationContext(), "File not found");
						}
								}
						}
						else {
					if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/custom_block_System/block.json"))) {
						s.edit().putString("data", FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/custom_block_System/block.json"))).commit();
						SketchwareUtil.showMessage(getApplicationContext(), "Import done ✅");
					} else {
						SketchwareUtil.showMessage(getApplicationContext(), "File not found");
					}
						}
			}
		});
		
		exports.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (Build.VERSION.SDK_INT >= 23) {
								if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED) {
										requestPermissions(new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1000);
								}
								else {
						FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/custom_block_System/block.json"), s.getString("data", ""));
						SketchwareUtil.showMessage(getApplicationContext(), "file saved");
								}
						}
						else {
					FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/custom_block_System/block.json"), s.getString("data", ""));
					SketchwareUtil.showMessage(getApplicationContext(), "file saved");
						}
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), MainActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_edit_dialoge();
			}
		});
	}
	
	private void initializeLogic() {
		if (!s.contains("firstlaunch")) {
			s.edit().putString("firstlaunch", "false").commit();
			s.edit().putString("data", "{\n  \"regular\": [\n    {\"name\": \"Heading\", \"content\": \"Blocks\", \"colour\": \"FB8C00\", \"type\": \"heading\", \"code\": \"Heading: %1$s\"},\n    {\"name\": \"set boolean\", \"content\": \"set boolean to %v.b\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"set boolean to %1$s\"},\n    {\"name\": \"set number\", \"content\": \"set number to %e.n\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"set number to %1$s\"},\n    {\"name\": \"num +1\", \"content\": \"Number %e.n increase 1\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"increase number %1$s by 1\"},\n    {\"name\": \"num -1\", \"content\": \"Number %e.n decrease 1\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"decrease number %1$s by 1\"},\n    {\"name\": \"set string\", \"content\": \"set string to %e.s\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"set string to %1$s\"},\n    {\"name\": \"append string\", \"content\": \"append %e.s to %e.s\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"append %1$s to %2$s\"},\n    {\"name\": \"get length\", \"content\": \"length of %e.s\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"get length of %1$s\"},\n    {\"name\": \"contains\", \"content\": \"%e.s contains %e.s\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"%1$s contains %2$s\"},\n    {\"name\": \"set list\", \"content\": \"set list %v.l\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"set list to %1$s\"},\n    {\"name\": \"add to list\", \"content\": \"add %e.v to %v.l\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"add %1$s to list %2$s\"},\n    {\"name\": \"remove from list\", \"content\": \"remove %e.v from %v.l\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"remove %1$s from list %2$s\"},\n    {\"name\": \"clear list\", \"content\": \"clear %v.l\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"clear list %1$s\"},\n    {\"name\": \"get from list\", \"content\": \"get item %e.n from %v.l\", \"colour\": \"FB8C00\", \"type\": \"regular\", \"code\": \"get item %1$s from list %2$s\"}\n  ],\n  \"if\": [\n    {\"name\": \"Heading\", \"content\": \"Control\", \"colour\": \"FB8C00\", \"type\": \"heading\", \"code\": \"Control Block: %1$s\"},\n    {\"name\": \"repeat\", \"content\": \"repeat %e.n\", \"colour\": \"FB8C00\", \"type\": \"if\", \"code\": \"repeat %1$s times\"},\n    {\"name\": \"repeat custom+\", \"content\": \"repeat %e.n %e.s ++\", \"colour\": \"FB8C00\", \"type\": \"if\", \"code\": \"repeat %1$s %2$s incremented\"},\n    {\"name\": \"repeat custom-\", \"content\": \"repeat %e.s %e.n --\", \"colour\": \"FB8C00\", \"type\": \"if\", \"code\": \"repeat %1$s %2$s decremented\"},\n    {\"name\": \"forever\", \"content\": \"forever\", \"colour\": \"FB8C00\", \"type\": \"if\", \"code\": \"forever\"},\n    {\"name\": \"if (true)\", \"content\": \"if %v.b then\", \"colour\": \"FB8C00\", \"type\": \"if\", \"code\": \"if %1$s then\"},\n    {\"name\": \"if (true) else\", \"content\": \"if %v.b then\", \"content2\": \"else\", \"colour\": \"FB8C00\", \"type\": \"if.e\", \"code\": \"if %1$s then else\"},\n    {\"name\": \"while\", \"content\": \"while %v.b\", \"colour\": \"FB8C00\", \"type\": \"if\", \"code\": \"while %1$s\"},\n    {\"name\": \"do while\", \"content\": \"do %e.s while %v.b\", \"colour\": \"FB8C00\", \"type\": \"if\", \"code\": \"do %1$s while %2$s\"},\n    {\"name\": \"break\", \"content\": \"break\", \"colour\": \"FB8C00\", \"type\": \"if\", \"code\": \"break\"},\n    {\"name\": \"continue\", \"content\": \"continue\", \"colour\": \"FB8C00\", \"type\": \"if\", \"code\": \"continue\"}\n  ]\n}\n").commit();
		}
		if (s.contains("data")) {
			dataMap = gson.fromJson(s.getString("data", ""), type);
			
			for (String key : dataMap.keySet()) {
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("name", key);
					listm.add(_item);
			}
		}
		if (Build.VERSION.SDK_INT >= 23) {
						if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED) {
								requestPermissions(new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1000);
						}
						else {
				 
						}
				}
				else {
			 
				}
		listview1.setAdapter(new Listview1Adapter(listm));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
	}
	
	
	@Override
	public void onBackPressed() {
		d.setTitle("⚠️ Warning ⚠️");
		d.setMessage("Click PROCEED to exit".concat(""));
		d.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				finish();
			}
		});
		d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
	}
	public void _edit_dialoge() {
		AlertDialog dialog1;
		dialog1 = new AlertDialog.Builder(AddblockActivity.this).create();
		LayoutInflater dialog1LI = getLayoutInflater();
		View dialog1CV = (View) dialog1LI.inflate(R.layout.edit_dialogue, null);
		dialog1.setView(dialog1CV);
		final TextView heading = (TextView)
		dialog1CV.findViewById(R.id.heading);
		final EditText edit = (EditText)
		dialog1CV.findViewById(R.id.edit);
		final Button cancel = (Button)
		dialog1CV.findViewById(R.id.cancel);
		final Button save = (Button)
		dialog1CV.findViewById(R.id.save);
		dialog1.setCancelable(false);
		edit.setHint("Name");
		heading.setText("Create a new palette");
		dialog1.show();
		save.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				_adddata(edit.getText().toString());
				dialog1.dismiss();
			}
		});
		cancel.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				_adddata("%xx.null");
				dialog1.dismiss();
			}
		});
	}
	
	
	public void _adddata(final String _data) {
		if (!(_data.equals("%xx.null") || _data.equals("empty"))) {
			dataMap.put(_data, new ArrayList<>());
			Gson gsons = new Gson();
			String jsonString = gsons.toJson(dataMap);
			s.edit().putString("data", jsonString).commit();
			i.setClass(getApplicationContext(), AddblockActivity.class);
			startActivity(i);
			finish();
		}
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
				_view = _inflater.inflate(R.layout.normal_customlist, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView name = _view.findViewById(R.id.name);
			
			name.setText(_data.get((int)_position).get("name").toString());
			linear1.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					i.setClass(getApplicationContext(), BlockListEditActivity.class);
					i.putExtra("dataname", _data.get((int)_position).get("name").toString());
					startActivity(i);
				}
			});
			name.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					i.setClass(getApplicationContext(), BlockListEditActivity.class);
					i.putExtra("dataname", _data.get((int)_position).get("name").toString());
					startActivity(i);
				}
			});
			linear1.setOnLongClickListener(new View.OnLongClickListener(){
				@Override
				public boolean onLongClick(View _view){
					d.setTitle("⚠️ Warning ⚠️");
					d.setMessage("Are You Sure To Delete ".concat(_data.get((int)_position).get("name").toString()));
					d.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							dataMap.remove(list.get((int)(_position)));
							s.edit().putString("data", new Gson().toJson(dataMap)).commit();
							i.setClass(getApplicationContext(), AddblockActivity.class);
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
			name.setOnLongClickListener(new View.OnLongClickListener(){
				@Override
				public boolean onLongClick(View _view){
					d.setTitle("⚠️ Warning ⚠️");
					d.setMessage("Are You Sure To Delete ".concat(_data.get((int)_position).get("name").toString()));
					d.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							dataMap.remove(list.get((int)(_position)));
							s.edit().putString("data", new Gson().toJson(dataMap)).commit();
							i.setClass(getApplicationContext(), AddblockActivity.class);
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
