package com.example.sahou;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;

public class StartActivity extends Activity {
	@Override
	protected void onCreate(Bundle start) {
		super.onCreate(start);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);		
		/*Bitmap bitmap = getHttpBitmap("http://cdn.zzgdapp.com/trade/mobile/images/loding.gif");
		ImageView imv = (ImageView)findViewById(R.id.imageView1);
		imv.setImageBitmap(bitmap);*/
		Timer load = new Timer();
		load.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				Intent it = new Intent(StartActivity.this,first.class);
				startActivity(it);
				StartActivity.this.finish();
			}
		},2000);
		/*ImageButton btn = (ImageButton)findViewById(R.id.start_buttom);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent it = new Intent(StartActivity.this,first.class);
				startActivity(it);
				StartActivity.this.finish();
			}
		});*/
		
	}	
	 /** 
     * 获取网落图片资源  
     * @param url 
     * @return 
     */  
    public static Bitmap getHttpBitmap(String url){  
        URL myFileURL;  
        Bitmap bitmap=null;  
        try{  
            myFileURL = new URL(url);  
            //获得连接  
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();  
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制  
            conn.setConnectTimeout(6000);  
            //连接设置获得数据流  
            conn.setDoInput(true);  
            //不使用缓存  
            conn.setUseCaches(false);  
            //这句可有可无，没有影响  
            //conn.connect();  
            //得到数据流  
            InputStream is = conn.getInputStream();  
            //解析得到图片  
            bitmap = BitmapFactory.decodeStream(is);  
            //关闭数据流  
            is.close();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
          
        return bitmap;  
          
    } 
}
