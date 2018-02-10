package forkjoin;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractObjectPool<T> {
  protected final int min;
  protected final int max;
  protected final List<T> usings = new LinkedList<>();
  protected final List<T> buffer = new LinkedList<>();
  private volatile boolean inited = false;
  public AbstractObjectPool(int min, int max) {
    this.min = min;
    this.max = max;
    if (this.min < 0 || this.min > this.max) {
      throw new IllegalArgumentException(String.format(
          "need 0 <= min <= max <= Integer.MAX_VALUE, given min: %s, max: %s", this.min, this.max));
    }
  }
  public void init() {
    for (int i = 0; i < min; i++) {
      buffer.add(newObject());
    }
    inited = true;
  }
  protected void checkInited() {
    if (!inited) {
      throw new IllegalStateException("not inited");
    }
  }
  abstract protected T newObject();
  public synchronized T getObject() {
    checkInited();
    if (usings.size() == max) {
      return null;
    }
    if (buffer.size() == 0) {
      T newObj = newObject();
      usings.add(newObj);
      return newObj;
    }
    T oldObj = buffer.remove(0);
    usings.add(oldObj);
    return oldObj;
  }
  public synchronized void freeObject(T obj) {
    checkInited();
    if (!usings.contains(obj)) {
      throw new IllegalArgumentException(String.format("obj not in using queue: %s", obj));
    }
    usings.remove(usings.indexOf(obj));
    buffer.add(obj);
  }
}