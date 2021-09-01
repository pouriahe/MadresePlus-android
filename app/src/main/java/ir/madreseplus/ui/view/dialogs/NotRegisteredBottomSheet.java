package ir.madreseplus.ui.view.dialogs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ir.madreseplus.R;

public class NotRegisteredBottomSheet extends BottomSheetDialogFragment {
    private static final String TAG = NotRegisteredBottomSheet.class.getSimpleName();

    private Button callSupportButton;

    public static NotRegisteredBottomSheet newInstance() {
        return new NotRegisteredBottomSheet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_not_registered, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callSupportButton = view.findViewById(R.id.btn_callToSupport_bottomSheet);
        callSupportButton.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(getString(R.string.tel_support))
        )));
    }
}
