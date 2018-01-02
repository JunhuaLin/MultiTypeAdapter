package cn.junhua.android.commonadapter.bean.taobao;

import java.util.Arrays;
import java.util.List;

import cn.junhua.android.commonadapter.imp.SpanSize;

/**
 * Created by junhua.lin on 2017/12/29.
 */
public class BannerBean implements SpanSize {
    private List<String> urlList;

    public BannerBean() {
        this.urlList = Arrays.asList(
                "http://img.alicdn.com/imgextra/i4/188/TB2BtF8kf6H8KJjSspmXXb2WXXa_!!188-0-yamato.jpg_q50.jpg",
                "http://gw.alicdn.com/imgextra/i3/125/TB2yajhkf2H8KJjy1zkXXXr7pXa_!!125-0-lubanu.jpg_q50.jpg",
                "http://aecpm.alicdn.com/tfscom/TB1saFMdpTM8KJjSZFlXXaO8FXa.jpg_q50.jpg",
                "http://gw.alicdn.com/imgextra/i4/26/TB2y7Tmj9_I8KJjy0FoXXaFnVXa_!!26-0-luban.jpg_q50.jpg"
        );

    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    @Override
    public int getSpanSize() {
        return 2;
    }
}
