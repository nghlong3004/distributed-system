package vn.io.nghlong3004.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageQueue<T> {

  private final Logger log = LoggerFactory.getLogger(MessageQueue.class);

  private final int MAX = 1 << 20;

  private int capacity;

  private Node<T> head;
  private Node<T> tail;

  private final int minCapacity;
  private final int maxCapacity;
  private final double growthFactor;
  private final int growThreshold;
  private final double lowWatermark;
  private final int shrinkThreshold;

  private int consecutiveFullHits = 0;
  private int consecutiveLowHits = 0;
  private int size = 0;

  public MessageQueue(int capacity) {
    log.info("Initial MessageQueue with capacity = {}", capacity);
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity > 0");
    }
    this.capacity = capacity;
    this.minCapacity = capacity;
    this.maxCapacity = Math.max(capacity * 5, MAX);
    this.growthFactor = 1.5;
    this.growThreshold = 4;
    this.lowWatermark = 0.25;
    this.shrinkThreshold = 5;

    head = null;
    tail = head;
  }

  public synchronized void put(T item) throws InterruptedException {
    log.info("Thread: {} join put", Thread.currentThread().getName());
    while (size == capacity) {
      if (tryGrowLocked())
        break;
      this.wait();
    }
    log.info("Thread: {} start put", Thread.currentThread().getName());
    consecutiveFullHits = 0;
    Node<T> node = new Node<>(item);
    if (head == null)
      head = tail = node;
    else {
      tail.next = node;
      tail = node;
    }
    size++;
    this.notify();
  }

  public synchronized T take() throws InterruptedException {
    log.info("Thread: {} join take", Thread.currentThread().getName());
    while (head == null) {
      this.wait();
    }
    log.info("Thread: {} start take", Thread.currentThread().getName());
    Node<T> node = head;
    head = head.next;
    if (head == null)
      tail = null;
    size--;
    T item = node.item;
    node.item = null;
    node.next = null;

    this.notify();

    tryShrinkLocked();
    return item;
  }

  public synchronized int size() {
    return size;
  }

  public synchronized int capacity() {
    return capacity;
  }

  private boolean tryGrowLocked() {
    if (capacity >= maxCapacity)
      return false;
    consecutiveFullHits++;
    if (consecutiveFullHits < growThreshold)
      return false;

    int current = capacity;
    int target = (int) Math.ceil(current * growthFactor);
    target = Math.min(target, maxCapacity);
    if (target <= current)
      return false;

    log.info("Thread: {} alter capacity = {}", Thread.currentThread().getName(), target);
    capacity = target;
    consecutiveFullHits = 0;
    consecutiveLowHits = 0;
    return true;
  }

  private void tryShrinkLocked() {
    if (size <= (int) Math.floor(capacity * lowWatermark)) {
      consecutiveLowHits++;
      if (consecutiveLowHits >= shrinkThreshold && capacity > minCapacity) {
        int target =
            Math.max(minCapacity, Math.max(size, (int) Math.ceil(capacity / growthFactor)));
        if (target < capacity) {
          log.info("Thread: {} alter capacity = {}", Thread.currentThread().getName(), target);
          capacity = target;
        }
        consecutiveLowHits = 0;
        consecutiveFullHits = 0;
      }
    } else {
      consecutiveLowHits = 0;
    }
  }


  private static final class Node<T> {
    T item;
    Node<T> next;

    Node(T item) {
      this.item = item;
    }
  }

}
