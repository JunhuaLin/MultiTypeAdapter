package cn.junhua.android.commonadapter.bean.taobao;

import cn.junhua.android.commonadapter.imp.SpanSize;

/**
 * 猜你喜欢
 * Created by junhua.lin on 2017/12/29.
 */

public class LikeBean implements SpanSize {
    private String url = "http://img.alicdn.com/bao/uploaded/i4/3185860334/TB2KB3xcgwjyKJjSsppXXaxUpXa_!!3185860334.jpg_560x370Q50s50.jpg_.webp";
    private String title = "[为你推荐]日本成人男女士隐形矫正衣脊椎驼背矫正带儿童背部纠正防驼背神器";
    private String price = "66";
    private String count = "12345";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public int getSpanSize() {
        return 1;
    }
}
