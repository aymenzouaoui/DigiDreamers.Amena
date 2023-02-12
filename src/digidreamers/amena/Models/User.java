/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.Models;

import java.sql.Date;

/**
 *
 * @author aymen
 */
public class User {
    
   private int id ;
   private String cin;
   private String adress;
  private String nom ;
  private String prenom ;
  private Date date_naissance ;
  private Date date_creation_c ;
  private boolean status;
  private String mot_pass ;
  private String email ;
  private Role role ;

    public User(int id, String cin, String adress, String nom, String prenom, Date date_naissance, Date date_creation_c, boolean status, String mot_pass, String email, Role role) {
        this.id = id;
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.date_creation_c = date_creation_c;
        this.status = status;
        this.mot_pass = mot_pass;
        this.email = email;
        this.role = role;
    }

    public User(String cin, String adress, String nom, String prenom, Date date_naissance, boolean status, String mot_pass, String email, Role role) {
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.status = status;
        this.mot_pass = mot_pass;
        this.email = email;
        this.role = role;
    }

    public User() {
       
    }

    public User(String string, String tn, String nom, String pren, Date date, boolean b, String mdp, String aa, int role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Date getDate_creation_c() {
        return date_creation_c;
    }

    public void setDate_creation_c(Date date_creation_c) {
        this.date_creation_c = date_creation_c;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMot_pass() {
        return mot_pass;
    }

    public void setMot_pass(String mot_pass) {
        this.mot_pass = mot_pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", cin=" + cin + ", adress=" + adress + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance + ", date_creation_c=" + date_creation_c + ", status=" + status + ", mot_pass=" + mot_pass + ", email=" + email + ", role=" + role + '}';
    }

   
   
}
