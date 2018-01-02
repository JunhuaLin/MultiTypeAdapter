# MultiTypeAdapter
RecyclerView在我们开发的过程中使用频率极高，而在创建适配器的时候每次都要写很多相同的代码，每次都这样操作效率很低。
当在处理多类型条目时候，虽然写几个判断条件创建不同的ViewHolder和对应布局就可以了，但是当布局非常复杂的时候这种方式就很难维护了。
因此该项目将适配器创建的过程进行了封装，使代码量大幅降低同时还使适配器的复用粒度降低到布局条目级别。
MultiTypeAdapter的优点如下：
1.易维护
2.复用粒度更细
3.易上手

## 快速使用

### 1.引入依赖

使用Android Studio

在build.gradle中添加如下代码：
```
dependencies {
    compile 'cn.junhua.android.adapter:adapter:1.0.0'
}
```

### 2.基本用法

#### a.首先创建JavaBean和布局文件

Item1.class
```
public class Item1 {
    private int imageId;
    private String title;
}
```
为了方便省略构造方法，getter和setter方法。

layout_item1_type1.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/bg_ll"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:gravity="center"
    android:orientation="vertical">

    <ImageView
        android:id="@+id/icon_iv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <TextView
        android:id="@+id/title_tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</LinearLayout>
```

b.继承SingleTypeViewBinder<Item1>

RecyclerFirstType1ViewBinder.java
```
public class RecyclerFirstType1ViewBinder extends SingleTypeViewBinder<Item1> {

    public RecyclerFirstType1ViewBinder() {
        super(Item1.class, R.layout.layout_item1_type1);
    }

    @Override
    public int onCountView(Item1 bean, int position) {
        return 3;
    }

    @Override
    public void onBindView(ViewHolder holder, Item1 bean, int position) {
        //通用方法
        TextView title_tv = holder.findView(R.id.title_tv);
        title_tv.setText(bean.getTitle());
        //便捷方法
        holder.setText(R.id.title_tv, bean.getTitle() + "  layout_item1_type1")
                .setImageResource(R.id.icon_iv, bean.getImageId())
                .setBackgroundResource(R.id.bg_ll, R.mipmap.image1);
    }
}
```

方法介绍：
>public SingleTypeViewBinder(Class<T> beanClass, int layoutId)

- 参数1：beanClass为泛型T的类类型，如Item1.class,用于区分条目。
- 参数2：layoutId，为条目布局资源id。

>public int onCountView(Item1 bean, int position)

该方法返回用于初始化该布局View缓存的初始值，默认值为6，建议重写减少扩容和内存浪费。

>public void onBindView(ViewHolder holder, Item1 bean, int position)

- 第一个参数：ViewHolder，用来封装复用条目的，通过它的findView（id）可以得到条目中所有到View对象，然后进行数据填充即可。同一个id的View findView只会查找一次，之后从缓存中获取。
ViewHolder的setImageResource()和setBackgroundResource()是用来快速填充文字和图片的方法，它们的返回值是ViewHolder本身。
- 第二个参数：Item1，就是我们数据对象的引用。
- 第三个参数：position，当前条目位置。

c.设置适配器
```
    RecyclerView recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
    //再创建MultiTypeAdapter
    MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter(this);
    //注册ViewBinder
    multiTypeAdapter.registerViewBinder(new RecyclerFirstType1ViewBinder());
```


#### 高级用法：一对多

a.再创建一个布局文件

layout_item1_type2.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/bg_ll"
    android:layout_width="match_parent"
    android:layout_height="60dp"
    android:gravity="center"
    android:orientation="horizontal">

    <ImageView
        android:id="@+id/icon_iv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <TextView
        android:id="@+id/title_tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="1" />

    <TextView
        android:id="@+id/type_tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:text="2" />
</LinearLayout>
```

b.再创建ViewBinder

RecyclerFirstType2ViewBinder.java
```
public class RecyclerFirstType2ViewBinder extends SingleTypeViewBinder<Item1> {

    public RecyclerFirstType2ViewBinder() {
        super(Item1.class, R.layout.layout_item1_type2);
    }

    @Override
    public int onCountView(Item1 bean, int position) {
        return 3;
    }

    @Override
    public void onBindView(ViewHolder holder, Item1 bean, int position) {
        holder.setText(R.id.title_tv, bean.getTitle())
                .setImageResource(R.id.icon_iv, bean.getImageId())
                .setText(R.id.type_tv, "layout_item1_type2");

        LinearLayout linearLayout = holder.findView(R.id.bg_ll);
        linearLayout.setBackgroundResource(R.mipmap.image2);
    }
}
```

c.创建一对多适配器并填充数据

```
    RecyclerView recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

    //再创建MultiTypeAdapter
    MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter(this);
    recycler_view.setLayoutManager(new LinearLayoutManager(this));

    //注册ViewBinder
    multiTypeAdapter.registerViewBinder(new RecyclerSecondViewBinder());
    multiTypeAdapter.registerViewBinder(new RecyclerThreeViewBinder());

    //方式一：一对多条目注册
    List<ViewBinder<Item1>> list = new ArrayList<>();
    list.add(new RecyclerFirstType1ViewBinder());
    list.add(new RecyclerFirstType2ViewBinder());
    MultiTypeViewBinder<Item1> multiTypeViewBinder = new MultiTypeViewBinder<>(Item1.class, list, new OnMatchListener<Item1>() {
        @Override
        public Class<? extends ViewBinder<Item1>> onMatch(Item1 bean, int position) {
            if (bean.getType() == 1) {
                return RecyclerFirstType1ViewBinder.class;
            }
            return RecyclerFirstType2ViewBinder.class;
        }
    });
    multiTypeAdapter.registerViewBinder(multiTypeViewBinder);

    //方式二：一对多条目注册(推荐)
    multiTypeAdapter.registerViewBinder(Item1.class)
            .mapping(new RecyclerFirstType1ViewBinder(), new RecyclerFirstType2ViewBinder())
            .match(new OnMatchListener<Item1>() {
                @Override
                public Class<? extends ViewBinder<Item1>> onMatch(Item1 bean, int position) {
                    if (bean.getType() == 1) {
                        return RecyclerFirstType1ViewBinder.class;
                    }
                    return RecyclerFirstType2ViewBinder.class;
                }
            });

    //设置数据集合
    mDataList = new ArrayList<>();
    for (int i = 0; i < 30; i++) {
        mDataList.add(new Item1(R.mipmap.ic_launcher, Item1.class.getSimpleName() + "条目", R.layout.layout_item1_type1));
        mDataList.add(new Item2(Item2.class.getSimpleName() + "条目", "item"));
        for (int j = 0; j < 10; j++) {
            mDataList.add(new Item3(Item3.class.getSimpleName() + "条目", "item"));
        }
    }

    multiTypeAdapter.setList(mDataList);
    recycler_view.setAdapter(multiTypeAdapter);
```

## 结语

欢迎Pull Requests 和 Issues。