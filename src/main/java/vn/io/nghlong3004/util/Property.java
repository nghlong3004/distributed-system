package vn.io.nghlong3004.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Property {

  private static Logger log = LoggerFactory.getLogger(Property.class);
  private final Properties properties;

  private static Property instance;

  public static Property getInsance() {
    if (instance == null) {
      instance = new Property();
    }
    return instance;
  }

  private Property() {
    log.info("initilized proerty...");
    properties = new Properties();
    try (InputStream input = getClass().getResourceAsStream("/application.properties")) {
      properties.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int getThreadProducerSize() {
    return Integer.valueOf(getPropertyValue("thread.producer.size"));
  }

  public int getThreadProducerSleepMs() {
    return Integer.valueOf(getPropertyValue("thread.producer.sleep-ms"));
  }

  public int getThreadConsumerSize() {
    return Integer.valueOf(getPropertyValue("thread.consumer.size"));
  }

  public int getThreadConsumerSleepMs() {
    return Integer.valueOf(getPropertyValue("thread.consumer.sleep-ms"));
  }

  public int getSizeQueue() {
    return Integer.valueOf(getPropertyValue("queue.size"));
  }

  public String getDatasourceUrl() {
    return getPropertyValue("datasource.url");
  }

  public String getDatasourceUsername() {
    return getPropertyValue("datasource.username");
  }

  public String getDatasourcePassword() {
    return getPropertyValue("datasource.password");
  }

  public String getDatasourceDriverClassName() {
    return getPropertyValue("datasource.driver-class-name");
  }

  private String getPropertyValue(String property) {
    return properties.getProperty(property);
  }
}
