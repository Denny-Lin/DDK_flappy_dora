package com.example.game;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	private ImageButton Start;
	private ImageButton Exit;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Start= (ImageButton)findViewById(R.id.imageButton1);
		Exit = (ImageButton)findViewById(R.id.imageButton2);
		//layout1 = (FrameLayout)findViewById(R.id.frameLayout1);
		Start.setOnClickListener(gamestart_form_show);	
		
		Exit.setOnClickListener(new OnClickListener()
	    {
	        @Override
	        public void onClick(View v)
	        {
	            // TODO Auto-generated method stub
	            finish();
	            System.exit(0);
	        }
	    });
		
	}

	private OnClickListener gamestart_form_show = new OnClickListener()
	{
	    @Override
	    public void onClick(View v) {
	        // TODO Auto-generated method stub
	        //Switch to gamestart page
	        Intent intent = new Intent();
	        intent.setClass(MainActivity.this, GameStart.class);
	        startActivity(intent);
	    }
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
