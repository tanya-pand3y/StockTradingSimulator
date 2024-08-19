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
    private JLabel sharePriceLabel;
    private JLabel orderPriceLabel;
    private JButton getQuoteButton;
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
        mainPanel.setLayout(new GridLayout(5, 2, 10, 10));
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Stock ticker input
        JLabel stockTickerLabel = new JLabel("Ticker:");
        styleLabel(stockTickerLabel);
        this.stockTickerField = new JTextField();
        mainPanel.add(stockTickerLabel);
        mainPanel.add(stockTickerField);

        // Quantity input
        JLabel quantityLabel = new JLabel("Quantity:");
        styleLabel(quantityLabel);
        this.quantityField = new JTextField();
        mainPanel.add(quantityLabel);
        mainPanel.add(quantityField);

        // Get Quote button
        this.getQuoteButton = new JButton("Get Quote");
        styleButton(getQuoteButton, new Color(70, 130, 180));
        this.getQuoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchQuote();
            }
        });
        mainPanel.add(new JLabel()); // Empty cell for layout
        mainPanel.add(getQuoteButton);

        // Share Price display
        JLabel sharePriceTextLabel = new JLabel("Current Price:");
        styleLabel(sharePriceTextLabel);
        this.sharePriceLabel = new JLabel("0.00");
        styleLabel(this.sharePriceLabel);
        mainPanel.add(sharePriceTextLabel);
        mainPanel.add(sharePriceLabel);

        // Order Price display
        JLabel orderPriceTextLabel = new JLabel("Total Order Price:");
        styleLabel(orderPriceTextLabel);
        this.orderPriceLabel = new JLabel("0.00");
        styleLabel(this.orderPriceLabel);
        mainPanel.add(orderPriceTextLabel);
        mainPanel.add(orderPriceLabel);

        add(mainPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Cancel button
        this.cancelButton = new JButton("Cancel");
        styleButton(cancelButton, new Color(220, 20, 60));
        this.cancelButton.addActionListener(e -> System.out.println("Cancel"));
        buttonPanel.add(cancelButton);

        // Buy button
        this.buyButton = new JButton(this.viewModel.EXECUTE_LABEL);
        styleButton(buyButton, new Color(34, 139, 34));
        this.buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeBuyOrder();
            }
        });
        buttonPanel.add(buyButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
    }

    private void styleButton(JButton button, Color backgroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
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
        } else if (BuyViewModel.SHARE_PRICE_PROPERTY.equals(evt.getPropertyName())) {
            updateSharePrice((Double) evt.getNewValue());
        }
    }

    private void fetchQuote() {
        String stockTicker = stockTickerField.getText();
        if (stockTicker == null || stockTicker.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ticker", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        controller.fetchSharePrice(stockTicker);
    }

    private void updateSharePrice(double sharePrice) {
        this.sharePriceLabel.setText(String.format("%.2f", sharePrice));
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            double sharePrice = Double.parseDouble(sharePriceLabel.getText());
            double totalPrice = quantity * sharePrice;
            orderPriceLabel.setText(String.format("%.2f", totalPrice));
        } catch (NumberFormatException e) {
            orderPriceLabel.setText("0.00");
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

    //Added this for testing purposes
    public BuyController getController() {
        return controller;
    }
}
