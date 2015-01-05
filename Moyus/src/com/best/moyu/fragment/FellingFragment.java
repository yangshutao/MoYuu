package com.best.moyu.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.best.moyu.activity.FellingAddActivity;
import com.best.moyu.activity.FellingDetailActivity;
import com.best.moyu.adapter.FellingAdapter;
import com.best.moyu.data.MyDataActivity;
import com.best.moyu.entity.Share;
import com.best.moyu.ui.FunctionMainActivity;
import com.example.moyu.R;

public class FellingFragment extends Fragment implements OnPageChangeListener, OnClickListener{
	private ViewPager vp;
	View v;
	ImageView touxiang;
	Button fabu;
	ListView lv_all,lv_zuijin,lv_tuisong;
	
	private List<View> mviews = new ArrayList<View>();
	int[] layout ={R.layout.activity_detail_1,R.layout.activity_detail_2,
			R.layout.activity_detail_3};
	//标题名集合
	private List<TextView> title_texts = new ArrayList<TextView>();
	int [] v_t0 = {R.id.titli_text_1,R.id.titli_text_2,
			R.id.titli_text_3};
	//标题View集合
	private List<View> title_views = new ArrayList<View>();
	int[] v_v0={R.id.titli_view_1,R.id.titli_view_2,R.id.titli_view_3};
	List<Share> shares = new ArrayList<Share>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_felling, container,false);
		touxiang = (ImageView) v.findViewById(R.id.touxiang);
		fabu = (Button) v.findViewById(R.id.fabu);
		vp= (ViewPager) v.findViewById(R.id.detail_pager);
		LayoutInflater l = getActivity().getLayoutInflater();
		for(int i=0;i<3;i++){
			mviews.add(l.inflate(layout[i],null));
			//添加数据
			title_texts.add((TextView)v.findViewById(v_t0[i]));		
			title_views.add((View) v.findViewById(v_v0[i]));
		}
		vp.setAdapter(new MyPagerAdapter());
		vp.setCurrentItem(1);
		//设置监听
		vp.setOnPageChangeListener(this);
		lv_all = (ListView) mviews.get(0).findViewById(R.id.listView_all);
		lv_zuijin = (ListView) mviews.get(1).findViewById(R.id.listView_zuijin);
		lv_tuisong = (ListView) mviews.get(2).findViewById(R.id.listView_tuisong);
		
		title_texts.get(1).setTextColor(Color.WHITE);
		title_views.get(1).setBackgroundColor(Color.WHITE);
		BmobQuery<Share> shares_all = new BmobQuery<Share>();
		
		shares_all.findObjects(getActivity(), new FindListener<Share>(){

			@Override
			public void onError(int arg0, String arg1) {}

			@Override
			public void onSuccess(List<Share> arg0) {
				
				shares = arg0;
			}
			
		});
		lv_all.setAdapter(new FellingAdapter(FunctionMainActivity.context,shares));
		lv_all.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//点击跳转到心情详情页
				Intent intent  = new Intent(FunctionMainActivity.context,FellingDetailActivity.class);
				intent.putExtra("username", shares.get(arg2).getLoginUserID()+"");
				intent.putExtra("biaoshi", shares.get(arg2).getBiaoshi());
				intent.putExtra("sharetime", shares.get(arg2).getShareTime());
				intent.putExtra("text", shares.get(arg2).getText());
				startActivity(intent);
			}
		});
		touxiang.setOnClickListener(this);
		fabu.setOnClickListener(this);
		return v;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.touxiang:
			//个人信息详情页
			Intent intent  = new Intent(FunctionMainActivity.context,MyDataActivity.class);
			startActivity(intent);
			break;
		case R.id.fabu:
			//个人心情发布页
			Intent intent2  = new Intent(FunctionMainActivity.context,FellingAddActivity.class);
			startActivity(intent2);
			break;
		default:
			break;
		}
		
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		for(TextView tv:title_texts){
			tv.setTextColor(Color.BLACK);
		}
		for(View v:title_views){
			v.setBackgroundColor(Color.BLACK);
		}
		title_texts.get(arg0).setTextColor(Color.WHITE);
		title_views.get(arg0).setBackgroundColor(Color.WHITE);
		if(arg0 == 0){
			/*BmobQuery<Share> shares_zuijin = new BmobQuery<Share>();
			shares_zuijin.findObjects(getActivity(), new FindListener<Share>(){

				@Override
				public void onError(int arg0, String arg1) {}

				@Override
				public void onSuccess(List<Share> arg0) {
					lv_zuijin.setAdapter(new FellingAdapter(FunctionMainActivity.context,arg0));
				}
				
			});*/
			lv_zuijin.setAdapter(new FellingAdapter(FunctionMainActivity.context,shares));
			lv_zuijin.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					//  点击跳转到心情详情页
					Intent intent  = new Intent(FunctionMainActivity.context,FellingDetailActivity.class);
					startActivity(intent);
				}
			});
		}
		if(arg0 == 1){
			BmobQuery<Share> shares_all = new BmobQuery<Share>();
			shares_all.findObjects(getActivity(), new FindListener<Share>(){

				@Override
				public void onError(int arg0, String arg1) {}

				@Override
				public void onSuccess(List<Share> arg0) {
					lv_all.setAdapter(new FellingAdapter(FunctionMainActivity.context,arg0));
				}
				
			});
			lv_all.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					//  点击跳转到心情详情页
					Intent intent  = new Intent(FunctionMainActivity.context,FellingDetailActivity.class);
					startActivity(intent);
				}
			});
		}
		if(arg0 == 2){
			BmobQuery<Share> shares_tuisong = new BmobQuery<Share>();
			shares_tuisong.findObjects(getActivity(), new FindListener<Share>(){

				@Override
				public void onError(int arg0, String arg1) {}

				@Override
				public void onSuccess(List<Share> arg0) {
					lv_tuisong.setAdapter(new FellingAdapter(FunctionMainActivity.context,arg0));
				}
				
			});
			lv_tuisong.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					//  点击跳转到心情详情页
					Intent intent  = new Intent(FunctionMainActivity.context,FellingDetailActivity.class);
					startActivity(intent);
				}
			});
		}
	}
	class MyPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mviews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 ==arg1;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			//super.destroyItem(container, position, object);
			container.removeView(mviews.get(position));
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			//return super.instantiateItem(container, position);
			View v = mviews.get(position);		
			container.addView(v, 0);
			return v;
		}
	}
}
