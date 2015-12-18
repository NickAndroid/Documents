package com.nick.documents.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.nick.documents.R;
import com.nick.documents.dashboard.QuickTile;
import com.nick.documents.dashboard.TileCategory;
import com.nick.documents.fragment.DocumentsCategoryFragment;
import com.nick.documents.fragment.FileExplorerFragment;
import com.nick.documents.fragment.StagedFragment;

public class NavigatorActivity extends TransactionSafeActivity implements StagedFragment.HomeAsUpListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigator_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        placeFragment(R.id.container, new DocumentsCategoryFragment(), null, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mShowingFragment != null) {
            if (mShowingFragment.onOptionsItemSelected(item)) return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onTileClick(QuickTile tile) {
        TileCategory category = tile.getTileCategory();
        switch (category) {
            case APPLICATION:
                break;
            case FILE:
                placeFragment(R.id.container, new FileExplorerFragment(), null, true);
                break;
            case MUSIC:
            case VIDEO:
            case DOC:
            case PHOTO:
                tile.setEnabled(false);
                break;
            default:
                throw new IllegalArgumentException("Unknown tile.");
        }
    }

    @Override
    public void onRequestHomeAsUp(StagedFragment fragment, boolean enable) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
    }

    @Override
    public void onRequestUpLevel(StagedFragment fragment) {
        placeFragment(R.id.container, new DocumentsCategoryFragment(), null, true);
    }
}
