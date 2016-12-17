package org.ict.algorithm.leetcode;

public class StackData {
  public int start;

  public int pointer;

  public int size = 0;

  public int capacity;

  public StackData(int start, int capacity) {
    this.start = 0;
    this.capacity = 0;
  }

  public boolean isWithinStack(int index, int totalSize) {
    if (start <= index && index < (start + capacity)) {
        return true;
    } else if ((start + capacity) > totalSize && index < (start + capacity) % totalSize) {
        return true;
    }
    return false;
  }
}
