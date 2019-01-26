package com.example.activitydialog;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.activitydialog.view.DialogActivity;
import com.example.activitydialog.vo.Child;
import com.example.activitydialog.vo.Parent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("测试");
        setSupportActionBar(toolbar);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it  = new Intent(MainActivity.this, DialogActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", initData());
                it.putExtra("data", bundle);
                startActivityForResult(it, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            ArrayList<Parent> list = (ArrayList<Parent>) data.getBundleExtra("list").getSerializable("data");
            for (Parent parent : list) {
                for (Child child : parent.list) {
                    if (child.isCheck) {
                        Log.e("childCheck", child.name);
                    }
                }
                if (parent.isCheck) {
                    Log.e("parentCheck", parent.name);
                }
            }
        }
    }

    private ArrayList<Parent> initData(){
        ArrayList<Parent> list = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            ArrayList<Child> children = new ArrayList<>();
            for (int j = 0; j < 12; j++){
                Child child = new Child();
                child.parentId = i;
                child.id = j;
                child.isCheck = false;
                child.name = i + "选项" + j;
                children.add(child);
            }
            Parent parent = new Parent();
            parent.id = i;
            parent.name = i + "数据" + i;
            if (i == 0){
                parent.isCheck = true;
            }else{
                parent.isCheck = false;
            }
            parent.list = children;
            list.add(parent);
        }
        return list;
    }
}
