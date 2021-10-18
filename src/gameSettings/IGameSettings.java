package gameSettings;

public interface IGameSettings {
  int WIDTH = 1200;
  int HEIGHT = 600;
  int NUM_BLOCKS = 200;
  int BLOCK_WIDTH = (2 * WIDTH) / (3 * NUM_BLOCKS);
  long TICK_RATE = 1;
}
