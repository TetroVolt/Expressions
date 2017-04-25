import expression.Expression;

import java.util.Scanner;

public class Main {

    private static boolean done = false;
    private static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Math evaluator v1.0\nEnter a math expression or q to quit.");
        while (!done) {
            PromptExpression();
        }
    }

    private static void PromptExpression() {
        System.out.print(" >>> ");
        String expr = scn.nextLine();
        if (expr.isEmpty()) return;
        if (expr.toLowerCase().equals("q") || expr.toLowerCase().equals("quit")) {
            done = true; return;
        } else if (expr.toLowerCase().startsWith("clear")) {
            for (int i = 0; i < 24; i++) {
                System.out.println();
            } return;
        }

        try {
            Expression ee = Expression.compile(expr);
            System.out.println("\n[EXP] : " + expr);
            System.out.println("[ANS] : " + ee.evaluate().getValue());
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Invalid statement. ");
            // done = true;
        }
    }
}
