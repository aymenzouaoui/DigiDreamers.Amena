/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.Services;


import digidreamers.amena.Interfaces.InterfaceCRUD;
import digidreamers.amena.Models.Role;
import digidreamers.amena.Utils.MyConnection;

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
public class RoleService implements InterfaceCRUD <Role>{
Connection cnx = MyConnection.getInstance().getConnection();
    Statement stm;
    public RoleService() {
    try {
        stm = cnx.createStatement();
    } catch (SQLException ex) {
        Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
    
@Override
    public void ajouter (Role role)  {
        
         try {
        String querry="INSERT INTO `role`( `role`) VALUES ('"+role.getRole()+"')";
          stm.executeUpdate(querry);
            //sddq
        } catch (SQLException ex) {
            System.out.println("Personne non ajouté");
        }
    }
@Override
    public void modifier (int id,Role role) {
        
        try {
        String querry ="UPDATE `role` SET `role`='"+role+"' WHERE `id`="+id;
         stm.executeUpdate(querry);
            //sddq
        } catch (SQLException ex) {
            System.out.println("Personne non ajouté");
        }
    }
@Override
    public void supprimer(int id ) {
        
   
   
    try {
         String querry ="DELETE FROM `role` WHERE id="+id;
        Statement st = cnx.createStatement();
            st.executeUpdate(querry);
            System.out.println("Personne deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Role> afficher() {
    List<Role> roles = new ArrayList<Role>();
    try {
        String query = "SELECT * FROM role";
        PreparedStatement statement = cnx.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int id = result.getInt("id");
            String role = result.getString("role");
            Role r = new Role(id, role);
            roles.add(r);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return roles;
}

    
@Override
    public Role getByID(int id) {
    Role role = null;
    try {
        String query = "SELECT * FROM role WHERE id = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String roleName = result.getString("role");
            role = new Role(id, roleName);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return role;
}


    
    
   
}
