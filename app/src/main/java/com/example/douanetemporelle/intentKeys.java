
package com.example.douanetemporelle;



public class IntentKeys {

    //// trajaalk el cle eli tnajem a partir de lui trajaalk el valeur mte3ou

    public static final String MATRICULE = "1234";

    //// meme principle

    public static String nomVoyageur(String matricule) {
        return matricule + "_NOM_VOYAGEUR";
    }

    //// kifkif lhne trajaalk  1234_ANNEE_NAISSANCE

    public static String anneeNaissance(String matricule) {
        return matricule + "_ANNEE_NAISSANCE";
    }
    ////nafess jaw
    public static String anneeDestination(String matricule) {
        return matricule + "_ANNEE_DESTINATION";
    }
    ////meme
    public static String motifVoyage(String matricule) {
        return matricule + "_MOTIF_VOYAGE";
    }
////kifkiffffff
    public static String verdict(String matricule) {
        return matricule + "_VERDICT";
    }
}

