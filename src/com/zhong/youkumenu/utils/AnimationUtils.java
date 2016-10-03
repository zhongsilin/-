package com.zhong.youkumenu.utils;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimationUtils {
	public static int runningAnimationCount = 0;

	/**
	 * 选择出去的动画
	 * 
	 * @param RelativeLayout
	 *            要旋转的控件
	 */
	public static void rotateOutAnim(RelativeLayout rl, long startOffset) {
		// 如果转出去，找到所有子控件，屏蔽它。
		int childCount = rl.getChildCount();
		for (int i = 0; i < childCount; i++) {
			rl.getChildAt(i).setEnabled(false);
		}
		// 起始角度，选装角度，X轴相对于自身旋转，X轴的值为控件长的一半，后Y轴同理
		RotateAnimation ra = new RotateAnimation(0f, -180f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				1.0f);

		ra.setDuration(500);
		ra.setFillAfter(true);
		ra.setStartOffset(startOffset);
		rl.startAnimation(ra);

		ra.setAnimationListener(new Myanimnation());

	}

	public static class Myanimnation implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
			runningAnimationCount++;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			runningAnimationCount--;
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

	}

	public static void rotateIntAnim(RelativeLayout rl, long startOffset) {
		int childCount = rl.getChildCount();
		for (int i = 0; i < childCount; i++) {
			rl.getChildAt(i).setEnabled(true);
		}

		RotateAnimation rm = new RotateAnimation(-180f, 0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				1.0f);

		rm.setDuration(500);
		rm.setFillAfter(true);
		rm.setStartOffset(startOffset);
		rl.startAnimation(rm);

		rm.setAnimationListener(new Myanimnation());

	}

}
