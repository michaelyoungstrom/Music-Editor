package cs3500.music.view.gui;

import javax.swing.*;
import java.awt.*;

public class GridCellPanel extends JPanel {
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(Constants.GRID_CELL_SIZE, Constants.GRID_CELL_SIZE);
  }
}