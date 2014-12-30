package com.example.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {
    int width;
    ImageView openbtn;
    ImageView newbtn;
    Openbtn_Listener openbtn_listener;
    Openre_Listener openre_listener;
    Newbtn_Listener newbtn_listener;
    Newre_Listener newre_listener;
    private List<Map<String, String>> planetsList = new ArrayList<Map<String,String>>();
    SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_activity);
        ImageView openbtn=(ImageView)findViewById(R.id.openbtn);
        ImageView newbtn=(ImageView)findViewById(R.id.newbtn);
        Button button =(Button) findViewById(R.id.testbtn);
        ImageView newre=(ImageView) findViewById(R.id.newreturn);

        openbtn_listener=new Openbtn_Listener(this);
        openre_listener=new Openre_Listener(this);
        newbtn_listener=new Newbtn_Listener(this);
        newre_listener=new Newre_Listener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent();
                i.setClass(MainActivity.this,EditActivity.class);
                startActivity(i);
            }
        });
        newbtn.setOnClickListener(newbtn_listener);
        openbtn.setOnClickListener(openbtn_listener);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
         width = size.x;
        int height = size.y;


        iniList();
        ListView listView = (ListView) findViewById(R.id.openmenu);
        ListView listView1=(ListView) findViewById(R.id.newmenu);
        simpleAdapter = new SimpleAdapter(this, planetsList, R.layout.listtext, new String[] {"planet"}, new int[] {android.R.id.text1});
        listView.setAdapter(simpleAdapter);
        listView1.setAdapter(simpleAdapter);
    }
    private void iniList(){
        planetsList.add(createPlanet("planet", "Mercury"));
        planetsList.add(createPlanet("planet", "Venus"));
        planetsList.add(createPlanet("planet", "Mars"));
        planetsList.add(createPlanet("planet", "Jupiter"));
        planetsList.add(createPlanet("planet", "Saturn"));
        planetsList.add(createPlanet("planet", "Uranus"));
        planetsList.add(createPlanet("planet", "Neptune"));

    }
    private HashMap<String, String> createPlanet(String key, String name) {
        HashMap<String, String> planet = new HashMap<String, String>();
        planet.put(key, name);
        return planet;
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class Openbtn_Listener implements View.OnClickListener{
        private MainActivity activity;
        public Openbtn_Listener(MainActivity activity)
        {
            this.activity=activity;
        }
        @Override
        public void onClick(View view)
        {
            Toast.makeText(getApplication(), String.valueOf(width), Toast.LENGTH_LONG).show();
            LinearLayout listView2 = (LinearLayout) findViewById(R.id.opmucon);
            ImageView imageView=(ImageView) findViewById(R.id.openbtn);
            ImageView imageView1 = (ImageView) findViewById(R.id.newbtn);
            TranslateAnimation anim = new TranslateAnimation(0, width * 45 / 100, 0, 0);
            TranslateAnimation anim2 = new TranslateAnimation(0, width * 45 / 100, 0, 0);
            anim.setDuration(1000);
            anim.setFillAfter(true);
            anim2.setDuration(1000);
            anim2.setFillAfter(true);
            Animation alpha = new AlphaAnimation(1.0f, 0.0f);
            alpha.setDuration(2000);
            alpha.setRepeatCount(0);
            alpha.setFillAfter(false);
            alpha.setFillAfter(false);
            alpha.setFillEnabled(true);
            ImageView opre=(ImageView)findViewById(R.id.opreturn);
            opre.setOnClickListener(new Openre_Listener(MainActivity.this));
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    LinearLayout listView2 = (LinearLayout) findViewById(R.id.opmucon);
                    ImageView re = (ImageView) findViewById(R.id.opreturn);

                    Animation alpha = new AlphaAnimation(0.0f, 1.0f);
                    alpha.setDuration(1000);
                    alpha.setRepeatCount(0);
                    alpha.setFillAfter(true);
                    listView2.setVisibility(View.VISIBLE);
                    re.setVisibility(View.VISIBLE);
                    re.startAnimation(alpha);
                    listView2.startAnimation(alpha);
                    ImageView imageView = (ImageView) findViewById(R.id.newbtn);
                    ImageView imageView2 = (ImageView) findViewById(R.id.openbtn);
                    imageView.setOnClickListener(null);
                    imageView2.setOnClickListener(null);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            imageView.startAnimation(anim);
            imageView1.startAnimation(anim2);

        }
    }
    class Openre_Listener implements View.OnClickListener{
        private MainActivity activity;
        public Openre_Listener(MainActivity activity)
        {
            this.activity=activity;
        }
        @Override
        public void onClick(View view)
        {

            LinearLayout listView2 = (LinearLayout) findViewById(R.id.opmucon);
            ImageView re = (ImageView) findViewById(R.id.opreturn);


            Animation alpha = new AlphaAnimation(1.0f, 0.0f);
            alpha.setDuration(1000);
            alpha.setRepeatCount(0);
            alpha.setFillAfter(true);


            alpha.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Toast.makeText(getApplicationContext(),"end",Toast.LENGTH_LONG).show();
                    ImageView imageView = (ImageView) findViewById(R.id.newbtn);
                    ImageView imageView2 = (ImageView) findViewById(R.id.openbtn);
                    LinearLayout listView2 = (LinearLayout) findViewById(R.id.opmucon);
                    ImageView re = (ImageView) findViewById(R.id.opreturn);
                    imageView.setOnClickListener(newbtn_listener);
                    imageView2.setOnClickListener(openbtn_listener);
                    TranslateAnimation anim = new TranslateAnimation( width * 45 / 100,0, 0, 0);
                    anim.setDuration(1000);
                    anim.setFillAfter(true);
                    imageView.startAnimation(anim);
                    imageView2.startAnimation(anim);
                    listView2.setVisibility(View.INVISIBLE);
                    re.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            listView2.startAnimation(alpha);
            re.startAnimation(alpha);
        }
    }
    class Newbtn_Listener implements View.OnClickListener{
        private MainActivity activity;
        public Newbtn_Listener(MainActivity activity)
        {
            this.activity=activity;
        }
        @Override
        public void onClick(View view)
        {
            Toast.makeText(getApplication(), String.valueOf(width), Toast.LENGTH_LONG).show();
            ImageView imageView=(ImageView) findViewById(R.id.openbtn);
            ImageView imageView1 = (ImageView) findViewById(R.id.newbtn);
            TranslateAnimation anim = new TranslateAnimation(0, -width * 45 / 100, 0, 0);
            TranslateAnimation anim2 = new TranslateAnimation(0, -width * 45 / 100, 0, 0);
            anim.setDuration(1000);
            anim.setFillAfter(true);
            anim2.setDuration(1000);
            anim2.setFillAfter(true);
            Animation alpha = new AlphaAnimation(1.0f, 0.0f);
            alpha.setDuration(2000);
            alpha.setRepeatCount(0);
            alpha.setFillAfter(false);
            alpha.setFillAfter(false);
            alpha.setFillEnabled(true);
            ImageView newre=(ImageView)findViewById(R.id.newreturn);
            newre.setOnClickListener(new Newre_Listener(MainActivity.this));
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            imageView.startAnimation(anim);
            imageView1.startAnimation(anim2);

        }
    }
    class Newre_Listener implements View.OnClickListener{
        private MainActivity activity;
        public Newre_Listener(MainActivity activity)
        {
            this.activity=activity;
        }
        @Override
        public void onClick(View view)
        {
            Toast.makeText(getApplication(), String.valueOf(width), Toast.LENGTH_LONG).show();
            ImageView imageView=(ImageView) findViewById(R.id.openbtn);
            ImageView imageView1 = (ImageView) findViewById(R.id.newbtn);
            TranslateAnimation anim = new TranslateAnimation(0, width * 45 / 100, 0, 0);
            TranslateAnimation anim2 = new TranslateAnimation(0, width * 45 / 100, 0, 0);
            anim.setDuration(1000);
            anim.setFillAfter(true);
            anim2.setDuration(1000);
            anim2.setFillAfter(true);
            Animation alpha = new AlphaAnimation(1.0f, 0.0f);
            alpha.setDuration(2000);
            alpha.setRepeatCount(0);
            alpha.setFillAfter(false);
            alpha.setFillAfter(false);
            alpha.setFillEnabled(true);
            ImageView opre=(ImageView)findViewById(R.id.newreturn);
            opre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"newre",Toast.LENGTH_LONG).show();
                }
            });
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    LinearLayout listView2 = (LinearLayout) findViewById(R.id.newmucon);
                    ImageView re=(ImageView) findViewById(R.id.newreturn);


                    Animation alpha = new AlphaAnimation(0.0f, 1.0f);
                    alpha.setDuration(1000);
                    alpha.setRepeatCount(0);
                    alpha.setFillAfter(true);
                    listView2.setVisibility(View.VISIBLE);
                    re.setVisibility(View.VISIBLE);
                    listView2.startAnimation(alpha);
                    re.startAnimation(alpha);
                    ImageView imageView=(ImageView) findViewById(R.id.newbtn);
                    ImageView imageView2=(ImageView) findViewById(R.id.openbtn);
                    imageView.setOnClickListener(null);
                    imageView2.setOnClickListener(null);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            imageView.startAnimation(anim);
            imageView1.startAnimation(anim2);

        }
    }
}
