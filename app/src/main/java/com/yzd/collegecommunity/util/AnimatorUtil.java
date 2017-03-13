package com.yzd.collegecommunity.util;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.Nullable;

/*
* Created by Laiyin on 2017/2/25.
*
* 动画工具类
*/

public class AnimatorUtil {

    private AnimatorSet scaleAndtranslationAnimatorSet;     //缩放和位移动画集
    private AnimatorSet alphaAndtranslationAnimatorSet;     //透明度和位移动画集
    private AnimatorSet alphaAndrotateAnimatorSet;          //透明度和旋转动画集
    private AnimatorSet scaleAndrotateAnimatorSet;          //缩放和旋转动画集
    private AnimatorSet scaleAndAlphaAnimatorSet;           //缩放和透明度动画集
    private AnimatorSet scaleAndTranslationAnimatorSet;     //缩放和位移动画集
    private AnimatorSet alphaAnimatorSet;                   //透明度动画集
    private AnimatorSet translationAnimatorSet;             //位移动画集
    private AnimatorSet scaleAnimatorSet;                   //缩放动画集
    private AnimatorSet rotateAnimationSet;                 //旋转动画集

    /**
     * 缩放和位移的组合动画
     * @param target 动画对象
     * @param values_fromScaleX X轴方向初始缩放倍数(宽度)
     * @param values_toScaleX X轴方向结束缩放倍数
     * @param values_fromScaleY Y轴方向初始缩放倍数(高度)
     * @param values_toScaleY Y轴方向结束缩放倍数
     * @param values_translationY_first Y轴方向初始位置
     * @param values_translationY_end Y轴方向结束位置
     * @param values_translationX_first X轴方向初始位置
     * @param values_translationX_end X轴方向结束位置
     * @param duration 动画持续时间
     */
    public void scaleAndTranslationAnimator(Object target, float values_fromScaleX, float values_toScaleX, float values_fromScaleY, float values_toScaleY, float values_translationY_first, float values_translationY_end, float values_translationX_first, float values_translationX_end, long duration,@Nullable Animator.AnimatorListener animatorListener) {
        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(target, "scaleX", values_fromScaleX, values_toScaleX);
        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(target, "scaleY", values_fromScaleY, values_toScaleY);
        ObjectAnimator transAnimatorY = ObjectAnimator.ofFloat(target, "translationY", values_translationY_first, values_translationY_end);
        ObjectAnimator transAnimatorX = ObjectAnimator.ofFloat(target, "translationX", values_translationX_first, values_translationX_end);

        scaleAndTranslationAnimatorSet = new AnimatorSet();
        if(animatorListener!=null)
        {
            scaleAndTranslationAnimatorSet.addListener(animatorListener);
        }
        scaleAndTranslationAnimatorSet.play(xAnimator).with(yAnimator).with(transAnimatorY).with(transAnimatorX);
        scaleAndTranslationAnimatorSet.setDuration(duration).start();
    }

    /**
     * 缩放和透明的组合动画
     * @param target 动画对象
     * @param values_fromScaleX X轴方向初始缩放倍数
     * @param values_toScaleX X轴方向结束缩放倍数
     * @param values_fromScaleY Y轴方向初始缩放倍数
     * @param values_toScaleY Y轴方向结束缩放倍数
     * @param alpha_first 初始透明值
     * @param alpha_end 结束时的透明值
     * @param duration 动画持续时间
     */
    public void scaleAndAlphaAnimator(Object target, float values_fromScaleX, float values_toScaleX, float values_fromScaleY, float values_toScaleY, float alpha_first, float alpha_end,long duration) {
        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(target, "scaleX", values_fromScaleX, values_toScaleX);
        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(target, "scaleY", values_fromScaleY, values_toScaleY);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(target, "alpha", alpha_first, alpha_end);

        scaleAndAlphaAnimatorSet = new AnimatorSet();

        scaleAndAlphaAnimatorSet.play(xAnimator).with(yAnimator).with(alphaAnimator);
        scaleAndAlphaAnimatorSet.setDuration(duration).start();
    }

    /**
     * 缩放和旋转的组合动画
     * @param target 动画对象
     * @param values_fromScaleX X轴方向初始缩放倍数
     * @param values_toScaleX X轴方向结束缩放倍数
     * @param values_fromScaleY Y轴方向初始缩放倍数
     * @param values_toScaleY Y轴方向结束缩放倍数
     * @param fromDegrees 初始旋转角度
     * @param toDegrees 结束时的旋转角度
     * @param duration 动画持续时间
     */
    public void scaleAndrotateAnimator(Object target, float values_fromScaleX, float values_toScaleX, float values_fromScaleY, float values_toScaleY, float fromDegrees, float toDegrees, long duration) {
        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(target, "scaleX", values_fromScaleX, values_toScaleX);
        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(target, "scaleY", values_fromScaleY, values_toScaleY);
        ObjectAnimator rotateAnimator=ObjectAnimator.ofFloat(target,"rotation",fromDegrees,toDegrees);

        scaleAndrotateAnimatorSet = new AnimatorSet();

        scaleAndrotateAnimatorSet.play(xAnimator).with(yAnimator).with(rotateAnimator);
        scaleAndrotateAnimatorSet.setDuration(duration).start();
    }

