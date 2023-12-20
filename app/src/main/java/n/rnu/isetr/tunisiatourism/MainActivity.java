package n.rnu.isetr.tunisiatourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.support.v4.os.ResultReceiver;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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


import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.text.DecimalFormat;
import org.json.JSONException;
import org.tensorflow.lite.Interpreter;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    ViewFlipper v_flipper;

    RecyclerView destinations;

    ArrayList<Destinations_MODEL> destinations_models;
    Destinations_ADAPTER destinations_adapter;
    LinearLayoutManager manager;
    TextView seetouristdestinations, explore, dining, festivals;
    BottomNavigationView bottomNavigationView;

    private Interpreter tflite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Interpreter tflite = new Interpreter(loadModelFile());
        }catch (Exception ex){
            ex.printStackTrace();
        }

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );


        int images[] = {R.drawable.ribat, R.drawable.hammamet, R.drawable.tunismedina};

        v_flipper = findViewById(R.id.flipper);

        for (int image : images) {
            flipperImages(image);
        }


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userData = snapshot.getValue(User.class);

                if (userData != null) {
                    String fullName = userData.fullName;
                    greetingTextView.setText(fullName + "!");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
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
        explore = findViewById(R.id.discover_llink);
        dining = findViewById(R.id.dining_link);
        festivals = findViewById(R.id.festivalslink);

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


    public void flipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

//animation
        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }

    private void getWeatherData(String cityName) {
        String apiKey = "ad6111536fabfbf8fdc994c25ac0aac5";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey;
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject mainObject = response.getJSONObject("main");
                            double temperatureKelvin = mainObject.getDouble("temp");

                            double temperatureCelsius = temperatureKelvin - 273.15;

                            DecimalFormat df = new DecimalFormat("#.##");
                            String formattedTemperature = df.format(temperatureCelsius);
                            Toast.makeText(MainActivity.this, "Temperature: " + formattedTemperature + "°C", Toast.LENGTH_SHORT).show();
                            int weatherImageResource;
                            if (temperatureCelsius > 25) {
                                weatherImageResource = R.drawable.img_1;
                            } else if (temperatureCelsius > 15) {
                                weatherImageResource = R.drawable.img;
                            } else {
                                weatherImageResource = R.drawable.img_2;
                            }

                            flipperImages(weatherImageResource);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle kesalahan jaringan atau respons API
                        Toast.makeText(MainActivity.this, "Error fetching weather data", Toast.LENGTH_SHORT).show();
                    }
                }
        );


        queue.add(jsonObjectRequest);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;
        }
        return false;
    }

    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd("degree.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declareLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declareLength);
    }
    private float doInference(String inputString) {
        float[] inputVal=new float[1];
        inputVal[0]=Float.parseFloat(inputString);
        float[][] output=new float[1][1];
        tflite.run(inputVal,output);
        return output[0][0];
    }

}