import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Observer extends GuiClass implements ObserverInterface {
     private ArrayList<ArtSupply> artSupplies;

     public Observer(SubjectInterface subject) {
         super(subject);
         subject.register(this);
         setTitle("Results Window");
         setPreferredSize(new Dimension(400, 600));
         setMinimumSize(new Dimension(300, 150));
         getPanel().setLayout(new BoxLayout(getPanel(), BoxLayout.PAGE_AXIS));
         getPanel().setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
     }

     public void update() {
         getPanel().removeAll();
         getPanel().repaint();
         JLabel title = new JLabel("Results");
         title.setFont(new Font("Serif", Font.BOLD, 20));
         title.setAlignmentX(Component.CENTER_ALIGNMENT);
         getPanel().add(title);
         getPanel().add(Box.createRigidArea(new Dimension(0, 10)));
         artSupplies = getSubject().getData();
         for (ArtSupply artSupply : artSupplies) {
             JLabel artSupplyDisplay = new JLabel(artSupply.toString());
             getPanel().add(artSupplyDisplay);
             artSupplyDisplay.setBackground(Color.RED);
             artSupplyDisplay.setOpaque(true);
             artSupplyDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
         }
         pack();
         validate();
         setVisible(true);

         //TODO: try returning an HTML String from the ArtSupply class and displaying them all in a Widget, then
         //Just remove that widget so you don't have to redo all the setup
         //Put the setup in the constructor
     }
}
