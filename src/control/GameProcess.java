package control;
import model.CellLifeWorld;

public class GameProcess {

    protected CellLifeWorld world;

    public GameProcess() {
        this.world = new CellLifeWorld(10, 10);
    }

    public GameProcess(int row, int col) {
        this.world = new CellLifeWorld(row, col);
    }
    public GameProcess(int row, int col,boolean user[][]) {
        this.world = new CellLifeWorld(row, col,user);
    }

    public void cellMultiply() {
        int i,j;

        for(i = 0; i < this.world.getRow(); ++i) {
            for(j = 0; j < this.world.getCol(); ++j) {
                int cellNum = this.world.getNearbyCellNum(i, j);
                if (this.world.getCellStatus(i, j) != this.cellSavePrinciple(this.world.getCellStatus(i, j), cellNum)) {
                    this.world.setChangeFlag(i, j);
                }
            }
        }

        for(i = 0; i < this.world.getRow(); ++i) {
            for(j = 0; j < this.world.getCol(); ++j) {
                if (this.world.getChangeStatus(i, j)) {
                    this.world.changeCellStatus(i, j);
                    this.world.cancelChangeFlag(i, j);
                }
            }
        }

    }

    public static boolean  cellSavePrinciple(boolean curStatus, int surCellNumber) {
        if (surCellNumber == 3) {
            return true;
        } else {
            return surCellNumber == 2 ? curStatus : false;
        }
    }

    public boolean changeCellStatus(int row, int col) {
        return this.world.changeCellStatus(row, col);
    }

    public void setDefaultWorld() {
        this.world.RandomCellsStatus();
    }

    public int[][] getAllCellStatus() {
        int width = this.world.getRow();
        int length = this.world.getCol();
        int[][] cellSaveRect = new int[width][length];

        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < length; ++j) {
                if (this.world.getCellStatus(i, j)) {
                    cellSaveRect[i][j] = 1;
                } else {
                    cellSaveRect[i][j] = 0;
                }
            }
        }

        return cellSaveRect;
    }
}
