import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;


/**
 * Setup class (parent) for all GUI classes; cannot be instantiated
 */
public abstract class GuiClass extends JFrame {
    /**
     * subject this GUI sends data to in the subject observer model
     */
    private final SubjectInterface subject;
    /**
     * panel this GUI displays widgets on
     */
    private final JPanel panel;

    /**
     * Basic constructor for GUIs
     *
     * @param subject subject this GUI sends data to in the subject observer model; cannot be null
     */
    public GuiClass(SubjectInterface subject) {
        if (subject == null) throw new IllegalArgumentException("subject cannot be null");
        this.subject = subject;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    }

    /**
     * Returns the subject this GUI sends data to
     *
     * @return the subject this GUI sends data to
     */
    public SubjectInterface getSubject() {
        return subject;
    }

    /**
     * returns the panel this GUI displays widgets on
     *
     * @return the panel this GUI displays widgets on
     */
    public JPanel getPanel() {
        return panel;
    }
}
