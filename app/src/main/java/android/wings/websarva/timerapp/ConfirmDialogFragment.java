package android.wings.websarva.timerapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;


public class ConfirmDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String str01 = getArguments().getString("VALUE01");
        String dialogMsg = String.format(getString(R.string.dialog_msg), str01);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(dialogMsg);
        builder.setPositiveButton(R.string.dialog_btn_ok, new DialogButtonClickListener());
        builder.setNegativeButton(R.string.dialog_btn_ng, new DialogButtonClickListener());
        AlertDialog dialog = builder.create();
        return dialog;
    }

    private class DialogButtonClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            String msg = "";
            MainActivity2 ma2 = new MainActivity2();
            String str01 = getArguments().getString("VALUE01");
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    msg = String.format(getString(R.string.dialog_ok_toast), str01);
                    ma2.setDialogResult(true);
                    ma2.dialogClosedProcess();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    msg = getString(R.string.dialog_ng_toast);
                    ma2.setDialogResult(false);
                    break;
            }
            Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        }

    }

}
