package io.github.mezk.dminer.gui.views;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * Http://findbugs.sourceforge.net/bugDescriptions.html#SW_SWING_METHODS_INVOKED_IN_SWING_THREAD
 */
public class FrameShower implements Runnable {
    /** Frame. */
    private final Component parent;

    /** Message. */
    private final String msg;

    /** Message type. */
    private final int messageType;

    /**
     * @param parent Frame's component.
     * @param msg Message to show.
     */
    public FrameShower(Component parent, final String msg, int messageType) {
        this.parent = parent;
        this.msg = msg;
        this.messageType = messageType;
    }

    /** Display a frame. */
    @Override
    public void run() {
        JOptionPane.showMessageDialog(parent, msg, "", messageType);
    }
}
