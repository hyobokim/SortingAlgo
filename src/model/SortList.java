package model;

import gameSettings.IGameSettings;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 * Represents a list of numbers to be sorted
 */
public class SortList {

  private int numItems;
  private int max;
  private List<Integer> listOfNums;
  private JPanel panel;
  private int recentlyUpdated;
  private List<Integer> beingSearched;

  public SortList(int num, int max) {
    this.numItems = num;
    this.listOfNums = new ArrayList<>();
    this.max = max;
    this.beingSearched = new ArrayList<>();

    resetBoard();
  }

  public void resetBoard() {
    this.listOfNums = new ArrayList<>();

    Random rand = new Random();

    for (int i = 0; i < numItems; i++) {
      this.listOfNums.add(rand.nextInt(max - 49) + 50);
    }
  }

  public int getRecentlyUpdated() {
    return this.recentlyUpdated;
  }

  public List<Integer> getBeingSearched() {
    return this.beingSearched;
  }

  public void setViewer(JPanel view) {
    this.panel = view;
  }

  /**
   * Getter method to return the list of integers. Since Integers are immutable, a deep copy is not
   * required, and the actual list is returned, with the same order.
   *
   * @return the list of numbers at this moment
   */
  public List<Integer> getList() {
    return this.listOfNums;
  }

  public void selectionSort() {
    new SwingWorker() {
      @Override
      protected Object doInBackground() throws Exception {
        for (int index = 0; index < numItems; index++) {
          System.out.println(getList());
          int min = listOfNums.get(index);
          int minIndex = index;
          beingSearched.add(minIndex);

          for (int i = index; i < numItems; i++) {
            beingSearched.add(i);
            panel.repaint();

            try {
              Thread.sleep(IGameSettings.TICK_RATE);
            } catch (Exception e) {
            }

            if (listOfNums.get(i) < min) {
              min = listOfNums.get(i);
              beingSearched.remove(beingSearched.indexOf(minIndex));
              minIndex = i;
            } else {
              beingSearched.remove(beingSearched.indexOf(i));
            }

          }

          beingSearched = new ArrayList<>();

          listOfNums.remove(minIndex);
          listOfNums.add(index, min);

          recentlyUpdated = index;

          panel.repaint();
        }
        return null;
      }
    }.execute();
    this.recentlyUpdated = -1;
  }

  public void mergeSort(int start, int end) {
    new SwingWorker() {

      @Override
      protected Object doInBackground() throws Exception {
        int mid = (start + end) / 2;

        mergeSortHelper(start, mid);
        mergeSortHelper(mid + 1, end);

        merge(start, mid, end);

        return null;
      }
    }.execute();

    beingSearched = new ArrayList<>();
  }

  public void mergeSortHelper(int start, int end) {

    int mid = (start + end) / 2;

    if (start < end) {
      mergeSortHelper(start, mid);
      mergeSortHelper(mid + 1, end);

      merge(start, mid, end);
    }
  }

  public void merge(int start, int mid, int end) {
    List<Integer> newList = new ArrayList<>();

    int i = start, j = mid + 1;

    while (i <= mid && j <= end) {
      beingSearched = new ArrayList<>();
      beingSearched.add(i);
      beingSearched.add(j);
      panel.repaint();

      try {
        Thread.sleep(IGameSettings.TICK_RATE);
      } catch (Exception e) {
      }

      if (listOfNums.get(i) < listOfNums.get(j)) {
        newList.add(listOfNums.get(i));
        i++;
      } else {
        newList.add(listOfNums.get(j));
        j++;
      }
    }

    beingSearched = new ArrayList<>();
    panel.repaint();

    if (i <= mid) {
      newList.addAll(listOfNums.subList(i, mid + 1));
    } else if (j <= end) {
      newList.addAll(listOfNums.subList(j, end + 1));
    }

    // placing the new merged halves into the list
    for (int idx = start; idx <= end; idx++) {
      listOfNums.remove(idx);
      listOfNums.add(idx, newList.get(idx - start));
      recentlyUpdated = idx;
      panel.repaint();

      try {
        Thread.sleep(IGameSettings.TICK_RATE);
      } catch (Exception e) {
      }

      recentlyUpdated = -1;
      panel.repaint();
    }
  }
}
