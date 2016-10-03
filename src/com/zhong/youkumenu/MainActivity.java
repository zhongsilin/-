package com.zhong.youkumenu;

import com.zhong.youkumenu.utils.AnimationUtils;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private RelativeLayout rl_level1;
	private RelativeLayout rl_level2;
	private RelativeLayout rl_level3;
	boolean level1Display = true;
	boolean level2Display = true;
	boolean level3Display = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
	}

	/**
	 * 初始化控件
	 */
	private void initViews() {
		findViewById(R.id.ib_home).setOnClickListener(this);
		findViewById(R.id.ib_menu).setOnClickListener(this);
		rl_level1 = (RelativeLayout) findViewById(R.id.rl_level1);
		rl_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
		rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			
			if (AnimationUtils.runningAnimationCount > 0) {
				return true;
			}
			
			if (level1Display) {
				long delay = 0;
				if (level3Display) {
					AnimationUtils.rotateOutAnim(rl_level3, 0);
					level3Display = false;
					delay += 200;
				}
				if (level2Display) {
					AnimationUtils.rotateOutAnim(rl_level2, delay);
					level2Display = false;
					delay += 200;
				}
				if (level1Display) {
					AnimationUtils.rotateOutAnim(rl_level1, delay);
					level1Display = false;
				}
			}else{
				AnimationUtils.rotateIntAnim(rl_level3,0);
				AnimationUtils.rotateIntAnim(rl_level2,200);
				AnimationUtils.rotateIntAnim(rl_level1,400);
				
				level1Display = true;
				level2Display = true;
				level3Display = true;
			}

		}
		return true;
	}

	@Override
	public void onClick(View v) {
		if (AnimationUtils.runningAnimationCount > 0) {
			return;
		}
		switch (v.getId()) {
		case R.id.ib_home:
			if (level2Display) {
				long delay = 0;
				// 如果二级页面出来了，转回去
				if (level3Display) {
					AnimationUtils.rotateOutAnim(rl_level3, 0);
					delay += 200;
					level3Display = false;
				}
				AnimationUtils.rotateOutAnim(rl_level2, delay);
			} else {
				// 没出来，转出来
				AnimationUtils.rotateIntAnim(rl_level2,0);
			}

			level2Display = !level2Display;
			break;

		case R.id.ib_menu:
			if (level3Display) {
				// 如果三级页面出来了，转回去
				AnimationUtils.rotateOutAnim(rl_level3, 0);
			} else {
				// 没出来，转出来
				AnimationUtils.rotateIntAnim(rl_level3,0);
			}

			level3Display = !level3Display;
			break;
		}

	}

}
