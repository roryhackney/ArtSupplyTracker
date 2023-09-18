import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegisterGui extends GuiClass {
    public UserRegisterGui(SubjectInterface subject) {
        super(subject);
        setTitle("User Registration");
        setMinimumSize(new Dimension(300, 400));
        getPanel().setLayout(new BoxLayout(getPanel(), BoxLayout.PAGE_AXIS));

        JButton example = new JButton("Example button");
        example.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSubject().setData();
            }
        });
        example.setPreferredSize(new Dimension(100, 50));
        getPanel().add(example);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
