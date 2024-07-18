package use_case.TransactStock;
import Repositories.StockRepository;
import entity.Stock;
import entity.Transaction;
import entity.CommonUser;
import entity.Portfolio;
import entity.Holding;

public class TransactStockUseCase {
    //Need stock repository to fetch stock data
    private final StockRepository stockRepository;

    public TransactStockUseCase(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public boolean buyStock(CommonUser user, String stockID, int quantity) {
        Stock stock = stockMarket.get(stockID); // get stock based on stockID
        double totalPrice = stock.getCurrentPrice() * quantity; // Get current price of stock
        Portfolio portfolio = user.getPortfolio();
        double cash = portfolio.getCash();
        //Checks if user has enough money to buy stock
        if (cash >= totalPrice) {
            Holding holding = new Holding(stock, stock.getCurrentPrice(), quantity); // Create new holding
            portfolio.deductCash(totalPrice); //Balance is updated after user buys
            portfolio.addHolding(holding); //Holding is added to portfolio
            recordTransaction(user.getUserID(), stockID, "buy", quantity, stock.getCurrentPrice());
            return true;
        } else {
            // Insufficient funds
            return false;
        }
    }
    public Transaction sellStock(String stockSymbol, int quantity) {
        return stockRepository.sellStock(stockSymbol, quantity);
    }

}
