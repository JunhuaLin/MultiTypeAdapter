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

### 2.基本用法步骤

1. 创建JavaBean，如BannerBean.class。
2. 创建布局文件，如binder_banner.xml。
3. 创建ViewBinder：
    - 单布局条目：创建单布局条目继承ViewBinder子类SingleViewBinder。
    - 多布局条目：创建多布局条目使用MultiViewBinder组合多个单布局条目即可。
4. 创建MultiTypeAdapter并注册ViewBinder。

注：具体细节参考**实例展示**或者查看**源代码**。

### 3.实例展示
#### 3.1淘宝首页效果（基本用法）
##### a.首先创建JavaBean和布局文件

GoodsShowBean.class
```java
public class GoodsShowBean {

    private String title;
    private String title1;
    private String photo1;

    private String title2;
    private String photo2;

    private String title3;
    private String subtitle3;
    private String photo3_1;
    private String photo3_2;

    private String title4;
    private String subtitle4;
    private String photo4_1;
    private String photo4_2;

    private String title5;
    private String subtitle5;
    private String photo5_1;
    private String photo5_2 ;

    private String title6;
    private String subtitle6;
    private String photo6_1;
    private String photo6_2;
}
```
为了方便省略构造方法，getter和setter方法。
以及省略的BannerBean.class、LikeBean.class、BigTitleBean.class和MenuBean.class等java bean同GoodsShowBean.class类似。

binder_goods_show.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:card_view="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    card_view:cardCornerRadius="8dp"
    card_view:cardElevation="8dp">


    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:layout_marginTop="10dp"
            android:orientation="horizontal">

            <FrameLayout
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="2">

                <ImageView
                    android:id="@+id/iv_goods_photo1"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:scaleType="centerCrop"
                    android:src="@mipmap/show_photo1" />

                <TextView
                    android:id="@+id/tv_title1"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="bottom"
                    android:layout_margin="10dp"
                    android:text="Air Jordan1 全新配色！"
                    android:textColor="#000000"
                    android:textSize="12sp" />
            </FrameLayout>

            <FrameLayout
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1">

                <ImageView
                    android:id="@+id/iv_goods_photo2"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent" />

                <TextView
                    android:id="@+id/tv_title2"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center_horizontal|bottom"
                    android:layout_marginBottom="10dp"
                    android:paddingBottom="3dp"
                    android:paddingLeft="16dp"
                    android:paddingRight="16dp"
                    android:paddingTop="3dp"
                    android:text="智能办公"
                    android:textSize="12sp" />
            </FrameLayout>

            <TextView
                android:id="@+id/info_tv"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />


        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <LinearLayout
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:orientation="vertical"
                android:padding="10dp">

                <TextView
                    android:id="@+id/tv_goods_title3"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="潮男养成"
                    android:textSize="14sp" />

                <TextView
                    android:id="@+id/tv_goods_subtitle3"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="5dp"
                    android:text="右品有范有风尚"
                    android:textSize="12sp" />

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal">

                    <ImageView
                        android:id="@+id/iv_goods_photo3_1"
                        android:layout_width="80dp"
                        android:layout_height="80dp" />

                    <ImageView
                        android:id="@+id/iv_goods_photo3_2"
                        android:layout_width="80dp"
                        android:layout_height="80dp"
                        android:layout_marginLeft="10dp" />

                </LinearLayout>

            </LinearLayout>

            <LinearLayout
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:orientation="vertical"
                android:padding="10dp">

                <TextView
                    android:id="@+id/tv_goods_title4"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="潮男养成"
                    android:textSize="14sp" />

                <TextView
                    android:id="@+id/tv_goods_subtitle4"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="5dp"
                    android:text="右品有范有风尚"
                    android:textSize="12sp" />

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal">

                    <ImageView
                        android:id="@+id/iv_goods_photo4_1"
                        android:layout_width="80dp"
                        android:layout_height="80dp" />

                    <ImageView
                        android:id="@+id/iv_goods_photo4_2"
                        android:layout_width="80dp"
                        android:layout_height="80dp"
                        android:layout_marginLeft="10dp" />

                </LinearLayout>

            </LinearLayout>
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <LinearLayout
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:orientation="vertical"
                android:padding="10dp">

                <TextView
                    android:id="@+id/tv_goods_title5"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="潮男养成"
                    android:textSize="14sp" />

                <TextView
                    android:id="@+id/tv_goods_subtitle5"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="5dp"
                    android:text="右品有范有风尚"
                    android:textSize="12sp" />

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal">

                    <ImageView
                        android:id="@+id/iv_goods_photo5_1"
                        android:layout_width="80dp"
                        android:layout_height="80dp" />

                    <ImageView
                        android:id="@+id/iv_goods_photo5_2"
                        android:layout_width="80dp"
                        android:layout_height="80dp"
                        android:layout_marginLeft="10dp" />

                </LinearLayout>

            </LinearLayout>

            <LinearLayout
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:orientation="vertical"
                android:padding="10dp">

                <TextView
                    android:id="@+id/tv_goods_title6"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="潮男养成"
                    android:textSize="14sp" />

                <TextView
                    android:id="@+id/tv_goods_subtitle6"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="5dp"
                    android:text="右品有范有风尚"
                    android:textSize="12sp" />

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal">

                    <ImageView
                        android:id="@+id/iv_goods_photo6_1"
                        android:layout_width="80dp"
                        android:layout_height="80dp" />

                    <ImageView
                        android:id="@+id/iv_goods_photo6_2"
                        android:layout_width="80dp"
                        android:layout_height="80dp"
                        android:layout_marginLeft="10dp" />

                </LinearLayout>

            </LinearLayout>
        </LinearLayout>


    </LinearLayout>

    <TextView
        android:id="@+id/tv_goods_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@drawable/goods_title_bg"
        android:paddingBottom="3dp"
        android:paddingLeft="20dp"
        android:paddingRight="20dp"
        android:paddingTop="3dp"
        android:text="买遍全球"
        android:textColor="#000000"
        android:textSize="12sp" />

