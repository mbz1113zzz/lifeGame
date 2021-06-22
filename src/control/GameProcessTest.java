package control;

import model.CellLifeWorld;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameProcessTest {

    GameProcess GameProcess0;
    GameProcess GameProcess1;
    @Before
    public void setUp() throws Exception {
        boolean user0[][] = new boolean[][]{{true,true,false},
                {true,false,false},
                {false,false,false}};
        GameProcess0 = new GameProcess(3,3,user0);

        boolean user1[][] = new boolean[][]{{true,true,false},
                {true,true,false},
                {false,false,false}};
        GameProcess1 = new GameProcess(3,3,user1);
        System.out.println("测试开始");
    }

    @After
    public void tearDown() throws Exception {

        System.out.println("测试结束");

    }

    @Test
    public void testcellMultiply() {
        GameProcess0.cellMultiply();
        int [][] User01 = new int[][]{
                {1,1,0},
                {1,1,0},
                {0,0,0}
        };
        assertArrayEquals(GameProcess0.getAllCellStatus(),User01);

        GameProcess1.cellMultiply();
        int [][] User11 = new int[][]{
                {1,1,0},
                {1,1,0},
                {0,0,0}
        };
        assertArrayEquals(GameProcess1.getAllCellStatus(),User11);
    }
    @Test
    public void testcellSavePrinciple(){

        assertEquals(GameProcess.cellSavePrinciple(true,2),true);
        assertEquals(GameProcess.cellSavePrinciple(false,2),false);
        assertEquals(GameProcess.cellSavePrinciple(true,4),false);
        assertEquals(GameProcess.cellSavePrinciple(false,4),false);
        assertEquals(GameProcess.cellSavePrinciple(true,3),true);
        assertEquals(GameProcess.cellSavePrinciple(false,3),true);
    }

    @Test
    public void testgetAllCellStatus() {
        int [][] User00 = new int[][]{
                {1,1,0},
                {1,0,0},
                {0,0,0}
        };
        assertArrayEquals(GameProcess0.getAllCellStatus(),User00);

        int [][] User10 = new int[][]{
                {1,1,0},
                {1,1,0},
                {0,0,0}
        };
        assertArrayEquals(GameProcess1.getAllCellStatus(),User10);


    }
}