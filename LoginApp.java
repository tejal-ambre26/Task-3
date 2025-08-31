package loginapp;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LoginApp {
    public static void main(String[] args) {
        // Try Nimbus look & feel for a cleaner UI
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            // Fallback to default LAF
        }

        // Launch the Login Form on the EDT
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}
