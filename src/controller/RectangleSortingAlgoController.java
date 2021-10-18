package controller;

import gameSettings.IGameSettings;
import java.awt.event.KeyEvent;
import model.SortList;
import view.RectangleSortingVisualizer;

public class RectangleSortingAlgoController implements SortingAlgoController {

  private SortList model;
  private RectangleSortingVisualizer view;

  /**
   * Constructs a SortingAlgoController object
   * @param model the model that handles sorting the list
   */
  public RectangleSortingAlgoController(SortList model) {
    this.model = model;
    this.view = new RectangleSortingVisualizer(this, model);
    this.model.setViewer(this.view);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch(e.getKeyChar()) {
      case 'q':
        model.selectionSort();
        view.repaint();
        break;
      case 'w':
        model.mergeSort(0, model.getList().size() - 1);
        break;
      case 'r':
        model.resetBoard();
        view.repaint();
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  public static void main(String[] args) {
    SortList model = new SortList(IGameSettings.NUM_BLOCKS , IGameSettings.HEIGHT);

    RectangleSortingAlgoController control = new RectangleSortingAlgoController(model);
  }
}
