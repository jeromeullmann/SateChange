package com.example.juju.satechange;

/**
 * Created by pevd620 on 27/03/2015.
 */
public class Lieu {
    //private variables
    int _id;
    String _nomLieu;
    String _type;

    // Empty constructor
    public Lieu(){

    }
    // constructor
    public Lieu(int id, String nomLieu, String type){
        this._id = id;
        this._nomLieu = nomLieu;
        this._type = type;
    }

    // constructor
    public Lieu(String nomLieu, String type){
        this._nomLieu = nomLieu;
        this._type = type;
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
    public String get_nomLieu(){
        return this._nomLieu;
    }

    // setting name
    public void set_nomLieu(String nomLieu){
        this._nomLieu = nomLieu;
    }

    // getting phone number
    public String get_type(){
        return this._type;
    }

    // setting phone number
    public void set_type(String type){
        this._type = type;
    }

}
