package com.example.activitydialog.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.activitydialog.vm.DialogViewModel;
import com.example.activitydialog.R;
import com.example.activitydialog.vo.Parent;

import java.util.ArrayList;

public class DialogActivity extends AppCompatActivity{

    DialogViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dialog);

        final ArrayList<Parent> list = (ArrayList<Parent>) getIntent().getBundleExtra("data").getSerializable("list");
        viewModel = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new DialogViewModel(list);
            }
        }).get(DialogViewModel.class);

        ParentFragment parentFragment = new ParentFragment();
        replaceFragment(parentFragment, R.id.parent_fragment);

        viewModel.isParentChecked.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isParentCheck) {
                if (isParentCheck) {
                    ChildFragment newFrg = new ChildFragment();
                    replaceFragment(newFrg, R.id.child_fragment);
                } else {
                    ChildFragment childFragment = (ChildFragment) getSupportFragmentManager().findFragmentById(R.id.child_fragment);
                    if (childFragment != null){
                        childFragment.updateView();
                    }
                }
            }
        });

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.reset();
            }
        });

        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", viewModel.parentList.getValue());
                Intent it = new Intent();
                it.putExtra("list", bundle);
                setResult(Activity.RESULT_OK, it);
                finish();
            }
        });

        findViewById(R.id.v1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogActivity.super.onBackPressed();
            }
        });

        findViewById(R.id.v2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogActivity.super.onBackPressed();
            }
        });
    }

    private void replaceFragment(Fragment fragment, int id){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }

}
