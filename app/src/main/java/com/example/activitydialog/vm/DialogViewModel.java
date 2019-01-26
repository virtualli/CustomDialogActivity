package com.example.activitydialog.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.activitydialog.vo.Child;
import com.example.activitydialog.vo.Parent;

import java.util.ArrayList;

public class DialogViewModel extends ViewModel {

    public final MutableLiveData<ArrayList<Parent>> parentList = new MutableLiveData<>();

    public final MutableLiveData<ArrayList<Child>> childList = new MutableLiveData<>();
    public final MutableLiveData<Boolean> isParentChecked = new MutableLiveData<>();

    public DialogViewModel(ArrayList<Parent> list) {
        parentList.setValue(list);
        childList.setValue(parentList.getValue().get(0).list);
        isParentChecked.setValue(true);
    }

    public void checkParent(Parent parent){
        if (parent.isCheck){
            return;
        }

        ArrayList<Parent> tempList = new ArrayList<>();
        for (int i = 0; i < parentList.getValue().size(); i++){
            Parent temp = parentList.getValue().get(i);
            temp.isCheck = temp.id == parent.id;
            tempList.add(temp);
        }
        parentList.setValue(tempList);

        childList.setValue(parent.list);

        isParentChecked.setValue(true);
    }

    public void checkChild(Child child){

        ArrayList<Child> tempList = new ArrayList<>();
        for (int i = 0; i < childList.getValue().size(); i++){
            Child temp = childList.getValue().get(i);
            if (temp.id == child.id){
                child.isCheck = !child.isCheck;
                tempList.add(child);

            }else{
                tempList.add(temp);
            }
        }
        childList.setValue(tempList);

        isParentChecked.setValue(false);
    }

    public void reset(){
        ArrayList<Parent> tempList = new ArrayList<>();
        for (int i = 0; i < parentList.getValue().size(); i++){
            Parent parent = parentList.getValue().get(i);
            if (i == 0){
                parent.isCheck = true;
            }else{
                parent.isCheck = false;
            }

            for (Child child : parent.list){
                child.isCheck = false;
            }

            tempList.add(parent);
        }

        parentList.setValue(tempList);
        childList.setValue(parentList.getValue().get(0).list);
        isParentChecked.setValue(true);
    }


}
