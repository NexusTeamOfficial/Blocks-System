package com.eaglehoster.dragdrop;

import com.eaglehoster.dragdrop.AddblockActivity;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import java.util.Map;
import android.content.ClipboardManager;
import java.lang.reflect.Field;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;;

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Map<View, Map<String, String>> blockValuesMap = new HashMap<>();
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private double n = 0;
	private Type type = new TypeToken<Map<String, List<Map<String, String>>>>(){}.getType();
	private Gson gson = new Gson();
	private Map<String, List<Map<String, String>>> dataMap = new HashMap<>();
	private boolean toogle = false;
	public Map<String, Object> map = new HashMap<>();
	private Map<View, Map<String, String>> blockValues = new HashMap<>();
	String EVENT_NAME = "onCreate";
	Helper<String> Helper;
	
	private ArrayList<String> liststring = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
	private LinearLayout linear5;
	private LinearLayout linear2;
	private FloatingActionButton floating_action_button1;
	private LinearLayout linear8;
	private TextView copyRightFinal;
	private LinearLayout linear1;
	private LinearLayout shadow;
	private LinearLayout linear7;
	private TextView textview1;
	private Button save;
	private Button sourceCode;
	private Button load;
	private ImageView delete;
	private ScrollView vscroll2;
	private HorizontalScrollView hscroll1;
	private LinearLayout main;
	private LinearLayout linear13;
	private ScrollView vscroll3;
	private LinearLayout linear10;
	private LinearLayout linear14;
	private HorizontalScrollView hscroll3;
	private LinearLayout blockarea;
	private ListView listview1;
	
	private TimerTask t;
	private TimerTask tt;
	private SharedPreferences s;
	private Intent i = new Intent();
	private AlertDialog.Builder dilo;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
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
		linear5 = findViewById(R.id.linear5);
		linear2 = findViewById(R.id.linear2);
		floating_action_button1 = findViewById(R.id.floating_action_button1);
		linear8 = findViewById(R.id.linear8);
		copyRightFinal = findViewById(R.id.copyRightFinal);
		linear1 = findViewById(R.id.linear1);
		shadow = findViewById(R.id.shadow);
		linear7 = findViewById(R.id.linear7);
		textview1 = findViewById(R.id.textview1);
		save = findViewById(R.id.save);
		sourceCode = findViewById(R.id.sourceCode);
		load = findViewById(R.id.load);
		delete = findViewById(R.id.delete);
		vscroll2 = findViewById(R.id.vscroll2);
		hscroll1 = findViewById(R.id.hscroll1);
		main = findViewById(R.id.main);
		linear13 = findViewById(R.id.linear13);
		vscroll3 = findViewById(R.id.vscroll3);
		linear10 = findViewById(R.id.linear10);
		linear14 = findViewById(R.id.linear14);
		hscroll3 = findViewById(R.id.hscroll3);
		blockarea = findViewById(R.id.blockarea);
		listview1 = findViewById(R.id.listview1);
		s = getSharedPreferences("s", Activity.MODE_PRIVATE);
		dilo = new AlertDialog.Builder(this);
		
		floating_action_button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (toogle) {
					toogle = false;
					linear8.setVisibility(View.GONE);
				} else {
					toogle = true;
					linear8.setVisibility(View.VISIBLE);
				}
			}
		});
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				saveLogic();
			}
		});
		
		sourceCode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				showSourceCodeDialog();
			}
		});
		
		load.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				loadLogic(/*EVENT_NAME*/);
			}
		});
		
		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				n = _position;
				_refrieshlist();
			}
		});
	}
	
	private void initializeLogic() {
		if (s.contains("data")) {
			dataMap = gson.fromJson(s.getString("data", ""), type);
			
			for (String key : dataMap.keySet()) {
					liststring.add(key);
			}
		}
		n = 0;
		linear1.setOnDragListener(new drag());
		linear1.setTag("top");
		blockarea.setTag("blocks");
		main.setTag("main");
		linear5.setTag("");
		linear1.getBackground().setColorFilter(0xFFE65100, PorterDuff.Mode.MULTIPLY);
		shadow.getBackground().setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
		Helper = new Helper<>();
		linear8.setVisibility(View.GONE);
		((ViewGroup)shadow.getParent()).removeView(shadow);
		delete.setOnDragListener(new delete());
		listview1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, liststring));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		textview1.setText(EVENT_NAME);
		_refrieshlist();
	}
	
	
	@Override
	public void onBackPressed() {
		if (toogle) { 
			    toogle = false;
			    linear8.setVisibility(View.GONE); // Directly GONE kar diya
			
			    // Directly dialog dikha rahe hain, koi delay nahi
			    showExitDialog();
		} else {
			    toogle = true;
			    showExitDialog();
		}
		
	}
	private void showExitDialog() {
		    AlertDialog.Builder dilo = new AlertDialog.Builder(this);
		    dilo.setTitle("⚠️ Warning ⚠️");
		    dilo.setMessage("Are You Sure,\nYour all progress would be lost");
		
		    dilo.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface _dialog, int _which) {
				            Intent i = new Intent(getApplicationContext(), AddblockActivity.class);
				            startActivity(i);
				            finish();
				        }
			    });
		
		    dilo.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface _dialog, int _which) {
				            // Do nothing, just dismiss
				        }
			    });
		
		    dilo.create().show();
	}
	{
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//@Override
		//public boolean onCreateOptionsMenu(Menu menu) {
		    getMenuInflater().inflate(R.menu.main_menu, menu);
		    
		    // Change color of icons based on undo/redo availability
		    MenuItem undoItem = menu.findItem(R.id.action_undo);
		    MenuItem redoItem = menu.findItem(R.id.action_redo);
		
		    if (undoItem != null) {
			        Drawable undoIcon = undoItem.getIcon();
			        if (undoIcon != null) {
				            undoIcon.setColorFilter(undoStack.isEmpty() ? Color.GRAY : Color.BLACK, PorterDuff.Mode.SRC_IN);
				            undoItem.setIcon(undoIcon);
				        }
			    }
		
		    if (redoItem != null) {
			        Drawable redoIcon = redoItem.getIcon();
			        if (redoIcon != null) {
				            redoIcon.setColorFilter(redoStack.isEmpty() ? Color.GRAY : Color.BLACK, PorterDuff.Mode.SRC_IN);
				            redoItem.setIcon(redoIcon);
				        }
			    }
		
		    //return true;
		//}
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		final int _id = item.getItemId();
		final String _title = (String) item.getTitle();
		//@Override
		//public boolean onOptionsItemSelected(MenuItem item) {
		    switch (item.getItemId()) {
			        case R.id.action_undo:
			            undo();
			            invalidateOptionsMenu(); // Refresh menu
			            return true;
			
			        case R.id.action_redo:
			            redo();
			            invalidateOptionsMenu(); // Refresh menu
			            return true;
			
			        default:
			           // return super.onOptionsItemSelected(item);
			    }
		//}
		
		return super.onOptionsItemSelected(item);
	}
	public void _regular() {
	}
	class regular extends forAll {
		public regular(Context ctx){
			super(ctx);
			this.setBackgroundResource(R.drawable.blockimg);
			this.getBackground().setColorFilter(0xFFFB8C00, PorterDuff.Mode.MULTIPLY);
			this.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			this.setPadding((int) 8,(int) 16,(int) 8,(int) 16);
			this.setLayoutParams(new LinearLayout.LayoutParams((int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT),(int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT)));
			this.setOnLongClickListener(new View.OnLongClickListener(){
				@Override
				public boolean onLongClick(View _view){
					ClipData  data = ClipData.newPlainText("","");
					         //add drag shadow
					DragShadowBuilder shadow = new View.DragShadowBuilder(_view);
					if (Build.VERSION.SDK_INT >= 24){
						_view.startDragAndDrop(data, shadow, _view,1);
						 } else {
						_view.startDrag(data,shadow,_view, 1); 
					}
					return false;
				}
			});
			this.setTag(this);
			this.setOnDragListener(new drag());
		}
	}
	class boolen extends forAll {
    public boolen(Context ctx) {
        super(ctx);
        this.setBackgroundResource(R.drawable.boolean_block);
        this.getBackground().setColorFilter(0xFF2E7D32, PorterDuff.Mode.MULTIPLY);
        this.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        this.setLayoutParams(new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, 
            LinearLayout.LayoutParams.WRAP_CONTENT
        ));
    }
}

	class forAll extends LinearLayout {
		public forAll(Context ctx){
			super(ctx);
		}
		/*
}
}
*/
	}
	
	
	public void _dragNdrop() {
	}
	protected class drag implements View.OnDragListener {
		public boolean onDrag(final View v,final DragEvent event) {
			final int action = event.getAction();
			final View DV = ((View) event.getLocalState());
			switch(action) {
				case DragEvent.ACTION_DRAG_STARTED:
				DV.setVisibility(View.GONE);
				return true;
				case DragEvent.ACTION_DRAG_ENTERED:
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DRAG_LOCATION:
				//drag started
				if (!((LinearLayout)v.getParent()).getTag().toString().equals("blocks")) {
					if (v.getTag().toString().equals("top")) {
						try {
								((ViewGroup)shadow.getParent()).removeView(shadow);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) main).addView(shadow,(int)(0));
						try {
								((ViewGroup)DV.getParent()).removeView(DV);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) main).addView(DV,(int)(0));
					} else {
						try {
								((ViewGroup)shadow.getParent()).removeView(shadow);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) ((LinearLayout)v.getParent())).addView(shadow,(int)(((LinearLayout)v.getParent()).indexOfChild(v) + 1));
						try {
								((ViewGroup)DV.getParent()).removeView(DV);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) ((LinearLayout)v.getParent())).addView(DV,(int)(((LinearLayout)v.getParent()).indexOfChild(v) + 1));
					}
				}
				return true;
				case DragEvent.ACTION_DRAG_EXITED:
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DROP:
				//drop
				storeBlockData(DV);
				v.invalidate();
				return true;
				case DragEvent.ACTION_DRAG_ENDED:
				v.invalidate();
				final
				View dr = DV;
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								try{
									((ViewGroup)shadow.getParent()).removeView(shadow);
								}catch(Exception e){
									 
								}
								dr.setVisibility(View.VISIBLE);
							}
						});
					}
				};
				_timer.schedule(t, (int)(5));
				_refrieshlist();
				return true;
				default:
				break;
			}
			return false;
		}
	};
	{
	}
	
	
	public void _if_block() {
	}
	class if_block extends forAll {
		public if_block(Context ctx)  {
			super(ctx);
			LayoutInflater thisLL = getLayoutInflater(); 
			View thisVV = thisLL.inflate(R.layout.if_block, null);
			((LinearLayout) this).addView(thisVV);
			final LinearLayout top = (LinearLayout)
			thisVV.findViewById(R.id.top);
			final LinearLayout center = (LinearLayout)
			thisVV.findViewById(R.id.center);
			final LinearLayout middle = (LinearLayout)
			thisVV.findViewById(R.id.middle);
			final LinearLayout center2 = (LinearLayout)
			thisVV.findViewById(R.id.center2);
			final LinearLayout bottom = (LinearLayout)
			thisVV.findViewById(R.id.bottom);
			this.setLayoutParams(new LinearLayout.LayoutParams((int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT),(int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT)));
			top.getBackground().setColorFilter(0xFF2195F3, PorterDuff.Mode.MULTIPLY);
			bottom.getBackground().setColorFilter(0xFF2195F3, PorterDuff.Mode.MULTIPLY);
			middle.getBackground().setColorFilter(0xFF2195F3, PorterDuff.Mode.MULTIPLY);
			center.getBackground().setColorFilter(0xFF2195F3, PorterDuff.Mode.MULTIPLY);
			center2.getBackground().setColorFilter(0xFF2195F3, PorterDuff.Mode.MULTIPLY);
			middle.setVisibility(View.GONE);
			center2.setVisibility(View.GONE);
			/*
this.getBackground().setColorFilter(0xFF2195F3, PorterDuff.Mode.MULTIPLY);
*/
			this.center = center;
			this.center2 = center2;
			this.middle = middle;
			this.top = top;
			this.bottom = bottom;
			bottom.setTag(this);
			top.setTag(this);
			middle.setTag(this);
			this.setOnDragListener(new if_drop());
			top.setOnDragListener(new if_droptop());
			middle.setOnDragListener(new if_droptop2());
			bottom.setOnDragListener(new if_dropdown());
			this.setOnLongClickListener(new View.OnLongClickListener(){
				@Override
				public boolean onLongClick(View _view){
					ClipData  data = ClipData.newPlainText("","");
					         //add drag shadow
					DragShadowBuilder shadow = new View.DragShadowBuilder(_view);
					if (Build.VERSION.SDK_INT >= 24){
						_view.startDragAndDrop(data, shadow, _view,1);
						 } else {
						_view.startDrag(data,shadow,_view, 1); 
					}
					return false;
				}
			});
		}
		public LinearLayout center;
		public LinearLayout center2;
		public LinearLayout top;
		public LinearLayout middle;
		public LinearLayout bottom;
	}
	
	
	public void _if_dragNdrop() {
	}
	protected class if_drop implements View.OnDragListener {
		public boolean onDrag(final View v,final DragEvent event) {
			final int action = event.getAction();
			final View DV = ((View) event.getLocalState());
			switch(action) {
				case DragEvent.ACTION_DRAG_STARTED:
				DV.setVisibility(View.GONE);
				return true;
				case DragEvent.ACTION_DRAG_ENTERED:
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DRAG_LOCATION:
				//drag started
				try{
					if (!((LinearLayout)v.getParent()).getTag().toString().equals("blocks")) {
						if ((v instanceof if_block)) {
							try {
									((ViewGroup)shadow.getParent()).removeView(shadow);
							} catch (Exception exp65676) {
									 
							}
							((ViewGroup) ((if_block)v).center).addView(shadow);
							try {
									((ViewGroup)DV.getParent()).removeView(DV);
							} catch (Exception exp65676) {
									 
							}
							((ViewGroup) ((if_block)v).center).addView(DV);
						} else {
							try {
								   try {
										((ViewGroup)shadow.getParent()).removeView(shadow);
								} catch (Exception exp65676) {
										 
								}
								((ViewGroup) ((LinearLayout)((View) (v.getTag())).getParent())).addView(shadow,(int)(((LinearLayout)((View) (v.getTag())).getParent()).indexOfChild(((View) (v.getTag()))) + 1));
								try {
										((ViewGroup)DV.getParent()).removeView(DV);
								} catch (Exception exp65676) {
										 
								}
								((ViewGroup) ((LinearLayout)((View) (v.getTag())).getParent())).addView(DV,(int)(((LinearLayout)((View) (v.getTag())).getParent()).indexOfChild(((View) (v.getTag()))) + 1));
							} catch (Exception p) {
									 
							}
						}
					}
				}catch(Exception e){
					if ((v instanceof if_block)) {
						try {
								((ViewGroup)shadow.getParent()).removeView(shadow);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) ((if_block)v).center).addView(shadow);
						try {
								((ViewGroup)DV.getParent()).removeView(DV);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) ((if_block)v).center).addView(DV);
					} else {
						try {
							   try {
									((ViewGroup)shadow.getParent()).removeView(shadow);
							} catch (Exception exp65676) {
									 
							}
							((ViewGroup) ((LinearLayout)((View) (v.getTag())).getParent())).addView(shadow,(int)(((LinearLayout)((View) (v.getTag())).getParent()).indexOfChild(((View) (v.getTag()))) + 1));
							try {
									((ViewGroup)DV.getParent()).removeView(DV);
							} catch (Exception exp65676) {
									 
							}
							((ViewGroup) ((LinearLayout)((View) (v.getTag())).getParent())).addView(DV,(int)(((LinearLayout)((View) (v.getTag())).getParent()).indexOfChild(((View) (v.getTag()))) + 1));
						} catch (Exception p) {
								 
						}
					}
				}
				return true;
				case DragEvent.ACTION_DRAG_EXITED:
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DROP:
				//drop
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DRAG_ENDED:
				v.invalidate();
				final
				View dr = DV;
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								try{
									((ViewGroup)shadow.getParent()).removeView(shadow);
								}catch(Exception e){
									 
								}
								dr.setVisibility(View.VISIBLE);
							}
						});
					}
				};
				_timer.schedule(t, (int)(5));
				_refrieshlist();
				return true;
				default:
				break;
			}
			return false;
		}
	};
	{
	}
	protected class if_droptop implements View.OnDragListener {
		public boolean onDrag(final View v,final DragEvent event) {
			final int action = event.getAction();
			final View DV = ((View) event.getLocalState());
			switch(action) {
				case DragEvent.ACTION_DRAG_STARTED:
				DV.setVisibility(View.GONE);
				return true;
				case DragEvent.ACTION_DRAG_ENTERED:
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DRAG_LOCATION:
				//drag started
				try{
					if (!((LinearLayout)((View) (v.getTag())).getParent()).getTag().toString().equals("blocks")) {
						if_block block = (if_block) v.getTag();
						LinearLayout center = block.center;
						try {
								((ViewGroup)shadow.getParent()).removeView(shadow);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) ((ViewGroup) center)).addView(shadow,(int)(0));
						try {
								((ViewGroup)DV.getParent()).removeView(DV);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) ((ViewGroup) center)).addView(DV,(int)(0));
					}
				}catch(Exception e){
					try{
						if_block block = (if_block) v.getTag();
						LinearLayout center = block.center;
						try {
								((ViewGroup)shadow.getParent()).removeView(shadow);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) ((ViewGroup) center)).addView(shadow,(int)(0));
						try {
								((ViewGroup)DV.getParent()).removeView(DV);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) ((ViewGroup) center)).addView(DV,(int)(0));
					} catch (Exception exp101) {
					}
				}
				return true;
				case DragEvent.ACTION_DRAG_EXITED:
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DROP:
				//drop
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DRAG_ENDED:
				v.invalidate();
				final
				View dr = DV;
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								try{
									((ViewGroup)shadow.getParent()).removeView(shadow);
								}catch(Exception e){
									 
								}
								dr.setVisibility(View.VISIBLE);
							}
						});
					}
				};
				_timer.schedule(t, (int)(5));
				_refrieshlist();
				return true;
				default:
				break;
			}
			return false;
		}
	};
	{
	}
	protected class if_droptop2 implements View.OnDragListener {
		public boolean onDrag(final View v,final DragEvent event) {
			final int action = event.getAction();
			final View DV = ((View) event.getLocalState());
			switch(action) {
				case DragEvent.ACTION_DRAG_STARTED:
				DV.setVisibility(View.GONE);
				return true;
				case DragEvent.ACTION_DRAG_ENTERED:
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DRAG_LOCATION:
				//drag started
				try{
					if (!((LinearLayout)((View) (v.getTag())).getParent()).getTag().toString().equals("blocks")) {
						if_block block = (if_block) v.getTag();
						LinearLayout center = block.center2;
						try {
								((ViewGroup)shadow.getParent()).removeView(shadow);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) ((ViewGroup) center)).addView(shadow,(int)(0));
						try {
								((ViewGroup)DV.getParent()).removeView(DV);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) ((ViewGroup) center)).addView(DV,(int)(0));
					}
				}catch(Exception e){
					if_block block = (if_block) v.getTag();
					LinearLayout center = block.center2;
					try {
							((ViewGroup)shadow.getParent()).removeView(shadow);
					} catch (Exception exp65676) {
							 
					}
					((ViewGroup) ((ViewGroup) center)).addView(shadow,(int)(0));
					try {
							((ViewGroup)DV.getParent()).removeView(DV);
					} catch (Exception exp65676) {
							 
					}
					((ViewGroup) ((ViewGroup) center)).addView(DV,(int)(0));
				}
				return true;
				case DragEvent.ACTION_DRAG_EXITED:
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DROP:
				//drop
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DRAG_ENDED:
				v.invalidate();
				final
				View dr = DV;
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								try{
									((ViewGroup)shadow.getParent()).removeView(shadow);
								}catch(Exception e){
									 
								}
								dr.setVisibility(View.VISIBLE);
							}
						});
					}
				};
				_timer.schedule(t, (int)(5));
				_refrieshlist();
				return true;
				default:
				break;
			}
			return false;
		}
	};
	{
	}
	protected class if_dropdown implements View.OnDragListener {
		public boolean onDrag(final View v,final DragEvent event) {
			final int action = event.getAction();
			final View DV = ((View) event.getLocalState());
			switch(action) {
				case DragEvent.ACTION_DRAG_STARTED:
				DV.setVisibility(View.GONE);
				return true;
				case DragEvent.ACTION_DRAG_ENTERED:
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DRAG_LOCATION:
				//drag started
				try{
					if (!((LinearLayout)((View) (v.getTag())).getParent()).getTag().toString().equals("blocks")) {
						try {
								((ViewGroup)shadow.getParent()).removeView(shadow);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) ((LinearLayout)((View) (v.getTag())).getParent())).addView(shadow,(int)(((LinearLayout)((View) (v.getTag())).getParent()).indexOfChild(((View) (v.getTag()))) + 1));
						try {
								((ViewGroup)DV.getParent()).removeView(DV);
						} catch (Exception exp65676) {
								 
						}
						((ViewGroup) ((LinearLayout)((View) (v.getTag())).getParent())).addView(DV,(int)(((LinearLayout)((View) (v.getTag())).getParent()).indexOfChild(((View) (v.getTag()))) + 1));
					}
				}catch(Exception e){
					try {
							((ViewGroup)shadow.getParent()).removeView(shadow);
					} catch (Exception exp65676) {
							 
					}
					((ViewGroup) ((LinearLayout)((View) (v.getTag())).getParent())).addView(shadow,(int)(((LinearLayout)((View) (v.getTag())).getParent()).indexOfChild(((View) (v.getTag()))) + 1));
					try {
							((ViewGroup)DV.getParent()).removeView(DV);
					} catch (Exception exp65676) {
							 
					}
					((ViewGroup) ((LinearLayout)((View) (v.getTag())).getParent())).addView(DV,(int)(((LinearLayout)((View) (v.getTag())).getParent()).indexOfChild(((View) (v.getTag()))) + 1));
				}
				return true;
				case DragEvent.ACTION_DRAG_EXITED:
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DROP:
				//drop
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DRAG_ENDED:
				v.invalidate();
				final
				View dr = DV;
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								try{
									((ViewGroup)shadow.getParent()).removeView(shadow);
								}catch(Exception e){
									 
								}
								dr.setVisibility(View.VISIBLE);
							}
						});
					}
				};
				_timer.schedule(t, (int)(5));
				_refrieshlist();
				return true;
				default:
				break;
			}
			return false;
		}
	};
	{
	}
	
	
	public void _deletedragNdrop() {
	}
	protected class delete implements View.OnDragListener {
		public boolean onDrag(final View vm88,final DragEvent event) {
			final ImageView v = ((ImageView)vm88);
			final int action = event.getAction();
			final View DV = ((View) event.getLocalState());
			 
			switch(action) {
				case DragEvent.ACTION_DRAG_STARTED:
				 
				return true;
				case DragEvent.ACTION_DRAG_ENTERED:
				 
				v.invalidate();
				return true;
				case DragEvent.ACTION_DRAG_LOCATION:
				//drag started
				delete.setImageResource(R.drawable.bin1);
				return true;
				case DragEvent.ACTION_DRAG_EXITED:
				delete.setImageResource(R.drawable.bin);
				v.invalidate();
				return true;
				case DragEvent.ACTION_DROP:
				//drop
				((ViewGroup)DV.getParent()).removeView(DV);
				v.invalidate();
				return true;
				case DragEvent.ACTION_DRAG_ENDED:
				v.invalidate();
				delete.setImageResource(R.drawable.bin);
				return true;
				default:
				break;
			}
			return false;
		}
	};
	{
	}
	
	
	public void _addViewsTo(final View _view, final String _code) {
		for(String i:_code.split(" ")) {
			if (i.equals("%e.s")) {
				((LinearLayout)_view).addView(new TextView(MainActivity.this));
				View txt = ((LinearLayout)_view).getChildAt((int) ((LinearLayout) _view).getChildCount() - 1);
				((TextView)txt).setBackgroundColor(0xFFFFFFFF);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (120),(int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
						params.setMargins((int) 0, (int) 0, (int) 16, (int) 12);
						txt.setPadding((int) 5,(int) 2,(int) 5,(int) 2);
				        txt.setLayoutParams(params);
				((TextView)txt).setTextSize((int)12);
				((TextView)txt).setSingleLine(true);
				txt.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						try{
							if (!((LinearLayout)((View) (_view.getTag())).getParent()).getTag().toString().equals("blocks")) {
								_editdialogue(txt, "string");
							}
						}catch(Exception e){
							try {
								   if (!((LinearLayout)_view.getParent()).getTag().toString().equals("blocks")) {
									_editdialogue(txt, "string");
								}
							} catch (Exception p) {
									_editdialogue(txt, "string");
							}
						}
					}
				});
			} else {
				if (i.equals("%v.b")) {
					((LinearLayout)_view).addView(new boolen(MainActivity.this));
					View txt = ((LinearLayout)_view).getChildAt((int) ((LinearLayout) _view).getChildCount() - 1);
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT),(int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
							params.setMargins((int) 0, (int) 0, (int) 16, (int) 12);
					        txt.setLayoutParams(params);
					tt = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (Math.max(150, txt.getWidth())),(int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
											params.setMargins((int) 0, (int) 0, (int) 16, (int) 12);
									        txt.setLayoutParams(params);
								}
							});
						}
					};
					_timer.schedule(tt, (int)(50));
				} else {
					if (i.equals("%e.n")) {
						((LinearLayout)_view).addView(new TextView(MainActivity.this));
						View txt = ((LinearLayout)_view).getChildAt((int) ((LinearLayout) _view).getChildCount() - 1);
						((TextView)txt).setBackgroundColor(0xFFFFFFFF);
						((TextView)txt).setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)90, 0xFFFFFFFF));
						LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (120),(int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
								params.setMargins((int) 0, (int) 0, (int) 8, (int) 8);
								txt.setPadding((int) 5,(int) 2,(int) 5,(int) 2);
						        txt.setLayoutParams(params);
						((TextView)txt).setTextSize((int)12);
						((TextView)txt).setSingleLine(true);
						txt.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View _view){
								try{
									if (!((LinearLayout)((View) (_view.getTag())).getParent()).getTag().toString().equals("blocks")) {
										_editdialogue(txt, "number");
									}
								}catch(Exception e){
									try {
										   if (!((LinearLayout)_view.getParent()).getTag().toString().equals("blocks")) {
											_editdialogue(txt, "number");
										}
									} catch (Exception p) {
											_editdialogue(txt, "number");
									}
								}
							}
						});
					} else {
						if (i.equals("%v.b")) {
							
							    boolen boolBlock = new boolen(MainActivity.this);
							    ((LinearLayout)_view).addView(boolBlock);
							
							    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
							        LinearLayout.LayoutParams.WRAP_CONTENT,
							        LinearLayout.LayoutParams.WRAP_CONTENT
							    );
							    params.setMargins(0, 0, 16, 12);
							    boolBlock.setLayoutParams(params);
							
						} else {
							((LinearLayout)_view).addView(new TextView(MainActivity.this));
							View txt = ((LinearLayout)_view).getChildAt((int) ((LinearLayout) _view).getChildCount() - 1);
							((TextView)txt).setText(i + " ");
							((TextView)txt).setTextColor(0xFFFFFFFF);
							((TextView)txt).setTypeface(Typeface.DEFAULT, 1);
						}
					}
				}
			};
		}
	}
	
	
	public void _editdialogue(final View _view, final String _type) {
		/**
 * Project: Open Source Block-Based Programming System  
 *  
 * Description:  
 * This project is designed to provide an intuitive, drag-and-drop block-based  
 * programming environment. It enables developers, especially beginners and  
 * those with limited resources, to create applications visually without  
 * needing deep coding knowledge. The system supports real-time source code  
 * generation from blocks, making coding more accessible and efficient.  
 *  
 * Features:  
 * - Visual block-based coding interface  
 * - Real-time source code generation  
 * - Open-source and free to modify  
 * - Designed for beginners and experienced developers alike  
 * - Lightweight, efficient, and highly scalable  
 *  
 * @author NexusTeam & SmartIndiaGaming  
 * @created 23-02-2025  
 * @license Open Source - Free to Modify & Distribute  
 * @origin Made in India 🇮🇳 | Empowering Developers Globally  
 * @note Developed to make coding accessible to all.  
 */
		
		AlertDialog dialog1 = new AlertDialog.Builder(MainActivity.this).create();
		LayoutInflater dialog1LI = getLayoutInflater();
		View dialog1CV = dialog1LI.inflate(R.layout.edit_dialogue, null);
		dialog1.setView(dialog1CV);
		
		final EditText edit = dialog1CV.findViewById(R.id.edit);
		final Button cancel = dialog1CV.findViewById(R.id.cancel);
		final Button save = dialog1CV.findViewById(R.id.save);
		
		dialog1.setCancelable(true);
		
		if (_type.equals("string")) {
			    edit.setHint("Enter String Value");
		} else if (_type.equals("number")) {
			    edit.setHint("Enter Number Value");
			    edit.setInputType(InputType.TYPE_CLASS_NUMBER);
		}
		
		final String placeholder = (String) _view.getTag();
		
		String currentText = ((TextView) _view).getText().toString();
		String placeholderz = _view.getContentDescription().toString();
		//edit.setText(((TextView) _view).getText().toString());
		if (currentText.equals(placeholder)) {
			        edit.setText("");
			    } else {
			        edit.setText(currentText);
			    }
		dialog1.show();
		
		save.setOnClickListener(new View.OnClickListener() {
			    @Override
			    public void onClick(View v) {
				        String newValue = edit.getText().toString().trim();
				        if (placeholder != null && !newValue.isEmpty()) {
					            View parentBlock = (View) _view.getParent();
					
					            if (parentBlock != null) {
						                // Ensure blockValues entry exists
						                if (!blockValues.containsKey(parentBlock)) {
							                    blockValues.put(parentBlock, new HashMap<>());
							                }
						
						                Map<String, String> placeholders = blockValues.get(parentBlock);
						
						                // ✅ Get old value (if exists)
						                String oldValue = placeholders.getOrDefault(placeholder, "");
						
						                // ✅ Store new value
						                placeholders.put(placeholder, newValue);
						
						                // ✅ Update TextView
						                ((TextView) _view).setText(newValue);
						                try {
							               // _view.setTag(R.id.main, newValue);
							               }catch(Exception e){
							 
							               }
						               
						               saveLogic();
						
						                // ✅ Update block code properly
						                if (parentBlock.getTag() != null) {
							                    String blockCode = parentBlock.getTag().toString();
							
							                    // 🔥 Ensure both first-time and updates work correctly
							                    if (!oldValue.isEmpty() && blockCode.contains(oldValue)) {
								                        blockCode = blockCode.replace(oldValue, newValue);
								                    } else if (blockCode.contains(placeholder)) {
								                        blockCode = blockCode.replace(placeholder, newValue);
								                    }
							
							                    parentBlock.setTag(blockCode);
							                    Log.d("Pitchholder", "Block Code Updated: " + blockCode);
							                }
						                Log.d("Pitchholder", "Value updated: " + placeholder + " -> " + newValue);
						            }
					        }
				
				        // ✅ Fix layout
				        _updateViewLayout(_view);
				        dialog1.dismiss();
				    }
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			    @Override
			    public void onClick(View v) {
				        dialog1.dismiss();
				    }
		});
		
		
	}
	
	private void _updateViewLayout(final View txt) {
		    TimerTask tt = new TimerTask() {
			        @Override
			        public void run() {
				            runOnUiThread(new Runnable() {
					                @Override
					                public void run() {
						                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						                            Math.max(125, txt.getWidth()), LinearLayout.LayoutParams.WRAP_CONTENT);
						                    params.setMargins(0, 0, 16, 12);
						
						                    txt.setPadding(5, 2, 5, 2);
						                    txt.setLayoutParams(params);
						                }
					            });
				        }
			    };
		    _timer.schedule(tt, 50);
	}
	{
	}
	
	
	public void _refrieshlist() {
		//private void _refrieshlist() {
		    t = new TimerTask() {
			        @Override
			        public void run() {
				            runOnUiThread(new Runnable() {
					                @Override
					                public void run() {
						                    try {
							                        if (s.contains("data")) {
								                            dataMap = gson.fromJson(s.getString("data", ""), type);
								                            if (dataMap == null || !dataMap.containsKey(liststring.get((int) n))) {
									                                Log.e("Error", "No data found for the selected index");
									                                return;
									                            }
								                        } else {
								                            Log.e("Error", "SharedPreferences 'data' key missing!");
								                            return;
								                        }
							
							                        List<Map<String, String>> dataMapList = dataMap.get(liststring.get((int) n));
							                        if (dataMapList == null) return;
							
							                        list = new ArrayList<>();
							                        for (Map<String, String> map : dataMapList) {
								                            HashMap<String, Object> hashMap = new HashMap<>();
								                            for (String key : map.keySet()) {
									                                String value = map.get(key);
									                                hashMap.put(key, value);
									                            }
								                            list.add(hashMap);
								                        }
							
							                        blockarea.removeAllViews();
							                        blockContentMap.clear(); 
							                        
							                        List<String> allContents = new ArrayList<>();
							
							                        for (int i = 0; i < list.size(); i++) {
								                            String type = list.get(i).get("type").toString();
								                            String content = list.get(i).get("content").toString();
								                            String blockCode = list.get(i).containsKey("code") ? list.get(i).get("code").toString() : "";
								                            String colorCode = list.get(i).containsKey("colour") ? list.get(i).get("colour").toString() : "";
								                            
								                            Map<String, String> contentDataxy = new HashMap<>();
								                            contentDataxy.put("content", content);
								                           // contentData.put("blockCode", blockCode);
								                           contentDataxy.put("color", colorCode);
								                           String jsonTag1 = gson.toJson(contentDataxy);
								                           Helper.setKey(R.id.main, jsonTag1);
								                            
								                            if (type.equals("heading")) {
									                                TextView txt = new TextView(MainActivity.this);
									                                txt.setText(content);
									                                txt.setBackgroundColor(0xFF212121);
									                                txt.setTextColor(0xFFFFFFFF);
									                                blockarea.addView(txt);
									
									                            } else if (type.equals("regular")) {
									                                regular r = new regular(MainActivity.this);
									                                blockarea.addView(r);
									                                Map<String, String> contentDataq = new HashMap<>();
									                                    contentDataq.put("content", content);
									                                   // contentData.put("blockCode", blockCode);
									                                    contentDataq.put("color", colorCode);
									
									                                    String jsonTag = gson.toJson(contentDataq);
									                                    r.setTag(R.id.main, jsonTag);  // ✅ Safe tag setting
									                               // r.setTag(R.id.main + 1, "#" + colorCode);
									                                _addViewsTo(r, content, blockCode, "");
									
									                                try {
										                                    int colour = Color.parseColor("#" + list.get(i).get("colour").toString());
										                                    r.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
										                                    r.invalidate();
										                                } catch (Exception e) {
										                                    Log.e("ColorError", "Error applying color", e);
										                                }
									
									                            } else if (type.equals("if")) {
									                                if_block ifb = new if_block(MainActivity.this);
									                                blockarea.addView(ifb);
									                                Map<String, String> contentDatax = new HashMap<>();
									                                    contentDatax.put("content", content);
									                                   // contentData.put("blockCode", blockCode);
									                                    contentDatax.put("color", colorCode);
									
									                                    String jsonTag = gson.toJson(contentDatax);
									                                    ifb.setTag(R.id.main, jsonTag);
									                             //  ifb.setTag(R.id.main + 1, "#" + colorCode);
									                                _addViewsTo(ifb.top, content, blockCode, "");
									
									                                try {
										                                    int colour = Color.parseColor("#" + list.get(i).get("colour").toString());
										                                    ifb.top.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
										                                    ifb.center.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
										                                    ifb.bottom.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
										
										                                    ifb.top.invalidate();
										                                    ifb.center.invalidate();
										                                    ifb.bottom.invalidate();
										                                } catch (Exception e) {
										                                    Log.e("ColorError", "Error applying color to if_block", e);
										                                }
									
									                            } else if (type.equals("if.e")) {
									                                if_block ifb = new if_block(MainActivity.this);
									                                blockarea.addView(ifb);
									                                Map<String, String> contentDataz = new HashMap<>();
									                                    contentDataz.put("content", content);
									                                //    contentData.put("blockCode", blockCode);
									                                    contentDataz.put("color", colorCode);
									
									                                    String jsonTag = gson.toJson(contentDataz);
									                                    ifb.setTag(R.id.main, jsonTag);
									                                //ifb.setTag(R.id.main + 1, "#" + colorCode);
									                                _addViewsTo(ifb.top, content, blockCode, "");
									
									                                if (list.get(i).containsKey("content2")) {
										                                    _addViewsTo(ifb.middle, list.get(i).get("content2").toString(), blockCode, "");
										                                } else {
										                                    _addViewsTo(ifb.middle, " ", blockCode, "");
										                                }
									
									                                ifb.middle.setVisibility(View.VISIBLE);
									                                ifb.center2.setVisibility(View.VISIBLE);
									
									                                try {
										                                    int colour = Color.parseColor("#" + list.get(i).get("colour").toString());
										                                    ifb.top.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
										                                    ifb.center.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
										                                    ifb.bottom.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
										                                    ifb.center2.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
										                                    ifb.middle.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
										
										                                    ifb.top.invalidate();
										                                    ifb.center.invalidate();
										                                    ifb.bottom.invalidate();
										                                    ifb.center2.invalidate();
										                                    ifb.middle.invalidate();
										                                } catch (Exception e) {
										                                    Log.e("ColorError", "Error applying color to if.e block", e);
										                                }
									
									                            } else if (type.equals("variable")) { // ✅ Handling `%v.s`
									                                variable_block vb = new variable_block(MainActivity.this);
									                                blockarea.addView(vb);
									
									                                // ✅ Restore Variable Name from Saved Data
									                                if (list.get(i).containsKey("content")) {
										                                    //vb.setVariableName(list.get(i).get("content").toString());
										                                    vb.setText(list.get(i).get("content").toString());
										                                }
									
									                                _addViewsTo(vb, list.get(i).get("content").toString(), blockCode,""); // ✅ Adding Editable String Inside
									
									                                try {
										                                    int colour = Color.parseColor("#" + list.get(i).get("colour").toString());
										                                    vb.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
										                                    vb.invalidate();
										                                } catch (Exception e) {
										                                    Log.e("ColorError", "Error applying color to variable block", e);
										                                }
									                            }
								                        }
							                    } catch (Exception e) {
							                        Log.e("BlockLoadingError", "Error loading blocks", e);
							                    }
						                }
					            });
				        }
			    };
		    _timer.schedule(t, 50);
		//}
		/*
t = new TimerTask() {
@Override
public void run() {
runOnUiThread(new Runnable() {
@Override
public void run() {
dataMap = gson.fromJson(s.getString("data", ""), type);
List<Map<String, String>> dataMapList = dataMap.get(liststring.get((int)(n)));
list = new ArrayList<>();
for (Map<String, String> map : dataMapList) { 
    HashMap<String, Object> hashMap = new HashMap<>();
    for (String key : map.keySet()) {
	        String value = map.get(key);
	        hashMap.put(key, value);
	    }

    list.add(hashMap);
};
blockarea.removeAllViews();
for(int _repeat61 = 0; _repeat61 < (int)(list.size()); _repeat61++) {
if (list.get((int)_repeat61).get("type").toString().equals("heading")) {
((LinearLayout)blockarea).addView(new TextView(MainActivity.this));
View txt = ((LinearLayout)blockarea).getChildAt((int) ((LinearLayout) blockarea).getChildCount() - 1);
((TextView)txt).setText(list.get((int)_repeat61).get("content").toString());
((TextView)txt).setBackgroundColor(0xFF212121);
((TextView)txt).setTextColor(0xFFFFFFFF);
} else {
if (list.get((int)_repeat61).get("type").toString().equals("regular")) {
regular r = new regular(MainActivity.this);
blockarea.addView(r);
_addViewsTo(r, list.get((int)_repeat61).get("content").toString());
try {
   String colours = "#" + list.get((int)_repeat61).get("colour").toString();
int colour = Color.parseColor(colours);
r.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
r.invalidate();
} catch (Exception eror) {
	 
}
} else {
if (list.get((int)_repeat61).get("type").toString().equals("if")) {
((LinearLayout)blockarea).addView(new if_block(MainActivity.this));
View if_b = ((LinearLayout)blockarea).getChildAt((int) ((LinearLayout) blockarea).getChildCount() - 1);
((if_block)if_b).top.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
_addViewsTo(((if_block)if_b).top, list.get((int)_repeat61).get("content").toString());
try {
   String colours = "#" + list.get((int)_repeat61).get("colour").toString();
int colour = Color.parseColor(colours);
((if_block)if_b).top.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
((if_block)if_b).center.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);((if_block)if_b).bottom.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);

((if_block)if_b).top.invalidate();
((if_block)if_b).center.invalidate();
((if_block)if_b).bottom.invalidate();
} catch (Exception eror) {
	 
}
} else {
if (list.get((int)_repeat61).get("type").toString().equals("if.e")) {
((LinearLayout)blockarea).addView(new if_block(MainActivity.this));
View if_b = ((LinearLayout)blockarea).getChildAt((int) ((LinearLayout) blockarea).getChildCount() - 1);
((if_block)if_b).top.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
_addViewsTo(((if_block)if_b).top, list.get((int)_repeat61).get("content").toString());
if (list.get((int)_repeat61).get("content2") != null) {
_addViewsTo(((if_block)if_b).middle, list.get((int)_repeat61).get("content2").toString());
} else {
_addViewsTo(((if_block)if_b).middle, " ");
}
((if_block)if_b).middle.setVisibility(View.VISIBLE);
((if_block)if_b).center2.setVisibility(View.VISIBLE);
try {
   String colours = "#" + list.get((int)_repeat61).get("colour").toString();
int colour = Color.parseColor(colours);
((if_block)if_b).top.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
((if_block)if_b).center.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);((if_block)if_b).bottom.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
((if_block)if_b).center2.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);((if_block)if_b).middle.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);

((if_block)if_b).top.invalidate();
((if_block)if_b).center.invalidate();
((if_block)if_b).bottom.invalidate();
((if_block)if_b).center2.invalidate();
((if_block)if_b).middle.invalidate();
} catch (Exception eror) {
	 
}
} else {

}
}
}
}
}
}
});
}
};
_timer.schedule(t, (int)(50));
*/
	}
	
	
	public void _variable() {
	}
	private void showSourceCodeDialog() {
		    StringBuilder sourceCode = new StringBuilder();
		
		    for (int i = 0; i < main.getChildCount(); i++) {
			        View block = main.getChildAt(i);
			
			        if (block instanceof regular || block instanceof if_block) {
				            String blockCode = getBlockContent(block);
				            String updatedBlockCode = replacePlaceholders(block, blockCode);
				            sourceCode.append(updatedBlockCode).append("\n");
				        }
			    }
		
		    showFormattedSourceCode(sourceCode.toString());
	}
	
	// ✅ Extracts all EditText values recursively
	private List<String> extractEditTextValues(ViewGroup parent) {
		    List<String> values = new ArrayList<>();
		
		    for (int i = 0; i < parent.getChildCount(); i++) {
			        View child = parent.getChildAt(i);
			        if (child instanceof EditText) {
				            String value = ((EditText) child).getText().toString().trim();
				            values.add(value.isEmpty() ? "EMPTY_VALUE" : value); // ✅ Handle empty values
				        } else if (child instanceof ViewGroup) {
				            values.addAll(extractEditTextValues((ViewGroup) child)); // ✅ Recursively check for nested views
				        }
			    }
		
		    return values;
	}
	
	
	// 🔥 Show final source code in a dialog
	private void showFormattedSourceCode(String code) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    builder.setTitle("Generated Source Code");
		
		    ScrollView scrollView = new ScrollView(this);
		    TextView editText = new TextView(this);
		    //source code copy
		    editText.setTextIsSelectable(true);
		
		    editText.setText(code);
		    //size to say
		    editText.setTextSize(14);
		    editText.setPadding(16, 16, 16, 16);
		    //background color customize
		    editText.setBackgroundColor(Color.TRANSPARENT);
		 //   editText.setFocusable(false);
		
		    scrollView.addView(editText);
		    builder.setView(scrollView);
		
		    builder.setPositiveButton("Copy", (dialog, which) -> {
			        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			        ClipData clip = ClipData.newPlainText("Source Code", code);
			        clipboard.setPrimaryClip(clip);
			        Toast.makeText(MainActivity.this, "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
			    });
		
		    builder.setNegativeButton("Close", null);
		    builder.show();
	}
	// ✅ Properly replaces placeholders like `%1$s`, `%2$s` with values
	private String replacePlaceholders(View block, String code) {
		    if (blockValues.containsKey(block)) {
			        for (Map.Entry<String, String> entry : blockValues.get(block).entrySet()) {
				            code = code.replace(entry.getKey(), entry.getValue());
				        }
			    }
		    return code;
	}
	
	/**
 * Saves the current blocks displayed in the main view to a JSON file.
 * 
 * @throws IOException If an error occurs while writing to the file.
 * @throws JsonSyntaxException If there's an issue parsing existing JSON data.
 * @gets Retrieves block data from the UI and stores it persistently.
 */
	private void saveBlocks() {
		    if (main.getChildCount() == 0) {
			        showToast("No blocks available.");
			        return; // ✅ Prevent saving empty data
			    }
		    
		    
		    _refrieshlist();
		    
		
		    @NonNull List<Map<String, String>> savedBlocks = new ArrayList<>();
		
		    for (int j = 0; j < main.getChildCount(); j++) {
			        @Nullable View view = main.getChildAt(j);
			        @NonNull Map<String, String> contentData = new HashMap<>();
			
			        if (view == null) continue; // Skip if view is null
			
			        // ✅ Ensure "blockCode" is not empty
			        @NonNull String blockCode = getBlockContent(view);
			        if (blockCode.isEmpty()) continue; // Skip invalid blocks
			
			        contentData.put("blockCode", blockCode);
			
			        // ✅ Determine block type
			        if (view instanceof regular) {
				            contentData.put("type", "regular");
				        } else if (view instanceof if_block) {
				            contentData.put("type", "if_block");
				            @NonNull if_block ifb = (if_block) view;
				
				            // ✅ Save secondary content if exists
				            @Nullable Object middleTag = ifb.middle.getTag(R.id.main);
				            if (middleTag != null) {
					                contentData.put("content_secondary", middleTag.toString());
					            }
				        }
			
			        // ✅ Merge existing JSON data from `setTag()`
			        @Nullable Object tag = view.getTag(R.id.main);
			        if (tag != null) {
				            try {
					                @Nullable Map<String, String> parsedData = gson.fromJson(tag.toString(), Map.class);
					                if (parsedData != null) {
						                    contentData.putAll(parsedData);
						                }
					            } catch (Exception e) {
					            }
				        }
			
			        // ✅ Save placeholder values safely
			        if (blockValues.containsKey(view)) {
				            contentData.putAll(blockValues.get(view));
				        }
			       
			       @Nullable List<Integer> keys = new Helper<String>().getAllKeys();
			if (keys != null) {
				    for (Integer key : keys) {
					        contentData.put(String.valueOf(key), key.toString()); // Store each key as both key and value
					    }
			}
			
			
			
			
			        // ✅ Fix placeholder values before saving
			
			        // ✅ Add the block to list
			        savedBlocks.add(contentData);
			    }
		
		    // ✅ Save only if valid blocks exist
		    saveBlocksToFile(EVENT_NAME, savedBlocks);
	}
	
	/**
 * Shows a Toast message.
 */
	private void showToast(@NonNull String message) {
		    @NonNull Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
		    toast.show();
	}
	
	private void loadBlocks() {
		    main.removeAllViews();
		    String json = s.getString("saved_blocks", "[]"); // ✅ Load saved blocks
		
		    if (!json.isEmpty()) {
			        try {
				            Type type = new TypeToken<List<Map<String, String>>>() {}.getType();
				            List<Map<String, String>> savedBlocks = gson.fromJson(json, type);
				
				            blockContentMap.clear();
				            blockValues.clear();
				
				            for (Map<String, String> data : savedBlocks) {
					                String typeValue = data.get("type");
					                String content = data.getOrDefault("content", "");
					                String blockCode = data.getOrDefault("code", "");
					                String colorCode = data.getOrDefault("colour", "");
					                View block;
					
					                if ("regular".equals(typeValue)) {
						                    block = new regular(this);
						                } else if ("if_block".equals(typeValue)) {
						                    block = new if_block(this);
						                } else {
						                    continue; // Skip unknown block types
						                }
					
					                // ✅ Restore block ID and content
					                int blockId = View.generateViewId();
					                block.setId(blockId);
					
					                if (!blockValues.containsKey(block)) {
						                    blockValues.put(block, new HashMap<>());
						                }
					
					                // ✅ Restore placeholders from saved data
					                for (Map.Entry<String, String> entry : data.entrySet()) {
						                    if (entry.getKey().startsWith("%")) {
							                        blockValues.get(block).put(entry.getKey(), entry.getValue());
							                    }
						                }
					
					                _addViewsTo(block, content, blockCode, ""); // ✅ Add text views
					
					                // ✅ Restore placeholder text inside TextViews
					                if (block instanceof LinearLayout) {
						                    LinearLayout blockLayout = (LinearLayout) block;
						                    for (int i = 0; i < blockLayout.getChildCount(); i++) {
							                        View child = blockLayout.getChildAt(i);
							                        if (child instanceof TextView && child.getTag() != null) {
								                            String placeholder = child.getTag().toString();
								                            if (blockValues.get(block).containsKey(placeholder)) {
									                                ((TextView) child).setText(blockValues.get(block).get(placeholder)); // ✅ Restore text
									                            }
								                        }
							                    }
						                }
					
					                // ✅ Restore block color
					                try {
						                    int color = Color.parseColor("#" + colorCode);
						                    if (block instanceof regular) {
							                        block.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
							                    } else if (block instanceof if_block) {
							                        if_block ifb = (if_block) block;
							                        ifb.top.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
							                        ifb.center.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
							                        ifb.bottom.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
							                        ifb.middle.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
							                        ifb.center2.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
							                    }
						                } catch (Exception e) {
						                    Log.e("ColorError", "Error applying color", e);
						                }
					
					                main.addView(block);
					                blockContentMap.put(blockId, data);
					            }
				        } catch (Exception e) {
				            Log.e("LoadBlocks", "Error loading blocks", e);
				        }
			    }
	}
	
	
	/**
 * Retrieves the block's stored content (code) from its tag.
 *
 * @param block The block view from which content is extracted.
 * @return The stored block content if available, otherwise an empty string.
 * @receives A block view containing stored data.
 * @gets Fetches and returns the code stored in the block's tag.
 */
	@NonNull
	private String getBlockContent(@NonNull View block) {
		    if (block instanceof regular || block instanceof if_block) {
			        if (block.getTag() != null) {
				            return block.getTag().toString(); // ✅ Ensure "code" is always fetched
				        }
			    }
		    return "";  // 🔴 If no code is found, return an empty string
	}
	
	
	
	/**
 * Retrieves input values from EditText views inside a given block.
 *
 * @param block The block view containing EditText fields.
 * @return A list of input values extracted from the block.
 * @receives A block view containing input fields.
 * @gets Collects and returns all input values from EditTexts within the block.
 */
	@NonNull
	private List<String> getBlockInputValues(@NonNull View block) {
		    @NonNull List<String> values = new ArrayList<>();
		
		    if (block instanceof LinearLayout) {
			        LinearLayout layout = (LinearLayout) block;
			        for (int i = 0; i < layout.getChildCount(); i++) {
				            View child = layout.getChildAt(i);
				            if (child instanceof EditText) {
					                values.add(((EditText) child).getText().toString()); // ✅ Extract EditText values
					            } else if (child instanceof ViewGroup) {
					                values.addAll(getBlockInputValues((ViewGroup) child)); // ✅ Nested inputs handle karega
					            }
				        }
			    }
		
		    return values;
	}
	
	/**
 * Stores block-related data such as code, type, and color into a map.
 *
 * @param block The block whose data needs to be stored.
 * @receives A block view containing relevant data.
 * @gets Extracts and stores the block's data for later retrieval.
 */
	private void storeBlockData(@NonNull View block) {
		    if (block instanceof regular || block instanceof if_block) {
			        @NonNull Map<String, String> blockData = new HashMap<>();
			
			        // 🔥 Extract and store block's code
			        @NonNull String extractedCode = block.getTag() != null ? block.getTag().toString() : "";
			
			        blockData.put("code", extractedCode);
			        blockData.put("type", block instanceof regular ? "regular" : "if_block");
			
			        if (block instanceof regular) {
				            blockData.put("colour", "#FB8C00");
				        } else {
				            blockData.put("colour", "#2195F3");
				        }
			
			        // ✅ "code" ko block ke tag me store karo (Direct way)
			        block.setTag(extractedCode);
			
			        Log.d("Debug", "Block Stored with Code: " + extractedCode);
			    }
	}
	
	
	/**
 * Retrieves stored placeholder values for a given block.
 *
 * @param block The block whose placeholder values are retrieved.
 * @return A list of stored placeholder values, or an empty list if none exist.
 * @throws JsonSyntaxException If an error occurs while parsing the JSON data.
 * @receives A block view with associated stored placeholder values.
 * @gets Fetches and returns placeholder values stored in the block.
 */
	@NonNull
	private List<String> getBlockPitchHolders(@NonNull View block) {
		    @NonNull List<String> inputValues = new ArrayList<>();
		    @NonNull String key = "values_" + block.hashCode();
		
		    if (map.containsKey(key)) {
			        try {
				            Type type = new TypeToken<List<String>>() {}.getType();
				            @NonNull String jsonString = String.valueOf(map.get(key)); // ✅ Ensure String type
				            inputValues = new Gson().fromJson(jsonString, type);
				        } catch (Exception e) {
				            Log.e("BlockPitchHolders", "Error retrieving values", e);
				            showMessage("Error retrieving values: " + e.toString());
				        }
			    }
		
		    return inputValues;
	}
	
	/*public void _addViewsTo(final View _view, final String _code, String blockCode) {
    _view.setTag(blockCode);
    int placeholderIndex = 1;

    for (String i : _code.split(" ")) {
        if (i.equals("%e.s") || i.equals("%e.n")) {
            TextView txt = new TextView(MainActivity.this);
            txt.setId(View.generateViewId());
            txt.setTag("%" + placeholderIndex + "$s");
            txt.setText("Tap to enter");
            txt.setTextSize(12);
            txt.setPadding(5, 2, 5, 2);
            txt.setBackgroundColor(0xFFFFFFFF);
            txt.setTextColor(Color.BLACK);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 16, 12);
            txt.setLayoutParams(params);

            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _editdialogue(txt, "string");
                }
            });

            // Ensure block-specific storage in Global Map
            if (!blockValues.containsKey(_view)) {
                blockValues.put(_view, new HashMap<>());
            }

            blockValues.get(_view).put("%" + placeholderIndex + "$s", "Tap to enter");

            ((LinearLayout) _view).addView(txt);
            placeholderIndex++;
        } else {
            TextView textView = new TextView(MainActivity.this);
            textView.setText(i + " ");
            textView.setTextColor(0xFFFFFFFF);
            textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            ((LinearLayout) _view).addView(textView);
        }
    }
}*/
	
	/**
 * Project: Open Source Block-Based Programming System  
 *  
 * Description:  
 * This project is designed to provide an intuitive, drag-and-drop block-based  
 * programming environment. It enables developers, especially beginners and  
 * those with limited resources, to create applications visually without  
 * needing deep coding knowledge. The system supports real-time source code  
 * generation from blocks, making coding more accessible and efficient.  
 *  
 * Features:  
 * - Visual block-based coding interface  
 * - Real-time source code generation  
 * - Open-source and free to modify  
 * - Designed for beginners and experienced developers alike  
 * - Lightweight, efficient, and highly scalable  
 *  
 * @author NexusTeam & SmartIndiaGaming  
 * @created 23-02-2025  
 * @license Open Source - Free to Modify & Distribute  
 * @origin Made in India 🇮🇳 | Empowering Developers Globally  
 * @note Developed to make coding accessible to all.  
 */
	
	public void _addViewsTo(final View _view, final String _code, String blockCode, final String _values) {
		    _view.setTag(blockCode);
		    int placeholderIndex = 1;
		    String[] valueArr = _values.split(";");
		    int valueIndex = 0;
		    
		    ViewGroup parentLayout = (ViewGroup) _view;
		    
		    parentLayout.removeAllViews();
		
		    for (String i : _code.split(" ")) {
			
			        if (i.equals("%e.s")) {
				            ((LinearLayout) _view).addView(new TextView(MainActivity.this));
				
				            View txt = ((LinearLayout) _view).getChildAt(((LinearLayout) _view).getChildCount() - 1);
				
				            ((TextView) txt).setBackgroundColor(0xFFFFFFFF);
				
				            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120, LinearLayout.LayoutParams.WRAP_CONTENT);
				            params.setMargins(0, 0, 16, 12);
				
				            txt.setPadding(5, 2, 5, 2);
				            txt.setLayoutParams(params);
				            txt.setId(View.generateViewId());
				
				            ((TextView) txt).setTextSize(12);
				            ((TextView) txt).setSingleLine(true);
				            txt.setTag("%" + placeholderIndex + "$s");
				            ((TextView) txt).setContentDescription(i);
				
				            // Ensure blockValues entry exists
				            if (!blockValues.containsKey(_view)) {
					                blockValues.put(_view, new HashMap<>());
					            }
				            
				             // Show placeholder first, then value if available
				            if (valueArr.length > valueIndex && !valueArr[valueIndex].isEmpty()) {
					                 ((TextView) txt).setText(valueArr[valueIndex]); // Show saved value
					            } else {
					                 ((TextView) txt).setText(i); // Show placeholder if no value exists
					            }
				            valueIndex++;
				
				            // Add placeholder text in map
				            blockValues.get(_view).put(txt.getTag().toString(), ""/*"Tap to enter"*/);
				
				            placeholderIndex++;
				
				            txt.setOnClickListener(new View.OnClickListener() {
					                @Override
					                public void onClick(View v) {
						                    try {
							                        if (!((LinearLayout) v.getParent()).getTag().toString().equals("blocks")) {
								                            _editdialogue(txt, "string");
								                        }
							                    } catch (Exception e) {
							                        _editdialogue(txt, "string");
							                    }
						                }
					            });
				
				        } else if (i.equals("%e.n")) {
				            ((LinearLayout) _view).addView(new TextView(MainActivity.this));
				
				            View txt = ((LinearLayout) _view).getChildAt(((LinearLayout) _view).getChildCount() - 1);
				
				            ((TextView) txt).setBackgroundColor(0xFFFFFFFF);
				            ((TextView) txt).setBackground(new GradientDrawable() {
					                public GradientDrawable getIns(int a, int b) {
						                    this.setCornerRadius(a);
						                    this.setColor(b);
						                    return this;
						                }
					            }.getIns(90, 0xFFFFFFFF));
				
				            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120, LinearLayout.LayoutParams.WRAP_CONTENT);
				            params.setMargins(0, 0, 8, 8);
				
				            txt.setPadding(5, 2, 5, 2);
				            txt.setLayoutParams(params);
				            txt.setId(View.generateViewId());
				
				            ((TextView) txt).setTextSize(12);
				            ((TextView) txt).setSingleLine(true);
				            txt.setTag("%" + placeholderIndex + "$s");
				             ((TextView) txt).setContentDescription(i);
				
				            // Ensure blockValues entry exists
				            if (!blockValues.containsKey(_view)) {
					                blockValues.put(_view, new HashMap<>());
					            }
				            
				             // Show placeholder first, then value if available
				            if (valueArr.length > valueIndex && !valueArr[valueIndex].isEmpty()) {
					                 ((TextView) txt).setText(valueArr[valueIndex]); // Show saved value
					            } else {
					                 ((TextView) txt).setText(i); // Show placeholder if no value exists
					            }
				            valueIndex++;
				
				            // Add placeholder text in map
				            blockValues.get(_view).put(txt.getTag().toString(), "");
				
				            placeholderIndex++;
				
				            txt.setOnClickListener(new View.OnClickListener() {
					                @Override
					                public void onClick(View v) {
						                    try {
							                        if (!((LinearLayout) v.getParent()).getTag().toString().equals("blocks")) {
								                            _editdialogue(txt, "number");
								                        }
							                    } catch (Exception e) {
							                        _editdialogue(txt, "number");
							                    }
						                }
					            });
				
				        } else if (i.equals("%v.b")) {
				            boolen boolBlock = new boolen(MainActivity.this);
				            ((LinearLayout) _view).addView(boolBlock);
				
				            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				                LinearLayout.LayoutParams.WRAP_CONTENT,
				                LinearLayout.LayoutParams.WRAP_CONTENT
				            );
				            params.setMargins(0, 0, 16, 12);
				            boolBlock.setLayoutParams(params);
				
				        } else {
				            if (i.equals("%v.visibility")) {
					    ((LinearLayout) _view).addView(new TextView(MainActivity.this));
					
					    View txt = ((LinearLayout) _view).getChildAt(((LinearLayout) _view).getChildCount() - 1);
					
					    ((TextView) txt).setBackgroundColor(0xFFFFFFFF);
					    ((TextView) txt).setBackground(new GradientDrawable() {
						        public GradientDrawable getIns(int a, int b) {
							            this.setCornerRadius(a);
							            this.setColor(b);
							            return this;
							        }
						    }.getIns(90, 0xFFFFFFFF));
					
					    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					        120, 
					        LinearLayout.LayoutParams.WRAP_CONTENT
					    );
					    params.setMargins(0, 0, 8, 8);
					    
					    txt.setPadding(5, 2, 5, 2);
					    txt.setLayoutParams(params);
					    
					    ((TextView) txt).setTextSize(12);
					    ((TextView) txt).setSingleLine(true);
					     // Show placeholder first, then value if available
					            if (valueArr.length > valueIndex && !valueArr[valueIndex].isEmpty()) {
						                 ((TextView) txt).setText(valueArr[valueIndex]); // Show saved value
						            } else {
						                 ((TextView) txt).setText(i); // Show placeholder if no value exists
						            }
					            valueIndex++;
					    ((TextView) txt).setId(View.generateViewId());
					    
					    ((TextView) txt).setTag("%" + placeholderIndex + "$s");
					     ((TextView) txt).setContentDescription(i);
					    
					    // Ensure blockValues entry exists
					            if (!blockValues.containsKey(_view)) {
						                blockValues.put(_view, new HashMap<>());
						            }
					
					            // Add placeholder text in map
					            blockValues.get(_view).put(txt.getTag().toString(), "View."/*"Tap to enter"*/);
					
					            placeholderIndex++;
					
					    txt.setOnClickListener(new View.OnClickListener() {
						        @Override
						        public void onClick(View _view) {
							            showVisibilityDialog((TextView) txt);
							        }
						    });
				} else {
					    if (i.equals("%d.drawable")) { 
						        ((LinearLayout) _view).addView(new TextView(MainActivity.this));
						
						    View txt = ((LinearLayout) _view).getChildAt(((LinearLayout) _view).getChildCount() - 1);
						
						    ((TextView) txt).setBackgroundColor(0xFFFFFFFF);
						    ((TextView) txt).setBackground(new GradientDrawable() {
							        public GradientDrawable getIns(int a, int b) {
								            this.setCornerRadius(a);
								            this.setColor(b);
								            return this;
								        }
							    }.getIns(90, 0xFFFFFFFF));
						
						    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						        120, 
						        LinearLayout.LayoutParams.WRAP_CONTENT
						    );
						    params.setMargins(0, 0, 8, 8);
						    
						    txt.setPadding(5, 2, 5, 2);
						    txt.setLayoutParams(params);
						    
						    ((TextView) txt).setTextSize(12);
						    ((TextView) txt).setSingleLine(true);
						     // Show placeholder first, then value if available
						            if (valueArr.length > valueIndex && !valueArr[valueIndex].isEmpty()) {
							                 ((TextView) txt).setText(valueArr[valueIndex]); // Show saved value
							            } else {
							                 ((TextView) txt).setText(i); // Show placeholder if no value exists
							            }
						            valueIndex++;
						    ((TextView) txt).setId(View.generateViewId());
						    
						    ((TextView) txt).setTag("%" + placeholderIndex + "$s");
						     ((TextView) txt).setContentDescription(i);
						    
						    // Ensure blockValues entry exists
						            if (!blockValues.containsKey(_view)) {
							                blockValues.put(_view, new HashMap<>());
							            }
						
						            // Add placeholder text in map
						            blockValues.get(_view).put(txt.getTag().toString(), "R.drawable."/*"Tap to enter"*/);
						
						            placeholderIndex++;
						
						    txt.setOnClickListener(new View.OnClickListener() {
							        @Override
							        public void onClick(View _view) {
								            showDrawableSelectionDialog((TextView) txt);
								        }
							    });
						
						     } else {
						         if (i.equals("%l.transcriptmode")) { 
							             ((LinearLayout) _view).addView(new TextView(MainActivity.this));
							
							    View txt = ((LinearLayout) _view).getChildAt(((LinearLayout) _view).getChildCount() - 1);
							
							    ((TextView) txt).setBackgroundColor(0xFFFFFFFF);
							    ((TextView) txt).setBackground(new GradientDrawable() {
								        public GradientDrawable getIns(int a, int b) {
									            this.setCornerRadius(a);
									            this.setColor(b);
									            return this;
									        }
								    }.getIns(90, 0xFFFFFFFF));
							
							    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
							        120, 
							        LinearLayout.LayoutParams.WRAP_CONTENT
							    );
							    params.setMargins(0, 0, 8, 8);
							    
							    txt.setPadding(5, 2, 5, 2);
							    txt.setLayoutParams(params);
							    
							    ((TextView) txt).setTextSize(12);
							    ((TextView) txt).setSingleLine(true);
							     // Show placeholder first, then value if available
							            if (valueArr.length > valueIndex && !valueArr[valueIndex].isEmpty()) {
								                 ((TextView) txt).setText(valueArr[valueIndex]); // Show saved value
								            } else {
								                 ((TextView) txt).setText(i); // Show placeholder if no value exists
								            }
							            valueIndex++;
							    ((TextView) txt).setId(View.generateViewId());
							    
							    ((TextView) txt).setTag("%" + placeholderIndex + "$s");
							     ((TextView) txt).setContentDescription(i);
							    
							    // Ensure blockValues entry exists
							            if (!blockValues.containsKey(_view)) {
								                blockValues.put(_view, new HashMap<>());
								            }
							
							            // Add placeholder text in map
							            blockValues.get(_view).put(txt.getTag().toString(), "Listview."/*"Tap to enter"*/);
							
							            placeholderIndex++;
							
							    txt.setOnClickListener(new View.OnClickListener() {
								        @Override
								        public void onClick(View _view) {
									            showTranscriptDialog((TextView) txt);
									        }
								    });
							         } else {
							             if (i.equals("%v.font")) {  
								                  ((LinearLayout) _view).addView(new TextView(MainActivity.this));
								
								    View txt = ((LinearLayout) _view).getChildAt(((LinearLayout) _view).getChildCount() - 1);
								
								    ((TextView) txt).setBackgroundColor(0xFFFFFFFF);
								    ((TextView) txt).setBackground(new GradientDrawable() {
									        public GradientDrawable getIns(int a, int b) {
										            this.setCornerRadius(a);
										            this.setColor(b);
										            return this;
										        }
									    }.getIns(90, 0xFFFFFFFF));
								
								    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
								        120, 
								        LinearLayout.LayoutParams.WRAP_CONTENT
								    );
								    params.setMargins(0, 0, 8, 8);
								    
								    txt.setPadding(5, 2, 5, 2);
								    txt.setLayoutParams(params);
								    
								    ((TextView) txt).setTextSize(12);
								    ((TextView) txt).setSingleLine(true);
								     // Show placeholder first, then value if available
								            if (valueArr.length > valueIndex && !valueArr[valueIndex].isEmpty()) {
									                 ((TextView) txt).setText(valueArr[valueIndex]); // Show saved value
									            } else {
									                 ((TextView) txt).setText(i); // Show placeholder if no value exists
									            }
								            valueIndex++;
								    ((TextView) txt).setId(View.generateViewId());
								     ((TextView) txt).setContentDescription(i);
								    ((TextView) txt).setTag("%" + placeholderIndex + "$s");
								    
								    // Ensure blockValues entry exists
								            if (!blockValues.containsKey(_view)) {
									                blockValues.put(_view, new HashMap<>());
									            }
								
								            // Add placeholder text in map
								            blockValues.get(_view).put(txt.getTag().toString(), "Typeface.DEFAULT"/*"Tap to enter"*/);
								
								            placeholderIndex++;
								
								    txt.setOnClickListener(new View.OnClickListener() {
									        @Override
									        public void onClick(View _view) {
										            showFontSelectionDialog((TextView) txt);
										        }
									    });
								             } else {
								              if (i.equals("%v.typeface")) {    
									                  ((LinearLayout) _view).addView(new TextView(MainActivity.this));
									
									    View txt = ((LinearLayout) _view).getChildAt(((LinearLayout) _view).getChildCount() - 1);
									
									    ((TextView) txt).setBackgroundColor(0xFFFFFFFF);
									    ((TextView) txt).setBackground(new GradientDrawable() {
										        public GradientDrawable getIns(int a, int b) {
											            this.setCornerRadius(a);
											            this.setColor(b);
											            return this;
											        }
										    }.getIns(90, 0xFFFFFFFF));
									
									    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
									        120, 
									        LinearLayout.LayoutParams.WRAP_CONTENT
									    );
									    params.setMargins(0, 0, 8, 8);
									    
									    txt.setPadding(5, 2, 5, 2);
									    txt.setLayoutParams(params);
									    
									    ((TextView) txt).setTextSize(12);
									    ((TextView) txt).setSingleLine(true);
									     // Show placeholder first, then value if available
									            if (valueArr.length > valueIndex && !valueArr[valueIndex].isEmpty()) {
										                 ((TextView) txt).setText(valueArr[valueIndex]); // Show saved value
										            } else {
										                 ((TextView) txt).setText(i); // Show placeholder if no value exists
										            }
									            valueIndex++;
									    ((TextView) txt).setId(View.generateViewId());
									     ((TextView) txt).setContentDescription(i);
									    ((TextView) txt).setTag("%" + placeholderIndex + "$s");
									    
									    // Ensure blockValues entry exists
									            if (!blockValues.containsKey(_view)) {
										                blockValues.put(_view, new HashMap<>());
										            }
									
									            // Add placeholder text in map
									            blockValues.get(_view).put(txt.getTag().toString(), "0"/*"Tap to enter"*/);
									
									            placeholderIndex++;
									
									    txt.setOnClickListener(new View.OnClickListener() {
										        @Override
										        public void onClick(View _view) {
											            showTypeFaceSelectionDialog((TextView) txt);
											        }
										    });
									               } else {
									             if (i.equals("%v.view")) { 
										                 ((LinearLayout) _view).addView(new TextView(MainActivity.this));
										
										    View txt = ((LinearLayout) _view).getChildAt(((LinearLayout) _view).getChildCount() - 1);
										
										    ((TextView) txt).setBackgroundColor(0xFFFFFFFF);
										    ((TextView) txt).setBackground(new GradientDrawable() {
											        public GradientDrawable getIns(int a, int b) {
												            this.setCornerRadius(a);
												            this.setColor(b);
												            return this;
												        }
											    }.getIns(90, 0xFFFFFFFF));
										
										    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
										        120, 
										        LinearLayout.LayoutParams.WRAP_CONTENT
										    );
										    params.setMargins(0, 0, 8, 8);
										    
										    txt.setPadding(5, 2, 5, 2);
										    txt.setLayoutParams(params);
										    
										    ((TextView) txt).setTextSize(12);
										    ((TextView) txt).setSingleLine(true);
										     // Show placeholder first, then value if available
										            if (valueArr.length > valueIndex && !valueArr[valueIndex].isEmpty()) {
											                 ((TextView) txt).setText(valueArr[valueIndex]); // Show saved value
											            } else {
											                 ((TextView) txt).setText(i); // Show placeholder if no value exists
											            }
										            valueIndex++;
										    ((TextView) txt).setId(View.generateViewId());
										     ((TextView) txt).setContentDescription(i);
										    ((TextView) txt).setTag("%" + placeholderIndex + "$s");
										    
										    // Ensure blockValues entry exists
										            if (!blockValues.containsKey(_view)) {
											                blockValues.put(_view, new HashMap<>());
											            }
										
										            // Add placeholder text in map
										            blockValues.get(_view).put(txt.getTag().toString(), ""/*"Tap to enter"*/);
										
										            placeholderIndex++;
										
										    txt.setOnClickListener(new View.OnClickListener() {
											        @Override
											        public void onClick(View _view) {
												            showViewSelectionDialog((TextView) txt);
												        }
											    });
										             } else {
										            ((LinearLayout) _view).addView(new TextView(MainActivity.this));
										
										            View txt = ((LinearLayout) _view).getChildAt(((LinearLayout) _view).getChildCount() - 1);
										
										            ((TextView) txt).setText(i + " ");
										            ((TextView) txt).setTextColor(0xFFFFFFFF);
										            ((TextView) txt).setTypeface(Typeface.DEFAULT, Typeface.BOLD);
										            }
									            }
								            }
							         }
						       }     
					        }
				        
				    }
			 }
	}
	private void updateBlockPlaceholders(View block, String placeholder, String newValue) {
		    if (block.getTag() != null) {
			        String blockCode = block.getTag().toString();
			        blockCode = blockCode.replace(placeholder, newValue);
			        block.setTag(blockCode);
			        Log.d("Debug", "Updated Block Code: " + blockCode);
			    }
	}
	private List<String> extractPlaceholdersOnly(String content, String content2) {
		    List<String> placeholders = new ArrayList<>();
		    Pattern pattern = Pattern.compile("%(\\d+)\\$s"); // ✅ Match `%1$s`, `%2$s`, etc.
		
		    // ✅ Find placeholders in `content`
		    Matcher matcher = pattern.matcher(content);
		    while (matcher.find()) {
			        placeholders.add(matcher.group());
			    }
		
		    // ✅ Find placeholders in `content2` (if exists)
		    if (content2 != null) {
			        Matcher matcher2 = pattern.matcher(content2);
			        while (matcher2.find()) {
				            placeholders.add(matcher2.group());
				        }
			    }
		
		    Log.d("Debug", "Extracted Placeholders: " + placeholders);
		    return placeholders;
	}
	private Map<String, String> extractPlaceholderValues(String blockCode, int index) {
		    Map<String, String> placeholderValues = new HashMap<>();
		    Pattern pattern = Pattern.compile("%(\\d+)\\$s"); // ✅ Match `%1$s`, `%2$s`, etc.
		
		    Matcher matcher = pattern.matcher(blockCode);
		    while (matcher.find()) {
			        String placeholder = matcher.group(); // Example: `%1$s`
			        String valueKey = "value" + index;  // Example: `"value1"`
			
			        // ✅ Ensure value is converted to String
			        if (list.get(index).containsKey(valueKey)) {
				            String value = String.valueOf(list.get(index).get(valueKey)); // 🔥 Fix: Convert Object to String
				            placeholderValues.put(placeholder, value);
				        } else {
				            placeholderValues.put(placeholder, "MISSING_VALUE"); // ✅ Default if not found
				        }
			    }
		
		    Log.d("Debug", "Extracted Placeholder Values: " + placeholderValues);
		    return placeholderValues;
	}
	
	// ✅ Get block color (Fix multiple blocks issue)
	
	// ✅ Apply block color properly (Fix multiple blocks issue)
	private void applyBlockColor(View block, String colourString) {
		    try {
			        if (colourString != null && !colourString.isEmpty()) {
				            int colour = Color.parseColor(colourString);
				            GradientDrawable drawable = new GradientDrawable();
				            drawable.setColor(colour);
				            block.setBackground(drawable);
				            block.invalidate();
				        }
			    } catch (Exception e) {
			        Log.e("ColorError", "Error applying color", e);
			    }
	}
	
	private String getBlockColor(View view) {
		    if (view.getBackground() instanceof ColorDrawable) {
			        int color = ((ColorDrawable) view.getBackground()).getColor();
			        return String.format("#%06X", (0xFFFFFF & color));
			    } else {
			        return "#FB8C00";  // Default color
			    }
	}
	
	private View createBlockFromData(Map<String, String> blockData) {
		    View block;
		    if ("regular".equals(blockData.get("type"))) {
			        block = new regular(this);
			    } else {
			        block = new if_block(this);
			    }
		
		    // ✅ Use `list.get(i).get("content")` instead of `getBlockContent(view)`
		    _addViewsTo(block, blockData.getOrDefault("content", ""), blockData.getOrDefault("code", ""),"");
		
		    // ✅ Restore placeholders (Editable text inside blocks)
		    if (blockData.containsKey("placeholders")) {
			        Map<String, String> restoredPlaceholders = gson.fromJson(blockData.get("placeholders"), new TypeToken<Map<String, String>>() {}.getType());
			        blockValues.put(block, restoredPlaceholders);
			    }
		
		    // ✅ Restore color
		    if (blockData.containsKey("colour")) {
			        try {
				            int colour = Color.parseColor("#" + blockData.get("colour"));
				            block.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
				            block.invalidate();
				        } catch (Exception e) {
				            Log.e("ColorError", "Error applying color", e);
				        }
			    }
		
		    // ✅ Restore "content2" if applicable
		    if (block instanceof if_block && blockData.containsKey("content2")) {
			        if_block ifb = (if_block) block;
			        _addViewsTo(ifb.middle, blockData.get("content2"), blockData.getOrDefault("code", ""), "");
			    }
		
		    // ✅ Load child blocks if they exist
		    if (blockData.containsKey("children")) {
			        List<Map<String, String>> childBlocks = gson.fromJson(blockData.get("children"), new TypeToken<List<Map<String, String>>>() {}.getType());
			        for (Map<String, String> childData : childBlocks) {
				            View childBlock = createBlockFromData(childData);
				            if (childBlock != null) {
					                ((ViewGroup) block).addView(childBlock);
					            }
				        }
			    }
		
		    return block;
	}
	private List<Map<String, String>> getBlockInputValuesq(View block) {
		    List<Map<String, String>> values = new ArrayList<>();
		    if (block instanceof ViewGroup) {
			        ViewGroup group = (ViewGroup) block;
			        for (int i = 0; i < group.getChildCount(); i++) {
				            View child = group.getChildAt(i);
				            if (child instanceof TextView) {
					                Map<String, String> valueMap = new HashMap<>();
					                valueMap.put("tag", String.valueOf(child.getTag())); // ✅ Store original tag (%e.s, %e.n, etc.)
					                valueMap.put("value", ((TextView) child).getText().toString()); // ✅ Store actual text
					                values.add(valueMap);
					            }
				        }
			    }
		    return values;
	}
	private void restoreBlockInputsq(View block, List<Map<String, String>> values) {
		    if (block instanceof ViewGroup) {
			        ViewGroup group = (ViewGroup) block;
			        int valueIndex = 0;
			        for (int i = 0; i < group.getChildCount(); i++) {
				            View child = group.getChildAt(i);
				            if (child instanceof TextView && valueIndex < values.size()) {
					                Map<String, String> valueMap = values.get(valueIndex);
					                ((TextView) child).setText(valueMap.get("value")); // ✅ Restore saved text
					                child.setTag(valueMap.get("tag")); // ✅ Restore original tag (%e.s, %e.n, etc.)
					                valueIndex++;
					            }
				        }
			    }
	}
	
	private void saveBlocks(String... contents) {
		    List<Map<String, String>> blockList = new ArrayList<>();
		
		    // ✅ Load existing dataMap
		    if (s.contains("data")) {
			        dataMap = gson.fromJson(s.getString("data", ""), type);
			    }
		
		    // ✅ Clear `list` before adding new blocks
		    list.clear();
		
		    // ✅ Convert `List<Map<String, String>>` to `List<HashMap<String, Object>>`
		    if (dataMap.containsKey(liststring.get((int) n))) {
			        List<Map<String, String>> tempList = dataMap.get(liststring.get((int) n));
			
			        for (Map<String, String> map : tempList) {
				            HashMap<String, Object> hashMap = new HashMap<>(map);
				            list.add(hashMap);
				        }
			    }
		
		    // ✅ Loop through **ALL CHILDREN** in `main`
		    for (int i = 0; i < main.getChildCount(); i++) {
			        View view = main.getChildAt(i);
			        if (view instanceof regular || view instanceof if_block) {
				            Map<String, String> blockData = new HashMap<>();
				            blockData.put("type", view instanceof regular ? "regular" : "if_block");
				
				            // ✅ Store **unique block data**
				            blockData.put("block_data", getBlockContent(view));
				
				            // ✅ Store **individual content for each block**
				          /*  if (i < list.size() && list.get(i).containsKey("content")) {
                blockData.put("content", list.get(i).get("content").toString());
            } else {
                blockData.put("content", "");  // ✅ Default empty content (Avoid null errors)
            }*/
				            // ✅ Extract text from child views (like EditText/TextView)
				List<Map<String, String>> inputValues = getBlockInputValuesq(view);
				//blockData.put("content", gson.toJson(inputValues)); // ✅ Store JSON string
				    
				
				
				            // ✅ Store `content_secondary` for `if_block`
				            if (view instanceof if_block) {
					                if_block ifb = (if_block) view;
					                blockData.put("content_secondary", ifb.middle.getTag() != null ? ifb.middle.getTag().toString() : "");
					            }
				            
				            if (view instanceof if_block) {
					            if_block ifb = (if_block) view;
					        //    blockData.put("type", "if");
					           // blockData.put("content", content);
					
					            // 🔥 Save content in Map
					           // ifBlockData.put(ifb.getId(), ifb.getTag(ifb.getId()).toString());
					        } 
				        
				        if (view instanceof regular) {
					            regular r = (regular) view;
					        //    blockData.put("type", "if");
					           // blockData.put("content", r.getTag(r.getId()).toString());
					
					            // 🔥 Save content in Map
					           // ifBlockData.put(ifb.getId(), ifb.getTag(ifb.getId()).toString());
					        } 
				
				            // ✅ Store **unique block position**
				            blockData.put("position", String.valueOf(i));
				            for (int j = 0; j < contents.length; j++) {
					                blockData.put("content" + j, contents[j]); // 🔥 Save as content0, content1, content2...
					            }
				
				            // ✅ Store code for **each block**
				            blockData.put("code", view.getTag() != null ? view.getTag().toString() : "");
				
				            // ✅ Store **color for each block**
				            if (i < list.size() && list.get(i).containsKey("colour")) {
					                blockData.put("colour", list.get(i).get("colour").toString());
					            } else {
					                blockData.put("colour", "FB8C00");  // ✅ Default empty content (Avoid null errors)
					            }
				
				            // ✅ Store **placeholders for each block**
				            if (blockValues.containsKey(view)) {
					                Map<String, String> placeholders = blockValues.get(view);
					                blockData.put("placeholders", gson.toJson(placeholders));
					            }
				
				            // ✅ **Add block to the list (Fix multiple blocks issue)**
				            blockList.add(blockData);
				        }
			    }
		
		    // ✅ Save ALL blocks data in SharedPreferences
		    s.edit().putString("saved_blocks", gson.toJson(blockList)).apply();
	}
	
	
	private void loadBlocksq() {
		    main.removeAllViews();
		    String json = s.getString("saved_blocks", "");
		
		    if (!json.isEmpty()) {
			        try {
				            Type listType = new TypeToken<List<Map<String, String>>>() {}.getType();
				            List<Map<String, String>> blockList = gson.fromJson(json, listType);
				
				            for (Map<String, String> blockData : blockList) {
					                View block;
					
					                if ("regular".equals(blockData.get("type"))) {
						                    block = new regular(this);
						                } else {
						                    block = new if_block(this);
						                }
					
					                // ✅ Retrieve multiple content fields
					                List<String> contents = new ArrayList<>();
					                for (int j = 0; blockData.containsKey("content" + j); j++) {
						                    contents.add(blockData.get("content" + j));
						                }
					
					                // ✅ Restore each content separately
					                for (String content : contents) {
						                    _addViewsTo(block, content, blockData.getOrDefault("code", ""), "");
						                }
					
					                if (block instanceof if_block) {
						                    if_block ifb = (if_block) block;
						                    int colour = Color.parseColor("#" + blockData.getOrDefault("colour", "FB8C00"));
						                    ifb.top.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
						                    ifb.center.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
						                    ifb.bottom.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
						                    ifb.top.invalidate();
						                    ifb.center.invalidate();
						                    ifb.bottom.invalidate();
						                }
					
					                main.addView(block);
					            }
				        } catch (Exception e) {
				            Log.e("LoadBlocks", "Error loading blocks", e);
				        }
			    }
	}
	
	/**
 * Loads saved blocks for the specified event name and adds them to the main view.
 * 
 * @param eventName The name of the event whose blocks should be loaded.
 * @throws IOException If an error occurs while reading the blocks file.
 * @throws JsonSyntaxException If the JSON format is invalid.
 * @gets Retrieves saved block data from the JSON file and populates the UI.
 */
	private void loadBlocks(@NonNull String eventName) {
		    main.removeAllViews();
		    File file = new File(Environment.getExternalStorageDirectory(), "app/blocks.json");
		
		    if (!file.exists()) {
			        Log.e("LoadError", "No saved blocks found.");
			        return;
			    }
		
		    try (FileReader reader = new FileReader(file)) {
			        Type type = new TypeToken<Map<String, List<Map<String, String>>>>() {}.getType();
			        Map<String, List<Map<String, String>>> allEventsData = gson.fromJson(reader, type);
			
			        if (allEventsData == null) {
				            Log.e("LoadBlocks", "Failed to parse blocks.json.");
				            return;
				        }
			
			        List<Map<String, String>> savedBlocks = allEventsData.getOrDefault(eventName, new ArrayList<>());
			
			        blockContentMap.clear();
			        blockValues.clear();
			
			        for (@NonNull Map<String, String> data : savedBlocks) {
				            @Nullable String typeValue = data.get("type");
				            @Nullable String content = data.get("content");
				            @Nullable String blockCode = data.get("blockCode");
				            @Nullable String colorCode = data.getOrDefault("color", "FB8C00"); // Default color
				
				            if (typeValue == null || blockCode == null || content == null) {
					                Log.e("LoadBlocks", "Skipping invalid block data: " + data);
					                showMessage("Skipping invalid block data: " + data);
					                ((android.content.ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE))
					                        .setPrimaryClip(ClipData.newPlainText("errorlog", "Skipping invalid block data: " + data));
					                continue;
					            }
				
				            // **Fix: Ensure placeholders are properly replaced**
				         /*   for (Map.Entry<String, String> entry : data.entrySet()) {
    if (entry.getKey().startsWith("%") && entry.getValue() != null && blockCode != null) {
        blockCode = blockCode.replace(entry.getKey(), entry.getValue());
    }
}
*/
				
				            @NonNull View block;
				            if ("regular".equals(typeValue)) {
					                block = new regular(this);
					            } else if ("if_block".equals(typeValue)) {
					                block = new if_block(this);
					            } else {
					                continue;
					            }
				
				            int blockId = View.generateViewId();
				            block.setId(blockId);
				
				            blockValues.putIfAbsent(block, new HashMap<>());
				
				            // ✅ Fix: Ensure blockValues stores placeholders correctly
				         /*   for (Map.Entry<String, String> entry : data.entrySet()) {
                if (entry.getKey().startsWith("%")) {
                    blockValues.get(block).put(entry.getKey(), entry.getValue());
                }
            }*/
				
				            // ✅ Restore placeholders in `_addViewsTo`
				            _addViewsTo(block, content, blockCode, "");
				
				            // ✅ Restore block color
				            restoreBlockColor(block, colorCode);
				
				            main.addView(block);
				            blockContentMap.put(blockId, data);
				        }
			
			    } catch (Exception e) {
			        Log.e("LoadBlocks", "Error loading blocks", e);
			    }
	}
	
	{
	}
	
	
	public void _init() {
	}
	private Stack<List<Map<String, String>>> undoStack = new Stack<>();
	private Stack<List<Map<String, String>>> redoStack = new Stack<>();
	private ImageView undoImage, redoImage;
	private Map<Integer, Map<String, String>> blockContentMap = new HashMap<>();
	private void saveState() {
		    if (main.getChildCount() > 0) {
			        List<Map<String, String>> currentState = new ArrayList<>();
			
			        for (int i = 0; i < main.getChildCount(); i++) {
				            View view = main.getChildAt(i);
				            if (view instanceof regular || view instanceof if_block) {
					                Map<String, String> blockData = new HashMap<>();
					                blockData.put("type", view instanceof regular ? "regular" : "if_block");
					
					                // ✅ Store `block_data` separately
					                String block_data = getBlockContent(view);
					                blockData.put("block_data", block_data);
					
					                // ✅ Store `content` from the list
					                String content = list.get(i).get("content").toString();
					                blockData.put("content", content);
					
					                // ✅ Store `content_secondary`
					                if (view instanceof if_block) {
						                    if_block ifb = (if_block) view;
						                    blockData.put("content_secondary", ifb.middle.getTag() != null ? ifb.middle.getTag().toString() : "");
						                }
					
					                // ✅ Store color
					                Drawable background = view.getBackground();
					                if (background instanceof ColorDrawable) {
						                    int color = ((ColorDrawable) background).getColor();
						                    blockData.put("colour", String.format("#%06X", (0xFFFFFF & color)));
						                } else {
						                    blockData.put("colour", "#FB8C00");
						                }
					
					                // ✅ Store placeholders
					                if (blockValues.containsKey(view)) {
						                    for (Map.Entry<String, String> entry : blockValues.get(view).entrySet()) {
							                        blockData.put(entry.getKey(), entry.getValue());
							                    }
						                }
					
					                currentState.add(blockData);
					            }
				        }
			
			        undoStack.push(currentState);
			        redoStack.clear();
			        invalidateOptionsMenu(); 
			        updateUndoRedoUI();
			    }
	}
	private void restoreBlocks(List<Map<String, String>> blocks) {
		    main.removeAllViews();
		
		    for (Map<String, String> blockData : blocks) {
			        View block;
			        if ("regular".equals(blockData.get("type"))) {
				            block = new regular(this);
				        } else {
				            block = new if_block(this);
				        }
			
			        // Use _addViewsTo to restore placeholders
			        _addViewsTo(block, blockData.get("content"), blockData.get("code"), "");
			
			        // Restore color
			        try {
				            String colours = blockData.get("colour");
				            int colour = Color.parseColor(colours);
				            block.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
				            block.invalidate();
				        } catch (Exception e) {
				            Log.e("RestoreBlocks", "Error parsing color", e);
				        }
			
			        main.addView(block);
			    }
	}
	private void updateUndoRedoUI() {
		    undoImage.setColorFilter(undoStack.isEmpty() ? Color.GRAY : Color.BLACK);
		    redoImage.setColorFilter(redoStack.isEmpty() ? Color.GRAY : Color.BLACK);
	}
	
	private void redo() {
		    if (!redoStack.isEmpty()) {
			        undoStack.push(new ArrayList<>(redoStack.pop())); // Save current state for undo
			        restoreBlocks(undoStack.peek());
			        updateUndoRedoUI();
			        invalidateOptionsMenu(); 
			    }
	}
	
	private void undo() {
		    if (!undoStack.isEmpty()) {
			        redoStack.push(new ArrayList<>(undoStack.pop())); // Save current state for redo
			        restoreBlocks(undoStack.isEmpty() ? new ArrayList<>() : undoStack.peek());
			        updateUndoRedoUI();
			        invalidateOptionsMenu(); 
			    }
	}
	
	class variable_block extends TextView implements View.OnDragListener {
		
		    public variable_block(Context ctx) {
			        super(ctx);
			        this.setBackgroundResource(R.drawable.var_block);
			        this.getBackground().setColorFilter(0xFF7E57C2, PorterDuff.Mode.MULTIPLY);
			        this.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			        this.setPadding(8, 16, 8, 16);
			        this.setLayoutParams(new LinearLayout.LayoutParams(
			                LinearLayout.LayoutParams.WRAP_CONTENT,
			                LinearLayout.LayoutParams.WRAP_CONTENT
			        ));
			
			        this.setText("");  // Default Variable Name
			        this.setTextSize(12);
			        this.setPadding(8, 4, 8, 4);
			        this.setBackgroundColor(0xFFFFFFFF);
			        this.setTextColor(Color.BLACK);
			        this.setSingleLine(true);
			
			        // 🔥 Click Listener to Edit Variable Name
			        this.setOnClickListener(new View.OnClickListener() {
				            @Override
				            public void onClick(View _view) {
					                _editdialogue(variable_block.this, "string");  // Open Edit Dialog
					            }
				        });
			
			        // 🔥 Drag & Drop Support (Only for %e.s Blocks)
			        this.setOnLongClickListener(new View.OnLongClickListener() {
				            @Override
				            public boolean onLongClick(View _view) {
					                ClipData.Item item = new ClipData.Item("string");  // Mark as variable type
					                ClipData data = new ClipData("variable", new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
					                DragShadowBuilder shadow = new View.DragShadowBuilder(_view);
					                
					                if (Build.VERSION.SDK_INT >= 24) {
						                    _view.startDragAndDrop(data, shadow, _view, 1);
						                } else {
						                    _view.startDrag(data, shadow, _view, 1);
						                }
					                return true;
					            }
				        });
			
			        this.setTag("string");
			        this.setOnDragListener(this);  // Implementing DragListener directly
			    }
		
		    // 🔥 Drag Event Handler
		    @Override
		    public boolean onDrag(View v, DragEvent event) {
			        switch (event.getAction()) {
				            case DragEvent.ACTION_DRAG_STARTED:
				                return event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);
				
				            case DragEvent.ACTION_DROP:
				                if ("%e.s".equals(event.getClipData().getItemAt(0).getText().toString())) {
					                    this.setText(event.getClipData().getItemAt(0).getText().toString());  // Set dragged value
					                    return true;
					                }
				                return false;
				
				            default:
				                return false;
				        }
			    }
	}
	
	{
	}
	
	
	public void _features() {
		/**
 * Project: Open Source Block-Based Programming System  
 *  
 * Description:  
 * This project is designed to provide an intuitive, drag-and-drop block-based  
 * programming environment. It enables developers, especially beginners and  
 * those with limited resources, to create applications visually without  
 * needing deep coding knowledge. The system supports real-time source code  
 * generation from blocks, making coding more accessible and efficient.  
 *  
 * New Additions (24-02-2025):  
 * - Added placeholders in blocks for better customization.  
 * - Example: `%v.visibility` can now be used to set visibility states such as `GONE`, `VISIBLE`, `INVISIBLE`.  
 *  
 * Features:  
 * - Visual block-based coding interface  
 * - Real-time source code generation  
 * - Open-source and free to modify  
 * - Designed for beginners and experienced developers alike  
 * - Lightweight, efficient, and highly scalable  
 *  
 * @author NexusTeam & SmartIndiaGaming  
 * @created 23-02-2025  
 * @license Open Source - Free to Modify & Distribute  
 * @origin Made in India 🇮🇳 | Empowering Developers Globally  
 * @note Developed to make coding accessible to all.  
 */
		
	}
	private void showVisibilityDialog(TextView txt) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		    builder.setTitle("Select Visibility");
		
		    String[] options = {"VISIBLE", "INVISIBLE", "GONE"};
		    builder.setItems(options, new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
				            String selectedValue = options[which];
				            txt.setText(selectedValue); // Update text with selected visibility
				
				            // Ensure blockValues entry exists
				            View parentBlock = (View) txt.getParent();
				            if (!blockValues.containsKey(parentBlock)) {
					                blockValues.put(parentBlock, new HashMap<>());
					            }
				
				            // Store the selected value in blockValues
				            blockValues.get(parentBlock).put(txt.getTag().toString(), "View." + selectedValue);
				        }
			    });
		
		    builder.setNegativeButton("Cancel", null);
		    builder.show();
	}
	private void showDrawableSelectionDialog(TextView txt) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		    builder.setTitle("Select Drawable");
		
		    // Get drawable files from /custom_folder
		    List<String> drawablePaths = getDrawableFiles();
		    List<String> displayNames = new ArrayList<>();
		
		    // Create a mapping of displayed names to actual paths
		    Map<String, String> nameToPathMap = new HashMap<>();
		
		    // Add "None" option
		    displayNames.add("None");
		    nameToPathMap.put("None", "R.drawable."); // None = null path
		
		    for (String path : drawablePaths) {
			        if (path != null) {
				            String name = removeFileExtension(new File(path).getName());
				            displayNames.add(name);
				            nameToPathMap.put(name, path); // Map name to path correctly
				        }
			    }
		
		    builder.setItems(displayNames.toArray(new String[0]), new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
				            String selectedName = displayNames.get(which);
				            String selectedPath = nameToPathMap.get(selectedName);
				
				            if (selectedPath == null) {
					                // None selected
					                txt.setText("None");
					              //  txt.setBackground(null);
					            } else {
					                // Set drawable
					             //   txt.setBackground(Drawable.createFromPath(selectedPath));
					                txt.setText(selectedName);
					            }
				
				            // Store path in blockValues
				            View parentBlock = (View) txt.getParent();
				            if (!blockValues.containsKey(parentBlock)) {
					                blockValues.put(parentBlock, new HashMap<>());
					            }
				            blockValues.get(parentBlock).put(txt.getTag().toString(), 
				                selectedPath == null ? "R.drawable." : "R.drawable." + selectedName);
				        }
			    });
		
		    builder.setNegativeButton("Cancel", null);
		    builder.show();
	}
	private void showTranscriptDialog(TextView txt) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		    builder.setTitle("Select transcript mode");
		
		    String[] options = {"TRANSCRIPT_MODE_NORMAL", "TRANSCRIPT_MODE_DISABLED", "TRANSCRIPT_MODE_ALWAYS_SCROLL"};
		    builder.setItems(options, new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
				            String selectedValue = options[which];
				            txt.setText(selectedValue); // Update text with selected visibility
				
				            // Ensure blockValues entry exists
				            View parentBlock = (View) txt.getParent();
				            if (!blockValues.containsKey(parentBlock)) {
					                blockValues.put(parentBlock, new HashMap<>());
					            }
				
				            // Store the selected value in blockValues
				            blockValues.get(parentBlock).put(txt.getTag().toString(), "ListView." + selectedValue);
				        }
			    });
		
		    builder.setNegativeButton("Cancel", null);
		    builder.show();
	}
	private void showFontSelectionDialog(TextView txt) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		    builder.setTitle("Select Font");
		
		    // Get font files from /custom_folder
		    List<String> fontPaths = getFontFiles();
		    List<String> displayNames = new ArrayList<>();
		
		    // Create a mapping of displayed names to actual paths
		    Map<String, String> nameToPathMap = new HashMap<>();
		
		    // Add "None" option
		    displayNames.add("None");
		    nameToPathMap.put("NONE", "Typeface.DEFAULT"); // None = Typeface.DEFAULT
		
		    for (String path : fontPaths) {
			        if (path != null) {
				            String name = removeFontExtension(new File(path).getName());
				            displayNames.add(name);
				            nameToPathMap.put(name, path); // Map name to path correctly
				        }
			    }
		
		    builder.setItems(displayNames.toArray(new String[0]), new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
				            String selectedName = displayNames.get(which);
				            String selectedPath = nameToPathMap.get(selectedName);
				
				            if (selectedPath == null) {
					                // None selected
					                txt.setText("None");
					            } else {
					                // Set selected font
					                txt.setText(selectedName);
					            }
				
				            // Store path in blockValues
				            View parentBlock = (View) txt.getParent();
				            if (!blockValues.containsKey(parentBlock)) {
					                blockValues.put(parentBlock, new HashMap<>());
					            }
				
				            // Format the selected font path correctly
				            String formattedFontPath = selectedPath == null ? "Typeface.DEFAULT" : "\"fonts/" + selectedName + ".ttf\"";
				            blockValues.get(parentBlock).put(txt.getTag().toString(), formattedFontPath);
				        }
			    });
		
		    builder.setNegativeButton("Cancel", null);
		    builder.show();
	}
	
	private void showTypeFaceSelectionDialog(TextView txt) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		    builder.setTitle("Select Typeface");
		
		    String[] options = {"normal", "bold", "italic", "bold|italic"};
		    builder.setItems(options, new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
				            txt.setText(options[which]); // Show selected text
				
				            // Ensure blockValues entry exists
				            View parentBlock = (View) txt.getParent();
				            if (!blockValues.containsKey(parentBlock)) {
					                blockValues.put(parentBlock, new HashMap<>());
					            }
				
				            // Store the selected index in blockValues instead of text
				            blockValues.get(parentBlock).put(txt.getTag().toString(), String.valueOf(which));
				        }
			    });
		
		    builder.setNegativeButton("Cancel", null);
		    builder.show();
	}
	private void showViewSelectionDialog(TextView txt) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		    builder.setTitle("Select view");
		    
		    /**
    suggesting to use shapun layout editor and enjoy with this feature. 
    @support NexusTeam & SmartIndiaGaming.
    total present YouTube subscribers is 481. 
    **/
		
		    String[] options = {"view1", "view2", "view3"};
		    builder.setItems(options, new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
				            String selectedValue = options[which];
				            txt.setText(selectedValue); // Update text with selected visibility
				
				            // Ensure blockValues entry exists
				            View parentBlock = (View) txt.getParent();
				            if (!blockValues.containsKey(parentBlock)) {
					                blockValues.put(parentBlock, new HashMap<>());
					            }
				
				            // Store the selected value in blockValues
				            blockValues.get(parentBlock).put(txt.getTag().toString(), selectedValue);
				        }
			    });
		
		    builder.setNegativeButton("Cancel", null);
		    builder.show();
	}
	{
	}
	
	
	public void _helper() {
	}
	private String removeFileExtension(String fileName) {
		    return fileName.replaceAll("\\.(png|jpg|jpeg|webp|bmp|gif|svg|ico|tiff|tif)$", "");
	}
	private List<String> getDrawableFiles() {
		    List<String> files = new ArrayList<>();
		    //select drawable path
		    String folderPath = "/storage/emulated/0/custom_folder/";
		
		    File dir = new File(folderPath);
		    if (dir.exists() && dir.isDirectory()) {
			        File[] fileList = dir.listFiles((dir1, name) -> 
			            name.matches(".*\\.(png|jpg|jpeg|webp|bmp|gif|svg|ico|tiff|tif)$")
			        );
			
			        if (fileList != null) {
				            for (File file : fileList) {
					                files.add(file.getAbsolutePath());
					            }
				        }
			    }
		
		    return files;
	}
	private String removeFontExtension(String fileName) {
		    return fileName.replaceAll("\\.(ttf|otf|woff|woff2|eot)$", "");
	}
	private List<String> getFontFiles() {
		    List<String> files = new ArrayList<>();
		    // Select font path
		    String folderPath = "/storage/emulated/0/custom_folder/";
		
		    File dir = new File(folderPath);
		    if (dir.exists() && dir.isDirectory()) {
			        File[] fileList = dir.listFiles((dir1, name) -> 
			            name.matches(".*\\.(ttf|otf|woff|woff2|eot)$")
			        );
			
			        if (fileList != null) {
				            for (File file : fileList) {
					                files.add(file.getAbsolutePath());
					            }
				        }
			    }
		
		    return files;
	}
	// Extract all child blocks recursively
	private List<Map<String, String>> getChildBlocks(ViewGroup parent) {
		    List<Map<String, String>> childBlocks = new ArrayList<>();
		
		    for (int i = 0; i < parent.getChildCount(); i++) {
			        View child = parent.getChildAt(i);
			        if (child instanceof regular || child instanceof if_block) {
				            Map<String, String> childData = new HashMap<>();
				            childData.put("type", child instanceof regular ? "regular" : "if_block");
				            childData.put("content", getBlockContent(child));
				            childData.put("code", child.getTag() != null ? child.getTag().toString() : "");
				
				            if (blockValues.containsKey(child)) {
					                childData.put("placeholders", gson.toJson(blockValues.get(child)));
					            }
				
				            if (child instanceof ViewGroup) {
					                childData.put("children", gson.toJson(getChildBlocks((ViewGroup) child)));
					            }
				
				            childBlocks.add(childData);
				        }
			    }
		    return childBlocks;
	}
	/**
 * Saves block data to a JSON file while preserving all other event data.
 *
 * @param eventName The name of the event (e.g., "onCreate").
 * @param newBlocks A list of new block data to be saved.
 */
	private void saveBlocksToFile(@NonNull String eventName, @NonNull List<Map<String, String>> newBlocks) {
		    try {
			        File dir = new File(Environment.getExternalStorageDirectory(), "app");
			        if (!dir.exists() && !dir.mkdirs()) {
				            showToast("Failed to create directory");
				            return;
				        }
			
			        File file = new File(dir, "blocks.json");
			        Map<String, List<Map<String, String>>> allEventsData = new HashMap<>();
			
			        // ✅ Load existing event data but do NOT load current event's old data
			        if (file.exists()) {
				            try (FileReader reader = new FileReader(file)) {
					                Type type = new TypeToken<Map<String, List<Map<String, String>>>>() {}.getType();
					                allEventsData = gson.fromJson(reader, type);
					                if (allEventsData == null) allEventsData = new HashMap<>();
					            }
				        }
			
			        // ✅ Directly replace only the current event's data
			        allEventsData.put(eventName, newBlocks);
			
			        // ✅ Save updated data to file
			        try (FileWriter writer = new FileWriter(file, false)) {
				            gson.toJson(allEventsData, writer);
				            showToast("Blocks saved successfully!");
				        }
			    } catch (Exception e) {
			        Log.e("SaveError", "Failed to save blocks", e);
			    }
	}
	
	private void replacePlaceholderText(ViewGroup parentBlock, String placeholder, String value) {
		    for (int i = 0; i < parentBlock.getChildCount(); i++) {
			        View child = parentBlock.getChildAt(i);
			
			        if (child instanceof TextView && placeholder.equals(child.getTag())) {
				            ((TextView) child).setText(value); // ✅ Placeholder ko actual value se replace karo
				        } else if (child instanceof ViewGroup) {
				            replacePlaceholderText((ViewGroup) child, placeholder, value); // ✅ Nested views ke liye recursion
				        }
			    }
	}
	private void restoreBlockColor(@NonNull View block, @Nullable String colorCode) {
		    try {
			        if (colorCode != null && !colorCode.isEmpty()) {
				            int color = Color.parseColor("#" + colorCode);
				            if (block instanceof regular) {
					                block.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
					            } else if (block instanceof if_block) {
					                if_block ifb = (if_block) block;
					                ifb.top.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
					                ifb.center.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
					                ifb.bottom.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
					                ifb.middle.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
					                ifb.center2.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
					            }
				        }
			    } catch (Exception e) {
			        Log.e("ColorError", "Error applying color", e);
			    }
	}
	{
	}
	
	
	public void _implementation() {
	} 
	private void saveLogic() {
		    List<Map<String, String>> storedBlocks = new ArrayList<>();
		
		    for (int i = 0; i < main.getChildCount(); i++) {
			        View child = main.getChildAt(i);
			        if (child instanceof regular) {
				            Map<String, String> blockData = new HashMap<>();
				            blockData.put("type", "regular");
				
				            StringBuilder content = new StringBuilder();
				            StringBuilder values = new StringBuilder();
				
				            for (int j = 0; j < ((LinearLayout) child).getChildCount(); j++) {
					                View subChild = ((LinearLayout) child).getChildAt(j);
					
					                if (subChild.getContentDescription() != null) {
						                    content.append(subChild.getContentDescription().toString()).append(" ");
						                    values.append(((TextView) subChild).getText().toString()).append(";");
						                } else if (subChild instanceof TextView) {
						                    content.append(((TextView) subChild).getText().toString()).append(" ");
						                }
					            }
				
				            String blockCode = getBlockContent(child);
				            String cleanContent = content.toString().replaceAll("\\s+", " ").trim();
				            blockData.put("code", child.getTag() != null ? child.getTag().toString() : "");
				            blockData.put("blockCode", blockCode);
				            blockData.put("content", cleanContent);
				            blockData.put("values", values.toString().trim());
				
				            // ✅ Safe width-height measurement
				            int DEFAULT_WIDTH = 300;
				            int DEFAULT_HEIGHT = 100;
				
				            child.measure(
				                View.MeasureSpec.makeMeasureSpec(main.getWidth(), View.MeasureSpec.AT_MOST),
				                View.MeasureSpec.makeMeasureSpec(main.getHeight(), View.MeasureSpec.AT_MOST)
				            );
				
				            int width = child.getMeasuredWidth() > 0 ? child.getMeasuredWidth() : DEFAULT_WIDTH;
				            int height = child.getMeasuredHeight() > 0 ? child.getMeasuredHeight() : DEFAULT_HEIGHT;
				
				            blockData.put("width", String.valueOf(width));
				            blockData.put("height", String.valueOf(height));
				
				            // ✅ Background color copy
				            try {
					                Drawable background = child.getBackground();
					                if (background instanceof ColorDrawable) {
						                    int color = ((ColorDrawable) background).getColor();
						                    blockData.put("colour", String.format("#%06X", (0xFFFFFF & color)));
						                } else {
						                    blockData.put("colour", "#FB8C00"); // Default color
						                }
					            } catch (Exception e) {
					                showMessage(e.toString());
					            }
				
				            storedBlocks.add(blockData);
				        }
			    }
		
		    Map<String, Object> dataMap = new HashMap<>();
		    dataMap.put(EVENT_NAME, storedBlocks);
		    StorageUtils.saveData(dataMap);
	}
	private void loadLogic() {
		    Map<String, Object> dataMap = StorageUtils.loadData();
		    if (dataMap.containsKey(EVENT_NAME)) {
			        List<Map<String, String>> storedBlocks = (List<Map<String, String>>) dataMap.get(EVENT_NAME);
			        main.removeAllViews(); // ✅ Remove previous views to prevent duplicates
			
			        for (Map<String, String> block : storedBlocks) {
				            String content = block.getOrDefault("content", "");
				            String blockCode = block.getOrDefault("code", "");
				            int width = Integer.parseInt(block.getOrDefault("width", "300"));
				            int height = Integer.parseInt(block.getOrDefault("height", "100"));
				            String colorCode = block.getOrDefault("colour", "#FB8C00"); // Default color
				
				            if ("regular".equals(block.get("type"))) {
					                regular r = new regular(MainActivity.this);
					                
					                int blockId = View.generateViewId();
					                r.setId(blockId);
					                
					                r.setTag(blockCode);
					
					                // ✅ Restore placeholders from saved data
					                if (!blockValues.containsKey(r)) {
						                    blockValues.put(r, new HashMap<>());
						                }
					
					                for (Map.Entry<String, String> entry : block.entrySet()) {
						                    if (entry.getKey().startsWith("%")) {
							                        blockValues.get(r).put(entry.getKey(), entry.getValue());
							                    }
						                }
					
					                // ✅ Ensure correct layout params
					                r.setLayoutParams(new LinearLayout.LayoutParams((int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT),(int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT)));
					
					                // ✅ Restore background color
					                if (r.getBackground() instanceof ColorDrawable) {
						                    r.getBackground().clearColorFilter();
						                }
					                r.getBackground().setColorFilter(Color.parseColor(colorCode), PorterDuff.Mode.MULTIPLY);
					
					                // ✅ Remove old views before adding new ones
					                if (r.getChildCount() > 0) {
						                    r.removeAllViews();
						                }
					
					                _addViewsTo(r, content, blockCode, block.get("values"));
					                blockContentMap.put(blockId, block);
					
					                main.addView(r); // ✅ Finally, add the restored block to the main layout
					            }
				        }
			    }
	}
	{
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