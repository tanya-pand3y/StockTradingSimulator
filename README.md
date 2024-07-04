# Stock Trading Simulator

**Team Name:** Doubly Linked List

**Domain:** Stock trading simulator

### **Software Specification:**
To give users a platform to invest in stocks, view their profits and losses, their own and other’s portfolios, view stock price trends, and use fake money to buy and sell stocks. The target audience are finance enthusiasts who want to learn stock trading. 

### **User Stories:**
1. Sophia is a self-taught finance enthusiast eager to improve her trading skills. She explores other users' portfolios on our platform for inspiration, learning new strategies and techniques. By comparing different approaches, she refines her own trading style and progresses steadily in her trading journey. Using fake money, she practices buying and selling stocks without any financial risk. She also tracks her profits and losses, views detailed stock price trends, and analyzes the historical performance of stocks to make better investment decisions. [Team Story]

2. Barack is a student who is interested in learning how to trade stocks. He doesn't want to risk real money because he is still learning. He uses our financial paper trading system to practice.

3. Lebron wants to learn how to analyze the historical performance of a stock in order to make better investment decisions and to understand market movements. He views the stock price trends to see how prices have changed and when price were their highest and lowest.

4. Mia is a finance enthusiast who is learning by herself. She wants to get better and inspired by other people's portfolios to make better decisions herself and progress in her trading journey.

5. Olivia is a data-driven finance enthusiast who loves analyzing market trends and stock performance. She uses our platform's advanced graphs to visualize market data and her portfolio's performance. With line charts, bar charts, and scatter plots, Olivia identifies patterns and insights that inform her trading decisions, helping her make more strategic investments.

### **Proposed entities for domain:**
User
- string username
- string userID
- array userInventory
- string Password

Stock
- str stock_id
- float current_price
- Dictionary/array historical_prices

Portfolio
- str portfolioID
- str userID
- ArrayList stockNames

Transaction
- str transactionID
- str portfolioID
- str stockID
- str type
- float/int quantity
- float price
- str/datetimeObject date

Market Data
- str marketDataID
- str stockID
- float openPrice
- float closePrice
- float highPrice
- float lowPrice
- float purchaseVolume



