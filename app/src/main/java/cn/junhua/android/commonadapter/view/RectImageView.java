package cn.junhua.android.commonadapter.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by junhua.lin on 2018/1/2.
 */

public class RectImageView extends android.support.v7.widget.AppCompatImageView {

    public RectImageView(Context context) {
        super(context);
    }

    public RectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
