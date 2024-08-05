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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QueryStockView extends JPanel {
    private JTextField tickerField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton queryButton;
    private JTextArea resultArea;
    private JTable priceHistoryTable;
    private final QueryStockController controller;
    private final QueryStockViewModel viewModel;
    private JButton resetButton; // Declare the reset button

    public QueryStockView(QueryStockController controller, QueryStockViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        // Set the layout of the panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        // Initialize the components
        tickerField = new JTextField(10);
        startDateField = new JTextField(10);
        endDateField = new JTextField(10);
        queryButton = new JButton("Query Stock");

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Stock Ticker:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(tickerField, gbc);

        //reset button
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        resetButton = new JButton("Reset");
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

        // Add action listener to the button
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleQueryStock();
                updateDisplay();
            }
        });

        // Add a property change listener to the ViewModel
//        viewModel.addPropertyChangeListener(new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                updateDisplay();
//            }
//        });

    }

    //Reset view method
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

    }


    private void handleQueryStock() {
        // Get the values from the text fields
        String ticker = tickerField.getText();
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();

        // Pass the data to the controller
        controller.execute(ticker, startDate, endDate);
    }


    private void updateDisplay() {
        // Get the updated data from the ViewModel
        String ticker = viewModel.getTicker();
        Double currentPrice = viewModel.getCurrentPrice();
        ArrayList<Double> priceHistory = viewModel.getPriceHistory();
        String startDate = viewModel.getStartDate();
        String endDate = viewModel.getEndDate();

        // Update the result area with the new data
        String resultText = String.format("Ticker: %s\nCurrent Price: $%.2f", ticker, currentPrice);
        resultArea.setText(resultText);

        // Update the price history table if price history data is available
        //if (priceHistory != null && !priceHistory.isEmpty())
        if (priceHistory != null) {
            String[] columnNames = {"Date", "Price"};
            Object[][] data = new Object[priceHistory.size()][2];

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                Date start = null;
                Date end = null;

                // Check if startDate and endDate are not empty
                if (startDate != null && !startDate.trim().isEmpty()) {
                    start = dateFormat.parse(startDate);
                }
//                else {
//                    // Handle the case where startDate is empty or invalid
//                    start = new Date(); // Default to current date
//                }

                if (endDate != null && !endDate.trim().isEmpty()) {
                    end = dateFormat.parse(endDate);
                }
//                else {
//                    // Handle the case where endDate is empty or invalid
//                    end = new Date(); // Default to current date
//                }

//                if (start.after(end)) {
//                    throw new IllegalArgumentException("Start date cannot be after end date.");
//                }

                long totalDays = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
                long intervalDays = totalDays / (priceHistory.size() - 1);

                for (int i = 0; i < priceHistory.size(); i++) {
                    Date date = new Date(start.getTime() + (i * intervalDays * 1000 * 60 * 60 * 24));
                    data[i][0] = dateFormat.format(date);
                    data[i][1] = priceHistory.get(i);
                }

            } catch (ParseException e) {
                e.printStackTrace();
                // Handle parse exception for date format issues
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                // Handle invalid date range
            } catch (Exception e) {
                e.printStackTrace();
                // Handle other exceptions
            }

//            if (priceHistoryTable != null) {
//                remove(priceHistoryTable);
//            }

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
        // Create the ViewModel
        QueryStockViewModel viewModel = new QueryStockViewModel("QueryStockViewModel");

        // Create the real presenter
        QueryStockOutputBoundary presenter = new QueryStockPresenter(viewModel);

        // Create the real interactor
        QueryStockInputBoundary interactor = new QueryStockInteractor(presenter);

        // Create the controller with the real interactor
        QueryStockController controller = new QueryStockController(interactor);

        // Create the view
        QueryStockView view = new QueryStockView(controller, viewModel);

        // Set up the frame
        JFrame frame = new JFrame("Query Stock View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(view, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}

