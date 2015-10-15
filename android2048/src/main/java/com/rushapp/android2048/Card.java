package com.rushapp.android2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by miraclewong on 15/10/15.
 */
public class Card extends FrameLayout {
    public Card(Context context) {
        super(context);

        label = new TextView(getContext());
        label.setTextSize(32);
        label.setBackgroundColor(0x33ffffff);
        label.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(label, lp);
        setNum(0);
    }

    private int num = 0;
    private TextView label;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        // setText的类型是int，代表的是资源ID
        if (num == 0)
        {
            label.setText("");
        }else {
            label.setText(num + "");
        }
    }

    // 判断card上的数字是否相同来判断
    public boolean equals(Card o) {
        return getNum() == o.getNum();
    }
}
