package kr.edcan.sunrinton.views;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Junseok Oh on 2017-05-09.
 */

public class CartaSquareView extends android.support.v7.widget.AppCompatImageView {
    public CartaSquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}
