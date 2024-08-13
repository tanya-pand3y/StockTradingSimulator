package interface_adapter.QueryStock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QueryStockViewModelTest {
    private QueryStockViewModel viewModel;

    @BeforeEach
    public void setUp() {
        viewModel = new QueryStockViewModel();
    }

    //This also tests the getters and setters
    @Test
    public void testUpdateView() {
        ArrayList<Double> priceHistory = new ArrayList<>();
        priceHistory.add(150.0);
        ArrayList<ZonedDateTime> dates = new ArrayList<>();
        dates.add(ZonedDateTime.now());

        viewModel.updateView("AAPL", 150.0, "2024-01-01", "2024-12-31", priceHistory, dates);

        assertEquals("AAPL", viewModel.getTicker());
        assertEquals(150.0, viewModel.getCurrentPrice());
        assertEquals("2024-01-01", viewModel.getStartDate());
        assertEquals("2024-12-31", viewModel.getEndDate());
        assertEquals(priceHistory, viewModel.getPriceHistory());
        assertEquals(dates, viewModel.getDates());
    }

    @Test
    public void testSetPriceHistory() {
        ArrayList<Double> priceHistory = new ArrayList<>();
        priceHistory.add(150.0);

        viewModel.setPriceHistory(priceHistory);
        assertEquals(priceHistory, viewModel.getPriceHistory());
    }

    @Test
    public void testSetDates() {
        ArrayList<ZonedDateTime> dates = new ArrayList<>();
        dates.add(ZonedDateTime.now());

        viewModel.setDates(dates);
        assertEquals(dates, viewModel.getDates());
    }
}