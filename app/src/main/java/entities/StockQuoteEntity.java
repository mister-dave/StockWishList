package entities;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;


public class StockQuoteEntity {
    @DatabaseField(id = true)
    @SerializedName("symbol")
    private String symbol;
    @DatabaseField
    @SerializedName("AverageDailyVolume")
    private String averageDailyVolume;
    @DatabaseField
    @SerializedName("Change")
    private String change;
    @DatabaseField
    @SerializedName("DaysLow")
    private String daysLow;
    @DatabaseField
    @SerializedName("DaysHigh")
    private String daysHigh;
    @DatabaseField
    @SerializedName("YearLow")
    private String yearLow;
    @DatabaseField
    @SerializedName("YeahHigh")
    private String yearHigh;
    @DatabaseField
    @SerializedName("LastTradePriceOnly")
    private String lastTradePriceOnly;
    @DatabaseField
    @SerializedName("Name")
    private String name;


    public void setType(String type) {
        this.type = type;
    }

    @DatabaseField
    @SerializedName("type")
    private String type;

    public StockQuoteEntity() {
    }

    public StockQuoteEntity(String symbol, String averageDailyVolume, String change, String daysLow, String daysHigh, String yearLow, String yearHigh, String lastTradePriceOnly, String name) {
        this.symbol = symbol;
        this.averageDailyVolume = averageDailyVolume;
        this.change = change;
        this.daysLow = daysLow;
        this.daysHigh = daysHigh;
        this.yearLow = yearLow;
        this.yearHigh = yearHigh;
        this.lastTradePriceOnly = lastTradePriceOnly;
        this.name = name;
    }

    public String getAverageDailyVolume() {
        return averageDailyVolume;
    }

    public void setAverageDailyVolume(String averageDailyVolume) {
        this.averageDailyVolume = averageDailyVolume;
    }

    public String getType() {
        return type;
    }

    public String getYearLow() {
        return yearLow;
    }

    public void setYearLow(String yearLow) {
        this.yearLow = yearLow;
    }

    public String getYearHigh() {
        return yearHigh;
    }

    public void setYearHigh(String yearHigh) {
        this.yearHigh = yearHigh;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getDaysLow() {
        return daysLow;
    }

    public void setDaysLow(String daysLow) {
        this.daysLow = daysLow;
    }

    public String getDaysHigh() {
        return daysHigh;
    }

    public void setDaysHigh(String daysHigh) {
        this.daysHigh = daysHigh;
    }

    public String getLastTradePriceOnly() {
        return lastTradePriceOnly;
    }

    public void setLastTradePriceOnly(String lastTradePriceOnly) {
        this.lastTradePriceOnly = lastTradePriceOnly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
