package android.wings.websarva.timerapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class TimeInputDialogFragment extends DialogFragment {

    int num1, num2;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditText editText = new EditText(this.getContext());
        AlertDialog dialog = new AlertDialog.Builder(this.getContext())
                .setTitle("時間入力画面")
                .setMessage("時間を入力してください")
                .setView(editText)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String inputNum = String.valueOf(editText.getText());
                        Log.i("入力された値", inputNum);
                        String arr[] = inputNum.split(" ");
                        num1 = (Integer.parseInt(arr[0]) * 60);
                        num2 = Integer.parseInt(arr[1]);
                        int timeNum = num1 + num2;
                        Log.i("合計された秒数", String.valueOf(timeNum));
                        MainActivity2 ma2 = new MainActivity2();
                        ma2.getTimeFunction(timeNum);
                    }
                })
                .create();
        return dialog;
    }


}
