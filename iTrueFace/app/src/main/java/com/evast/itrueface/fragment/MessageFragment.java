package com.evast.itrueface.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.evast.itrueface.R;
import com.evast.itrueface.adapter.MessageAdapter;
import com.evast.itrueface.bean.message.MessageVo;
import com.evast.itrueface.core.BaseFragment;
import com.evast.itrueface.xmpp.XmlppManager;

import java.util.List;

/**
 * Created by 72963 on 2015/12/10.
 */
public class MessageFragment extends BaseFragment {
    private ListView listView;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message,container,false);
    }

    @Override
    protected void init(View rootView) {
        listView = (ListView) rootView.findViewById(R.id.message_listview);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData(){
        List<MessageVo> list = XmlppManager.getInstance().getMessageData();
        listView.setAdapter(new MessageAdapter(context,list));
    }
}
