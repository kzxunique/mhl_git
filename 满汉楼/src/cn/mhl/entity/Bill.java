package cn.mhl.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 账单表(Bill)实体类
 *
 * @author KangZhongXiao
 * @since 2022-01-29 18:08:29
 */
public class Bill implements Serializable {
    private static final long serialVersionUID = -63029661714600908L;
    /**
     * 编号id
     */
    private Integer id;
    /**
     * 账单编号
     */
    private String billId;
    /**
     * 菜品编号
     */
    private Integer menuId;
    /**
     * 菜品数量
     */
    private Integer nums;
    /**
     * 金额
     */
    private double money;
    /**
     * 餐桌号
     */
    private Integer diningTableId;
    /**
     * 订单日期
     */
    private Date billDate;
    /**
     * 订单状态
     */
    private String state;

    public Bill(Integer id, String billId, Integer menuId, Integer nums, double money, Integer diningTableId, Date billDate, String state) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
    }

    public Bill() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return  id +
                "\t\t" + menuId +
                "\t\t" + nums +
                "\t\t\t" + money +
                "\t" + diningTableId +
                "\t" + billDate +
                "\t\t\t" + state;
    }
}

