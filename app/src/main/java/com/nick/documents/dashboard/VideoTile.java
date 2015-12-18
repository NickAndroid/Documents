package com.nick.documents.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nick.documents.R;

public class VideoTile extends QuickTile {

    public VideoTile(@NonNull Context context, TileListener listener) {
        super(context, listener);
        this.titleRes = R.string.drawer_title_video;
        this.iconRes = R.drawable.ic_movie;
        this.tileView = new QuickTileView(context, this);
    }

    public
    @NonNull
    @Override
    TileCategory getTileCategory() {
        return TileCategory.VIDEO;
    }
}
