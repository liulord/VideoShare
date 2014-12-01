package com.liulord.videoshare.api.request;

import com.liulord.videoshare.api.response.VideoInfoResponse;
import com.plugin.internet.core.annotations.HttpMethod;
import com.plugin.internet.core.annotations.OptionalParam;
import com.plugin.internet.core.annotations.RequiredParam;
import com.plugin.internet.core.annotations.RestMethodUrl;

/**
 * Created by liu_lord on 14/12/1.
 */
@RestMethodUrl("http://182.92.226.25:80/mv_app/Search.php")
@HttpMethod("GET")
public class VideoInfoRequest extends VideoRequestBase<VideoInfoResponse> {

    @RequiredParam("req_type")
    int req_type;

    @RequiredParam("start_num")
    int start_num;

    @RequiredParam("req_num")
    int req_num;

    @RequiredParam("order")
    int order;

    @RequiredParam("doc_type")
    int doc_type;

    @RequiredParam("query")
    String query;

    @OptionalParam("version")
    String version;

    public VideoInfoRequest(int req_type, int start_num, int req_num, int order, int doc_type) {
        this.req_type = req_type;
        this.start_num = start_num;
        this.req_num = req_num;
        this.order = order;
        this.doc_type = doc_type;
        this.query="[all]_";
    }

    public VideoInfoRequest(int req_type, int start_num, int req_num, int order, int doc_type, String query) {
        this.req_type = req_type;
        this.start_num = start_num;
        this.req_num = req_num;
        this.order = order;
        this.doc_type = doc_type;
        this.query = query;
    }

    public VideoInfoRequest(int req_type, int start_num, int req_num, int order, int doc_type, String query, String version) {
        this.req_type = req_type;
        this.start_num = start_num;
        this.req_num = req_num;
        this.order = order;
        this.doc_type = doc_type;
        this.query = query;
        this.version = version;
    }
}
