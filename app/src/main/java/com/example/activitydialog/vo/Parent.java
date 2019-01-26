package com.example.activitydialog.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class Parent implements Serializable {
    public int id;
    public String name;
    public boolean isCheck;
    public ArrayList<Child> list;
}
