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
import java.util.List;;

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
	
	private ArrayList<String> liststring = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
	private LinearLayout linear5;
	private LinearLayout linear2;
	private FloatingActionButton floating_action_button1;
	private LinearLayout linear8;
	private LinearLayout linear1;
	private LinearLayout shadow;
	private LinearLayout linear7;
	private TextView textview1;
	private Button button2;
	private Button button1;
	private ImageView delete;
	private Button button3;
	private ScrollView vscroll2;
	private HorizontalScrollView hscroll1;
	private LinearLayout main;
	private LinearLayout linear13;
	private ScrollView vscroll3;
	private LinearLayout linear10;
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
		linear1 = findViewById(R.id.linear1);
		shadow = findViewById(R.id.shadow);
		linear7 = findViewById(R.id.linear7);
		textview1 = findViewById(R.id.textview1);
		button2 = findViewById(R.id.button2);
		button1 = findViewById(R.id.button1);
		delete = findViewById(R.id.delete);
		button3 = findViewById(R.id.button3);
		vscroll2 = findViewById(R.id.vscroll2);
		hscroll1 = findViewById(R.id.hscroll1);
		main = findViewById(R.id.main);
		linear13 = findViewById(R.id.linear13);
		vscroll3 = findViewById(R.id.vscroll3);
		linear10 = findViewById(R.id.linear10);
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
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				saveBlocks();
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				showSourceCodeDialog();
			}
		});
		
		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				loadBlocks();
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
		((ViewGroup)shadow.getParent()).removeView(shadow);
		delete.setOnDragListener(new delete());
		listview1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, liststring));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		_refrieshlist();
	}
	
	
	@Override
	public void onBackPressed() {
		if (toogle) { 
			    toogle = false;
			    linear8.setVisibility(View.GONE);
			
			    // Thoda delay add karte hain taaki GONE hone ke baad dialog dikhe
			    new Handler().postDelayed(new Runnable() {
				        @Override
				        public void run() {
					            showExitDialog();
					        }
				    }, 100); // 100ms delay
			
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
		protected class if_droptop implements View.OnDragListener {
			    public boolean onDrag(final View v, final DragEvent event) {
				        final int action = event.getAction();
				        final View DV = (View) event.getLocalState();
				
				        if (DV == null) return false; // Prevent null pointer crash
				
				        switch (action) {
					            case DragEvent.ACTION_DRAG_STARTED:
					                DV.setVisibility(View.GONE);
					                return true;
					
					            case DragEvent.ACTION_DRAG_ENTERED:
					                v.invalidate();
					                return true;
					
					            case DragEvent.ACTION_DRAG_LOCATION:
					                try {
						                    if (v.getTag() instanceof if_block) {
							                        if_block block = (if_block) v.getTag();
							                        LinearLayout center = block.center;
							
							                        // Remove shadow from previous parent safely
							                        if (shadow.getParent() instanceof ViewGroup) {
								                            ((ViewGroup) shadow.getParent()).removeView(shadow);
								                        }
							
							                        // Add shadow at the first position
							                        ((ViewGroup) center).addView(shadow, 0);
							
							                        // Remove dragged view from its previous parent safely
							                        if (DV.getParent() instanceof ViewGroup) {
								                            ((ViewGroup) DV.getParent()).removeView(DV);
								                        }
							
							                        // Add the dragged view at the first position
							                        ((ViewGroup) center).addView(DV, 0);
							                    }
						                } catch (Exception e) {
						                    Log.e("DragError", "Error during drag: ", e);
						                }
					                return true;
					
					            case DragEvent.ACTION_DRAG_EXITED:
					                v.invalidate();
					                return true;
					
					            case DragEvent.ACTION_DROP:
					                v.invalidate();
					                return true;
					
					            case DragEvent.ACTION_DRAG_ENDED:
					                v.invalidate();
					
					                new Handler().postDelayed(() -> {
						                    try {
							                        if (shadow.getParent() instanceof ViewGroup) {
								                            ((ViewGroup) shadow.getParent()).removeView(shadow);
								                        }
							                        DV.setVisibility(View.VISIBLE);
							                    } catch (Exception e) {
							                        Log.e("DragError", "Error restoring visibility: ", e);
							                    }
						                }, 5); // Small delay to ensure UI updates properly
					
					                _refrieshlist();
					                return true;
					
					            default:
					                break;
					        }
				        return false;
				    }
		}
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
		edit.setText(((TextView) _view).getText().toString());
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
		
		t = new TimerTask() {
			    @Override
			    public void run() {
				        runOnUiThread(new Runnable() {
					            @Override
					            public void run() {
						                dataMap = gson.fromJson(s.getString("data", ""), type);
						                List<Map<String, String>> dataMapList = dataMap.get(liststring.get((int) n));
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
						
						                for (int _repeat61 = 0; _repeat61 < list.size(); _repeat61++) {
							                    String type = list.get(_repeat61).get("type").toString();
							                    String content = list.get(_repeat61).get("content").toString();
							                    String blockCode = list.get(_repeat61).get("code").toString(); // ✅ Get block "code"
							
							                    if (type.equals("heading")) {
								                        TextView txt = new TextView(MainActivity.this);
								                        txt.setText(content);
								                        txt.setBackgroundColor(0xFF212121);
								                        txt.setTextColor(0xFFFFFFFF);
								                        blockarea.addView(txt);
								
								                    } else if (type.equals("regular")) {
								                        regular r = new regular(MainActivity.this);
								                        blockarea.addView(r);
								                        _addViewsTo(r, content, blockCode); // ✅ Pass blockCode
								
								                        try {
									                            String colours = "#" + list.get(_repeat61).get("colour").toString();
									                            int colour = Color.parseColor(colours);
									                            r.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
									                            r.invalidate();
									                        } catch (Exception e) { }
								                        
								                    } else if (type.equals("if")) {
								                        if_block ifb = new if_block(MainActivity.this);
								                        blockarea.addView(ifb);
								                        _addViewsTo(ifb.top, content, blockCode); // ✅ Pass blockCode
								
								                        try {
									                            String colours = "#" + list.get(_repeat61).get("colour").toString();
									                            int colour = Color.parseColor(colours);
									                            ifb.top.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
									                            ifb.center.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
									                            ifb.bottom.getBackground().setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
									
									                            ifb.top.invalidate();
									                            ifb.center.invalidate();
									                            ifb.bottom.invalidate();
									                        } catch (Exception e) { }
								
								                    } else if (type.equals("if.e")) {
								                        if_block ifb = new if_block(MainActivity.this);
								                        blockarea.addView(ifb);
								                        _addViewsTo(ifb.top, content, blockCode); // ✅ Pass blockCode
								
								                        if (list.get(_repeat61).get("content2") != null) {
									                            _addViewsTo(ifb.middle, list.get(_repeat61).get("content2").toString(), blockCode);
									                        } else {
									                            _addViewsTo(ifb.middle, " ", blockCode);
									                        }
								
								                        ifb.middle.setVisibility(View.VISIBLE);
								                        ifb.center2.setVisibility(View.VISIBLE);
								
								                        try {
									                            String colours = "#" + list.get(_repeat61).get("colour").toString();
									                            int colour = Color.parseColor(colours);
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
									                        } catch (Exception e) { }
								                    }
							                }
						            }
					        });
				    }
		};
		_timer.schedule(t, 50);
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
		
		    editText.setText(code);
		    editText.setTextSize(14);
		    editText.setPadding(16, 16, 16, 16);
		    editText.setBackgroundColor(Color.TRANSPARENT);
		    editText.setFocusable(false);
		
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
	
	private void saveBlocks() {
		    List<Map<String, String>> blockList = new ArrayList<>();
		    for (int i = 0; i < main.getChildCount(); i++) {
			        View view = main.getChildAt(i);
			        if (view instanceof regular || view instanceof if_block) {
				            Map<String, String> blockData = new HashMap<>();
				            blockData.put("type", view instanceof regular ? "regular" : "if_block");
				            blockData.put("content", getBlockContent(view));
				            int color;
				            if (view instanceof regular) {
					                color = 0xFFFB8C00;
					            } else {
					                color = 0xFF2195F3;
					            }
				            blockData.put("color", String.format("#%06X", (0xFFFFFF & color)));
				            blockList.add(blockData);
				        }
			    }
		    String json = gson.toJson(blockList);
		    s.edit().putString("saved_blocks", json).apply();
	}
	
	private void loadBlocks() {
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
					                int color = "regular".equals(blockData.get("type")) ? 0xFFFB8C00 : 0xFF2195F3;
					                ((LinearLayout) block).getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
					                _addViewsTo(block, blockData.get("content"));
					                main.addView(block);
					            }
				        } catch (Exception e) {
				            Log.e("LoadBlocks", "Error parsing saved blocks JSON", e);
				        }
			    }
	}
	
	private String getBlockContent(View block) {
		    if (block instanceof regular || block instanceof if_block) {
			        if (block.getTag() != null) {
				            return block.getTag().toString(); // ✅ Ensure "code" is always fetched
				        }
			    }
		    return "";  // 🔴 If no code is found, return an empty string
	}
	
	
	private List<String> getBlockInputValues(View block) {
		    List<String> values = new ArrayList<>();
		
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
	
	private void storeBlockData(View block) {
		    if (block instanceof regular || block instanceof if_block) {
			        Map<String, String> blockData = new HashMap<>();
			
			        // 🔥 Block ke tag me code store karo
			        String extractedCode = block.getTag() != null ? block.getTag().toString() : "";
			
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
	
	
	
	private List<String> getBlockPitchHolders(View block) {
		    List<String> inputValues = new ArrayList<>();
		    String key = "values_" + block.hashCode();
		
		    if (map.containsKey(key)) {
			        try {
				            Type type = new TypeToken<List<String>>() {}.getType();
				            String jsonString = String.valueOf(map.get(key)); // ✅ Ensure String type
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
	
	public void _addViewsTo(final View _view, final String _code, String blockCode) {
		    _view.setTag(blockCode);
		    int placeholderIndex = 1;
		
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
				
				            // Ensure blockValues entry exists
				            if (!blockValues.containsKey(_view)) {
					                blockValues.put(_view, new HashMap<>());
					            }
				
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
				
				            // Ensure blockValues entry exists
				            if (!blockValues.containsKey(_view)) {
					                blockValues.put(_view, new HashMap<>());
					            }
				
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
				            ((LinearLayout) _view).addView(new TextView(MainActivity.this));
				
				            View txt = ((LinearLayout) _view).getChildAt(((LinearLayout) _view).getChildCount() - 1);
				
				            ((TextView) txt).setText(i + " ");
				            ((TextView) txt).setTextColor(0xFFFFFFFF);
				            ((TextView) txt).setTypeface(Typeface.DEFAULT, Typeface.BOLD);
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