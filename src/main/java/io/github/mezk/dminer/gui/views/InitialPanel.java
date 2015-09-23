package io.github.mezk.dminer.gui.views;

import io.github.mezk.dminer.utils.LocalizedMessage;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class InitialPanel extends JPanel {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8753218658467586411L;

    private static final int EMPTY_BORDER = 5;

    public InitialPanel() {
        LocalizedMessage messages = new LocalizedMessage("messages");
        setBorder(new EmptyBorder(EMPTY_BORDER, EMPTY_BORDER, EMPTY_BORDER, EMPTY_BORDER));
        setLayout(new BorderLayout(0, 0));
        final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab(messages.getMessage("gui.tabbedPane.ols"), new OlsPanel());
        tabbedPane.addTab(messages.getMessage("gui.tabbedPane.simplex"), new SimplexPanel());
        add(tabbedPane);
    }
}
