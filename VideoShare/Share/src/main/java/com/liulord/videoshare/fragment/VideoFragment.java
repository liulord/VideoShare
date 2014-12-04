package com.liulord.videoshare.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.liulord.utils.DebugLog;
import com.liulord.videoshare.R;
import com.liulord.videoshare.activity.VideoPlayActivity;
import com.liulord.videoshare.api.request.VideoInfoRequest;
import com.liulord.videoshare.api.response.VideoInfoResponse;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
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

    private static final String TAG = "videoshare";
    public List<VideoData> mVideolist;
    DisplayImageOptions mOptions;
    VideoDataAdapter mAdapter;
    PullToRefreshGridView mBaseGrid;
    Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVideolist = new ArrayList<VideoData>();
//        ImageLoader.getInstance().init(new ImageLoaderConfiguration());
        for(int i = 0 ; i!= 20; ++i)
            mVideolist.add(new VideoData("", "", "", "",0, ""));
        mOptions = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.icon)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)     //设置图片的解码类型
                .build();
        mHandler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_video, container, false);

        final Context context = this.getActivity().getApplicationContext();
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        mBaseGrid = (PullToRefreshGridView) rootView.findViewById(R.id.baseGrid);
        mBaseGrid.setMode(PullToRefreshBase.Mode.BOTH);
        mBaseGrid.getLoadingLayoutProxy(false, true).setPullLabel("正在加载");
        mBaseGrid.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载");
        mBaseGrid.getLoadingLayoutProxy(false, true).setReleaseLabel("加载完成");
        mBaseGrid.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                DebugLog.d(TAG, "pull down!");
                requestData(context, 0);
//                base.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                DebugLog.d(TAG, "pull up!");
                requestData(context, 1);
//                base.onRefreshComplete();
            }
        });

//        SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), getData(), R.layout.vlist,
//                new String[] {"background", "user", "hint"},
//                new int[] { R.id.backgroud, R.id.user, R.id.hint});
//        setListAdapter(adapter);
        mAdapter = new VideoDataAdapter(mVideolist, this.getActivity());
        mBaseGrid.setAdapter(mAdapter);
        mBaseGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                VideoData data = (VideoData) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(context, VideoPlayActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(data.videoUrl));
//                intent.putExtra("url",data.videoUrl);
                context.startActivity(intent);
            }
        });
        requestData(context,0);
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


    /**
     *
     * @param context
     * @param mode 0: refresh 1: load more
     */
    private void requestData(final Context context, final int mode ) {
        CustomThreadPool.asyncWork(new Runnable() {
            @Override
            public void run() {
                try {
                    switch (mode) {
                        case 0: {
                            VideoInfoRequest request = new VideoInfoRequest(0, 0, 20, 0, 1);
                            VideoInfoResponse response = InternetUtils.request(context, request);
                            if (response != null && response.detailInfo != null && !response.detailInfo.isEmpty()) {
                                mVideolist.clear();
                                for (VideoInfoResponse.VideoDetailResponse data : response.detailInfo) {
                                    mVideolist.add(new VideoData(data.snapshot_url, data.user_url, data.user_name, data.desc, data.like_num, data.video_url));
                                }
                                DebugLog.d(TAG, response.toString());
                            }
                        }
                        break;
                        case 1: {
                            int start_num = mVideolist.size();
                            VideoInfoRequest request = new VideoInfoRequest(0, start_num, 20, 0, 1);
                            VideoInfoResponse response = InternetUtils.request(context, request);
                            if (response != null && response.detailInfo != null && !response.detailInfo.isEmpty()) {
//                        mVideolist.clear();
                                for (VideoInfoResponse.VideoDetailResponse data : response.detailInfo) {
                                    mVideolist.add(new VideoData(data.snapshot_url, data.user_url, data.user_name, data.desc, data.like_num, data.video_url));
                                }
                                DebugLog.d(TAG, response.toString());
                            }
                        }
                        break;
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                            mBaseGrid.onRefreshComplete();
                        }
                    });
                } catch (NetWorkException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    class VideoData {
        public String backUrl;
        public String userUrl;
        public String userName;
        public String hint;
        public int like;
        public String videoUrl;

        VideoData(String backUrl, String userUrl, String userName, String hint, int like, String videoUrl) {
            this.backUrl = backUrl;
            this.userUrl = userUrl;
            this.userName = userName;
            this.hint = hint;
            this.like = like;
            this.videoUrl = videoUrl;
        }
    }

    class VideoDataAdapter extends BaseAdapter
    {
        List<VideoData> mList;
        LayoutInflater mInflater;
        public VideoDataAdapter(List<VideoData> data, Activity context) {
            super();
            mList = data;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View root = view;
            ViewHolder holder;

            if(root == null) {
                root = mInflater.inflate(R.layout.vlist, viewGroup, false);
                holder = new ViewHolder();
                holder.background = (ImageView) root.findViewById(R.id.backgroud);
                holder.user = (ImageView)root.findViewById(R.id.user);
                holder.hint = (TextView) root.findViewById(R.id.hint);
                holder.user_name = (TextView) root.findViewById(R.id.user_name);
                root.setTag(holder);
            }
            else
                holder = (ViewHolder)view.getTag();
            ImageLoader.getInstance().displayImage(((VideoData)getItem(i)).backUrl, holder.background, mOptions);
            ImageLoader.getInstance().displayImage(((VideoData)getItem(i)).userUrl, holder.user, mOptions);
            holder.hint.setText(((VideoData)getItem(i)).hint);
            holder.user_name.setText(((VideoData)getItem(i)).userName);

            return root;
        }
    }

    static class ViewHolder
    {
        ImageView background;
        ImageView user;
        TextView hint;
        TextView user_name;
    }

}
