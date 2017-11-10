package ai_blackjack.skynet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
	
	// CHANGEABLE VARIABLES
	static int games_to_train = 20000;
	static int games_to_exploit = 5000;
	static int games_to_play_with_money = 20000;
	static int decks = 1;
	static double agent_money = 10000;
	static double default_bet = 10;
	// DO NOT CHANGE THESE
	static int total_games = 0;
	static int player_wins = 0;
	static int dealer_wins = 0;
	static DecimalFormat df = new DecimalFormat("####0.00");
	static boolean silent = true;
	static double best_win = 0;
	static SkynetAiAgent best_agent;
	static int buy = 0;
	static int sell = 0;
	static int no_action = 0;
	

	public static void main(String[] args) throws IOException {
		
		//long start = System.currentTimeMillis();
		
		/*
		 * Modified version of
		 * https://github.com/XinHuang123/BlackJack-with-Artificial-Intelligence
		 */

		// ALL or OFF
		//LOGGER.setLevel(Level.OFF);

		/*
		 * Alpha = Learning rate, set between 0 and 1. Setting it to 0 means
		 * that the Q-values are never updated, hence nothing is learned.
		 * Setting a high value such as 0.9 means that learning can occur
		 * quickly. 
		 * 
		 * Epsilon = Greedy Policy to allow the agent to occasionally
		 * not to take the optimal action according to its experience. 
		 * 
		 * Discount = Gamma, this models the fact that future rewards are worth less than
		 * immediate rewards
		 */

		// Decks || Epsilon || Discount || Alpha || Number of games to play || Agent name || Starting sum of agent
		//SkynetAiAgent donkey = new SkynetAiAgent(decks, 0.4, 0.2, 0.5, games_to_train, "donkey", agent_money);
		//train(donkey);
		//exploit(donkey);
		//playWithMoney(donkey, default_bet);
		// Decks || Epsilon || Discount || Alpha || Number of games to play || Agent name || Starting sum of agent
	//	SkynetAiAgent greedy = new SkynetAiAgent(decks, 0.2, 0.3, 0.2, games_to_train, "GreedySkynet", agent_money);
		//train(greedy);
		//exploit(greedy);
		//playWithMoney(greedy, default_bet);
		//greedy.saveQvaluesToCSV();
		//ArrayList<Stock> stockvalues = greedy.importData();
		//trainStockstuff(greedy, stockvalues);
		//greedy.saveStockQvaluesToCSV();

		
		// Batch testing of agents
		/*
		for (int i = 1; i < 11; i++) {
			String name = "donkey"+i;
			SkynetAiAgent q = new SkynetAiAgent(decks, (i*0.1), (0.1), (0.1), games_to_train, name, agent_money);
			SkynetAiAgent q2 = new SkynetAiAgent(decks, (0.1), (i*0.1), (0.1), games_to_train, name + "q2", agent_money);
			SkynetAiAgent q3 = new SkynetAiAgent(decks, (0.1), (0.1), (i*0.1), games_to_train, name + "q3", agent_money);
			SkynetAiAgent q4 = new SkynetAiAgent(decks, (i*0.1), (i*0.1), (0.1), games_to_train, name + "q4", agent_money);
			SkynetAiAgent q5 = new SkynetAiAgent(decks, (0.1), (i*0.1), (i*0.1), games_to_train, name + "q5", agent_money);
			SkynetAiAgent q6 = new SkynetAiAgent(decks, (i*0.1), (0.1), (i*0.1), games_to_train, name + "q6", agent_money);
			SkynetAiAgent q7= new SkynetAiAgent(decks, (i*0.1), (i*0.1), (i*0.1), games_to_train, name + "q7", agent_money);
			
			train(q);
			exploit(q);
			train(q2);
			exploit(q2);
			train(q3);
			exploit(q3);
			train(q4);
			exploit(q4);
			train(q5);
			exploit(q5);
			train(q6);
			exploit(q6);
			train(q7);
			exploit(q7);
			playWithMoney(q, default_bet);
			playWithMoney(q2, default_bet);
			playWithMoney(q3, default_bet);
			playWithMoney(q4, default_bet);
			playWithMoney(q5, default_bet);
			playWithMoney(q6, default_bet);
			playWithMoney(q7, default_bet);
			
			//q.saveQvaluesToCSV();
		}
		*/
		
		//Do not enable on larger set of games, very slow. Suggested games <10000
		//donkey.printQvalues();
		//greedy.printQvalues();
		
		//greedy.saveQvaluesToCSV();
		//donkey.saveQvaluesToCSV();

		//Pair dpairs=(Pair) donkey.qvalues.keySet().toArray()[50];
		//Pair gpairs=(Pair) greedy.qvalues.keySet().toArray()[50];
		/*
		long end = System.currentTimeMillis();
		if (!silent) {
			System.out.println("*******************************");
			System.out.println("Best agent, win " + df.format(best_win) + "% Name: " + best_agent.getName());
			System.out.println("Best agent, balance: " + df.format(best_agent.getMoney()));
			System.out.println("*******************************");
			System.out.println("Skynet wins: " + player_wins);
			System.out.println("Dealer wins: " + dealer_wins);
			System.out.println("Total games: " + total_games);
			System.out.println("Skynet win " + df.format(100 / ((double) total_games / (double) player_wins))+ "%");
			System.out.println("Execution time is " + df.format((end - start) / 1000d) + " seconds");
			System.out.println("*******************************");
		}*/
	}
	public static void doShit(ArrayList<Stock> stockvalues) {
		long start = System.currentTimeMillis();
		LOGGER.setLevel(Level.OFF);

		SkynetAiAgent greedy = new SkynetAiAgent(decks, 0.2, 0.3, 0.2, games_to_train, "GreedySkynet", agent_money);

		//ArrayList<Stock> stockvalues = greedy.importData();
		trainStockstuff(greedy, stockvalues);
		//greedy.saveStockQvaluesToCSV();

		long end = System.currentTimeMillis();
		if (!silent) {
			OpenFile.addLine("*******************************");
			OpenFile.addLine("Best agent, win " + df.format(best_win) + "% Name: " + best_agent.getName());
			OpenFile.addLine("Best agent, balance: " + df.format(best_agent.getMoney()));
			OpenFile.addLine("*******************************");
			OpenFile.addLine("Skynet wins: " + player_wins);
			OpenFile.addLine("Dealer wins: " + dealer_wins);
			OpenFile.addLine("Total games: " + total_games);
			OpenFile.addLine("Skynet win " + df.format(100 / ((double) total_games / (double) player_wins))+ "%");
			OpenFile.addLine("Execution time is " + df.format((end - start) / 1000d) + " seconds");
			OpenFile.addLine("*******************************");
		}
	}

	public static void train(SkynetAiAgent agent) {
		long start = System.currentTimeMillis();
		int total = 0;
		int reward = 0;
		int[] oldState;
		int action;
		int games = agent.numTraining;
		while (agent.numTraining > 0) {
			agent.dealer.gameBegin();
			while (true) {
				oldState = agent.getState();
				action = agent.getAction(oldState);
				if (agent.dealer.playerTurn(action)) {
					break;
				}
				int[] newState = agent.getState();
				reward = 0;
				agent.update(oldState, action, newState, reward);
			}
			boolean isWin = agent.dealer.winFlag;
			if (isWin) {
				reward = 1;
				total += 1;
				
			} else {
				reward = -1;
			}
			
			int dealer_hand_count = agent.dealer.dealerHand.size();
			int player_hand_count = agent.dealer.playerHand.size();
			int dealer_hand_value = agent.dealer.getDealerValue();
			int player_hand_value = agent.dealer.getPlayerValue();
			
			agent.info.add(player_hand_count + "#" + player_hand_value + "#" + dealer_hand_count + "#" +dealer_hand_value + "#" + isWin + "#" + agent.getName());
			agent.update(oldState, action, agent.getState(), reward);
			agent.numTraining -= 1;
		}
		if (!silent) {
			long training_end = System.currentTimeMillis();
			System.out.println("*******TRAINING DATA***********");
			System.out.println("Agent: " + agent.getName());
			System.out.println("Won " + total + " out of " + games);
			System.out.println("Agent specs: A:" + agent.getAlpha() + " D: " + agent.getDiscount() + " E: " + agent.epsilon);
			System.out.println("Training win " + df.format(100 / ((double) games / (double) total)) + "%");
			System.out.println("Training took " + df.format((training_end - start) / 1000d) + " seconds");
			System.out.println("*******TRAINING DATA***********\n");
		}
	}

	public static void exploit(SkynetAiAgent agent) {
		long start = System.currentTimeMillis();
		int total = 0;
		int games = 0;
		int reward = 0;
		int[] oldState;
		int action;
		//Stop greedy and set learning rate to conservative
		agent.setEpsilon(0);
		agent.setAlpha(0.2);

		while (games < games_to_exploit) {
			agent.dealer.gameBegin();
			
			while (true) {
				oldState = agent.getState();
				action = agent.getAction(oldState);
				if (agent.dealer.playerTurn(action)) {
					break;
				}
				int[] newState = agent.getState();
				reward = 0;
				agent.update(oldState, action, newState, reward);

			}
			boolean isWin = agent.dealer.winFlag;
			if (isWin) {
				reward = 2;
				total += 1;
			} else {
				reward = -1;
			}
			
			//agent.info.add(player_hand_count + "#" + player_hand_value + "#" + dealer_hand_count + "#" +dealer_hand_value + "#" + isWin + "#" + agent.getName());
			agent.update(oldState, action, agent.getState(), reward);
			games += 1;
			
		}
		if (!silent) {
			long playing_end = System.currentTimeMillis();			
			System.out.println("*******AGENT EXPLOIT DATA*********");
			System.out.println("Agent: " + agent.getName());
			System.out.println("Agent specs: A:" + agent.getAlpha() + " D: " + agent.getDiscount() + " E: " + agent.epsilon);
			System.out.println("Won " + total + " out of " + games);
			System.out.println("Playing win " + df.format(100 / ((double) games / (double) total)) + "%");
			System.out.println("Playing took " + df.format((playing_end - start) / 1000d) + " seconds");
			System.out.println("*******AGENT EXPLOIT DATA*********\n");

		}
	}

	
	public static void playWithMoney(SkynetAiAgent agent, double bet) {
		long start = System.currentTimeMillis();
		int total = 0;
		int reward = 0;
		int[] oldState;
		int action;
		int games = agent.numTraining;
		
		//Stop learning, use stuff you have. Update rewards on win/loss still.
		agent.setEpsilon(0);
		agent.setAlpha(0);
		agent.setDiscount(0);
		

		while (games < games_to_play_with_money) {
			agent.dealer.gameBegin();
			agent.setMoney(agent.money-bet);
			while (true) {
				oldState = agent.getState();
				action = agent.getAction(oldState);
				if (agent.dealer.playerTurn(action)) {
					break;
				}
				int[] newState = agent.getState();
				reward = 0;
				agent.update(oldState, action, newState, reward);

			}
			boolean isWin = agent.dealer.winFlag;
			if (isWin) {
				reward = 1;
				total += 1;
				player_wins++;
				if (agent.dealer.getPlayerValue() == 21 && agent.dealer.getPlayerHand().size() == 2){
					agent.setMoney(agent.money = agent.money + bet*5);
					//System.out.println("Jackpot with two cards, won: "+ (bet*5));
					
				}
				
				else if (agent.dealer.getPlayerValue() == 21) {
					agent.setMoney(agent.money = (agent.money + bet*3.5));
					//System.out.println("Jackpot, won: "+ bet*3.5);
					
				}
				else {
					agent.setMoney(agent.money = (agent.money + bet*2));
					//System.out.println("Won: "+ (bet*2));
				}

				
			} else {
				reward = -1;
				dealer_wins++;
			}
			//System.out.println("Agent money: " + agent.money);
			int dealer_hand_count = agent.dealer.dealerHand.size();
			int player_hand_count = agent.dealer.playerHand.size();
			int dealer_hand_value = agent.dealer.getDealerValue();
			int player_hand_value = agent.dealer.getPlayerValue();
			
			agent.info.add(player_hand_count + "#" + player_hand_value + "#" + dealer_hand_count + "#" +dealer_hand_value + "#" + isWin + "#" + agent.getName());
			agent.update(oldState, action, agent.getState(), reward);
			games += 1;
			total_games++;	
			
		}		
		if (!silent) {
			long playing_end = System.currentTimeMillis();
			System.out.println("*******AGENT PLAY DATA*********");
			System.out.println("Agent: " + agent.getName());
			System.out.println("Agent specs: A:" + agent.getAlpha() + " D: " + agent.getDiscount() + " E: " + agent.epsilon);
			System.out.println("Won " + total + " out of " + games);
			System.out.println("Playing win " + df.format(100 / ((double) games / (double) total)) + "%");
			System.out.println("Playing took " + df.format((playing_end - start) / 1000d) + " seconds");
			System.out.println("Balance left: " + agent.money);
			System.out.println("*******AGENT PLAY DATA*********\n");
		}
		double win = 100 / ((double) games / (double) total);
		if (win > best_win){
			best_win = win;
			best_agent = agent;
		}

	}
	
	public static void trainStockstuff(SkynetAiAgent agent, ArrayList<Stock> stockvalues) {
		
		// Momentum kaava
		// Multiply that number by 100. M = (Price Today/Price Five Days Ago) x100.
		
		long start = System.currentTimeMillis();
		double reward = 0;
		double[] oldState;
		double action;
		boolean isWin;
		int momentum=0;
		int old_momemtum =0;
		
		double old_price = 0;
		double old_pe_val = 0;
		Stock old_stock = null;
		Stock current_stock = null;
		double starting_money = agent.getMoney();
		System.out.println("Agent starting money: " + df.format(starting_money) + "€");
		for (int i = 0; i < stockvalues.size(); i++) {
			//agent.setMoney(agent.getMoney() + 100);
			if(old_price == 0) {
				old_price = stockvalues.get(i).getShare_price();
			}
			current_stock = stockvalues.get(i);
			double price = stockvalues.get(i).getShare_price();
			momentum = (int) ((price/old_price)*100);			
			current_stock.setMomentum(momentum);			
			oldState = agent.getStockState(current_stock, old_stock);
			action = agent.getStockAction(oldState);
						
			double pe_val = stockvalues.get(i).getPe_value();
			double[] newState = agent.getStockState(current_stock, old_stock);
			action = agent.getStockAction(oldState);
			
			if (old_momemtum < momentum){
				isWin = false;				
				reward = old_momemtum-momentum;
				agent.updateStock(oldState, action, newState, reward);
			} else {
				isWin = true;				
				reward = momentum-old_momemtum;				
				agent.updateStock(oldState, action, newState, reward);
			}
			
			int how_many_to_sell = 0;
			if (action == 2) { // Sell
				if(momentum +3 >= old_momemtum && stockvalues.get(i).getShare_price() > old_price){
					how_many_to_sell = (int) (agent.getStockCount() * 0.5);
					sell++;
				}
				
				else if (stockvalues.get(i).getShare_price() > old_price){
					how_many_to_sell = (int) (agent.getStockCount() * 0.1);
					sell++;
				}
				
				double money_from_selling = sellStocks(how_many_to_sell, stockvalues.get(i).getShare_price());
				agent.setMoney(agent.getMoney()+money_from_selling);
				agent.setStockCount(agent.getStockCount() - how_many_to_sell);
				
				
			}
			double usable_money = 0;
			if (action == 1) {// buy stocks 
				if(old_momemtum >= momentum-3 && stockvalues.get(i).getShare_price() < old_price){
					usable_money = agent.getMoney() * 0.5;
					buy++;
					
<<<<<<< HEAD
				} else if (stockvalues.get(i).getShare_price() < old_price) {
					usable_money = agent.getMoney() * 0.1;
					buy++;
				}
				agent.setStockCount(agent.getStockCount() + buyStocks(usable_money,stockvalues.get(i).getShare_price()));
				agent.setMoney(agent.getMoney()-usable_money);
				
			}
			agent.info.add(old_price + "#" + old_pe_val + "#" + price + "#" +pe_val + "#" + isWin + "#" + agent.getName());
=======
			OpenFile.addQLine(old_price,old_pe_val,price,pe_val, isWin,agent.getName());
>>>>>>> a9736786f0c19f1dd60c7ff32e23b7a94cfca442
			old_pe_val = stockvalues.get(i).getPe_value();
			old_price = stockvalues.get(i).getShare_price();
			old_stock = stockvalues.get(i);
			old_stock.setMomentum(momentum);
			old_momemtum = (int) ((price/old_price)*100);
			
		}
		double nw = agent.getMoney() + (stockvalues.get(stockvalues.size()-1).getShare_price()) * agent.getStockCount();
		System.out.println("Agent Money: " + df.format(agent.getMoney()) + "€");
		System.out.println("Agent stock count: " + agent.getStockCount());
		System.out.println("Agent stock worth: " + df.format((stockvalues.get(stockvalues.size()-1).getShare_price()) * agent.getStockCount()) + "€");
		System.out.println("Agent networth: " + df.format(nw) + "€");
		System.out.println("Profit or loss: " + df.format(nw - starting_money) + "€");
		System.out.println("Buy events: " + buy);
		System.out.println("Sell events: " + sell);
		System.out.println("No action: " + (stockvalues.size() - buy - sell));
		long end = System.currentTimeMillis();
		OpenFile.addLine("Fiddling data took " + df.format((end - start) / 1000d) + " seconds");
	}
	
	public static int buyStocks(double money, double stock_prize) {		
		
		return (int) (money / stock_prize);
	}
	
	public static double sellStocks (int count, double stock_prize) {
		return count * stock_prize;
	}

}
