package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cargo {
    private int cargoId;
    private String cargoName;
    private double safetyStock;
    private String unit;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;


    private List<String> tableName = new ArrayList<String>();

    public Cargo () {
        tableName.add("商品编号");
        tableName.add("商品名称");
        tableName.add("安全存量");
        tableName.add("商品单位");
        tableName.add("商品建议购价");
        tableName.add("商品建议售价");

    }

    public int getCargoId() {
        return cargoId;
    }

    public String getCargoName() {
        return cargoName;
    }

    public double getSafetyStock() {
        return safetyStock;
    }

    public String getUnit() {
        return unit;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }



    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public void setSafetyStock(double safetyStock) {
        this.safetyStock = safetyStock;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public List<String> getTableName() {
        return tableName;
    }

    public void setTableName(List<String> tableName) {
        this.tableName = tableName;
    }
}
