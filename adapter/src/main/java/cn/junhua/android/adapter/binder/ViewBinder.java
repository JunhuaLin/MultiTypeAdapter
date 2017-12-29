package cn.junhua.android.adapter.binder;

/**
 * 视图绑定器基类
 * Created by junhua.lin on 2017/12/27.
 */
@SuppressWarnings("unchecked")
public abstract class ViewBinder<T> {

    private Class<T> mBeanClass;

    public ViewBinder(Class<T> clazz) {
        this.mBeanClass = clazz;
    }

    /**
     * 返回数据类的Class
     *
     * @return 返回数据类的Class
     */
    public final Class<T> getBeanClass() {
        return mBeanClass;
    }


    /**
     * 获得ViewBinder中findView次数,即需要缓存View的个数
     *
     * @param bean     数据
     * @param position 数据在集合中的位置
     * @return ViewBinder中findView次数
     */
    public int performCountView(Object bean, int position) {
        return onCountView((T) bean, position);
    }

    /**
     * 在子类中重写该方法<br/>
     * 作用：<br/>
     * 1.减少存储结构扩容带来的性能消耗。<br/>
     * 2.避免容量未完全使用带来的内存浪费。<br/>
     *
     * @return 需要缓存View的个数
     */
    public abstract int onCountView(T bean, int position);

    /**
     * 执行获取布局id操作
     *
     * @param bean     数据
     * @param position 在列表中的位置
     * @return 返回布局文件的id
     */
    public int performItemViewType(Object bean, int position) {
        return onItemViewType((T) bean, position);
    }

    /**
     * 返回布局文件的id
     *
     * @param bean     数据
     * @param position 在列表中的位置
     * @return 返回布局文件的id
     */
    public abstract int onItemViewType(T bean, int position);


    /**
     * 执行绑定视图操作
     *
     * @param holder   视图的holder
     * @param bean     数据
     * @param position 在列表中的位置
     */
    public void performBindView(ViewHolder holder, Object bean, int position) {
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
