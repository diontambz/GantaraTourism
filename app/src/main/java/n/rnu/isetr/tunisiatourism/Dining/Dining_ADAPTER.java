package n.rnu.isetr.tunisiatourism.Dining;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import n.rnu.isetr.tunisiatourism.R;

public class Dining_ADAPTER extends RecyclerView.Adapter<Dining_ADAPTER.ViewHolder>{
    Context mycontext;
    ArrayList<Dining_MODEL> model;
    private onBtnFavListener OnBtnFavListener;




    Dining_ADAPTER(Context mycontext, ArrayList<Dining_MODEL> model,onBtnFavListener onBtnFavListener) {
        this.mycontext = mycontext;
        this.model = model;
        this.OnBtnFavListener=onBtnFavListener;
    }

    @NonNull
    @Override
    public Dining_ADAPTER.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mycontext).inflate(R.layout.dining_list_item,null, true);
        return new Dining_ADAPTER.ViewHolder(myview,OnBtnFavListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Dining_ADAPTER.ViewHolder holder, int position) {
        Dining_MODEL dimodel = model.get(position);
        holder.place.setImageResource(dimodel.image);
        holder.placeName.setText(dimodel.placeName);
        holder.guide.setText(dimodel.guide);

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView place;
        TextView placeName,guide;
        onBtnFavListener onBtnFavListener;
        Button btnfav;

        ViewHolder(View itemView,onBtnFavListener onBtnFavListener) {
            super(itemView);

            place = itemView.findViewById(R.id.ivPlace);
            placeName = itemView.findViewById(R.id.tvPlaceName);
            guide=itemView.findViewById(R.id.guide);
            btnfav=itemView.findViewById(R.id.favBtn);

            this.onBtnFavListener=onBtnFavListener;

            btnfav.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onBtnFavListener.onBtnFavClick(getAdapterPosition());
        }
    }
    public interface onBtnFavListener{
        void onBtnFavClick(int pos);
    }
}