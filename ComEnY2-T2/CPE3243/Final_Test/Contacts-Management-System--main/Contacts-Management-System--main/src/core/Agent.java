package core;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Agent extends Contact {
    private int salaire;
    private String statut;
    private String categorie;
    private double indiceDeSalaire;
    private String occupation;

    // Constructeur par défaut
    public Agent() {
        super(); // Appel du constructeur par défaut de la classe parent Contact
        this.salaire = 0;
        this.statut = "temporaire";
        this.categorie = "abc";
        this.indiceDeSalaire = 0.1;
        this.occupation = "receptioniste";
    }

    // Constructeur avec paramètres
    public Agent(String code, String nom, Date dateDeNaissance, String address, String email, String telNumber, int salaire, String statut, String categorie, double indiceDeSalaire, String occupation) {
        super(code, nom, dateDeNaissance, address, email, telNumber); // Appel du constructeur avec paramètres de la classe parent Contact
        this.salaire = salaire;
        this.statut = statut;
        this.categorie = categorie;
        this.indiceDeSalaire = indiceDeSalaire;
        this.occupation = occupation;
    }

    // Getters et Setters
    public int getSalaire() {
        return salaire;
    }
    public String getStatut() {
        return statut;
    }
    public String getCategorie() {
        return categorie;
    }
    public double getIndiceDeSalaire() {
        return indiceDeSalaire;
    }
    public String getOccupation() {
        return occupation;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public void setIndiceDeSalaire(double indiceDeSalaire) {
        this.indiceDeSalaire = indiceDeSalaire;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    // Implémentation de la méthode abstraite pour insérer un agent
    @Override
    public void insererDansBase(Connection connection) throws SQLException {
        String sql = "INSERT INTO agents (code, nom, dateDeNaissance, address, email, telNumber, salaire, statut, categorie, indiceDeSalaire, occupation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, getCode());
            statement.setString(2, getNom());
            statement.setDate(3, new java.sql.Date(getDateDeNaissance().getTime()));
            statement.setString(4, getAddress());
            statement.setString(5, getEmail());
            statement.setString(6, getTelNumber());
            statement.setInt(7, getSalaire());
            statement.setString(8, getStatut());
            statement.setString(9, getCategorie());
            statement.setDouble(10, getIndiceDeSalaire());
            statement.setString(11, getOccupation());
            statement.executeUpdate();
        }
    }
}