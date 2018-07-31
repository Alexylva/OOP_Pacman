package oopacman;

import java.io.File;
import java.io.InputStream;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UserInterface implements UIObject {

        private static int score = 0;
        private final double x, y;
        
        public UserInterface(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public int getScore() {
                return this.score;
        }
        
        public static void resetScore() {
                UserInterface.score = 0;
        }
        public static void addScore(int score) {
                UserInterface.score += score;
        }

        @Override
        public void render(GraphicsContext gc, double time ){
            gc.save();
            gc.translate(this.x,this.y);
            gc.setFill(new Color(1,1,1,1));
            gc.setFont(Font.font("Tahoma", 30));
            
            gc.fillText("SCORE\n"+getScore(), 30, 30);
            gc.restore();
            
        }


        public void update( GraphicsContext gc, double time ){

        }
}
