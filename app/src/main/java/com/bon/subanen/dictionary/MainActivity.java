package com.bon.subanen.dictionary;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StrictMode;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;





public class MainActivity extends  Activity { 
	
	private Timer _timer = new Timer();
	
	private String allText = "";
	private String searchme = "";
	private double ken = 0;
	private double len = 0;
	private double listmap_postion = 0;
	private double random = 0;
	private String word_of_day = "";
	private double font_size = 0;
	private String default_font = "";
	private double n = 0;
	private HashMap<String, Object> mapme = new HashMap<>();
	private String empty = "";
	
	private ArrayList<HashMap<String, Object>> searchmap = new ArrayList<>();
	private ArrayList<String> spinnerstring = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> history_listmap = new ArrayList<>();
	private ArrayList<String> liststring_history = new ArrayList<>();
	
	private LinearLayout linear3;
	private LinearLayout sub_parent_linear;
	private LinearLayout bottom_buttonslinear;
	private LinearLayout ad_view_container;
	private LinearLayout data_content_linear;
	private ScrollView vscroll1;
	private LinearLayout search_linear;
	private ListView searchmaplist;
	private ImageView imageview5;
	private EditText edittext1;
	private ImageView x_image;
	private ImageView search_helping;
	private LinearLayout info_linear;
	private LinearLayout linear_setting_spinner;
	private LinearLayout buttons_parent;
	private WebView banner_webview;
	private TextView textview1;
	private TextView textview2;
	private Spinner word_frequency_notification_spinner;
	private TextView textview3;
	private ProgressBar ad_progress;
	private LinearLayout linear19;
	private LinearLayout linear18;
	private LinearLayout linear21;
	private Button share_apk;
	private Button background_optimize;
	private Button history_button;
	private Button saved_button;
	private Button updates;
	private Button about;
	private LinearLayout linear20;
	private TextView textview4;
	private ImageView give_coffee;
	private ImageView video_ad;
	private TextView textview5;
	private SeekBar seekbar1;
	private LinearLayout linear10;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private LinearLayout linear11;
	private LinearLayout linear14;
	private Button button3;
	private ImageView imageview2;
	private LinearLayout linear15;
	private Button button2;
	private ImageView imageview3;
	private LinearLayout linear16;
	private Button button1;
	private ImageView imageview4;
	
	private Intent intent = new Intent();
	private SharedPreferences database;
	private TimerTask timer;
	private AlertDialog.Builder dialogabout;
	private Intent intent_share = new Intent();
	private Intent fb_open = new Intent();
	private Intent check_updates = new Intent();
	private Intent gcash_intent = new Intent();
	private Intent search_help = new Intent();
	private AlertDialog.Builder search_help_dialog;
	private Vibrator vibrator;
	private Intent history_intent = new Intent();
	private Intent view_bookmarks = new Intent();
        
        private Context mContext;
       private static final int NOTIFICATION_PERMISSION_REQUEST = 2001;

private void requestNotificationPermission() {
    if (Build.VERSION.SDK_INT >= 33) {
        if (checkSelfPermission(
                Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                new String[]{
                    Manifest.permission.POST_NOTIFICATIONS
                },
                NOTIFICATION_PERMISSION_REQUEST
            );
        }
    }
}
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
		mContext = getApplicationContext();
		video_ad.setVisibility(View.GONE);
		requestNotificationPermission();
            
            
            
          //  startService(new Intent(getApplicationContext(), BackgroundProvider.class));
                
                
          
