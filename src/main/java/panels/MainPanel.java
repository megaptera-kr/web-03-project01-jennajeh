package panels;

import models.CurrentUser;
import models.User;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.FileNotFoundException;
import java.util.List;

public class MainPanel extends JPanel {
    private List<User> users;
    private CurrentUser currentUser;

    public MainPanel(CurrentUser currentUser) {
        this.currentUser = currentUser;

        initButtonPanel();
    }

    public MainPanel(List<User> users, CurrentUser currentUser) {
        this.users = users;
        this.currentUser = currentUser;

        initButtonPanel();
    }

    private void initButtonPanel() {
        JPanel initButtonPanel = new JPanel();
        initButtonPanel.setOpaque(false);

        initButtonPanel.add(checkListButton());
        initButtonPanel.add(reviewButton());
        initButtonPanel.add(logoutButton());

        this.setOpaque(false);
        this.add(initButtonPanel);
    }

    private JButton checkListButton() {
        JButton checkListButton = new JButton("체크 리스트");
        checkListButton.addActionListener(event -> {
            try {
                updatePanel(new CheckListPanel(currentUser));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return checkListButton;
    }

    private JButton reviewButton() {
        JButton checkListButton = new JButton("리뷰 게시판");
        checkListButton.addActionListener(event -> {
            try {
                updatePanel(new ReviewPanel(users, currentUser));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return checkListButton;
    }

    private JButton logoutButton() {
        JButton checkListButton = new JButton("로그아웃");
        checkListButton.addActionListener(event -> {
            JOptionPane optionPane = new JOptionPane();

            JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "Fries!", JOptionPane.PLAIN_MESSAGE);

            currentUser.logout();

            try {
                updatePanel(new InitLoginPanel());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return checkListButton;
    }

    private void updatePanel(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.setVisible(false);
        this.setVisible(true);
    }
}
