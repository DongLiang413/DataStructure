public class CollisionLogger {
    /* add data members here */
    
    public CollisionLogger(int screenWidth, int screenHeight, int bucketWidth) {
    	/* constructor code here */
    }

     /**
     * This method records the collision event between two balls. Specifically, in increments the bucket corresponding to
     * their x and y positions to reflect that a collision occurred in that bucket.
     */
    public void collide(Ball one, Ball two) {
    	/* update data members to reflect the collision event between ball "one" and ball "two" */

    }

    /**
     * Returns the heat map scaled such that the bucket with the largest number of collisions has value 255,
     * and buckets without any collisions have value 0.
     */
    public int[][] getNormalizedHeatMap() {
    	
        int[][] normalizedHeatMap = new int[1][1]; //NOTE-- these dimensions need to be updated properly!!
    	/* implement your code to produce a normalized heat map of type int[][] here */

        return normalizedHeatMap;
    }
}
