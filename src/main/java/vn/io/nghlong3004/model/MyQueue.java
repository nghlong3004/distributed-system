package vn.io.nghlong3004.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyQueue implements Queue<MyPair> {

  private int size = 0;

  @Override
  public synchronized int size() {
    return size;
  }

  @Override
  public synchronized boolean isEmpty() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public synchronized boolean contains(Object o) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Iterator<MyPair> iterator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object[] toArray() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> T[] toArray(T[] a) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public synchronized boolean remove(Object o) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends MyPair> c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public synchronized void clear() {
    // TODO Auto-generated method stub

  }

  @Override
  public synchronized boolean add(MyPair e) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean offer(MyPair e) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public synchronized MyPair remove() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public synchronized MyPair poll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MyPair element() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public synchronized MyPair peek() {
    // TODO Auto-generated method stub
    return null;
  }

}
