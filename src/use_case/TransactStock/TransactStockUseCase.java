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

    public boolean buyStock(CommonUser user, Stock stock, int quantity) {
        double totalPrice = stock.getCurrentPrice() * quantity; // Get current price of stock
        Portfolio portfolio = user.getPortfolio();
        double cash = portfolio.getCash();
        //Checks if user has enough money to buy stock
        if (cash >= totalPrice) {
            Holding holding = new Holding(stock, stock.getCurrentPrice(), quantity); // Create new holding
            portfolio.deductCash(totalPrice); //Balance is updated after user buys
            portfolio.addHolding(holding); //Holding is added to portfolio
            return true;
        } else {
            // Insufficient funds
            return false;
        }
    }

    public boolean sellStock(CommonUser user, Stock stock, int quantity) {
        Portfolio portfolio = user.getPortfolio(); // Get user portfolio
        if (portfolio.getQuantity(stockID) >= quantity) {
            double totalPrice = stock.getCurrentPrice() * quantity;
            portfolio.setCash(portfolio.getCash() + totalPrice); //Update cash
            portfolio.removeHolidng(stockID, quantity); //Remove stocks from portfolio
            return true;
        } else {
            // Insufficient stock quantity
            return false;
        }
    }
}
