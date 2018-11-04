public class MatrixEntry {
    private int row, col, data;
    private MatrixEntry nextCol;
    private MatrixEntry nextRow;
    public MatrixEntry(int row, int col, int data){
        this.row = row;
        this.col = col;
        this.data = data;
    }
    public int getColumn(){ return col; }
    public void setColumn(int col){ this.col = col; }
    public int getRow(){ return row; }
    public void setRow(int row){ this.row = row; }
    public int getData(){ return data; }
    public void setData(int data){ this.data = data;}

    public void setNextRow(MatrixEntry el){
        if (el.getColumn() != this.getColumn())
            System.out.println("Not the next row");
        else
            nextRow = el;
    }

    public MatrixEntry getNextRow(){
        if (this.nextRow.getColumn() == this.getColumn())
            return nextRow;
        else {
            System.out.println("not in the same column");
            return null;
        }
    }

    public void setNextCol(MatrixEntry el){
        if (el.getRow() != this.getRow())
            System.out.println("Not the next col");
        else
            nextCol = el;
    }

    public MatrixEntry getNextCol(){
        if (this.nextCol.getRow() == this.getRow())
            return nextCol;
        else {
            System.out.println("not in the same row");
            return null;
        }
    }

}
