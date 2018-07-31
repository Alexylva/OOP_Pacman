package oopacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Path extends StaticEntity {

    private boolean capturado = false;

    public Path(int x, int y) {
        super(x, y, "Path");
        setSize((int) Math.floor(OOPacman.gameAreaObject.getWidth()/15));
    }

    public void capturar(){
        this.capturado = true;
    }

    public boolean getCapturado(){
        return this.capturado;
    }

    @Override
    public void render(GraphicsContext gc, double time) {
        Color fill;
        if (capturado) { fill = new Color(0,0,0,1); }
        else { fill = new Color(1.0, 1.0, 0, 1); }
        gc.setFill(Color.BLACK);
        gc.setStroke(new Color(1,1,1,1));
        gc.fillRect(getX(), getY(), getSize(), getSize());
        gc.strokeRect(getX(), getY(), getSize(), getSize());
        gc.setFill(fill);
        gc.fillOval(getX() + 0.33*getSize(), getY() + 0.33*getSize(), getSize()*0.33, getSize()*0.33);
    }

    @Override
    public void update(GraphicsContext gc, double time) {
        //deve mudar caso this.capturado ser true
    }



}
