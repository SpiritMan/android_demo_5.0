package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.recyclerview.R;

public class CustomAdapter extends BaseAdapter {
	@SuppressWarnings("unused")
	private static Context mContext;
	private String[] nameStrings;
	private LayoutInflater inflater;

	@SuppressWarnings("static-access")
	public CustomAdapter(Context context, String[] nameStrings) {
		this.mContext = context;
		inflater = LayoutInflater.from(context);
		this.nameStrings = nameStrings;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return nameStrings.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return nameStrings[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item, parent, false);
		}
		CircularImageView avatarImageView = (CircularImageView) convertView
				.findViewById(R.id.item_avatar);
//		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.avatar01);
		avatarImageView.setImageResource(Image.image[position]);
		TextView nameTextView = (TextView) convertView
				.findViewById(R.id.item_name);
		nameTextView.setText(nameStrings[position]);
		return convertView;
	}

}
