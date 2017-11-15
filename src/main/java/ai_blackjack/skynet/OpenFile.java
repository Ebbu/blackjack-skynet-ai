package ai_blackjack.skynet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.DefaultCategoryDataset;

public class OpenFile {
	JFileChooser filechooser = new JFileChooser();
	ArrayList<String> Data = new ArrayList<String>();
	static DefaultTableModel Taulu, Qtaulu;

	List<String> tyot;

	public void FilePicker(DefaultTableModel model, DefaultTableModel model2, DefaultTableModel model3)
			throws Exception {
		filechooser.setCurrentDirectory(new java.io.File("."));
		if (filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			Taulu = model2;
			Qtaulu = model3;
			File file = filechooser.getSelectedFile();
			ArrayList<Stock> stockList = new ArrayList<Stock>();

			Scanner input = new Scanner(file);

			while (input.hasNext()) {
				Data.add(input.nextLine());
			}
			input.close();

			for (int i = 0; i < Data.size(); i++) {

				String temp = Data.get(i).toString();

				String[] asdf = temp.split(";");
				String date = asdf[0];
				double price = Double.parseDouble(asdf[1]);
				double pe_value = Double.parseDouble(asdf[2]);
				Stock s = new Stock(date, price, pe_value, 0);
				model.addRow(new Object[] { date, price, pe_value });
				stockList.add(s);

			}

			App.startGui(stockList);
			
		}

	}

	public static void addLine(String string) {
		Taulu.addRow(new Object[] { string });

	}

	public static void addQLine(String state, double old_price, double old_pe_val, double price, double pe_val,
			String name, int momemtum, String ai_action, String money, int stock_count, String networth) {
		Qtaulu.addRow(new Object[] {state, old_price, old_pe_val, price, pe_val, name, momemtum, ai_action, money,
				stock_count, networth });

	}
	  public static DefaultCategoryDataset createDataset( ) {
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      double networth = 0;
	      String input ="";
	      for (int i = 0; i < Qtaulu.getRowCount(); i++) {	  
	    	  input = Qtaulu.getValueAt(i,10).toString();
	    	  String[]inputArray = input.split(",");	  
	    	  networth = Double.parseDouble(inputArray[0]);
	    	 dataset.addValue(networth, "Networth", Integer.toString(i));
		}
	     
	      return dataset;
	   }
	
}