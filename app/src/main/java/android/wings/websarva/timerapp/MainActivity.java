package android.wings.websarva.timerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    android.os.Handler handler = new android.os.Handler();
    public int base_sec = 3120;
    public int initial_sec = 3120;
    boolean is_timer_started = false;
    boolean is_paused = false;
    private TestOpenHelper helper;
    public static final String EXTRA_DATA = "YourPackageName.MESSAGE";
    static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new TestOpenHelper(getApplicationContext());

        TextView tv_time = findViewById(R.id.tv_time);
        Button bt_start = findViewById(R.id.button_start);
        Button bt_reset = findViewById(R.id.button_reset);
        Button bt_screen_transition = findViewById(R.id.button_screen_transition);
        HelloListener listener = new HelloListener();
        tv_time.setOnClickListener(listener);
        bt_start.setOnClickListener(listener);
        bt_reset.setOnClickListener(listener);
        bt_screen_transition.setOnClickListener(listener);
    }

    public String convertTime(int num) {
        return String.format(Locale.US, "%02d", num / 60) +
                ":" + String.format(Locale.US, "%02d", num % 60);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView tv_time = findViewById(R.id.tv_time);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE && null != data) {
            Bundle bundle = data.getExtras();
            int received_time = bundle.getInt("SelectedTime");
            base_sec = received_time;
            initial_sec = received_time;
            String converted_received_time = convertTime(received_time);
            tv_time.setText(converted_received_time);
            // Log.i("uketotta", String.valueOf(baba));
        }
    }

    private class HelloListener implements View.OnClickListener {
        timerOperation timeroperation = new timerOperation();

        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.button_start:
                    timeroperation.timer_start_stop();
                    break;
                case R.id.button_reset:
                    timeroperation.timer_reset_process(initial_sec);
                    break;
                case R.id.button_screen_transition:
                    timeroperation.screen_transition_process();
                    break;
                case R.id.tv_time:
                    timeroperation.screen_transition_process();
                    break;
            }
        }
    }

    public class timerOperation {

        Timer timer = new Timer();
        TextView tv_Time = findViewById(R.id.tv_time);
        MainTimerTask tt = new MainTimerTask();
        Button bt_start = findViewById(R.id.button_start);
        boolean is_once_started = false;

        public void timer_reset_process(int num) {
            base_sec = num;
            String converted_time = convertTime(num);
            tv_Time.setText(converted_time);
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
            startActivityForResult(intent, 1);
        }
    }

    private class MainTimerTask extends TimerTask {
        boolean is_switched = false;
        String converted_time;
        TextView tv_Time = findViewById(R.id.tv_time);

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (is_paused) {

                    } else {
                        clockMove(base_sec);
                        base_sec--;
                    }

                }
            });
        }
        public void clockMove(int num) {
            if (base_sec == 0) {
                if (initial_sec == 3120) {
                    switchTime();
                } else {
                    new timerOperation().timer_reset_process(initial_sec);
                }
            }
            this.converted_time = convertTime(num);
            tv_Time.setText(this.converted_time);
        }


        public void switchTime() {
            if (!this.is_switched) {
                base_sec = 1020 + 1;
                this.is_switched = true;
            } else if (this.is_switched) {
                base_sec = 3120 + 1;
                this.is_switched = false;
            }
        }
        public void setSec(int num) {
            base_sec = num;
        }
        public int getSec() {
            return base_sec;
        }
    }
}