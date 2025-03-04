package Graphics; // Déclaration du package Graphics

import javax.swing.*; // Importation des classes Swing pour l'interface graphique
import java.awt.*; // Importation des classes AWT pour la mise en page et les composants
import java.awt.event.*; // Importation des classes pour la gestion des événements
import core.*; // Importation des classes du package core

// Déclaration de la classe CreateContactWindow qui hérite de JFrame pour créer une fenêtre
public class CreateContactWindow extends JFrame {
    // Déclaration des champs de texte pour les informations du contact
    private JTextField codeField, nomField, dateNaissanceField, addressField, emailField, telNumberField;
    // Déclaration d'une liste déroulante pour le type de contact
    private JComboBox<String> typeContact;
    // Déclaration d'un bouton pour sauvegarder le contact
    private JButton saveButton;

    // Constructeur de la classe CreateContactWindow
    public CreateContactWindow() {
        setTitle("Créer un Contact"); // Définit le titre de la fenêtre
        setSize(1000, 800); // Définit la taille de la fenêtre
        setLayout(new GridBagLayout()); // Utilise GridBagLayout pour la disposition des composants
        
        GridBagConstraints gbc = new GridBagConstraints(); // Crée un objet GridBagConstraints pour spécifier les contraintes de positionnement
        gbc.fill = GridBagConstraints.HORIZONTAL; // Les composants s'étendent horizontalement
        gbc.insets = new Insets(5, 5, 5, 5); // Définit les marges externes des composants

        // Configuration et ajout des composants à la fenêtre en utilisant GridBagConstraints
        gbc.gridx = 0; // Colonne 0
        gbc.gridy = 0; // Ligne 0
        add(new JLabel("Type de Contact:"), gbc); // Ajout du label "Type de Contact:"

        typeContact = new JComboBox<>(new String[]{"Etudiant", "Enseignant", "Agent"}); // Initialisation de la liste déroulante avec les types de contact
        gbc.gridx =1; // Colonne 1
        add(typeContact, gbc); // Ajout de la liste déroulante à la fenêtre

        gbc.gridy = 0; // Ligne 1
        add(new JLabel("Code:"), gbc); // Ajout du label "Code:"

        codeField = new JTextField("1234"); // Initialisation du champ de texte pour le code avec une valeur par défaut
        gbc.gridx = 1; // Colonne 1
        add(codeField, gbc); // Ajout du champ de texte pour le code à la fenêtre

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Nom:"), gbc);

        nomField = new JTextField("John Doe"); // Initialisation du champ de texte pour le nom avec une valeur par défaut
        gbc.gridx = 1;
        add(nomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Date de Naissance:"), gbc);

        dateNaissanceField = new JTextField("2000-01-01"); // Initialisation du champ de texte pour la date de naissance avec une valeur par défaut
        gbc.gridx = 1;
        add(dateNaissanceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Adresse:"), gbc);

        addressField = new JTextField("123 Rue ABC"); // Initialisation du champ de texte pour l'adresse avec une valeur par défaut
        gbc.gridx = 1;
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Email:"), gbc);

        emailField = new JTextField("john.doe@example.com"); // Initialisation du champ de texte pour l'email avec une valeur par défaut
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Numéro de Téléphone:"), gbc);

        telNumberField = new JTextField("1234567890"); // Initialisation du champ de texte pour le numéro de téléphone avec une valeur par défaut
        gbc.gridx = 1;
        add(telNumberField, gbc);

        gbc.gridx = 4; // Colonne 4
        gbc.gridy = 7; // Ligne 7
        gbc.gridwidth = 100; // Le bouton s'étend sur 100 colonnes
        saveButton = new JButton("Enregistrer"); // Création du bouton "Enregistrer"
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données des champs de texte
                String type = (String) typeContact.getSelectedItem();
                String code = codeField.getText();
                String nom = nomField.getText();
                String dateNaissance = dateNaissanceField.getText();
                String address = addressField.getText();
                String email = emailField.getText();
                String telNumber = telNumberField.getText();

                // Créer un contact en fonction du type sélectionné
                Contact contact = null;
                switch (type) {
                    case "Etudiant":
                        contact = new Etudiant();
                        break;
                    case "Enseignant":
                        contact = new Enseignant();
                        break;
                    case "Agent":
                        contact = new Agent();
                        break;
                    default:
                        // Gérer le cas où le type n'est pas reconnu
                        break;
                }

                // Ajouter le contact au répertoire
                MainFrmApplication.repertoire.add(contact);

                // Enregistrer le contact dans la base de données
                MainFrmApplication.enregistrer();
            }
        });
        add(saveButton, gbc); // Ajout du bouton "Enregistrer" à la fenêtre

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Définit l'opération de fermeture par défaut
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        setVisible(true); // Rend la fenêtre visible
    }

    // Méthode main pour exécuter l'application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CreateContactWindow(); // Crée et affiche la fenêtre
            }
        });
    }
}
