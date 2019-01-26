package com.example.activitydialog.view;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activitydialog.view.adapter.ChildAdapter;
import com.example.activitydialog.vm.DialogViewModel;
import com.example.activitydialog.R;

public class ChildFragment extends Fragment {

    private DialogViewModel model;
    RecyclerView list;
    ChildAdapter adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(DialogViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child, container, false);
        list = view.findViewById(R.id.child_list);
        list.setFocusable(true);
        list.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 1;
            }
        });
        adapter = new ChildAdapter(getActivity(), model.childList.getValue(), model);
        list.setAdapter(adapter);
        return view;
    }

    public void updateView(){
        adapter.notifyDataSetChanged();
    }
}
