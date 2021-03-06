package indi.github.icear.simpleclass.viewmodule.classlist;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import indi.github.icear.simpleclass.module.academicdata.entity.IClass;

/**
 * {@link RecyclerView.Adapter} that can display a {@link IClass} and makes a call to the
 * ListActionCallBack
 */
class ClassListRecyclerViewAdapter extends RecyclerView.Adapter<ClassListRecyclerViewAdapter.ViewHolder> {
    private final static String TAG = ClassListRecyclerViewAdapter.class.getSimpleName();

    private final List<IClass> mItemList;
    private final List<Integer> mColorList;
    private final ListActionCallBack mListener;

    ClassListRecyclerViewAdapter(List<IClass> items, List<Integer> colorList, ListActionCallBack listener) {
        //在这里创建Adapter并传入要展示的item数据，同时设定传到上层的单击监听事件
        mItemList = items;
        mListener = listener;
        mColorList = colorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //在这里创建view并放入viewHolder
        View view = LayoutInflater.from(parent.getContext())
                .inflate(indi.github.icear.simpleclass.R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //在这里给View填充(覆盖)内容
        holder.mItem = mItemList.get(position);
        holder.mTitle.setText(mItemList.get(position).getName());
        holder.mSubtitle.setText(mItemList.get(position).getTeachers());
        holder.mIcon.setColorFilter(mColorList.get(position));//从ColorList中取出对应的颜色并填充
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListItemClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

//    /**
//     * 向数据表添加指定item
//     * @param item item
//     */
//    public void addItem(IClass item){
//        if(item != null){
//            mItemList.add(item);
//            mColorList.add(RandomColorUtil.getRandomColor());//同时为新的item添加颜色
//            notifyDataSetChanged();
//        }else{
//            throw new NullPointerException("the item is null");
//        }
//    }
//
//    /**
//     * 从数据表删除指定item，如果item不存在则操作无效
//     * @param position position
//     */
//    void delItemData(int position) {
//        if(0 < position && position < mItemList.size()){
//            mItemList.remove(position);
//            mColorList.remove(position);//同时移除颜色列表的对应值
//            notifyDataSetChanged();
//        }else{
//            throw new IndexOutOfBoundsException("position: " + position);
//        }
//    }
//
//    /**
//     * 交换数据表中指定两个item的位置，如果item不存在则操作无效
//     * @param position1 position
//     * @param position2 position
//     */
//    public void swapItemData(int position1, int position2){
//        if(0 < position1 && 0 < position2
//                && position1 < mItemList.size() && position2 <mItemList.size()){
//            Collections.swap(mItemList,position1,position2);
//            Collections.swap(mColorList,position1,position2);
//            notifyDataSetChanged();
//        }else{
//            throw new IndexOutOfBoundsException("position1: " + position1 + " position2:" + position2);
//        }
//    }

    interface ListActionCallBack {
        void onListItemClick(IClass item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mTitle;
        final TextView mSubtitle;
        final ImageView mIcon;
        IClass mItem;

        ViewHolder(View view) {
            //在这里预先hold住View中的元素并保存到holder中
            super(view);
            mView = view;
            mIcon = (ImageView) view.findViewById(indi.github.icear.simpleclass.R.id.imageView_class);
            mTitle = (TextView) view.findViewById(indi.github.icear.simpleclass.R.id.textView_title);
            mSubtitle = (TextView) view.findViewById(indi.github.icear.simpleclass.R.id.textView_subTitle);
        }
//        @Override
//        public String toString() {
//            return super.toString() + " '" + mSubtitle.getText() + "'";
//        }
    }
}
