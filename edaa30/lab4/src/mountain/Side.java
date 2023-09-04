package mountain;

import java.security.PublicKey;

public class Side {
    private Point p1, p2;

    public Side (Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Side){
            Side p = (Side) obj;
            return (p1 == p.p1 && p2 == p.p2) || (p1 == p.p2 && p2 == p.p1);
        }

        return false;
    }

    @Override
    public int hashCode(){
        return p1.hashCode() + p2.hashCode();
    }
}
