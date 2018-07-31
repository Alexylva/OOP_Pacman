/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopacman;

import javafx.scene.canvas.GraphicsContext;

public interface GameObject {

    public void render(GraphicsContext graphics); //Logica de desenho na tela

    public void update(GraphicsContext graphics); //Logica de atualização
}
