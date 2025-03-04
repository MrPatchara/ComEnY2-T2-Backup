package core;

import java.util.Date;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class  Contact {
    private String code;
    private String nom;
    private Date dateDeNaissance;
    private String address;
    private String email;
    private String telNumber;

    // Constructeur par défaut
    public Contact() {
        this.code = "0000";
        this.nom = "ABC";
        this.dateDeNaissance = new Date(); // Vous devez créer un objet Date ici
        this.address = "112";
        this.email = "toto@gmail.com";
        this.telNumber = "123456789";
    }
    // Constructeur avec paramètre
    public Contact(String code, String nom, Date dateDeNaissance, String address, String email, String telNumber) {
        this.code = code; // Correction de l'assignation
        this.nom = nom; // Correction de l'assignation
        this.dateDeNaissance = dateDeNaissance; // Correction de l'assignation
        this.address = address; // Correction de l'assignation
        this.email = email; // Correction de l'assignation
        this.telNumber = telNumber; // Correction de l'assignation
    }

    // Getters et Setters
    // ...



	//getters
	public String getCode() {
		return code;
	}
	public String getNom() {
		return nom;
	}
	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public String getTelNumber() {
		return telNumber;
	}

	//setters
	public void setCode(String code) {
		this.code=code;
	}
	public void setNom(String nom) {
		this.nom=nom;
	}
	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance=dateDeNaissance;
	}
	public void setAddress(String address) {
		this.address=address;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber=telNumber;
	}
	
    // Méthode abstraite pour insérer un contact dans la base de données
    public abstract void insererDansBase(Connection connection) throws SQLException;
}

