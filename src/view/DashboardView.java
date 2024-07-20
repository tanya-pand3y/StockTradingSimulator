package view;

import data_access.StockQuantityDataAccessObject;
import interface_adapter.dashboard.DashboardController;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.dashboard.DashboardInputData;
import use_case.dashboard.DashboardInteractor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DashboardView extends JFrame {
    public final String viewName = "dashboard";

    private DashboardViewModel viewModel;
    private DashboardController controller;

    public DashboardView(DashboardViewModel viewModel, DashboardController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set up the frame
        setTitle("Stock Trading Simulator Dashboard");
        setSize(1000, 800);
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
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome, " + this.viewModel.getUsername());
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        topPanel.add(welcomeLabel);

        JLabel portfolioValueLabel = new JLabel("Portfolio Value: " + this.viewModel.getPortfolioValue());
        portfolioValueLabel.setForeground(Color.WHITE);
        portfolioValueLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        topPanel.add(portfolioValueLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Table panel
        String[] columnNames = {"Name", "Ticker", "Qty", "Cost Basis", "Current Price", "Gain ($)", "Gain (%)"};
        Object[][] data = this.viewModel.getStructuredDashboard();

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);

        // Custom cell renderer for table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Right panel for market status and buttons
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(5, 1, 0, 10));
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.setPreferredSize(new Dimension(250, 0));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel marketStatusLabel = new JLabel("Market is OPEN");
        marketStatusLabel.setForeground(Color.WHITE);
        marketStatusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        marketStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(marketStatusLabel);

        // Get current date and time
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        JLabel dateTimeLabel = new JLabel("Date & Time: " + formattedDate);
        dateTimeLabel.setForeground(Color.WHITE);
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        dateTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(dateTimeLabel);

        JButton buyButton = createButton("Buy", new Color(34, 139, 34), "Buy stocks");
        rightPanel.add(buyButton);

        JButton sellButton = createButton("Sell", new Color(220, 20, 60), "Sell stocks");
        rightPanel.add(sellButton);

        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    private JButton createButton(String text, Color backgroundColor, String toolTip) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setToolTipText(toolTip);
        return button;
    }

    public static void main(String[] args) {
        StockQuantityDataAccessObject a = new StockQuantityDataAccessObject("Meer");
        DashboardInteractor interactor = new DashboardInteractor(a);
        DashboardController controller = new DashboardController(interactor);
        DashboardViewModel viewModel = new DashboardViewModel(controller, "Meer");
        SwingUtilities.invokeLater(() -> new DashboardView(viewModel, controller));
    }
}
