package n.rnu.isetr.tunisiatourism.AllTourDestinations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import n.rnu.isetr.tunisiatourism.R;

import java.util.ArrayList;

public class DestinationsList extends AppCompatActivity {

    RecyclerView recycler;
    LinearLayoutManager manager;
    DestinationsList_Adapter adapter;
    ArrayList<DestinationsList_Model> array;
    ImageView img ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_attractions_list);
        ImageView img = (ImageView) findViewById(R.id.imageView);



        array = new ArrayList<>();
        array.add(new DestinationsList_Model("El Djem", "Mahdia", R.drawable.djem));
        array.add(new DestinationsList_Model("Djerba", "Medenine", R.drawable.djerba));
        array.add(new DestinationsList_Model("Carthage", "Tunis", R.drawable.carthage));
        array.add(new DestinationsList_Model("Bardo", "Tunis", R.drawable.bardo));
        array.add(new DestinationsList_Model("Sidi Bou Said", "Tunis", R.drawable.sidibousaid));
        array.add(new DestinationsList_Model("Douz", "Kebili", R.drawable.sahra));
        array.add(new DestinationsList_Model("Tabarka", "Jendouba", R.drawable.tabarka));
        array.add(new DestinationsList_Model("Hammamet", "Nabeul", R.drawable.hammamet));
        array.add(new DestinationsList_Model("Tunis Medina", "Tunis", R.drawable.tunismedina));
        array.add(new DestinationsList_Model("Ribat", "Monastir", R.drawable.ribat));

        adapter = new DestinationsList_Adapter(this, array);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recycler = findViewById(R.id.tourattr_recycler);

        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);


        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
             }
        });
    }



}