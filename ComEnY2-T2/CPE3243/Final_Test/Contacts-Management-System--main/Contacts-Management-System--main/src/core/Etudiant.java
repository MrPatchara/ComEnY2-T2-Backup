package core;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Etudiant extends Contact {
    private String cycle;
    private int niveau;

    // Constructeur par défaut
    public Etudiant() {
        super("0000", "ABC", new Date(), "112", "toto@gmail.com", "123456789"); // Appel du constructeur de la classe parent avec des valeurs par défaut
        this.cycle = "Licence";
        this.niveau=1;
    }

    // Constructeur avec paramètres
    public Etudiant(String code, String nom, Date dateDeNaissance, String address, String email, String telNumber, String cycle, int niveau) {
        super(code, nom, dateDeNaissance, address, email, telNumber); // Appel du constructeur de la classe parent avec les paramètres fournis
        this.cycle = cycle;
        this.niveau = niveau;
    }

    // Getters et Setters
    public String getCycle() {
        return cycle;
    }
    public int getNiveau() {
        return niveau;
    }
    public void setCycle(String cycle) {
        this.cycle = cycle; // Correction de l'assignation
    }
    public void setNiveau(int niveau) {
        this.niveau = niveau; // Correction de l'assignation
    }
    @Override
    public void insererDansBase(Connection connection) throws SQLException {
        String sql = "INSERT INTO etudiants (code, nom, dateDeNaissance, address, email, telNumber, cycle, niveau) VALUES ('123', 'toto','2004-1-1' ,'yaoun' , 'gmai','653258547','licence', 3)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, getCode());
            statement.setString(2, getNom());
            statement.setDate(3, new java.sql.Date(getDateDeNaissance().getTime()));
            statement.setString(4, getAddress());
            statement.setString(5, getEmail());
            statement.setString(6, getTelNumber());
            statement.setString(7, getCycle());
            statement.setInt(8, getNiveau());
            statement.executeUpdate();
        }
    }
}
