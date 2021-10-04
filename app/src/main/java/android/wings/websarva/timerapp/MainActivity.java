package android.wings.websarva.timerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    android.os.Handler handler = new android.os.Handler();
    int base_sec = 3120;
    boolean is_timer_started = false;
    boolean is_once_started = false;
    boolean is_paused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_start = findViewById(R.id.button_start);
        Button bt_reset = findViewById(R.id.button_reset);
        Button bt_screen_transition = findViewById(R.id.button_screen_transition);
        HelloListener listener = new HelloListener();
        bt_start.setOnClickListener(listener);
        bt_reset.setOnClickListener(listener);
        bt_screen_transition.setOnClickListener(listener);
    }

    private class HelloListener implements View.OnClickListener {
        timerOperation timeroperation = new timerOperation();

        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch(id) {
                case R.id.button_start:
                    timeroperation.timer_start_stop();
                    break;
                case R.id.button_reset:
                    timeroperation.timer_reset_process();
                    break;
                case R.id.button_screen_transition:
                    timeroperation.screen_transition_process();
                    break;
            }
        }
    }

    public class timerOperation {

        Timer timer = new Timer();
        TextView tv_Time = findViewById(R.id.text_time);
        MainTimerTask tt = new MainTimerTask();
        Button bt_start = findViewById(R.id.button_start);
        Button bt_reset = findViewById(R.id.button_reset);

        public void timer_reset_process() {
            tt.setSec(3120);
            tv_Time.setText(R.string.base_time);
            bt_start.setText(R.string.bt_start);
            MainTimerTask tt = new MainTimerTask();
            is_timer_started = false;
            is_paused = true;
        }
        public void timer_start_stop() {
            if (!is_timer_started) {
                if (!is_once_started) {
                    timer.scheduleAtFixedRate(tt, 0, 1000);
                }
                bt_start.setText(R.string.bt_stop);
                is_timer_started = true;
                is_once_started = true;
                is_paused = false;
            } else if (is_timer_started) {
                bt_start.setText(R.string.bt_start);
                is_timer_started = false;
                is_paused = true;
            }
        }
        public void screen_transition_process() {
            int send_second = base_sec;
            Intent intent = new Intent(getApplication(), MainActivity2.class);
            intent.putExtra("send_second", send_second);
            startActivity(intent);
        }
    }

    private class MainTimerTask extends TimerTask {
        int sec = 3120;
        boolean is_switched = false;
        String converted_time;
        TextView tv_Time = findViewById(R.id.text_time);

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (is_paused) {

                    } else {
                        clockMove(sec);
                        sec--;
                    }

                }
            });
        }
        public void clockMove(int num) {
            if (this.sec == 0) { switchTime(); }
            this.converted_time = String.format(Locale.US, "%02d",  num / 60) +
                    ":" + String.format(Locale.US,"%02d", num % 60);
            tv_Time.setText(this.converted_time);
        }
        public void switchTime() {
            if (!this.is_switched) {
                this.sec = 1020 + 1;
                this.is_switched = true;
            } else if (this.is_switched) {
                this.sec = 3120 + 1;
                this.is_switched = false;
            }
        }
        public void setSec(int num) {
            sec = num;
        }
        public int getSec() {
            return this.sec;
        }
    }
}