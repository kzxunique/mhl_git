package cn.mhl.entity;

import java.io.Serializable;

/**
 * 用户表(Employee)实体类
 *
 * @author KangZhongXiao
 * @since 2022-01-29 15:11:41
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 477508052842230452L;
    /**
     * ID
     */
    private Integer id;
    /**
     * 员工编号
     */
    private String empId;
    /**
     * 密码md5
     */
    private String password;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 岗位
     */
    private String job;

    public Employee() {
    }

    public Employee(Integer id, String empId, String password, String name, String job) {
        this.id = id;
        this.empId = empId;
        this.password = password;
        this.name = name;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee {" +
                "id : " + id + ", " +
                "empId : " + empId + ", " +
                "password : " + password + ", " +
                "name : " + name + ", " +
                "job : " + job + ", " +
                '}';
    }
}

