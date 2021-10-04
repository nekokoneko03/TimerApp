package android.wings.websarva.timerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        secondListener secondlistener = new secondListener();

        Intent intent = getIntent();

        int received_second = intent.getIntExtra("send_second", 0);

//        TextView tv_test = findViewById(R.id.tv_test);
//        tv_test.setText(Integer.toString(received_second));

        ListView lvTime = findViewById(R.id.lvTime);
        List<String> timeList = new ArrayList<>();
        timeList.add("52:00");
        timeList.add("3:00");
        timeList.add("5:00");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, timeList);
        lvTime.setAdapter(adapter);
        lvTime.setOnItemClickListener(new ListItemClickListener());

        Button button_screen_return = findViewById(R.id.button_screen_return);
        button_screen_return.setOnClickListener(secondlistener);

    }

    private class secondListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            finish();
        }
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ConfirmDialogFragment dialogFragment = new ConfirmDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "ConfirmDialogFragment");
        }
    }
}