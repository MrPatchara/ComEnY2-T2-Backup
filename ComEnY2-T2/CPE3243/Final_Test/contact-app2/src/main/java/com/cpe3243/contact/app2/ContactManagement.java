import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class ContactManagement extends JFrame {
    private int userId;
    private JTextField txtId, txtFName, txtLName, txtPhone, txtEmail;
    private JTextArea txtAddress;
    private JComboBox<String> cmbGroup;
    private JLabel lblProfilePic;
    private JTable contactTable;
    private DefaultTableModel tableModel;
    private File selectedFile = null;

    public ContactManagement(int userId) {
        this.userId = userId;
        setTitle("My Contacts");
        setSize(900, 700);
        setLayout(null);
        getContentPane().setBackground(new Color(35, 35, 35)); 

        // Header Panel 
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 102, 0)); 
        headerPanel.setBounds(0, 0, 900, 50); 
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel lblTitle = new JLabel("My Contacts", SwingConstants.CENTER);
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setFont(new Font("Impact", Font.BOLD, 28)); 
        lblTitle.setBounds(0, 5, 900, 40); 
        headerPanel.add(lblTitle);

        JButton btnClose = new JButton("X");
        btnClose.setBounds(850, 5, 40, 40); 
        btnClose.setBackground(new Color(255, 50, 50));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Arial", Font.BOLD, 18)); 
        btnClose.addActionListener(e -> System.exit(0));
        headerPanel.add(btnClose);
        
        // Navigation Buttons
        JButton btnFirst = new JButton("<<");
        JButton btnPrev = new JButton("<");
        JButton btnNext = new JButton(">");
        JButton btnLast = new JButton(">>");

        btnFirst.setBounds(100, 295, 50, 25);
        btnPrev.setBounds(160, 295, 50, 25);
        btnNext.setBounds(220, 295, 50, 25);
        btnLast.setBounds(280, 295, 50, 25);


        add(btnFirst);
        add(btnPrev);
        add(btnNext);
        add(btnLast);


        // Form ด้านซ้าย
        String[] labels = {"ID:", "First Name:", "Last Name:", "Phone:", "Email:", "Group:"};
        int y = 60;
        for (String label : labels) {
            JLabel lbl = new JLabel(label);
            lbl.setForeground(Color.ORANGE); // สีตัวอักษรแนว Half-Life
            lbl.setBounds(20, y, 100, 25);
            add(lbl);
            y += 40;
        }

        txtId = new JTextField();
        txtId.setBounds(120, 60, 50, 25);
        txtId.setEditable(false);
        add(txtId);

        txtFName = new JTextField();
        txtFName.setBounds(120, 100, 200, 25);
        add(txtFName);

        txtLName = new JTextField();
        txtLName.setBounds(120, 140, 200, 25);
        add(txtLName);

        txtPhone = new JTextField();
        txtPhone.setBounds(120, 180, 200, 25);
        add(txtPhone);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 220, 200, 25);
        add(txtEmail);

        String[] groups = {"Family", "Friends", "Work"};
        cmbGroup = new JComboBox<>(groups);
        cmbGroup.setBounds(120, 260, 200, 25);
        add(cmbGroup);

        // รูป Profile Picture
        JLabel lblProfile = new JLabel("Profile Picture:");
        lblProfile.setForeground(Color.ORANGE);
        lblProfile.setBounds(420, 60, 120, 25);
        add(lblProfile);

        lblProfilePic = new JLabel();
        lblProfilePic.setBounds(530, 60, 100, 100);
        lblProfilePic.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        add(lblProfilePic);

        JButton btnBrowse = new JButton("Browse");
        btnBrowse.setBounds(640, 100, 100, 30);
        btnBrowse.addActionListener(e -> chooseImage());
        add(btnBrowse);

        // ช่องกรอก Address
        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setForeground(Color.ORANGE);
        lblAddress.setBounds(420, 180, 100, 25);
        add(lblAddress);

        txtAddress = new JTextArea();
        txtAddress.setBounds(530, 180, 200, 60);
        add(txtAddress);

        // ปุ่มควบคุม
        JButton btnDelete = new JButton("Delete");
        JButton btnEdit = new JButton("Edit");
        JButton btnAdd = new JButton("Add");

        btnDelete.setBounds(500, 260, 120, 40);
        btnDelete.setBackground(new Color(255, 50, 50));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.addActionListener(e -> deleteContact());
        add(btnDelete);

        btnEdit.setBounds(630, 260, 120, 40);
        btnEdit.setBackground(new Color(52, 152, 219));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.addActionListener(e -> updateContact());
        add(btnEdit);

        btnAdd.setBounds(760, 260, 120, 40);
        btnAdd.setBackground(new Color(46, 204, 113));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.addActionListener(e -> insertContact());
        add(btnAdd);

        // ตารางแสดง Contact พร้อมรูป
        tableModel = new DefaultTableModel();
        contactTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(contactTable);
        scrollPane.setBounds(20, 320, 850, 300);
        add(scrollPane);

        tableModel.addColumn("ID");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Group");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Email");
        tableModel.addColumn("Address");
        tableModel.addColumn("Image");

        contactTable.setRowHeight(100);
        TableColumn imageColumn = contactTable.getColumnModel().getColumn(7);
        imageColumn.setCellRenderer(new ImageRenderer());

        loadContacts();
        btnNext.addActionListener(e -> moveSelection(1));
        btnLast.addActionListener(e -> moveSelection(contactTable.getRowCount() - 1));
        btnPrev.addActionListener(e -> moveSelection(-1));
        btnFirst.addActionListener(e -> moveSelection(-contactTable.getRowCount() + 1));

        contactTable.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        int selectedRow = contactTable.getSelectedRow();
        
        
        if (selectedRow >= 0) {
            txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtFName.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtLName.setText(tableModel.getValueAt(selectedRow, 2).toString());
            cmbGroup.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
            txtPhone.setText(tableModel.getValueAt(selectedRow, 4).toString());
            txtEmail.setText(tableModel.getValueAt(selectedRow, 5).toString());
            txtAddress.setText(tableModel.getValueAt(selectedRow, 6).toString());

            
            System.out.println("Selected Contact ID: " + txtId.getText());
        } else {
            System.out.println("No row selected.");
        }
    }
});

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void moveSelection(int step) {
    int rowCount = contactTable.getRowCount();
    if (rowCount == 0) return;

    int currentRow = contactTable.getSelectedRow();
    int newRow = (currentRow + step + rowCount) % rowCount; // Ensures looping

    contactTable.setRowSelectionInterval(newRow, newRow);
    contactTable.scrollRectToVisible(new Rectangle(contactTable.getCellRect(newRow, 0, true)));
}

    
    private void insertContact() {
    // Validate if all required fields are filled
    StringBuilder missingFields = new StringBuilder();

    if (txtFName.getText().trim().isEmpty()) missingFields.append("- First Name\n");
    if (txtLName.getText().trim().isEmpty()) missingFields.append("- Last Name\n");
    if (txtPhone.getText().trim().isEmpty()) missingFields.append("- Phone\n");
    if (txtEmail.getText().trim().isEmpty()) missingFields.append("- Email\n");
    if (txtAddress.getText().trim().isEmpty()) missingFields.append("- Address\n");

    if (missingFields.length() > 0) {
        JOptionPane.showMessageDialog(this, "Please fill in the following fields before saving:\n" + missingFields, "Missing Information", JOptionPane.WARNING_MESSAGE);
        return; // Stop execution if fields are missing
    }

    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_db", "root", "engineer");
         PreparedStatement pst = con.prepareStatement("INSERT INTO contacts (first_name, last_name, phone, email, address, group_name, image, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

        pst.setString(1, txtFName.getText());
        pst.setString(2, txtLName.getText());
        pst.setString(3, txtPhone.getText());
        pst.setString(4, txtEmail.getText());
        pst.setString(5, txtAddress.getText());
        pst.setString(6, cmbGroup.getSelectedItem().toString());
        pst.setInt(8, userId);

        if (selectedFile != null) {
            FileInputStream fis = new FileInputStream(selectedFile);
            pst.setBinaryStream(7, fis, (int) selectedFile.length());
        } else {
            pst.setNull(7, java.sql.Types.BLOB);
        }

        int rowsInserted = pst.executeUpdate();
        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(this, "Contact Added Successfully!");
            loadContacts();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to Add Contact.");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}


    private void updateContact() {
    String contactId = txtId.getText().trim();
    
    // Debug: ตรวจสอบค่าก่อนอัปเดต
    System.out.println("Updating Contact ID: " + contactId);

    if (contactId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a contact to update!");
        return;
    }

    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_db", "root", "engineer");
         PreparedStatement pst = con.prepareStatement("UPDATE contacts SET first_name=?, last_name=?, phone=?, email=?, address=?, group_name=?, image=? WHERE id=? AND user_id=?")) {

        pst.setString(1, txtFName.getText());
        pst.setString(2, txtLName.getText());
        pst.setString(3, txtPhone.getText());
        pst.setString(4, txtEmail.getText());
        pst.setString(5, txtAddress.getText());
        pst.setString(6, cmbGroup.getSelectedItem().toString());
        pst.setInt(8, Integer.parseInt(contactId));
        pst.setInt(9, userId);

        if (selectedFile != null) {
            FileInputStream fis = new FileInputStream(selectedFile);
            pst.setBinaryStream(7, fis, (int) selectedFile.length());
        } else {
            pst.setNull(7, java.sql.Types.BLOB);
        }

        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Contact Updated Successfully!");
            loadContacts();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to Update Contact. Please Try Again!");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}



    private void deleteContact() {
    String contactId = txtId.getText().trim();
    
    if (contactId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a contact ID to delete!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this contact?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.NO_OPTION) {
        return;
    }

    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_db", "root", "engineer");
         PreparedStatement pst = con.prepareStatement("DELETE FROM contacts WHERE id=? AND user_id=?")) {

        pst.setInt(1, Integer.parseInt(contactId));
        pst.setInt(2, userId);

        int rowsDeleted = pst.executeUpdate();
        if (rowsDeleted > 0) {
            JOptionPane.showMessageDialog(this, "Contact Deleted Successfully!");
            loadContacts();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "No contact found with this ID. Please check again!");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}

    private void loadContacts() {
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_db", "root", "engineer");
         PreparedStatement pst = con.prepareStatement("SELECT * FROM contacts WHERE user_id = ?")) {
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();

        tableModel.setRowCount(0); 
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String group = rs.getString("group_name");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            String address = rs.getString("address");

           
            byte[] imgData = rs.getBytes("image");
            ImageIcon imgIcon = null;
            if (imgData != null) {
                Image img = new ImageIcon(imgData).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                imgIcon = new ImageIcon(img);
            }

            
            tableModel.addRow(new Object[]{id, firstName, lastName, group, phone, email, address, imgIcon});
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


    private void clearFields() {
    txtId.setText("");
    txtFName.setText("");
    txtLName.setText("");
    txtPhone.setText("");
    txtEmail.setText("");
    txtAddress.setText("");
    cmbGroup.setSelectedIndex(0);
    lblProfilePic.setIcon(null);
    selectedFile = null;
}



    private void chooseImage() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "jpeg"));
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        selectedFile = fileChooser.getSelectedFile();
        lblProfilePic.setIcon(new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage()
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
    }
}

    public static void main(String[] args) {
        new ContactManagement(1);
    }
}


class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            JLabel label = new JLabel((ImageIcon) value);
            label.setHorizontalAlignment(JLabel.CENTER); 
            return label;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}


