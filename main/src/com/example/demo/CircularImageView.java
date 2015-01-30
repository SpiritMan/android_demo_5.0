package com.example.demo;

import com.example.android.recyclerview.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircularImageView extends ImageView {
	private Paint mPaint;
	private boolean border;
	private Context context;
	private int imageCategory;
	private Bitmap starBitmap;
	private Bitmap bitmap;
	private Bitmap dstBitmap;
	private Bitmap output;
	private Bitmap srcBitmap;

	public CircularImageView(Context context) {
		super(context);
		mPaint = new Paint();
		this.context = context;
	}

	@SuppressLint("Recycle")
	public CircularImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		this.context = context;
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CircularImageView);

		border = typedArray.getBoolean(R.styleable.CircularImageView_border,
				false);
		imageCategory = typedArray.getInteger(
				R.styleable.CircularImageView_imagecategory, 0);
		if (imageCategory == 1) {
			starBitmap = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.icon_achi_animal_finish);
		} else if (imageCategory == 0) {
			starBitmap = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.avatar01);
		} else {
			starBitmap = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.icon_star_big);
		}
		// mMaskSource =
		// typedArray.getResourceId(R.styleable.CircularImageView_mask,
		// R.drawable.avatar01);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		Drawable drawable = getDrawable();
		if (null != drawable) {
			bitmap = ((BitmapDrawable) drawable).getBitmap();
			final Rect rectSrc = new Rect(0, 0, bitmap.getWidth(),
					bitmap.getHeight());
			final Rect rectDest = new Rect(0, 0, getWidth(), getHeight());
			mPaint.setAntiAlias(true); // Ïû³ý¾â³Ý
			dstBitmap = getStarBitmap(bitmap);
			// mPaint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			canvas.drawBitmap(dstBitmap, rectSrc, rectDest, mPaint);
			if (imageCategory == 1) {
				if (border) {
					srcBitmap = BitmapFactory.decodeResource(
							context.getResources(), R.drawable.image_cirborder);
					canvas.drawBitmap(srcBitmap, null, rectDest, mPaint);
				}

			} else if (imageCategory == 0) {
				if (border) {
					srcBitmap = BitmapFactory.decodeResource(
							context.getResources(), R.drawable.image_border);
					canvas.drawBitmap(srcBitmap, null, rectDest, mPaint);
				}
			}
		} else {
			super.onDraw(canvas);
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		// TODO Auto-generated method stub
//		if (bitmap != null) {
//			bitmap.recycle();
//		}
		if (starBitmap != null) {
			starBitmap.recycle();
		}
		if (dstBitmap != null) {
			dstBitmap.recycle();
		}
		if (output != null) {
			output.recycle();
		}
		if (srcBitmap != null) {
			srcBitmap.recycle();
		}
		super.onDetachedFromWindow();

	}

	private Bitmap getStarBitmap(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setAntiAlias(true);
		// canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.BLACK);
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// try {
		canvas.drawBitmap(bitmap, rect, rect, null);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		canvas.drawBitmap(starBitmap, null, rect, paint);
		return output;
	}
}
