package com.nick.documents.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.nick.documents.R;

public class FileExplorerActivity extends TransactionSafeActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.files);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
