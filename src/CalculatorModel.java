public class CalculatorModel {

    private float calculatedValue = 0;

    public void addition(float a, float b){
        calculatedValue = a + b;
    }

    public void subtraction(float a, float b){
        calculatedValue = a - b;
    }

    public void multiplication(float a, float b){
        calculatedValue = a * b;
    }

    public void division(float a, float b){
        calculatedValue = a / b;
    }

    public float getCalculatedValue(){
        return calculatedValue;
    }

}
