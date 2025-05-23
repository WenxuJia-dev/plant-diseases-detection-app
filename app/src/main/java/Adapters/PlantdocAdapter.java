package Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.tencent.yolov5ncnn.R;
import com.tencent.yolov5ncnn.detail_apple;
import com.tencent.yolov5ncnn.detail_plant;

import java.text.DecimalFormat;
import java.util.ArrayList;

import domain.AppleDomain;

public class PlantdocAdapter extends RecyclerView.Adapter<PlantdocAdapter.ViewHolder>{
    ArrayList<AppleDomain> items;
    //Decimal十进制  Format格式
    DecimalFormat formatter;

    public PlantdocAdapter(ArrayList<AppleDomain> items) {
        this.items = items;
        this.formatter =new DecimalFormat("###,###,###,###");
    }

    @NonNull
    @Override
    public PlantdocAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflatter布局填充器
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_plant,parent,false);
        return new PlantdocAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantdocAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
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
                Intent intent = new Intent(holder.itemView.getContext(), detail_plant.class);
                intent.putExtra("object",items.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            titleTxt=itemView.findViewById(R.id.title_plant);
            pic = itemView.findViewById(R.id.image_plant);
        }
    }
}
