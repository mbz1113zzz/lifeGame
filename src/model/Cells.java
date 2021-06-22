package model;

public class Cells {
    private boolean CellStatus;
    private boolean IsChangeStatus;

    public Cells(){
        this.CellStatus = false;
        this.IsChangeStatus = false;
    }

    public Cells(boolean cellStatus, boolean isChangeStatus){
        this.CellStatus = cellStatus;
        this.IsChangeStatus = isChangeStatus;
    }

    public boolean isCellStatus() {
        return this.CellStatus;
    }

    public void setCellStatus(boolean cellStatus) {
        this.CellStatus = cellStatus;
    }

    public boolean isChangeStatus() {
        return this.IsChangeStatus;
    }

    public void setChangeStatus(boolean isChangeStatus) {
        this.IsChangeStatus = isChangeStatus;
    }


}
