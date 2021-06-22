package model;

import static org.junit.Assert.*;

public class CellsTest {

    Cells Cells1;
    //Cells Cells2;

    @org.junit.Before
    public void setUp() throws Exception {
        Cells1 = new Cells(true,false);
        System.out.println("测试开始");
    }

    @org.junit.After
    public void tearDown() throws Exception {
        System.out.println("测试结束");
    }


    @org.junit.Test
    public void testsetCellStatus() {
        Cells1.setCellStatus(false);
        assertEquals(Cells1.isCellStatus(),false);
    }

    @org.junit.Test
    public void testsetChangeStatus() {
        Cells1.setChangeStatus(true);
        assertEquals(Cells1.isChangeStatus(),true);
    }
}