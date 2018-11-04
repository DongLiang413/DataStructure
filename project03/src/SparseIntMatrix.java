import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SparseIntMatrix {
    private int numRows, numCols;
    private MatrixEntry head;
    private String fileName = "NOT_DEFINED";
    private String[][] newMatrix;
    public SparseIntMatrix(int numRows, int numCols){
        this.numRows = numRows;
        this.numCols = numCols;
    }

    public SparseIntMatrix(int numRows, int numCols, String inputFile){
        this.numRows = numRows;
        this.numCols = numCols;
        this.fileName = inputFile;
        // TODO: set head according to different given files.
        this.head = null;
        // TODO: parse file line by line
    }

    public SparseIntMatrix(String inputFileName) {
        try {
            parseInputFile(inputFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseInputFile(String fileLocation) throws IOException {
        FileInputStream inputStream = new FileInputStream(fileLocation);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        int lineNum = 0;
        boolean modifiedColNum = false;
        String content = null;
        int colCounter = 0;
        while((content = bufferedReader.readLine()) != null) {
            // TODO: Implement any comprehensive logic here(like , split functionality)
            lineNum++;
            colCounter++;
            String[] info = content.split(",");
            if (lineNum == 1) {
                this.head = new MatrixEntry(Integer.parseInt(info[0]), Integer.parseInt(info[1]),
                        Integer.parseInt(info[2]));
            }

            if (!info[0].equals("0") && !modifiedColNum) {
                this.numCols = colCounter;
                modifiedColNum = true;
            }

            // ......

            System.out.println("#Line: " + lineNum + " is " + content);

        }

        this.numRows = lineNum / numCols;
        //close inputStream and bufferedReader
        inputStream.close();
        bufferedReader.close();
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
        return false;
    }

    public boolean minus(SparseIntMatrix otherMat){
        return false;
    }

}
