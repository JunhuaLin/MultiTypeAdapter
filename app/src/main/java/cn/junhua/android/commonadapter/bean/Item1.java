package cn.junhua.android.commonadapter.bean;

/**
 * Created by junhua on 17-3-15.
 */

public class Item1 {
    private int imageId;
    private String title;

    public Item1() {
    }

    public Item1(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
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
