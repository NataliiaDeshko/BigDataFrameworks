import java.util.Random;
import java.util.Scanner;

public class Dichotom {

    public  static int enterInteger(Scanner scan){
        boolean correct = false;
        while (!correct) {
            if (scan.hasNextInt())
                return scan.nextInt();
            else {
                String entered = scan.next();
                System.out.println("Please enter integer value:");
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        // default values
        int min; // = 0;
        int max; // = 10;

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter min :");
        min = enterInteger(scan);

        System.out.println("Enter max :");
        max = enterInteger(scan);

        if (max < min) {
            int tmp = max;
            max = min;
            min = tmp;
        }

        Random rand = new Random();
        int number = rand.nextInt((max - min) + 1) + min;
        //System.out.println(number);

        System.out.println("Enter your guess (integer):");
        int guess= number+1;

        while (guess!=number) {
            guess = enterInteger(scan);
            if (guess == number)
                System.out.println("Success");
            else if (guess < number)
                System.out.println("Try bigger values");
            else if (guess > number)
                System.out.println("Try smaller values");
        }
    }
}
