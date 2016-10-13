package facades;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.StockQuoteDAO;
import entities.StockQuoteEntity;
import services.StockService;
import utils.Callback;
import utils.Task;

@Singleton
public class StockFacade {
    private static final String QUOTES = "quotes";
    private StockService mStockService;
    @Inject
    StockQuoteDAO stockQuoteDAO;

    public StockFacade() {
        mStockService = new StockService();
    }


    public void getSingleStock(@NonNull final String stockSymbol, @Nullable final Callback<StockQuoteEntity> callback) {

        new Task<StockQuoteEntity>(new Task.RunOnBackground<StockQuoteEntity>() {
            @Override
            public StockQuoteEntity runInBackground() throws Exception {
                StockQuoteEntity entity = mStockService.fetchStocks(stockSymbol);

                try {
                    stockQuoteDAO.createOrUpdate(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return entity;
            }
        }, callback).execute();
    }

}