    /**
     * 透明和旋转的组合动画
     * @param target 动画对象
     * @param alpha_first 初始透明值
     * @param alpha_end 结束时的透明值
     * @param fromDegrees 初始旋转角度
     * @param toDegrees 结束时的旋转角度
     * @param duration 动画持续时间
     */
    public void alphaAndrotateAnimator(Object target, float alpha_first, float alpha_end, float fromDegrees, float toDegrees, long duration) {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(target, "alpha", alpha_first, alpha_end);
        ObjectAnimator rotateAnimator=ObjectAnimator.ofFloat(target,"rotation",fromDegrees,toDegrees);

        alphaAndrotateAnimatorSet = new AnimatorSet();

        alphaAndrotateAnimatorSet.play(alphaAnimator).with(rotateAnimator);
        alphaAndrotateAnimatorSet.setDuration(duration).start();
    }

    /**
     * 透明和位移的组合动画
     * @param target 动画对象
     * @param alpha_first 初始透明值
     * @param alpha_end 结束时的透明值
     * @param values_translationY_first Y轴方向初始位置
     * @param values_translationY_end Y轴方向结束位置
     * @param values_translationX_first X轴方向初始位置
     * @param values_translationX_end X轴方向结束位置
     * @param duration 动画持续时间
     */
    public void alphaAndtranslationAnimator(Object target, float alpha_first, float alpha_end, float values_translationY_first, float values_translationY_end, float values_translationX_first, float values_translationX_end, long duration) {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(target, "alpha", alpha_first, alpha_end);
        ObjectAnimator translationAnimator=ObjectAnimator.ofFloat(target,"translationY",values_translationY_first, values_translationY_end,values_translationX_first, values_translationX_end);

        alphaAndtranslationAnimatorSet = new AnimatorSet();

        alphaAndtranslationAnimatorSet.play(alphaAnimator).with(translationAnimator);
        alphaAndtranslationAnimatorSet.setDuration(duration).start();
    }

    /**
     * 位移和旋转的组合动画
     * @param target 动画对象
     * @param fromDegrees 初始旋转角度
     * @param toDegrees 结束时的旋转角度
     * @param values_translationY_first Y轴方向初始位置
     * @param values_translationY_end Y轴方向结束位置
     * @param values_translationX_first X轴方向初始位置
     * @param values_translationX_end X轴方向结束位置
     * @param duration 动画持续时间
     */
    public void scaleAndtranslationAnimator(Object target, float fromDegrees, float toDegrees, float values_translationY_first, float values_translationY_end, float values_translationX_first, float values_translationX_end, long duration) {
        ObjectAnimator rotateAnimator=ObjectAnimator.ofFloat(target,"rotation",fromDegrees,toDegrees);
        ObjectAnimator translationAnimator=ObjectAnimator.ofFloat(target,"translationY",values_translationY_first, values_translationY_end,values_translationX_first, values_translationX_end);

        scaleAndtranslationAnimatorSet = new AnimatorSet();

        scaleAndtranslationAnimatorSet.play(rotateAnimator).with(translationAnimator);
        scaleAndtranslationAnimatorSet.setDuration(duration).start();
    }

    /**
     * 透明动画
     * @param target 动画对象
     * @param alpha_first 初始透明值
     * @param alpha_end 结束时的透明值
     * @param duration 动画持续时间
     */
    public void alphaAnimator(Object target, float alpha_first, float alpha_end,long duration){
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(target, "alpha", alpha_first, alpha_end);

        alphaAnimatorSet = new AnimatorSet();
        alphaAnimatorSet.play(alphaAnimator);
        alphaAnimatorSet.setDuration(duration).start();
    }

    /**
     * 位移动画
     * @param target 动画对象
     * @param values_translationY_first Y轴方向初始位置
     * @param values_translationY_end Y轴方向结束位置
     * @param values_translationX_first X轴方向初始位置
     * @param values_translationX_end X轴方向结束位置
     * @param duration 动画持续时间
     */
    public void translationAnimator(Object target, float values_translationY_first, float values_translationY_end, float values_translationX_first, float values_translationX_end, long duration){
        ObjectAnimator translationAnimator=ObjectAnimator.ofFloat(target,"translationY",values_translationY_first, values_translationY_end,values_translationX_first, values_translationX_end);

        translationAnimatorSet = new AnimatorSet();

        translationAnimatorSet.play(translationAnimator);
        translationAnimatorSet.setDuration(duration).start();
    }

    /**
     * 缩放动画
     * @param target 动画对象
     * @param values_fromScaleX X轴方向初始倍数
     * @param values_toScaleX X轴方向结束倍数
     * @param values_fromScaleY Y轴方向初始倍数
     * @param values_toScaleY Y轴方向结束倍数
     * @param duration 动画持续时间
     */
    public void scaleAnimator(Object target, float values_fromScaleX, float values_toScaleX, float values_fromScaleY, float values_toScaleY, long duration) {
        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(target, "scaleX", values_fromScaleX, values_toScaleX);
        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(target, "scaleY", values_fromScaleY, values_toScaleY);

        scaleAnimatorSet = new AnimatorSet();

        scaleAnimatorSet.play(xAnimator).with(yAnimator);
        scaleAnimatorSet.setDuration(duration).start();
    }

    /**
     * 旋转动画
     * @param target 动画对象
     * @param fromDegrees 初始角度
     * @param toDegrees 旋转后的角度
     * @param duration 动画持续时间
     */
    public void rotateAnimation(Object target, float fromDegrees, float toDegrees, long duration,@Nullable Animator.AnimatorListener animatorListener){
        ObjectAnimator rotateAnimator=ObjectAnimator.ofFloat(target,"rotation",fromDegrees,toDegrees);

        rotateAnimationSet = new AnimatorSet();
        if(animatorListener!=null)
        {
            rotateAnimationSet.addListener(animatorListener);
        }
        rotateAnimationSet.play(rotateAnimator);
        rotateAnimationSet.setDuration(duration).start();
    }


}
