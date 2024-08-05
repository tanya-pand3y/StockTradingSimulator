package view;

import interface_adapter.buy.BuyController;
import interface_adapter.buy.BuyViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class BuyView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "buy";
    private BuyViewModel viewModel;
    private final BuyController controller;

    private JTextField stockTickerField;
    private JTextField quantityField;
    private JButton buyButton;

    public BuyView(BuyViewModel viewModel, BuyController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.initializeUI();
        this.viewModel.addPropertyChangeListener(this);
    }

    private void initializeUI() {
        setSize(50, 100);
        setLayout(new BorderLayout());
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));

        // Stock ticker input
        JLabel stockTickerLabel = new JLabel(this.viewModel.TICKER_LABEL);
        this.stockTickerField = new JTextField();

        // Quantity input
        JLabel quantityLabel = new JLabel(this.viewModel.QUANTITY_LABEL);
        this.quantityField = new JTextField();

        // Buy button
        this.buyButton = new JButton(this.viewModel.EXECUTE_LABEL);
        this.buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeBuyOrder();
                System.out.println("Buy " + quantityField.getText());
            }
        });

        // Add components to the panel
        mainPanel.add(stockTickerLabel);
        mainPanel.add(stockTickerField);
        mainPanel.add(quantityLabel);
        mainPanel.add(quantityField);
        mainPanel.add(new JLabel()); // empty cell
        mainPanel.add(buyButton);

        // Add main panel to the frame
        add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle any property changes if needed
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
        controller.execute(stockTicker, quantity, this.viewModel.getState().getUsername());
    }
}
