import java.util.Scanner;
import java.util.ArrayList;

public class Steve {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Steve!\nWhat can I help you with?");
        String divider = "----------";

        Scanner sc = new Scanner(System.in);
        ArrayList<String> userList = new ArrayList<>();


        while (true) {
            String inpt = sc.nextLine();
            if (inpt.equals("bye")) {
                break;
            }
            
            if (inpt.equals(("list"))) {
                System.out.println(divider);
                for (int i = 0; i < userList.size(); i++) {
                    boolean done = true;
                    System.out.println(i + 1 + ". " + "[" + (done ? "X" : "") + "] " + userList.get(i));
                }
                System.out.println(divider);
                continue;
            }

            userList.add(inpt);
            System.out.println(divider);
            System.out.println("added: " + inpt);
            System.out.println(divider);
        }


        System.out.println("---------------------");
        System.out.println("Bye! Glad I could help!");
    }
}
