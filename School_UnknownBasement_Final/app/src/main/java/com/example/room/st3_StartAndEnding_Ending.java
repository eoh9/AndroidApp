package com.example.room;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;

public class st3_StartAndEnding_Ending extends AppCompatActivity {
    TextView tvv;
    private Button btn_st3_end;
    MediaPlayer st3_ending_media;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.st3_exit_layout);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        StageDB.sp = getSharedPreferences(StageDB.save, MODE_PRIVATE);
        StageDB.editor = StageDB.sp.edit();

        st3_ending_media = MediaPlayer.create(st3_StartAndEnding_Ending.this, R.raw.st3_bgm);
        st3_ending_media.start();

        st3_gv.st3_is = getResources().openRawResource(R.raw.st3_ending_storyline);
        st3_gv.st3_sc = new Scanner(st3_gv.st3_is, "UTF-8");
        st3_gv.st3_tv = (TextView) findViewById(R.id.st3_txt_ending);
        st3_gv.st3_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(st3_gv.st3_sc.hasNextLine()) {
                    String storyLine = st3_gv.st3_sc.nextLine();
                    st3_gv.st3_tv.setText(storyLine);
                }
                else
                    st3_gv.st3_tv.setVisibility(View.GONE);
            }
        });

        btn_st3_end = findViewById(R.id.st3_btn_end);
        btn_st3_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StageDB.editor.putBoolean("stage3_clear", true);
                StageDB.editor.commit();

                st3_ending_media.stop();
                st3_ending_media.reset();

                Intent intent = new Intent(st3_StartAndEnding_Ending.this, main2_4.class);
                startActivity(intent);

            }
        });
    }
}
