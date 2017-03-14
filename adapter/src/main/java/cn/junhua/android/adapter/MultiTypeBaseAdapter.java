package cn.junhua.android.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 功能：Adapter封装简化使用步骤，适用于任意类别条目的列表数据填充<br/>
 * created by 林军华 on 2016/5/18 0026.<br/>
 */
public final class MultiTypeBaseAdapter extends BaseAdapter {
	// Log TAG
	private static final String TAG = MultiTypeBaseAdapter.class.getSimpleName();
	// data res
	private List<Object> mList;
	private LayoutInflater mLayoutInflater;
	// save ViewBinder
	private ViewBinderListManager mViewBinderListManager;

	private MultiTypeBaseAdapter(Context mContext) {
		this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mList = new ArrayList<Object>();
		mViewBinderListManager = new ViewBinderListManager();
	}

	public MultiTypeBaseAdapter(Context context, List<ViewBinder> viewBinders) {
		this(context, viewBinders, null);
	}

	public MultiTypeBaseAdapter(Context context, List<ViewBinder> viewBinders, List<Object> list) {
		this(context);
		mViewBinderListManager.addAll(viewBinders);
		setList(list);
	}


	/**
	 * 得到内部数据集合
	 *
	 * @return
	 */
	public List<Object> getList() {
		return mList;
	}

	/**
	 * 重置内部数据集合内容
	 *
	 * @param list
	 */
	public void setList(List<Object> list) {
		mList.clear();
		mList.addAll(list);
	}

	/**
	 * 向内部数据集合追加数据
	 *
	 * @param list
	 */
	public void appendList(List<Object> list) {
		mList.addAll(list);
	}

	/**
	 * 该方法在getItemViewType后调用getView<br/>
	 *
	 * @param position
	 * @return 该返回值必须小于getViewTypeCount的值，并且不同类型返回值要从0依次递增1(因为要做数组下标...)<br/>
	 *         采用ArrayList<View>[]缓存多类型条目的复用View<br/>
	 *         <p>
	 *         如下为AbsListView中使用getItemViewType(int position)的场景：
	 *         <p>
	 *         View getScrapView(int position) { final int whichScrap =
	 *         mAdapter.getItemViewType(position); if (whichScrap < 0) { return
	 *         null; } if (mViewTypeCount == 1) { return
	 *         retrieveFromScrap(mCurrentScrap, position); } else if (whichScrap
	 *         < mScrapViews.length) { return
	 *         retrieveFromScrap(mScrapViews[whichScrap], position); } return
	 *         null; }
	 *         <p>
	 *         从此代码可以看出：getItemViewType的返回值将作为数组下标找到对应的复用数组<br/>
	 *         报ArrayIndexOutOfBoundsException的根本原因<br/>
	 *         结论：<br/>
	 *         该返回值必须小于getViewTypeCount的值，并且不同类型返回值要从0依次递增1(因为要做数组下标...)
	 */
	@Override
	public int getItemViewType(int position) {
		Object obj = getItem(position);
		int index = mViewBinderListManager.indexOf(obj);
		// 你必须创建一个ViewBinder实例与JavaBean对应。
		if (index < 0) {
			throw new RuntimeException("You must create a " + ViewBinder.class.getCanonicalName() + " instance for "
					+ obj.getClass().getCanonicalName() + " .");
		}
		// 布局文件的Type是自动生成的
		return index;
	}

	@Override
	public int getViewTypeCount() {
		int count = mViewBinderListManager.size();
		// 没有具体的ViewBinder不能正常运行
		if (count <= 0) {
			throw new RuntimeException(
					"You need to create at least one concrete " + ViewBinder.class.getCanonicalName());
		}
		Log.d(TAG, "getViewTypeCount:" + count);
		// 有多少种填充的数据Bean就有至少有多少种布局与之对应
		return count;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 测试布局创建的次数
	int count = 0;

	@SuppressLint("ViewTag")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		Object bean = getItem(position);// 要填充的数据
		ViewBinder viewBinder = mViewBinderListManager.get(bean.getClass());
		// 你必须创建一个ViewBinder实例与JavaBean对应。
		if (viewBinder == null) {
			throw new RuntimeException("You must create a " + ViewBinder.class.getCanonicalName() + " instance for "
					+ bean.getClass().getCanonicalName() + " .");
		}
		int layoutId = viewBinder.getLayoutId();

		// Vie复用
		if (convertView == null) {

			Log.d(TAG, "inflate times:" + String.valueOf(++count) + "  id:" + layoutId);

			convertView = mLayoutInflater.inflate(layoutId, parent, false);
			// 此处holder用来辅助findView操作
			holder = new ViewHolder(convertView);
			convertView.setTag(layoutId, holder);
		} else {
			holder = (ViewHolder) convertView.getTag(layoutId);
		}

		// 此处抽取出来让不同的布局实现不同数据填充逻辑
		viewBinder.bindView(holder, bean, position);

		return convertView;
	}

