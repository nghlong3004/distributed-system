package vn.io.nghlong3004.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.io.nghlong3004.model.Message;
import vn.io.nghlong3004.model.MessageQueue;
import vn.io.nghlong3004.repository.ConsumerRepository;
import vn.io.nghlong3004.util.ObjectContainer;

public class ConsumerService implements Runnable {

  private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

  private final MessageQueue<Message> messageQueue;
  private final ConsumerRepository consumerRepository;

  public ConsumerService(MessageQueue<Message> messageQueue) {
    this.messageQueue = messageQueue;
    this.consumerRepository = ObjectContainer.getConsumerRepository();
  }

  @Override
  public void run() {

    while (true) {
      try {
        log.info("Thread: {} take message", Thread.currentThread().getName());
        Message message = messageQueue.take();
        log.info("Thread: {} insert message to database", Thread.currentThread().getName());
        this.consumerRepository.insert(message);
        Thread.sleep(ObjectContainer.getProperty().getThreadConsumerSleepMs());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

}
