package com.guango.voicedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements VoiceHelper.IView, View.OnClickListener {
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = findViewById(R.id.send);

        send.setOnClickListener(this);

        VoiceHelper.view = this;
    }

    @Override
    public void onClick(View v) {
//        Sockethelper.builderServer();
//        Sockethelper.buildServer();
        Sockethelper.buildClient();
//        VoiceHelper.applyPermission();
    }

    @Override
    public void requestPermissions() {
        int permission = checkSelfPermission(Manifest.permission.RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED) {

            String[] PERMISSION_AUDIO = {
                    Manifest.permission.RECORD_AUDIO
            };
            requestPermissions(PERMISSION_AUDIO, 1);
        }
        else {
            VoiceHelper.getVoice();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("yanyao", "granted");
                VoiceHelper.getVoice();

            } else {
                Log.d("yanyao", "granted");
            }
        }
    }
}
