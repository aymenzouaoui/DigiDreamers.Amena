/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena;

import digidreamers.amena.Models.Role;
import digidreamers.amena.Models.User;
import digidreamers.amena.Services.UserService;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aymen
 */
public class DigiDreamersAmena {

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) {
        Date date=new java.sql.Date(new java.util.Date().getTime());
        Role role=new Role(1,"admin");
        User user=new User("12", "tn", "nom", "pren",date , true, "mdp", "a@a",  role);
        System.out.println(user.toString());
        
         try{
             UserService userService=new UserService();
             userService.addUser(user);
             user.setEmail("updated@kk");
             
             userService.UpdateUser(user,52);
        }catch (SQLException e) {
            System.out.println("Personne non ajouté");
        }
        catch (NoSuchAlgorithmException ex) {
            System.out.println("Personne non ajouté");
        }
         
         
         
        // TODO code application logic here
    }
         
         
         
        // TODO code application logic here
    }
    

