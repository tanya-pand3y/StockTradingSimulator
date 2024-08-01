package view;

import interface_adapter.QueryStock.QueryStockController;
import interface_adapter.QueryStock.QueryStockPresenter;
import interface_adapter.QueryStock.QueryStockViewModel;
import use_case.query_stock.QueryStockInputBoundary;
import use_case.query_stock.QueryStockInputData;
import use_case.query_stock.QueryStockInteractor;
import use_case.query_stock.QueryStockOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class QueryStockView extends JPanel {
    private JTextField tickerField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton queryButton;
    private JTextArea resultArea;
    private final QueryStockController controller;
    private final QueryStockViewModel viewModel;

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
            }
        });

        // Add a property change listener to the ViewModel
        viewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateDisplay();
            }
        });
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
        ArrayList priceHistory = viewModel.getpriceHistory();
//        String startDate = viewModel.getStartDate();
//        String endDate = viewModel.getEndDate();

        // Update the result area with the new data
        String resultText = String.format("Ticker: %s\nCurrent Price: $%.2f\nPrice History: %s",
                ticker, currentPrice, priceHistory);
        resultArea.setText(resultText);
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

