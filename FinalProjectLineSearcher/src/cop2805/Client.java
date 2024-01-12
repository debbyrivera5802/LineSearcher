package cop2805;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.nio.charset.*;
public class Client {
	public static void main(String[] args) throws IOException{
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				constructGUI();
			}
		});
	}
	private static void constructGUI()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		MyFrame frame = new MyFrame();
		frame.setVisible(true);
	    frame.setTitle("Line Searcher");
	}
}

	class MyFrame extends JFrame {
	
		public  DefaultListModel<String> listModel;
		public JList<String> jlist;
		public JLabel resultText;
		public JTextField lineToSearch;
		public JButton connectButton;
		//public JList /*<String>*/ resultActual;
		public MyFrame() {
			super();
			init();
		}
		
		private void init() { 
			lineToSearch = new JTextField();     
			connectButton = new JButton("Connect");
			listModel = new DefaultListModel<String>();
			jlist = new JList<String> (listModel);
			jlist.setVisibleRowCount(5);
			
			
			connectButton.addActionListener(new Listen(this));
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// frame structure
			this.setTitle("JFrame Test");
			this.setLayout(new GridLayout(5,2));
			this.add(new JLabel("Line to search for "));
			this.add(lineToSearch);              
			this.add(new JLabel()); //space
			this.add(new JLabel()); //space
			this.add(new JLabel("Result goes here "));
			this.add(jlist); //space 
			this.add(new JScrollPane(jlist));
			//this.add(scrollPane);
			this.add(new JLabel()); //space 
			this.add(new JLabel()); //space 
			this.add(new JLabel()); //space 
			this.add(connectButton);
			int frameWidth = 500;
			int frameHeight = 375;
			Dimension screenSize = 
					Toolkit.getDefaultToolkit().getScreenSize();
			this.setBounds(
					(int)(screenSize.getWidth()/2)/* - frameWidth*/,
					(int)(screenSize.getHeight()/2) /*- frameHeight*/,
					frameWidth,
					frameHeight);
		}
	}
		class Listen implements ActionListener {
			  MyFrame fr;
			  public Listen(MyFrame frame){
			    fr = frame;
			  }
			  public void actionPerformed(ActionEvent e){
				  fr.listModel.clear(); // clears JList in GUI?
			 String input1 = String.valueOf(fr.lineToSearch.getText() +  "\n"); //reads string from GUI 
			 try {
			 Socket s = new Socket("127.0.0.1", 1239);
				//InputStream input = s.getInputStream();
				OutputStream output = s.getOutputStream();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				
				output.write(input1.getBytes());
				
				
				String response = " ";
				//String response = in.readLine();
						while(response != null) {
						 response = in.readLine();
						if(response != null) {
							fr.listModel.addElement(response);
						}
						}
				
				if (!s.isClosed()) 
					s.close();
				
			 }
			 catch (IOException p) {
				 p.printStackTrace();
			 }
			  }
		}
		