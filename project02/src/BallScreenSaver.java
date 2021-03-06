
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.GregorianCalendar;

public class BallScreenSaver extends AnimationFrame {

    private int numOfBalls;
    private CollisionLogger collisionLogger = new CollisionLogger(800,800,40);
    public final int BORDER = 30;
    private Ball[] ballArr;

    /* These are data members for managing the collision logging, which
       you should also use in your class. */
    private static final int COLLISION_BUCKET_WIDTH = 20;
    private int saveCounter=0;

    public BallScreenSaver(int w, int h, String name, int numOfBalls) {
        super(w, h, name);
        this.numOfBalls = numOfBalls;

        int i = 0;
        this.ballArr = new Ball[numOfBalls];
        while (i < numOfBalls) {
            ballArr[i] = generateBall();
            i++;
        }
        // set one ball with red color
        int j=randInt(0,ballArr.length-1);
        ballArr[j].setColor(Color.RED);
    }
    // generate a new ball according to the given input : numofballs
    public Ball generateBall() {
        Ball newBall = new Ball(randdouble(BORDER,getWidth()-BORDER), randdouble(BORDER,getWidth()-BORDER), 10, Color.GREEN);
        newBall.setSpeedX(randdouble(90, 150));
        newBall.setSpeedY(randdouble(90, 150));

        return newBall;
    }

    // take in all the balls.
    public void action() {

        // test boundary collision for each ball
        for (int i = 0; i < ballArr.length; i++) {
            boundaryTest(ballArr[i]);
        }
        // test ball-ball collision, loop for C(n ,2)
        for (int i = 0; i < ballArr.length; i++) {
            for (int j = i+1; j < ballArr.length; j++){
                collisionTest(ballArr[i],ballArr[j]);
            }
        }
        // collisionTest(bi, bj) // O(n^2)

		/* In your screen saver implementation, you will need to handle collisions with
		   the border as well as collisions between all pairs of balls. You will also need to
		   log any collisions using the CollisionLogger class. For example, the following code
		   updates the state of two ball objects to indicate that they have collided and records
		   this collision in the collisionLogger.
		   if (ball1.intersect(ball2)) {
		   		ball1.collide(ball2);
		   		collisionLogger.collide(ball1,ball2);
		   		}

		   Note that the processKeyEvent method implemented below will handle printing the collision log
		   when the "p" key is pressed.
		  */
        for (int i = 0; i < ballArr.length; i++) {
            for (int j = i+1; j < ballArr.length; j++){
                if (ballArr[i].intersect(ballArr[j])){
                    ballArr[i].collide(ballArr[j]);
                    collisionLogger.collide(ballArr[i],ballArr[j]);
                }
            }
        }
    }

    public void boundaryTest(Ball b) {
        //This method is called once every frame to update the state of the BouncingBall.
        //update both X and Y positions
        double incX = b.getSpeedX() / getFPS();
        double incY = b.getSpeedY() / getFPS();

        b.setPos(b.getXPos() + incX, b.getYPos() + incY);
//
//        ballX+=ballXSpeed/getFPS();
//        ballY+=ballYSpeed/getFPS();

        //handle collisions with the border
        if (b.getXPos() < BORDER || b.getXPos() + b.getRadius() > getHeight()- BORDER ) {
            b.setSpeedX(-1 * b.getSpeedX());
//            ballXSpeed*=-1;
        }
        if (b.getYPos() < BORDER || b.getYPos() + b.getRadius() > getWidth() - BORDER){
            b.setSpeedY(-1 * b.getSpeedY());

//            ballYSpeed*=-1;
        }
    }

    public void collisionTest(Ball b1, Ball b2) {
        if (b1.intersect(b2)) {
            b1.setColor(Color.RED);
            b2.setColor(Color.RED);
        }
        b1.collide(b2);
    }

    public void draw(Graphics g) {
        //This method is called once every frame to draw the Frame.

        //This is how you use the graphics object to draw
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.WHITE);
        g.drawRect(BORDER,BORDER,getWidth()-BORDER*2,getHeight()-BORDER*2);
        for (int i = 0; i< ballArr.length; i++){
            g.setColor(ballArr[i].getColor());
            g.fillOval((int)ballArr[i].getXPos(), (int)ballArr[i].getYPos(), (int)ballArr[i].getRadius(), (int)ballArr[i].getRadius());
        }

    }

    public void processKeyEvent(KeyEvent e) {
        int keyCode = e.getKeyCode();

        /* This captures the user pressing the "p" key and prints out the current collisionLog to an image.
        	You can use this directly in your implementation. Add other cases to the if/else statement to
        	handle other key events.
        */
        if (e.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_P) {
            EasyBufferedImage image = EasyBufferedImage.createImage(collisionLogger.getNormalizedHeatMap());
            try {
                image.save("heatmap"+saveCounter+".png", EasyBufferedImage.PNG);
                saveCounter++;
            } catch (IOException exc) {
                exc.printStackTrace();
                System.exit(1);
            }
        }

        // reduce speed when press left key
        if (e.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_LEFT){
            for (int i = 0; i < ballArr.length; i++) {
                ballArr[i].setSpeedX(0.9 * ballArr[i].getSpeedX());
                ballArr[i].setSpeedY(0.9 * ballArr[i].getSpeedY());
            }
        }
        // increase speed when press right key
        if (e.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_RIGHT){
            for (int i = 0; i < ballArr.length; i++) {
                ballArr[i].setSpeedX(1.1 * ballArr[i].getSpeedX());
                ballArr[i].setSpeedY(1.1 * ballArr[i].getSpeedY());
            }
        }
    }

    public int randInt(int min, int max){
        //a utility method to get a random int between min and max.
        return (int)(Math.random()*(max-min)+min);
    }

    public double randdouble(double min, double max){
        //a utility method to get a random double between min and max.
        return (Math.random()*(max-min)+min);
    }

    public static void main(String[] args){
        BallScreenSaver ballScreenSaver= new BallScreenSaver(800, 800, "BallScreenSaver", 15);
        ballScreenSaver.start();
    }

}
