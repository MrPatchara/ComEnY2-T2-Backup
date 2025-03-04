import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class signupForm extends JFrame {
    private JTextField txtFName, txtLName, txtUsername;
    private JPasswordField txtPassword, txtRetypePass;
    private JLabel lblImage;
    private File selectedFile = null;

    public signupForm() {
        setTitle("Create New Account");
        setSize(450, 550);
        setLayout(null);
        getContentPane().setBackground(new Color(44, 62, 80));

       
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(39, 174, 96));
        headerPanel.setBounds(0, 0, 450, 40);
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel lblTitle = new JLabel("Create New Account");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(10, 5, 250, 30);
        headerPanel.add(lblTitle);

        JButton btnClose = new JButton("X");
        btnClose.setBounds(410, 5, 30, 30);
        btnClose.setBackground(new Color(231, 76, 60));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.addActionListener(e -> System.exit(0));
        headerPanel.add(btnClose);
        
        
        
        
        String[] labels = {"First Name:", "Last Name:", "Username:", "Password:", "Retype Pass:", "Picture:"};
        JTextField[] textFields = {txtFName = new JTextField(), txtLName = new JTextField(), txtUsername = new JTextField()};
        JPasswordField[] passwordFields = {txtPassword = new JPasswordField(), txtRetypePass = new JPasswordField()};

        int y = 60;
        for (int i = 0; i < labels.length - 1; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setForeground(Color.WHITE);
            lbl.setBounds(50, y, 100, 25);
            add(lbl);

            if (i < 3) {
                textFields[i].setBounds(150, y, 200, 25);
                add(textFields[i]);
            } else {
                passwordFields[i - 3].setBounds(150, y, 200, 25);
                add(passwordFields[i - 3]);
            }
            y += 40;
        }

        JLabel lblPicture = new JLabel("Picture:");
        lblPicture.setForeground(Color.WHITE);
        lblPicture.setBounds(50, y, 100, 25);
        add(lblPicture);

        lblImage = new JLabel();
        lblImage.setBounds(150, y, 100, 100);
        lblImage.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        add(lblImage);

        JButton btnBrowse = new JButton("Browse");
        btnBrowse.setBounds(270, y + 30, 80, 30);
        btnBrowse.addActionListener(e -> chooseImage());
        add(btnBrowse);

        // Buttons
        JButton btnCancel = new JButton("Cancel");
        JButton btnCreate = new JButton("Create");

        btnCancel.setBounds(100, 450, 100, 35);
        btnCancel.setBackground(new Color(231, 76, 60));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(e -> System.exit(0));
        add(btnCancel);

        btnCreate.setBounds(250, 450, 100, 35);
        btnCreate.setBackground(new Color(52, 152, 219));
        btnCreate.setForeground(Color.WHITE);
        btnCreate.addActionListener(e -> registerUser());
        add(btnCreate);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "jpeg"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            lblImage.setIcon(new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage()
                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        }
    }

   private void registerUser() {
    String firstName = txtFName.getText().trim();
    String lastName = txtLName.getText().trim();
    String username = txtUsername.getText().trim();
    String password = new String(txtPassword.getPassword()).trim();
    String retypePassword = new String(txtRetypePass.getPassword()).trim();
    
    
    if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || retypePassword.isEmpty()) {
        String message = "Please fill in the following required fields:\n";
        if (firstName.isEmpty()) message += "- First Name\n";
        if (lastName.isEmpty()) message += "- Last Name\n";
        if (username.isEmpty()) message += "- Username\n";
        if (password.isEmpty()) message += "- Password\n";
        if (retypePassword.isEmpty()) message += "- Retype Password\n";
        JOptionPane.showMessageDialog(this, message, "Missing Information", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    
    if (!password.equals(retypePassword)) {
        JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_db", "root", "engineer");
         PreparedStatement pst = con.prepareStatement("INSERT INTO users (username, password, profile_picture) VALUES (?, ?, ?)") ) {

        pst.setString(1, username);
        pst.setString(2, password);

        if (selectedFile != null) {
            FileInputStream fis = new FileInputStream(selectedFile);
            pst.setBinaryStream(3, fis, (int) selectedFile.length());
        } else {
            pst.setNull(3, java.sql.Types.BLOB);
        }

        pst.executeUpdate();
        JOptionPane.showMessageDialog(this, "Signup Successful!");
        new loginForm().setVisible(true);
        dispose();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


    public static void main(String[] args) {
        new signupForm();
    }
}
