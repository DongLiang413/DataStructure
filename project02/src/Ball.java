import java.lang.Boolean;

public class Ball extends Circle {
    double speedx;
    double speedy;
    double units;

    public Ball(double x, double y, double r, double speedx,double speedy){
        super(x,y,r);
        this.speedx=speedx;
        this.speedy=speedy;
    }
    public void setSpeedX(double speedx){
        this.speedx=speedx;
    }

    public void setSpeedY(double speedy){
        this.speedy=speedy;
    }

    public double getSpeedX(){
        return speedx;
    }

    public double getSpeedY(){
        return speedy;
    }

    public void updatePosition(double units) {
        this.x=this.x+speedx*units;
        this.y=this.y+speedy*units;
    }

    public Boolean intersect(Ball other){
        if (this.x==other.getXPos() || this.y == other.getYPos()) {
            return true;
        }
        else{
            return false;
        }
    }

    public void collide(Ball other){
        if (intersect(other)){
            double d = Math.sqrt((this.x-other.getXPos())*(this.x-other.getXPos())+(this.y-other.getYPos())*(this.y-other.getYPos()));
            double changexd=(this.x-other.getXPos())/d;
            double changeyd=(this.y-other.getYPos())/d;
            if (d<(this.r+other.getRadius())){
                double newcolvelocity=this.speedx*changexd+this.speedy*changeyd;
                double newnormvelocity=-1*this.speedx*changeyd+this.speedy*changexd;
                this.speedx=newcolvelocity*changexd-newnormvelocity*changeyd;
                this.speedy=newcolvelocity*changeyd+newnormvelocity*changexd;
            }
        }
    }

}
