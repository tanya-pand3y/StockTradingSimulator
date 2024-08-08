package entity;

import data_access.StockQuantityDataAccessInterface;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HoldingTest {
    @Test
    public void testHoldingConstructor() {
        Stock mockStock = mock(Stock.class);
        when(mockStock.getCurrentPrice()).thenReturn(123.45);
        StockTransactionHistory mockHistory = mock(StockTransactionHistory.class);
        when(mockHistory.getTotalQuantity()).thenReturn(100);
        when(mockHistory.getTotalTransactionValue()).thenReturn(12300.0);

        Holding holding = new Holding(mockStock, mockHistory);
        assertEquals(mockStock, holding.getStock());
        assertEquals(100, holding.getQuantity());
        assertEquals(12345.0, holding.getCurrentValue(), 0.1);
        assertEquals(45.0, holding.getPnL(), 0.1);
    }

    @Test
    public void testRecalculate() {
        Stock mockStock = mock(Stock.class);
        when(mockStock.getCurrentPrice()).thenReturn(123.45);
        StockTransactionHistory mockHistory = mock(StockTransactionHistory.class);
        when(mockHistory.getTotalQuantity()).thenReturn(100);
        when(mockHistory.getTotalTransactionValue()).thenReturn(12300.0);

        Holding holding = new Holding(mockStock, mockHistory);
        holding.recalculate();

        assertEquals(12345.0, holding.getCurrentValue(), 0.1);
        assertEquals(45.0, holding.getPnL(), 0.1);
    }

    @Test
    public void testGetCurrentValue() {
        Stock mockStock = mock(Stock.class);
        when(mockStock.getCurrentPrice()).thenReturn(123.45);
        StockTransactionHistory mockHistory = mock(StockTransactionHistory.class);
        when(mockHistory.getTotalQuantity()).thenReturn(100);
        when(mockHistory.getTotalTransactionValue()).thenReturn(12300.0);

        Holding holding = new Holding(mockStock, mockHistory);
        double currentValue = holding.getCurrentValue();

        assertEquals(12345.0, currentValue, 0.1);
    }

    @Test
    public void testGetPnL() {
        Stock mockStock = mock(Stock.class);
        when(mockStock.getCurrentPrice()).thenReturn(123.45);
        StockTransactionHistory mockHistory = mock(StockTransactionHistory.class);
        when(mockHistory.getTotalQuantity()).thenReturn(100);
        when(mockHistory.getTotalTransactionValue()).thenReturn(12300.0);

        Holding holding = new Holding(mockStock, mockHistory);
        double pnl = holding.getPnL();
        assertEquals(45.0, pnl, 0.1);
    }

    @Test
    public void testGetStockTransactionHistory() {
        Stock mockStock = mock(Stock.class);
        StockTransactionHistory mockHistory = mock(StockTransactionHistory.class);

        Holding holding = new Holding(mockStock, mockHistory);
        assertEquals(mockHistory, holding.getStockTransactionHistory());
    }
}