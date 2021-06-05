package com.example.game;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.SurfaceHolder; 

public class GameStart extends Activity {
	//@Override
	private StringBuilder data;
	LinearLayout layout;
	
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.gamestart);	
		/*
		ImageView img = (ImageView) findViewById( R.id.imageView1);
		Animation am = new TranslateAnimation(0,100,0,0);		          
		      
		am.setDuration(5000);//動畫開始到結束的時間，2秒
		am.setRepeatCount(-1); // 動畫重覆次數 (-1表示一直重覆，0表示不重覆執行，所以只會執行一次)
		img.setAnimation(am);//將動畫寫入ImageView
		am.startNow(); //開始動畫
        */
		setContentView(new SampleView(this));
		
		data=new StringBuilder();
        //註冊觸控監聽物件	

    }

	private static class SampleView extends View {		
		// CONSTRUCTOR
		Paint p = new Paint();
		int flag=0;
        int i=0;
        int j=0;
        int rnd1 =(int)(Math.random() * 150-75);
        int rnd2 = (int)(Math.random() * 150-75);;
        String x; //觸控X,Y
		String y; 
		int ch=0; //按下去朝上潮下
        int dolach=1;//按下去變大變小
        int dolachitmp=160;
        int dolatmp=160;
        
        int score=0;
        
		public boolean onTouchEvent(MotionEvent event) { 
			 // TODO Auto-generated method stub 					
			 //取得觸控點個數 
			 int pointTouched = event.getPointerCount(); 		 
			 
			 for(int i=0; i<pointTouched; i++) { 			
				 x = String.valueOf(event.getX(i)); 
				 y = String.valueOf(event.getY(i));
				 //取得個別觸控點之觸控位置座標 
			    /*x = event.getX(i); 
			    y = event.getY(i); */
			    if(event.getAction()==MotionEvent.ACTION_DOWN) { 
			    	ch=1;
			    } 
			 } 	
			 return true; 
		}
		

		public SampleView(Context context) {
			super(context);
			setFocusable(true);
		}
		
		@Override
		protected void onDraw(Canvas canvas) {		
			super.onDraw(canvas);
			 //canvas.drawText(x, 0, 0, p);
			 //canvas.drawText(y, 30, 10, p);
			
			Bitmap background=BitmapFactory.decodeResource(this.getResources(), R.drawable.background1);
			Bitmap dola=BitmapFactory.decodeResource(this.getResources(), R.drawable.doladown);		
			Bitmap dolaup=BitmapFactory.decodeResource(this.getResources(), R.drawable.dolaup);
			Bitmap tubeup=BitmapFactory.decodeResource(this.getResources(), R.drawable.tubeup);
			Bitmap tubedown=BitmapFactory.decodeResource(this.getResources(), R.drawable.tubedown);
			Bitmap gameover=BitmapFactory.decodeResource(this.getResources(), R.drawable.gameover);	
			canvas.drawColor(Color.WHITE);
			
			
			p.setAntiAlias(true);
			p.setColor(Color.RED);
			p.setStyle(Paint.Style.FILL); 
			/*
			Handler handler=new Handler();		
            Runnable runnable= new Runnable(){public void run(){invalidate();}}; 
            */
			//handler.postDelayed(runnable,100);
            try {
            	Thread.sleep(0);
            	} catch (InterruptedException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            	}//毫秒的意思 3000就是3秒 以此類推
            	      
			//canvas.drawCircle(10+i, 10, 10, p);
            /*
            if(flag<10){
			   canvas.drawBitmap(dola, 50, 120+(60-20+rnd1), null);
			   flag++;
			}
            else if(flag>=10&&flag<20){
			   canvas.drawBitmap(dolaup, 47, 105+(60-20+rnd1), null);
			   flag++;
            }
			else{
			   flag=0;
			}*/ 
            
            //背景
            canvas.drawBitmap(background, 0, 0, null);
            
            
            if(ch==1){    //----飛行中DOLA的位置      	    
            	    for(int i=0;i<=10;i++){          	    	
            	        canvas.drawBitmap(dolaup, 50, dolatmp-3, null);
            	    } 
        	        dolatmp=dolatmp-30;
            	    ch=0;
            }
            else{          	        
                        canvas.drawBitmap(dola, 50, dolatmp, null);
                        dolatmp=dolatmp+4*dolach;
            }
                       
            //----柱子
			canvas.drawBitmap(tubedown, 240-i, 120-320+rnd1, null);
			canvas.drawBitmap(tubeup, 240-i, 210+rnd1, null);
			canvas.drawBitmap(tubedown, 380-j, 120-320+rnd2, null);
			canvas.drawBitmap(tubeup, 380-j, 210+rnd2, null);
						
			i=i+2;
			j=j+2;
			if(i>=287){
				i=0;
				rnd1 = (int)(Math.random() * 150-75);
			};
			if(j>=427){
				j=i-20;
				rnd2 = (int)(Math.random() * 150-75);
			};
			
			//分數
			if(50==240-i ||50==380-j){
				score=score+100;
			}
			canvas.drawText("SCORE:"+score, 10, 20, p); 
			
			//GAMEOVER 137*65
			if(dolatmp>=320-50 ||( 50==240-i-70 && dolatmp<120-320+rnd1+320) ||(50==240-i-70 && dolatmp>210+rnd1)){
				canvas.drawBitmap(gameover, 27, 127, null);
				//canvas.drawBitmap(dola, 50, dolatmp+i, null);
			}
			else if(( 50==380-j-70 && dolatmp<120-320+rnd2+320) ||(50==380-j-70 && dolatmp>210+rnd2)){
				canvas.drawBitmap(gameover, 27, 127, null);
			}
			else if(50>=240-i-70 && 50<=52+240-i-10 && (dolatmp<=120-320+rnd1+320-20 || dolatmp>210+rnd1-40)){
				canvas.drawBitmap(gameover, 27, 127, null);
			}
			else if(50>=380-j-70 && 50<=52+380-j-10 && (dolatmp<=120-320+rnd2+320-20 || dolatmp>210+rnd2-40)){
				canvas.drawBitmap(gameover, 27, 127, null);
			}			
			else{
			    invalidate();
			}

		}		

	}	
}	
