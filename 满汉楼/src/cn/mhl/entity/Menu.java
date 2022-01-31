package cn.mhl.entity;

import java.io.Serializable;

/**
 * 菜谱表(Menu)实体类
 *
 * @author KangZhongXiao
 * @since 2022-01-29 17:28:05
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 687126936063011894L;
    /**
     * 菜谱编号
     */
    private Integer id;
    /**
     * 菜品名称
     */
    private String name;
    /**
     * 菜品类别
     */
    private String type;
    /**
     * 菜品价格
     */
    private Object price;

    public Menu() {
    }

    public Menu(Integer id, String name, String type, Object price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return id + "\t\t\t" + name + "\t\t" + type + "\t\t" + price;
   }
}

