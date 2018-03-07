package com.example.dimit.pj_reservation_dpc;

/**
 * Created by dimit on 12/02/2018.
 */

public class UserInformation {
    public String nom;
    public String prenom;
    public String adresse1;
    public String adresse2;
    public String lieuDit;
    public String codePostal;
    public String ville;
    public String numTelephone;
    public String societe;
    public String mail;
   // public String admin;

    public UserInformation(){

    }

    public UserInformation(String nom, String prenom, String adresse1, String adresse2, String lieuDit, String codePostal, String ville, String numTelephone, String societe, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.lieuDit = lieuDit;
        this.codePostal = codePostal;
        this.ville = ville;
        this.numTelephone = numTelephone;
        this.societe = societe;
        this.mail = mail;
       // this.admin = admin;
    }
}