package com.goormthon_univ.cloudmoon;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class PreferencesManager extends AppCompatActivity {
    //SharedPreferences 설정
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public PreferencesManager(SharedPreferences pref){
        this.pref=pref;
        this.editor=pref.edit();
    }

    public void pref_write_boolean(String item,boolean tf){
        editor.putBoolean(item,tf);
        editor.commit();
    }

    public void pref_write_string(String item,String text){
        editor.putString(item,text);
        editor.commit();
    }

    public boolean pref_read_boolean(String item){
        if(pref!=null&&pref.contains(item)){
            if(pref.getBoolean(item,true)==true){
                return true;
            }
        }
        return false;
    }

    public String pref_read_string(String item){
        if(pref!=null&&pref.contains(item)){
            String n=pref.getString(item,"").toString();
            return n;
        }
        return null;
    }
}