</FrameLayout>
```

##### b.创建ViewBinder

单类型条目直接继承SingleViewBinder。

GoodsShowViewHinder.java
```java
public class GoodsShowViewHinder extends SingleViewBinder<GoodsShowBean> {

    public GoodsShowViewHinder() {
        super(GoodsShowBean.class, R.layout.binder_goods_show);
    }

    @Override
    public int onCountView(GoodsShowBean bean, int position) {
        return 21;
    }

    @Override
    public void onBindView(ViewHolder holder, GoodsShowBean bean, int position) {
        //省略其他类似代码
        //便捷用法
        holder.setText(R.id.tv_goods_title, bean.getTitle())
                .setText(R.id.tv_goods_subtitle6, bean.getSubtitle6());
        //基本用法
        ImageView iv_goods_photo1 = holder.findView(R.id.iv_goods_photo1);
        Glide.with(iv_goods_photo1.getContext()).load(url).into(iv_goods_photo1); 
    }
}
```

方法介绍：
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

##### c.设置适配器
TaobaoActivity.class
```java
   public class TaobaoActivity extends AppCompatActivity {
       private RecyclerView recycler_view;
       private MultiTypeAdapter multiTypeAdapter;
       private List<Object> dataList;
   
       @Override
       protected void onCreate(@Nullable Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_recycler_view);
           dataList = new ArrayList<>();
           dataList.add(new BannerBean());
           dataList.add(new MenuBean());
   
           for (int i = 0; i < 2; i++) {
               dataList.add(new GoodsShowBean());
           }
   
           dataList.add(new BigTitleBean("猜你喜欢"));
           for (int i = 0; i < 20; i++) {
               dataList.add(new LikeBean());
           }
   
           recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
   
           //设置布局管理器
           GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
           gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
               @Override
               public int getSpanSize(int position) {
                   Object object = dataList.get(position);
                   if (object instanceof SpanSize) {
                       return ((SpanSize) object).getSpanSize();
                   }
                   return 2;
               }
           });
           recycler_view.setLayoutManager(gridLayoutManager);
   
           //初始化MultiTypeAdapter
           multiTypeAdapter = new MultiTypeAdapter(this);
           //注册ViewBinder
           multiTypeAdapter.register(new BannerViewHinder());
           multiTypeAdapter.register(new MenuViewHinder());
           multiTypeAdapter.register(new LikeViewHinder());
           multiTypeAdapter.register(new GoodsShowViewHinder());
           multiTypeAdapter.register(new BigTitleViewHinder());
   
           multiTypeAdapter.setList(dataList);
           recycler_view.setAdapter(multiTypeAdapter);
       }
   }
