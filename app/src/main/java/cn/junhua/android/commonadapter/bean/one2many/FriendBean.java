package cn.junhua.android.commonadapter.bean.one2many;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.junhua.android.commonadapter.R;

/**
 * FriendBean.class
 * Created by linjunhua on 2018/1/6.
 */
public class FriendBean {
    private String name;
    private String content;
    private List<Integer> photos;
    private int avatar;

    public FriendBean() {
    }

    public FriendBean(String name, String content, List<Integer> photos, int avatar) {
        this.name = name;
        this.content = content;
        this.photos = photos;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Integer> photos) {
        this.photos = photos;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public static List<FriendBean> getRandomFriend() {
        Random random = new Random();
        List<FriendBean> beans = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            List<Integer> photoList = new ArrayList<>();
            for (int j = 0; j < random.nextInt(10); j++) {
                photoList.add(photoArray[random.nextInt(photoArray.length)]);
            }
            beans.add(new FriendBean(nameArray[random.nextInt(nameArray.length)],
                    contentArray[random.nextInt(contentArray.length)],
                    photoList,
                    avatarArray[random.nextInt(avatarArray.length)]));
        }
        return beans;
    }


    static String[] nameArray = new String[]{
            "杰克马",
            "小马哥",
            "贾跃亭",
            "孙中山",
            "李太白",
            "高圆圆",
            "白百何",
            "MC天佑"
    };
    static String[] contentArray = new String[]{
            "人生是一条没有回程的单行线，上帝不会给你一张返程的票",
            "我把思念都告诉了心跳，心里的你有没有偷听到。",
            "你的眼眸，是我永远企及不到的彼岸 \n你的灵魂，是我永远触摸不到的玉兰",
            "勇气是控制恐惧心理，而不是心里毫无恐惧。",
            "生活不可能像你想的那么美好，但也不会像你想的那么糟。",
            "多年后 你是喝我的喜酒 还是和我喝交杯酒",
            "胸不平何以平天下，乳不巨何以聚人心！",
            "我的名字与你的名字，不就是一段故事吗？",
            "障碍与失败，是通往成功最稳靠的踏脚石，肯研究、利用它们，便能从失败中培养出成功。",
            "决定你的人生高度的，不是你的才能，而是你的人生态度！"
    };
    static int[] photoArray = new int[]{
            R.mipmap.content0,
            R.mipmap.content1,
            R.mipmap.content2,
            R.mipmap.content3,
            R.mipmap.content4,

            R.mipmap.content5,
            R.mipmap.content6,
            R.mipmap.content7,
            R.mipmap.content8,
            R.mipmap.content9,

            R.mipmap.content10,
            R.mipmap.content11,
            R.mipmap.content12,
            R.mipmap.content13,
            R.mipmap.content14,

            R.mipmap.content15,
            R.mipmap.content16,
    };
    static int[] avatarArray = new int[]{
            R.mipmap.avatar0,
            R.mipmap.avatar1,
            R.mipmap.avatar2,
            R.mipmap.avatar3,
            R.mipmap.avatar4,

            R.mipmap.avatar5,
            R.mipmap.avatar6,
            R.mipmap.avatar7,
            R.mipmap.avatar8,
            R.mipmap.avatar9,

            R.mipmap.avatar10,
            R.mipmap.avatar11,
            R.mipmap.avatar12,
            R.mipmap.avatar13,
    };
}