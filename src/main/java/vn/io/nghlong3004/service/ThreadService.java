package vn.io.nghlong3004.service;

public class ThreadService {

  public void runThread(Runnable runnable, int size, String name) {
    for (int i = 0; i < size; ++i) {
      Thread thread = new Thread(runnable);
      thread.setName(name + "-" + i);
      thread.start();
    }
  }

}
