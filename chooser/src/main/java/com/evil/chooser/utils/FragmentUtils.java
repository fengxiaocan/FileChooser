package com.evil.chooser.utils;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 18:38
 * @描述： TODO
 */

public class FragmentUtils {
    public static void addFragment(FragmentManager fm, @IdRes int id, Fragment f) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(id, f);
        transaction.commit();
    }

    public static void addFragment(FragmentManager fm, @IdRes int id, Fragment f, String tag) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(id, f, tag);
        transaction.commit();
    }

    public static void addFragment(FragmentManager fm, Fragment f, String tag) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(f, tag);
        transaction.commit();
    }

    public static void addFragment(AppCompatActivity activity, Fragment f, String tag) {
        FragmentManager manager = activity.getSupportFragmentManager();
        addFragment(manager, f, tag);
    }

    public static void addFragment(AppCompatActivity activity, @IdRes int id, Fragment f) {
        FragmentManager manager = activity.getSupportFragmentManager();
        addFragment(manager, id, f);
    }


    public static void addFragment(AppCompatActivity activity, @IdRes int id, Fragment f, String tag) {
        FragmentManager manager = activity.getSupportFragmentManager();
        addFragment(manager, id, f, tag);
    }


    public static void addFragment(Fragment activity, Fragment f, String tag) {
        FragmentManager manager = activity.getChildFragmentManager();
        addFragment(manager, f, tag);
    }

    public static void addFragment(Fragment activity, @IdRes int id, Fragment f) {
        FragmentManager manager = activity.getChildFragmentManager();
        addFragment(manager, id, f);
    }


    public static void addFragment(Fragment activity, @IdRes int id, Fragment f, String tag) {
        FragmentManager manager = activity.getChildFragmentManager();
        addFragment(manager, id, f, tag);
    }


    public static void addFragment(android.app.FragmentManager fm, @IdRes int id, android.app.Fragment f) {
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(id, f);
        transaction.commit();
    }

    public static void addFragment(android.app.FragmentManager fm, @IdRes int id, android.app.Fragment f, String tag) {
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(id, f, tag);
        transaction.commit();
    }

    public static void addFragment(android.app.FragmentManager fm, android.app.Fragment f, String tag) {
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(f, tag);
        transaction.commit();
    }


    public static void addFragment(Activity activity, android.app.Fragment f, String tag) {
        android.app.FragmentManager manager = activity.getFragmentManager();
        addFragment(manager, f, tag);
    }

    public static void addFragment(Activity activity, @IdRes int id, android.app.Fragment f) {
        android.app.FragmentManager manager = activity.getFragmentManager();
        addFragment(manager, id, f);
    }


    public static void addFragment(Activity activity, @IdRes int id, android.app.Fragment f, String tag) {
        android.app.FragmentManager manager = activity.getFragmentManager();
        addFragment(manager, id, f, tag);
    }


    public static void addFragment(android.app.Fragment activity, android.app.Fragment f, String tag) {
        android.app.FragmentManager manager = activity.getFragmentManager();
        addFragment(manager, f, tag);
    }

    public static void addFragment(android.app.Fragment activity, @IdRes int id, android.app.Fragment f) {
        android.app.FragmentManager manager = activity.getFragmentManager();
        addFragment(manager, id, f);
    }


    public static void addFragment(android.app.Fragment activity, @IdRes int id, android.app.Fragment f, String tag) {
        android.app.FragmentManager manager = activity.getFragmentManager();
        addFragment(manager, id, f, tag);
    }
}
