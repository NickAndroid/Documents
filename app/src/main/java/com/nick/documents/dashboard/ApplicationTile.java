package com.nick.documents.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nick.documents.R;

public class ApplicationTile extends QuickTile {

    public ApplicationTile(@NonNull Context context, TileListener listener) {
        super(context, listener);
        this.titleRes = R.string.drawer_title_app;
        this.iconRes = R.drawable.ic_shopping;
        this.tileView = new QuickTileView(context, this);
    }

    public
    @NonNull
    @Override
    TileCategory getTileCategory() {
        return TileCategory.APPLICATION;
    }
}
