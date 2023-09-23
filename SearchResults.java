import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.*;

/**
 * GUI Observer that displays the results of the search sent from the Subject
 */
public class SearchResults extends GuiClass implements ObserverInterface {
    /**
     * List of ArtSupplies to be displayed
     */
    private JList<ArtSupply> artSupplies;

    /**
     * Model data to be displayed in the List
     */
    private final DefaultListModel<ArtSupply> model;

    /**
     * Constructs a SearchResults GUI window which Observes the given Subject
     * @param subject Subject (data) to be Observed; cannot be null
     */
    public SearchResults(SubjectInterface subject) {
        super(subject);
        subject.register(this);

        setTitle("Results Window");
        setPreferredSize(new Dimension(400, 600));
        setMinimumSize(new Dimension(300, 150));

        JLabel title = new HeadingLabel("Results");
        getPanel().add(title, BorderLayout.NORTH);

        model = new DefaultListModel<>();
        artSupplies = new JList<>(model);
    }

    /** upon notification that Subject data has updated, removes old data and displays new data */
    public void update() {
        getPanel().remove(artSupplies);
        getPanel().repaint();

        model.clear();
        model.addAll(getSubject().getData());
        artSupplies = new JList<>(model);
        getPanel().add(artSupplies, BorderLayout.CENTER);

        pack();
        validate();
        setVisible(true);
    }
}
