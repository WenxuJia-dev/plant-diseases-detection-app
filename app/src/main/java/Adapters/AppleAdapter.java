package Adapters;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.tencent.yolov5ncnn.DetailActivity;
import com.tencent.yolov5ncnn.R;
import com.tencent.yolov5ncnn.detail_apple;

import java.text.DecimalFormat;
import java.util.ArrayList;
import domain.AppleDomain;


public class AppleAdapter extends RecyclerView.Adapter<AppleAdapter.ViewHolder> {
    ArrayList<AppleDomain> items;
    //Decimal十进制  Format格式
    DecimalFormat formatter;

    public AppleAdapter(ArrayList<AppleDomain> items) {
        this.items = items;
        this.formatter =new DecimalFormat("###,###,###,###");
    }

    @NonNull
    @Override
    // ViewHolder通常出现在适配器里，为的是listview滚动的时候快速设置值，而不必每次都重新创建很多对象，从而提升性能。
    //onCreateViewHolder：这个方法负责创建 ViewHolder 实例。ViewHolder 是 RecyclerView 的一个内部类，
    // 用于缓存视图实例，以提高性能

    public AppleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflatter布局填充器
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder,parent,false);
        return new AppleAdapter.ViewHolder(inflate);
    }
    @Override
    // item是循环变量
    //onBindViewHolder：这个方法负责将数据绑定到 ViewHolder 的视图上。它从 items 列表中获取当前位置的数据
    // ，并设置标题和图片。图片通过 Glide 加载，并应用了 CenterCrop 和 GranularRoundedCorners 转换，以实现居中裁剪和圆角效果。
    public void onBindViewHolder(@NonNull AppleAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.titleTxt.setText(items.get(position).getDiseasetype());

        int drawableResId = holder.itemView.getResources().getIdentifier(items.get(position).getPic(),
                "drawable",holder.itemView.getContext().getPackageName());
        //对于CenterCrop，它是许多图片加载库 Glide提供的一个转换（Transformation），用
        //于将图片裁剪成居中裁剪的样式，以确保图片填满给定的区域而不失真
        //GranularRoundedCorners它提供了对图片进行圆角处理的功能，且支持指定不同角的圆角半径。
        Glide.with(holder.itemView.getContext()).load(drawableResId).
                transform(new CenterCrop(),new GranularRoundedCorners(40,40,40,40))
                .into(holder.pic);
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), detail_apple.class);
                intent.putExtra("object",items.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    //RecyclerView是进阶版的ListView，不仅可以实现ListView的纵向滑动，还可以横向滑动和瀑布流滑动
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleapple);
            pic = itemView.findViewById(R.id.image_apple);
        }
    }
}
