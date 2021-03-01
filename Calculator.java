import java.util.Scanner;

public class Calculator {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter first number:");
            int a = scanner.nextInt();
    
            System.out.print("Enter second numer:");
            int b = scanner.nextInt();


            CalculatorMethods calculatorMethods = new CalculatorMethods();
            System.out.println("soma = " + calculatorMethods.sum(a,b));
            System.out.println("subtracao = " + calculatorMethods.sub(a,b));
            System.out.println("divisao = " + calculatorMethods.div(a,b));
            System.out.println("multiplicacao = " + calculatorMethods.mult(a,b));
        } while (true);

        
        

        

    }
    
}
