package com.and.framework.common.activity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zyd on 2017/7/20.
 * @see this is toolbar config class 
 */

public class ToolbarConfigurator {

    private final Menu menu;
    private Toolbar toolbar;

    private boolean backPressEnabled = true;
    private String title = null;

    public ToolbarConfigurator(Menu menu, Toolbar toolbar) {
        this.menu = menu;
        this.toolbar = toolbar;
    }

    public boolean isBackPressEnabled() {
        return backPressEnabled;
    }

    public String getTitle() {
        return title;
    }

    public ToolbarConfigurator setBackPressEnabled(boolean backPressEnabled) {
        this.backPressEnabled = backPressEnabled;
        return this;
    }

    public ToolbarConfigurator setTitle(String title) {
        this.title = title;
        return this;
    }

    public void clearMenu(){
        menu.clear();
    }

    public ToolbarConfigurator addTextMenu(int menuText, int menuId) {
        MenuItem edit_item = menu.add(Menu.NONE, menuId, Menu.NONE, menuText);
        edit_item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return this;
    }

    public ToolbarConfigurator addTextMenuStr(String menuTextStr, int menuId) {
        MenuItem edit_item = menu.add(Menu.NONE, menuId, Menu.NONE, menuTextStr);
        edit_item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return this;
    }

    public ToolbarConfigurator addIconMenu(int drawable, int menuId) {
        MenuItem edit_item = menu.add(Menu.NONE, menuId, Menu.NONE, null);
        edit_item.setIcon(drawable);
        edit_item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return this;
    }
    public ToolbarConfigurator addView(View view, ViewGroup.LayoutParams layoutParams){
        toolbar.addView(view,layoutParams);
        return this;
    }
    
}
