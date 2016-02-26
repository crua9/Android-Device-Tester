package com.techreviewsandhelp.devicetester;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private String[] featuresNames;
    private Context mContext;
    private RecyclerViewClickListener itemListener;
    Features features;

    public RecyclerAdapter(Context context, String[] featuresNames, RecyclerViewClickListener itemListener){
        this.featuresNames = featuresNames;
        mContext = context;
        features = new Features(mContext);
        this.itemListener = itemListener;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.textView.setText(featuresNames[position]);
        switch (position){
            case Features.SCREEN_BURNOUT: {
                holder.imageView.setImageResource(R.drawable.ic_stay_primary_portrait_white_36dp);
                break;
            }
            case Features.LIGHT: {
                holder.imageView.setImageResource(R.drawable.ic_highlight_white_36dp);
                break;
            }
            case Features.SPEAKER: {
                holder.imageView.setImageResource(R.drawable.ic_speaker_white_36dp);
                break;
            }
            case Features.CALL: {
                holder.imageView.setImageResource(R.drawable.ic_call_white_36dp);
                break;
            }
            case Features.VIBRATE: {
                holder.imageView.setImageResource(R.drawable.ic_vibration_white_36dp);
                break;
            }
            case Features.GPS: {
                holder.imageView.setImageResource(R.drawable.ic_gps_fixed_white_36dp);
                break;
            }
            case Features.NFC: {
                holder.imageView.setImageResource(R.drawable.ic_nfc_white_36dp);
                break;
            }
            case Features.BLUETOOTH: {
                holder.imageView.setImageResource(R.drawable.ic_bluetooth_white_36dp);
                break;
            }
            case Features.ACCELEROMETER: {
                holder.imageView.setImageResource(R.drawable.ic_screen_rotation_white_36dp);
                break;
            }
            case Features.BUTTONS: {
                holder.imageView.setImageResource(R.drawable.ic_keyboard_white_36dp);
                break;
            }
            case Features.CAMERA: {
                holder.imageView.setImageResource(R.drawable.ic_photo_camera_white_36dp);
                break;
            }
        }
        Log.d("Recycler", featuresNames[position]);

    }

    @Override
    public int getItemCount() {
        return featuresNames.length;
    }



    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item,parent,false);
        final TextView textView = (TextView)view.findViewById(R.id.textView);
        final String textViewText =(String)textView.getText();
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            Log.d("Recycler", imageView.toString());
            textView = (TextView)itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v,this.getLayoutPosition());
        }
    }

    public interface RecyclerViewClickListener
    {
        void recyclerViewListClicked(View v, int position);
    }
}
