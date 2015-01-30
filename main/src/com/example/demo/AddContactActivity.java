package com.example.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.android.recyclerview.R;

public class AddContactActivity extends Activity implements OnClickListener{

	private ImageButton pic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 设置actionbar 悬浮
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		// 将actionbar 设置为透明
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(android.R.color.transparent));
		super.onCreate(savedInstanceState);
		getActionBar().setTitle("");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.addcontact);
		initView();
	}

	public void initView() {
		pic = (ImageButton) findViewById(R.id.pic_button);
		pic.setTransitionName("addbutton");
		pic.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_contact, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.save:
			Toast.makeText(AddContactActivity.this, "保存", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pic_button:
			AlertDialog.Builder dialog = new Builder(AddContactActivity.this);
			dialog.setTitle("Test");
			dialog.setIcon(R.drawable.avatar05);
			dialog.setMessage("This is a test");
			dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			dialog.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			dialog.show();
			break;

		default:
			break;
		}
	}

}
