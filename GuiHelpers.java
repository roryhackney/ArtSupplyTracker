import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Swing sucks here are helper methods
 */
public class GuiHelpers {
    //no instantiating please
    private GuiHelpers() {}

    /**
     * When clicked, the label sets focus to the component
     * @param label JLabel to add click handler to; cannot be null
     * @param component component to be focused on click; cannot be null
     */
    public static void makeLabelFocusComponent(JLabel label, Component component) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getComponent() == label) component.requestFocus();
            }
        });
    }
}
