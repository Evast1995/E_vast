package com.ychen.tourism.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ychen.tourism.MyApplication;
import com.ychen.tourism.R;
import com.ychen.tourism.activity.AddPlanActivity;
import com.ychen.tourism.activity.MainActivity;
import com.ychen.tourism.bean.PlanBean;
import com.ychen.tourism.helper.Table;
import com.ychen.tourism.service.InitService;
import com.ychen.tourism.util.ContantsUtil;
import com.ychen.tourism.widget.adapter.PlanAdapter;

/**
 * Created by evast on 16-3-17.
 */
public class PlanFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>,View.OnClickListener{
    private View mView;
    private ListView mListView;
    private Context mContext;
    private PlanAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_plan,null);
        initView();
        init();
        return mView;
    }

    /**
     * 初始化
     */
    private void init(){
        mListView = (ListView) mView.findViewById(R.id.list_view);
        mAdapter = new PlanAdapter(mContext,null);
        mListView.setAdapter(mAdapter);
        /** 加载数据库数据　加载成功后更新adatper*/
        getLoaderManager().initLoader(300, null, this);
        registerDataChange();
        itemClick();

    }

    /**
     * 列表点击事件
     */
    private void itemClick(){
        /** 短点击进入编辑页面*/
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext,AddPlanActivity.class);
                Cursor cursor = (Cursor) mAdapter.getItem(position);
                PlanBean planBean = new PlanBean(cursor);
                intent.putExtra(ContantsUtil.PLAN_BEAN_KEY,planBean);
                startActivity(intent);
            }
        });
        /** 长点击弹出删除对话框*/
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                Cursor cursor = (Cursor) mAdapter.getItem(position);
                builder.setTitle(R.string.is_delete);
                final PlanBean planBean = new PlanBean(cursor);
                builder.setMessage(planBean.toString());
                builder.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        MyApplication myApplication = (MyApplication) getActivity().getApplication();
                        InitService initService = ((MainActivity)getActivity()).getNetService();
                        initService.delete(Table.PlanTable.CONTENT_URI, Table.PlanTable.ID + " = ?",
                                new String[]{String.valueOf(planBean.getId())}, new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, R.string.delete_successful, Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        getLoaderManager().restartLoader(300, null, PlanFragment.this);
                                    }
                                });
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    /**
     * 数据库变化时重置列表数据
     */
    private ContentObserver mContentObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            getLoaderManager().restartLoader(300, null, PlanFragment.this);
        }
    };

    /**
     * 注册数据库发生变化时候对应列表数据也变化
     */
    private void registerDataChange(){
        mContext.getContentResolver().registerContentObserver(
                Table.PlanTable.CONTENT_URI, true, mContentObserver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.getContentResolver().unregisterContentObserver(mContentObserver);
    }

    private void initView(){
        mView.findViewById(R.id.add_plan).setOnClickListener(this);
    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new CursorLoader(mContext, Table.PlanTable.CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_plan: {
                addPlanClick();
            }
        }
    }

    private void addPlanClick(){
        Intent intent = new Intent(mContext, AddPlanActivity.class);
        startActivity(intent);
    }
}
