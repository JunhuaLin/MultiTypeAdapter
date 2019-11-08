# MultiTypeAdapter


[![GitHub license](https://img.shields.io/github/license/JunhuaLin/MultiTypeAdapter.svg?style=plastic)](https://github.com/JunhuaLin/MultiTypeAdapter/blob/master/LICENSE)
[![latest release](https://img.shields.io/github/release/JunhuaLin/MultiTypeAdapter.svg?style=plastic)](https://github.com/JunhuaLin/MultiTypeAdapter/releases)
[![pull requests welcome](https://img.shields.io/badge/pull%20requests-welcome-brightgreen.svg?style=plastic)](https://github.com/JunhuaLin/MultiTypeAdapter/pulls)
[![issues welcome](https://img.shields.io/badge/issues-welcome-brightgreen.svg?style=plastic)](https://github.com/JunhuaLin/MultiTypeAdapter/issues)


RecyclerView多条目适配器，灵活处理一对一，一对多条目，更小的复用粒度。

MultiTypeAdapter的优点如下：
- 1.易维护、易上手
- 2.复用粒度更细
- 3.一对一，一对多条目展示

## 快速使用

### 1.引入依赖

使用Android Studio

在build.gradle中添加如下代码：
```groovy
dependencies {
    implementation 'cn.junhua.android.adapter:multitypeadapter:3.0.0-beta'
}
```

注：3.x版本较2.x版本改动非常大，不兼容之前版本。2.x最后一版``2.3.3``



关系图：

![关系图](https://github.com/JunhuaLin/MultiTypeAdapter/blob/master/photo/关系图3.png)

### 2.基本用法
#### a.步骤
1. 创建JavaBean，如BasisImgBean.class，BasisTextBean.class。
2. 创建布局文件，如binder_basis_img.xml，binder_left_text.xml，binder_right_text.xml。
3. 创建ViewBinder：
    - 继承ItemViewBinder<T, VH extends RecyclerView.ViewHolder>
    - 继承SimpleItemViewBinder<T> ,内部默认使用CommonViewHolder简化创建代码
4. 创建MultiTypeAdapter并注册ViewBinder。

注：具体细节参考**实例展示**或者查看**源代码**。

#### b.实例
1.创建JavaBean
```java
public class BasisImgBean {
    private int res = R.mipmap.content8;
}

public class BasisTextBean {
    private int type;
    private String text = "BasisTextBean.class";
}
```
2.创建布局文件（略）

3.创建ViewBinder

两种方式创建ViewBinder：

a、继承ItemViewBinder<T, VH extends RecyclerView.ViewHolder>
b、继承SimpleItemViewBinder<T>

两者本质是一样的，SimpleItemViewBinder简化了ItemViewBinder的代码，默认使用CommonViewHolder简化创建过程。
CommonViewHolder内部实现了常用的方法，并提供View缓存功能，所有View查找一次后都会被缓存起来，后续查找直接缓存返回。


```java
//简单布局可以继承SimpleItemViewBinder简化创建代码
public class ImgBinderItemView extends SimpleItemViewBinder<BasisImgBean> {

    @Override
    public void onBindViewHolder(CommonViewHolder holder, BasisImgBean bean, int position) {
        holder.setText(R.id.tv_type_content, "BasisImgBean.class -> R.layout.binder_basis_img")
                .setImageResource(R.id.iv_img, bean.getRes());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.binder_basis_img;
    }
}

//对比两种继承方式
public class TextLeftBinderItemView extends SimpleItemViewBinder<BasisTextBean> {

    @Override
    public void onBindViewHolder(CommonViewHolder holder, BasisTextBean bean, int position) {
        holder.setText(R.id.tv_layout_left, "R.layout.binder_left_text")
                .setText(R.id.tv_class_left, bean.getText())
                .setOnItemClickListener(view -> Toast.makeText(view.getContext(),"TextLeftBinderItemView item " + position,Toast.LENGTH_SHORT).show());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.binder_left_text;
    }
}

//复杂布局可以继承ItemViewBinder，自定义ViewHolder完成复杂操作
public class TextRightBinderItemView extends ItemViewBinder<BasisTextBean, TextRightBinderItemView.TextRightViewHolder> {

    @Override
    public TextRightViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new TextRightViewHolder(inflater.inflate(R.layout.binder_right_text, parent, false));
    }

    @Override
    public void onBindViewHolder(TextRightViewHolder holder, BasisTextBean bean, int position) {
        holder.bindData(bean, position);
    }

    class TextRightViewHolder extends RecyclerView.ViewHolder {
        TextView tv_type_right;
        TextView tv_class_right;

        TextRightViewHolder(View itemView) {
            super(itemView);
            tv_type_right = itemView.findViewById(R.id.tv_type_right);
            tv_class_right = itemView.findViewById(R.id.tv_class_right);
        }

        void bindData(BasisTextBean bean, int position) {
            tv_type_right.setText("R.layout.binder_right_text");
            tv_class_right.setText(bean.getText());
            itemView.setOnClickListener(view -> Toast.makeText(view.getContext(), "TextRightBinderItemView item " + position, Toast.LENGTH_SHORT).show());
        }
    }
}

```

4.创建MultiTypeAdapter并注册ItemViewBinder
```java
    //初始化MultiTypeAdapter
    MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
    //注册ViewBinder
    multiTypeAdapter.register(BasisImgBean.class, new ImgBinderItemView());//一对一
    multiTypeAdapter.register(BasisTextBean.class)//一对多
            .mapping(new TextLeftBinderItemView(), new TextRightBinderItemView())
            .match((bean, position) -> {
                if (bean.getType() == 1) return TextLeftBinderItemView.class;
                return TextRightBinderItemView.class;
            });
    //设置数据集合
    mMultiTypeAdapter.setList(...);
```
#### c.效果图jpg
![基础用法](https://github.com/JunhuaLin/MultiTypeAdapter/blob/master/photo/基础用法.jpg)

### 3.更多方法

#### MultiTypeAdapter：
```java
//设置默认的ItemViewBinder，没有匹配到合适的ItemViewBinder使用默认的
public void setDefaultViewBinder(@Nullable ItemViewBinder<Object, ?> binder)

//为数据条目注册ItemViewBinder
public <T> void register(@NonNull Class<T> beanClass, @NonNull ItemViewBinder<T, ?> binder)

//为同一个数据类型注册不同的ItemViewBinder，处理一种数据类型对应多种ItemViewBinder情况
public <T> OneToManyMapper<T> register(@NonNull Class<T> beanClass)

//设置展示的数据集合，内部仅引用数据集合
public void setList(@Nullable List<?> list)
```

#### ItemViewBinder：
```java
public final MultiTypeAdapter getAdapter()
protected long getItemId(T item, int position)
protected void onViewRecycled(ViewHolder holder)
protected boolean onFailedToRecycleView(ViewHolder holder)
protected void onViewAttachedToWindow(ViewHolder holder)
protected void onViewDetachedFromWindow(ViewHolder holder)
```


#### CommonViewHolder：
```java
//findViewById同样的功能，内部提供缓存view的功能
public <T extends View> T findView(@IdRes int viewId)

public RecyclerView getRecyclerView()

//快捷设置TextView的文本
public CommonViewHolder setText(@IdRes int viewId, String text)
public CommonViewHolder setText(@IdRes int viewId, @StringRes int resId)

//快捷设置TextView的文本颜色
public CommonViewHolder setTextColor(@IdRes int viewId, ColorStateList colorStateList)
public CommonViewHolder setTextColor(@IdRes int viewId, @ColorInt int colorInt)

//快捷设置ImageView的src
public CommonViewHolder setImageResource(@IdRes int imageViewId, @DrawableRes int drawableId)

//快捷设置View的背景
public CommonViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int drawableId)

//设置条目的根itemView的点击事件
public CommonViewHolder setOnItemClickListener(View.OnClickListener onClickListener)

//清除View缓存，一般不需要调用
public void clear()

//获取常用资源
public Context getContext()
public Resources getResources()
public String getString(@StringRes int id)

```


### 4.更多实例展示

#### 淘宝首页效果（基本用法） 效果图gif
![淘宝首页效果](https://github.com/JunhuaLin/MultiTypeAdapter/blob/master/photo/淘宝首页.gif)

#### 朋友圈多图效果（高级用法：一对多）效果图gif
![朋友圈效果](https://github.com/JunhuaLin/MultiTypeAdapter/blob/master/photo/朋友圈.gif)

## 结语

感谢drakeet，部分优化参考[MultiType](https://github.com/drakeet/MultiType).

欢迎PR 和 Issues。
