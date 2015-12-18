package com.nick.documents.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nick.documents.R;

public class PhotoTile extends QuickTile {

    public PhotoTile(@NonNull Context context, TileListener listener) {
        super(context, listener);
        this.titleRes = R.string.drawer_title_photo;
        this.iconRes = R.drawable.ic_image;
        this.tileView = new QuickTileView(context, this);
    }

    public
    @NonNull
    @Override
    TileCategory getTileCategory() {
        return TileCategory.PHOTO;
    }
}
