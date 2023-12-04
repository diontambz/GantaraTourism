package n.rnu.isetr.tunisiatourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
 import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import n.rnu.isetr.tunisiatourism.AllTourDestinations.DestinationsList;
import n.rnu.isetr.tunisiatourism.Dining.DiningList;
import n.rnu.isetr.tunisiatourism.HomeDestinations.Destinations_ADAPTER;
import n.rnu.isetr.tunisiatourism.HomeDestinations.Destinations_MODEL;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    ViewFlipper v_flipper;

    RecyclerView  destinations ;

    ArrayList<Destinations_MODEL> destinations_models;
     Destinations_ADAPTER destinations_adapter;
    LinearLayoutManager manager;
    TextView seetouristdestinations,explore,dining,festivals;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );


        int images[]={R.drawable.ribat, R.drawable.hammamet, R.drawable.tunismedina};

        v_flipper=findViewById(R.id.flipper);

        for (int image:images){
            flipperImages(image);}




        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();
      final TextView greetingTextView=(TextView)findViewById(R.id.greeting);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userData=snapshot.getValue(User.class);

                if(userData!=null){
                    String fullName=userData.fullName;
                   greetingTextView.setText(fullName +"!");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"Something wrong happened!",Toast.LENGTH_LONG).show();
            }
        });

/***********************************************************************************/

        destinations = findViewById(R.id.destinations_recyclerview);

        destinations_models = new ArrayList<>();
        destinations_models.add(new Destinations_MODEL(R.drawable.djem, "El Djem Amphitheater", "Mahdia, El Djem"));
        destinations_models.add(new Destinations_MODEL(R.drawable.djerba, "Houmt Souk", "Djerba"));
        destinations_models.add(new Destinations_MODEL(R.drawable.carthage, "Antoine Baths", "Tunis, Carthage"));
        destinations_models.add(new Destinations_MODEL(R.drawable.bardo, "The National Bardo Museum", "Tunis, Bardo"));

        destinations_adapter = new Destinations_ADAPTER(this, destinations_models);
        manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        destinations.setAdapter(destinations_adapter);
        destinations.setLayoutManager(manager);



        seetouristdestinations = findViewById(R.id.seealllink);
        explore=findViewById(R.id.discover_llink);
        dining=findViewById(R.id.dining_link);
        festivals=findViewById(R.id.festivalslink);

        seetouristdestinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DestinationsList.class));
            }
        });


        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ExploreActivity.class));
            }
        });

        dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DiningList.class));
            }
        });
        festivals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FestivalsActivity.class));
            }
        });
        /***********************************************************************************/


        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);


    }



    public void flipperImages(int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

//animation
        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                return true;
        }
        return false;
    }
}