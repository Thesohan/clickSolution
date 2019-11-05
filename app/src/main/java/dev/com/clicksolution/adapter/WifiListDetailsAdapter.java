package dev.com.clicksolution.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import dev.com.clicksolution.GlideApp;
import dev.com.clicksolution.R;
import dev.com.clicksolution.dataset.RecyclerViewDataset;
import dev.com.clicksolution.interfaces.OnClickListenerEvent;

public class WifiListDetailsAdapter extends RecyclerView.Adapter<WifiListDetailsAdapter.WifiDetailsViewHolder> {

   private final static int FADE_DURATION = 1000;

   private ArrayList<RecyclerViewDataset> datasets;
   private int position;
   private OnClickListenerEvent onClickListenerEvent;
    private Context context;
    public WifiListDetailsAdapter(ArrayList<RecyclerViewDataset> datasets, Context context,OnClickListenerEvent onClickListenerEvent) {
        this.datasets = datasets;
        this.context = context;
        this.onClickListenerEvent=onClickListenerEvent;
    }

    @NonNull
    @Override
    public WifiDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
        return new WifiDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WifiDetailsViewHolder holder, final int position) {

       // holder.image.setImageBitmap(getRe datasets.get(position).getImage());

        GlideApp.with(context)
                .load(datasets.get(position).getImage())
                .centerCrop()
                .into(holder.image);
        holder.text_heading.setText(datasets.get(position).getName());
        holder.subText.setText(datasets.get(position).getSub_name());
        setFadeAnimation(holder.itemView);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(position);//setting the position so that we can access it later to edit / move / delete the items
                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onClickListenerEvent.onClickListener(position);
            }
        });

    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return datasets.size();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    
    class WifiDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener { 

        private ImageView image;
        private TextView text_heading;
        private TextView subText;

        WifiDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.Image);
            text_heading = itemView.findViewById(R.id.imgName);
            subText = itemView.findViewById(R.id.sub_text);
            TextView clickable_view_space = itemView.findViewById(R.id.imgClick);
            itemView.setOnCreateContextMenuListener(this);
        }

        //This context menu will show when user longPress on the recyclerView's item
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0,1,0,context.getString(R.string.edit));
            menu.add(0,2,0,context.getString(R.string.move));
            menu.add(0,3,0,context.getString(R.string.delete));
        }   

    }   
}
