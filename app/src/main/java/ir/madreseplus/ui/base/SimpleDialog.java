package ir.madreseplus.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import ir.madreseplus.R;

public class SimpleDialog extends Dialog {

    private @DrawableRes
    int mImageRes;
    private String mButtonTxt;
    private String mCancelBtnTxt;
    private String mMessage;
    private SimpleDialogListener mDialogListener;


    public SimpleDialog(@NonNull Context context) {
        super(context);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_simple_layout);
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn = findViewById(R.id.dialog_button);
        btn.setText(mButtonTxt);
        btn.setOnClickListener(v -> {
            SimpleDialog.this.dismiss();
            if (mDialogListener != null) {
                mDialogListener.onButtonClick(SimpleDialog.this, v);
            }
        });

        Button cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setText(mCancelBtnTxt);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        TextView dialog_text = findViewById(R.id.dialog_text);
        dialog_text.setText(mMessage);

        ImageView imageView = findViewById(R.id.dialog_picture);
        imageView.setImageResource(mImageRes);
    }

    public void setImageRes(@DrawableRes int imageRes) {
        mImageRes = imageRes;
    }

    public void setButtonTxt(String btnTxt) {
        mButtonTxt = btnTxt;
    }

    public void setCancelButtonTxt(String btnTxt) {
        mCancelBtnTxt = btnTxt;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public void setDialogListener(SimpleDialogListener dialogListener) {
        mDialogListener = dialogListener;
    }

    public void setCancelButtonVisibility(boolean visibility) {
        findViewById(R.id.cancelBtn).setVisibility(visibility ? View.VISIBLE : View.GONE);
    }


    public interface SimpleDialogListener {
        void onButtonClick(SimpleDialog dialog, View view);
    }
}
