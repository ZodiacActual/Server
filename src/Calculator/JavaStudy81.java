package Calculator;

public class JavaStudy81 {
    public static void main(String[] args) {
        //AddCalculator Cal = new AddCalculator(10, 5);
        DivideCalculator Cal = new DivideCalculator(10, 5);
        Cal.add();
        Cal.divide();
        Cal.minus();
    }
}
