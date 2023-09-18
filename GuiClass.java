import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GuiClass extends JFrame {
    private SubjectInterface subject;
    private JPanel panel;

    public GuiClass(SubjectInterface subject) {
        if (subject == null) throw new IllegalArgumentException("subject cannot be null");
        this.subject = subject;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setIconImage(Image);
        panel = new JPanel();
        getContentPane().add(panel);
        //TODO: change size based on amount of content (height), responsive screen size (width)
    }

    public SubjectInterface getSubject() {
        return subject;
    }

    public JPanel getPanel() {
        return panel;
    }
}
