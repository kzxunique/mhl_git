package cn.mhl.service;

import cn.mhl.dao.EmployeeDao;
import cn.mhl.entity.Employee;

/**
 * @author: 康中孝
 * @create: 2022/1/29 13:12
 * @version: 1.0
 * @description: 该类完成对Employee表的各种操作（通过调用EmployeeDAO对象完成）
 */
public class EmployeeService {
    //定义一个EmployeeDAO 属性
    private EmployeeDao employeeDao = new EmployeeDao();

    //方法，根据empId 和 password 返回一个Employee对象
    public Employee getEmployeeByIdAndPassword(String empId, String password) {
        Employee employee = employeeDao.querySingle("select * from employee where emp_id = ? and password = md5(?)", Employee.class, empId, password);

        return employee;
    }
}
