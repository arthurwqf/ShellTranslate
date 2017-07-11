package com.xingyun.jiujiugk.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xingyun.jiujiugk.R;
import com.xingyun.jiujiugk.common.CommonMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqf on 2016/6/30 0030.
 */
public class ViewPagerIndicator extends LinearLayout {

    private static final int TYPE_TRIANGLE = 0;
    private static final int TYPE_RECTANGLE = 1;
    private static final int TYPE_LINE = 2;

    private static final float TEXT_DEF_SIZE = 14;

    private Paint mPaint;
    /**
     * 指示器的样式
     */
    private int mType;
    /**
     * path构成一个三角形
     */
    private Path mPath;
    /**
     * 三角形的宽度
     */
    private int mTriangleWidth;
    /**
     * 三角形的高度
     */
    private int mTriangleHeight;
    /**
     * 三角形的宽度为单个Tab的1/6
     */
    private static final float RADIO_TRIANGEL = 1.0f / 6;
    /**
     * 三角形的最大宽度
     */
    private int mMaxTrangleWidth;
    /**
     * 初始时，三角形指示器的偏移量
     */
    private int mInitTranslationX;
    /**
     * 手指滑动时的偏移量
     */
    private float mTranslationX;
    /**
     * 标题正常时的默认颜色
     */
    private static final int COLOR_TEXT_NORMAL = 0x77FFFFFF;
    /**
     * 标题选中时的默认颜色
     */
    private static final int COLOR_TEXT_HIGHLIGHTCOLOR = 0xFFFFFFFF;
    /**
     * 指示器的默认颜色
     */
    private static final int COLOR_INDICATOR = 0x2EC960;
    private int mTabVisibleCount; //tab数量
    private List<String> mTabTitles; //tab上的内容
    private int mTabWidth;
    private ViewPager mViewPager; //与之绑定的viewPager;
    private int mScreenWidth;
    private Context mContext;
    private RectF mRect;
    private float mOffect;
    private float mTextSize;
    /**
     * 标题正常时的颜色
     */
    private int mColor_TextNormal = COLOR_TEXT_NORMAL;
    /**
     * 标题选中时的颜色
     */
    private int mColor_Text_HighLight = COLOR_TEXT_HIGHLIGHTCOLOR;
    /**
     * 指示器的颜色
     */
    private int mColor_indicator = COLOR_INDICATOR;
    private ArrayList<TextView> mRedPointTextViewList;

    public ViewPagerIndicator(Context context) {
        super(context);
        initView(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        if (attrs != null) {
            //获取自定义属性 tab的数量
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
            mTabVisibleCount = array.getInt(R.styleable.ViewPagerIndicator_item_count, 1);
            mType = array.getInt(R.styleable.ViewPagerIndicator_indicator_type, 0);
            mColor_TextNormal = array.getColor(R.styleable.ViewPagerIndicator_normalTextColor, COLOR_TEXT_NORMAL);
            mColor_Text_HighLight = array.getColor(R.styleable.ViewPagerIndicator_highLightTextColor, COLOR_TEXT_HIGHLIGHTCOLOR);
            mColor_indicator = array.getColor(R.styleable.ViewPagerIndicator_indicatorColor, COLOR_INDICATOR);
            mTextSize = array.getDimension(R.styleable.ViewPagerIndicator_titleSize, TEXT_DEF_SIZE);
            if (mTabVisibleCount < 0) {
                mTabVisibleCount = 1;
            }
            array.recycle();
        }
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor_indicator);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = manager.getDefaultDisplay().getWidth();
        mMaxTrangleWidth = (int) (mScreenWidth / 3 * RADIO_TRIANGEL);
    }

    /**
     * 设置布局中view的一些必要属性；如果设置了setTabTitles，布局中view则无效
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int cCount = getChildCount();
        if (cCount == 0) {
            return;
        }
        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LayoutParams params = (LayoutParams) view.getLayoutParams();
            params.weight = 0;
            params.width = mScreenWidth / mTabVisibleCount;
            view.setLayoutParams(params);
        }
        //设置点击事件
        setItemClickEvent();
    }

    /**
     * 初始化三角形的宽度
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTabWidth = w / mTabVisibleCount;
        switch (mType) {
            case TYPE_TRIANGLE:
                //三角形
                mTriangleWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGEL);
                mTriangleWidth = Math.min(mMaxTrangleWidth, mTriangleWidth);

                //初始化三角形
                initTriangle();
                //初始时的偏移量
                mInitTranslationX = getWidth() / mTabVisibleCount / 2 - mTriangleWidth / 2;
                break;
            case TYPE_RECTANGLE:
                //矩形
                mPaint.setColor(mColor_indicator);
                initRectangle(w / mTabVisibleCount, h);
                mInitTranslationX = 0;
                break;
            case TYPE_LINE:
                //线
                mPaint.setColor(mColor_indicator);
                initLine(w / mTabVisibleCount);
                break;
        }
    }

    /**
     * 绘制指示器
     *
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        //画笔平移到正确的位置
        canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 1);
        if (mType != TYPE_RECTANGLE) {
            try {
                canvas.drawPath(mPath, mPaint);
            } catch (Exception e) {

            }
        } else {
            float offK = (float) Math.abs(0.5 - mOffect);
            mPaint.setAlpha((int) (255 * (0.5 + offK)));
            canvas.drawRoundRect(mRect, 25 - 30 * offK, 25 - 30 * offK, mPaint);
        }
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    //设置点击事件
    private void setItemClickEvent() {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final int j = i;
            final View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonMethod.hideInputMethod(mContext, v);
                    if (mClickListener != null) {
                        mClickListener.onIndicatorClick(j);
                    }
                    if (mViewPager != null) {
                        mViewPager.setCurrentItem(j);
                    } else {
                        resetTextViewColor();
                        highLightTextView(j);
                        scroll(j, 0);
                    }
                }
            });
        }
    }

    private OnIndicatorClickListener mClickListener;

    public void setItemClickListener(OnIndicatorClickListener listener) {
        mClickListener = listener;
    }

    /**
     * 初始化三角形指示器
     */
    private void initTriangle() {
        mPath = new Path();
        mTriangleHeight = (int) (mTriangleWidth / 2 / Math.sqrt(2));
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();
    }

