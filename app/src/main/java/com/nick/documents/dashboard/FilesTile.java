package com.nick.documents.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nick.documents.R;

public class FilesTile extends QuickTile {

    public FilesTile(@NonNull Context context, TileListener listener) {
        super(context, listener);
        this.titleRes = R.string.drawer_title_files;
        this.iconRes = R.drawable.ic_file;
        this.tileView = new QuickTileView(context, this);
    }

    public
    @NonNull
    @Override
    TileCategory getTileCategory() {
        return TileCategory.FILE;
    }


}
