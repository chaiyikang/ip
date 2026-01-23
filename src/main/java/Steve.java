import java.util.Scanner;

public class Steve {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Steve!\nWhat can I help you with?");
        String divider = "----------";

        Scanner sc = new Scanner(System.in);
        while (true) {
            String inpt = sc.nextLine();
            if (inpt.equals("bye")) {
                break;
            }
            System.out.println(divider);
            System.out.println(inpt + "? uh...ok");
            System.out.println(divider);
        }


        System.out.println("---------------------");
        System.out.println("Bye! Glad I could help!");
    }
}
