package com.liulord.videoshare.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.liulord.videoshare.R;
import com.liulord.videoshare.api.request.VideoInfoRequest;
import com.liulord.videoshare.api.response.VideoInfoResponse;
import com.plugin.common.utils.CustomThreadPool;
import com.plugin.internet.InternetUtils;
import com.plugin.internet.core.NetWorkException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aaronliu on 14-12-1.
 */
public class VideoFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestData(this.getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_video, container, false);


        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        GridView base = (GridView) rootView.findViewById(R.id.baseGrid);
        SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), getData(), R.layout.vlist,
                new String[] {"background", "user", "hint"},
                new int[] { R.id.backgroud, R.id.user, R.id.hint});
//        setListAdapter(adapter);
        base.setAdapter(adapter);
        return rootView;
    }

    private List<Map<String, Object>> getData() {
        int count = 10;
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        for(int i=0; i!= count; ++i) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("background", R.drawable.icon);
            map.put("user", R.drawable.ic_launcher);
            map.put("hint", "test for list fragment");
            list.add(map);
        }
        return list;
    }

    private void requestData(final Context context)
    {
        CustomThreadPool.asyncWork(new Runnable() {
            @Override
            public void run() {
                try {
                    VideoInfoRequest request = new VideoInfoRequest(0, 0, 10, 0, 1);
                    VideoInfoResponse response = InternetUtils.request(context, request);

                } catch (NetWorkException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
