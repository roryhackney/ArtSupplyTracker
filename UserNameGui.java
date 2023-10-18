import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

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
        final int FORM_WIDTH_MIN = 400;
        final int INPUT_HEIGHT_MAX = 20;

        //prevent window from resizes smaller than the form
        setMinimumSize(new Dimension(FORM_WIDTH_MIN + 20, 600));

        //creates a welcome message and default user if none exists
        JLabel welcomeMessage;
        if (user == null) {
            //creates a default user with random aliases if no user exists yet
            user = new User();
            welcomeMessage = new JLabel(user.randomWelcomeMessage(false));
        } else {
            welcomeMessage = new JLabel(user.randomWelcomeMessage(true));
        }
        //center message
        welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
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
        JTextField alias = new JTextField();
        //textField should be no wider than form, no stretching vertically
        alias.setMaximumSize(new Dimension(FORM_WIDTH_MAX, INPUT_HEIGHT_MAX));
        aliasLabel.setLabelFor(alias);
        //when you click the label it focuses the input box
        GuiHelpers.makeLabelFocusComponent(aliasLabel, alias);
        form.add(aliasLabel);
        form.add(alias);

        //form: set random boolean
        JCheckBox random = new JCheckBox("Would you like to use random names?", true);
        form.add(random);

        //form: editable table of random aliases (used only if random is true)
        JLabel randomAliasesLabel = new JLabel("What random names would you like to use?");
        JLabel limit = new JLabel(String.format("(up to %s names, %s characters each)", User.ALIASES_LIMIT, User.LENGTH_LIMIT));
        //TODO: add clear all button to table
        //TODO: add reset button to table (uses suggestions)
        DefaultTableModel model = new DefaultTableModel(new Object[] {"Aliases", " "}, User.ALIASES_LIMIT);
        JTable aliasTable = new JTable(model);
        aliasTable.setPreferredSize(new Dimension(FORM_WIDTH_MAX - 20, 0));

        //fill rows with user's saved aliases, empty rows at bottom if space remains
        int numRows = user.numRandomAliases();
        for (int row = 0; row < numRows; row++) {
            aliasTable.setValueAt(user.getRandomAliasAt(row), row, 0);
        }

        //add a clear button for each row in the second column
        TableColumn clearColumn = aliasTable.getColumn(" ");
        clearColumn.setCellRenderer(new TableColumnClearButtons(aliasTable));
        clearColumn.setCellEditor(new TableColumnClearButtons(aliasTable));

        //column sizes
        clearColumn.setPreferredWidth((int)(FORM_WIDTH_MAX * 0.25));
        aliasTable.getColumn("Aliases").setPreferredWidth((int)(FORM_WIDTH_MAX * 0.75));

        //add them to the form
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

        //never mind i don't want the main alias duplicated in the table thx though
//        //if user enters main alias, when they click away add it to the random names table
//        alias.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusLost(FocusEvent e) {
//                super.focusLost(e);
//                aliasTable.setValueAt(alias.getText(), 0, 0);
//            }
//        });
//        //also when they press enter
//        alias.addActionListener(e -> aliasTable.setValueAt(alias.getText(), 0, 0));


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


        //copy user for submit listener
        final User finalUser = user;

        //submit button validates data, if valid sends data to subject
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSubject().setData();

                //get all the aliases from the table, removing duplicates, blanks, same as alias. redisplay
                int aliasesLimit = User.ALIASES_LIMIT;
                String[] tableAliases = new String[aliasesLimit];
                for (int row = 0; row < aliasesLimit; row++) {
                    tableAliases[row] = (String) aliasTable.getValueAt(row, 0);
                }
                //if there were no valid aliases at all, clear left column
                if (! finalUser.replaceRandomAliases(tableAliases)) {
                    //TODO: clear column 0
                    int x = 5;
                }
                //if random is false and alias is blank, warn and retry
                //if table empty and alias is blank, warn and retry
                //if random is false and table has data not same as user randomAliases, warn of loss of aliases
                //overwrite alias
                //overwrite random
                //if random, replace user randomAliases pool
            }
        });
        //center window on screen
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
