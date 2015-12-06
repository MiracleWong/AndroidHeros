package com.miraclewong.viewanimationdemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btnAlpha(final View view) {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        view.startAnimation(aa);
    }

    public void btnRotate(final View view) {
        RotateAnimation ra = new RotateAnimation(0, 360, 100, 100);
        ra.setDuration(1000);
        view.startAnimation(ra);
    }

    public void btnRotateSelf(View view) {
        RotateAnimation ra = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5F,
                RotateAnimation.RELATIVE_TO_SELF, 0.5F);
        ra.setDuration(1000);
        view.startAnimation(ra);

    }

    public void btnTranslate(final View view) {
        TranslateAnimation ta = new TranslateAnimation(0, -100, 0, -200);
        ta.setDuration(1000);
        view.startAnimation(ta);
    }

    public void btnScale(View view) {
        ScaleAnimation sa = new ScaleAnimation(0, 2, 0, 2);
        sa.setDuration(1000);
        view.startAnimation(sa);
    }

    public void btnScaleSelf(View view) {
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF, 0.5F);
        sa.setDuration(1000);
        view.startAnimation(sa);
    }

    public void btnSet(View view) {
        AnimationSet as = new AnimationSet(true);
        as.setDuration(1000);

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        as.addAnimation(aa);

        TranslateAnimation ta = new TranslateAnimation(0, 100, 0, 200);
        ta.setDuration(1000);
        as.addAnimation(ta);

        view.startAnimation(as);
    }

    public void btnProHolder(final View view){
        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofFloat("translationX", 300f);
        PropertyValuesHolder propertyValuesHolder2 = PropertyValuesHolder.ofFloat("scaleX", 1f,0,1f);
        PropertyValuesHolder propertyValuesHolder3 = PropertyValuesHolder.ofFloat("scaleY", 1f,0,1f);
        ObjectAnimator.ofPropertyValuesHolder(view, propertyValuesHolder1, propertyValuesHolder2, propertyValuesHolder3).setDuration(3000).start();
    }
    public void btnValueAnimaotr(final View view){
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.setTarget(view);
        animator.setDuration(2000).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                Toast.makeText(MainActivity.this, value + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnAnimatorSet(final View view){
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationX", 300f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(animator1, animator2, animator3);
        set.start();
    }
    public void btnXML(final View view){
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.scalex);
        animator.setTarget(view);
        animator.start();
    }
}
