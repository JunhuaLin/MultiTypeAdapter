package cn.junhua.android.commonadapter.bean.taobao;

import cn.junhua.android.commonadapter.imp.SpanSize;

/**
 * 猜你喜欢
 * Created by junhua.lin on 2017/12/29.
 */

public class LikeBean implements SpanSize {
    private String url = "https://gw.alicdn.com/imgextra/i2/2989471133/TB2Sns2bLjM8KJjSZFNXXbQjFXa_!!2989471133.jpg_760x760q50s150.jpg_.webp";
    private String title = "[为你推荐]春装女装新款名媛气质红色礼服裙女中长款年会正式场合连衣裙";
    private String price = "666";
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
