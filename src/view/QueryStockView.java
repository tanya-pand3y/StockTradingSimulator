package view;

import interface_adapter.QueryStock.QueryStockController;
import interface_adapter.QueryStock.QueryStockPresenter;
import interface_adapter.QueryStock.QueryStockViewModel;
import use_case.query_stock.QueryStockInputBoundary;
import use_case.query_stock.QueryStockInteractor;
import use_case.query_stock.QueryStockOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class QueryStockView extends JPanel {
    public final String viewName = "QueryStockView";
    private JTextField tickerField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton queryButton;
    private JTextArea resultArea;
    private JTable priceHistoryTable;
    private final QueryStockController controller;
    private final QueryStockViewModel viewModel;
    private JButton resetButton; // Declare the reset button
    private JButton backButton;  // Declare the back button

    public QueryStockView(QueryStockController controller, QueryStockViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        // Set the layout of the panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Set preferred size to increase the window size
        setPreferredSize(new Dimension(800, 1000));

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        // Initialize the components
        tickerField = new JTextField(10);
        startDateField = new JTextField(10);
        endDateField = new JTextField(10);
        queryButton = new JButton("Query Stock");
        resetButton = new JButton("Reset");
        backButton = new JButton("Back");

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Stock Ticker:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(tickerField, gbc);

        // Add reset button
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetView();
            }
        });
        add(resetButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Start Date (YYYY-MM-DD):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(startDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("End Date (YYYY-MM-DD):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(endDateField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(queryButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(resultArea, gbc);

        // Add back button
        gbc.gridx = 0;
        gbc.gridy = 5;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle back button action
                System.out.println("Back button pressed");
                // You can add more logic here to switch views
            }
        });
        add(backButton, gbc);

        // Add action listener to the query button
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (handleQueryStock()) {
                    updateDisplay();
                }
            }
        });

        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.backButtonPressed();
                    }
                }
        );
    }

    // Reset view method
    private void resetView() {
        // Clear input fields
        tickerField.setText("");
        startDateField.setText("");
        endDateField.setText("");

        // Clear the result area
        resultArea.setText("");

        // Remove the price history table if it exists
        if (priceHistoryTable != null) {
            Container parent = priceHistoryTable.getParent();
            if (parent instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) parent;
                Container grandParent = scrollPane.getParent();
                if (grandParent != null) {
                    grandParent.remove(scrollPane); // Remove the JScrollPane
                }
            } else {
                if (parent != null) {
                    parent.remove(priceHistoryTable); // Directly remove the JTable if not in JScrollPane
                }
            }
            priceHistoryTable = null; // Set the reference to null
        }

        // Revalidate and repaint the view to update the UI
        revalidate();
        repaint();

        // Reset the ViewModel
        viewModel.setTicker("");
        viewModel.setStartDate("");
        viewModel.setEndDate("");
        viewModel.setCurrentPrice(0.0);
        viewModel.setPriceHistory(null);
        viewModel.setDates(null);
    }

    private boolean handleQueryStock() {
        // Get the values from the text fields
        String ticker = tickerField.getText();
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();

        if (ticker.isEmpty()) {
            resultArea.setText("Error: Please enter a valid stock ticker.");
            return false;
        }
        if (startDate.isEmpty() && !endDate.isEmpty()) {
            resultArea.setText("Error: Please enter a start date.");
            return false;
        }
        if (!startDate.isEmpty() && endDate.isEmpty()) {
            resultArea.setText("Error: Please enter an end date.");
            return false;
        }
        // Pass the data to the controller
        System.out.println("Ticker: " + ticker);
        controller.execute(ticker, startDate, endDate);
        return true;
    }

    private void updateDisplay() {
        // Get the updated data from the ViewModel
        String ticker = viewModel.getTicker();
        Double currentPrice = viewModel.getCurrentPrice();
        ArrayList<Double> priceHistory = viewModel.getPriceHistory();
        ArrayList<ZonedDateTime> dates = viewModel.getDates();

        // Update the result area with the new data
        String resultText = String.format("Ticker: %s\nCurrent Price: $%.2f", ticker, currentPrice);
        resultArea.setText(resultText);

        // Update the price history table if price history data is available
        if (priceHistory != null && dates != null) {
            String[] columnNames = {"Date", "Price"};
            Object[][] data = new Object[priceHistory.size()][2];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (int i = 0; i < priceHistory.size(); i++) {
                data[i][0] = dates.get(i).format(formatter); // format the ZonedDateTime to a String
                data[i][1] = priceHistory.get(i);
            }

            priceHistoryTable = new JTable(data, columnNames);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.BOTH;
            add(new JScrollPane(priceHistoryTable), gbc);
            revalidate();
            repaint();
        }
    }

    public static void main(String[] args) {

    }
}
