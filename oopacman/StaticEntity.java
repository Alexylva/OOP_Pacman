/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopacman;

public abstract class StaticEntity implements GameObject {
    private int x, y, size;
    private String tipo;
    
    public StaticEntity(int x, int y, String tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return size;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getTipo() {
        return tipo;
    }
}
