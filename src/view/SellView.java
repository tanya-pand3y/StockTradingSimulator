package view;

import use_case.sell.SellInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellView extends JFrame {

    private JComboBox<String> stockComboBox;
    private JTextField quantityField;
    private JButton sellButton;

    public SellView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Sell menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));

        // Stock selection
        JLabel stockLabel = new JLabel("Select Stock:");
        stockComboBox = new JComboBox<>(new String[]{"AAPL", "GOOGL", "MSFT"}); // Example stocks

        // Quantity input
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();

        // Sell button
        sellButton = new JButton("Sell");
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //executeSellOrder();
            }
        });

        // Add components to the panel
        mainPanel.add(stockLabel);
        mainPanel.add(stockComboBox);
        mainPanel.add(quantityLabel);
        mainPanel.add(quantityField);
        mainPanel.add(new JLabel()); // empty cell
        mainPanel.add(sellButton);

        // Add main panel to the frame
        add(mainPanel, BorderLayout.CENTER);
    }

//    private void executeSellOrder() {
//        String selectedStock = (String) stockComboBox.getSelectedItem();
//        int quantity;
//        try {
//            quantity = Integer.parseInt(quantityField.getText());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Please enter a valid quantity", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        // Execute the sell order using SellInteractor
//        SellInteractor sellInteractor = new SellInteractor();
//        sellInteractor.execute(selectedStock, quantity);
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SellView().setVisible(true));
    }
}
