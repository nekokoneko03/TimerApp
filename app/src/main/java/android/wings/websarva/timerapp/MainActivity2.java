package android.wings.websarva.timerapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    boolean is_pushed_yes = false;
    public static int selected_sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        secondListener secondlistener = new secondListener();

        Intent intent = getIntent();

        selected_sec = intent.getIntExtra("send_second", 0);

//        TextView tv_test = findViewById(R.id.tv_test);
//        tv_test.setText(Integer.toString(received_second));

        ConfirmDialogFragment test = new ConfirmDialogFragment();

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
            Intent intent = new Intent();
            intent.putExtra("SelectedTime", selected_sec);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String tmp_selected_time = (String) parent.getItemAtPosition(position);
            Log.i("selected", tmp_selected_time);
            ConfirmDialogFragment dialogFragment = new ConfirmDialogFragment();
            Bundle args = new Bundle();
            args.putString("VALUE01", tmp_selected_time);
            dialogFragment.setArguments(args);
            dialogFragment.show(getSupportFragmentManager(), "ConfirmDialogFragment");
        }
    }

    public void dialogClosedProcess(String str, boolean hi) {
        if (hi) {
            selected_sec = str;
        } else {
            selected_sec = received
        }
    }
}