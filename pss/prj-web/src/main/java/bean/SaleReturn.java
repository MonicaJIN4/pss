package bean;

import java.math.BigDecimal;
import java.util.Date;

public class SaleReturn {
    private int reSaleId;
    private int saleId;
    private int cargoId;
    private int number;
    private BigDecimal price;
    private String remark;
    private Date returnDate;

    private BigDecimal total;

    //cargo表
    private String cargoName;
    private String unit;

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public void setReSaleId(int reSaleId) {
        this.reSaleId = reSaleId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getSaleId() {
        return saleId;
    }

    public int getReSaleId() {
        return reSaleId;
    }

    public int getCargoId() {
        return cargoId;
    }

    public int getNumber() {
        return number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getRemark() {
        return remark;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
