package interface_adapter.sell;

import interface_adapter.ViewManagerModel;
import use_case.sell.SellOutputBoundary;
import use_case.sell.SellOutputData;

public class SellPresenter implements SellOutputBoundary {
    private final SellViewModel sellViewModel;
    private ViewManagerModel viewManagerModel;

    public SellPresenter(SellViewModel sellViewModel, ViewManagerModel viewManagerModel) {
        this.sellViewModel = sellViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SellOutputData response) {
        // TODO implement
    }

    @Override
    public void prepareFailView(String error) {
        // TODO implement
    }
}
