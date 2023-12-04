package n.rnu.isetr.tunisiatourism.AllTourDestinations;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import n.rnu.isetr.tunisiatourism.CityDetails;
import n.rnu.isetr.tunisiatourism.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class DestinationsList_Adapter extends RecyclerView.Adapter<DestinationsList_Adapter.ViewHolder>{

    Context context;
    ArrayList<DestinationsList_Model> arrayList;

    public DestinationsList_Adapter(Context context, ArrayList<DestinationsList_Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DestinationsList_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View showview = LayoutInflater.from(context).inflate(R.layout.tour_attractions_list_item_layout, null, true);
        return new DestinationsList_Adapter.ViewHolder(showview);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationsList_Adapter.ViewHolder holder, int position) {

        DestinationsList_Model model = arrayList.get(position);

        holder.city.setText(model.city);
        holder.state.setText(model.state);
        holder.picture.setImageResource(model.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CityDetails.class);

                intent.putExtra("putextra_city", model.city);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView city, state;
        ShapeableImageView picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            city = itemView.findViewById(R.id.tourattr_name);
            state = itemView.findViewById(R.id.tourattr_state);
            picture = itemView.findViewById(R.id.tourattr_image);

        }
    }
}
