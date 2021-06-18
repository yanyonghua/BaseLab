package com.aowang.baselib;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import java.util.List;

/**
 * @Author yanyonghua
 * @Date 2021/6/18-14:05
 * @Des $.
 */
public class PickerUtils {
    /**
     * 初始化选择弹出框
     * @param dataList 数据
     * @param context 上下文
     * @param listener 监听
     * @param <D>
     * @return
     */
    public static<D> OptionsPickerView initList(final List<D> dataList, Context context, final OnPickSelectListener listener) {
        if (dataList.size()>0){
            Resources resources = context.getResources();
            OptionsPickerView pvFeed = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    D object = dataList.get(options1);
                    listener.onSelect(options1,object, v);
                }
            })
                    .setTitleText(listener.getTitle())
                    .setContentTextSize(20)//设置滚轮文字大小
                    .setDividerColor(resources.getColor(R.color.color_line))//设置分割线的颜色
                    .setSelectOptions(0, 1)//默认选中项
                    .setBgColor(Color.WHITE)
                    .setTitleBgColor(resources.getColor(R.color.main_color))
                    .setTitleColor(Color.WHITE)
                    .setCancelColor(resources.getColor(R.color.item_wheel_title_text_color))
                    .setSubmitColor(resources.getColor(R.color.item_wheel_title_text_color))
                    .setTextColorCenter(Color.BLACK)
                    .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                    .setBackgroundId(0x00000000) //设置外部遮罩颜色
                    .build();
            pvFeed.setPicker(dataList);//一级选择器

            return pvFeed;
        }
        return null;
    }
    /**
     * 选择器得监听器
     */
    public interface OnPickSelectListener<D> {
        /**
         * 选择状态
         * @param options1 行数
         * @param object 选择得实体
         * @param v
         */
        void onSelect(int options1, D object, View v);
        String getTitle();
    }
}
