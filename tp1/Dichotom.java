import java.util.Random;
import java.util.Scanner;

public class Dichotom {

	//  a function which checks whether the entered sequence is an integer
    public  static int enterInteger(Scanner scan){
        boolean correct = false;
		// waiting until user enters a correct number
        while (!correct) {
			// the case when the sequence entered is an integer
            if (scan.hasNextInt())
                return scan.nextInt();
			// the case when the sequence cannot be read as an integer
            else {
				// getting current value just to pass to the next one
                String entered = scan.next();
                System.out.println("Please enter integer value:");
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        // initialization of variables for the edges of an interval
        int min;
        int max;

		// an object to read input
        Scanner scan = new Scanner(System.in);

		// demanding user to enter min and max values of an interval
		
        System.out.println("Enter min :");
        min = enterInteger(scan);

        System.out.println("Enter max :");
        max = enterInteger(scan);

		// if minimum is larger than max - switch tham		
        if (max < min) {
            int tmp = max;
            max = min;
            min = tmp;
        }
		
		// if minimum and maximum values coinside, we'll consider an interval of length 10 instead
		if (max = min) {
            max = min + 10;
        }

		// generating a random number from the given interval
        Random rand = new Random();
        int number = rand.nextInt((max - min) + 1) + min;
        
		// just to check what is the value that we should guess
		//System.out.println(number);

        System.out.println("Enter your guess (integer):");
        int guess= number+1;

		// user should guess a number
        while (guess!=number) {
			// chech whether a number is enntered correctly
            guess = enterInteger(scan);
			// case when the user guessed a number
            if (guess == number)
                System.out.println("Success");
            // cases when user didn't guess a number:
			// giving user a hint
			else if (guess < number)
                System.out.println("Try bigger values");
            else if (guess > number)
                System.out.println("Try smaller values");
        }
    }
}
