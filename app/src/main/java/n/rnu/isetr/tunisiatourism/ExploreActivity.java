package n.rnu.isetr.tunisiatourism;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

public class ExploreActivity extends AppCompatActivity {

    ShapeableImageView mainimage;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

         description=findViewById(R.id.tunisia_description);

         description.setText("Embark on a journey like never before with GANTARA, your ultimate companion in the realm of adventurous tourism. Designed to cater to the intrepid souls seeking unique and off-the-beaten-path experiences, GANTARA is not just an app; it's your passport to a world of exploration.\n" +
                "\n" +
                "Uncover the hidden treasures of each destination as GANTARA guides you to offbeat locales, ensuring you experience the extraordinary. Personalize your travel itinerary based on your interests, whether it's hiking, wildlife exploration, or cultural immersion. GANTARA crafts bespoke journeys to suit your adventurous spirit. Gain access to local insights and insider tips from seasoned adventurers and residents, providing you with a genuine and immersive travel experience.\n" +
                "\n" +
                "Stay in the loop with real-time updates on weather conditions, local events, and happenings, allowing you to make the most informed decisions during your journey. Connect with like-minded explorers through GANTARA's vibrant community. Share stories, exchange tips, and form bonds with fellow adventurers from around the globe. Prioritize safety with GANTARA's robust safety features, including emergency contacts, real-time location sharing, and local emergency service information.");

    }
}