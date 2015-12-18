package com.nick.documents.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nick.documents.tile.Tile;

public abstract class QuickTile extends Tile {

    private Context mContext;
    private TileListener mListener;

    public QuickTile(@NonNull Context context, TileListener listener) {
        mContext = context;
        mListener = listener;
    }

    public Context getContext() {
        return mContext;
    }

    public TileListener getListener() {
        return mListener;
    }

    public void setEnabled(boolean enabled) {
        if (getTileView() != null)
            getTileView().setEnabled(enabled);
    }

    public abstract
    @NonNull
    TileCategory getTileCategory();
}
