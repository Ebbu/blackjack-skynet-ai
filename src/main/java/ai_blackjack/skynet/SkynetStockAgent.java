package ai_blackjack.skynet;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.math3.util.Precision;

public class SkynetStockAgent {
	Stock stock;
	int count = 0;
	double epsilon;
	double discount;
	double alpha;
	double money;
	String name;
	HashMap<Pair, Double> qvalues;
	List<Object> info;
	int stock_count;
	static DecimalFormat df = new DecimalFormat("####0.00");
	String[] STOCK_HEADERS = { "STATE", "QVALUE", "AI SUGGESTED ACTION", "OLD STOCK PRICE", "OLD P/E",
			"CURRENT STOCK PRIZE", "CURRENT P/E", "BUY/SELL", "SKYNET HANDLE" };
	double qval = 0;

	Random r = new Random();

	public SkynetStockAgent(double e, double d, double a, String name, double m) {
		this.name = name;
		this.epsilon = e;
		this.discount = d;
		this.alpha = a;
		this.qvalues = new HashMap<Pair, Double>();
		this.info = new ArrayList<>();
		this.money = m;
		this.stock_count = 0;
		this.qval = 0;
	}

	public double[] getStockState(Stock current, Stock old) {

		double[] state = new double[4];

		try {
			double cp = current.getCommon_value();
			double cm = current.getMomentum();
			double op = old.getCommon_value();
			double om = old.getMomentum();
			state[0] = Precision.round(cp, 1);
			state[1] = cm;
			state[2] = Precision.round(op, 1);
			state[3] = om;
		} catch (Exception e) {
			state[2] = 0;
			state[3] = 0;
		}

		return state;
	}

	public double getStockAction(double[] state) {
		double[] legalActions = this.getLegalStockActions(state);

		// get next next boolean value
		double temp = Math.random();
		if (temp > this.epsilon) {
			Random randomG = new Random();
			int index = randomG.nextInt(3);
			int action = (int) legalActions[index];
			return action;
		}

		return this.getStockPolicy(state);
	}

	public void saveStockQvaluesToCSV() throws IOException {

		FileWriter out = new FileWriter("StockQvalues_" + getName() + ".csv");
		try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(STOCK_HEADERS))) {
			qvalues.forEach((pair, reward) -> {

				try {
					if (count < info.size()) { // Print end of game info
						printer.printRecord(Arrays.toString(pair.stateDouble), reward, pair.action_double,
								info.get(count));
					} else { // Print only state updates
						printer.printRecord(Arrays.toString(pair.stateDouble), reward, pair.action_double);
					}
					count++;
				} catch (Exception e) {

				}

			});
			System.out.println("Data saved to CSV file");
		}

	}

	public double getStockPolicy(double[] state) {

		double maxValue = -99999.0;
		double maxAction = 0;
		double actionValue = 0;
		double[] legalActions = this.getLegalStockActions(state);
		for (int i = 0; i < 3; i++) {
			actionValue = this.getStockQValue(state, legalActions[i]);

			if (maxValue < actionValue) {
				maxValue = actionValue;
				maxAction = legalActions[i];
			}
		}

		return maxAction;
	}

	@SuppressWarnings("unused")
	public double getStockValue(double[] state) {

		double maxValue = -99999.0;
		double maxAction = 0;
		double actionValue;
		double[] legalActions = this.getLegalStockActions(state);
		for (int i = 0; i < 3; i++) {
			actionValue = this.getStockQValue(state, legalActions[i]);
			
			if (maxValue < actionValue) {
				maxValue = actionValue;
				maxAction = legalActions[i];
			}
		}

		return maxValue;
	}

	public double getStockQValue(double[] state, double legalActions) {

		Pair pair = new Pair(state, legalActions);
		if (this.qvalues.get(pair) == null)
			return 0.0;
		return this.qvalues.get(pair);
	}

	public double[] getLegalStockActions(double[] state) {

		double[] a = new double[3];
		a[0] = 1; // Buy
		a[1] = 2; // Sell
		a[2] = 3; // nothings
		return a;
	}

	public void updateStock(double[] state, double action, double[] nextState, double reward) {
		double qvalue = this.getStockQValue(state, action) + this.alpha
				* (reward + this.discount * this.getStockValue(nextState) - this.getStockQValue(state, action));
		Pair pair = new Pair(state, action);
		setQval(qvalue);
		this.qvalues.put(pair, qvalue);
	}

	public void setEpsilon(double e) {
		this.epsilon = e;
	}

	public void setDiscount(double d) {
		this.discount = d;
	}

	public void setAlpha(double a) {
		this.alpha = a;
	}

	public void setName(String n) {
		this.name = n;
	}

	public String getName() {
		return this.name;
	}

	public Double getEpsilon() {
		return this.epsilon;
	}

	public Double getAlpha() {
		return this.alpha;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getMoney() {
		return this.money;
	}

	public ArrayList<Stock> importData() {

		String fileName = "Evli_pe_2016-2017.txt";
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		List<?> list;

		try {
			try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

				list = stream.collect(Collectors.toList());

			}

			for (int i = 0; i < list.size(); i++) {

				String temp = list.get(i).toString();

				String[] asdf = temp.split(";");
				String date = asdf[0];
				double price = Double.parseDouble(asdf[1]);
				double pe_value = Double.parseDouble(asdf[2]);
				Stock s = new Stock(date, price, 0, pe_value, 0, 0);
				stockList.add(s);

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		return stockList;
	}

	public void setStockCount(int count) {
		this.stock_count = count;
	}

	public int getStockCount() {
		return this.stock_count;
	}

	public int buyStocks(double money, double stock_prize) {

		return (int) (money / stock_prize);
	}

	public double sellStocks(int count, double stock_prize) {
		return count * stock_prize;
	}

	public double getQval() {
		return this.qval;
	}

	public void setQval(double q) {
		this.qval = q;
	}
}
