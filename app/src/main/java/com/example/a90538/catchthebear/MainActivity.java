package com.example.a90538.catchthebear;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

class MainActivity extends AppCompatActivity{
    TextView scoreText,timeText;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,
            imageView6, imageView7,imageView8,imageView9;
    int score;
    ImageView[]imageArray;
    Handler handler;
    Runnable runable;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageArray= new ImageView[]{imageView1, imageView2, imageView3, imageView4,
                imageView5, imageView6, imageView7, imageView8, imageView9};
        hideImages();
        score=0;

        new CountDownTimer(60000,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                TextView textView = (TextView) findViewById(R.id.textTime);
                textView.setText("Left:"+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeText=findViewById(R.id.textTime);
                timeText.setText("Time's off!");
                handler.removeCallbacks(runable);// handler stops.
                for(ImageView image:imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }.start();
    }
    public void increaseScore(View view)
    {
        scoreText = (TextView) findViewById(R.id.textScore);
        score++;
        scoreText.setText("Score:"+score);
    }
    public void hideImages()
    {
        handler=new Handler();
        runable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image:imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                Random r =new Random();
                int i=r.nextInt(8-0);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runable);

    }
}
