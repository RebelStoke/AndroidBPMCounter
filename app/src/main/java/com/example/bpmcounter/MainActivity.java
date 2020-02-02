package com.example.bpmcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.CompletableFuture;

public class MainActivity extends AppCompatActivity {
    int click = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        synchronized (this){
            refreshView();
        }
    }


    private void refreshView() {

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                showBPM(calculateBPM());
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


    public void bpmClick(View view) {
       click++;
    }
    public int calculateBPM(){
            return (click*60);
    }
    private void showBPM(int l){
        TextView v = findViewById(R.id.bpmView);
        v.setText(Integer.toString(l));
        click = 0;
    }
}
