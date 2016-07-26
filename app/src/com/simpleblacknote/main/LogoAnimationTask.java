package com.simpleblacknote.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.simpleblacknote.main.LogoActivity.AnimationType;

@SuppressLint("NewApi")
public class LogoAnimationTask extends AsyncTask<Void, Void, Void>
{

    private ILogo mLogoFlow;

    public LogoAnimationTask(ILogo logoFlow){
        mLogoFlow = logoFlow;
    }
    
    @Override
    protected void onPreExecute()
    {
        Random randomAnimationType = new Random();
        runLogoAnimations(randomAnimationType.nextInt(4));
    }
    
    @Override
    protected Void doInBackground(Void... params)
    {
        return null;
    }

    private void runLogoAnimations(int animationType)
    {
        AnimatorSet animation = new AnimatorSet();
        animation.playSequentially(setAnimationParameters(animationType));
        animation.start();
        animation.addListener(createAnimationListener());
    }

    private ValueAnimator setAnimation(float start, float end, String propertyName, int durationMS)
    {
        ValueAnimator animation;
        animation = ObjectAnimator.ofFloat(mLogoFlow.getLogo(), propertyName, start, end);
        animation.setDuration(durationMS);
        return animation;
    }

    private AnimatorSet zoomInAndRotation360()
    {
        AnimatorSet animations;
        ValueAnimator scaleX = setAnimation(0.1f, 1f, "scaleX", 2500);
        ValueAnimator scaleY = setAnimation(0.1f, 1f, "scaleY", 1250);
        ValueAnimator rotationY = setAnimation(0f, 360f, "rotationY", 2500);
        ValueAnimator rotationX = setAnimation(0f, 360f, "rotationX", 1250);
        animations = new AnimatorSet();
        animations.playTogether(scaleX, scaleY, rotationY, rotationX);
        return animations;
    }

    private AnimatorSet zoomOutAndRotation360()
    {
        AnimatorSet animations;
        ValueAnimator scaleX = setAnimation(2.0f, 1f, "scaleX", 2500);
        ValueAnimator scaleY = setAnimation(2.0f, 1f, "scaleY", 1250);
        ValueAnimator rotationY = setAnimation(0f, 360f, "rotationY", 2500);
        ValueAnimator rotationX = setAnimation(0f, 360f, "rotationX", 1250);
        animations = new AnimatorSet();
        animations.playTogether(scaleX, scaleY, rotationY, rotationX);
        return animations;
    }

    private AnimatorSet rotationYAxis720()
    {
        AnimatorSet animations;
        ValueAnimator rotationY = setAnimation(0f, 720f, "rotationY", 2500);
        animations = new AnimatorSet();
        animations.playTogether(rotationY);
        return animations;
    }

    private AnimatorSet rotationXAxis720()
    {
        AnimatorSet animations;
        ValueAnimator rotationX = setAnimation(0f, 720f, "rotationX", 2500);
        animations = new AnimatorSet();
        animations.playTogether(rotationX);
        return animations;
    }

    private List<Animator> setAnimationParameters(int type)
    {
        AnimationType animationType = AnimationType.values()[type];
        AnimatorSet animations = null;
        if (animationType == AnimationType.ZOOMIN_AND_ROTATION360) {
            animations = zoomInAndRotation360();
        } else if (animationType == AnimationType.ZOOMOUT_AND_ROTATION360) {
            animations = zoomOutAndRotation360();
        } else if (animationType == AnimationType.ROTATION_Y_AXIS720) {
            animations = rotationYAxis720();
        } else if (animationType == AnimationType.ROTATION_X_AXIS720) {
            animations = rotationXAxis720();
        }

        ValueAnimator alphaNormal = setAnimation(1f, 1f, "alpha", 1000);
        ValueAnimator fadeOut = setAnimation(1f, 0f, "alpha", 1000);

        List<Animator> animators = new ArrayList<Animator>();
        animators.add(animations);
        animators.add(alphaNormal);
        animators.add(fadeOut);
        return animators;
    }

    private AnimatorListener createAnimationListener()
    {
        return new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation)
            {
            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {
            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                mLogoFlow.finishLogo();
            }

            @Override
            public void onAnimationCancel(Animator animation)
            {

            }
        };
    }
    
}
