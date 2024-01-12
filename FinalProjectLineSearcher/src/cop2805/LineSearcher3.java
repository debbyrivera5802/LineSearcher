package cop2805;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.Scanner;
public class LineSearcher3 {
	static  List<String> hamletText = new ArrayList<>();
	//public static void main(String[] args) {
	LineSearcher3() {
		/*Scanner scnr = new Scanner(System.in);
        int userInput;
        userInput = scnr.nextInt( );*/
		Path filePath = Paths.get("hamlet.txt");
        Charset charset = StandardCharsets.UTF_8;
        try {
        	 hamletText = Files.readAllLines(filePath, charset);
            for(String line: hamletText) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        //List <String> Result = LineSearcher(userInput);
	}
	public List <String> LineSearcher(int input/*, List<String> text*/) {
		List<String> returnedLines = new ArrayList<>();
				int sub2 = input - 2; // gets second line above input # line
				int sub1 = input - 1; // gets line above input # line 
				int add2 = input + 2; // gets second line after input # line
				int add1 = input + 1; // gets line after input # line
				if(sub2 > 0) {		
					returnedLines.add(hamletText.get(sub2));
					returnedLines.add("\n");
				}
				if(sub1 > 0) {
					returnedLines.add(hamletText.get(sub1));
					returnedLines.add("\n");
				}
				returnedLines.add(hamletText.get(input));
				returnedLines.add("\n");
				returnedLines.add(hamletText.get(add1));
				returnedLines.add("\n");
				returnedLines.add(hamletText.get(add2));
				//System.out.println(returnedLines);
				return returnedLines;	
	}
	}	