package com.tensun.scalinglayoutdemo2;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import iammert.com.view.scalinglib.ScalingLayout;

/**
 * Created by mertsimsek on 30/09/2017.
 */

public class ScalingLayoutBehavior extends CoordinatorLayout.Behavior<ScalingLayout> {

    private final float toolbarHeightInPixel;

    public ScalingLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        toolbarHeightInPixel =
                context.getResources()
                        .getDimensionPixelSize(iammert.com.view.scalinglib.R.dimen.sl_toolbar_size);   // 透過getDimensionPixelSize(), 直接取得R.dimen.sl_toolbar_size的數值
    }

    /**
     * 只要是CoordinatorLayout内的View的状态发送了变化, 该方法就会执行
     * @param parent 顶层父控件CoordinatorLayout
     * @param child 我们设置这个Behavior的View
     * @param dependency View的值会不断的变化, 他会轮询CoordinatorLayout下所有所属的子View
     * @return 我们需要在这里判断dependency所属的View是哪一个, 如果是ture 执行onDependentViewChanged, false则不执行
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ScalingLayout child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    /**
     * 改變dependency
     * dependency是我們想要連動改變的View的原來範圍, 當View完全離開這塊範圍, 也就是被完全取代時, 會停止此方法
     * @param parent 顶层父控件CoordinatorLayout
     * @param child 我们设置这个Behavior的View
     * @param dependency View的值会不断的变化, 他会轮询CoordinatorLayout下所有所属的子View
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ScalingLayout child, View dependency) {

        int totalScrollRange = ((AppBarLayout) dependency).getTotalScrollRange();                   // 获取dependency 可以滑动的距离
        child.setProgress((-dependency.getY()) / totalScrollRange);                                 // 對於ScalingLayout 外觀變化的控制, 由橢圓形變成長方形

        if (totalScrollRange + dependency.getY() > (float) child.getMeasuredHeight() / 2) {         // 當dependency 還有保留 想要保留的範圍時
            child.setTranslationY(                                                                  // 保留展示ToolBar的空間
                    totalScrollRange + dependency.getY()
                            + toolbarHeightInPixel - (float) child.getMeasuredHeight() / 2
            );
        } else {                                                                                    // 當dependency 觸碰到想要保留的界線時
            child.setTranslationY(toolbarHeightInPixel);                                            // child 將根據toolbarHeightInPixel的值, 做出對應的Y軸移動
        }

        return super.onDependentViewChanged(parent, child, dependency);
    }
}
