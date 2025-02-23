package com.eaglehoster.dragdrop;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
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

public class EditblockdataActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private double temp = 0;
	private HashMap<String, String> map = new HashMap<>();
	private String PickedColor = "";
	private String tempr = "";
	private HashMap<String, Object> dataget = new HashMap<>();
	
	private ArrayList<String> type = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> properties = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linears1;
	private LinearLayout linears2;
	private LinearLayout linear8;
	private LinearLayout linear10;
	private LinearLayout linear9;
	private TextView textview1;
	private EditText name;
	private LinearLayout linear3;
	private TextView typename;
	private TextView textview3;
	private TextView designtxt;
	private LinearLayout colorcodeview;
	private HorizontalScrollView hscroll3;
	private EditText part2content;
	private TextView parameterstxt;
	private HorizontalScrollView parameterscroll;
	private EditText colouredit;
	private TextView textview6;
	private LinearLayout linear7;
	private LinearLayout block;
	private EditText content;
	private LinearLayout contentlist;
	private EditText sourceCode;
	private Button cancel;
	private Button submit;
	
	private Intent i = new Intent();
	private AlertDialog.Builder d;
	private TimerTask t;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.editblockdata);
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
		linear1 = findViewById(R.id.linear1);
		linears1 = findViewById(R.id.linears1);
		linears2 = findViewById(R.id.linears2);
		linear8 = findViewById(R.id.linear8);
		linear10 = findViewById(R.id.linear10);
		linear9 = findViewById(R.id.linear9);
		textview1 = findViewById(R.id.textview1);
		name = findViewById(R.id.name);
		linear3 = findViewById(R.id.linear3);
		typename = findViewById(R.id.typename);
		textview3 = findViewById(R.id.textview3);
		designtxt = findViewById(R.id.designtxt);
		colorcodeview = findViewById(R.id.colorcodeview);
		hscroll3 = findViewById(R.id.hscroll3);
		part2content = findViewById(R.id.part2content);
		parameterstxt = findViewById(R.id.parameterstxt);
		parameterscroll = findViewById(R.id.parameterscroll);
		colouredit = findViewById(R.id.colouredit);
		textview6 = findViewById(R.id.textview6);
		linear7 = findViewById(R.id.linear7);
		block = findViewById(R.id.block);
		content = findViewById(R.id.content);
		contentlist = findViewById(R.id.contentlist);
		sourceCode = findViewById(R.id.sourceCode);
		cancel = findViewById(R.id.cancel);
		submit = findViewById(R.id.submit);
		d = new AlertDialog.Builder(this);
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_edit_dialoge();
			}
		});
		
		colouredit.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				try{
					if (!_charSeq.contains("#")) {
						if (_charSeq.length() == 6) {
							PickedColor = _charSeq;
							try {
								   String colours = "#" + PickedColor;
								int colour = Color.parseColor(colours);
								block.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
								block.invalidate();
							} catch (Exception eror) {
									((EditText)colouredit).setError("Invalid colour");
							}
						} else {
							((EditText)colouredit).setError("Invalid length");
						}
					} else {
						((EditText)colouredit).setError("Invalid character");
					}
				}catch(Exception e){
					 
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		textview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ColorPickerPopUp colorpickerpopup = new ColorPickerPopUp(EditblockdataActivity.this);
				colorpickerpopup.setShowAlpha(false)
				.setDefaultColor(Color.RED)
				.setDialogTitle("Pick Color")
				.setOnPickColorListener(new ColorPickerPopUp.OnPickColorListener() { 
					@Override 
					public void onColorPicked(int color) {
						int a = Color.alpha(color);
						int r = Color.red(color);
						int g = Color.green(color);
						int b = Color.blue(color);
						
						PickedColor = String.format(Locale.getDefault(), "%02X%02X%02X", r, g, b);
						colouredit.setText(PickedColor);
						try{
							String colours = "#" + PickedColor;
							int colour = Color.parseColor(colours);
							block.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
							block.invalidate();
						}catch(Exception e){
							((EditText)colouredit).setError("Invalid colour");
						}
					} 
					@Override 
					public void onCancel() {
						 }
					 })
				.show();
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), BlockListEditActivity.class);
				i.putExtra("dataname", getIntent().getStringExtra("dataname"));
				startActivity(i);
				finish();
			}
		});
		
		submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View _view) {
        boolean check1 = false, check2 = false, check3 = false, check4 = false;

        if (!(name.getText().toString().trim().isEmpty())) {
            map.put("name", name.getText().toString().trim());
            check1 = true;
        } else {
            name.setError("Enter name correctly");
        }

        if (!(content.getText().toString().trim().isEmpty())) {
            map.put("content", content.getText().toString().trim());
            check2 = true;
        } else {
            content.setError("Enter content correctly");
        }

        if (!(typename.getText().toString().trim().isEmpty())) {
            map.put("type", typename.getText().toString().trim());
            check3 = true;
        } else {
            typename.setError("Enter type correctly");
        }

        if (!(sourceCode.getText().toString().trim().isEmpty())) {
            map.put("code", sourceCode.getText().toString().trim());  // 🔥 User khud `%1$s`, `%2$s` set karega
        } else {
            sourceCode.setError("Enter code correctly");
        }

        if (!(colouredit.getText().toString().trim().isEmpty())) {
            map.put("colour", colouredit.getText().toString().trim());
            check4 = true;
        } else {
            colouredit.setError("Enter color correctly");
        }

        if (check1 && check2 && check3 && check4) {
            Intent i = new Intent(getApplicationContext(), BlockListEditActivity.class);
            i.putExtra("datamap", new Gson().toJson(map));
            i.putExtra("dataname", getIntent().getStringExtra("dataname"));
            startActivity(i);
            finish();
        }
    }
});
	}
	
	private void initializeLogic() {
		if (getIntent().hasExtra("datablock")) {
			dataget = new Gson().fromJson(getIntent().getStringExtra("datablock"), new TypeToken<HashMap<String, Object>>(){}.getType());
			PickedColor = dataget.get("colour").toString();
			name.setText(dataget.get("name").toString());
			typename.setText(dataget.get("type").toString());
			colouredit.setText(dataget.get("colour").toString());
			PickedColor = dataget.get("colour").toString();
			content.setText(dataget.get("content").toString());
			sourceCode.setText(dataget.get("code").toString());
			try{
				part2content.setText(dataget.get("content2").toString());
			}catch(Exception e){
				 
			}
			t = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (typename.getText().toString().equals("heading")) {
								colorcodeview.setVisibility(View.GONE);
								designtxt.setVisibility(View.GONE);
								parameterstxt.setVisibility(View.GONE);
								parameterscroll.setVisibility(View.GONE);
								part2content.setVisibility(View.GONE);
								block.setBackgroundResource(0);
							} else {
								if (typename.getText().toString().equals("if.e")) {
									colorcodeview.setVisibility(View.VISIBLE);
									designtxt.setVisibility(View.VISIBLE);
									parameterstxt.setVisibility(View.VISIBLE);
									parameterscroll.setVisibility(View.VISIBLE);
									part2content.setVisibility(View.VISIBLE);
									block.setBackgroundResource(R.drawable.if_top);
								} else {
									if (typename.getText().toString().equals("if")) {
										colorcodeview.setVisibility(View.VISIBLE);
										designtxt.setVisibility(View.VISIBLE);
										parameterstxt.setVisibility(View.VISIBLE);
										parameterscroll.setVisibility(View.VISIBLE);
										part2content.setVisibility(View.GONE);
										block.setBackgroundResource(R.drawable.if_top);
									} else {
										if (typename.getText().toString().equals("regular")) {
											colorcodeview.setVisibility(View.VISIBLE);
											designtxt.setVisibility(View.VISIBLE);
											parameterstxt.setVisibility(View.VISIBLE);
											parameterscroll.setVisibility(View.VISIBLE);
											part2content.setVisibility(View.GONE);
											block.setBackgroundResource(R.drawable.blockimg);
										} else {
											
										}
									}
								}
							}
						}
					});
				}
			};
			_timer.schedule(t, (int)(10));
		} else {
			PickedColor = "FFAE00";
			colouredit.setText("FFAE00");
		}
		type = new Gson().fromJson("[\"heading\",\"regular\",\"if\",\"if.e\"]", new TypeToken<ArrayList<String>>(){}.getType());
		properties = new Gson().fromJson("[{\"name\":\"editSstring\",\"value\":\"%e.s\"},\n {\"name\":\"editNumber\",\"value\":\"%e.n\"},\n {\"name\":\"Boolen\",\"value\":\"%v.b\"}\n]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		for(int _repeat14 = 0; _repeat14 < (int)(properties.size()); _repeat14++) {
			((ViewGroup)contentlist).addView(new TextView(EditblockdataActivity.this),(int)_repeat14);
			TextView txt = ((TextView) contentlist.getChildAt((int) _repeat14));
			txt.setText(properties.get((int)_repeat14).get("name").toString());
			txt.setPadding((int) 16,(int) 8,(int) 16,(int) 8);
			txt.setBackgroundColor(Color.TRANSPARENT);
			txt.setTextColor(0xFF009688);
			if ((_repeat14 % 2) == 0) {
				txt.setTextColor(0xFF4CAF50);
			}
			String tempr = properties.get((int)_repeat14).get("value").toString();
			txt.setOnClickListener(new View.OnClickListener(){
				     private String tempra = tempr;
				     @Override
						public void onClick(View _view){
								content.setText(content.getText().toString().concat(" ").concat(tempra));
						}
				});
		}
		try{
			String colours = "#" + PickedColor;
			int colour = Color.parseColor(colours);
			block.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
			block.invalidate();
		}catch(Exception e){
			 
		}
		linears1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFFEEEEEE));
		linears2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFFEEEEEE));
		cancel.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF0288D1));
		submit.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFF0288D1));
		part2content.setVisibility(View.GONE);
	}
	
	@Override
	public void onBackPressed() {
		d.setTitle("⚠️ Warning ⚠️");
		d.setMessage("Your data will be lost.\nAre you sure to proceed?");
		d.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				i.setClass(getApplicationContext(), BlockListEditActivity.class);
				i.putExtra("dataname", getIntent().getStringExtra("dataname"));
				finish();
			}
		});
		d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		d.create().show();
	}
	public void _edit_dialoge() {
		AlertDialog dialog1;
		dialog1 = new AlertDialog.Builder(EditblockdataActivity.this).create();
		LayoutInflater dialog1LI = getLayoutInflater();
		View dialog1CV = (View) dialog1LI.inflate(R.layout.choosedialog, null);
		dialog1.setView(dialog1CV);
		final TextView heading = (TextView)
		dialog1CV.findViewById(R.id.heading);
		final LinearLayout base = (LinearLayout)
		dialog1CV.findViewById(R.id.base);
		final Button cancel = (Button)
		dialog1CV.findViewById(R.id.cancel);
		final Button save = (Button)
		dialog1CV.findViewById(R.id.save);
		dialog1.setCancelable(false);
		for(int _repeat41 = 0; _repeat41 < (int)(type.size()); _repeat41++) {
			CheckBox checkBox = new CheckBox(EditblockdataActivity.this);
			checkBox.setText(type.get(_repeat41));
			base.addView(checkBox);
			if (typename.getText() != null && typename.getText().toString().equals(type.get((int)(_repeat41)))) {
				checkBox.setChecked(true);
			}
			checkBox.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View _view){
					for(int _repeat67 = 0; _repeat67 < (int)(base.getChildCount()); _repeat67++) {
						CheckBox checkBoxs = ((CheckBox) base.getChildAt((int) _repeat67));
						checkBoxs.setChecked(false);
					}
					((CheckBox) _view).setChecked(true);
				}
			});
		}
		heading.setText("Choose type");
		dialog1.show();
		save.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				for(int _repeat84 = 0; _repeat84 < (int)(base.getChildCount()); _repeat84++) {
					CheckBox checkBoxs = ((CheckBox) base.getChildAt((int) _repeat84));
					if (checkBoxs.isChecked()) {
						typename.setText(checkBoxs.getText().toString());
						if (checkBoxs.getText().toString().equals("heading")) {
							colorcodeview.setVisibility(View.GONE);
							designtxt.setVisibility(View.GONE);
							parameterstxt.setVisibility(View.GONE);
							parameterscroll.setVisibility(View.GONE);
							part2content.setVisibility(View.GONE);
							block.setBackgroundResource(0);
						} else {
							if (checkBoxs.getText().toString().equals("if.e")) {
								colorcodeview.setVisibility(View.VISIBLE);
								designtxt.setVisibility(View.VISIBLE);
								parameterstxt.setVisibility(View.VISIBLE);
								parameterscroll.setVisibility(View.VISIBLE);
								part2content.setVisibility(View.VISIBLE);
								block.setBackgroundResource(R.drawable.if_top);
							} else {
								if (checkBoxs.getText().toString().equals("if")) {
									colorcodeview.setVisibility(View.VISIBLE);
									designtxt.setVisibility(View.VISIBLE);
									parameterstxt.setVisibility(View.VISIBLE);
									parameterscroll.setVisibility(View.VISIBLE);
									part2content.setVisibility(View.GONE);
									block.setBackgroundResource(R.drawable.if_top);
								} else {
									if (checkBoxs.getText().toString().equals("regular")) {
										colorcodeview.setVisibility(View.VISIBLE);
										designtxt.setVisibility(View.VISIBLE);
										parameterstxt.setVisibility(View.VISIBLE);
										parameterscroll.setVisibility(View.VISIBLE);
										part2content.setVisibility(View.GONE);
										block.setBackgroundResource(R.drawable.blockimg);
									} else {
										
									}
								}
							}
						}
					}
				}
				dialog1.dismiss();
			}
		});
		cancel.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				dialog1.dismiss();
			}
		});
	}
	
	
	public void _v() {
		temp = 1;
		PickedColor = "";
		tempr = "";
		map = new HashMap<>();
		dataget = new HashMap<>();
		Collections.sort(type);
		properties.clear();
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