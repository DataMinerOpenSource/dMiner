package io.github.mezk.dminer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

/**
 * Represents a message that can be localised. The translations come from
 * message.properties files. The underlying implementation uses
 * java.text.MessageFormat.
 *
 * @author Andrei Selkin
 */
public final class LocalizedMessage implements Serializable {
    /** Required for serialization. */
    private static final long serialVersionUID = 5675176836184862150L;

    /** The locale to localise messages to. **/
    private static Locale sLocale = Locale.getDefault();

    /**
     * A cache that maps bundle names to ResourceBundles.
     * Avoids repetitive calls to ResourceBundle.getBundle().
     */
    private static final Map<String, ResourceBundle> BUNDLE_CACHE =
        Collections.synchronizedMap(new HashMap<String, ResourceBundle>());

    /** Key for the message format. **/
    private final String key;

    /** Arguments for MessageFormat. **/
    private final Object[] args;

    /** Name of the resource bundle to get messages from. **/
    private final String bundle;

    /** Class of the source for this LocalizedMessage. */
    private final Class<?> sourceClass;

    /** A custom message overriding the default message from the bundle. */
    private final String customMessage;

    /**
     * Creates a new {@code LocalizedMessage} instance.
     *
     * @param bundle resource bundle name
     * @param key the key to locate the translation
     * @param args arguments for the translation
     * @param sourceClass the Class that is the source of the message
     * @param customMessage optional custom message overriding the default
     */
    public LocalizedMessage(String bundle,
                            String key,
                            Object[] args,
                            Class<?> sourceClass,
                            String customMessage) {
        this.key = key;

        if (args == null) {
            this.args = null;
        }
        else {
            this.args = Arrays.copyOf(args, args.length);
        }
        this.bundle = bundle;
        this.sourceClass = sourceClass;
        this.customMessage = customMessage;
    }

    /**
     * Creates a new {@code LocalizedMessage} instance.
     *
     * @param bundle resource bundle name
     * @param key the key to locate the translation
     * @param sourceClass the Class that is the source of the message
     */
    public LocalizedMessage(String bundle,
                            String key,
                            Class<?> sourceClass) {
        this(bundle, key, null, sourceClass, null);
    }

    /**
     * Creates a new {@code LocalizedMessage} instance.
     *
     * @param bundle resource bundle name
     * @param key the key to locate the translation
     */
    public LocalizedMessage(String bundle,
                            String key) {
        this(bundle, key, null, LocalizedMessage.class, null);
    }

    /**
     * Creates a new {@code LocalizedMessage} instance.
     *
     * @param bundle resource bundle name
     */
    public LocalizedMessage(String bundle) {
        this(bundle, "", null, LocalizedMessage.class, null);
    }

