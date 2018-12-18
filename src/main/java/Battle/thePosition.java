package Battle;

import Creatures.Creature;
import javafx.scene.canvas.Canvas;

import static java.lang.System.exit;

public class thePosition
{
    private Creature creature;
    private int i;
    private int j;
    private boolean empty;

    thePosition(int i,int j) {
        this.i = i;
        this.j = j;
        this.empty = true;
        creature = null;
    }

    //初始化
    public void getCre(Creature temp) {
        creature = temp;
        this.empty = false;
        temp.changeTheposition(i,j);
    }

    //战场移动
    public void movCre(thePosition temp) {
        //生物改变
        creature = temp.creature;
        creature.changeTheposition(i,j);
        empty = false;
        temp.creature = null;
        temp.empty = true;
    }

    //战场交换
    public void swapCre(thePosition temp) {
        Creature tempswap = temp.creature;
        temp.creature = creature;
        creature = tempswap;
        creature.changeTheposition(i,j);
        temp.creature.changeTheposition(temp.i,temp.j);
    }

    public boolean isEmpty() {
        return empty;
    }

    public void showThecreature(Canvas canvas) {
        if(empty != true) {
            //creature.getTheinfo();
            if (canvas != null)
                creature.show_GUI(canvas);
            else {
                System.out.println("why no canvas?");
                exit(-1);
            }
        }
    }

    public Creature retCreature(){
        return this.creature; //返回当前的creature
    }

    public void clearPos() {
        this.creature = null;
        this.empty = true;
    }
}