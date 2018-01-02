package cn.junhua.android.commonadapter.bean.taobao;

import cn.junhua.android.commonadapter.imp.SpanSize;

/**
 * Created by junhua.lin on 2017/12/29.
 */

public class MenuBean implements SpanSize {
    private String url = "http://img.alicdn.com/tps/TB1Y3HqPVXXXXaCXFXXXXXXXXXX-200-200.png_230x87Q90s50.jpg_.webp";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int getSpanSize() {
        return 2;
    }
}
