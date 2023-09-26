import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/** User Name Settings GUI which sends user selections to its Subject */
public class UserNameGui extends GuiClass {

    /**
     * Constructor
     * @param subject Subject which receives user selections and updates its Observers with new data
     * @param user User of the ArtSupply Manager; if null, new User will be created
     */
    public UserNameGui(SubjectInterface subject, User user) {
        super(subject);
        setTitle("Username Settings");

        //random greeting will display alias if user already exists
        String[] greetings = new String[] {"Welcome", "Hello", "Greetings", "Hi"};
        int index = (int) (Math.random() * (greetings.length));
        JLabel welcomeMessage = new HeadingLabel("");
        if (user == null) {
//            getPanel().setBackground(Color.lightGray);
            welcomeMessage.setText(greetings[index] + "!");
//            welcomeMessage.setOpaque(true);
//            welcomeMessage.setBackground(Color.darkGray);
//            welcomeMessage.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            user = new User();
        } else {
            welcomeMessage.setText(greetings[index] + ", " + user.getAlias());
        }
        getPanel().add(welcomeMessage, BorderLayout.NORTH);

        //form

        JPanel form = new JPanel();
        form.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        getPanel().add(form, BorderLayout.CENTER);
        form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));

        //main alias
        JLabel aliasLabel = new JLabel("What would you like to be called? (optional)");
        JTextField alias = new JTextField(20);
        alias.setMaximumSize(new Dimension(700, 20));
        aliasLabel.setLabelFor(alias);
        form.add(aliasLabel);
//        form.add(Box.createHorizontalGlue());
        form.add(alias);
//        form.add(Box.createHorizontalGlue());

        //random checkbox
        JLabel randomLabel = new JLabel("Would you like to use random names?");
        JCheckBox random = new JCheckBox();
        randomLabel.setLabelFor(random);
        form.add(randomLabel);
        form.add(random);

        //editable table of random names
        JLabel randomAliasesLabel = new JLabel("What random names would you like to use?");
        JLabel limit = new JLabel(String.format("(up to %s names, %s characters each)", User.ALIASES_LIMIT, User.LENGTH_LIMIT));
        int numRows = user.numRandomAliases() + 1;
        JTable aliasTable = new JTable(21, 1);
        aliasTable.setTableHeader(null);
        aliasTable.setValueAt(alias, 0, 0);
        for (int row = 1; row < numRows; row++) {
            aliasTable.setValueAt(user.getRandomAlias(row - 1), row, 0);
        }
        aliasTable.setMaximumSize(new Dimension(700, 99999));
        randomAliasesLabel.setLabelFor(aliasTable);
        form.add(randomAliasesLabel);
        form.add(limit);
        form.add(aliasTable);


        //testing, will launch SearchResults with dummy data
        JButton example = new JButton("Example button");
        example.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSubject().setData();
            }
        });
        example.setPreferredSize(new Dimension(100, 50));
        getPanel().add(example, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
