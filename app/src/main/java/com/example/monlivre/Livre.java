package com.example.monlivre;

import java.util.StringTokenizer;

public class Livre {
    private String Id;
    private String Titre;
    private String Auteur;
    private String Couverture;
    private String Année;
    private boolean Lu;
    private String Description;

    public  Livre(String Id,String Titre, String Auteur, String Couverture, String Description, String Annee, boolean Lu) {
        this.Id = Id;
        this.Titre = Titre;
        this.Auteur = Auteur;
        this.Année = Annee;
        this.Couverture = Couverture;
        this.Description = Description;
        this.Lu = Lu;
    }

    public String getCouverture() {
        return Couverture;
    }

    public String getTitre() {
        return Titre;
    }
    public String getAuteur() {
        return Auteur;
    }
    public String getColor(){
        if (!Lu){
            return "#ef3c3c";
        }
        else{
            return "#5e8c48";
        }
    }
    public String getId(){
        return Id;
    }

}
