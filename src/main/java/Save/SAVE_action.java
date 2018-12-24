package Save;

import Creatures.Creature;

public class SAVE_action { //保存战斗的动作
    int attack_or_move;
    int[] start_pos;
    int[] end_pos; //留作攻击，每一个Creature进行操作;
    public SAVE_action(int t, int[] start_pos, int[] end_pos){
        this.attack_or_move = t; //是攻击还是移动
        this.start_pos = start_pos;
        this.end_pos = end_pos;
    }
    public int getAttack_or_move()
    {
        return this.attack_or_move;
    }
    public int getXStart(){
        return start_pos[0];
    }
    public int getYStart(){
        return start_pos[1];
    }
    public int getXEnd() {
        return end_pos[0];
    }
    public int getYEnd(){
        return end_pos[1];
    }
}
