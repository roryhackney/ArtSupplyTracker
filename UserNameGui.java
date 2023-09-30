import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
        setTitle("Username Settings | Art Supply Manager");
        final int FORM_WIDTH_MAX = 700;
        final int INPUT_HEIGHT_MAX = 20;

        //prevent window from resizes smaller than the form
        setMinimumSize(new Dimension(400, 600));

        //random greeting will display random greeting + alias if user already exists
        String[] greetings = new String[] {"Welcome", "Hello", "Greetings", "Hi"};
        int index = (int) (Math.random() * (greetings.length));
        JLabel welcomeMessage = new HeadingLabel("");
        //creates a default user with random aliases if no user exists yet
        if (user == null) {
            welcomeMessage.setText(greetings[index] + "!");
            user = new User();
        } else {
            welcomeMessage.setText(greetings[index] + ", " + user.getAlias() + "!");
        }
        getPanel().add(welcomeMessage, BorderLayout.NORTH);

        //centers form horizontally between expandable whitespace
        JPanel spacing = new JPanel();
        spacing.setLayout(new BoxLayout(spacing, BoxLayout.LINE_AXIS));
        getPanel().add(spacing, BorderLayout.CENTER);
        spacing.add(Box.createHorizontalGlue());

        //form contains all inputs
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        form.setMaximumSize(new Dimension(FORM_WIDTH_MAX, 99999));
        spacing.add(form);
        spacing.add(Box.createHorizontalGlue());

        //form: set main alias
        JLabel aliasLabel = new JLabel("What would you like to be called? (optional)");
        JTextField alias = new JTextField(20);
        //textField should be no wider than form, no stretching vertically
        alias.setMaximumSize(new Dimension(FORM_WIDTH_MAX, INPUT_HEIGHT_MAX));
        aliasLabel.setLabelFor(alias);
        form.add(aliasLabel);
        form.add(alias);

        //form: set random boolean
        JCheckBox random = new JCheckBox("Would you like to use random names?", true);
        form.add(random);

        //form: editable table of random aliases (used only if random is true)
        JLabel randomAliasesLabel = new JLabel("What random names would you like to use?");
        JLabel limit = new JLabel(String.format("(up to %s names, %s characters each)", User.ALIASES_LIMIT, User.LENGTH_LIMIT));
        JTable aliasTable = new JTable(User.ALIASES_LIMIT + 1, 2);
        aliasTable.setTableHeader(null);
        //first row is main alias
        aliasTable.setValueAt(alias.getText(), 0, 0);
        //fill next rows with user's saved aliases, empty rows at bottom if space remains
        int numRows = user.numRandomAliases() + 1;
        for (int row = 1; row < numRows; row++) {
            aliasTable.setValueAt(user.getRandomAlias(row - 1), row, 0);
            //TODO: add clear button to second column for each alias
//            JButton button = new JButton("X");
//            button.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    aliasTable.setValueAt("", aliasTable.getSelectedRow(), 0);
//                }
//            });
        }
        randomAliasesLabel.setLabelFor(aliasTable);
        form.add(randomAliasesLabel);
        form.add(limit);
        form.add(aliasTable);



        //center submit button instead of stretching horizontally
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.PAGE_AXIS));
        getPanel().add(bottom, BorderLayout.SOUTH);

        JButton submit = new JButton("Submit");
        submit.setAlignmentX(Box.CENTER_ALIGNMENT);
        bottom.add(submit);

        //action listeners

        //if user enters main alias, when they click away add it to the random names table
        alias.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                aliasTable.setValueAt(alias.getText(), 0, 0);
            }
        });
        //also when they press enter
        alias.addActionListener(e -> aliasTable.setValueAt(alias.getText(), 0, 0));

        //TODO: if user changes first row, it should change alias also

        //if random is unchecked, disable table
        random.addActionListener(e -> {
                    if (random.isSelected()) {
                        aliasTable.setEnabled(true);
                        aliasTable.setBackground(Color.WHITE);
                    } else {
                        aliasTable.setEnabled(false);
                        aliasTable.setBackground(Color.LIGHT_GRAY);

                    }
                });

        //submit button sends data to subject
        submit.addActionListener(e -> getSubject().setData());

        //center window on screen
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
