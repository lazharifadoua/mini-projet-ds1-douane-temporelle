package com.example.douanetemporelle;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText nom, naissance, destination;
    Spinner motif;
    Button inspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1: Houni nrabtou les éléments XML bel code Java.
        nom = findViewById(R.id.txt_nom_nj);
        naissance = findViewById(R.id.txt_naissance_nj);
        destination = findViewById(R.id.txt_destination_nj);
        motif = findViewById(R.id.sp_motif_nj);
        inspecter = findViewById(R.id.btn_inspecter_nj);

        // 2: N7adhrou liste mta3 les motifs.
        String[] motifs = {
                "Tourisme",
                "Modification du passé",
                "Fuite"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                motifs
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        motif.setAdapter(adapter);

        // 3: Ki el utilisateur yenzel 3al bouton.
        inspecter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 4: Nrajj3ou les informations saisies.
                String nomVoyageur = nom.getText().toString().trim();
                String anneeN = naissance.getText().toString().trim();
                String anneeD = destination.getText().toString().trim();

                String motifChoisi = motif.getSelectedItem().toString();

                // 5: Nethabtou elli les champs lkol mahoumch ferghin.
                if (nomVoyageur.isEmpty() ||
                        anneeN.isEmpty() ||
                        anneeD.isEmpty()) {

                    Toast.makeText(MainActivity.this,
                            "Veuillez remplir tous les champs",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // 6: Nbadlou les années men texte l a3ded entiers.
                int anneeNaissance;
                int anneeDestination;

                try {
                    anneeNaissance = Integer.parseInt(anneeN);
                    anneeDestination = Integer.parseInt(anneeD);

                    if (anneeNaissance <= 0 || anneeDestination <= 0) {
                        Toast.makeText(MainActivity.this,
                                "Veuillez saisir des années valides",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (NumberFormatException e) {

                    Toast.makeText(MainActivity.this,
                            "Années invalides",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // 7: Ncréiw Intent explicite lel activité de vérification.
                Intent i = new Intent(MainActivity.this,
                        verificationActivity.class);

                // 8: Nab3thou les informations lel activité thenya.
                i.putExtra(
                        IntentKeys.nomVoyageur(IntentKeys.MATRICULE),
                        nomVoyageur
                );

                i.putExtra(
                        IntentKeys.anneeNaissance(IntentKeys.MATRICULE),
                        anneeNaissance
                );

                i.putExtra(
                        IntentKeys.anneeDestination(IntentKeys.MATRICULE),
                        anneeDestination
                );

                i.putExtra(
                        IntentKeys.motifVoyage(IntentKeys.MATRICULE),
                        motifChoisi
                );

                // 9: Nlanciw activité mta3 vérification.
                startActivity(i);
            }
        });
    }
}
