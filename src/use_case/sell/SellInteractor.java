package use_case.sell;

import entity.Stock;

public class SellInteractor implements SellInputBoundary {
    final SellOutputBoundary sellPresenter;

    public SellInteractor(SellOutputBoundary sellOutputBoundary) {
        this.sellPresenter = sellOutputBoundary;
    }

    public void execute(SellInputData sellInputData) {
        if (!sellInputData.stockInAccount()) {
            sellPresenter.prepareFailView("Stock not in account");
        } else if (sellInputData.getQuantity() > sellInputData.getQuantityHeld()) {
            sellPresenter.prepareFailView("Insufficient quantity in account");
        } else {
            SellOutputData sellOutputData = new SellOutputData();
            sellPresenter.prepareSuccessView(sellOutputData);
        }
    }
}
