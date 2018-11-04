public class SparseIntMatrix {
    private int numRows, numCols;
    private String[][] newMatrix;
    public SparseIntMatrix(int numRows, int numCols){
        this.numRows = numRows;
        this.numCols = numCols;
    }
    public SparseIntMatrix(int numRows, int numCols, String inputFile){
        this.numRows = numRows;
        this.numCols = numCols;
        newMatrix = ；

    }

    public int getElement(int row, int col){
        if (row <= numRows && col <= numCols)
            return newMatrix(row,col).getData();
        else
            return 0;
    }

    public boolean setElement(int row, int col, int data){
        if (row <= numRows && col <= numCols){
            newMatrix(row,col) = data;
            return true;
        }
        else
            return false;
    }

    public boolean removeElement(int row, int col, int data){
        if (row > numRows || col > numCols || newMatrix(row,col) = null){
            return false;
        }
        else{
            newMatrix(row,col) = null;
            return true;
        }
    }

    public int getNumCols(){
        return newMatrix[1].length;
    }

    public int getNumRows(){
        return newMatrix.length;
    }

    public boolean plus(SparseIntMatrix otherMat){

    }

    public boolean minus(SparseIntMatrix otherMat){

    }

}
