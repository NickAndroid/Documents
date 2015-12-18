package com.nick.documents.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

public class StagedFragment extends Fragment {

    public interface HomeAsUpListener {
        void onRequestHomeAsUp(StagedFragment fragment, boolean enable);

        void onRequestUpLevel(StagedFragment fragment);
    }

    private HomeAsUpListener mHomeAsUpListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
        mHomeAsUpListener = (HomeAsUpListener) context;
        mHomeAsUpListener.onRequestHomeAsUp(this, true);
    }

    @Override
    public void onDetach() {
        mHomeAsUpListener.onRequestHomeAsUp(this, false);
        super.onDetach();
    }

    protected void onRequestUpLevel() {
        mHomeAsUpListener.onRequestUpLevel(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onRequestUpLevel();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
