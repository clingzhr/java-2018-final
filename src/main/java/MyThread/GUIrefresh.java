package MyThread;

import Battle.Battlefield;
import javafx.scene.canvas.Canvas;

public class GUIrefresh  extends Thread{
    private Canvas mycanvas;
    private Battlefield ground; //

    public  GUIrefresh(Canvas mycanvans,Battlefield ground)
    {
        this.mycanvas = mycanvans;
        this.ground = ground;
    }

    @Override
    public void run() {
        while(true) {
                this.mycanvas.getGraphicsContext2D().clearRect(0, 0, 1100, 600);
                synchronized (this.ground) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 20; j++) {
                            this.ground.showThecreature(i, j, this.mycanvas);
                        }
                        //System.out.println();
                    }
                }
                this.mycanvas.getGraphicsContext2D().save();
                try {
                        sleep(50);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
            }
    }
}
