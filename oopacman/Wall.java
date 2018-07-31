/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Alexylva
 */
public class Wall extends StaticEntity {

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    private Color color = new Color(1.00, 1.00, 0.50, 1.0);
    public Wall(int x, int y) {
        super(x, y, "Wall");
        setSize((int) Math.floor(OOPacman.gameAreaObject.getWidth()/15));
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.setStroke(new Color(0,0,0,1));
        gc.fillRect(getX(), getY(), getSize(), getSize());
        gc.strokeRect(getX(), getY(), getSize(), getSize());
    }

    @Override
    public void update(GraphicsContext gc) {}
    
    
    
}
