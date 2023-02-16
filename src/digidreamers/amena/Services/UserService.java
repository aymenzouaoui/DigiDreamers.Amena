/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.Services;

import digidreamers.amena.Interfaces.InterfaceCRUD;

import digidreamers.amena.Models.User;
import digidreamers.amena.Utils.MyConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aymen
 */
public class UserService implements InterfaceCRUD <User> {

    Connection cnx = MyConnection.getInstance().getConnection();
    Statement stm;
    User loggeduser;

    public UserService() throws SQLException {
        stm = cnx.createStatement();
    }

    @Override
    public void ajouter(User u) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        int stat = 0;
        if (u.isStatus()) {
            stat = 1;
        }
        try {
            // hadhage mot ppass
            String password = u.getMot_pass();
            String hashedPassword = hashPassword(password);
            u.setMot_pass(hashedPassword);
            String querry = " INSERT INTO `user` "
                    + "( `nom`, `prenom`, `adress`, `cin`, `dateNaissance`, `dateCreationC`, `status`, `role`, `motPass`, `email`) "
                    + "VALUES ('"
                    + u.getNom() + "', '"
                    + u.getPrenom() + "', '"
                    + u.getAdress() + "', '"
                    + u.getCin() + "', '"
                    + u.getDate_naissance() + "', '"
                    + date + "', '"
                    + stat + "','"
                    + u.getRole().getId() + "', '"
                    + u.getMot_pass() + "', '"
                    + u.getEmail() + "')";
            stm.executeUpdate(querry);
            //sddq
        } catch (SQLException ex) {
            System.out.println("Personne non ajouté");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // update avec hashage
    @Override
    public void modifier(int id,User u) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        
        byte[] hashedPassword = md.digest(u.getMot_pass().getBytes(StandardCharsets.UTF_8));

        PreparedStatement pla = cnx.prepareStatement("UPDATE user SET nom=?,prenom=?,adress=?,cin=?,dateNaissance=?,role=?,motPass=?,email=? where id=?");

        pla.setString(1, u.getNom());
        pla.setString(2, u.getPrenom());
        pla.setString(3, u.getAdress());
        pla.setString(4, u.getCin());
        pla.setDate(5, u.getDate_naissance());
        pla.setInt(6, u.getRole().getId());
        pla.setString(7, new String(hashedPassword, StandardCharsets.UTF_8));
        pla.setString(8, u.getEmail());
        pla.setInt(9, id);
        pla.executeUpdate();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> afficher() {
        List<User> list = new ArrayList<>();
        try {
            String req = "Select * from user";
            Statement stm = cnx.createStatement();

            ResultSet RS = stm.executeQuery(req);
            while (RS.next()) {
                User p = new User();
                p.setNom(RS.getString("nom"));
                p.setId(RS.getInt(1));
                p.setPrenom(RS.getString(3));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public User getUserByName(String name) throws SQLException {
        String querry = "SELECT * FROM `user` WHERE `nom`='" + name + "'";
        Statement stm = cnx.createStatement();

        User user = new User();

        ResultSet rs = stm.executeQuery(querry);

        while (rs.next()) {

            user.setAdress(rs.getString("adress"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setMot_pass(rs.getString("motPass"));
            user.setEmail(rs.getString("email"));
            user.setCin(rs.getString("cin"));

        }
        return user;

    }

    public User getUserByCIN(String cin) throws SQLException {
        String querry = "SELECT *  FROM `user` WHERE `cin`=" + cin;
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(querry);

        User user = new User();
        while (rs.next()) {

            user.setAdress(rs.getString("adress"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setMot_pass(rs.getString("motPass"));
            user.setEmail(rs.getString("email"));
            user.setCin(rs.getString("cin"));

        }
        return user;
    }

    @Override
    public User  getByID(int id) {
         User user = new User();
        try{
        String querry = "SELECT *  FROM `user` WHERE `id`=" + id;
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(querry);

       
        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setAdress(rs.getString("adress"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setMot_pass(rs.getString("motPass"));
            user.setEmail(rs.getString("email"));
            user.setCin(rs.getString("cin"));
}} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encodedhash);
    }
    /*
    
    
    
    
    
  public void createUser(User user) {
    try {
        String query = "INSERT INTO `user`(`nom`, `prenom`, `adress`, `cin`, `dateNaissance`, `dateCreationC`, `status`, `role`, `motPass`, `email`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, user.getNom());
        statement.setString(2, user.getPrenom());
        statement.setString(3, user.getAdress());
        statement.setString(4, user.getCin());
        statement.setDate(5, new java.sql.Date(user.getDate_naissance().getTime()));
        statement.setDate(6, new java.sql.Date(user.getDate_creation_c().getTime()));
        statement.setBoolean(7, user.isStatus());
        statement.setInt(8, user.getRole().getId());
        statement.setString(9, user.getMot_pass());
        statement.setString(10, user.getEmail());
        
        statement.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

     */

  
}
