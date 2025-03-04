package Graphics;

import core.Contact;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsTable extends JFrame {
    private JTable contactsTable;

    public ContactsTable(List<Contact> contactsList) {
        setTitle("Liste des Contacts");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"Code", "Nom", "Date de Naissance", "Adresse", "Email", "Numéro de Téléphone"};
        Object[][] data = new Object[contactsList.size()][6];

        for (int i = 0; i < contactsList.size(); i++) {
            Contact contact = contactsList.get(i);
            data[i][0] = contact.getCode();
            data[i][1] = contact.getNom();
            data[i][2] = contact.getDateDeNaissance();
            data[i][3] = contact.getAddress();
            data[i][4] = contact.getEmail();
            data[i][5] = contact.getTelNumber();
        }

        contactsTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(contactsTable);
        contactsTable.setFillsViewportHeight(true);

        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Contact> contactsList = getContactsListFromDatabase();
            ContactsTable frame = new ContactsTable(contactsList);
            frame.setVisible(true);
        });
    }

    private static List<Contact> getContactsListFromDatabase() {
        List<Contact> contactsList = new ArrayList<>();
        Connection connection = null;
        try {
            // Connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/POO_projet", "root", "pavel");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contact");

            // Parcourir les résultats de la requête et ajouter chaque contact à la liste
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String nom = resultSet.getString("nom");
                Date dateDeNaissance = resultSet.getDate("dateDeNaissance");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String telNumber = resultSet.getString("telNumber");

                // Création d'un objet Contact avec les données récupérées
                Contact contact = null;
                contactsList.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer la connexion à la base de données
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return contactsList;
    }
}
