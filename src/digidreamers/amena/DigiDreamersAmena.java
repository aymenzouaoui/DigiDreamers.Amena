/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena;

import digidreamers.amena.Models.Role;
import digidreamers.amena.Models.User;
import digidreamers.amena.Services.RoleService;
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
       // Role role=new Role(20,"admin");
        /* Role a=new Role(34,"client");
          Role b=new Role(35,"transporteur");
           Role c=new Role(36,"transporteu_s");
          
        
        User user1=new User("12", "tn", "nom", "pren",date , true, "mdp", "a@a",  a);
         User user2=new User("12", "tn", "nom", "pren",date , true, "mdp", "a@a",  b);
          User user3=new User("12", "tn", "nom", "pren",date , true, "mdp", "a@a",  c);
        */  
        //System.out.println(user1.toString());
        
         try{
             
              /*RoleService role =new RoleService();
            role.addRole(a);
            role.addRole(b);
            role.addRole(c);
            
            */
              
            UserService userService=new UserService();
             //userService.addUser(user1);
              // userService.addUser(user2);
               //  userService.addUser(user3);
             
             //user.setEmail("updated@kk");
            
            // role.deleteRole(23);
             
             System.out.println(userService.getUserByName("nohhm"));
             System.out.println(userService.getUserByID(235));
             
             //userService.UpdateUser(user,52);
        }catch (SQLException e) {
            System.out.println("Personne non ajouté");
        }
            // TODO code application logic here

         
         
         
        // TODO code application logic here
    }
         
         
         
        // TODO code application logic here
    }
    

