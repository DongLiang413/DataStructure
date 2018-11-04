import org.junit.*;
import static org.junit.Assert.*;

public class MatrixEntryTest {
    private MatrixEntry matrix;
    private final int row =1;
    private final int col=1;
    private final int data=4;

    @Before
    public void setUp(){
        matrix= new MatrixEntry(row,col,data);
    }

    @Test
    public void testsetrow(){
        matrix.setRow(2);
        assertEquals(2,matrix.getRow());
    }

    @Test
    public void testsetcolumn(){
        matrix.setColumn(3);
        assertEquals(3,matrix.getColumn());
    }

    @Test
    public void testsetdata(){
        matrix.setData(3);
    }

    @Test
    public void testsetnextcolumn(){
        MatrixEntry matrix1=new MatrixEntry(1,2,3);
        matrix.setNextCol(matrix1);
    }

    @Test
    public void testgetnextcolumn(){
        MatrixEntry matrix1=new MatrixEntry(1,2,3);
        assertEquals(1,matrix1.getRow());
    }

    @Test
    public void testsetnextrow(){
        MatrixEntry matrix2=new MatrixEntry(2,1,3);
        matrix.setNextRow(matrix2);
    }

    @Test
    public void testgetnextrow(){
        MatrixEntry matrix2=new MatrixEntry(2,1,3);
        assertEquals(matrix.getColumn(),matrix2.getColumn());
    }
}
