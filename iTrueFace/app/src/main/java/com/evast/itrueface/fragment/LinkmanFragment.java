package com.evast.itrueface.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.evast.itrueface.R;
import com.evast.itrueface.adapter.ClassRoomAdapter;
import com.evast.itrueface.adapter.FriendAdapter;
import com.evast.itrueface.bean.message.CourseVo;
import com.evast.itrueface.bean.message.FriendVo;
import com.evast.itrueface.core.BaseFragment;
import com.evast.itrueface.xmpp.XmlppManager;

import java.util.List;

/**
 * Created by 72963 on 2015/12/10.
 */
public class LinkmanFragment extends BaseFragment {

    private Context context;
    private ListView courseLv;
    private ListView friendLv;

    private TextView classRoomTv;
    private TextView friendTv;

    private List<CourseVo> courseList;
    private List<FriendVo> friendList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_linkman,container,false);
    }

    @Override
    protected void init(View rootView) {
        initDate();
        initView(rootView);
    }

    /**
     * 初始化数据
     */
    private void initDate(){
        courseList =  XmlppManager.getInstance().getContactsCourseData();
        friendList =XmlppManager.getInstance().getContactsFriendData();
    }
    /**
     * 初始化视图
     * @param rootView
     */
    private void initView(View rootView){
        courseLv = (ListView)rootView.findViewById(R.id.course_listview);
        friendLv = (ListView) rootView.findViewById(R.id.friend_listview);
        classRoomTv = (TextView) rootView.findViewById(R.id.contacts_classroom);
        friendTv = (TextView) rootView.findViewById(R.id.contacts_friends);

        String friendStr = getResources().getString(R.string.friend);
        friendStr = String.format(friendStr, friendList.size());
        friendTv.setText(friendStr);

        String classRoomStr = getResources().getString(R.string.classroom);
        classRoomStr = String.format(classRoomStr,courseList.size());
        classRoomTv.setText(classRoomStr);

        courseLv.setAdapter(new ClassRoomAdapter(context, courseList));
        friendLv.setAdapter(new FriendAdapter(context,friendList));
    }


}
