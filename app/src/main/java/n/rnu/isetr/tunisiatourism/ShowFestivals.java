package n.rnu.isetr.tunisiatourism;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ShowFestivals extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_festivals);

        tv=findViewById(R.id.festivals_description);

        String key= getIntent().getStringExtra("key");

        if (Integer.parseInt(key)==0){
            tv.setText("Dance:\n" +
                    "\n" +
                    "The Carthage Choreographic Days take place for a week around the month of June. It offers an international and Tunisian artistic program as well as conferences and debates.\n" +
                    "\n" +
                    "Music:\n" +
                    "\n" +
                    "The Carthage International Festival is the biggest festival in Tunisia. It is one of the most important African festivals. It takes place at the Ancient Roman Theater, in the open air and generally in the months of July-August. The shows feature music, dance, drama. James Brown, Hédi Jouini, Youssou N'Dour, Najwa Karam or even Joe Cocker and Latifa Arfaoui have performed there.\n" +
                    "\n" +
                    "The International Meetings of Alternative Music of Carthage, Mousiqa Wassalem, takes place in August and generally lasts four days. The festival takes place on the esplanade of the Museum of Carthage.\n" +
                    "\n" +
                    "Carthage Urban Fest is a two-day rap music festival during the month of August. It takes place in the open-air Ancient Roman Theatre.\n" +
                    "\n" +
                    "arts and history:\n" +
                    "\n" +
                    "The Carthage Festival takes place in August and commemorates the Battle of Cannae led by Hannibal on August 2, 216 BC. In each edition, conferences and artistic performances recall the ancient history of the city.");
        }
        else if(Integer.parseInt(key)==1){
            tv.setText("Theater:\n" +
                    "\n" +
                    "The International Festival of Theatrical Days of Carthage (JTC) welcomes theater troupes from all over the world. Its programming presents original creations, joint co-productions, conferences, workshops, professional meetings and tributes to seniors. It is usually held in November.\n" +
                    "\n" +
                    "Music:\n" +
                    "\n" +
                    "The famous Musical October of Carthage is a music festival that takes place at the Acropolium of Carthage. This eclectic classical music festival invites renowned talents and transports us into the worlds and traditions of artists from all over the world.\n" +
                    "\n" +
                    "Art & Architecture:\n" +
                    "\n" +
                    "The Carthage Contemporary Art Days take place in November for a week in different places in the Tunis-Carthage region.\n" +
                    "\n" +
                    "The Architectural Days of Carthage take place in November and December for a few days and are the occasion for conferences, meetings-debates, and exhibitions dedicated to architecture and the profession of architect.");
        }else{
            tv.setText("Music:\n" +
                    "\n" +
                    "Jazz à Carthage is a ten-day festival dedicated to Jazz music. It takes place from April in Gammarth and welcomes the big names in international jazz music.\n" +
                    "\n" +
                    "In Sidi Bou Saïd, the Samsung Blues Festival hosted at the Ennejma Ezzahara Palace for four days in March brings together the big names in international blues.\n" +
                    "\n" +
                    "Poetry:\n" +
                    "\n" +
                    "Carthage Poetic Days usually last a week and are held in the spring, between March and April. They give pride of place to the various Tunisian and international poetic currents. Around conferences, round tables and poetic evenings, the participants are transported into the vast universe of Arab and Western poetry.\n" +
                    "\n" +
                    "Arts & History:\n" +
                    "\n" +
                    "During Heritage Month, you can enjoy the many events and exhibitions in the region.");
        }

    }
}