package view;

import interface_adapter.dashboard.DashboardController;
import interface_adapter.dashboard.DashboardViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DashboardView extends JFrame {

    private DashboardViewModel viewModel;
    private DashboardController controller;


    public DashboardView() {
        initializeUI();
    }

    private void initializeUI() {
        // Set up the frame
        setTitle("Stock Trading Simulator Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);

        // Top panel for user information
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));
        topPanel.setBackground(Color.DARK_GRAY);

        JLabel welcomeLabel = new JLabel("Welcome, <Username>");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(welcomeLabel);

        JLabel portfolioValueLabel = new JLabel("Portfolio Value: $308,265");
        portfolioValueLabel.setForeground(Color.WHITE);
        portfolioValueLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(portfolioValueLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Table panel
        String[] columnNames = {"Name", "Ticker", "Qty", "Cost Basis", "Current Price", "Gain ($)", "Gain (%)"};
        Object[][] data = this.viewModel.getStructuredDashboard();

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Right panel for market status and buttons
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(5, 1, 0, 10));
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.setPreferredSize(new Dimension(200, 0));

        JLabel marketStatusLabel = new JLabel("Market is OPEN");
        marketStatusLabel.setForeground(Color.WHITE);
        marketStatusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        marketStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(marketStatusLabel);

        // Get current date and time
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        JLabel dateTimeLabel = new JLabel("Date & time: " + formattedDate);
        dateTimeLabel.setForeground(Color.WHITE);
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(dateTimeLabel);

        JButton buyButton = new JButton("Buy");
        buyButton.setBackground(Color.GREEN);
        buyButton.setFont(new Font("Arial", Font.BOLD, 16));
        rightPanel.add(buyButton);

        JButton sellButton = new JButton("Sell");
        sellButton.setBackground(Color.RED);
        sellButton.setFont(new Font("Arial", Font.BOLD, 16));
        rightPanel.add(sellButton);

        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardView());
    }
}
