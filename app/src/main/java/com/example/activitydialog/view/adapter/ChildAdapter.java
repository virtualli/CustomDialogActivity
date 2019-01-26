package com.example.activitydialog.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.activitydialog.databinding.ItemChildBinding;
import com.example.activitydialog.vm.DialogViewModel;
import com.example.activitydialog.R;
import com.example.activitydialog.vo.Child;

import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Child> list;
    private DialogViewModel viewModel;

    public ChildAdapter(Context context, ArrayList<Child> list, DialogViewModel viewModel) {
        this.context = context;
        this.list = list;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemChildBinding binding = ItemChildBinding.inflate(LayoutInflater.from(context), viewGroup, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.itemView.setTag(i);
        myViewHolder.binding.setChild(list.get(i));
        myViewHolder.binding.setViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ItemChildBinding binding;
        View itemView;
        TextView title;
        public MyViewHolder(ItemChildBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemView =binding.getRoot();
            title = binding.childName;
        }
    }
}
