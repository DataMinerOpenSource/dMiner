package io.github.mezk.dminer.gui.views;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import io.github.mezk.dminer.gui.Main;
import io.github.mezk.dminer.gui.controllers.OlsController;

/**
 * Menu bar for the dMiner.
 * @author Vladislav Lisetskiy
 */
public class MenuBar extends JMenuBar {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7886301116245206525L;

    /**
     * Create the Menu bar.
     */
    public MenuBar() {
        final JMenu menuFile = new JMenu("File");
        add(menuFile);

        final JMenuItem openMenuItem = new JMenuItem("Open...");
        openMenuItem.addActionListener(new FileSelectionAction());
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        menuFile.add(openMenuItem);

        final JMenu editMenu = new JMenu("Edit");
        add(editMenu);

        final JMenu editOls = new JMenu("OLS");
        editMenu.add(editOls);

        final JMenuItem addRow = new JMenuItem("Add row");
        addRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOlsPanelFromContentPane().addRow();
            }
        });
        addRow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        editOls.add(addRow);

        final JMenuItem removeRow = new JMenuItem("Remove row");
        removeRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOlsPanelFromContentPane().removeRow();
            }
        });
        removeRow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        editOls.add(removeRow);

        final JMenuItem clearPanel = new JMenuItem("Clear panel");
        clearPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOlsPanelFromContentPane().cleanPanel();
            }
        });
        clearPanel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        editOls.add(clearPanel);
    }

    /**
     * Getter for the OlsPanel from the content pane.
     * @return OlsPanel from the content pane.
     */
    private OlsPanel getOlsPanelFromContentPane() {
        final Container contentPane = Main.getFrame().getContentPane();
        final JTabbedPane tabbedPane = (JTabbedPane) contentPane.getComponent(0);
        final OlsPanel olsPanel = (OlsPanel) tabbedPane.getComponentAt(0);
        return olsPanel;
    }

    /**
     * Handler for file selection action events.
     */
    private class FileSelectionAction extends AbstractAction {
        /**
         * Serial ID.
         */
        private static final long serialVersionUID = -1926935338069418119L;

        /** Default constructor to setup current action. */
        FileSelectionAction() {
            super("Select Java File");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            final FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fc.addChoosableFileFilter(filter);

            final OlsPanel olsPanel = getOlsPanelFromContentPane();
            fc.showDialog(olsPanel, "Open");
            final File file = fc.getSelectedFile();
            OlsController.openFile(file, olsPanel);
        }
    }
}
