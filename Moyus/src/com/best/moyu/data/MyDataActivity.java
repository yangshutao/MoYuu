package com.best.moyu.data;

import java.io.File;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;

import com.best.moyu.baseactivity.BaseActivity;
import com.best.moyu.ui.ScanLocalPicture;
import com.example.moyu.R;

/*
 * 个人信息详情页
 * 其中点击头像可以更换头像
 * */
public class MyDataActivity extends BaseActivity implements OnClickListener{
	ImageView xiangxixinxi;
	RadioButton bianjiziliao;
	Button carema,album,give_up;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mydata);
		xiangxixinxi = (ImageView) findViewById(R.id.xiangxixinxi);
		bianjiziliao = (RadioButton) findViewById(R.id.bianjiziliao);
		bianjiziliao.setOnClickListener(this);
		xiangxixinxi.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch (arg0.getId()) {
		case R.id.bianjiziliao:
			Intent intent = new Intent(MyDataActivity.this,EditMyDataActivity.class);
			startActivity(intent);
			break;
		case R.id.xiangxixinxi:
			//调用虚拟更换头像按键
			showDialog();
			break;
		default:
			break;
		}
	}
	//更换头像点击事件
	private void showDialog() {
		View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog, null);
		carema = (Button) view.findViewById(R.id.camera);
		album = (Button) view.findViewById(R.id.album);
		give_up = (Button) view.findViewById(R.id.give_up);
		final Dialog dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
		dialog.setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		Window window = dialog.getWindow();
		// 设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = getWindowManager().getDefaultDisplay().getHeight();
		// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		dialog.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		dialog.setCanceledOnTouchOutside(true);
		carema.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				/**
				 * 下面这句还是老样子，调用快速拍照功能，至于为什么叫快速拍照，大家可以参考如下官方
				 * 文档，you_sdk_path/docs/guide/topics/media/camera.html
				 * 我刚看的时候因为太长就认真看，其实是错的，这个里面有用的太多了，所以大家不要认为
				 * 官方文档太长了就不看了，其实是错的，这个地方小马也错了，必须改正
				 */
				Intent intent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				//下面这句指定调用相机拍照后的照片存储的路径
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
						.fromFile(new File(Environment
								.getExternalStorageDirectory(),
								"xiaoma.jpg")));
				startActivityForResult(intent, 2);
			}
		});
		album.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(MyDataActivity.this,ScanLocalPicture.class);
				startActivity(intent);
			}
		});
		
		give_up.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				
			}
		});
		
		dialog.show();
	}

}
