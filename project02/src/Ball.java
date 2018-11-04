// Wenqing Wu     wuxx1734@umn.edu; 5328675
// Yangjiawen Xu     xuxx1262@umn.edu; 5332558

import java.awt.*;
import java.lang.Boolean;

public class Ball extends Circle{
    private double speedX;
    private double speedY;

    public Ball(double x,double y,double r, Color color){
        super(x,y,r);
        this.setColor(color);
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY){
        this.speedY=speedY;
        }

    public double getSpeedX(){
        return speedX;
        }

    public double getSpeedY() {
        return speedY;
    }

    public void updatePosition(double units){
        this.x=this.x+speedX*units;
        this.y=this.y+speedY*units;
    }

    public Boolean intersect(Ball other){
        double dis=Math.sqrt((this.x-other.getXPos())*(this.x-other.getXPos())+ (this.y-other.getYPos())*(this.y-other.getYPos()));
        if (dis<(this.r+other.getRadius())||dis==(this.r+other.getRadius())){
            return true;
        }
        else{
            return false;
        }
    }

    public void collide(Ball other){
        if (intersect(other)){
            double dis=Math.sqrt((this.x-other.getXPos())*(this.x-other.getXPos())+ (this.y-other.getYPos())*(this.y-other.getYPos()));
            //find unit vectors corresponding to collision coordinate system
            double changeXd=(this.x-other.getXPos())/dis;
            double changeYd=(this.y-other.getYPos())/dis;
            //redefine the velocity according to collision coordinate system
            double colvelocity1=this.speedX*changeXd+this.speedY*changeYd;
            double normvelocity1= -this.speedX*changeYd+this.speedY*changeXd;
            double colvelocity2=other.speedX*changeXd+other.speedY*changeYd;
            double normvelocity2= -other.speedX*changeYd+other.speedY*changeXd;
            //compute the post-collision velocities of the balls by swapping the new collision velocities of each ball
            double newcolvelocity1=colvelocity2;
            double newcolvelocity2=colvelocity1;
            // get the final speed of both balls
            this.speedX = newcolvelocity1*changeXd-normvelocity1*changeYd;
            this.speedY = newcolvelocity1*changeYd+normvelocity1*changeXd;
            other.speedX = newcolvelocity2*changeXd-normvelocity2*changeYd;
            other.speedY = newcolvelocity2*changeYd+normvelocity2*changeXd;
        }
    }
}