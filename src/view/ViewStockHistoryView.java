package view;


import interface_adapter.ViewStockHistory.ViewStockHistoryController;
import interface_adapter.ViewStockHistory.ViewStockHistoryViewModel;
import use_case.stock_history.ViewStockHistoryOutputData;
import use_case.stock_history.ViewStockHistoryInputBoundary;
import use_case.stock_history.ViewStockHistoryInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ViewStockHistoryView extends JPanel implements ActionListener {
    private final ViewStockHistoryController controller;
    public final String viewName = "View stock history";
    private final ViewStockHistoryViewModel viewModel;

    private JTextField tickerField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton searchButton;
    private JTextArea stockHistoryArea;

    public ViewStockHistoryView(ViewStockHistoryController controller, ViewStockHistoryViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Main panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 5, 5));

        // Ticker input
        JLabel tickerLabel = new JLabel("Enter Stock Ticker:");
        tickerField = new JTextField(15);

        // Start Date input
        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateField = new JTextField(10);

        // End Date input
        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateField = new JTextField(10);

        // Search button
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);

        // Add components to the input panel
        inputPanel.add(tickerLabel);
        inputPanel.add(tickerField);
        inputPanel.add(startDateLabel);
        inputPanel.add(startDateField);
        inputPanel.add(endDateLabel);
        inputPanel.add(endDateField);
        inputPanel.add(new JLabel()); // empty cell
        inputPanel.add(searchButton);

        // Stock history display area
        stockHistoryArea = new JTextArea(10, 50);
        stockHistoryArea.setEditable(false);
        add(new JScrollPane(stockHistoryArea), BorderLayout.CENTER);

        // Add input panel to the frame
        add(inputPanel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchStockHistory();
        }
    }

    private void searchStockHistory() {
        String ticker = tickerField.getText().trim();
        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();

        if (ticker.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter ticker, start date, and end date.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Pass the input data to the controller
        controller.execute(ticker, startDate, endDate);
    }

    public void displayStockHistory(ViewStockHistoryOutputData data) {
        StringBuilder displayText = new StringBuilder();
        displayText.append("Ticker: ").append(data.getTicker()).append("\n");
        displayText.append("Price History:\n");

        for (Double price : data.getStockPrices()) {
            displayText.append(price).append("\n");
        }

        stockHistoryArea.setText(displayText.toString());
    }
}