	/**
	 * 设置条目中控件显示信息的封装类
	 */
	public static abstract class ViewBinder {
		// 布局文件
		private int mLayoutId;
		// 区分不同类别的条目
		private int mTypeId;

		/**
		 * 填充数据类类型此处仅仅作为不同布局的标识,用于根据数据源区分布局对象<br/>
		 * 因为：Bean->Layout->View<br/>
		 */
		private Class<?> mBeanClass;

		public ViewBinder(Class<?> beanClass, int layoutId) {
			this.mLayoutId = layoutId;
			this.mBeanClass = beanClass;
		}

		public int getLayoutId() {
			return mLayoutId;
		}

		public Class<?> getBeanClass() {
			return mBeanClass;
		}

		public int getViewType() {
			return mTypeId;
		}

		private void setTypeId(int mTypeId) {
			this.mTypeId = mTypeId;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			ViewBinder that = (ViewBinder) o;

			if (mLayoutId != that.mLayoutId)
				return false;
			return !(mBeanClass != null ? !mBeanClass.getCanonicalName().equals(that.mBeanClass.getCanonicalName())
					: that.mBeanClass != null);

		}

		@Override
		public int hashCode() {
			int result = mLayoutId;
			result = 31 * result + (mBeanClass != null ? mBeanClass.getCanonicalName().hashCode() : 0);
			return result;
		}

		@Override
		public String toString() {
			return "ViewBinder{" + "mLayoutId=" + mLayoutId + ", mTypeId=" + mTypeId + ", mBeanClass="
					+ mBeanClass.getCanonicalName() + '}';
		}

		/**
		 * 设置条目中控件显示的信息
		 *
		 * @param holder
		 *            封装adapter中item的复用操作代码的对象
		 * @param bean
		 *            数据Bean需要强制类型转化
		 * @param position
		 *            当前条目位置
		 */
		public abstract void bindView(ViewHolder holder, Object bean, int position);
	}

	/**
	 * 通用ViewHolder MultiTypeBaseAdapter的内部类，封装adapter中item的复用操作代码<br/>
	 * 使用方法：<br/>
	 * 通过ViewHolder对象的方法getView(...)传入View的id得到对应控件<br/>
	 */
	public final static class ViewHolder {
		private SparseArray<View> mViews;
		private View convertView;

		/**
		 * 传入复用对象
		 *
		 * @param root
		 */
		public ViewHolder(View root) {
			this.mViews = new SparseArray<View>();
			this.convertView = root;
		}

		/**
		 * 得到对应Id的布局控件
		 *
		 * @param viewId
		 *            控件Id
		 * @return 控件T extends View
		 */
		@SuppressWarnings("unchecked")
		public <T extends View> T getView(int viewId) {

			View view = mViews.get(viewId);
			if (view == null) {
				view = convertView.findViewById(viewId);
				mViews.put(viewId, view);
			}
			return (T) view;
		}

		/**
		 * 便捷设置TextView的内容
		 *
		 * @param viewId
		 *            TextView的id
		 * @param text
		 *            TextView的内容
		 * @return ViewHolder自己实现链式编程
		 */
		public ViewHolder setText(int viewId, String text) {
			TextView textView = getView(viewId);
			textView.setText(text);
			return this;
		}

	}

	/**
	 * ViewBinder的列表管理器
	 * 
	 * @author lin
	 *
	 *         2016年6月6日上午11:26:57
	 */
	private static class ViewBinderListManager extends ArrayList<ViewBinder> {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7149248838995627451L;

		/**
		 * 添加ViewBinder并自动生成ViewBinder的TypeId
		 */
		@Override
		public boolean add(ViewBinder viewBinder) {
			if (viewBinder == null) {
				return false;
			}
			ViewBinder temp = get(viewBinder.getBeanClass());

			if (temp == null) {// 首次添加
				viewBinder.setTypeId(size());
				super.add(viewBinder);
			} else {// 替换
				// 替换原来的TypeId
				viewBinder.setTypeId(temp.getViewType());
				super.remove(temp);
				super.add(viewBinder);
			}
			return true;
		}

		@Override
		public boolean addAll(Collection<? extends ViewBinder> collection) {
			boolean b = false;
			for (ViewBinder vb : collection) {
				b |= add(vb);
			}
			return b;
		}

		/**
		 * 通过JavaBean的Class得到对应的ViewBinder
		 * 
		 * @param clazz
		 * @return
		 */
		public ViewBinder get(Class<?> clazz) {
			if (clazz == null) {
				return null;
			}

			String modelCanonicalName = clazz.getCanonicalName();
			for (ViewBinder viewBinder : this) {
				if (viewBinder.getBeanClass().getCanonicalName().equals(modelCanonicalName)) {
					return viewBinder;
				}
			}
			return null;
		}

		/**
		 * 通过数据源JavaBean的实例得到ViewBinder在复用列表中的索引位置
		 */
		@Override
		public int indexOf(Object object) {
			ViewBinder viewBinder = get(object.getClass());
			if (viewBinder == null)
				return -1;
			return viewBinder.getViewType();
		}

	}
}
