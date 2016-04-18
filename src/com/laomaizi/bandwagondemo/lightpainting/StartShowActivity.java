package com.laomaizi.bandwagondemo.lightpainting;
/**
 * @author Tony
 * @20160412
 * �������й�沥��ҳ��
 */

import com.laomaizi.bandwagondemo.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StartShowActivity extends Activity {

	private static final String TAG = "BandWagon";
	private int ifont;
	private MarqueeText showtext;
	private Vibrator vibrator;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ʹӦ�ó����ޱ�����
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_startshow);
		Intent intent = getIntent();
		String data = intent.getStringExtra("showtext");
		String fontsize = intent.getStringExtra("fontsize");
		int ispeed = intent.getIntExtra("speed", 150);
		int istop = intent.getIntExtra("stop", 1);
		String allData = "";
		for (int i = 0; i < istop + 1; i++) {
			allData += " ";
			Log.d(TAG, ("�ڶ���activity���ӿո�" + String.valueOf(i)));
		}
		allData += data;
		String color = intent.getStringExtra("color");
		ifont = Integer.parseInt(fontsize);
		showtext = (MarqueeText) findViewById(R.id.showtext);
		showtext.setText(allData);

		showtext.setTextSize(ifont);
		showtext.setTextColor(Color.parseColor(color));
		start(showtext, ispeed);
	}

	public boolean onTouchEvent(MotionEvent e) {
		((Activity) this).finish();
		return true;
	}

	public void start(View v, int i) {
		// ��ʼ�ƶ��ַ�ǰ��һ��
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = { 100, 400, 100, 400 }; // ֹͣ ���� ֹͣ ����
		vibrator.vibrate(pattern, -1); // �ظ����������pattern ���ֻ����һ�Σ�index��Ϊ-1
		showtext.startScroll(i);
	}

	public void stop(View v) {
		showtext.stopScroll();
	}

	public void startFor0(View v) {
		showtext.startFor0();
	}


}