package com.example.coco.searchview;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by coco on 2017/11/6.
 */

public class SearchUtils {
    public static void handleToolBar(final Context context, final CardView search, EditText editText){
        if (search.getVisibility()== View.VISIBLE){//如果搜索框显示
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){//5.0以上
                final Animator animatorHide = ViewAnimationUtils.createCircularReveal(search,
                        search.getWidth() -  dip2px(context, 56),
                        dip2px(context, 23),
                        //确定圆的半径（算长宽的斜边长，这样半径不会太短也不会很长效果比较舒服）
                        (float) Math.hypot(search.getWidth(), search.getHeight()),
                        0);
                animatorHide.addListener(new Animator.AnimatorListener() {//给动画添加监听
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {//在动画结束的时候让搜索框隐藏
                        search.setVisibility(View.GONE);
                        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(search.getWindowToken(), 0);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animatorHide.setDuration(300);//设置动画持续时间
                animatorHide.start();//设置动画开启
            }else {//不是5.0以上
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(search.getWindowToken(), 0);
                search.setVisibility(View.GONE);//搜索框隐藏
            }
            editText.setText("");//编辑框设置字体
            search.setEnabled(false);//搜索框不可点击
        }else {//搜索框隐藏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){//大于5.0
                final Animator animator = ViewAnimationUtils.createCircularReveal(search,
                        search.getWidth() - dip2px(context, 56),
                        dip2px(context, 23),
                        0,
                        (float) Math.hypot(search.getWidth(), search.getHeight()));
                animator.addListener(new Animator.AnimatorListener() {//添加动画
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {//关闭输入法弹窗
                        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                                .toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                search.setVisibility(View.VISIBLE);
                if (search.getVisibility() == View.VISIBLE) {
                    animator.setDuration(300);
                    animator.start();//动画开启
                    search.setEnabled(true);//搜索框显示的时候可点击
                }
            }else {
                search.setVisibility(View.VISIBLE);
                search.setEnabled(true);
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                        .toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }
    }

    public static int dip2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5);
    }
}
