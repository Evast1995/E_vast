package com.ychen.tourism.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ychen.tourism.R;
import com.ychen.tourism.activity.AddCommentActivity;
import com.ychen.tourism.bean.CommentBean;
import com.ychen.tourism.util.ContantsUtil;
import com.ychen.tourism.util.JSONParseUtil;
import com.ychen.tourism.util.SharedPreferenceUtil;
import com.ychen.tourism.widget.adapter.CommentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evast on 16-3-17.
 */
public class CommentFragment extends Fragment implements View.OnClickListener{
    private Context mContext;
    private ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment,null);
        initListView(view);
        return view;
    }

    private void initListView(View view){
        view.findViewById(R.id.add_comment).setOnClickListener(this);
        listView = (ListView) view.findViewById(R.id.comment_list);
        List<CommentBean> list = getData();
        listView.setAdapter(new CommentAdapter(mContext,list));

    }

    private List<CommentBean> getData(){
        List<CommentBean> list = new ArrayList<>();
        int num = (int) SharedPreferenceUtil.get(mContext,ContantsUtil.COMMENT_NUM,0);
        for(int i=0;i<=num;i++){
            String json = (String) SharedPreferenceUtil.get(mContext,ContantsUtil.COMMENT_KEY+i,"");
            if(!TextUtils.isEmpty(json)){
                CommentBean commentBean = JSONParseUtil.parseObject(json,CommentBean.class);
                list.add(commentBean);
            }
        }
        Log.e("--main--","list size:"+list.size());
        return list;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_comment:{
                Intent intent = new Intent(mContext, AddCommentActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
