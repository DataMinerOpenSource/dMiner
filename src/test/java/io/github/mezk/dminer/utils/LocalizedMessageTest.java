package io.github.mezk.dminer.utils;

import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class LocalizedMessageTest {

    @After
    public void clearCache() {
        LocalizedMessage.clearCache();
    }

    @Test
    public void testGeMessage() {
        final LocalizedMessage message = new LocalizedMessage(
            "messages", "gui.programName", null, this.getClass(), null);
        Assert.assertEquals("dMiner", message.getMessage());
    }

    @Test
    public void testGeMessageByKey() {
        final LocalizedMessage message = new LocalizedMessage("messages");
        Assert.assertEquals("dMiner", message.getMessage("gui.programName"));
    }

    @Test
    public void testGetKey() {
        final LocalizedMessage message = new LocalizedMessage(
            "messages", "gui.programName");
        Assert.assertEquals("gui.programName", message.getKey());
    }

    @Test
    public void testSetLocale() {
        final LocalizedMessage message = new LocalizedMessage(
            "messages", "gui.programName", this.getClass());
        message.setLocale(new Locale("en"));
        Assert.assertEquals("dMiner", message.getMessage());
    }

    @Test
    public void testGetSourceName() {
        final LocalizedMessage message = new LocalizedMessage(
            "messages", "gui.programName", null, this.getClass());
        Assert.assertEquals("io.github.mezk.dminer.utils.LocalizedMessageTest",
            message.getSourceName());
    }

}
