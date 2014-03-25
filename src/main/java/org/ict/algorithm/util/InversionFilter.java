package org.ict.algorithm.util;
/**
 * This class is used to probe sequence in String based on inversion principle of linear algebra.
 */
public class InversionFilter {
  /**
   * The default probe length of sequence.
   *
   * @serial
   */
  private int probeLen = 6;
  
  private int inversionCount = 0;

  private int sameOrderCount = 0;

  public InversionFilter() {}

  public InversionFilter(int probeLen) {
    super();
    this.probeLen = probeLen;
  }

  private void mergeArray(int source[], int start, int mid, int end, int temp[]) {
    int i = start;
    int j = mid + 1;
    int m = mid;
    int n = end;
    int k = 0;

    while (i <= m && j <= n) {
      //System.out.println("source[" + i + "]:"+source[i]+" , "+ "source[" + j + "]:"+source[j]);
      if (source[i] <= source[j]) {
        temp[k++] = source[i++];
      } else {
        temp[k++] = source[j++];
        inversionCount += (m -i + 1);
      }
    }

    while (i <= m) {
      temp[k++] = source[i++];
    }
    while (j <= n) {
      temp[k++] = source[j++];
    }
    for (int l = 0; l < k; l++) {
      source[start+l] = temp[l];
    }
  }

  private void mergeSort(int source[], int start, int end, int temp[]) {
    if (start < end) {
      int mid = (start + end)/2;
      //System.out.println("start:" + start + "mid:" + mid + "end:" + end);
      mergeSort(source,start,mid,temp);
      mergeSort(source,mid+1,end,temp);
      mergeArray(source,start,mid,end,temp);
    }
  }

  public boolean mergeSortInversion(int source[], int length) {
    int[] temp = new int[length];
    if (temp == null) {
      return false;
    }
    mergeSort(source,0,length-1,temp);
    return true;
  }

  public boolean mergeSortInversion(int source[], int start, int end) {
    int[] temp = new int[end - start + 1];
    if (temp == null) {
      return false;
    }
    mergeSort(source,start,end,temp);
    return true;
  }

  public void setProbeLen(int probeLen) {
    this.probeLen = probeLen;
  }

  public int getProbeLen() {
    return this.probeLen;
  }

  public void setInversionCount(int inversionCount) {
    this.inversionCount = inversionCount;
  }

  public int getInversionCount() {
    return this.inversionCount;
  }
  
  public void setSameOrderCount(int sameOrderCount) {
    this.sameOrderCount = sameOrderCount;
  }

  public int getSameOrderCount() {
    return this.sameOrderCount;
  }

  /**
   * inversion count:3
   * inversion count:7
   * inversion count:11
   * inversion count:13
   * if inversion count equals zero or equals (step)(step-1)/2,it illustrate the illegal sequence.
   */
  public static void main(String[] args) {
    String phoneImei = "A000002C8F7462";
    System.out.println(phoneImei);
    int[] numberArr = new int[phoneImei.length()];
    for (int j = 0; j < phoneImei.length(); j++) {
      char c = phoneImei.charAt(j);
      int x = (int)c;
      numberArr[j] = x;
      System.out.print(x + ",");
    }
    System.out.println("\n");

    boolean hasContinuous = false;
    int step = 6;
    int maxInversion = (step * (step - 1))/2;
    int[] test = numberArr;//{2,1,8,3,4,9,5,12,11,1,1,3,2,1,1};
    InversionFilter iv = new InversionFilter(6);
    int lastCount = 0;
    for (int i = 0; (step - 1 + i) < test.length; i++) {
      iv.mergeSortInversion(test,i,step-1+i);
      int count = iv.getInversionCount();
      System.out.println("from " + i +":"+test[i] +" to "+ (step-1+i)+":"+test[step-1+i] +",inversion count:" + count);
      //if (count == 0 || count == maxInversion) {
      //  System.out.println("The input sequence has too much inversion numbers.");
      //  hasContinuous = true;
      //  break;
      //}
      iv.setInversionCount(0);
    }
  }

}
