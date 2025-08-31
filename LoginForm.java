package loginapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {
    private JLabel titleLabel;
    private JLabel userLabel;
    private JLabel passLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JCheckBox showPasswordCheck;
    private JLabel statusLabel;

    public LoginForm() {
        super("Login Page");
        initComponents();
        setSize(400, 300);               // Set window size
        setLocationRelativeTo(null);     // Center the form
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        titleLabel = new JLabel("Login Page", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");

        usernameField = new JTextField();
        usernameField.setToolTipText("Enter your username");

        passwordField = new JPasswordField();
        passwordField.setToolTipText("Enter your password");

        showPasswordCheck = new JCheckBox("Show");
        showPasswordCheck.addActionListener(e -> {
            if (showPasswordCheck.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•'); // bullet
            }
        });

        loginButton = new JButton("Login");
        loginButton.addActionListener(this::onLoginClicked);

        // Make Enter key trigger Login
        getRootPane().setDefaultButton(loginButton);

        statusLabel = new JLabel(" ");
        statusLabel.setForeground(new Color(180, 0, 0));

        // Layout with GroupLayout (NetBeans-like)
        JPanel content = new JPanel();
        GroupLayout layout = new GroupLayout(content);
        content.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titleLabel)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(userLabel)
                        .addComponent(passLabel))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(usernameField, 200, 200, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(passwordField, 200, 200, Short.MAX_VALUE)
                            .addComponent(showPasswordCheck))))
                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addComponent(statusLabel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel)
                    .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passLabel)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showPasswordCheck))
                .addGap(15)
                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(8)
                .addComponent(statusLabel)
        );

        setContentPane(content);
    }

    private void onLoginClicked(ActionEvent evt) {
        String username = usernameField.getText().trim();
        char[] passChars = passwordField.getPassword();
        String password = new String(passChars);

        if (username.isEmpty() || password.isEmpty()) {
            setStatus("Please enter both username and password.");
            return;
        }

        boolean ok = AuthService.authenticate(username, password);
        if (ok) {
            setStatus("Login successful!");
            // Open dashboard
            SwingUtilities.invokeLater(() -> {
                Dashboard dashboard = new Dashboard(username);
                dashboard.setVisible(true);
            });
            dispose();
        } else {
            setStatus("Invalid credentials. Try again.");
            passwordField.setText("");
            passwordField.requestFocusInWindow();
        }
    }

    private void setStatus(String msg) {
        statusLabel.setText(msg);
    }
}
