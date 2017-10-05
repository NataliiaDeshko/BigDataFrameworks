public class Recho {

    public static void main(String[] args) {

		// check whether there are any arguments entered
		if (args.length>0){
			
			// going through the entered strings from the last one to the second
			for (int i=args.length-1;i>0;i--)
				// displaying them in a reversed order
				System.out.print(args[i]+" ");
        
			// displaying the first string which wasn't shown in the loop
			System.out.print(args[0]);
		}
    }
}