    /**
     * 初始化矩形指示器
     */
    private void initRectangle(int w, int h) {
//        mPath = new Path();
//        mPath.moveTo(0, 0);
//        mPath.lineTo(w, 0);
//        mPath.lineTo(w, -h);
//        mPath.lineTo(0, -h);
//        mPath.close();
        mRect = new RectF(0, -h, w, 0);
    }

    /**
     * 初始化线指示器
     */
    private void initLine(int w) {
        mPath = new Path();
        mPath.moveTo(20, 0);
        mPath.lineTo(w - 20, 0);
        mPath.lineTo(w - 20, -6);
        mPath.lineTo(20, -6);
        mPath.close();
    }

    public void setViewPager(ViewPager viewPager, final int pos) {
        this.mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //滚动
                scroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                //设置字体颜色高亮
                resetTextViewColor();
                highLightTextView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //设置当前页
        mViewPager.setCurrentItem(pos);
        //高亮
        highLightTextView(pos);
    }

    /**
     * 高亮文本
     *
     * @param position
     */
    public void highLightTextView(int position) {
        View view = getChildAt(position);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(mColor_Text_HighLight);
        }
        if (view instanceof RelativeLayout) {
            ((TextView) view.findViewById(R.id.tv_text)).setTextColor(mColor_Text_HighLight);
        }
    }

    /**
     * 重置文本颜色
     */
    private void resetTextViewColor() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(mColor_TextNormal);
            } else if (view instanceof RelativeLayout) {
                ((TextView) view.findViewById(R.id.tv_text)).setTextColor(mColor_TextNormal);
            }
        }
    }

    /**
     * 指示器跟随手指滚动，以及容器滚动
     *
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {
        //不断改变偏移量，invalidate
        mTranslationX = getWidth() / mTabVisibleCount * (position + offset);
        mOffect = offset;
        int tabWidth = mScreenWidth / mTabVisibleCount;
        //容器滚动，当移动到倒数最后一个的时候，开始滚动
        if (offset > 0 && position >= (mTabVisibleCount - 2) && getChildCount() > mTabVisibleCount) {
            if (mTabVisibleCount != 1) {
                this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth + (int) (tabWidth * offset), 0);
            } else {
                //当count为1时的特殊处理
                this.scrollTo(position * tabWidth + (int) (tabWidth * offset), 0);
            }
        }
        invalidate();
    }

    /**
     * 设置可见的tab的数量
     *
     * @param count
     */
    public void setVisibleTabCount(int count) {
        this.mTabVisibleCount = count;
        initLine(getWidth() / count);
        invalidate();
    }

    public void setTabItemTitle(List<String> datas) {
        //如果传入的list有值
        if (datas != null && datas.size() > 0) {
            this.removeAllViews();
            this.mTabTitles = datas;
            for (String title : mTabTitles) {
                addView(generateTextView(title));
            }
            setItemClickEvent();
        }
    }

    public void setTabItemTitle(String[] datas) {
        //如果传入的list有值
        if (datas != null && datas.length > 0) {
            this.removeAllViews();
            for (String title : datas) {
                addView(generateTextView(title));
            }
            setItemClickEvent();
        }
    }

    private TextView generateTextView(String title) {
        TextView textView = new TextView(mContext);
        textView.setText(title);
        textView.setTextSize(mTextSize);
        textView.setTextColor(mColor_TextNormal);
        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    public void setRedPointTabItemTitle(String[] datas) {
        if (datas != null && datas.length > 0) {
            mRedPointTextViewList = new ArrayList<>();
            this.removeAllViews();
            for (String title : datas) {
                addView(generateRedPintLayout(title));
            }
            setItemClickEvent();
        }
    }

    private RelativeLayout generateRedPintLayout(String title) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_red_point_textview, null);
        if (view instanceof RelativeLayout) {
            TextView tv_text = (TextView) view.findViewById(R.id.tv_text);
            tv_text.setText(title);
            TextView tv_point = (TextView) view.findViewById(R.id.tv_red_point);
            mRedPointTextViewList.add(tv_point);
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            view.setLayoutParams(params);
            return (RelativeLayout) view;
        } else
            return null;
    }

    /**
     * 标题上带红点数字的 设置数字
     *
     * @param i
     * @param num
     */
    public void setRedPointText(int i, int num) {
        if (mRedPointTextViewList != null) {
            TextView tv_point = mRedPointTextViewList.get(i);
            if (num > 0) {
                tv_point.setVisibility(VISIBLE);
                tv_point.setText(num + "");
            } else {
                tv_point.setVisibility(GONE);
            }
        }
    }

    public interface OnIndicatorClickListener {
        void onIndicatorClick(int position);
    }
}
