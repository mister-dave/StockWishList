package entities;

import com.google.gson.annotations.SerializedName;


public class HistoricalStockPriceEntity {

    @SerializedName(value = "symbol", alternate = {"Symbol"})
    private String Symbol;

    @SerializedName(value = "date", alternate = {"Date"})
    private int sharesOwned;

    @SerializedName(value = "Adj_Close", alternate = {"adj_close"})
    private String adjustedClose;

    public HistoricalStockPriceEntity() {
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public int getSharesOwned() {
        return sharesOwned;
    }

    public void setSharesOwned(int sharesOwned) {
        this.sharesOwned = sharesOwned;
    }

    public String getAdjustedClose() {
        return adjustedClose;
    }

    public void setAdjustedClose(String adjustedClose) {
        this.adjustedClose = adjustedClose;
    }
}
