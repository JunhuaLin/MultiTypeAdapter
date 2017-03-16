# CommonAdapter
通用适配器，适用于ListView和RecyclerView单类型或多类型显示。ListView和RecyclerView在我们开发的过程中使用频率极高，而在创建适配器的时候每次都要写很多相同的代码，每次都这样操作不仅效率低而且很不爽。
所有将适配器创建过程中的通用代码提取出来，然后将与业务相关部分留到子类中去实现。该通用适配器已经实现了条目复用，多条目加载等功能。

## 快速使用

### 1.引入依赖

使用Android Studio

在build.gradle中添加如下代码：
```
dependencies {
    compile 'cn.junhua.android.adapter:adapter:1.0.0'
}
```

### 2.adapter分类

- SingleBaseAdapter：适用于ListView或GridView单类型数据条目填充。
- CommonBaseAdapter：适用于ListView或GridView多类型数据条目填充。
- SingleRecyclerViewAdapter：适用于RecyclerView单类型数据条目填充。
- CommonRecyclerViewAdapter：适用于RecyclerView多类型数据条目填充。

注：单类型多类型指代的是Item的布局文件是一种还是多种。

### 3.在代码中使用

SingleBaseAdapter和SingleRecyclerViewAdapter的使用方法基本一样，CommonBaseAdapter和CommonRecyclerViewAdapter基本使用一样。下面就按单类型和多类型分别介绍。

#### 单类型数据适配器
SingleBaseAdapter和SingleRecyclerViewAdapter采用泛型来指定填充数据类型。所以

a.首先创建JavaBean

```java
public class Item1 {
    private int imageId;
    private String title;
}
```

为了方便省略构造方法，getter和setter方法。


b.继承SingleBaseAdapter或SingleRecyclerViewAdapter

```java
public class ListViewSingleAdapter extends SingleBaseAdapter<Item1> {

    private int[] images = new int[]{
            R.mipmap.image1,
            R.mipmap.image2,
            R.mipmap.image3
    };

    public ListViewSingleAdapter(Context mContext, int layoutId) {
        super(mContext, layoutId);
    }

    @Override
    public void onBindView(ViewHolder holder, Item1 bean, int position) {
        holder.setText(R.id.title_tv, bean.getTitle())
                .setImage(R.id.icon_iv, bean.getImageId());

        LinearLayout linearLayout = holder.getView(R.id.bg_ll);
        linearLayout.setBackgroundResource(images[position % images.length]);
    }

}
```


看下onBindView方法：
>public void onBindView(ViewHolder holder, Item1 bean, int position)

- 第一个参数：ViewHolder，用来封装复用条目的，通过它的getView（id）可以得到条目中所有到View对象，然后进行数据填充即可。
ViewHolder的setText()和setImage()是用来快速填充文字和图片的方法，它们的返回值是ViewHolder本身。
- 第二个参数：Item1，就是我们数据对象的引用。
- 第三个参数：position，当前条目位置。

c.设置适配器
```java
        mListView = (ListView) findViewById(R.id.list_view);

        mListViewSingleAdapter = new ListViewSingleAdapter(this, R.layout.layout_item1);

        mDataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDataList.add(new Item1(R.mipmap.ic_launcher, "item " + i));
        }
        mListViewSingleAdapter.setList(mDataList);
        mListView.setAdapter(mListViewSingleAdapter);
```

d.运行效果图

![single adapter](https://github.com/JunhuaLin/CommonAdapter/blob/master/photo/singleadapter.png)


#### 多类型数据适配器

CommonBaseAdapter和CommonRecyclerViewAdapter需要通过JavaBean的类类型即Class来区分其所要对应的布局文件。

a.创建JavaBean

```java
public class Item1 {
    private int imageId;
    private String title;
}
```

再增加一个Item2
```java
public class Item2 {
    private String title;
    private String info;
}
```

b.创建ViewBinder

要继承CommonBaseAdapter或CommonRecyclerViewAdapter对应的ViewBinder。

```java
public class ListViewFirstViewBinder extends CommonBaseAdapter.ViewBinder {

    private int[] images = new int[]{
            R.mipmap.image1,
            R.mipmap.image2,
            R.mipmap.image3
    };

    public ListViewFirstViewBinder(Class<?> beanClass, int layoutId) {
        super(beanClass, layoutId);
    }

    @Override
    public void bindView(CommonBaseAdapter.ViewHolder holder, Object bean, int position) {

        Item1 item = (Item1) bean;

        holder.setText(R.id.title_tv, item.getTitle())
                .setImage(R.id.icon_iv, item.getImageId());

        LinearLayout linearLayout = holder.getView(R.id.bg_ll);
        linearLayout.setBackgroundResource(images[position % images.length]);
    }
}
```
ListViewFirstViewBinder用来填充Item1对应的布局文件。

```java
public class ListViewSecondViewBinder extends CommonBaseAdapter.ViewBinder {
    public ListViewSecondViewBinder(Class<?> beanClass, int layoutId) {
        super(beanClass, layoutId);
    }

    @Override
    public void bindView(CommonBaseAdapter.ViewHolder holder, Object bean, int position) {
        Item2 item = (Item2) bean;
        holder.setText(R.id.title_tv, item.getTitle())
                .setText(R.id.info_tv, item.getInfo());
    }
}
```
ListViewSecondViewBinder用来填充Item2对应的布局文件。

>注意：这里bindView传进来的bean需要强转成你要处理的对象。

其中bindView和单条目适配器中的onBindView一样。


c.创建适配器并填充数据

```java
        list_view = (ListView) findViewById(R.id.list_view);

        //创建ViewBinder
        List<CommonBaseAdapter.ViewBinder> viewBinders = new ArrayList<>();
        viewBinders.add(new ListViewFirstViewBinder(Item1.class, R.layout.layout_item1));
        viewBinders.add(new ListViewSecondViewBinder(Item2.class, R.layout.layout_item2));

        //在创建CommonBaseAdapter
        mCommonBaseAdapter = new CommonBaseAdapter(this, viewBinders);

        //设置数据集合
        mDataList = new ArrayList<Object>();
        for (int i = 0; i < 10; i++) {
            mDataList.add(new Item1(R.mipmap.ic_launcher, "第一类条目的item" + i));
            mDataList.add(new Item2("我是第二类条目", "item" + i));
        }
        mCommonBaseAdapter.setList(mDataList);

        //为ListView设置Adapter
        list_view.setAdapter(mCommonBaseAdapter);
```

>注意：此处的数据集合泛型必须为Object，因为要同时处理多种JavaBean。

d.运行效果图

![common adapter](https://github.com/JunhuaLin/CommonAdapter/blob/master/photo/commonadapter.png)


## 结语

欢迎Pull Requests 和 Issues。