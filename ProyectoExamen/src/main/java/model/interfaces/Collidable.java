package model.interfaces;

import java.awt.*;

public interface Collidable {

    Rectangle getBounds();

    void onCollision(Collidable other);
}
