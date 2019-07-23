import java.util.Scanner;

  public class Assignment1_1 {

	public static void main (String[]args) {

		System.out.println("Hello World");
		Scanner userinput = new Scanner(System.in);
		String name = userinput.nextLine();
		System.out.println("Hello "+ name);
		userinput.close();
	}

}
