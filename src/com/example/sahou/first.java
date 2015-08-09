package com.example.sahou;

import java.util.Timer;
import java.util.TimerTask;

import com.example.tools.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class first extends Activity{
	private WebView vv;
	private Handler mHandler=new Handler();
	private static Boolean isExit = false; 
	@Override
	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
	public void onCreate(Bundle ll){
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(ll);
		setContentView(R.layout.myapp);
		vv=(WebView)findViewById(R.id.clk);
		vv.getSettings().setJavaScriptEnabled(true);
		vv.setWebViewClient(new hello());
		/*vv.getSettings().setSavePassword(false);*/
		vv.getSettings().setSaveFormData(false);
		//vv.getSettings().setSupportZoom(true);	/*可以缩放*/
		//vv.getSettings().setBuiltInZoomControls(true);/*显示缩放控制器*/
		//vv.getSettings().setUseWideViewPort(true);/*扩大比例缩放*/
		//vv.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);/*自适应缩放*/
		vv.getSettings().setLoadWithOverviewMode(true);
		vv.setWebChromeClient(new tools());  /*设置webview的浏览器属性。让其支持浏览器方法*/
		vv.addJavascriptInterface(new DemoJavaScriptInterface(),"android"); 
		//vv.loadUrl("http://www.baidu.com");
		//vv.loadUrl("http://cdn.slowcc.cn/www/tc/list.html");
		vv.loadUrl("http://cdn.zzgdapp.com/mydemo/defenserate.html?v=123");
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//System.out.println(vv.canGoBack());
		//return true;
		// TODO Auto-generated method stub
		if(vv.canGoBack()){
			vv.goBack();
			return false;
		}else{
			boolean i=false;
			if(keyCode == KeyEvent.KEYCODE_BACK){    
		         exitBy2Click();      //调用双击退出函数  
		         i=false;
			}else if(keyCode==KeyEvent.KEYCODE_MENU){
				super.openOptionsMenu(); 
				i=true;
			}  
			return i;
		}		
	}
	@Override
	public void openOptionsMenu() {       
		super.openOptionsMenu();  
	} 
	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {   
		 super.onCreateOptionsMenu(menu); 
		 int group1 = 1;       
		 menu.add(group1, 1, 1, "退出程序"); 
		 return true;  
	 }     
	 
	  @Override    
	 
	 public boolean onOptionsItemSelected(MenuItem item) {  
		  switch (item.getItemId()) {  
		  	case 1:
		  	{
		  		new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_info)  
		  		.setTitle("确定退出")  
		  		.setMessage("确定退出吗")  
		  		.setPositiveButton("是", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						finish();  
				        android.os.Process.killProcess(android.os.Process.myPid());
					}
				})
		  		.setNegativeButton("否", null)  
		  		.show();  
		  		/* finish();  
			     android.os.Process.killProcess(android.os.Process.myPid());*/
		  		 break;
		  	}   
		  	default:  
		  	{
	         return super.onOptionsItemSelected(item);  
		  	}
	      }
		  return true;
	  }
	public void exitBy2Click(){
		  Timer tExit = null;  
		    if (isExit == false) {  
		        isExit = true; // 准备退出  
		        Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();  
		        tExit = new Timer();  
		        tExit.schedule(new TimerTask() {  
		            @Override  
		            public void run() {  
		                isExit = false; // 取消退出  
		            }  
		        }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务  
		  
		    } else {  
		        finish();  
		        android.os.Process.killProcess(android.os.Process.myPid());
		    }
	}
	private class hello extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) { 
			view.loadUrl(url);return true; 
		} 
	}
	final class DemoJavaScriptInterface {	
		DemoJavaScriptInterface (){}
		public void clickOnAndroid(){
			 mHandler.post(new Runnable() {  
	             @Override
				public void run() {/*调用js中的onJsAndroid方法  */
	                 vv.loadUrl("javascript:onJsAndroid()");  
	             }  
	         });  		
		}
	}	
}
