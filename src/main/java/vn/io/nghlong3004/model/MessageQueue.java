package vn.io.nghlong3004.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {

  private final BlockingQueue<MyPair> blockingQueue;

  private static MessageQueue instance;

  public static synchronized MessageQueue getInstance() {
    if (instance == null) {
      instance = new MessageQueue();
    }
    return instance;
  }

  private MessageQueue() {
    blockingQueue = new LinkedBlockingQueue<>(5);
  }

  public BlockingQueue<MyPair> getBlockingQueue() {
    return this.blockingQueue;
  }

}
