package com.best.moyu.fragment;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.moyu.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MeetFragment extends Fragment {
	//创建页面通知
	private Toast mToast; 
	
	
	private BMapManager mBMapManager;  
	 
	
	/** 
	 * MapView 是地图主控件 
	 * MapView就是我们的地图控件了，MapView有和Activity同步的生命周期，例如onResume() ，
	 * onPause() ，onRestoreInstanceState(Bundle state) ，destroy()等，
	 * 我们可以通过getController()方法获取地图控制器MapController，这个对象可用于控制和驱动平移和缩放等
	 */  
	private MapView mMapView = null; 
	/**
	 *  用MapController(地图控制器 [kənˈtroʊlə(r)] )来完成地图的控制
	 */
	private MapController mMapController = null;  
	/**
	 * 用MKMapViewListener来处理地图事件的回调
	 * 地图监听器
	 * MapView有两个接口可以注册，分别是MKMapTouchListener（地图点击事件监听器），
	 * MKMapViewListener（地图监听器）
	 */
	
	
	MKMapViewListener mMapListener = null;  
	
	
	
	
	
	@Override
	public android.view.View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, Bundle savedInstanceState) {
		
		
	
		
		/** 
		  * 使用地图sdk前需先初始化BMapManager，这个必须在setContentView()先初始化 
		  */    
		mBMapManager = new BMapManager(getActivity());
		/**
		 * 
		 * BMapManager是地图的引擎类，这个必须在setContentView方法之前被实例化，
		 * 我们需要使用其方法init(String strKey,MKGeneralListener listener)来加入API key，
		 *  MKGeneralListener  该接口返回网络状态，授权验证等结果，我们需要实现该接口以处理相应事件
		 * */
		//第一个参数是API  key
		////第二个参数是常用事件监听，用来处理通常的网络错误，授权验证错误等，你也可以不添加这个回调接口  
		mBMapManager.init("sB2ivba5ws4ykzTl8MyUh0w9", new MKGeneralListener() {
			
			@Override
			public void onGetPermissionState(int arg0) {
				// TODO Auto-generated method stub
				//授权错误的时候调用的回调函数    
				
			    if (arg0 ==  MKEvent.ERROR_PERMISSION_DENIED) {  
			    	    
			    	Toast.makeText(getActivity(),"API  Key閿欒",Toast.LENGTH_LONG).show(); 
			       }  

			}
			 //一些网络状态的错误处理回调函数 
			@Override
			public void onGetNetworkState(int arg0) {
				// TODO Auto-generated method stub
				  if (arg0 == MKEvent.ERROR_NETWORK_CONNECT){  
					       Toast.makeText(getActivity(), "鎮ㄧ殑缃戠粶鍑洪敊鍟︼紒", Toast.LENGTH_LONG).show();  
					 }  

			}
		});
		
		View v = inflater.inflate(R.layout.fragment_meet,container,false);
	   
	    

		
		mMapView = (MapView)v.findViewById(R.id.bmapView);  
			
		//获取地图控制器 
		mMapController = mMapView.getController();  
		
		//设置地图是否相应点击事件
		mMapController.enableClick(true);
		
		//设置地图缩放级别
		
		mMapController.setZoom(12);
		
		//显示内置缩放控件
		
		mMapView.setBuiltInZoomControls(true); 
		
		//保存经度和纬度的类（类是包内的）
		GeoPoint p = new GeoPoint((int)(22.547923 * 1E6), (int)(114.067368 * 1E6));  
		
		//设置p地方为中心点
		
		mMapController.setCenter(p);  
		//注册地图移动时监听事件
		mMapView.regMapViewListener(mBMapManager,new MKMapViewListener() {
			
			
			//地图移动完成时调用此接口方法
			@Override
			public void onMapMoveFinish() {
				// TODO Auto-generated method stub
				
				Toast.makeText(getActivity(),  "地图移动完毕", Toast.LENGTH_LONG).show(); 
				
			}
			//地图加载完毕，回调此接口方法
			@Override
			public void onMapLoadFinish() {
				// TODO Auto-generated method stub
				
				Toast.makeText(getActivity(),"地图载入完毕"	, Toast.LENGTH_LONG).show(); 
				
			}
			//地图完成带动画的操作后调用如（animationto）,此回调被触发
			@Override
			public void onMapAnimationFinish() {
				// TODO Auto-generated method stub
				
				
				
			}
			
		   // 当调用过 mMapView.getCurrentMap()后，此回调会被触发  可在此保存截图至存储设备  

			@Override
			public void onGetCurrentMap(Bitmap arg0) {
				// TODO Auto-generated method stub
				
				
			}
			
			//点击地图上被标记的点回到此方法
			@Override
			public void onClickMapPoi(MapPoi arg0) {
				// TODO Auto-generated method stub
				  if (arg0 != null){  
					  Toast.makeText(getActivity(), arg0.strText, Toast.LENGTH_LONG).show();  
				  }  

				
			}
		 });
		
		return v;	
		
	
		
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		//MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()  
		mMapView.onResume();  
		super.onResume();
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		mMapView.onPause();
		super.onPause();
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
		mMapView.destroy();
		//退出应用调用BMapManager的destroy()方法    
	   if(mBMapManager != null){  
		    mBMapManager.destroy();  
		    mBMapManager = null;  
	     }  

		
		super.onDestroy();
		
	}
	
}
