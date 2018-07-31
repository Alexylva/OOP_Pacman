/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopacman;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Alexylva
 */
public interface GameObject {

    public void render(GraphicsContext gc);

    public void update(GraphicsContext gc);
}
