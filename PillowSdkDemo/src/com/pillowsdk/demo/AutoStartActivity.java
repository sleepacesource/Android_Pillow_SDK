package com.pillowsdk.demo;

import com.pillowsdk.demo.view.wheelview.NumericWheelAdapter;
import com.pillowsdk.demo.view.wheelview.OnItemSelectedListener;
import com.pillowsdk.demo.view.wheelview.WheelAdapter;
import com.pillowsdk.demo.view.wheelview.WheelView;
import com.sleepace.sdk.interfs.IResultCallback;
import com.sleepace.sdk.manager.CallbackData;
import com.sleepace.sdk.pillow.PillowHelper;
import com.sleepace.sdk.util.LogUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class AutoStartActivity extends BaseActivity {
    private WheelView wvHour, wvMinute;
    private WheelAdapter hourAdapter, minuteAdapter;
    private Button btnSave;
    private PillowHelper pillowHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pillowHelper = PillowHelper.getInstance(this);
        setContentView(R.layout.activity_autostart);
        findView();
        initListener();
        initUI();
    }


    public void findView() {
    	super.findView();
        wvHour = (WheelView) findViewById(R.id.hour);
        wvMinute = (WheelView) findViewById(R.id.minute);
        btnSave = (Button) findViewById(R.id.btn_save);
    }

    public void initListener() {
    	super.initListener();
    	btnSave.setOnClickListener(this);
    }

    public void initUI() {
    	super.initUI();
        tvTitle.setText(R.string.set_auto_monitor);
        
        wvHour.setAdapter(new NumericWheelAdapter(0, 23));
        wvHour.setTextSize(20);
        wvHour.setCyclic(true);
        wvHour.setOnItemSelectedListener(onHourItemSelectedListener);

        wvMinute.setAdapter(new NumericWheelAdapter(0, 59));
        wvMinute.setTextSize(20);
        wvMinute.setCyclic(true);
        wvMinute.setOnItemSelectedListener(onMiniteItemSelectedListener);

        wvHour.setRate(5 / 4.0f);
        wvMinute.setRate(1 / 2.0f);
        
        wvHour.setCurrentItem(22);
        wvMinute.setCurrentItem(0);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }


    @Override
    public void onClick(View v) {
    	super.onClick(v);
    	if(v == btnSave){
    		final int hour = wvHour.getCurrentItem();
			final int minute = wvMinute.getCurrentItem();
			printLog(getString(R.string.writing_automatically_monitor_device, String.format("%02d:%02d", hour,minute)));
			int repeat = 127; //转车二进制 ：01111111，从右到左，分别表示周一，周二，周三，如果该位是1，表示当天重复，否则不重复。故127表示，周一到周日重复
			pillowHelper.setAutoCollection(true, hour, minute, repeat, 1000, new IResultCallback<Void>() {
				@Override
				public void onResultCallback(final CallbackData<Void> cd) {
					// TODO Auto-generated method stub
					LogUtil.log(TAG+" setAutoCollection hour:" + hour+",minute:" + minute + " " + cd);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							String msg = null;
							if(cd.isSuccess()){
								msg = getString(R.string.write_success);
							}else{
								msg = getErrMsg(cd.getStatus());
							}
							Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
							printLog(msg);
							finish();
						}
					});
				}
			});
    	}
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
    }



    //更新控件快速滑动
    private OnItemSelectedListener onHourItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(int index) {
            
        }
    };

    private OnItemSelectedListener onMiniteItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(int index) {
            
        }
    };
    
}












