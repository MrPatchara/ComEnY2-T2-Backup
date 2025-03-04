import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class loginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public loginForm() {
        setTitle("Login Form");
        setSize(400, 350);
        setLayout(null);
        getContentPane().setBackground(new Color(44, 62, 80));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(39, 174, 96));
        headerPanel.setBounds(0, 0, 400, 40);
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel lblTitle = new JLabel("Login Form");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(10, 5, 200, 30);
        headerPanel.add(lblTitle);

        JButton btnClose = new JButton("X");
        btnClose.setBounds(360, 5, 30, 30);
        btnClose.setBackground(new Color(231, 76, 60));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.addActionListener(e -> System.exit(0));
        headerPanel.add(btnClose);

        // Labels & Fields
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");

        lblUsername.setForeground(Color.WHITE);
        lblPassword.setForeground(Color.WHITE);

        lblUsername.setBounds(50, 60, 100, 25);
        lblPassword.setBounds(50, 100, 100, 25);

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        txtUsername.setBounds(150, 60, 200, 25);
        txtPassword.setBounds(150, 100, 200, 25);

        // Checkbox Show Password
        JCheckBox chkShowPass = new JCheckBox("Show Pass");
        chkShowPass.setBounds(150, 130, 100, 20);
        chkShowPass.setBackground(new Color(44, 62, 80));
        chkShowPass.setForeground(Color.WHITE);
        chkShowPass.addActionListener(e -> {
            txtPassword.setEchoChar(chkShowPass.isSelected() ? (char) 0 : '*');
        });

        // Buttons
        JButton btnCancel = new JButton("Cancel");
        JButton btnLogin = new JButton("Login");

        btnCancel.setBounds(80, 180, 100, 35);
        btnCancel.setBackground(new Color(231, 76, 60));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(e -> System.exit(0));
        add(btnCancel);

        btnLogin.setBounds(220, 180, 100, 35);
        btnLogin.setBackground(new Color(52, 152, 219));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(e -> loginUser());
        add(btnLogin);

        // Signup Link Label
        JLabel lblSignup = new JLabel("New user? Click here to signup");
        lblSignup.setForeground(Color.CYAN);
        lblSignup.setBounds(110, 230, 200, 25);
        lblSignup.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblSignup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                new signupForm().setVisible(true);
                dispose();
            }
        });
        add(lblSignup);

        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(chkShowPass);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private int loginAttempts = 0; 

    private void loginUser() {
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_db", "root", "engineer");
        PreparedStatement pst = con.prepareStatement("SELECT id FROM users WHERE username=? AND password=?")) {

        pst.setString(1, txtUsername.getText());
        pst.setString(2, new String(txtPassword.getPassword()));

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int userId = rs.getInt("id"); 
            JOptionPane.showMessageDialog(this, "Login Successful!");
            new ContactManagement(userId).setVisible(true);
            dispose(); // Close login form
        } else {
            loginAttempts++; 
            if (loginAttempts >= 3) {
                JOptionPane.showMessageDialog(this, "Incorrect login attempt 3 times! Please check your username and password.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Login Failed! Incorrect username or password.");
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


    public static void main(String[] args) {
        new loginForm();
    }
}
