/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopacman;

import static oopacman.OOPacman.*;

/**
 *
 * @author Alexylva
 */
public abstract class Entity implements EntityObject {

    private int x, y, size;
    private String tipo;
    private final int maxX = (int) Math.round(gameAreaObject.getWidth());
    private final int maxY = (int) Math.round(gameAreaObject.getHeight());
    

    public Entity(int x, int y, String tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = constrain(x,0, maxX-getSize());
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = constrain(y,0, maxY-getSize());
    }
    
    public int getSize() {
        return this.size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }

    public String getTipo() {
        return tipo;
    }

    private int constrain(int i, int min, int max) {
        if (i >= min) {
            if (i <= max) {
                return i;
            } else {
                return max;
            }
        } else {
            return min;
        }
    }
}
