import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
				
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Search Engine!");
		
		System.out.println();
		Engine engine = new Engine("input.txt");
		System.out.println("All set! Good to go!");
		System.out.println();
		System.out.println("Type the word you want to search or else type 'exit' to close!");
		String wordtosearch = scanner.nextLine();
		while (!wordtosearch.trim().toLowerCase().equals("quit")) {
			String[] multiplewords = wordtosearch.split("[[,]*|[ ]*]+");
			String[] result = engine.search(multiplewords);
			if(result == null) {
				System.out.println("There are no results found for the above word! Please type another word below or type - \"quit\" to close !");
				wordtosearch = scanner.nextLine();
				continue;
			}
			if(result != null) {
				System.out.println("The results for your word can be found in the links shown below: \n");
			
				for (int i = 0; i < result.length; ++i) {
					System.out.println(result[i]);
					System.out.println();
				}
			}
			
			System.out.println("\nType the word you want to search for or type - \"quit\" to close!");
			wordtosearch = scanner.nextLine();
		}
		scanner.close();
		System.out.println("Goodbye Friend!");
		
	}

}