
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class Main extends JFrame implements ActionListener{
	JButton btn;
	JLabel lbl;
	JPanel panel;
	public Main() {
		this.setSize(300,200);
	    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setTitle("Gallery genrator");
	    this.setLocationRelativeTo(null);
	    
	    btn = new JButton ("Select folder");
	    btn.addActionListener(this);
	    btn.setBackground(Color.WHITE);
		btn.setFocusable(false);
	    lbl = new JLabel("Select images directory: ");
		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		this.add(panel);
	    panel.add(lbl);
	    panel.add(btn);
	    this.setVisible(true);
	    
	}
	public static void main(String[] args) {
		new Main();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== btn) {
			JFileChooser image = new JFileChooser();
			// To select file directory;
			image.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			// Displays file window;
			image.showOpenDialog(this);
			// get selected file;
			File photoDirectory = image.getSelectedFile();
			//create a list of files in the selected folder
			File [] picture = photoDirectory.listFiles();
			 ArrayList<String> photo = new ArrayList<String>();
			 String pictureUrl = "";
			 // generate html <a> tag for each picture file in the folder
			for(File URL:picture) {
				photo.add("<a href= " + URL + "><img src = " + URL +" ></a>");
			}
			//concatinate all the genrated <a> tags into a single string
			for (String i : photo) {
				pictureUrl += i + "\n";
			}
			//dynamic HTML code generator
			String html = "<html><style>img{ border:1px solid #ddd; border-radius: 4px;  padding: 5px; width: 150;}</style> <body>"
					+pictureUrl + "</body></html>";
			try {
				//create the HTML file
				FileWriter writer = new FileWriter("gallery.html");
				writer.write(html);
				writer.close();
				
				JOptionPane.showMessageDialog(null,"Gallery created, check program source folder for \"gallery.html\"");
				System.exit(DISPOSE_ON_CLOSE);
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
		
	}

}
