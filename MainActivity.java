package com.example.entrainementb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int compteur_croissant;
    private int compteur_chocolat;
    private int compteur_painchocolat;

    public static final String COMPTEUR_CROISSANT="com.example.entrainementb.COMPTEUR_CROISSANT";
    public static final String COMPTEUR_CHOCOLAT="com.example.entrainementb.COMPTEUR_CHOCOLAT";
    public static final String COMPTEUR_PAINCHOCOLAT="com.example.entrainementb.COMPTEUR_PAINCHOCOLAT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( savedInstanceState != null ){
            compteur_croissant = savedInstanceState . getInt ( COMPTEUR_CROISSANT ,compteur_croissant) ;
            compteur_chocolat = savedInstanceState . getInt ( COMPTEUR_CHOCOLAT ,compteur_chocolat) ;
            compteur_painchocolat = savedInstanceState . getInt ( COMPTEUR_PAINCHOCOLAT ,compteur_painchocolat) ;
        }
        TextView textcroissant = findViewById(R.id.textcroissant);
        TextView textchocolat = findViewById(R.id.textchocolat);
        TextView textpainchocolat = findViewById(R.id.textpainchocolat);

        ImageView imcroissant = findViewById(R.id.imcroissant);
        ImageView imchocolat = findViewById(R.id.imchocolat);
        ImageView impainchocolat = findViewById(R.id.impainchocolat);

        textcroissant.setText(" croissant est : " + compteur_croissant);
        textchocolat.setText(" chocolat est : " + compteur_chocolat);
        textpainchocolat.setText(" pain au chocolat est : " + compteur_painchocolat);

        imcroissant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compteur_croissant++;
                textcroissant.setText(" croissant est : " + compteur_croissant);
            }
        });

        imchocolat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compteur_chocolat++;
                textchocolat.setText(" chocolat est : " + compteur_chocolat);
            }
        });

        impainchocolat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (compteur_chocolat<compteur_croissant
                        ||(compteur_croissant==0 && compteur_chocolat==0)
                ||compteur_chocolat>compteur_croissant){
                    Toast.makeText(getApplicationContext(),
                            "Plus assez de matière\n" +
                                    "première   ",
                            Toast.LENGTH_SHORT).show();
                }

                if ((compteur_croissant!=1)
                        ||(compteur_croissant>compteur_chocolat)
                        ||compteur_chocolat>compteur_croissant){


                    int temp =nbChocolatines(compteur_croissant, compteur_chocolat);

                    for (int i=temp;i>0;i--) {
                        if ((compteur_croissant > 0 )&&( compteur_chocolat > 0)) {

                            compteur_painchocolat++;
                            compteur_croissant--;
                            compteur_chocolat = compteur_chocolat - 2;

                            textcroissant.setText("croissant est : " + compteur_croissant);
                            textchocolat.setText(" chocolat est : " + compteur_chocolat);
                            textpainchocolat.setText("nombre pain chocolat : " + compteur_painchocolat);
                        }                                                                    /* 2cr 1ch -> 1cr -ch*/
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Plus assez de matière\n" +
                                    "première   ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public int nbChocolatines(int compteur_croissant, int compteur_chocolat) {
        int temp = 0;
        while ((compteur_chocolat > compteur_croissant)
                ||(compteur_chocolat < compteur_croissant)
                || (compteur_chocolat == compteur_croissant)) {

            if (compteur_chocolat > 1) {
                temp = compteur_chocolat / 2;
                return temp;
            }

            if (compteur_croissant > 0) {
                int i = temp - compteur_croissant;
                return i;
            }
            return temp;
        }
        return temp;
    }

    protected void onSaveInstanceState ( Bundle outState ) {
        outState.putInt(COMPTEUR_CROISSANT,compteur_croissant);
        super.onSaveInstanceState(outState);

        outState.putInt(COMPTEUR_CHOCOLAT,compteur_chocolat);
        super.onSaveInstanceState(outState);

        outState.putInt(COMPTEUR_PAINCHOCOLAT,compteur_painchocolat);
        super.onSaveInstanceState(outState);
    }
}
