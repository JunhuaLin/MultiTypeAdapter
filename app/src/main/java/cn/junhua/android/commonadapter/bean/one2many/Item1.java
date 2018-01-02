package cn.junhua.android.commonadapter.bean.one2many;

/**
 * Created by junhua on 17-3-15.
 */

public class Item1 {
    private int imageId;
    private String title;
    private int type;

    public Item1() {
    }

    public Item1(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    public Item1(int imageId, String title, int type) {
        this.imageId = imageId;
        this.title = title;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
