package n.rnu.isetr.tunisiatourism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

public class CityDetails extends AppCompatActivity {

    ShapeableImageView mainimage, attr1img, attr2img, food1img, food2img;
    TextView city, description, duration, timetovisit, attr1name, attr1des, attr2name, attr2des, food1name, food1des, food2name, food2des,besttime;
    Button locationbtn;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        mainimage = findViewById(R.id.citydetails_image);
        besttime=findViewById(R.id.textView13);
        attr1img = findViewById(R.id.citydetails_place1image);
        attr2img = findViewById(R.id.citydetails_place2image);
        food1img = findViewById(R.id.citydetails_food1image);
        food2img = findViewById(R.id.citydetails_food2image);
        city = findViewById(R.id.citydetails_name);
        description = findViewById(R.id.citydetails_description);
         timetovisit = findViewById(R.id.citydetails_besttime);
        attr1name = findViewById(R.id.citydetails_place1name);
        attr2name = findViewById(R.id.citydetails_place2name);
        food1name = findViewById(R.id.citydetails_food1name);
        food2name = findViewById(R.id.citydetails_food2name);
        attr1des = findViewById(R.id.citydetails_place1description);
        attr2des = findViewById(R.id.citydetails_place2description);
        food1des = findViewById(R.id.citydetails_food1description);
        food2des = findViewById(R.id.citydetails_food2description);
        locationbtn = findViewById(R.id.citydetails_getlocationbtn);

        String city_str = getIntent().getStringExtra("putextra_city");

        if (city_str.equals("El Djem")) {
            city.setText("Mahdia, El Djam");
            attr1name.setText("El Djem Amphitheater");
            attr2name.setText("El Jem Archaeological Museum");
            attr1des.setText("The walls of the mighty Roman amphitheater of El Djem");
            attr2des.setText("An archaeological museum located in El Djem");
            attr1img.setImageResource(R.drawable.djem);
            attr2img.setImageResource(R.drawable.djammuseum);
            food1name.setText("Chapati EL Mahdia");
            food2name.setText("Brik El Mahdia");
            food1des.setText("A sandwich that is very popular in Mahdia");
            food2des.setText("A traditional dessert");
            food1img.setImageResource(R.drawable.chapatimahdia);
            food2img.setImageResource(R.drawable.brikmahdia);
            besttime.setText("Summer: Mahdia Weather in July: Warm and pleasant day time temperatures, coupled with right amount of dampness in the air makes July the best time to visit Mahdia.");
            description.setText("El Jem is a small town in the east of Tunisia that holds the remains of a UNESCO World Heritage listed Roman amphitheater.");
            location = "https://www.google.com/maps/place/El+Jem/data=!4m2!3m1!1s0x1301fc8250fbd357:0x2ac18b0a4bde5c20?sa=X&ved=2ahUKEwiR5MXO1oj8AhV48rsIHbQxCf4Q8gF6BQiDARAB";
            timetovisit.setText("Best Time to Visit");
             mainimage.setImageResource(R.drawable.djem);
        }

        if (city_str.equals("Djerba")) {
            city.setText("Medenine, Djerba");
            attr1name.setText("Houmt Souk Old Town");
            attr2name.setText("Plage Sidi Maharès");
            attr1des.setText("Djerba's main town");
            attr2des.setText("Djerba's most popular beach");
            attr1img.setImageResource(R.drawable.djerba);
            attr2img.setImageResource(R.drawable.plagesidimehrez);

            food1name.setText("Djerbian rice");
            food2name.setText("Chermoula with fish");
            food1des.setText("A traditional dish");
            food2des.setText("A traditional dish");
            food1img.setImageResource(R.drawable.rouz);
            food2img.setImageResource(R.drawable.chermoula);

            besttime.setText("Spring: Spring is the best time to go to Djerba before the sizzling heat arrives in the summer. ");
            description.setText("Djerba is a small island in south Tunisia with white sandy beaches and whitewashed domed houses.");
            location = "https://www.google.com/search?q=djerba+&client=opera&hs=cAG&tbm=lcl&sxsrf=ALiCzsa671J8yG2bTCbcVuqEKHKz70mM9w%3A1671996771692&ei=Y6WoY_7RKYKK9u8P04y7oAk&ved=0ahUKEwi-j5GtwZX8AhUChf0HHVPGDpQQ4dUDCAg&uact=5&oq=djerba+&gs_lcp=Cg1nd3Mtd2l6LWxvY2FsEAMyBAgjECcyBAgAEEMyBQgAEIAEMgQIABBDMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOgYIABAWEB5Q2gNY2gNg4gdoAHAAeACAAYwBiAGFApIBAzAuMpgBAKABAcABAQ&sclient=gws-wiz-local";
            timetovisit.setText("Best Time to Visit");
            mainimage.setImageResource(R.drawable.djerba);
        }



        locationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(location)));
            }
        });



    }
}



