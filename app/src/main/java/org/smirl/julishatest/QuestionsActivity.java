package org.smirl.julishatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class QuestionsActivity extends AppCompatActivity {
    TextView tv, textViewCountDown;
    Button btnconfimer, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4;
   // Context contex = this;
    //String asText;
    String questions[] = {
            "Est-ce que vous avez une toux récente ? \n \n " +
                    "Toux récente signifie une toux que vous n'aviez pas avant ou si vous toussez de manière chronique, que votre toux s'est empirée.",
            "Avez-vous des difficultés à respirer ?",
            "Pensez-vous avoir eu de la fièvre ces derniers jours ? ",
            "Avez-vous une fatigue inhabituelle ces derniers jours ?",
            "Avez-vous mal à la gorge ?",
            "Avez-vous une impossibilité de manger ou boire depuis 24 heures ou plus ?",
            "Avez-vous des courbatures en dehors des douleurs musculaires liées à une activité sportive intense ?",
            "Avez-vous perdu l’odorat de manière brutale sans rapport avec le nez bouché ?",
            "Avez-vous la diarrhée ? \n \n Avoir la diarrhée signifie émettre au moins 3 selles molles ou liquides par jour ou à une fréquence (c’est à dire un nombre de fois pour une période de temps) anormale pour la personne."

    };

    String reponses[] = {"Oui", "Oui",  "Oui", "Oui","Oui","Oui","Oui","Oui","Oui","int"};
    String choix[] = {
            "Oui", "Non","", "",
            "Oui", "Non", "", "",
            "Oui","Non","","",
            "Oui","Non","","",
            "Oui","Non","","",
            "Oui","Non","","",
            "Oui","Non","","",
            "Oui","Non","","",
            "Oui","Non","","",

    };
    int flag = 0;
    public static int Etat = 0, Positif = 0, Nagatif = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        setTitle("Covid19 Diagnostic ");

        TextView textView = (TextView) findViewById(R.id.DispName);
        textView.setText("Question:");


        textView.setText("Question:");

        btnconfimer = (Button) findViewById(R.id.button3);
        //quitbutton=(Button)findViewById(R.id.buttonquit);
        tv = (TextView) findViewById(R.id.tvque);

        radio_g = (RadioGroup) findViewById(R.id.answersgrp);
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);

        tv.setText(questions[flag]);
        rb1.setText(choix[0]);
        rb2.setText(choix[1]);

        btnconfimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if (radio_g.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Veillez faire un choix SVP", Toast.LENGTH_SHORT).show();

                    return;
                }

                RadioButton Qreponse = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String repText = Qreponse.getText().toString();

                if (repText.equals(reponses[flag])) {
                    Positif++;


                } else {
                    Nagatif++;
                    //Toast.makeText(getApplicationContext(), "Question Suivante", Toast.LENGTH_SHORT).show();


                }

                flag++;


                if (flag < questions.length) {
                    tv.setText(questions[flag]);
                    rb1.setText(choix[flag * 4]);
                    rb2.setText(choix[flag * 4 + 1]);

                } else {
                    Etat = Positif;


                    Intent in = new Intent(getApplicationContext(), CovidResultatActivity.class);
                    startActivity(in);
                    finish();
                }
                radio_g.clearCheck();


            }


        });
    }

        @Override
        public void onBackPressed () {
            View parentLayout = findViewById(android.R.id.content);

            Snackbar snackbar = Snackbar.make(parentLayout, "Abandonner le Test en cours ?", Snackbar.LENGTH_LONG)
                    .setAction("OUI", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           // Intent in = new Intent(getApplicationContext(), CovidResultatActivity.class);
                            //startActivity(in);
                            finish();

                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.white));
            View sview = snackbar.getView();

            sview.setBackgroundColor(Color.RED);
            snackbar.show();


        }

    }








