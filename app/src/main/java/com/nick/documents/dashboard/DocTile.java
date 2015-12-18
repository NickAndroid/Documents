package com.nick.documents.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import com.nick.documents.R;
import com.nick.documents.tile.DropDownTileView;

import java.util.ArrayList;
import java.util.List;

public class DocTile extends QuickTile {

    public DocTile(@NonNull Context context, TileListener listener) {
        super(context, listener);
        this.titleRes = R.string.drawer_title_doc;
        this.iconRes = R.drawable.ic_doc;
        this.tileView = new DocDropDownTileView(context);
    }

    public
    @NonNull
    @Override
    TileCategory getTileCategory() {
        return TileCategory.DOC;
    }

    private class DocDropDownTileView extends DropDownTileView {

        public DocDropDownTileView(Context context) {
            super(context);
        }

        public DocDropDownTileView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected List<String> onCreateDropDownList() {
            List<String> list = new ArrayList<>();
            list.add("Word");
            list.add("Pdf");
            list.add("Draw");
            return list;
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
        }
    }
}
