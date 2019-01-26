package com.example.activitydialog.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activitydialog.vm.DialogViewModel;
import com.example.activitydialog.R;
import com.example.activitydialog.view.adapter.ParentAdapter;
import com.example.activitydialog.vo.Parent;

import java.util.ArrayList;


public class ParentFragment extends Fragment {


    private DialogViewModel model;
    private ParentAdapter adapter;
    private RecyclerView list;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = ViewModelProviders.of(getActivity()).get(DialogViewModel.class);

        model.parentList.observe(this, new Observer<ArrayList<Parent>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Parent> parents) {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent, container, false);
        list = view.findViewById(R.id.parent_list);
        list.setFocusable(true);
        adapter = new ParentAdapter(getActivity(), model.parentList.getValue(), model);
        list.setAdapter(adapter);
        return view;
    }

}
