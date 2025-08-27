package vn.io.nghlong3004;

import vn.io.nghlong3004.model.Message;
import vn.io.nghlong3004.model.MessageQueue;
import vn.io.nghlong3004.service.ConsumerService;
import vn.io.nghlong3004.service.ProducerService;
import vn.io.nghlong3004.service.ThreadService;

public class DistributedSystemApplication {

  public static void main(String[] args) throws InterruptedException {
    MessageQueue<Message> messageQueue = new MessageQueue<Message>(5);
    ThreadService threadService = new ThreadService();
    threadService.runThread(new ProducerService(messageQueue), 3, "Producer");
    threadService.runThread(new ConsumerService(messageQueue), 5, "Consumer");
  }

}
