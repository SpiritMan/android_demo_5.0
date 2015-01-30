package com.example.demo;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.PathInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.recyclerview.R;

public class DetialActivity extends Activity implements OnClickListener {
	private int position;
	private String name;
	private boolean important = false;
	private ImageButton importantButton;
	private TextView nameTextView;
	private LinearLayout container;
	private CircularImageView avatar;
	 private static final int SCALE_DELAY = 300;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 设置actionbar 悬浮
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		// 设置activity入场动画
		Fade ex = new Fade();
		ex.setInterpolator(new PathInterpolator(0.0f, 0, 1, 1));
		ex.setDuration(1000);
		getWindow().setEnterTransition(ex);
		super.onCreate(savedInstanceState);
		// 将actionbar 设置为透明
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(android.R.color.transparent));
		setContentView(R.layout.detail);
		getActionBar().setTitle("");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		container = (LinearLayout) findViewById(R.id.container);
		getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {

			@Override
			public void onTransitionStart(Transition transition) {

			}

			@Override
			public void onTransitionResume(Transition transition) {

			}

			@Override
			public void onTransitionPause(Transition transition) {

			}

			@Override
			public void onTransitionEnd(Transition transition) {
				getWindow().getEnterTransition().removeListener(this);
				for (int i = 0; i < container.getChildCount(); i++) {
					View rowView = container.getChildAt(i);
                    rowView.animate().setStartDelay(i * SCALE_DELAY)
                            .scaleX(1).scaleY(1);
				}
			}
			@Override
			public void onTransitionCancel(Transition transition) {

			}
		});
		initView();
	}

	public void initView() {
		importantButton = (ImageButton) findViewById(R.id.important_button);
		importantButton.setOnClickListener(this);
		name = getIntent().getStringExtra("name");
		position = getIntent().getIntExtra("position", 0);
		avatar = (CircularImageView) findViewById(R.id.avatar);
		avatar.setTransitionName("avatar");
		avatar.setImageResource(Image.image[position]);
		nameTextView = (TextView) findViewById(R.id.name);
		nameTextView.setText(name);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.detialmenu, menu);
		return super.onCreateOptionsMenu(menu);

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.detial_delete:
			Toast.makeText(DetialActivity.this, "删除!", Toast.LENGTH_SHORT)
					.show();
			return true;
		case R.id.detial_edit:
			Toast.makeText(DetialActivity.this, "编辑", Toast.LENGTH_SHORT)
					.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onDestroy() {
		avatar.setImageBitmap(null);
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.important_button:
			if (important == false) {
				importantButton
						.setImageResource(R.drawable.ic_action_important);
				important = true;
			} else {
				importantButton
						.setImageResource(R.drawable.ic_action_not_important);
				important = false;
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		for (int i = 0; i < container.getChildCount(); i++) {

            View rowView = container.getChildAt(i);
            ViewPropertyAnimator propertyAnimator = rowView.animate().setStartDelay(i * SCALE_DELAY)
                .scaleX(0).scaleY(0);

            propertyAnimator.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {}

                @Override
                public void onAnimationEnd(Animator animator) {

                    finishAfterTransition();
                }

                @Override
                public void onAnimationCancel(Animator animator) {}

                @Override
                public void onAnimationRepeat(Animator animator) {}
            });
        }
	}

	
	
}
