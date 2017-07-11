package com.xingyun.jiujiugk.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.xingyun.jiujiugk.R;
import com.xingyun.jiujiugk.common.CompatUtils;

/**
 * Created by wangqf on 2017/2/22 0022.
 */

public class ItemLinearLayoutView extends LinearLayout {

    private static final int CONCAVE_TOP = 0;  //顶部凹角
    private static final int CONCAVE_BOTTOM = 1; //底部凹角

    private int mBackgroundColor;
    private Paint mPaint;
    private int mConcaveAngleType = -1;

    public ItemLinearLayoutView(Context context) {
        super(context);
        initView(null);
        setWillNotDraw(false);
    }

    public ItemLinearLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
        setWillNotDraw(false);
    }

    public ItemLinearLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
        setWillNotDraw(false);
    }

    private void initView(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ItemLinearLayoutView);
            mBackgroundColor = typedArray.getColor(R.styleable.ItemLinearLayoutView_background_color, Color.WHITE);
            mConcaveAngleType = typedArray.getInt(R.styleable.ItemLinearLayoutView_concave_angle, -1);
            typedArray.recycle();
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.color_background));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setBackgroundResource(R.drawable.item_layout_view_ripple);
        } else {
            setBackgroundResource(R.drawable.item_layout_view);
        }

//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        if (mConcaveAngleType == CONCAVE_TOP) {
            canvas.drawCircle(0, 0, CompatUtils.dp2px(getContext(), 10), mPaint);
            canvas.drawCircle(getWidth(), 0, CompatUtils.dp2px(getContext(), 10), mPaint);
        } else if (mConcaveAngleType == CONCAVE_BOTTOM) {
            canvas.drawCircle(0, getHeight(), CompatUtils.dp2px(getContext(), 10), mPaint);
            canvas.drawCircle(getWidth(), getHeight(), CompatUtils.dp2px(getContext(), 10), mPaint);
        }
    }
}
