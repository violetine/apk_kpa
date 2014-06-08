package com.example.apk_kpa.app;

/**
 * Created by Marius on 6/8/2014.
 */
        import android.content.Context;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

public class FirstFragment extends Fragment {
    public static Fragment newInstance(Context context) {
        FirstFragment frag = new FirstFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login, null);
        return root;
    }
}