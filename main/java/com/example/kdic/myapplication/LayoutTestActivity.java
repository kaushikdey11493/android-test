package com.example.kdic.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.squareup.picasso.Picasso;

public class LayoutTestActivity extends AppCompatActivity {

    SliderLayout sliderLayout;
    private String s[]={
            "kd1","kd2","kd3","kd4","kd5","kd6","kd7","kd8","kd9"
    };
    String url[][]={ {"parrot","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNZfVz5XojnyDzv_z8F4aiL0RHVXOliSKoDtc4U0NKuK1lE-zE"},
                    {"panda","https://institute.sandiegozoo.org/sites/default/files/heros-giant-panda.jpg"},
                    {"kingfisher","https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/%E2%99%82_Common_Kingfisher_%28Alcedo_atthis%29_Photograph_By_Shantanu_Kuveskar%2C_Mangaon%2C_Maharashtra%2C_India.jpg/220px-%E2%99%82_Common_Kingfisher_%28Alcedo_atthis%29_Photograph_By_Shantanu_Kuveskar%2C_Mangaon%2C_Maharashtra%2C_India.jpg"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_test);
        sliderLayout=findViewById(R.id.slider);
        for(String s[]:url)
        {
            addImageToSlider(s,sliderLayout);
        }
        try {
            Thread.sleep(2000);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        sliderLayout.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
        LinearLayout rootlayout=findViewById(R.id.main_image);
        LinearLayout layout=findViewById(R.id.root_main);
        GridLayout gridLayout=new GridLayout(this);
        gridLayout.setColumnCount(3);
        for(String s1 : s)
        {
            addFlag(s1,gridLayout);
        }
        HorizontalScrollView scrollView=new HorizontalScrollView(this);
        LinearLayout linearLayout=new LinearLayout(this);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(params);
        for(String s1 : s)
        {
            addFlag(s1,linearLayout);
        }
        scrollView.addView(linearLayout);
        TextView tv=new TextView(this);
        tv.setText("Grid View");
        layout.addView(tv);
        layout.addView(gridLayout);

        tv=new TextView(this);
        tv.setText("Horizontal Scroll View");
        layout.addView(tv);
        layout.addView(scrollView);

        tv=new TextView(this);
        tv.setText("Grid View");
        gridLayout=new GridLayout(this);
        gridLayout.setColumnCount(2);
        for(String s1 : s)
        {
            addFlag(s1,gridLayout);
        }
        layout.addView(tv);
        layout.addView(gridLayout);
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(LayoutTestActivity.this, "Portrait Mode", Toast.LENGTH_SHORT).show();
        }
        else
        {
            rootlayout.setVisibility(View.INVISIBLE);
            AlertDialog.Builder builder=new AlertDialog.Builder(LayoutTestActivity.this);
            builder.setTitle("My Dialog");
            builder.setMessage("App doesnot support landscape mode");
            AlertDialog dialog=builder.create();
            dialog.show();
        }

    }

    @Override
    protected void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    private void addImageToSlider(String[] s, SliderLayout sliderLayout)
    {
        TextSliderView tsv=new TextSliderView(this);
        tsv.description(s[0])
                .image(s[1]);
        sliderLayout.addSlider(tsv);
    }

    private void addFlag(final String s, ViewGroup layout)
    {
        View view=getLayoutInflater().inflate(R.layout.flag,null);
        ImageView imageView=view.findViewById(R.id.image);
        imageView.setImageResource(getResources().getIdentifier("image","drawable",getPackageName()));
        TextView tv=view.findViewById(R.id.text);
        tv.setText("Hello "+s);
        layout.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LayoutTestActivity.this,"You clicked "+s,Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder=new AlertDialog.Builder(LayoutTestActivity.this);
                builder.setTitle("My Dialog");
                builder.setMessage("You clicked : "+s);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LayoutTestActivity.this,"Dialog positive button woking fine",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LayoutTestActivity.this,"Dialog negative button woking fine",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
    }



    /*To Add View {
    *
    * TextView tv=new TextView(this);
        LinearLayout layout=findViewById(R.id.main);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(0,50,0,0);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
        tv.setText("Hello Kaushik"+tv.getTextSize());
        layout.addView(tv);
        Button btn=new Button(this);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        btn.setText("Click Me");
        params.gravity=Gravity.CENTER | Gravity.TOP;
        btn.setLayoutParams(params);
        layout.addView(btn,0);
        }
     */

}
