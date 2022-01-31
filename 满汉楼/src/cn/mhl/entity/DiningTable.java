package cn.mhl.entity;

import java.io.Serializable;

/**
 * 餐桌表(Diningtable)实体类
 *
 * @author KangZhongXiao
 * @since 2022-01-29 15:53:31
 */
public class DiningTable implements Serializable {
    private static final long serialVersionUID = 272727647348525221L;
    /**
     * 餐桌编号
     */
    private Integer id;
    /**
     * 餐桌状态
     */
    private String state;
    /**
     * 预定人姓名
     */
    private String orderName;
    /**
     * 预定人电话
     */
    private String orderTel;

    public DiningTable() {
    }

    public DiningTable(Integer id, String state, String orderName, String orderTel) {
        this.id = id;
        this.state = state;
        this.orderName = orderName;
        this.orderTel = orderTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    @Override
    public String toString() {
        return id + "\t\t\t" + state;
    }
}

