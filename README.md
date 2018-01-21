# MultiTypeAdapter
RecyclerView在我们开发的过程中使用频率极高，而在创建适配器的时候每次都要写很多相同的代码，每次都这样操作效率很低。
当在处理多类型条目时候，虽然写几个判断条件创建不同的ViewHolder和对应布局就可以了，但是当布局非常复杂的时候这种方式就很难维护了。
因此该项目将适配器创建的过程进行了封装，使代码量大幅降低同时还使适配器的复用粒度降低到布局条目级别。

MultiTypeAdapter的优点如下：
- 1.易维护
- 2.复用粒度更细
- 3.易上手

## 快速使用

### 1.引入依赖

使用Android Studio

在build.gradle中添加如下代码：
```groovy
dependencies {
    compile 'cn.junhua.android.adapter:multitypeadapter:2.1.0'
}
```

### 2.基本用法
#### a.步骤
1. 创建JavaBean，如BasisImgBean.class，BasisTextBean.class。
2. 创建布局文件，如binder_basis_img.xml，binder_left_text.xml，binder_right_text.xml。
3. 创建ViewBinder：
    - 单布局条目：创建单布局条目继承ViewBinder子类SingleViewBinder。
    - 多布局条目：创建多布局条目使用MultiViewBinder组合多个单布局条目即可。
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
```java
public class ImgBinderView extends SingleViewBinder<BasisImgBean> {

    public ImgBinderView() {
        super(BasisImgBean.class, R.layout.binder_basis_img);
    }

    @Override
    public void onBindView(ViewHolder holder, BasisImgBean bean, int position) {
        holder.setText(R.id.tv_type_content, "BasisImgBean.class" + "->R.layout.binder_basis_img")
                .setImageResource(R.id.iv_img, bean.getRes());
    }
}

public class TextLeftBinderView extends SingleViewBinder<BasisTextBean> {

    public TextLeftBinderView() {
        super(BasisTextBean.class, R.layout.binder_left_text);
    }

    @Override
    public void onBindView(ViewHolder holder, BasisTextBean bean, int position) {
        holder.setText(R.id.tv_layout_left, "R.layout.binder_left_text")
                .setText(R.id.tv_class_left, bean.getText());
    }
}

public class TextRightBinderView extends SingleViewBinder<BasisTextBean> {

    public TextRightBinderView() {
        super(BasisTextBean.class, R.layout.binder_right_text);
    }

    @Override
    public void onBindView(ViewHolder holder, BasisTextBean bean, final int position) {
        holder.setText(R.id.tv_type_right, "R.layout.binder_right_text")
                .setText(R.id.tv_class_right, bean.getText());

        holder.findView(R.id.rl_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "clock item " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
```

SingleViewBinder方法介绍：
>public SingleViewBinder(Class<T> beanClass, int layoutId)

- 参数1：beanClass，为泛型T的类类型，如Item1.class,用于区分条目。
- 参数2：layoutId，为条目布局资源id。

>public int onCountView(Item1 bean, int position)

该方法返回用于初始化该布局View缓存的初始值，默认值为6，建议重写避免扩容和内存浪费。

>public void onBindView(ViewHolder holder, Item1 bean, int position)

- 第一个参数：holder，用来封装复用条目的，通过它的findView（id）可以得到条目中所有到View对象，然后进行数据填充即可。同一个id的View findView只会查找一次，之后从缓存中获取。
ViewHolder的setImageResource()和setBackgroundResource()是用来快速填充文字和图片的方法，它们的返回值是ViewHolder本身。
- 第二个参数：bean，就是我们数据对象的引用。
- 第三个参数：position，当前条目位置。


4.创建MultiTypeAdapter并注册ViewBinder
```java
    //初始化MultiTypeAdapter
    MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
    //注册ViewBinder
    multiTypeAdapter.register(new ImgBinderView());//一对一
    multiTypeAdapter.register(BasisTextBean.class)//一对多
            .mapping(new TextLeftBinderView(), new TextRightBinderView())
            .match(new TypeMatcher<BasisTextBean>() {
                @Override
                public Class<? extends ViewBinder<BasisTextBean>> onMatch(BasisTextBean bean, int position) {
                    if (bean.getType() == 1) return TextLeftBinderView.class;
                    return TextRightBinderView.class;
                }
            });
```
#### c.效果图jpg
![基础用法](https://github.com/JunhuaLin/MultiTypeAdapter/blob/master/photo/基础用法.jpg)

#### 3.UML以及更多方法
![UML](https://github.com/JunhuaLin/MultiTypeAdapter/blob/master/photo/uml.png)

ViewHolder：
```java
public <T extends View> T findView(@IdRes int viewId)
public RecyclerView getRecyclerView()
public ViewHolder setText(@IdRes int viewId, String text)
public ViewHolder setText(@IdRes int viewId, @StringRes int resId)
public ViewHolder setImageResource(@IdRes int imageViewId, @DrawableRes int drawableId) 
public ViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int drawableId)
```

BinderView：
```java
protected long getItemId(@NonNull T bean) 
protected void onViewRecycled(ViewHolder holder) 
protected boolean onFailedToRecycleView(ViewHolder holder)
protected void onViewAttachedToWindow(ViewHolder holder)
protected void onViewDetachedFromWindow(ViewHolder holder)
```

### 4.更多实例展示

#### 淘宝首页效果（基本用法） 效果图gif
![淘宝首页效果](https://github.com/JunhuaLin/MultiTypeAdapter/blob/master/photo/淘宝首页.gif)

#### 朋友圈多图效果（高级用法：一对多）效果图gif
![朋友圈效果](https://github.com/JunhuaLin/MultiTypeAdapter/blob/master/photo/朋友圈.gif)

## 结语

欢迎PR 和 Issues。