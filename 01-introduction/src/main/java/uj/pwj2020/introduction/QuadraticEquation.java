package uj.pwj2020.introduction;

public class QuadraticEquation {

    public double[] findRoots(double a, double b, double c)
    {
        double[] result = new double[0];
        double delta = b*b - 4*a*c;
        if(delta > 0){
            result = new double[2];
            double sqrtdelta = Math.sqrt(delta);
            result[0] = (-b + sqrtdelta)/(2*a);
            result[1] = (-b - sqrtdelta)/(2*a);
        }else if(delta == 0){
            result = new double[1];
            result[0] = -b/(2*a);
        }
        
        return result;
    }

}

