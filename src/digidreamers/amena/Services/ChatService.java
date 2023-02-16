/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.Services;

import digidreamers.amena.Interfaces.InterfaceCRUD;
import digidreamers.amena.Models.Message;
import digidreamers.amena.Models.User;
import digidreamers.amena.Utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
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
public class ChatService implements InterfaceCRUD <Message>  {

    Statement ste;
    Connection cnx = MyConnection.getInstance().getConnection();

    public void ajouter(Message m) {
        try {
            String query = "INSERT INTO `message` (`senderId`, `receiverId`, `content`, `timestamp`) VALUES (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(query);

            ste.setInt(1, m.getSenderId());
            ste.setInt(2, m.getReceiverId());
            ste.setString(3, m.getContent());
            ste.setDate(4, (Date) m.getTimestamp());

            ste.executeUpdate();

            System.out.println("user ajouterajoute!!!");
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    public List<Message> getChatsByReceiverId(int receiverId) throws SQLException {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM message WHERE receiverid = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setInt(1, receiverId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int senderId = resultSet.getInt("senderid");
            String content = resultSet.getString("Conetent");
            Date timestamp = resultSet.getDate("timestamp");

            Message message = new Message(id, senderId, receiverId, content, timestamp);
            messages.add(message);
        }

        return messages;
    }

    public List<Message> getAllChats() {
    List<Message> messages = new ArrayList<>();
    try {
        String query = "SELECT * FROM message";
        PreparedStatement statement = cnx.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int receiverId = resultSet.getInt("receiverId");
            int senderId = resultSet.getInt("senderid");
            String content = resultSet.getString("Conetent");
            Date timestamp = resultSet.getDate("timestamp");

            Message message = new Message(id, senderId, receiverId, content, timestamp);
            messages.add(message);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return messages;
}

    @Override
    public void supprimer(int id) {
        try {
            String query = "DELETE FROM message WHERE id = " + id;;
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("Message suprimer!");
            ste.executeUpdate(query);

            System.out.println("message deleted !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(int id, Message t) {
    try {
        String query = "UPDATE message SET receiverId = ?, senderid = ?, Conetent = ?, timestamp = ? WHERE id = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setInt(1, t.getReceiverId());
        statement.setInt(2, t.getSenderId());
        statement.setString(3, t.getContent());
        statement.setDate(4, new java.sql.Date(t.getTimestamp().getTime()));
        statement.setInt(5, id);
        statement.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
public List<Message> afficher(int receiverId) {
    List<Message> messages = new ArrayList<>();
    try {
        String query = "SELECT * FROM message WHERE receiverId = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setInt(1, receiverId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int senderId = resultSet.getInt("senderid");
            String content = resultSet.getString("Conetent");
            Date timestamp = resultSet.getDate("timestamp");

            Message message = new Message(id, senderId, receiverId, content, timestamp);
            messages.add(message);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return messages;
}


    @Override
    public List<Message> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message getByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
