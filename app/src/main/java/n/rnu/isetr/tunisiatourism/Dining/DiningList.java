package n.rnu.isetr.tunisiatourism.Dining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import n.rnu.isetr.tunisiatourism.R;

import java.util.ArrayList;
import java.util.HashMap;


public class DiningList extends AppCompatActivity implements Dining_ADAPTER.onBtnFavListener {
    RecyclerView recycler;
    LinearLayoutManager manager;
    Dining_ADAPTER adapter;
    ArrayList<Dining_MODEL> array;
    Button favBtn;
    boolean  isInMyFavorite = false;
    private static final String TAG = "MyActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_list);


        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        array = new ArrayList<>();
        array.add(new Dining_MODEL(R.drawable.billionaire,"Billionaire","NIGHTS OUTINGS • Gammarth"));
        array.add(new Dining_MODEL(R.drawable.shantounsi,"Bab Jdid","STREET FOOD • Tunis-center"));
        array.add(new Dining_MODEL(R.drawable.baboca,"Baboca Beach Club","BEACH RESTAURANT • Gammarth"));
        array.add(new Dining_MODEL(R.drawable.carpediem,"Carpe Diem","NIGHTS OUTINGS • Marsa"));
        array.add(new Dining_MODEL(R.drawable.icecream,"Champs-Elysées ice cream cafe","Tunisian pastry and ice cream parlors • Tunis-center"));


        adapter = new Dining_ADAPTER(this, array,this);

        recycler = findViewById(R.id.recyclerView);
        favBtn=findViewById(R.id.favBtn);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);

        ///




}



public  void addToFavorite(int pos){
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        long timestamp=System.currentTimeMillis();
    HashMap<String,Object> hashMap=new HashMap<>();
    hashMap.put("ditemId",""+pos);
    hashMap.put("time",""+timestamp);

    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
    ref.child(firebaseAuth.getUid()).child("Favorites").child(String.valueOf(pos)).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void unused) {
            Toast.makeText(DiningList.this,"Added to your favorites list...",Toast.LENGTH_LONG).show();
            isInMyFavorite=true;
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(DiningList.this,"Failed to add to favorite due to "+e.getMessage(),Toast.LENGTH_LONG).show();

        }
    });

}
public  void removeFromFavorite(int pos){
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");

    ref.child(firebaseAuth.getUid()).child("Favorites").child(String.valueOf(pos)).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void unused) {
            Toast.makeText(DiningList.this,"Removed from your favorites list...",Toast.LENGTH_LONG).show();
            isInMyFavorite=false;
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(DiningList.this,"Failed to remove from favorite due to "+e.getMessage(),Toast.LENGTH_LONG).show();

        }
    });
}

    @Override
    public void onBtnFavClick(int pos) {


          if (isInMyFavorite==true) {
                    removeFromFavorite(pos);

                }else{
              addToFavorite(pos);
         }


    }}