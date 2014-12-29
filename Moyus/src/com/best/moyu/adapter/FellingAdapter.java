package com.best.moyu.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.moyu.entity.Share;
import com.example.moyu.R;

public class FellingAdapter extends BaseAdapter {
		Context context;
		List<Share> shares;
		public FellingAdapter(Context context,List<Share> shares){
			this.context = context;
			this.shares = shares;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return shares.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return shares.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vh;
			if(convertView == null){
				convertView = LayoutInflater.from(context).inflate(R.layout.fragment_felling_item, null);
				vh = new ViewHolder();
				vh.touxiang = (ImageView) convertView.findViewById(R.id.image_touxiang);
				vh.nick = (TextView) convertView.findViewById(R.id.text_username);
				vh.xiangju = (TextView) convertView.findViewById(R.id.text_userjieshao);
				vh.xinqing = (TextView) convertView.findViewById(R.id.text_userxinqing);
				vh.zan = (Button) convertView.findViewById(R.id.zan);
				vh.cai = (Button) convertView.findViewById(R.id.cai);
				vh.pinglun = (Button) convertView.findViewById(R.id.pinglun);
				convertView.setTag(vh);
			}else {
				vh = (ViewHolder) convertView.getTag();
			}
			vh.touxiang.setImageResource(R.drawable.touxiang);
			vh.nick.setText(shares.get(position).getLoginUserID()+"");
			vh.xiangju.setText(shares.get(position).getShareLongitude()+"jingweid "+shares.get(position).getShareLatitude());
			vh.xinqing.setText(shares.get(position).getText());
			vh.zan.setText("赞（"+shares.get(position).getApproves()+"）");
			vh.cai.setText("赞（"+shares.get(position).getStamps()+"）");
			vh.pinglun.setText("赞（"+shares.get(position).getComments()+"）");
			return convertView;
		}
	class ViewHolder{
			ImageView touxiang;
			TextView nick;
			TextView xiangju;
			TextView xinqing;
			Button zan;
			Button cai;
			Button pinglun;
		}
}


