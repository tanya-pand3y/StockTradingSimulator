package use_case.stock_history;
import java.util.List;
import java.time.LocalDate;

public class ViewStockHistoryOutputData {
    private final String ticker;
    private final List<StockHistoryRecord> historyRecords;

    public ViewStockHistoryOutputData(String ticker, List<StockHistoryRecord> historyRecords) {
        this.ticker = ticker;
        this.historyRecords = historyRecords;
    }

    public String getTicker() {
        return ticker;
    }

    public List<StockHistoryRecord> getHistoryRecords() {
        return historyRecords;
    }

    // Inner class to represent a record of stock history
    public static class StockHistoryRecord {
        private final LocalDate date;
        private final double price;
        private final int volume;

        public StockHistoryRecord(LocalDate date, double price, int volume) {
            this.date = date;
            this.price = price;
            this.volume = volume;
        }

        public LocalDate getDate() {
            return date;
        }

        public double getPrice() {
            return price;
        }

        public int getVolume() {
            return volume;
        }
    }
}
