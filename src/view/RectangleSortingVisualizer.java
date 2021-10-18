package view;

import controller.SortingAlgoController;
import gameSettings.IGameSettings;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.SortList;


public class RectangleSortingVisualizer extends JPanel implements SortingVisualizer {

  private SortingAlgoController control;
  private SortList model;
  private JFrame window;

  /**
   * Constructs a RectangleSortingVisualizer model
   *
   * @param control the control to handle user input
   * @param model   the model to handle sorting the lists
   */
  public RectangleSortingVisualizer(SortingAlgoController control, SortList model) {
    this.control = control;
    this.model = model;
    this.addKeyListener(control);
    this.setFocusable(true);

    this.window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.add(this);
    window.getContentPane().setPreferredSize(new Dimension(IGameSettings.WIDTH, IGameSettings.HEIGHT));
    window.pack();
    window.setVisible(true);

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.setBackground(Color.BLACK);
    int xPos = 0;

    for (int i = 0; i < model.getList().size(); i++) {
      if (i == model.getRecentlyUpdated()) {
        g.setColor(Color.GREEN);
      } else if (model.getBeingSearched().contains(i)) {
        g.setColor(Color.RED);
      } else {
        g.setColor(Color.BLUE);
      }

      g.fillRect(xPos, IGameSettings.HEIGHT - model.getList().get(i), IGameSettings.BLOCK_WIDTH, model.getList().get(i));
      xPos += IGameSettings.BLOCK_WIDTH * 3 / 2;
    }
  }
}
