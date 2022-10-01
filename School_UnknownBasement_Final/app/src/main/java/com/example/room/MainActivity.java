package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button start, video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        start = findViewById(R.id.start);
        video = findViewById(R.id.btn_video);

        // 스테이지를 저장할 DB로 SharedPreferences를 사용한다.
        StageDB.sp = getSharedPreferences(StageDB.save, MODE_PRIVATE);
        StageDB.editor = StageDB.sp.edit();

        StageDB.st1_clear = (Boolean) StageDB.sp.getBoolean("stage1_clear", true);
        StageDB.st2_clear = (Boolean) StageDB.sp.getBoolean("stage2_clear", true);
        StageDB.st3_clear = (Boolean) StageDB.sp.getBoolean("stage3_clear", true);
        StageDB.st4_clear = (Boolean) StageDB.sp.getBoolean("stage4_clear", true);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StageDB.st4_clear) {                    //stage4까지 클리어
                    Intent intent = new Intent(MainActivity.this, main2_5.class);
                    startActivity(intent);

                } else if (StageDB.st3_clear) {               //stage3까지 클리어
                    Intent intent = new Intent(MainActivity.this, main2_4.class);
                    startActivity(intent);

                } else if (StageDB.st2_clear) {               //stage2까지 클리어
                    Intent intent = new Intent(MainActivity.this, main2_3.class);
                    startActivity(intent);

                } else if (StageDB.st1_clear) {               //stage1까지 클리어
                    Intent intent = new Intent(MainActivity.this, main2_2.class);
                    startActivity(intent);

                } else {                                     //stage1 clear X
                    Intent intent = new Intent(MainActivity.this, main2_1.class);
                    startActivity(intent);
                }
            }
        });


        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity_Video.class);
                startActivity(intent);
            }
        });
    }
}