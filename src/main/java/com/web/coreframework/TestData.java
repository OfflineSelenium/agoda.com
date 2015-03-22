package com.web.coreframework;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class TestData {

    private Properties props;

    /**
     * Load the default properties file
     */
    public void load(String fileName) throws IOException {
        props = new Properties();
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);

        if (inputStream != null) {
            try {
                props.load(inputStream);
                inputStream.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get a property from the list
     */
    public String getProperty(String key) {
        return props.getProperty(key);
    }

}
