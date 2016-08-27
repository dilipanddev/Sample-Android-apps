package com.taksykraft.spotlightdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;
import com.wooplr.spotlight.utils.SpotlightListener;

public class MainActivity extends Activity
{

	private boolean isRevealEnabled = false;
		//adasdadssdasda
	private static final String BTN1 = "btn1";
	private static final String BTN2 = "btn2";
	private static final String BTN3 = "btn3";

	private Button btn1, btn2 , btn3;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1 = (Button)findViewById(R.id.btn1);
		btn2 = (Button)findViewById(R.id.btn2);
		btn3 = (Button)findViewById(R.id.btn3);

		PreferencesManager mPreferencesManager = new PreferencesManager(MainActivity.this);
		mPreferencesManager.resetAll();
		showIntro(btn1, BTN1);
	}

	private void showIntro(View view, String usageId)
	{
		final SpotlightView spotlightView = new SpotlightView.Builder(this)
				.introAnimationDuration(400)
				.enableDismissAfterShown(true)
				.enableRevalAnimation(isRevealEnabled)
				.performClick(true)
				.setListener(new SpotlightListener()
				{
					@Override
					public void onUserClicked(String spotlightViewId)
					{
						if(spotlightViewId.equalsIgnoreCase(BTN1))
							showIntro(btn2, BTN2);
						else if(spotlightViewId.equalsIgnoreCase(BTN2))
							showIntro(btn3, BTN3);
					}
				})
				.fadeinTextDuration(400)
				//.setTypeface(FontUtil.get(this, "RemachineScript_Personal_Use"))
				.headingTvColor(Color.parseColor("#eb273f"))
				.headingTvSize(32)
				.headingTvText("Volume Control")
				.subHeadingTvColor(Color.parseColor("#ffffff"))
				.subHeadingTvSize(16)
				.subHeadingTvText("From here you can increase or decrease volume.")
				.maskColor(Color.parseColor("#dc000000"))
				.target(view)
				.lineAnimDuration(400)
				.lineAndArcColor(Color.parseColor("#eb273f"))
				.dismissOnTouch(true)
				.enableDismissAfterShown(true)
				.usageId(usageId) //UNIQUE ID
				.show();


		spotlightView.setPerformClick(true);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				spotlightView.dismiss();
			}
		},5000);
	}

}
