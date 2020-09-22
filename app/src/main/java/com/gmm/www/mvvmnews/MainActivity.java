package com.gmm.www.mvvmnews;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.gmm.www.base.activity.BaseActivity;
import com.gmm.www.base.viewmodel.BaseViewModel;
import com.gmm.www.mvvmnews.application.NewsApplication;
import com.gmm.www.mvvmnews.databinding.ActivityMainBinding;
import com.gmm.www.mvvmnews.otherfragment.AccountFragment;
import com.gmm.www.mvvmnews.otherfragment.CategoryFragment;
import com.gmm.www.mvvmnews.otherfragment.ServiceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseActivity<ActivityMainBinding,BaseViewModel> {
    private Fragment homeFragment;
    private CategoryFragment categoryFragment = new CategoryFragment();
    private ServiceFragment serviceFragment = new ServiceFragment();
    private AccountFragment accountFragment = new AccountFragment();
    private Fragment fromFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set Toolbar
        setSupportActionBar(viewDataBinding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.menu_home));
        //取消图标被Tint颜色覆盖
        viewDataBinding.bottomView.setItemIconTintList(null);

        CCResult result = CC.obtainBuilder("Home")
                .setActionName("showHomeFragment")
                .build()
                .call();
        homeFragment = result.getDataItem("home_fragment");
        fromFragment = homeFragment;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(viewDataBinding.container.getId(),homeFragment);
        transaction.commit();

        viewDataBinding.bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        fragment = homeFragment;
                        break;

                    case R.id.menu_categories:
                        fragment = categoryFragment;
                        break;

                    case R.id.menu_services:
                        fragment = serviceFragment;
                        break;

                    case R.id.menu_account:
                        fragment = accountFragment;
                        break;
                }
                if (null != getSupportActionBar()){
                    getSupportActionBar().setTitle(menuItem.getTitle());
                }
                switchFragment(fromFragment,fragment);
                fromFragment = fragment;
                return true;
            }
        });

        showBadgeView(3,5);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onRetryBtnClick() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Toast.makeText(NewsApplication.sApplication, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    /**
     * BottomNavigationView显示角标
     * @param viewIndex tab索引
     * @param showNumber 显示的数字，小于等于0是不显示
     */
    private void showBadgeView(int viewIndex,int showNumber) {
        //具体child的查找和view的嵌套结构请在源码中查看
        //从bottomNavigationView中获得BottomNavigationMenuView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) viewDataBinding.bottomView.getChildAt(0);
        //从BottomNavigationMenuView中获得childView，BottomNavigationItemView
        if (viewIndex < menuView.getChildCount()) {
            //获得viewIndex对应的子tab
            View view = menuView.getChildAt(viewIndex);
            //从子tab中获得其中显示图片的ImageView
//            View icon = view.findViewById(com.google.android.material.R.id.icon);
            //显示badgeView
            new QBadgeView(this)
                    .bindTarget(view)
                    .setGravityOffset(50,14,false)//默认是在右上角，根据需求设置偏移量
                    .setBadgeNumber(showNumber);
        }

    }

    private void switchFragment(Fragment from,Fragment to) {
        if (from != to) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {
                if (null != from) {
                    transaction.hide(from);
                }
                if (null != to) {
                    transaction.add(viewDataBinding.container.getId(),to).commit();//只有最后调用commit，才会执行添加
                }
            } else {
                if (null != from) {
                    transaction.hide(from);
                }
                if (null != to) {
                    transaction.show(to).commit();//只有最后调用commit，才会执行显示
                }
            }
        }
    }

}