```
注：SpanSize接口用于返回条目在网格布局中占得空间。

##### d.效果图gif
![淘宝首页效果](https://github.com/JunhuaLin/MultiTypeAdapter/blob/master/photo/淘宝首页.gif)


#### 3.2朋友圈多图效果（高级用法：一对多）

##### a.创建JavaBean和多个不同的布局

FriendBean.class
```java
public class FriendBean {
    private String name;
    private String content;
    private List<Integer> photos;
    private int avatar;
}
```
布局文件：
binder_friend_photo1.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="12dp">

    <ImageView
        android:id="@+id/iv_avatar"
        android:layout_width="70dp"
        android:layout_height="70dp" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="10dp">

        <TextView
            android:id="@+id/tv_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:ellipsize="end"
            android:maxLines="1"
            android:text="大狗子"
            android:textColor="#5A636A"
            android:textSize="14sp" />

        <TextView
            android:id="@+id/tv_content"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="6dp"
            android:text="堆雪人了 好开森！！！"
            android:textColor="#0E0E0E"
            android:textSize="12sp" />

        <cn.junhua.android.commonadapter.view.RectImageView
            android:id="@+id/iv_photo1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="6dp"
            android:maxHeight="200dp"
            android:maxWidth="200dp"
            android:scaleType="centerCrop" />

    </LinearLayout>

</LinearLayout>
```

binder_friend_photo3.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="12dp">

    <ImageView
        android:id="@+id/iv_avatar"
        android:layout_width="70dp"
        android:layout_height="70dp" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="10dp">

        <TextView
            android:id="@+id/tv_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:ellipsize="end"
            android:maxLines="1"
            android:text="大狗子"
            android:textColor="#5A636A"
            android:textSize="14sp" />

        <TextView
            android:id="@+id/tv_content"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="6dp"
            android:text="堆雪人了 好开森！！！"
            android:textColor="#0E0E0E"
            android:textSize="12sp" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="horizontal">

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo1"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop"
                android:visibility="visible" />

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo2"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop" />

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo3"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="horizontal">

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo4"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop"
                android:visibility="visible" />

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo5"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop" />

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo6"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="horizontal">

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo7"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop"
                android:visibility="visible" />

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo8"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop" />

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo9"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop" />
        </LinearLayout>


    </LinearLayout>

</LinearLayout>
```

binder_friend_photo4.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="12dp">

    <ImageView
        android:id="@+id/iv_avatar"
        android:layout_width="70dp"
        android:layout_height="70dp" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="10dp">

        <TextView
            android:id="@+id/tv_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:ellipsize="end"
            android:maxLines="1"
            android:text="大狗子"
            android:textColor="#5A636A"
            android:textSize="14sp" />

        <TextView
            android:id="@+id/tv_content"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="6dp"
            android:text="堆雪人了 好开森！！！"
            android:textColor="#0E0E0E"
            android:textSize="12sp" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="horizontal">

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo1"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop"
                android:visibility="visible" />

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo2"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="horizontal">

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo3"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop" />

            <cn.junhua.android.commonadapter.view.RectImageView
                android:id="@+id/iv_photo4"
                android:layout_width="0dp"
                android:layout_height="200dp"
                android:layout_margin="2dp"
                android:layout_marginTop="6dp"
                android:layout_weight="1"
                android:scaleType="centerCrop" />
        </LinearLayout>

    </LinearLayout>

</LinearLayout>
```
##### b.创建ViewBinder

先创建单类型条目继承SingleViewBinder。

FriendPhotoViewBinder.java基类提取公共代码
```java
public abstract class FriendPhotoViewBinder extends SingleViewBinder<FriendBean> {

    public FriendPhotoViewBinder(int layoutId) {
        super(FriendBean.class, layoutId);
    }

    @Override
    public int onCountView(FriendBean bean, int position) {
        return 3 + bean.getPhotos().size();
    }

    @Override
    public void onBindView(ViewHolder holder, FriendBean bean, int position) {
        holder.setText(R.id.tv_name, bean.getName())
                .setText(R.id.tv_content, bean.getContent());

        setImageRes(holder, R.id.iv_avatar, bean.getAvatar());

        onBindImage(holder, bean, position);
    }

    /**
     * 仅处理晒图图片的绑定
     */
    public abstract void onBindImage(ViewHolder holder, FriendBean bean, int position);

    public void setImageRes(ViewHolder holder, @IdRes int id,@DrawableRes int mipmap) {
        ImageView imageView = holder.findView(id);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(mipmap);
    }
}
```

