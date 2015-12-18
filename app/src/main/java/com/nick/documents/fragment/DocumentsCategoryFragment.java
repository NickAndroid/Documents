package com.nick.documents.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nick.documents.activity.FileExplorerActivity;
import com.nick.documents.dashboard.ApplicationTile;
import com.nick.documents.dashboard.DocTile;
import com.nick.documents.dashboard.FilesTile;
import com.nick.documents.dashboard.MusicTile;
import com.nick.documents.dashboard.PhotoTile;
import com.nick.documents.dashboard.QuickTile;
import com.nick.documents.dashboard.TileCategory;
import com.nick.documents.dashboard.TileListener;
import com.nick.documents.dashboard.VideoTile;
import com.nick.documents.tile.Category;
import com.nick.documents.tile.DashboardFragment;

import java.util.List;

public class DocumentsCategoryFragment extends DashboardFragment implements TileListener {

    public static final String TAG = "CategoryFragment";

    @Override
    protected void onCreateDashCategories(List<Category> categories) {
        super.onCreateDashCategories(categories);
        Category category = new Category();
        category.addTile(new FilesTile(getActivity(), this));
        category.addTile(new MusicTile(getActivity(), this));
        category.addTile(new VideoTile(getActivity(), this));
        category.addTile(new PhotoTile(getActivity(), this));
        category.addTile(new ApplicationTile(getActivity(), this));
        category.addTile(new DocTile(getActivity(), this));
        categories.add(category);
    }

    @Override
    public void onTileClick(@NonNull QuickTile tile) {
        TileCategory category = tile.getTileCategory();
        Log.d(TAG, "onTileClick: " + category);
        Intent intent = null;
        switch (category) {
            case APPLICATION:
                break;
            case FILE:
                intent = buildFileExplorerIntent();
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
        // TODO Remove when code ready.
        if (intent != null)
            startActivity(intent);
    }

    private Intent buildFileExplorerIntent() {
        return new Intent(getActivity(), FileExplorerActivity.class);
    }
}
