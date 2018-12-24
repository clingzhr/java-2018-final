package Save;

import Battle.Battlefield;

public class SAVE_init { //保存的初始化的战场
    private int[][]field = new int[10][20];

    public SAVE_init(int [][]init){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++){
                field[i][j] = init[i][j];
            }
        }
    }
    public int[][] getField()
    {
        return this.field;
    }
}
