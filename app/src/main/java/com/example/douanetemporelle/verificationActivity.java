package com.example.douanetemporelle;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VerificationActivity extends Activity {

    TextView txtNom, txtAge, txtMotif, txtAlerte;
    Button btnApprouver, btnRefuser;

    String nomVoyageur;
    String motifChoisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification); //tarbet l code bl interface xml

        // 1: Houni narbtou les éléments XML bel code Java.
        // Nesta3mlou les ID bech nwaṣlou lel textes w lel boutons.
        txtNom = findViewById(R.id.txt_nom_recu_nj);
        txtAge = findViewById(R.id.txt_age_nj);
        txtMotif = findViewById(R.id.txt_motif_recu_nj);
        txtAlerte = findViewById(R.id.txt_alerte_nj);

        btnApprouver = findViewById(R.id.btn_approuver_nj);
        btnRefuser = findViewById(R.id.btn_refuser_nj);

        // 2: Nrecuperiw les informations elli tba3thou men MainActivity.
        // Kol information nesta3mloulha nafs el clé elli sta3malneha fel envoi.
        Intent i = getIntent();

        nomVoyageur = i.getStringExtra( //njibou esm l voyageur li teb3ath ml mainactivity w nhotouh f variable nomvoyageur
                IntentKeys.nomVoyageur(IntentKeys.MATRICULE)
        );

        int anneeNaissance = i.getIntExtra( //meme principe
                IntentKeys.anneeNaissance(IntentKeys.MATRICULE),
                0
        );

        int anneeDestination = i.getIntExtra(
                IntentKeys.anneeDestination(IntentKeys.MATRICULE),
                0
        );

        motifChoisi = i.getStringExtra(
                IntentKeys.motifVoyage(IntentKeys.MATRICULE)
        );

        // 3: Na7sbou el âge temporel b différence bin les deux années.
        int ageTemporel = anneeDestination - anneeNaissance;

        // 4: Naffichiw les informations mta3 el voyageur fel écran.
        // Les textes yetbaddlou 7asb les données elli waslet men MainActivity.
        txtNom.setText("Nom : " + nomVoyageur);
        txtAge.setText("Âge temporel : " + ageTemporel + " ans");
        txtMotif.setText("Motif : " + motifChoisi);

        // 5: Nethabtou men condition mta3 risque de paradoxe.
        // Ken el motif Modification du passé w el différence premier,
        // na3mlou désactivation lel bouton Approuver.
        if ("Modification du passé".equals(motifChoisi)
                && estPremier(ageTemporel)) {

            btnApprouver.setEnabled(false);

            txtAlerte.setText(
                    "Attention : approbation impossible, risque de paradoxe !"
            );

        } else {

            btnApprouver.setEnabled(true);
            txtAlerte.setText("");
        }

        // 6: Ki el utilisateur ya9bel el voyage,
        // nab3thou verdict positif w esm el voyageur lel activité result.
        btnApprouver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                envoyerVerdict(true);
            }
        });

        // 7: Ki el utilisateur yorfedh el voyage,
        // nab3thou verdict négatif w esm el voyageur lel activité result.
        btnRefuser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                envoyerVerdict(false);
            }
        });
    }

    // 8: Hédhi méthode bech na3rfou ken el nombre premier walla le.
    // El nombre premier ma yet9sem ken 3la 1 w 3la rou7ou.
    private boolean estPremier(int nombre) {

        if (nombre < 2) {
            return false;
        }

        for (int i = 2; i <= nombre / i; i++) {

            if (nombre % i == 0) {
                return false;
            }
        }

        return true;
    }

    // 9: Hédhi méthode ta3mel Intent lel activité ResultActivity.
    // Nab3thou fiha verdict el nihai w esm el voyageur.
    private void envoyerVerdict(boolean verdict) {

        Intent i = new Intent(VerificationActivity.this,
                ResultActivity.class); //naamlou intent jdid bch nmchyw l rsultactivity

        i.putExtra(
                IntentKeys.verdict(IntentKeys.MATRICULE),
                verdict
        ); //nabaathou l 9arar true wla false

        i.putExtra(
                IntentKeys.nomVoyageur(IntentKeys.MATRICULE),
                nomVoyageur
        ); // nabaathou esm l voyageur zeda

        startActivity(i);
    }
}
// yaani pour conclure
// fl partie hedhy ysir este9bel maaloumet l voyageur mel mainactivity
//7asben l'age temporel
//tthabet li chourout mriglin w tamna3 l'acceptation ken lzem
//w fellekhr tabaath l9arar finale m3a nom l resultActivity