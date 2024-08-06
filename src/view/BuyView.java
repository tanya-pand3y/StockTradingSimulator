package view;

import interface_adapter.buy.BuyController;
import interface_adapter.buy.BuyViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BuyView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "buy";
    private BuyViewModel viewModel;
    private final BuyController controller;

    private JTextField stockTickerField;
    private JTextField quantityField;
    private JLabel currentPriceLabel;
    private JLabel totalPriceLabel;
    private JButton buyButton;
    private JButton cancelButton;

    public BuyView(BuyViewModel viewModel, BuyController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.initializeUI();
        this.viewModel.addPropertyChangeListener(this);
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2));
        mainPanel.setBackground(Color.DARK_GRAY);

        // Stock ticker input
        JLabel stockTickerLabel = new JLabel(this.viewModel.TICKER_LABEL);
        stockTickerLabel.setForeground(Color.WHITE);
        this.stockTickerField = new JTextField();
        mainPanel.add(stockTickerLabel);
        mainPanel.add(stockTickerField);

        // Current price display
        JLabel currentPriceTextLabel = new JLabel("Current Price:");
        currentPriceTextLabel.setForeground(Color.WHITE);
        this.currentPriceLabel = new JLabel("399.61"); // Example price
        this.currentPriceLabel.setForeground(Color.WHITE);
        mainPanel.add(currentPriceTextLabel);
        mainPanel.add(currentPriceLabel);

        // Quantity input
        JLabel quantityLabel = new JLabel(this.viewModel.QUANTITY_LABEL);
        quantityLabel.setForeground(Color.WHITE);
        this.quantityField = new JTextField();
        this.quantityField.setText("");
        this.quantityField.addActionListener(e -> updateTotalPrice());
        mainPanel.add(quantityLabel);
        mainPanel.add(quantityField);

//        // Total price display
//        JLabel totalPriceTextLabel = new JLabel("Total Order Price:");
//        totalPriceTextLabel.setForeground(Color.WHITE);
//        this.totalPriceLabel = new JLabel("3996.10"); // Example total price
//        this.totalPriceLabel.setForeground(Color.WHITE);
//        mainPanel.add(totalPriceTextLabel);
//        mainPanel.add(totalPriceLabel);

        add(mainPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setBackground(Color.BLACK);

        // Cancel button
        this.cancelButton = new JButton("Cancel");
        this.cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel();
                System.out.println("Cancel buy");
            }
        });
        buttonPanel.add(cancelButton);

        // Buy button
        this.buyButton = new JButton(this.viewModel.EXECUTE_LABEL);
        this.buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeBuyOrder();
                System.out.println("Buy " + quantityField.getText());
            }
        });
        buttonPanel.add(buyButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle any actions if needed
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (BuyViewModel.ERROR_MESSAGE_PROPERTY.equals(evt.getPropertyName())) {
            String errorMessage = (String) evt.getNewValue();
            if (errorMessage != null && !errorMessage.isEmpty()) {
                JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTotalPrice() {
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            double currentPrice = Double.parseDouble(currentPriceLabel.getText());
            double totalPrice = quantity * currentPrice;
            totalPriceLabel.setText(String.format("%.2f", totalPrice));
        } catch (NumberFormatException e) {
            totalPriceLabel.setText("0.00");
        }
    }

    private void executeBuyOrder() {
        String stockTicker = stockTickerField.getText();
        int quantity;
        try {
            quantity = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        controller.execute(stockTicker, quantity, this.viewModel.getState().getPortfolio());
    }

    private void cancel() {
        controller.cancel();
    }
}
