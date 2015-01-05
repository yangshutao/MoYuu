package com.best.moyu.activity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

import com.best.moyu.adapter.PicAdapter;
import com.best.moyu.baseactivity.BaseActivity;
import com.best.moyu.entity.Pic;
import com.best.moyu.entity.Share;
import com.best.moyu.utils.DataUtils;
import com.best.moyu.utils.ImageUtils;
import com.best.moyu.utils.SDCardUtils;
import com.example.moyu.R;

public class FellingAddActivity extends BaseActivity implements OnClickListener{
	Button quxiao,tijiao,tupian,yuyin,xinqing,shipin,yinyue,weizhi,haoyouquan;
	EditText et;
	Share share;
	public int yuyins =0; 
	static ImageView iv;
	View vg;
	String texts;
	int biaoshifu;
	static AlertDialog alertlist;
	static GridView gv;
    private String mAppDir;//存储文件夹目录地址;
    private String mNewImagePath;//拍照得到的新图片地s址;
    private MediaRecorder mRecorder;
    private File mRecAudioFile;

    private final int TAKE_CAMERA_CODE = 0X0001;//打开相机请求码;
    private final int TAKE_PHOTOS_CODE = 0X0002;//打开图库请求码;
    
    private final String APP_SERVICE_KEK = "6a82445c5203b53ba1acffbf57d2f5cb";
   
    public static final int UPLOAD_WORD = 0X0011;//表示上传文字心情;
    public static final int UPLOAD_IMAGE = 0X0012;//表示上传图片心情;
    public static final int UPLOAD_MUSIC = 0X0013;//表示上传音乐心情;
    public static final int UPLOAD_VIDEO = 0X0014;//表示上传视频心情;
    public static final int UPLOAD_SOUND = 0X0016;//表示上传视频心情;
    private static final int UPLOAD_END = 0X0015;//表示文件上传完毕;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_felling_add);
		et = (EditText) findViewById(R.id.miaoshu);
		quxiao = (Button) findViewById(R.id.quxiao);
		tijiao = (Button) findViewById(R.id.tijiao);
		tupian = (Button) findViewById(R.id.tupian);
		yuyin = (Button) findViewById(R.id.yuyin);
		xinqing = (Button) findViewById(R.id.xinqing);
		shipin = (Button) findViewById(R.id.shipin);
		yinyue = (Button) findViewById(R.id.yinyue);
		weizhi = (Button) findViewById(R.id.weizhi);
		
		haoyouquan = (Button) findViewById(R.id.haoyouquan);
		iv = (ImageView) findViewById(R.id.imageView_tupian);
		
		quxiao.setOnClickListener(this);
		tijiao.setOnClickListener(this);
		tupian.setOnClickListener(this);
		yuyin.setOnClickListener(this);
		xinqing.setOnClickListener(this);
		shipin.setOnClickListener(this);
		yinyue.setOnClickListener(this);
		weizhi.setOnClickListener(this);
		haoyouquan.setOnClickListener(this);
		Bmob.initialize(this, APP_SERVICE_KEK);
		addActivity(this);
		
