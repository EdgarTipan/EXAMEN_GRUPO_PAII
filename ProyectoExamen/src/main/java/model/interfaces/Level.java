package model.interfaces;

import java.awt.*;

public interface Level {

    void setup();

    void draw(Graphics g, int width, int height);

    void update();

    boolean isLevelCompleted();

    void advanceToNextLevel();
}
