package com.nick.documents.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nick.documents.R;

public class MusicTile extends QuickTile {

    public MusicTile(@NonNull Context context, TileListener listener) {
        super(context, listener);
        this.titleRes = R.string.drawer_title_music;
        this.iconRes = R.drawable.ic_mic;
        this.tileView = new QuickTileView(context, this);
    }

    public
    @NonNull
    @Override
    TileCategory getTileCategory() {
        return TileCategory.MUSIC;
    }
}
