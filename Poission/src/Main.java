
public class Main {

    public static void main (String [] args){
        double t = poisson(lambdaP0(2,1),3, 0, 0);
        System.out.println(t);
    }

    private static double lambdaP0(double lambda, double t){

        return  lambda * t;
    }

    private static double poisson (double p0, int decimals, int min, int max){

        double res = 0;
        double factor = Math.pow(10, decimals);
        for (int i = min; i <= max; i++) {
            res += Math.pow(p0, i)/factorial(i) * Math.pow(Math.E, -p0);
        }

        res*= factor;
        return Math.round(res) / factor;
    }

    private static int factorial(int x){

        int factorial = 1;
        for (int i = 2; i <= x ; i++) {
            factorial = factorial * i;
        }

        return factorial;
    }
}
