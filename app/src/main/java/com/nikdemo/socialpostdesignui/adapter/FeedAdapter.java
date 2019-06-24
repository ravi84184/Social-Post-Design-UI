package com.nikdemo.socialpostdesignui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nikdemo.socialpostdesignui.R;
import com.nikdemo.socialpostdesignui.model.FeedModel;
import com.nikdemo.socialpostdesignui.utils.customui.CustomEditText;
import com.nikdemo.socialpostdesignui.utils.customui.CustomTextView;

import java.util.ArrayList;


public class FeedAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<FeedModel> list;
    private setOnFeedClickListener listener;
    public interface setOnFeedClickListener{
        void onFeedClick(int index,FeedModel model);
    }

    public FeedAdapter(Context context, ArrayList<FeedModel> list,setOnFeedClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_fees_view, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        FeedModel model = list.get(position);
        holder.row_user_name.setText(model.getName());
        holder.row_time.setText(model.getTime());
        holder.row_description.setText(model.getDescription());
        holder.row_txt_like.setText(String.valueOf(model.getLike()));
        holder.row_txt_view.setText(String.valueOf(model.getView()));
        holder.row_txt_comment.setText(String.valueOf(model.getComment()));

        holder.row_usr_img.setImageDrawable(ContextCompat.getDrawable(context,model.getProfile()));
        holder.row_post.setImageDrawable(ContextCompat.getDrawable(context,model.getPost()));

        holder.row_usr_img.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.e("TouchTest", "Touch down");
                listener.onFeedClick(0,model);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                Log.e("TouchTest", "Touch up");
                listener.onFeedClick(1,model);
            }
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CustomTextView row_user_name,row_time,row_description,row_txt_like,row_txt_view,row_txt_comment;
        ImageView row_usr_img,row_post;

        public MyViewHolder(View itemView) {
            super(itemView);

            row_usr_img = itemView.findViewById(R.id.row_usr_img);
            row_user_name = itemView.findViewById(R.id.row_user_name);
            row_time = itemView.findViewById(R.id.row_time);
            row_post = itemView.findViewById(R.id.row_post);
            row_description = itemView.findViewById(R.id.row_description);
            row_txt_like = itemView.findViewById(R.id.row_txt_like);
            row_txt_view = itemView.findViewById(R.id.row_txt_view);
            row_txt_comment = itemView.findViewById(R.id.row_txt_comment);

        }
    }
}
