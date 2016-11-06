package io.github.mezk.dminer.gui;

import io.github.mezk.dminer.gui.views.InitialPanel;
import io.github.mezk.dminer.gui.views.MenuBar;
import io.github.mezk.dminer.utils.LocalizedMessage;

import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Entry point for starting the checkstyle GUI.
 *
 * @author Vladislav Lisetskiy
 */
public final class Main {

    /** Frame's initial X coordinate. */
    private static final int FRAME_X = 100;

    /** Frame's initial Y coordinate. */
    private static final int FRAME_Y = 100;

    /** Frame's initial width. */
    private static final int FRAME_WIDTH = 910;

    /** Frame's initial X height. */
    private static final int FRAME_HEIGHT = 590;

    /** Main frame. */
    private static JFrame frame;

    /** Hidden constructor of the current utility class. */
    private Main() {
        // no code
    }

    /**
     * Launch the application.
     * @param args arguments.
     */
    public static void main(String[] args) {
        final LocalizedMessage messages = new LocalizedMessage("messages");
        frame = new JFrame(messages.getMessage("gui.programName"));
        frame.setContentPane(new InitialPanel());
        frame.setJMenuBar(new MenuBar());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Locale.setDefault(new Locale("ru"));
                frame.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
                frame.setVisible(true);
            }
        });
    }

    /**
     * @return Main GUI's frame.
     */
    public static JFrame getFrame() {
        return frame;
    }
}
