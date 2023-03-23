package DataModels;

public class Monom {
    private double coeficient;
    private int grad;
    public Monom(double coeficient, int grad){
        this.coeficient = coeficient;
        this.grad = grad;
    }

    public void setCoeficient(double coeficient) {
        this.coeficient = coeficient;
    }

    public void setGrad(int grad) {
        this.grad = grad;
    }

    public int getGrad() {
        return grad;
    }

    public double getCoeficient() {
        return coeficient;
    }
    public String toString(){
        return coeficient+"*x^"+grad;
    }
}
