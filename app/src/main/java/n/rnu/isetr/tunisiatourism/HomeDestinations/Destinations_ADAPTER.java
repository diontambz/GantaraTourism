package n.rnu.isetr.tunisiatourism.HomeDestinations;
;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import n.rnu.isetr.tunisiatourism.R;

public class Destinations_ADAPTER extends RecyclerView.Adapter<Destinations_ADAPTER.ViewHolder> {

    Context mycontext;
    ArrayList<Destinations_MODEL> model;

    public Destinations_ADAPTER(Context mycontext, ArrayList<Destinations_MODEL> model) {
        this.mycontext = mycontext;
        this.model = model;
    }

    @NonNull
    @Override
    public Destinations_ADAPTER.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mycontext).inflate(R.layout.home_recyclerview_layout,null, true);
        return new Destinations_ADAPTER.ViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull Destinations_ADAPTER.ViewHolder holder, int position) {
        Destinations_MODEL model2 = model.get(position);
        holder.image.setImageResource(model2.image);
        holder.name.setText(model2.name);
        holder.state.setText(model2.state);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, state;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.city_image);
            name = itemView.findViewById(R.id.city_name);
            state = itemView.findViewById(R.id.state_name);



        }
    }
}
