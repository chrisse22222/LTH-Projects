package mountain;

import fractal.*;

import java.util.HashMap;

public class Mountain extends Fractal {

    private Point aStart, bStart, cStart;
    private double dev;
    private HashMap<Side, Point> map;
    public Mountain(double dev){
        super();
        this.dev = dev;
        map = new HashMap<>();
    }

    public String getTitle(){
        return "Mountain";
    }

    @Override
    public void draw(TurtleGraphics g) {
        aStart = new Point(g.getWidth() / 2 - 20, g.getHeight()/2 - 100);
        bStart = new Point(500, 500);
        cStart = new Point(75, 475);
        fractalTriangle(g, order, aStart, bStart, cStart, RandomUtilities.randFunc(dev));
    }

    public void fractalTriangle(TurtleGraphics g, int order, Point a, Point b, Point c, double dev){
        if (order == 0){
            g.moveTo(a.getX(), a.getY());
            g.forwardTo(b.getX(), b.getY());
            g.forwardTo(c.getX(),c.getY());
            g.forwardTo(a.getX(),a.getY());
        }else {

            Point midA_B = getMiddlePoint(b, a, dev);
            Point midB_C = getMiddlePoint(b, c, dev);
            Point midC_A = getMiddlePoint(c, a, dev);

            fractalTriangle(g, order - 1, a, midA_B, midC_A, dev / 2);
            fractalTriangle(g, order - 1, midA_B, b, midB_C, dev / 2);
            fractalTriangle(g, order - 1, midB_C, midA_B, midC_A, dev / 2);
            fractalTriangle(g, order - 1, midB_C, c, midC_A, dev /2);
        }
    }

    private Point getMiddlePoint(Point p1, Point p2, double dev){
        Point mid;
        Side s = new Side(p1, p2);

        if (map.containsKey(s)){
            mid = map.remove(s);
        }else{
            mid = new Point((p1.getX() - (p1.getX() - p2.getX())/2),
                    (p1.getY() - (p1.getY() - p2.getY())/2) + (int)RandomUtilities.randFunc(dev));
            map.put(s, mid);
        }

        return mid;
    }
}
