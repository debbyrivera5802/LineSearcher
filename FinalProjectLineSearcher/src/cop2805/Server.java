package cop2805;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.nio.charset.*;
public class Server {

	public static void main(String[] args)throws IOException {
		LineSearcher3 LineSearch = new  LineSearcher3();
		ServerSocket ss = null;
		boolean shutdown = false;
		
		try {
		ss = new ServerSocket(1239);
		System.out.println("Port bound. Accepting connections");
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		while(!shutdown) {
		Socket client = null;
		//InputStream input = null;
		OutputStream output = null;
		
		try {
		client = ss.accept();  // set up input + output streams
		//input = client.getInputStream();
		output = client.getOutputStream();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String clientInput = in.readLine();
		
		int num;
		num = Integer.parseInt(clientInput);
		 List <String> Result = LineSearch.LineSearcher(num/*, hamletText*/);
		 for(String x : Result) {
			 String response = x + "\n";
			 output.write(response.getBytes());
		 }
		client.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			//continue;
		}
				
		
	}
}
}
	