FriendPhoto3ViewBinder.java
```java
/**
 * 朋友圈2-3或5-9张图片
 * Created by linjunhua on 2018/1/6.
 */
public class FriendPhoto3ViewBinder extends FriendPhotoViewBinder {

    private int[] imageViewIds;

    public FriendPhoto3ViewBinder() {
        super(R.layout.binder_friend_photo3);
        imageViewIds = new int[]{
                R.id.iv_photo1,
                R.id.iv_photo2,
                R.id.iv_photo3,

                R.id.iv_photo4,
                R.id.iv_photo5,
                R.id.iv_photo6,

                R.id.iv_photo7,
                R.id.iv_photo8,
                R.id.iv_photo9,
        };
    }

    @Override
    public int onCountView(FriendBean bean, int position) {
        return 3 + 9;
    }

    @Override
    public void onBindImage(ViewHolder holder, FriendBean bean, int position) {
        List<Integer> urls = bean.getPhotos();
        int size = urls.size();
        for (int i = 0; i < imageViewIds.length; i++) {
            if (i < size) {
                setImageRes(holder, imageViewIds[i], urls.get(i));
            } else {
                holder.findView(imageViewIds[i]).setVisibility(((size - 1) / 3 == i / 3) ? View.INVISIBLE : View.GONE);
            }
        }
    }

}
```
FriendPhoto4ViewBinder.class
```java
/**
 * 朋友圈4张图片
 * Created by linjunhua on 2018/1/6.
 */
public class FriendPhoto4ViewBinder extends FriendPhotoViewBinder {

    private int[] imageViewIds;

    public FriendPhoto4ViewBinder() {
        super(R.layout.binder_friend_photo4);
        imageViewIds = new int[]{
                R.id.iv_photo1,
                R.id.iv_photo2,
                R.id.iv_photo3,
                R.id.iv_photo4
        };
    }

    @Override
    public void onBindImage(ViewHolder holder, FriendBean bean, int position) {
        for (int i = 0; i < imageViewIds.length; i++) {
            setImageRes(holder, imageViewIds[i], bean.getPhotos().get(i));
        }
    }
}
```
省略FriendPhoto1ViewBinder.class。代码效果同FriendPhoto3ViewBinder.class。

##### c.设置适配器

```java
    
public class One2ManyActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private MultiTypeAdapter mMultiTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        //再创建MultiTypeAdapter
        mMultiTypeAdapter = new MultiTypeAdapter(this);
        //注册ViewBinder
        //方式一：一对多条目注册(不推荐)
        List<ViewBinder<FriendBean>> list = new ArrayList<>();
        list.add(new FriendPhoto1ViewBinder());
        list.add(new FriendPhoto3ViewBinder());
        list.add(new FriendPhoto4ViewBinder());
        MultiViewBinder<FriendBean> multiViewBinder = new MultiViewBinder<>(FriendBean.class, list, new TypeMatcher<FriendBean>() {
            @Override
            public Class<? extends ViewBinder<FriendBean>> onMatch(FriendBean bean, int position) {
                int size = bean.getPhotos().size();
                if (size == 0 || size == 1) return FriendPhoto1ViewBinder.class;
                if (size == 4) return FriendPhoto4ViewBinder.class;
                return FriendPhoto3ViewBinder.class;
            }
        });
        mMultiTypeAdapter.register(multiViewBinder);
        mMultiTypeAdapter.unregister(multiViewBinder);

        //方式二：一对多条目注册(推荐)
        mMultiTypeAdapter.register(FriendBean.class)
                .mapping(new FriendPhoto1ViewBinder(),
                        new FriendPhoto3ViewBinder(),
                        new FriendPhoto4ViewBinder())
                .match(new TypeMatcher<FriendBean>() {
                    @Override
                    public Class<? extends ViewBinder<FriendBean>> onMatch(FriendBean bean, int position) {
                        int size = bean.getPhotos().size();
                        if (size == 0 || size == 1) return FriendPhoto1ViewBinder.class;
                        if (size == 4) return FriendPhoto4ViewBinder.class;
                        return FriendPhoto3ViewBinder.class;
                    }
                });

        mMultiTypeAdapter.setList(FriendBean.getRandomFriend());        //  设置数据
        
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recycler_view.setAdapter(mMultiTypeAdapter);
    }
}
```

##### d.效果图gif
![朋友圈效果](https://github.com/JunhuaLin/MultiTypeAdapter/blob/master/photo/朋友圈.gif)

## 结语

欢迎PR 和 Issues。