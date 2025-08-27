package vn.io.nghlong3004.repository;

import vn.io.nghlong3004.model.Message;
import vn.io.nghlong3004.util.DatabaseUtil;

public class ConsumerRepository {

  private final DatabaseUtil databaseUtil;

  public static ConsumerRepository instance;

  public static final ConsumerRepository getInstance(DatabaseUtil databaseUtil) {
    if (instance == null) {
      instance = new ConsumerRepository(databaseUtil);
    }
    return instance;
  }

  private ConsumerRepository(DatabaseUtil databaseUtil) {
    this.databaseUtil = databaseUtil;
  }

  public void insert(Message message) {
    String query =
        "INSERT INTO message(pub_id, sub_id, message_pub, name_pub, message_sub, name_sub, created) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    databaseUtil.execute(query, message.getPubId(), message.getSubId(), message.getMessagePub(),
        message.getNamePub(), message.getMessageSub(), message.getNameSub(), message.getCreated());
  }
}
