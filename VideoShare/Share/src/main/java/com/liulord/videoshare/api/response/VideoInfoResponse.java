package com.liulord.videoshare.api.response;

import com.plugin.internet.core.ResponseBase;
import com.plugin.internet.core.json.JsonProperty;

import java.util.List;

/**
 * Created by liu_lord on 14/12/1.
 */
public class VideoInfoResponse extends ResponseBase {

    @JsonProperty("data")
    public List<VideoDetailResponse> detailInfo;

    @JsonProperty("status")
    public int status;

    @JsonProperty("start_num")
    public int start_num;

    @JsonProperty("req_num")
    public int req_num;

    @JsonProperty("res_num")
    public int res_num;

    @JsonProperty("total_num")
    public int total_num;

    public static class VideoDetailResponse extends ResponseBase {

        @JsonProperty("doc_id")
        public String doc_id;

        @JsonProperty("user")
        public String user_name;

        @JsonProperty("like_num")
        public int like_num;

        @JsonProperty("click_num")
        public int click_num;

        @JsonProperty("url")
        public String video_url;

        @JsonProperty("abs")
        public String desc;

        @JsonProperty("screen")
        public String snapshot_url;

        @JsonProperty("avatar")
        public String user_url;

        @JsonProperty("time")
        public long time;

    }
}
