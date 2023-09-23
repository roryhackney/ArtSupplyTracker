import javax.swing.JLabel;
import java.awt.Font;

/** Label for headings, with centered bold 20pt serif font */
public class HeadingLabel extends JLabel {
    /** Constructor */
    public HeadingLabel(String label) {
        super(label);
        setFont(new Font("Serif", Font.BOLD, 20));
        setHorizontalAlignment(JLabel.CENTER);
    }
}
