package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Stock {

    private int stockId;
    private String cargoName;
    private int number;
    private double safetyStock;
    private String unit;
    private Date buyDate;
    private Date sellDate;
    private int cargoId;
    private List<String> tableName = new ArrayList<String>();

    public Stock () {
        tableName.add("库存编号");
        tableName.add("商品名称");
        tableName.add("当前数量");
        tableName.add("安全存量");
        tableName.add("商品单位");

    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public int getStockId() {
        return stockId;
    }

    public int getNumber() {
        return number;
    }

    public int getCargoId() {
        return cargoId;
    }

    public double getSafetyStock() {
        return safetyStock;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public void setSafetyStock(double safetyStock) {
        this.safetyStock = safetyStock;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}


