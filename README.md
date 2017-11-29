# Generic Qlearning AI bot for blackjack and stockmarket

Project is loosely based on XinHuang123's blackjack AI [XinHuang123 Github](https://github.com/XinHuang123/BlackJack-with-Artificial-Intelligence)

# Lisence

Copyright 2017 Leif Salminen / Eero Salomies / Antti Ryökkynen / Mokhtar Paukkio / Niko Pieviläinen

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

# Purpose of this project

First versions of project was concentrated making AI to play blackjack. We achieved around 34-35% win rate for blackjack.  
AI Technology for this project is Reinforcement Learning/Qlearning.
After that, project took new turn and we are now trying to use AI to make/help/support to make decisions for financial decisision.


# Usage

First you need some stock market data, suggested to use Yahoo finance for csv download.  
Data must be in following format
* Header row: date,close,adjclose

* Date = String for dates
* close = Stock closing price (format 0.0)
* Adjusted closing price = Stock adjusted closing price, if using data from longer perioid automatically calculates possible splits and dividends in price. Yahoo Finance offer this information. (format 0.0)

Example  
date,close,adjclose  
2000-01-03,7.23256254196167,4.525106430053711  

You can train AI with multiple different stocks, we are suggesting you use different stocks to train and for 'real' simulation stock you want to get results in.

You can change from code what value AI uses (current closing price or adjusted closing price).

Bot creation: SkynetStockAgent hal = new SkynetStockAgent(epsilon, discount, alpha, "HAL 9000", agent_money);
* Epsilon = 

# Requirements
 
| Software     | Version         | 
| ------------- |:-------------:| 
| Java      | 1.8.0.131 | 
| jfreechart     | 1.5.0  | 
| Apache commons-csv     | 1.5 |
| Apache commons-math3     | 3.6.1 |

* Some stock market data (preferably 5+ stocks)

# Useful links

Yahoo Finance for csv data : [Yahoo Finance](https://finance.yahoo.com/)

# Whats missing from project

* Proper data saving from GUI
* Ability to clear all data and start over (Now you need to restart program for that)
* Easy way to follow what AI does

# Team

* Leif Salminen   
* Eero Salomies   
* Antti Ryökkynen  
* Mokhtar Paukkio 
* Niko Pieviläinen