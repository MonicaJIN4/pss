package bean;

import java.math.BigDecimal;
import java.util.Date;

public class SaleOut {
    private int saleId;
    private int cargoId;
    private int number;
    private BigDecimal price;
    private int staffId;
    private int cusId;
    private Date outDate;
    private BigDecimal total;

    private int status;
    private  String cusName;
    private  String cName;
    private String cargoName;
    private String unit;

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public int getSaleId() {
        return saleId;
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

    public int getStaffId() {
        return staffId;
    }

    public int getCusId() {
        return cusId;
    }

    public Date getOutDate() {
        return outDate;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