		share = new Share();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.quxiao:
			//取消
			FellingAddActivity.this.finish();
			break;
		case R.id.tijiao:
			//提交
			texts = et.getText().toString();
			if(!"".equals(texts)){
				share.setText(texts);
			}
			/*share.setText(texts);
			if(biaoshifu == UPLOAD_WORD){
				uploadData(share);
			}else */
			if(biaoshifu == UPLOAD_IMAGE){
				if (!"".equals(mNewImagePath)) {
					 share.setImage(mNewImagePath);
					 Toast.makeText(FellingAddActivity.this, "lalas", Toast.LENGTH_SHORT).show();
					 if (share != null) {
						 Toast.makeText(FellingAddActivity.this, "不空", Toast.LENGTH_SHORT).show();
		                    uploadData(share);
		                   
		                } else {
		                	Toast.makeText(FellingAddActivity.this, "空", Toast.LENGTH_SHORT).show();
		                }
				}
				
						
				 
			}else if(biaoshifu == UPLOAD_MUSIC){
				
				share.setMusic("");
			}else if(biaoshifu == UPLOAD_VIDEO){
				
				share.setVideo("");
			}else if(biaoshifu == UPLOAD_SOUND){
				
				share.setSound("");
			}else{
				if (share != null) {
                    uploadData(share);
                    Toast.makeText(FellingAddActivity.this, share.getText(), Toast.LENGTH_SHORT).show();
                } else {
                	Toast.makeText(FellingAddActivity.this, "空", Toast.LENGTH_SHORT).show();
                }
			}
			break;
		case R.id.tupian:
			//添加图片
			biaoshifu = UPLOAD_IMAGE;
			//打开图库
			Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, TAKE_PHOTOS_CODE);
            //打开相机
			break;
		case R.id.yuyin:
			//添加语音
			yuyins+=1;
			if(yuyins%2==1){
				yuyinStart();
				yuyin.setText("点击结束");
			}else{
				yuyinEnd();
				yuyin.setText("点击说话");
			}
			break;
		case R.id.xinqing:
			biaoshifu = UPLOAD_WORD;
			selectReDianList();
			//添加表情
			break;
		case R.id.shipin:
			//添加视频
			break;
		case R.id.yinyue:
			//添加音乐
			break;
		case R.id.weizhi:
			//定位
			break;
		case R.id.haoyouquan:
			//好友圈
			break;
		default:
			break;
		}
		
	}
	//上传文件;
    private boolean uploadData(Share share) {  //传入分享心情类;
        boolean iSucceed = false;
        share.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(FellingAddActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(FellingAddActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
            }
        });

        return iSucceed;
    }
    //显示表情图片
    public void selectReDianList(){
		AlertDialog.Builder builder1 = new  AlertDialog.Builder(FellingAddActivity.this);
		builder1.setCancelable(true);
		vg = getLayoutInflater().inflate(R.layout.fragment_felling_add_biaoqing, null);
		builder1.setView(vg);
		alertlist = builder1.create();
		alertlist.show();
		final int[] biaoqings = new int[]{R.drawable.f000,
									R.drawable.f001,R.drawable.f002,R.drawable.f003,R.drawable.f004,R.drawable.f005,
									R.drawable.f006,R.drawable.f007,R.drawable.f008,R.drawable.f009,R.drawable.f010,
									R.drawable.f011,R.drawable.f012,R.drawable.f013,R.drawable.f014,R.drawable.f015,
									R.drawable.f016,R.drawable.f017,R.drawable.f018,R.drawable.f019,R.drawable.f020,
									R.drawable.f021,R.drawable.f022,R.drawable.f023,R.drawable.f024,R.drawable.f025,
									R.drawable.f026,R.drawable.f027,R.drawable.f028,R.drawable.f029,R.drawable.f030,
									R.drawable.f031,R.drawable.f032,R.drawable.f033,R.drawable.f034,R.drawable.f035,
									R.drawable.f036,R.drawable.f037,R.drawable.f038,R.drawable.f039,R.drawable.f040,
									R.drawable.f041,R.drawable.f042,R.drawable.f043,R.drawable.f044,R.drawable.f045,
									R.drawable.f046,R.drawable.f047,R.drawable.f048,R.drawable.f049,R.drawable.f050,
									R.drawable.f051,R.drawable.f052,R.drawable.f053,R.drawable.f054,R.drawable.f055,
									R.drawable.f056,R.drawable.f057,R.drawable.f058,R.drawable.f059,R.drawable.f060,
									R.drawable.f061,R.drawable.f062,R.drawable.f063,R.drawable.f064,R.drawable.f065,
									R.drawable.f066,R.drawable.f067,R.drawable.f068,R.drawable.f069,R.drawable.f070,
									R.drawable.f071,R.drawable.f072,R.drawable.f073,R.drawable.f074,R.drawable.f075,
									R.drawable.f076,R.drawable.f077,R.drawable.f078,R.drawable.f079,R.drawable.f080,
									};
		List<Pic> pics= new ArrayList<Pic>();
		Pic pic = null;
		for(int i = 0 ;i<81;i++){
			pic = new Pic(biaoqings[i]+"");
			pics.add(pic);
		}
		gv = (GridView) vg.findViewById(R.id.gridView_list);
		gv.setAdapter(new PicAdapter(this,pics));
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//texts = et.getText().toString()+"|"+biaoqings[arg2]; 
				//et.setText(texts);
				
				final SpannableString ss = new SpannableString("[easy]");   
		         //得到drawable对象，即所要插入的图片    
		         Drawable d = getResources().getDrawable(biaoqings[arg2]);   
		         d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());   
		         //用这个drawable对象代替字符串easy    
		         ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);   
		         //包括0但是不包括"easy".length()即：4。[0,4)。值得注意的是当我们复制这个图片的时候，实际是复制了"easy"这个字符串。    
		         ss.setSpan(span, 0, "[easy]".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);   
		         et.append(ss); 
				
				/*
				 * 
				 * final int id = biaoqings[arg2];
				ImageGetter imageGetter = new ImageGetter() {  
		                @Override
		               public Drawable getDrawable(String source) {
		               

		              //根据id从资源文件中获取图片对象
		               Drawable d = getResources().getDrawable(id);
		               d.setBounds(0, 0, d.getIntrinsicWidth(),d.getIntrinsicHeight());
		                return d;
		               }
		        };        
		        et.append(Html.fromHtml("<img src='"+id+"'/>", imageGetter, null));
*/
				
			}
		});
		vg.findViewById(R.id.quxiao_list).setOnClickListener(new OnClickListener() {
			@Override  
			public void onClick(View v) {			
				alertlist.hide();
			}
		});
		
	}
    public void yuyinStart() {//语音————开始录音
		if (SDCardUtils.isExistSDCard()) {
			File mRecAudioPath = Environment.getExternalStorageDirectory();
			File fPath = new File("/sdcard/Moyu/" + File.separator
					+ "Audio");
			fPath.mkdirs();

			mRecAudioFile = fPath;
		} else {
			Toast.makeText(this, "请插入SDcard", 0).show();

			return;
		}

		try {
			mRecAudioFile = File.createTempFile(String.valueOf("tmp_record"),
					".amr", mRecAudioFile);
		} catch (IOException e) {
			Log.e("Jarvis", "mRec", e);
		}

		// instance
		mRecorder = new MediaRecorder();
		// 设置麦克风
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		// 输出文件格式
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		// 音频文件编码
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
		// 输出文件路径
		mRecorder.setOutputFile(mRecAudioFile.getAbsolutePath());
		Log.e("Jarvis", "输出文件路径:" + mRecAudioFile.getAbsolutePath());

		// 准备--开始
		try {
			mRecorder.prepare();
			mRecorder.start();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public void yuyinEnd() {//语音————结束录音
		if (mRecorder != null) {
			mRecorder.stop();
			mRecAudioFile.getName();
			Log.e("Jarvis", "Name:" + mRecAudioFile.getName() + "~"
					+ mRecAudioFile.getAbsolutePath());
			mRecorder.release();
			mRecorder = null;
		}
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //相机拍照或图库获取图片后接收地址并上传;
        if (requestCode == TAKE_CAMERA_CODE && resultCode == RESULT_OK) {
            Toast.makeText(this, mNewImagePath, Toast.LENGTH_SHORT).show();
            ImageView iv = (ImageView) this.findViewById(R.id.imageView_tupian);
            ImageUtils.viewLocalImage(iv, 8, mNewImagePath);
        } else {
            if (requestCode == TAKE_PHOTOS_CODE && resultCode == RESULT_OK) {
                Uri uri = data.getData();
                String[] pogo = {MediaStore.Images.Media.DATA};
                Cursor cursor = managedQuery(uri, pogo, null, null, null);
                if (cursor != null) {
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    mNewImagePath = cursor.getString(columnIndex);
                    Toast.makeText(FellingAddActivity.this, mNewImagePath, Toast.LENGTH_SHORT).show();
                    ImageView iv = (ImageView) this.findViewById(R.id.imageView_tupian);
                    ImageUtils.viewLocalImage(iv, 8, mNewImagePath);
                    share.setImage(mNewImagePath);
                } else {
                }
            }
        }
    }
    private class ShowCamera implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //创建存储文件夹;
            if (SDCardUtils.isExistSDCard()) {
                mAppDir = SDCardUtils.createAppDir();
            } else {
                mAppDir = FellingAddActivity.this.getFilesDir().getPath();
            }
            //打开相机,并存储拍的照片;
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            mNewImagePath = mAppDir + "/" + DataUtils.formatDate(new Date()) + ".jpg";
            File file = new File(mNewImagePath);
            Uri uri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, TAKE_CAMERA_CODE);
        }
    }
}
