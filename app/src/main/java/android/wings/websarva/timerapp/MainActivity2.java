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

    // boolean is_pushed_yes = false;
    public static int selected_sec;
    List<Integer> timeList = new ArrayList<>();

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

        timeList.add(3120); //52:00
        timeList.add(180); //3
        timeList.add(300); //5

        List<String> unkTime = new ArrayList<>();
        unkTime.add("52:00");
        unkTime.add("3:00");
        unkTime.add("5:00");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, unkTime);
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
            Log.i("タップされたポジション", String.valueOf(position));
            String tmp_selected_time = (String) parent.getItemAtPosition(position);
            Log.i("仮の時間", tmp_selected_time);
            ConfirmDialogFragment dialogFragment = new ConfirmDialogFragment();
            Bundle args = new Bundle();
            Log.i("送る時間", String.valueOf(timeList.get(position)));
            args.putInt("VALUE01", timeList.get(position));
            dialogFragment.setArguments(args);
            dialogFragment.show(getSupportFragmentManager(), "ConfirmDialogFragment");
        }
    }

    public void dialogClosedProcess(int num, boolean hi) {
        if (hi) {
            selected_sec = num;
        } else {
            ;
        }
    }
}