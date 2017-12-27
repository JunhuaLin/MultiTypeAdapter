package cn.junhua.android.adapter.recyclerview;

/**
 * 视图绑定器基类
 * Created by junhua.lin on 2017/12/27.
 */

public abstract class ViewBinder<T> {

    protected Class<T> mBeanClass;

    public ViewBinder(Class<T> clazz) {
        this.mBeanClass = clazz;
    }

    /**
     * 数据分发
     *
     * @param bean     数据
     * @param position 在列表中的位置
     * @return 返回布局文件的id
     */
    public int dispatchLayoutId(Object bean, int position) {
        return getLayoutId((T) bean, position);
    }

    /**
     * 返回布局文件的id
     *
     * @param bean     数据
     * @param position 在列表中的位置
     * @return 返回布局文件的id
     */
    public abstract int getLayoutId(T bean, int position);


    /**
     * 返回数据类的Class
     *
     * @return 返回数据类的Class
     */
    public Class<T> getBeanClass() {
        return mBeanClass;
    }


    /**
     * 数据分发
     *
     * @param holder   视图的holder
     * @param bean     数据
     * @param position 在列表中的位置
     */
    void dispatchBindView(ViewHolder holder, Object bean, int position) {
        onBindView(holder, (T) bean, position);
    }

    /**
     * 处理数据与视图绑定
     *
     * @param holder   封装adapter中item的复用操作代码的对象
     * @param bean     数据Bean需要强制类型转化
     * @param position 当前条目位置
     */
    public abstract void onBindView(ViewHolder holder, T bean, int position);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewBinder<?> that = (ViewBinder<?>) o;

        return mBeanClass != null ? mBeanClass.equals(that.mBeanClass) : that.mBeanClass == null;
    }

    @Override
    public int hashCode() {
        return mBeanClass != null ? mBeanClass.hashCode() : 0;
    }
}
