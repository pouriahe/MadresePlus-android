package ir.madreseplus.ui.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import ir.madreseplus.R;
import ir.madreseplus.utilities.StringUtil;

public class InputPracticeDialog extends Dialog {

    private String mButtonTxt;
    private EditText mEditText;
    private SimpleDialogListener mDialogListener;
    private PAType paType;
    private String mInputText;

    public InputPracticeDialog(@NonNull Context context, PAType paType) {
        super(context);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_simple_input_layout);
        setCancelable(false);
        this.paType = paType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEditText = findViewById(R.id.et_inputDialog);
        mEditText.requestFocus();

        Button btn = findViewById(R.id.dialog_button);
        btn.setOnClickListener(v -> {
            if (mDialogListener != null) {
                if (valid()) {
                    InputPracticeDialog.this.dismiss();
                    mDialogListener.onButtonClick(InputPracticeDialog.this, v, paType);
                } else {
                    mEditText.setError("لطفا یک مقدار وارد کنید!!");
                }
            }
        });


    }

    private boolean valid() {
        mInputText = mEditText.getText().toString();
        if (!StringUtil.hasText(mInputText)) {
            return false;
        }

        return true;

    }

    public void setDialogListener(SimpleDialogListener dialogListener) {
        mDialogListener = dialogListener;
    }

    public void setCancelButtonVisibility(boolean visibility) {
        findViewById(R.id.cancelBtn).setVisibility(visibility ? View.VISIBLE : View.GONE);
    }


    public interface SimpleDialogListener {
        void onButtonClick(InputPracticeDialog dialog, View view, PAType paType);
    }

    public int getInputText() {
        return Integer.valueOf(mInputText);
    }

    public enum PAType {
        CORRECT, WRONG, NOTANSWERED;
    }
}
