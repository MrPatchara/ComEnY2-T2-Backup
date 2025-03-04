package core;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Enseignant extends Contact {
    private String statut;

    // Constructeur par défaut
    public Enseignant() {
        super(); // Appel du constructeur par défaut de la classe parent Contact
        this.statut = "permanent";
    }

    // Constructeur avec paramètre
    public Enseignant(String code, String nom, Date dateDeNaissance, String address, String email, String telNumber, String statut) {
        super(code, nom, dateDeNaissance, address, email, telNumber); // Appel du constructeur avec paramètres de la classe parent Contact
        this.statut = statut;
    }

    // Getter
    public String getStatut() {
        return statut;
    }

    // Setter
    public void setStatut(String statut) {
        this.statut = statut; // Correction de l'assignation
    }
    // Implémentation de la méthode abstraite pour insérer un enseignant
    @Override
    public void insererDansBase(Connection connection) throws SQLException {
        String sql = "INSERT INTO enseignants (code, nom, dateDeNaissance, address, email, telNumber, statut) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, getCode());
            statement.setString(2, getNom());
            statement.setDate(3, new java.sql.Date(getDateDeNaissance().getTime()));
            statement.setString(4, getAddress());
            statement.setString(5, getEmail());
            statement.setString(6, getTelNumber());
            statement.setString(7, getStatut());
            statement.executeUpdate();
        }
    }
}
