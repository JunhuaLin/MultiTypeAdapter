package cn.junhua.android.adapter.recyclerview;


/**
 * 多类型条目包装类->条目由多个SingleTypeViewBinder组成
 */
public class MutilTypeViewBinder<T> extends ViewBinder<T> {

    private ViewBinderManager mViewBinderManager;

    public MutilTypeViewBinder(Class<T> beanClass) {
        super(beanClass);
        mViewBinderManager = new ViewBinderManager();
    }

    @Override
    public int getLayoutId(T bean, int position) {
        return 0;
    }

    @Override
    public void onBindView(ViewHolder holder, T bean, int position) {
        //todo 采用创建这模式构建多条目 ViewBinder
    }
}
