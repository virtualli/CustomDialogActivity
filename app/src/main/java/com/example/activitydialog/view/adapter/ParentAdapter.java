package com.example.activitydialog.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.activitydialog.vm.DialogViewModel;
import com.example.activitydialog.R;
import com.example.activitydialog.databinding.ItemParentBinding;
import com.example.activitydialog.vo.Parent;

import java.util.ArrayList;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Parent> list;
    private DialogViewModel viewModel;

    public ParentAdapter(Context context, ArrayList<Parent> list, DialogViewModel viewModel) {
        this.context = context;
        this.list = list;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ParentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemParentBinding binding = ItemParentBinding.inflate(LayoutInflater.from(context), viewGroup, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.itemView.setTag(i);
        myViewHolder.binding.setParent(list.get(i));
        myViewHolder.binding.setViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ItemParentBinding binding;
        View itemView;
        TextView title;
        public MyViewHolder(ItemParentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemView =binding.getRoot();
            title = itemView.findViewById(R.id.title_text);
        }
    }
}
