package com.pillowsdk.demo.fragment;

import com.pillowsdk.demo.MainActivity;
import com.pillowsdk.demo.R;
import com.sleepace.sdk.manager.CallbackData;
import com.sleepace.sdk.pillow.PillowHelper;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public abstract class BaseFragment extends Fragment implements OnClickListener{
	
	protected String TAG = getClass().getSimpleName();
	protected MainActivity mActivity;
	private PillowHelper pillowHelper;
	private ScrollView scrollView;
	private TextView tvLog;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mActivity = (MainActivity) getActivity();
		pillowHelper = PillowHelper.getInstance(mActivity);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	public PillowHelper getPillowHelper() {
		return pillowHelper;
	}

	protected void findView(View root) {
		// TODO Auto-generated method stub
		tvLog = (TextView) root.findViewById(R.id.tv_log);
		scrollView = (ScrollView) root.findViewById(R.id.scrollview);
	}


	protected void initListener() {
		// TODO Auto-generated method stub
		mActivity.initTvLogTouchListener(scrollView, tvLog);
	}


	protected void initUI() {
		// TODO Auto-generated method stub
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	public void printLog(int strRes){
		String log = getString(strRes);
		printLog(log);
	}
	
	public void printLog(String log){
		mActivity.printLog(log, tvLog);
	}
	
	public boolean checkStatus(CallbackData cd){
		return mActivity.checkStatus(cd, tvLog);
	}
	
}