            if ("".equals(database.getString("daywordsettings", ""))) {
                word_frequency_notification_spinner.setSelection((int)(0));

                  
                 NotificationHelper.scheduleRepeatingRTCNotification(mContext, "24", "00");
                 NotificationHelper.scheduleRepeatingElapsedNotification1day(mContext);
                 NotificationHelper.enableBootReceiver(mContext);
               // Toast.makeText(this, "one day default", Toast.LENGTH_LONG).show();

                 

            }
            else {

            }
            if ("twice".equals(database.getString("daywordsettings", ""))) {
                word_frequency_notification_spinner.setSelection((int)(1));

                NotificationHelper.scheduleRepeatingRTCNotification(mContext, "12", "00");
                 NotificationHelper.scheduleRepeatingElapsedNotification12(mContext);
                 NotificationHelper.enableBootReceiver(mContext);
                 

            }
            else {

            }
            if ("1hour".equals(database.getString("daywordsettings", ""))) {
                word_frequency_notification_spinner.setSelection((int)(2));
                     NotificationHelper.scheduleRepeatingRTCNotification(mContext, "1", "00");
                 NotificationHelper.scheduleRepeatingElapsedNotificationhour( mContext);
                 NotificationHelper.enableBootReceiver(mContext);

                 
            }
            else {

            }
            if ("30mins".equals(database.getString("daywordsettings", ""))) {
                word_frequency_notification_spinner.setSelection((int)(3));
                   NotificationHelper.scheduleRepeatingRTCNotification(mContext, "00", "30");
                 NotificationHelper.scheduleRepeatingElapsedNotification30minutes( mContext);
                 NotificationHelper.enableBootReceiver(mContext);

                

            }
            else {

            }
            if ("15mins".equals(database.getString("daywordsettings", ""))) {
                word_frequency_notification_spinner.setSelection((int)(4));

                   NotificationHelper.scheduleRepeatingRTCNotification(mContext, "00", "15");
                 NotificationHelper.scheduleRepeatingElapsedNotification15minutes
                 (mContext);
                 NotificationHelper.enableBootReceiver(mContext);
                 

            }
            else {

            }
          
            
	}
        
        
    /*    private void loadadcolony (){
            
            // Construct optional app options object to be sent with configure
            AdColonyAppOptions appOptions = new AdColonyAppOptions()
                .setUserID("unique_user_id")
                .setKeepScreenOn(true);

            // Configure AdColony in your launching Activity's onCreate() method so that cached ads can
            // be available as soon as possible.
            AdColony.configure(this, appOptions, APP_ID, ZONE_ID);

            // Optional user metadata sent with the ad options in each request
            AdColonyUserMetadata metadata = new AdColonyUserMetadata()
                .setUserAge(26)
                .setUserEducation(AdColonyUserMetadata.USER_EDUCATION_BACHELORS_DEGREE)
                .setUserGender(AdColonyUserMetadata.USER_MALE);

            // Ad specific options to be sent with request
            adOptions = new AdColonyAdOptions()
                .enableConfirmationDialog(true)
                .enableResultsDialog(true)
                .setUserMetadata(metadata);

            // Create and set a reward listener
            AdColony.setRewardListener(new AdColonyRewardListener() {
                    @Override
                    public void onReward(AdColonyReward reward) {
                        // Query reward object for info here
                        Log.d( TAG, "onReward" );
                    }
                });

            // Set up listener for interstitial ad callbacks. You only need to implement the callbacks
            // that you care about. The only required callback is onRequestFilled, as this is the only
            // way to get an ad object.
            listener = new AdColonyInterstitialListener() {
                @Override
                public void onRequestFilled(AdColonyInterstitial ad) {
                    // Ad passed back in request filled callback, ad can now be shown
                    MainActivity.this.ad = ad;
                     video_ad.setEnabled(true);
                       ad_progress.setVisibility(View.GONE);
                    Log.d(TAG, "onRequestFilled");

                }

                @Override
                public void onRequestNotFilled(AdColonyZone zone) {
                    // Ad request was not filled
                    ad_progress.setVisibility(View.GONE);
                    Toast.makeText(MainActivity. this, "Sorry There is no Video ads this Time", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onRequestNotFilled");
                    
                }

                @Override
                public void onOpened(AdColonyInterstitial ad) {
                    // Ad opened, reset UI to reflect state change
                     video_ad.setEnabled(false);
                     ad_progress.setVisibility(View.VISIBLE);
                    Log.d(TAG, "onOpened");
                }

                @Override
                public void onExpiring(AdColonyInterstitial ad) {
                    // Request a new ad if ad is expiring
                       video_ad.setEnabled(false);
                    ad_progress.setVisibility(View.VISIBLE);
                    AdColony.requestInterstitial(ZONE_ID, this, adOptions);
                    Log.d(TAG, "onExpiring");
                }
            };

            // Set up button to show an ad when clicked
            //   showButton = findViewById(R.id.showbutton);
            //     showButton.setOnClickListener(new View.OnClickListener() {
            //      @Override
            //            public void onClick(View view) {

            //        }
            //          });
            consentme();
            
            
        }
    
    private void consentme(){
        // Your user's consent String. In this case, the user has given consent to store
// and process personal information. This value may be either O, 1, or an IAB consent string.
        String consent = "1";

// The value passed via setPrivacyFrameworkRequired() will determine the GDPR requirement of 
// the user. If it's set to true, the user is subject to the GDPR laws.
        AdColonyAppOptions options = new AdColonyAppOptions()
            .setPrivacyFrameworkRequired(AdColonyAppOptions.GDPR, true)
            .setPrivacyConsentString(AdColonyAppOptions.GDPR, consent);

// Pass options object to AdColony in configure call, or later in the session via
        AdColony.setAppOptions(options);
        AdColony.configure(this, options, APP_ID, ZONE_ID);




        // Your user's consent String. In this case, the user has not opted-out to the sale of their information.
        String consent2 = "1";

// The value passed via setPrivacyFrameworkRequired() will indicate to AdColony whether CCPA is applicable legislation for the user. 
// If it's set to true, the user is subject to the CCPA. Note that IAB US Privacy String has information embedded into the string to 
// indicate whether CCPA is applicable. In the event of conflicting signals between the IAB US Privacy String and setPrivacyFrameworkRequired(), 
// we will interpret as CCPA being applicable.
        AdColonyAppOptions options2 = new AdColonyAppOptions()
            .setPrivacyFrameworkRequired(AdColonyAppOptions.CCPA, true)
            .setPrivacyConsentString(AdColonyAppOptions.CCPA, consent2); 

// Pass options object to AdColony in configure call, or later in the session via
        AdColony.setAppOptions(options2);
        AdColony.configure(this, options2, APP_ID, ZONE_ID);

        // The value passed via setPrivacyFrameworkRequired() will determine whether COPPA is applicable for the user.
// If it's set to true, AdColony will behave with the understanding COPPA is applicable for the user.
        AdColonyAppOptions options3 = new AdColonyAppOptions()
            .setPrivacyFrameworkRequired(AdColonyAppOptions.COPPA, true);

// Pass options object to AdColony in configure call, or later in the session via
        AdColony.setAppOptions(options3);
        AdColony.configure(this, options3, APP_ID, ZONE_ID);


    }
       */ 
        
        
        
        
    public  void handleError(int errorCode, WebView view) {

        String message = null;
        if (errorCode == WebViewClient.ERROR_AUTHENTICATION) {
            message = "User authentication failed on server";
        } else if (errorCode == WebViewClient.ERROR_TIMEOUT) {
            message = "The server is taking too much time to communicate. Try again later.";
        } else if (errorCode == WebViewClient.ERROR_TOO_MANY_REQUESTS) {
            message = "Too many requests during this load";
        } else if (errorCode == WebViewClient.ERROR_UNKNOWN) {
            message = "Generic error";
        } else if (errorCode == WebViewClient.ERROR_BAD_URL) {
            message = "Check entered URL..";
        } else if (errorCode == WebViewClient.ERROR_CONNECT) {
            message = "Failed to connect to the server";
        } else if (errorCode == WebViewClient.ERROR_FAILED_SSL_HANDSHAKE) {
            message = "Failed to perform SSL handshake";
        } else if (errorCode == WebViewClient.ERROR_HOST_LOOKUP) {
            message = "Server or proxy hostname lookup failed";
        } else if (errorCode == WebViewClient.ERROR_PROXY_AUTHENTICATION) {
            message = "User authentication failed on proxy";
        } else if (errorCode == WebViewClient.ERROR_REDIRECT_LOOP) {
            message = "Too many redirects";
        } else if (errorCode == WebViewClient.ERROR_UNSUPPORTED_AUTH_SCHEME) {
            message = "Unsupported authentication scheme (not basic or digest)";
        } else if (errorCode == WebViewClient.ERROR_UNSUPPORTED_SCHEME) {
            message = "unsupported scheme";
        } else if (errorCode == WebViewClient.ERROR_FILE) {
            message = "Generic file error";
        } else if (errorCode == WebViewClient.ERROR_FILE_NOT_FOUND) {
            message = "File not found";
        } else if (errorCode == WebViewClient.ERROR_IO) {
            message = "The server failed to communicate. Try again later.";
        }
        if (message != null) {
           // Toast.makeText(getApplicationContext(), "" +message, Toast.LENGTH_LONG).show();
            banner_webview.setVisibility(View.GONE);
        }
    }
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		sub_parent_linear = (LinearLayout) findViewById(R.id.sub_parent_linear);
		bottom_buttonslinear = (LinearLayout) findViewById(R.id.bottom_buttonslinear);
		ad_view_container = (LinearLayout) findViewById(R.id.ad_view_container);
		data_content_linear = (LinearLayout) findViewById(R.id.data_content_linear);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		search_linear = (LinearLayout) findViewById(R.id.search_linear);
		searchmaplist = (ListView) findViewById(R.id.searchmaplist);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		x_image = (ImageView) findViewById(R.id.x_image);
		search_helping = (ImageView) findViewById(R.id.search_helping);
		info_linear = (LinearLayout) findViewById(R.id.info_linear);
		linear_setting_spinner = (LinearLayout) findViewById(R.id.linear_setting_spinner);
		buttons_parent = (LinearLayout) findViewById(R.id.buttons_parent);
		banner_webview = (WebView) findViewById(R.id.banner_webview);
		banner_webview.getSettings().setJavaScriptEnabled(true);
		banner_webview.getSettings().setSupportZoom(true);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		word_frequency_notification_spinner = (Spinner) findViewById(R.id.word_frequency_notification_spinner);
		textview3 = (TextView) findViewById(R.id.textview3);
		ad_progress = (ProgressBar) findViewById(R.id.ad_progress);
		linear19 = (LinearLayout) findViewById(R.id.linear19);
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		linear21 = (LinearLayout) findViewById(R.id.linear21);
		share_apk = (Button) findViewById(R.id.share_apk);
		background_optimize = (Button) findViewById(R.id.background_optimize);
		history_button = (Button) findViewById(R.id.history_button);
		saved_button = (Button) findViewById(R.id.saved_button);
		updates = (Button) findViewById(R.id.updates);
		about = (Button) findViewById(R.id.about);
		linear20 = (LinearLayout) findViewById(R.id.linear20);
		textview4 = (TextView) findViewById(R.id.textview4);
		give_coffee = (ImageView) findViewById(R.id.give_coffee);
		video_ad = (ImageView) findViewById(R.id.video_ad);
		textview5 = (TextView) findViewById(R.id.textview5);
		seekbar1 = (SeekBar) findViewById(R.id.seekbar1);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		button3 = (Button) findViewById(R.id.button3);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		button2 = (Button) findViewById(R.id.button2);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		button1 = (Button) findViewById(R.id.button1);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		database = getSharedPreferences("files", Activity.MODE_PRIVATE);
		dialogabout = new AlertDialog.Builder(this);
		search_help_dialog = new AlertDialog.Builder(this);
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
       
                
                
            ad_view_container = (LinearLayout) findViewById(R.id.ad_view_container);
		if (ad_view_container != null) {
			ad_view_container.setVisibility(View.GONE);
		}

            
                
                
		
		searchmaplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				database.edit().putString("wordview", searchmap.get((int)_position).get("word").toString()).commit();
				database.edit().putString("synonymview", searchmap.get((int)_position).get("synonym").toString()).commit();
				intent.setClass(getApplicationContext(), FullwordActivity.class);
				startActivity(intent);
				mapme = new HashMap<>();
				mapme.put("historywordview", searchmap.get((int)_position).get("word").toString());
				mapme.put("historysynonymview", searchmap.get((int)_position).get("synonym").toString());
				if ("".contains(database.getString("historydata", ""))) {
					database.edit().putString("historydata", new Gson().toJson(mapme)).commit();
				}
				else {
					database.edit().putString("historydata", database.getString("historydata", "").concat(",".concat(new Gson().toJson(mapme)))).commit();
				}
				mapme.clear();
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				searchme = "";
				if (searchme.equals(_charSeq)) {
					searchmaplist.setAdapter(new SearchmaplistAdapter(searchmap));
					((BaseAdapter)searchmaplist.getAdapter()).notifyDataSetChanged();
					x_image.setVisibility(View.GONE);
				}
				else {
					searchmap = new Gson().fromJson(allText, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					ken = searchmap.size();
					len = ken - 1;
					for(int _repeat26 = 0; _repeat26 < (int)(ken); _repeat26++) {
						if (searchmap.get((int)len).get("word").toString().toLowerCase().contains(_charSeq.toLowerCase()) || searchmap.get((int)len).get("synonym").toString().toLowerCase().contains(_charSeq.toLowerCase())) {
							
						}
						else {
							searchmap.remove((int)(len));
						}
						len--;
					}
					x_image.setVisibility(View.VISIBLE);
					searchmaplist.setAdapter(new SearchmaplistAdapter(searchmap));
					((BaseAdapter)searchmaplist.getAdapter()).notifyDataSetChanged();
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		x_image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edittext1.setText("");
				searchmap = new Gson().fromJson(allText, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				searchmaplist.setAdapter(new SearchmaplistAdapter(searchmap));
				((BaseAdapter)searchmaplist.getAdapter()).notifyDataSetChanged();
			}
		});
		
		search_helping.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				
				return true;
				}
			 });
		
		search_helping.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				search_help_dialog.setIcon(R.drawable.ic_help_grey);
				search_help_dialog.setTitle("Search Help");
				search_help_dialog.setMessage("To better and accurate Searching from English to Subanen put a \";\" (semicolon) after a keyword \n\nExample:\nI am searching for a word \"make\"\nI will put semicolon after that word the result will be\n\nmake;\n\neg.; fruit; , house; , fork; ");
				search_help_dialog.setNeutralButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				search_help_dialog.create().show();
			}
		});
		
		banner_webview.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
                                
				
				super.onPageFinished(_param1, _param2);      
			}
                     /*   
                    @SuppressWarnings("deprecation")
                    @Override
                    public void onReceivedError(WebView view, int errorCode, String description,        String failingUrl) {
                        handleError(errorCode,view);
                        super.onReceivedError(view, errorCode, description, failingUrl);
                    }

                    @TargetApi(android.os.Build.VERSION_CODES.M)
                    @Override
                    public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                        // Redirect to deprecated method, so you can use it in all SDK versions
                        onReceivedError(view, rerr.getErrorCode(),rerr.getDescription().toString(),req.getUrl().toString());

                        super.onReceivedError(view, req, rerr);
                    }
                    */
                  /*  public void onReceivedError(WebView view, int errorCod,String description, String failingUrl) {
                        Toast.makeText(MainActivity.this, "Your Internet Connection May not be active Or " + description , Toast.LENGTH_LONG).show();
                    banner_webview.setVisibility(View.INVISIBLE);
                        
                        }*/
                    
                    @Override
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                     //   Log.i("WEB_VIEW_TEST", "error code:" + errorCode);
                      //  Toast.makeText(getApplicationContext(), "error code is: " + errorCode, Toast.LENGTH_LONG).show();
                        banner_webview.setVisibility(View.INVISIBLE);
                        super.onReceivedError(view, errorCode, description, failingUrl);
                    }
                      
                        
                        
		});
		
		word_frequency_notification_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if ("Once a day".equals(spinnerstring.get((int)(_position)))) {
					database.edit().putString("daywordsettings", "").commit();
                                        
					vibrator.vibrate((long)(40));
                                      //  BackgroundProvider.getInstance().scheduleNotification1day("","");
                                    
                                    NotificationHelper.scheduleRepeatingRTCNotification(mContext, "24", "00");
                                    NotificationHelper.scheduleRepeatingElapsedNotification1day(mContext);
                                   NotificationHelper.enableBootReceiver(mContext);
                 
                                    
                                    
                                    
                                    
                                    
					SketchwareUtil.showMessage(getApplicationContext(), "Word Notification set to default Once a day");
				}
				if (spinnerstring.get((int)(_position)).equals("Twice a day")) {
					database.edit().putString("daywordsettings", "twice").commit();
					vibrator.vibrate((long)(40));
                                    NotificationHelper.scheduleRepeatingRTCNotification(mContext, "12", "00");
                                    NotificationHelper.scheduleRepeatingElapsedNotification12
                                    
                                    (mContext);
                                    NotificationHelper.enableBootReceiver(mContext);

                                    
                                        
                                        
					SketchwareUtil.showMessage(getApplicationContext(), "Word Notification will arrive Twice a day");
				}
				if (spinnerstring.get((int)(_position)).equals("Every hour")) {
					database.edit().putString("daywordsettings", "1hour").commit();
					vibrator.vibrate((long)(40));
                                        
                                    NotificationHelper.scheduleRepeatingRTCNotification(mContext, "1", "00");
                                    NotificationHelper.scheduleRepeatingElapsedNotificationhour(mContext);
                                    NotificationHelper.enableBootReceiver(mContext);

                                    
                                        
					SketchwareUtil.showMessage(getApplicationContext(), "Word Notification will arrive Every 1 hour");
				}
				if (spinnerstring.get((int)(_position)).equals("Every 30 minutes")) {
					database.edit().putString("daywordsettings", "30mins").commit();
					vibrator.vibrate((long)(40));
                                    NotificationHelper.scheduleRepeatingRTCNotification(mContext, "00", "30");
                                    NotificationHelper.scheduleRepeatingElapsedNotification30minutes(mContext);
                                    NotificationHelper.enableBootReceiver(mContext);

                                    
                                        
					SketchwareUtil.showMessage(getApplicationContext(), "Word Notification will arrive Every 30 minutes");
				}
				if (spinnerstring.get((int)(_position)).equals("Every 15 minutes")) {
					database.edit().putString("daywordsettings", "15mins").commit();
					vibrator.vibrate((long)(40));
                                        
                                    NotificationHelper.scheduleRepeatingRTCNotification(mContext, "00", "15");
                                    NotificationHelper.scheduleRepeatingElapsedNotification15minutes(mContext);
                                    NotificationHelper.enableBootReceiver(mContext);

                                    
					SketchwareUtil.showMessage(getApplicationContext(), "Word Notification will arrive Every 15 minutes");
				}
				
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		share_apk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				shareApplication();
			}
		});
		
		background_optimize.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_ignore_battery();
			}
		});
		
		history_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				history_intent.setClass(getApplicationContext(), HistoryMainActivity.class);
				startActivity(history_intent);
			}
		});
		
		saved_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				view_bookmarks.setClass(getApplicationContext(), BookmarkedActivity.class);
				startActivity(view_bookmarks);
			}
		});
		
		updates.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				check_updates.setAction(Intent.ACTION_VIEW);
				check_updates.setData(Uri.parse("https://subanendictionaryapp.blogspot.com/2019/11/update.html?m=1"));
				startActivity(check_updates);
			}
		});
		
		about.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				searchmap = new Gson().fromJson(allText, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				SketchwareUtil.showMessage(getApplicationContext(), "There has ".concat(String.valueOf((long)(searchmap.size())).concat(" words Registered.")));
				return true;
				}
			 });
		
		about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialogabout.setIcon(R.drawable.info_gray);
				dialogabout.setTitle("About");
				dialogabout.setMessage("This is Subanen Central Dictionary Translation App for Android for free data is converted from J2ME by SIL, UI and Functions compile and developed by Bonbon.\nLanguage Code: SYB");
				dialogabout.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialogabout.create().show();
			}
		});
		
		give_coffee.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				gcash_intent.setClass(getApplicationContext(), GcashActivity.class);
				startActivity(gcash_intent);
			}
		});
		
		video_ad.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
			}
		});
		
		seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				font_size = _progressValue;
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				database.edit().putString("fontsize", String.valueOf((long)(seekbar1.getProgress()))).commit();
				if ("0".equals(database.getString("fontsize", ""))) {
					edittext1.setTextSize((int)9);
					textview1.setTextSize((int)9);
					textview2.setTextSize((int)6);
					textview3.setTextSize((int)6);
					history_button.setTextSize((int)7);
					saved_button.setTextSize((int)7);
					share_apk.setTextSize((int)7);
					background_optimize.setTextSize((int)7);
					updates.setTextSize((int)7);
					about.setTextSize((int)7);
					textview4.setTextSize((int)6);
					button3.setTextSize((int)8);
					button2.setTextSize((int)8);
					button1.setTextSize((int)8);
					textview5.setTextSize((int)6);
				}
				if ("1".equals(database.getString("fontsize", ""))) {
					edittext1.setTextSize((int)14);
					textview1.setTextSize((int)14);
					textview2.setTextSize((int)11);
					textview3.setTextSize((int)11);
					saved_button.setTextSize((int)12);
					history_button.setTextSize((int)12);
					share_apk.setTextSize((int)12);
					background_optimize.setTextSize((int)12);
					updates.setTextSize((int)12);
					about.setTextSize((int)12);
					textview4.setTextSize((int)11);
					button3.setTextSize((int)13);
					button2.setTextSize((int)13);
					button1.setTextSize((int)13);
					textview5.setTextSize((int)11);
				}
				if ("2".equals(database.getString("fontsize", ""))) {
					edittext1.setTextSize((int)18);
					textview1.setTextSize((int)18);
					textview2.setTextSize((int)12);
					textview3.setTextSize((int)12);
					saved_button.setTextSize((int)14);
					history_button.setTextSize((int)14);
					share_apk.setTextSize((int)14);
					background_optimize.setTextSize((int)14);
					updates.setTextSize((int)14);
					about.setTextSize((int)14);
					textview4.setTextSize((int)12);
					button3.setTextSize((int)16);
					button2.setTextSize((int)16);
					button1.setTextSize((int)16);
					textview5.setTextSize((int)12);
				}
				if ("3".equals(database.getString("fontsize", ""))) {
					edittext1.setTextSize((int)27);
					textview1.setTextSize((int)27);
					textview2.setTextSize((int)18);
					textview3.setTextSize((int)18);
					history_button.setTextSize((int)21);
					saved_button.setTextSize((int)21);
					share_apk.setTextSize((int)21);
					background_optimize.setTextSize((int)21);
					updates.setTextSize((int)21);
					about.setTextSize((int)21);
					textview4.setTextSize((int)18);
					button3.setTextSize((int)24);
					button2.setTextSize((int)24);
					button1.setTextSize((int)24);
					textview5.setTextSize((int)18);
				}
				if ("4".equals(database.getString("fontsize", ""))) {
					edittext1.setTextSize((int)36);
					textview1.setTextSize((int)36);
					textview2.setTextSize((int)24);
					textview3.setTextSize((int)24);
					saved_button.setTextSize((int)28);
					history_button.setTextSize((int)28);
					share_apk.setTextSize((int)28);
					background_optimize.setTextSize((int)28);
					updates.setTextSize((int)28);
					about.setTextSize((int)28);
					textview4.setTextSize((int)24);
					button3.setTextSize((int)32);
					button2.setTextSize((int)32);
					button1.setTextSize((int)32);
					textview5.setTextSize((int)24);
				}
			}
		});
		
		linear14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				info_linear.setVisibility(View.GONE);
				data_content_linear.setVisibility(View.VISIBLE);
				searchmaplist.setAdapter(new SearchmaplistAdapter(searchmap));
				((BaseAdapter)searchmaplist.getAdapter()).notifyDataSetChanged();
				searchmaplist.smoothScrollToPosition((int)(SketchwareUtil.getDisplayHeightPixels(getApplicationContext())));
				searchmap = new Gson().fromJson(allText, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				searchmaplist.setAdapter(new SearchmaplistAdapter(searchmap));
				((BaseAdapter)searchmaplist.getAdapter()).notifyDataSetChanged();
				button3.setTextColor(0xFF2196F3);
				button2.setTextColor(0xFF757575);
				button1.setTextColor(0xFF757575);
				imageview2.setImageResource(R.drawable.home_blue);
				imageview3.setImageResource(R.drawable.fb_gray);
				imageview4.setImageResource(R.drawable.settings_gray);
				vibrator.vibrate((long)(40));
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				info_linear.setVisibility(View.GONE);
				data_content_linear.setVisibility(View.VISIBLE);
				searchmaplist.setAdapter(new SearchmaplistAdapter(searchmap));
				((BaseAdapter)searchmaplist.getAdapter()).notifyDataSetChanged();
				searchmaplist.smoothScrollToPosition((int)(SketchwareUtil.getDisplayHeightPixels(getApplicationContext())));
				searchmap = new Gson().fromJson(allText, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				searchmaplist.setAdapter(new SearchmaplistAdapter(searchmap));
				((BaseAdapter)searchmaplist.getAdapter()).notifyDataSetChanged();
				button3.setTextColor(0xFF2196F3);
				button2.setTextColor(0xFF757575);
				button1.setTextColor(0xFF757575);
				imageview2.setImageResource(R.drawable.home_blue);
				imageview3.setImageResource(R.drawable.fb_gray);
				imageview4.setImageResource(R.drawable.settings_gray);
				vibrator.vibrate((long)(40));
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				info_linear.setVisibility(View.GONE);
				data_content_linear.setVisibility(View.VISIBLE);
				searchmaplist.setAdapter(new SearchmaplistAdapter(searchmap));
				((BaseAdapter)searchmaplist.getAdapter()).notifyDataSetChanged();
				searchmaplist.smoothScrollToPosition((int)(SketchwareUtil.getDisplayHeightPixels(getApplicationContext())));
				searchmap = new Gson().fromJson(allText, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				searchmaplist.setAdapter(new SearchmaplistAdapter(searchmap));
				((BaseAdapter)searchmaplist.getAdapter()).notifyDataSetChanged();
				button3.setTextColor(0xFF2196F3);
				button2.setTextColor(0xFF757575);
				button1.setTextColor(0xFF757575);
				imageview2.setImageResource(R.drawable.home_blue);
				imageview3.setImageResource(R.drawable.fb_gray);
				imageview4.setImageResource(R.drawable.settings_gray);
				vibrator.vibrate((long)(40));
			}
		});
		
		linear15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				button2.setTextColor(0xFF2196F3);
				button3.setTextColor(0xFF757575);
				button1.setTextColor(0xFF757575);
				imageview2.setBackgroundResource(R.drawable.home_gray);
				imageview3.setBackgroundResource(R.drawable.fb_blue);
				imageview4.setBackgroundResource(R.drawable.settings_gray);
				imageview2.setImageResource(R.drawable.home_gray);
				imageview3.setImageResource(R.drawable.fb_blue);
				imageview4.setImageResource(R.drawable.settings_gray);
				fb_open.setAction(Intent.ACTION_VIEW);
				fb_open.setData(Uri.parse("https://www.facebook.com/subanendictionarytranslation/"));
				startActivity(fb_open);
				vibrator.vibrate((long)(40));
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				button2.setTextColor(0xFF2196F3);
				button3.setTextColor(0xFF757575);
				button1.setTextColor(0xFF757575);
				imageview2.setBackgroundResource(R.drawable.home_gray);
				imageview3.setBackgroundResource(R.drawable.fb_blue);
				imageview4.setBackgroundResource(R.drawable.settings_gray);
				imageview2.setImageResource(R.drawable.home_gray);
				imageview3.setImageResource(R.drawable.fb_blue);
				imageview4.setImageResource(R.drawable.settings_gray);
				fb_open.setAction(Intent.ACTION_VIEW);
				fb_open.setData(Uri.parse("https://www.facebook.com/subanendictionarytranslation/"));
				startActivity(fb_open);
				vibrator.vibrate((long)(40));
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				button2.setTextColor(0xFF2196F3);
				button3.setTextColor(0xFF757575);
				button1.setTextColor(0xFF757575);
				imageview2.setBackgroundResource(R.drawable.home_gray);
				imageview3.setBackgroundResource(R.drawable.fb_blue);
				imageview4.setBackgroundResource(R.drawable.settings_gray);
				imageview2.setImageResource(R.drawable.home_gray);
				imageview3.setImageResource(R.drawable.fb_blue);
				imageview4.setImageResource(R.drawable.settings_gray);
				fb_open.setAction(Intent.ACTION_VIEW);
				fb_open.setData(Uri.parse("https://www.facebook.com/subanendictionarytranslation/"));
				startActivity(fb_open);
				vibrator.vibrate((long)(40));
			}
		});
		
		linear16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				data_content_linear.setVisibility(View.GONE);
				info_linear.setVisibility(View.VISIBLE);
				button1.setTextColor(0xFF2196F3);
				button2.setTextColor(0xFF757575);
				button3.setTextColor(0xFF757575);
				imageview2.setBackgroundResource(R.drawable.home_gray);
				imageview3.setBackgroundResource(R.drawable.fb_gray);
				imageview4.setBackgroundResource(R.drawable.settings_blue);
				imageview2.setImageResource(R.drawable.home_gray);
				imageview3.setImageResource(R.drawable.fb_gray);
				imageview4.setImageResource(R.drawable.settings_blue);
				vibrator.vibrate((long)(40));
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				data_content_linear.setVisibility(View.GONE);
				info_linear.setVisibility(View.VISIBLE);
				button1.setTextColor(0xFF2196F3);
				button2.setTextColor(0xFF757575);
				button3.setTextColor(0xFF757575);
				imageview2.setBackgroundResource(R.drawable.home_gray);
				imageview3.setBackgroundResource(R.drawable.fb_gray);
				imageview4.setBackgroundResource(R.drawable.settings_blue);
				imageview2.setImageResource(R.drawable.home_gray);
				imageview3.setImageResource(R.drawable.fb_gray);
				imageview4.setImageResource(R.drawable.settings_blue);
				vibrator.vibrate((long)(40));
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				data_content_linear.setVisibility(View.GONE);
				info_linear.setVisibility(View.VISIBLE);
				button1.setTextColor(0xFF2196F3);
				button2.setTextColor(0xFF757575);
				button3.setTextColor(0xFF757575);
				imageview2.setBackgroundResource(R.drawable.home_gray);
				imageview3.setBackgroundResource(R.drawable.fb_gray);
				imageview4.setBackgroundResource(R.drawable.settings_blue);
				imageview2.setImageResource(R.drawable.home_gray);
				imageview3.setImageResource(R.drawable.fb_gray);
				imageview4.setImageResource(R.drawable.settings_blue);
				vibrator.vibrate((long)(40));
			}
		});
	}
	
	private void initializeLogic() {
		try {
			
			java.io.InputStream stream = getAssets().open("directory1.txt");
			
			
			java.io.BufferedReader bfr = new java.io.BufferedReader(new java.io.InputStreamReader(stream));
			
			String nextline = "";
			//String allText ="";
			
			while ( (nextline = bfr.readLine()) != null) {
				allText = allText + nextline + "\n";
			}
			
			//textview1.setText(allText);
			
			searchmap = new Gson().fromJson(allText, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			
		} catch (java.io.IOException e){
			
			showMessage(e.toString());
		}
		//private Context mContext = getApplicationContext();
		searchmaplist.setAdapter(new SearchmaplistAdapter(searchmap));
		((BaseAdapter)searchmaplist.getAdapter()).notifyDataSetChanged();
		info_linear.setVisibility(View.GONE);
		
		search_linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)45, 0xFFFAFAFA));
		bottom_buttonslinear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFFFFFFFF));
		sub_parent_linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFFEEEEEE));
		searchmaplist.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFFFFFFFF));
		spinnerstring.add("Once a day");
		spinnerstring.add("Twice a day");
		spinnerstring.add("Every hour");
		spinnerstring.add("Every 30 minutes");
		spinnerstring.add("Every 15 minutes");

		word_frequency_notification_spinner.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, spinnerstring));
		((ArrayAdapter)word_frequency_notification_spinner.getAdapter()).notifyDataSetChanged();
		    if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
			background_optimize.setEnabled(false);
		}
		button3.setTextColor(0xFF2196F3);
		button2.setTextColor(0xFF757575);
		button1.setTextColor(0xFF757575);
		imageview2.setImageResource(R.drawable.home_blue);
		imageview3.setImageResource(R.drawable.fb_gray);
		imageview4.setImageResource(R.drawable.settings_gray);
		button3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 0);
		button2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 0);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 0);
		background_optimize.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF03A9F4));
		about.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF03A9F4));
		share_apk.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF03A9F4));
		saved_button.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF03A9F4));
		history_button.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF03A9F4));
		updates.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF03A9F4));
		background_optimize.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		about.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		share_apk.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		history_button.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		saved_button.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		updates.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/century_gothic.ttf"), 0);
		banner_webview.loadUrl("https://subanendictionarybannerannouncement.blogspot.com/?m=0");
		banner_webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		banner_webview.clearCache(true);
		banner_webview.clearHistory();
		linear_setting_spinner.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFFFFFFFF));
		buttons_parent.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFFFFFFFF));
		linear19.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)2, 0xFF9E9E9E, 0xFFFFFFFF));
		x_image.setVisibility(View.GONE);
		
		
		default_font = "";
		if (default_font.equals(database.getString("defaultfont", ""))) {
			database.edit().putString("defaultfont", "done").commit();
			database.edit().putString("fontsize", "2").commit();
		}
		else {
			
		}
		if ("0".equals(database.getString("fontsize", ""))) {
			seekbar1.setProgress((int)0);
			edittext1.setTextSize((int)9);
			textview1.setTextSize((int)9);
			textview2.setTextSize((int)6);
			textview3.setTextSize((int)6);
			share_apk.setTextSize((int)7);
			history_button.setTextSize((int)7);
			saved_button.setTextSize((int)7);
			background_optimize.setTextSize((int)7);
			updates.setTextSize((int)7);
			about.setTextSize((int)7);
			textview4.setTextSize((int)6);
			button3.setTextSize((int)8);
			button2.setTextSize((int)8);
			button1.setTextSize((int)8);
			textview5.setTextSize((int)6);
		}
		if ("1".equals(database.getString("fontsize", ""))) {
			seekbar1.setProgress((int)1);
			edittext1.setTextSize((int)14);
			textview1.setTextSize((int)14);
			textview2.setTextSize((int)11);
			textview3.setTextSize((int)11);
			saved_button.setTextSize((int)12);
			history_button.setTextSize((int)12);
			share_apk.setTextSize((int)12);
			background_optimize.setTextSize((int)12);
			updates.setTextSize((int)12);
			about.setTextSize((int)12);
			textview4.setTextSize((int)11);
			button3.setTextSize((int)13);
			button2.setTextSize((int)13);
			button1.setTextSize((int)13);
			textview5.setTextSize((int)11);
		}
		if ("2".equals(database.getString("fontsize", ""))) {
			seekbar1.setProgress((int)2);
			edittext1.setTextSize((int)18);
			textview1.setTextSize((int)18);
			textview2.setTextSize((int)12);
			textview3.setTextSize((int)12);
			saved_button.setTextSize((int)14);
			history_button.setTextSize((int)14);
			share_apk.setTextSize((int)14);
			background_optimize.setTextSize((int)14);
			updates.setTextSize((int)14);
			about.setTextSize((int)14);
			textview4.setTextSize((int)12);
			button3.setTextSize((int)16);
			button2.setTextSize((int)16);
			button1.setTextSize((int)16);
			textview5.setTextSize((int)12);
		}
		if ("3".equals(database.getString("fontsize", ""))) {
			seekbar1.setProgress((int)3);
			edittext1.setTextSize((int)27);
			textview1.setTextSize((int)27);
			textview2.setTextSize((int)18);
			textview3.setTextSize((int)18);
			saved_button.setTextSize((int)21);
			history_button.setTextSize((int)21);
			share_apk.setTextSize((int)21);
			background_optimize.setTextSize((int)21);
			updates.setTextSize((int)21);
			about.setTextSize((int)21);
			textview4.setTextSize((int)18);
			button3.setTextSize((int)24);
			button2.setTextSize((int)24);
			button1.setTextSize((int)24);
			textview5.setTextSize((int)18);
		}
		if ("4".equals(database.getString("fontsize", ""))) {
			seekbar1.setProgress((int)4);
			edittext1.setTextSize((int)36);
			textview1.setTextSize((int)36);
			textview2.setTextSize((int)24);
			textview3.setTextSize((int)24);
			saved_button.setTextSize((int)28);
			history_button.setTextSize((int)28);
			share_apk.setTextSize((int)28);
			background_optimize.setTextSize((int)28);
			updates.setTextSize((int)28);
			about.setTextSize((int)28);
			textview4.setTextSize((int)24);
			button3.setTextSize((int)32);
			button2.setTextSize((int)32);
			button1.setTextSize((int)32);
			textview5.setTextSize((int)24);
		}
		ad_progress.setVisibility(View.GONE);
		StrictMode.VmPolicy.Builder builder =
		                new StrictMode.VmPolicy.Builder();
		            StrictMode.setVmPolicy(builder.build());
		            if(Build.VERSION.SDK_INT>=24){
			                 try{
				                    java.lang.reflect.Method m =
				                        StrictMode.class.getMethod(
				                        "disableDeathOnFileUriExposure");
				                     m.invoke(null);
				                }
			                catch(Exception e){
				                    showMessage(e.toString());
				                }
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
        
   
    
        
	
	public void _ignore_battery () {
		      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			            Intent intent = new Intent();
			            String packageName = getPackageName();
			            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
			            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
				                intent.setAction(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
				                intent.setData(Uri.parse("package:" + packageName));
				                startActivity(intent);
				            }
			        }
		        
	}
	
	
	public void _share () {
	}
	
	private void shareApplication() { 
		   
            if (database.getString("permissionreq", "").equals("deny") || database.getString("permissionreq", "").equals("")) {

            
                
            
        
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                    || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    database.edit().putString("permissionreq", "deny").commit();
                    requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
              //shareApplication(); 
                    }
                else {
                        sharemeapk();
                        database.edit().putString("permissionreq", "approved").commit();
                    
                    //initialize
                    
                    
                   
                   
                }
            }
            else {
                sharemeapk();
                //initializeLogic();
                
            }
        }
        else {
            sharemeapk();
        }
       

    }
        
        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if ((database.getString("permissionreq","")) == "deny" || (database.getString("permissionreq","") == "")) {
                //initializeLogic();
                //sharemeapk();
                shareApplication();
                
                Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package:" + getPackageName()));
                startActivity(i);
                Toast.makeText(this, "To share this App you must Accept the Storage Permission", Toast.LENGTH_LONG).show();
                
            }
            else {
                sharemeapk();
            }
            }
		    
	
	  
	private void sharemeapk () {
		android.content.pm.ApplicationInfo app = 
		    getApplicationContext().getApplicationInfo(); 
		    String filePath = app.sourceDir;
		    Intent intent = new Intent(Intent.ACTION_SEND); 
		    intent.setType("*/*"); 
		    java.io.File originalApk = new java.io.File(filePath); 
		    try {  
			     java.io.File file = new java.io.File(getExternalFilesDir(null) + java.io.File.separator + "Subanen Dictionary Translation/");
			            file.mkdirs();
			     java.io.File tempFile = new java.io.File(file, "SubanenDictionaryTranslationV1.0_Android5+.apk");
			     if (!tempFile.exists()) {
				     tempFile.createNewFile();
			     }
			      java.io.InputStream in = new java.io.FileInputStream (originalApk);
			      java.io.OutputStream out = new java.io.FileOutputStream(tempFile);
			      byte[] buf = new byte[1024];
			      int len; 
			      while ((len = in.read(buf)) > 0) { 
				        out.write(buf, 0, len); 
				      } 
			      in.close(); 
			      out.close(); 
			      intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
			      intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			      startActivity(Intent.createChooser(intent, "Share app via"));
			    } 
		    catch (java.io.IOException e) 
		    { showMessage(e.toString()); }
	} 



    
	
        
        
  
	
	public class SearchmaplistAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public SearchmaplistAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.searchword, null);
			}
			
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
			
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/san_fransico_pro_regular.ttf"), 0);
			textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/san_fransico_pro_regular.ttf"), 0);
			textview1.setText(searchmap.get((int)_position).get("word").toString());
			textview2.setText(searchmap.get((int)_position).get("synonym").toString());
			listmap_postion = _position;
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