    /**
     * Creates a new {@code LocalizedMessage} instance.
     *
     * @param bundle resource bundle name
     * @param key the key to locate the translation
     * @param args arguments for the translation
     * @param sourceClass the Class that is the source of the message
     */
    public LocalizedMessage(String bundle,
                            String key,
                            Object[] args,
                            Class<?> sourceClass) {
        this(bundle, key, args, sourceClass, null);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final LocalizedMessage localizedMessage = (LocalizedMessage) object;
        return Objects.equals(key, localizedMessage.key)
            && Objects.equals(bundle, localizedMessage.bundle)
            && Objects.equals(sourceClass, localizedMessage.sourceClass)
            && Objects.equals(customMessage, localizedMessage.customMessage)
            && Arrays.equals(args, localizedMessage.args);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, bundle, sourceClass,
            customMessage, Arrays.hashCode(args));
    }

    /** Clears the cache. */
    public static void clearCache() {
        synchronized (BUNDLE_CACHE) {
            BUNDLE_CACHE.clear();
        }
    }

    /**
     * Gets the translated message.
     * @return the translated message
     */
    public String getMessage() {
        String message = getCustomMessage();

        if (message == null) {
            try {
                // Important to use the default class loader, and not the one in
                // the GlobalProperties object. This is because the class loader in
                // the GlobalProperties is specified by the user for resolving
                // custom classes.
                final ResourceBundle resourceBundle = getBundle(bundle);
                final String pattern = resourceBundle.getString(key);
                message = MessageFormat.format(pattern, args);
            }
            catch (final MissingResourceException ignored) {
                // If the Check author didn't provide i18n resource bundles
                // and logs error messages directly, this will return
                // the author's original message
                message = MessageFormat.format(key, args);
            }
        }
        return message;
    }

    /**
     * Gets the translated message.
     * @param messageKey message key.
     * @return the translated message
     */
    public String getMessage(String messageKey) {
        String message = getCustomMessage();

        if (message == null) {
            try {
                // Important to use the default class loader, and not the one in
                // the GlobalProperties object. This is because the class loader in
                // the GlobalProperties is specified by the user for resolving
                // custom classes.
                final ResourceBundle resourceBundle = getBundle(bundle);
                final String pattern = resourceBundle.getString(messageKey);
                message = MessageFormat.format(pattern, args);
            }
            catch (final MissingResourceException ignored) {
                // If the Check author didn't provide i18n resource bundles
                // and logs error messages directly, this will return
                // the author's original message
                message = MessageFormat.format(key, args);
            }
        }
        return message;
    }

    /**
     * Returns the formatted custom message if one is configured.
     * @return the formatted custom message or {@code null}
     *          if there is no custom message
     */
    private String getCustomMessage() {

        if (customMessage == null) {
            return null;
        }

        return MessageFormat.format(customMessage, args);
    }

    /**
     * Find a ResourceBundle for a given bundle name. Uses the classloader
     * of the class emitting this message, to be sure to get the correct
     * bundle.
     * @param bundleName the bundle name
     * @return a ResourceBundle
     */
    private ResourceBundle getBundle(String bundleName) {
        synchronized (BUNDLE_CACHE) {
            ResourceBundle resourceBundle = BUNDLE_CACHE
                .get(bundleName);
            if (resourceBundle == null) {
                resourceBundle = ResourceBundle.getBundle(bundleName, sLocale,
                    sourceClass.getClassLoader(), new UTF8Control());
                BUNDLE_CACHE.put(bundleName, resourceBundle);
            }
            return resourceBundle;
        }
    }

    /**
     * Returns the message key to locate the translation, can also be used
     * in IDE plugins to map error messages to corrective actions.
     *
     * @return the message key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets the name of the source for this LocalizedMessage.
     * @return the name of the source for this LocalizedMessage
     */
    public String getSourceName() {
        return sourceClass.getName();
    }

    /**
     * Sets a locale to use for localization.
     * @param locale the locale to use for localization
     */
    public static void setLocale(Locale locale) {
        if (Locale.ENGLISH.getLanguage().equals(locale.getLanguage())) {
            sLocale = Locale.ROOT;
        }
        else {
            sLocale = locale;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Interface Comparable methods
    ////////////////////////////////////////////////////////////////////////////

    /**
     * <p>
     * Custom ResourceBundle.Control implementation which allows explicitly read
     * the properties files as UTF-8
     * </p>
     *
     * @author <a href="mailto:nesterenko-aleksey@list.ru">Aleksey Nesterenko</a>
     */
    protected static class UTF8Control extends Control {
        @Override
        public ResourceBundle newBundle(String aBaseName, Locale aLocale, String aFormat,
                                        ClassLoader aLoader, boolean aReload) throws IOException {
            // The below is a copy of the default implementation.
            final String bundleName = toBundleName(aBaseName, aLocale);
            final String resourceName = toResourceName(bundleName, "properties");
            InputStream stream = null;
            if (aReload) {
                final URL url = aLoader.getResource(resourceName);
                if (url != null) {
                    final URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            }
            else {
                stream = aLoader.getResourceAsStream(resourceName);
            }
            ResourceBundle resourceBundle = null;
            if (stream != null) {
                final Reader streamReader = new InputStreamReader(stream, "UTF-8");
                try {
                    // Only this line is changed to make it to read properties files as UTF-8.
                    resourceBundle = new PropertyResourceBundle(streamReader);
                }
                finally {
                    stream.close();
                }
            }
            return resourceBundle;
        }
    }
}
