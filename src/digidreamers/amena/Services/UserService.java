/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.Services;

import digidreamers.amena.Interfaces.UserServiceInterface;
import digidreamers.amena.Models.User;
import digidreamers.amena.Utils.MyConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aymen
 */
public class UserService implements UserServiceInterface{
    Connection cnx = MyConnection.getInstance().getConnection();
    Statement stm;
    User loggeduser;

    public UserService() throws SQLException{
        stm = cnx.createStatement();
    }
    
    public void addUser (User u) {
         java.sql.Date date=new java.sql.Date(new java.util.Date().getTime());
         int stat=0;
         if(u.isStatus()){
             stat=1;
         }
         try {
             // hadhage mot ppass
        String password = u.getMot_pass();
        String hashedPassword = hashPassword(password);
        u.setMot_pass(hashedPassword);
        String querry = " INSERT INTO `user` "
                + "( `nom`, `prenom`, `adress`, `cin`, `dateNaissance`, `dateCreationC`, `status`, `role`, `motPass`, `email`) "
                + "VALUES ('" 
                + u.getNom()+ "', '" 
                + u.getPrenom()+ "', '" 
                + u.getAdress() + "', '" 
                + u.getCin() + "', '" 
                + u.getDate_naissance() + "', '" 
                + date + "', '" 
                + stat+"','" 
                + u.getRole().getId() + "', '" 
                + u.getMot_pass() + "', '" 
                + u.getEmail()+ "')";
        stm.executeUpdate(querry);
       } catch (SQLException ex) {
            System.out.println("Personne non ajouté");
                      } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
     }
     
    
    
    // update avec hashage
     public void UpdateUser(User u,int id) throws SQLException, NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hashedPassword = md.digest(u.getMot_pass().getBytes(StandardCharsets.UTF_8));
    
    PreparedStatement pla = cnx.prepareStatement("UPDATE user SET nom=?,prenom=?,adress=?,cin=?,dateNaissance=?,role=?,motPass=?,email=? where id=?");
     
    pla.setString(1,u.getNom());
    pla.setString(2,u.getPrenom());
    pla.setString(3,u.getAdress());
    pla.setString(4,u.getCin());
    pla.setDate(5,u.getDate_naissance());
    pla.setInt(6,u.getRole().getId());
    pla.setString(7,new String(hashedPassword, StandardCharsets.UTF_8));
    pla.setString(8,u.getEmail());
    pla.setInt(9, id);
    pla.executeUpdate();
}

     
    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encodedhash);
    }

     

}