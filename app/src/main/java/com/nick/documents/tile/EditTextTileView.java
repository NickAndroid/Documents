package com.nick.documents.tile;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.nick.documents.R;

public class EditTextTileView extends TileView {

    EditText mEditText;
    AlertDialog mAlertDialog;

    public EditTextTileView(Context context) {
        super(context);
    }

    public EditTextTileView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onCreate(Context context) {
        super.onCreate(context);
        View editTextContainer = LayoutInflater.from(context).inflate(R.layout.dialog_edit_text, null, false);
        mAlertDialog = new AlertDialog.Builder(context, R.style.Material_Dialog)
                .setView(editTextContainer)
                .setTitle("Edit tile")
                .setPositiveButton("SAVE", null)
                .setNegativeButton("DISCARD", null)
                .create();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        mAlertDialog.show();
    }
}
