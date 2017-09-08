package huantai.smarthome.adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gizwits.gizwifisdk.api.GizWifiDevice;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import huantai.smarthome.bean.ControlDataible;
import huantai.smarthome.control.HomeFragment;
import huantai.smarthome.initial.R;
import huantai.smarthome.view.TextViewHolder;

public class AddRemoveNumberedAdapter extends RecyclerView.Adapter<TextViewHolder>{
  private static final int ITEM_VIEW_TYPE_ITEM = 0;
  private static final int ITEM_VIEW_TYPE_ADD = 1;

  private List<String> labels;

  public AddRemoveNumberedAdapter(int count) {
    labels = new ArrayList<String>(count);
    for (int i = 0; i < count; ++i) {
      labels.add(String.valueOf(i));
    }
  }

  @Override
  public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(
            viewType == ITEM_VIEW_TYPE_ADD ? R.layout.item_add : R.layout.activity_item, parent, false);
    return new TextViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final TextViewHolder holder, final int position) {
    if (position == labels.size()) {
      holder.tv_title.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          addItem();
        }
      });
      return;
    }

    //获取图片资源文件
    holder.iv_icon.setImageResource(R.drawable.home_images);
    if (position == 0){
      holder.tv_title.setText("温度");
      holder.tv_content.setText("60");
//      holder.iv_icon.setImageResource(R.drawable.home_icon_tenperature);
      holder.iv_icon.setImageLevel(0);

    }
    if (position == 1){
      holder.tv_title.setText("湿度");
      holder.tv_content.setText("80");
      holder.iv_icon.setImageLevel(1);
    }
//    final String label = labels.get(position);
//    holder.tv_title.setText(label);

    holder.tv_title.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        removeItem(holder.getPosition());
      }
    });
  }

  private void addItem() {
    if (labels.size() >=1){
      int lastItem = Integer.parseInt(labels.get(labels.size() - 1));
      labels.add(String.valueOf(lastItem + 1));
      notifyItemInserted(labels.size() - 1);
    } else {
      labels.add(new String("0"));
      notifyItemInserted(0);
    }

  }

  private void removeItem(int position) {
    labels.remove(position);
    notifyItemRemoved(position);
  }

  @Override
  public int getItemViewType(int position) {
    return position == labels.size() ? ITEM_VIEW_TYPE_ADD : ITEM_VIEW_TYPE_ITEM;
  }

  @Override
  public int getItemCount() {
    return labels.size()+1;
  }

}


//package com.sqisland.android.recyclerview;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AddRemoveNumberedAdapter extends RecyclerView.Adapter<TextViewHolder> {
//  private static final int ITEM_VIEW_TYPE_ITEM = 0;
//  private static final int ITEM_VIEW_TYPE_ADD = 1;
//
//  //控制item数量
//  private List<String> labels;
//
//  public AddRemoveNumberedAdapter(int count) {
//    labels = new ArrayList<String>(count);
//    for (int i = 0; i < count; ++i) {
//      labels.add(String.valueOf(i));
//    }
//  }
//
//  //注入item布局
//  @Override
//  public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//    View view = LayoutInflater.from(parent.getContext()).inflate(
//            viewType == ITEM_VIEW_TYPE_ADD ? R.layout.item_add : R.layout.activity_item, parent, false);
//    return new TextViewHolder(view);
//  }
//
//  //对item进行增删
//  @Override
//  public void onBindViewHolder(final TextViewHolder holder, final int position) {
////    if (position == labels.size()) {
////      holder.tv_content.setOnClickListener(new View.OnClickListener() {
////        @Override
////        public void onClick(View v) {
////          addItem();
////        }
////      });
////      return;
////    }
////    final String label = labels.get(position);
//    holder.tv_content.setText("465465");
//    holder.tv_title.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            removeItem(holder.getPosition());
//        }
//    });
//  }
//
//  private void addItem() {
//    if (labels.size() >=1){
//        int lastItem = Integer.parseInt(labels.get(labels.size() - 1));
//        labels.add(String.valueOf(lastItem + 1));
//        notifyItemInserted(labels.size() - 1);
//    } else {
//        labels.add(new String("0"));
//        notifyItemInserted(0);
//    }
//
//  }
//
//  private void removeItem(int position) {
//    labels.remove(position);
//    notifyItemRemoved(position);
//  }
//
//  @Override
//  public int getItemViewType(int position) {
//    return position == labels.size() ? ITEM_VIEW_TYPE_ADD : ITEM_VIEW_TYPE_ITEM;
//  }
//
//  @Override
//  public int getItemCount() {
//    return labels.size() + 1;
//  }
//}