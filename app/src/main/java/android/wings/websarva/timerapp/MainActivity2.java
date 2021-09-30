package android.wings.websarva.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        secondListener secondlistener = new secondListener();

        Intent intent = getIntent();

        int received_second = intent.getIntExtra("send_second", 0);

        TextView tv_test = findViewById(R.id.tv_test);
        tv_test.setText(Integer.toString(received_second));

        Button button_screen_return = findViewById(R.id.button_screen_return);
        button_screen_return.setOnClickListener(secondlistener);

    }

    private class secondListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            finish();
        }
    }
}