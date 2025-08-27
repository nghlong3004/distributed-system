package vn.io.nghlong3004.util;

import vn.io.nghlong3004.repository.ConsumerRepository;

public class ObjectContainer {

  private static final Property PROPERTY = Property.getInsance();
  private static final DatabaseUtil DATABASE_UTIL = DatabaseUtil.getInstance(PROPERTY);
  private static final ConsumerRepository CONSUMER_REPOSITORY =
      ConsumerRepository.getInstance(DATABASE_UTIL);

  public static Property getProperty() {
    return PROPERTY;
  }

  public static DatabaseUtil getDatabaseUtil() {
    return DATABASE_UTIL;
  }

  public static ConsumerRepository getConsumerRepository() {
    return CONSUMER_REPOSITORY;
  }

}
