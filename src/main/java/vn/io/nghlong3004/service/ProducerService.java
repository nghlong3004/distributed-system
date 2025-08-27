package vn.io.nghlong3004.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import vn.io.nghlong3004.model.Message;
import vn.io.nghlong3004.model.MessageQueue;

public class ProducerService implements Runnable {

  private final MessageQueue<Message> messageQueue;

  public ProducerService(MessageQueue<Message> messageQueue) {
    this.messageQueue = messageQueue;
  }

  @Override
  public void run() {
    while (true) {
      String messagePub = UUID.randomUUID().toString();
      String name = Thread.currentThread().getName();
      Timestamp created = Timestamp.from(Instant.now());
      Message message = new Message();
      message.setMessagePub(messagePub);
      message.setNamePub(name);
      message.setCreated(created);
      try {
        messageQueue.put(message);
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
