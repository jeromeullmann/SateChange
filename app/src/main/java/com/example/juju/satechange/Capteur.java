package com.example.juju.satechange;

/**
 * Created by pevd620 on 27/03/2015.
 */
public class Capteur {

    //private variables
    int _id;
    String _nomCapteur;
    String _etat;

    // Empty constructor
    public Capteur(){

    }
    // constructor
    public Capteur(int id, String nom, String etat){
        this._id = id;
        this._nomCapteur = nom;
        this._etat = etat;
    }

    // constructor
    public Capteur(String nom, String etat){
        this._nomCapteur = nom;
        this._etat = etat;
    }

    // getting ID
    public int get_id(){
        return this._id;
    }

    // setting id
    public void set_id(int id){
        this._id = id;
    }

    // getting name
    public String get_nomCapteur(){
        return this._nomCapteur;
    }

    // setting name
    public void set_nomCapteur(String nomCapteur){
        this._nomCapteur = nomCapteur;
    }

    // getting Etat capteur
    public String get_etat(){
        return this._etat;
    }

    // setting Etat capteur
    public void set_etat(String type){
        this._etat = type;
    }

}
