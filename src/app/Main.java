package app;

import interface_adapter.buy.BuyViewModel;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.sell.SellViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Welcome to your Stock Trading Simulator");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.

        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        DashboardViewModel dashboardViewModel = new DashboardViewModel();
        SellViewModel sellViewModel = new SellViewModel();
        BuyViewModel buyViewModel = new BuyViewModel();

//        SignupView signupView = app.SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel);
//        views.add(signupView, signupView.viewName);

//        LoginView loginView = new LoginView(loginViewModel, loginController);
//        views.add(loginView, loginView.viewName);

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel);
        views.add(signupView, signupView.viewName);

        // Creating Login View using LoginUseCaseFactory
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, dashboardViewModel, signupViewModel);
        views.add(loginView, loginView.viewName);

        // Creating Dashboard View
        DashboardView dashboardView = DashboardViewFactory.create(viewManagerModel, dashboardViewModel, sellViewModel, buyViewModel);
        views.add(dashboardView, dashboardView.viewName);

        // Creating sell view
        SellView sellView = SellViewFactory.create(viewManagerModel, dashboardViewModel, sellViewModel);
        views.add(sellView, sellView.viewName);

        // Creating buy view
        BuyView buyView = BuyViewFactory.create(viewManagerModel, dashboardViewModel, buyViewModel);
        views.add(buyView, buyView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();


        application.pack();
        application.setVisible(true);
    }
}
