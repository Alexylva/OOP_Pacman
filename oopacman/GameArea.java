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
public class GameArea implements GameObject {
    private final double width, height, x, y;
    
    public GameArea(double width, double height, double x, double y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(new Color(0.0, 0.0, 0.0, 1.0));
        gc.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void update(GraphicsContext gc) {}
    
}
