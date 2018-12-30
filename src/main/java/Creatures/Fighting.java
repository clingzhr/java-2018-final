package Creatures;

import Battle.Battlefield;
import javafx.scene.canvas.Canvas;

interface Fighting {
    public void attackEnemy(Creature enemy, Canvas canvas);
    public void usingSkill(Battlefield ground,Canvas canvas); //使用技能,非限定技能
}
