package model;

import java.util.Random;

public class CellLifeWorld {
    private int Row;
    private int Col;
    private Cells[][] cells;

    public CellLifeWorld(int Row, int Col){
        this.Row = Row;
        this.Col = Col;
        this.cells = new Cells[Row][Col];

        for(int i = 0;i < Row;++i ){
            for(int j = 0;j < Col;++j){
                this.cells[i][j] = new Cells();
            }
        }
    }

    public CellLifeWorld(int Row , int Col,boolean user[][]){
        this.Row = Row;
        this.Col = Col;
        this.cells = new Cells[Row][Col];
        for(int i = 0;i < Row;++i ){
            for(int j = 0;j < Col;++j){
                this.cells[i][j] = new Cells();
            }
        }

        for(int i = 0;i < Row;i++ ){
            for(int j = 0;j < Col;j++){
                this.cells[i][j].setCellStatus(user[i][j]);
            }
        }
    }

    public int getRow() {
        return this.Row;
    }

    public int getCol() {
        return this.Col;
    }


    public void RandomCellsStatus() {
        for(int i = 0; i < this.Row; ++i) {
            for(int j = 0; j < this.Col; ++j) {
                int randomNum=(int) (Math.random()*10);
                /*int randomNum = new Random().nextInt(10);*/
                if (randomNum >= 7) {
                    this.cells[i][j].setCellStatus(true);
                } else {
                    this.cells[i][j].setCellStatus(false);
                }
                this.cells[i][j].setChangeStatus(false);
            }
        }
    }


    public boolean getCellStatus(int row, int col) {
        return row >= 0 && row <= this.Row - 1 && col >= 0 && col <= this.Col - 1 ? this.cells[row][col].isCellStatus() : false;
    }

    public boolean changeCellStatus(int row, int col){
        boolean beforeStatus = this.cells[row][col].isCellStatus();
        this.cells[row][col].setCellStatus(!beforeStatus);
        return beforeStatus;
    }

    public boolean getChangeStatus(int row, int col) {
        return this.cells[row][col].isChangeStatus();
    }

    public void setChangeFlag(int row, int col) {
        this.cells[row][col].setChangeStatus(true);
    }

    public void cancelChangeFlag(int row, int col) {
        this.cells[row][col].setChangeStatus(false);
    }

    public int getNearbyCellNum(int row, int col) {
        int count = 0;

        if ((row < 0 || row > this.getRow()) || (col < 0 || col > this.getCol())) {
            return 0;

        } else {
            for (int i = row - 1; i <= row + 1; ++i) {
                for (int j = col - 1; j <= col + 1; ++j) {

                    if ((i != row || j != col) && this.getCellStatus(i, j)) {
                        ++count;
                    }
                }
            }
        return count;
        }
    }
}