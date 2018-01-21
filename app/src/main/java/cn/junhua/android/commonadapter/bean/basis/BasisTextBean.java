package cn.junhua.android.commonadapter.bean.basis;

/**
 * BasisTextBean
 * Created by junhua on 2018/1/21.
 */
public class BasisTextBean {
    private int type;
    private String text = "BasisTextBean.class";

    public BasisTextBean(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
