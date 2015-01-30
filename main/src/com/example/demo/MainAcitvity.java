package com.example.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.recyclerview.R;

public class MainAcitvity extends Activity implements OnClickListener {
	private CustomAdapter adapter;
	private ImageButton addButton;
	private ListView listView;
	private String[] nameStrings = { "Bonnie", "David", "Diana", "Francisco",
			"Harry", "James", "Linda", "Tom", "Wayne", "Yolanda", "Zoe", "Zack" };
	@SuppressLint("NewApi")
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * explode：从场景的中心移入或移出
		 * 
		 * slide：从场景的边缘移入或移出
		 * 
		 * fade：调整透明度产生渐变效果
		 */
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		getWindow().setEnterTransition(new Explode().setDuration(2000));
		setContentView(R.layout.main);
		getActionBar().setElevation(0);

		listView = (ListView) findViewById(R.id.list);
		addButton = (ImageButton) findViewById(R.id.add_button);
		addButton.setOnClickListener(this);
		adapter = new CustomAdapter(this, nameStrings);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				CircularImageView circularImageView = (CircularImageView) view
						.findViewById(R.id.item_avatar);
				Transition transition = new ChangeTransform();
				transition.setDuration(1000);
				getWindow().setExitTransition(transition);
				// 设置共享控件的动画
				Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(
						MainAcitvity.this, circularImageView, "avatar")
						.toBundle();

				Intent intent = new Intent(MainAcitvity.this,
						DetialActivity.class);
				intent.putExtra("name", nameStrings[position]);
				intent.putExtra("position", position);
				startActivity(intent, bundle);
			}
		});

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_delete:
			Toast.makeText(MainAcitvity.this, "删除!", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_button:
			Transition transition = new ChangeTransform();
			transition.setDuration(1000);
			getWindow().setExitTransition(transition);
			// 设置共享控件的动画
			Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(
					MainAcitvity.this, addButton, "addbutton")
					.toBundle();
			Intent intent = new Intent(MainAcitvity.this,AddContactActivity.class);
			startActivity(intent,bundle);
			break;

		default:
			break;
		}
	}

}
