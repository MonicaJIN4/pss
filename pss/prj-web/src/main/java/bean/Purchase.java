package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Purchase {
    private int purId;
    private int supId;
    private String supName;
    private int cargoId;
    private String cargoName;
    private int number;
    private BigDecimal buyPrice;
    private BigDecimal total;
    private Date date;


    private BigDecimal amount;

    private int status;

    private String unit;

    private List<String> tableName = new ArrayList<String>();

    public Purchase () {
        tableName.add("采购单单号");
        tableName.add("供应商编号");
        tableName.add("供应商名称");
        tableName.add("商品编号");
        tableName.add("商品名称");
        tableName.add("商品数量");
        tableName.add("商品单价");
        tableName.add("商品总价");
        tableName.add("采购日期");
    }

    public void setTableName(List<String> tableName) {
        this.tableName = tableName;
    }

    public List<String> getTableName() {
        return tableName;
    }


    public int getPurId() {
        return purId;
    }

    public String getSupName() {
        return supName;
    }

    public String getCargoName() {
        return cargoName;
    }

    public int getNumber() {
        return number;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }




    public void setPurId(int purId) {
        this.purId = purId;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setDate(Date date) {
        this.date = date;
    }




    public int getSupId() {
        return supId;
    }

    public void setSupId(int supId) {
        this.supId = supId;
    }




    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }



    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
