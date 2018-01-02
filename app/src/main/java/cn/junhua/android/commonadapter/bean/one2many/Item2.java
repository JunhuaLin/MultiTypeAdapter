package cn.junhua.android.commonadapter.bean.one2many;

/**
 * Created by junhua on 17-3-15.
 */

public class Item2 {
    private String title;
    private String info;


    public Item2() {
    }


    public Item2(String title, String info) {
        this.title = title;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
