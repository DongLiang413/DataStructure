
public class CollisionLogger {
    /* add data members here */
    private int Width;
    private int Height;
    private int bucket;
    private double avgX;
    private double avgY;
    private int[][] arr;


    public CollisionLogger(int screenWidth, int screenHeight, int bucketWidth) {
        //construct the class collisionlogger
        this.Width = screenWidth;
        this.Height = screenHeight;
        this.bucket = bucketWidth;
        arr = new int [this.Height / this.bucket][this.Width / this.bucket];
    }

    /**
     * This method records the collision event between two balls. Specifically, in increments the bucket corresponding to
     * their x and y positions to reflect that a collision occurred in that bucket.
     */
    public void collide(Ball one, Ball two) {
        //get the average position of ball one and ball two and change them into integer
        avgX = (one.getXPos() + two.getXPos()) / 2;
        avgY = (one.getYPos() + two.getYPos()) / 2;
        int bucketX = (int) avgX / this.bucket;
        int bucketY = (int) avgY / this.bucket;
        //check whether these two balls collide
        if (one.intersect(two)) {
            //if the average position of x and y is smaller than 0, set them into 0
            if (bucketX < 0) {
                bucketX = 0;
            }
            if (bucketY < 0) {
                bucketY = 0;
            }
            // if the average position of x and y is larger than the row and column value of arr, set them to the row and column of the arr and minus one
            if (bucketX > (this.Width / this.bucket) - 1) {
                bucketX = (this.Width / this.bucket) - 1;
            }
            if (bucketY > (this.Height / this.bucket) - 1) {
                bucketY = (this.Height / this.bucket) - 1;
            }
            // once intersect, bucket's value increase one
            arr[bucketY][bucketX] = arr[bucketY][bucketX] + 1;
        }
    }

    /**
     * Returns the heat map scaled such that the bucket with the largest number of collisions has value 255,
     * and buckets without any collisions have value 0.
     */
    public int[][] getNormalizedHeatMap() {

        int[][] normalizedHeatMap = arr; //NOTE-- these dimensions need to be updated properly!!
        /* implement your code to produce a normalized heat map of type int[][] here */
        int max = 1;
        int rescale;
        //find the max value in the arr
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (max < arr[i][j]) {
                    max = arr[i][j];
                }
            }
        }
        //rescale the arr to a normalized heat map
        for (int a = 0; a < arr.length; a++) {
            for (int b = 0; b < arr[a].length; b++) {
                rescale = arr[a][b] * 255;
                arr[a][b] = rescale / max;
            }
        }
        return normalizedHeatMap;
    }
}