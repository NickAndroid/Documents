package com.nick.documents.fragment;

import android.support.annotation.NonNull;

import com.nick.documents.activity.NavigatorActivity;
import com.nick.documents.dashboard.ApplicationTile;
import com.nick.documents.dashboard.DocTile;
import com.nick.documents.dashboard.FilesTile;
import com.nick.documents.dashboard.MusicTile;
import com.nick.documents.dashboard.PhotoTile;
import com.nick.documents.dashboard.QuickTile;
import com.nick.documents.dashboard.TileListener;
import com.nick.documents.dashboard.VideoTile;
import com.nick.documents.tile.Category;
import com.nick.documents.tile.DashboardFragment;

import java.util.List;

public class DocumentsCategoryFragment extends DashboardFragment implements TileListener {

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
        NavigatorActivity navigatorActivity = (NavigatorActivity) getActivity();
        navigatorActivity.onTileClick(tile);
    }

}
