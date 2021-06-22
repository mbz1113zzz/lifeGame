package model;

//import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellLifeWorldTest {

    CellLifeWorld CellLifeWorld1;
    CellLifeWorld CellLifeWorld2;
    boolean k;

    @Before
    public void setUp() throws Exception {
        boolean user[][] = new boolean[][]{{true,true,false},
                {true,false,false},
                {false,false,false}};
        CellLifeWorld1 = new CellLifeWorld(3,3,user);
        CellLifeWorld2 = new CellLifeWorld(3,3);
        System.out.println("娴嬭瘯寮�濮�");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("娴嬭瘯缁撴潫");
    }


    @Test
    public void testRandomCellsStatus() {

        CellLifeWorld2.RandomCellsStatus();
        for(int i=0 ; i< CellLifeWorld1.getRow() ;i++){
            for(int j=0 ; j< CellLifeWorld1.getCol() ;j++){
                if(CellLifeWorld2.getCellStatus(i,j)==true || CellLifeWorld2.getCellStatus(i,j)==false)
                     k = true;
                    assertEquals(k,true);
            }
        }
    }

    @Test
    public void testchangeCellStatus() {

        assertEquals(CellLifeWorld1.changeCellStatus(0,0), true);
        assertEquals(CellLifeWorld1.changeCellStatus(1,0), true);
        assertEquals(CellLifeWorld1.changeCellStatus(0,1), true);
        assertEquals(CellLifeWorld1.changeCellStatus(1,1), false);
    }

    @Test
    public void testgetCellStatus() {

        assertEquals(CellLifeWorld1.getCellStatus(0,0), true);
        assertEquals(CellLifeWorld1.getCellStatus(1,0), true);
        assertEquals(CellLifeWorld1.getCellStatus(0,1), true);
        assertEquals(CellLifeWorld1.getCellStatus(1,1), false);
    }

    @Test
    public void testgetNearbyCellNum() {

        assertEquals(CellLifeWorld1.getNearbyCellNum(-1,-1),0);
        assertEquals(CellLifeWorld1.getNearbyCellNum(4,4),0);
        assertEquals(CellLifeWorld1.getNearbyCellNum(0,0),2);
        assertEquals(CellLifeWorld1.getNearbyCellNum(2,2),0);
        assertEquals(CellLifeWorld1.getNearbyCellNum(1,1),3);

    }
}