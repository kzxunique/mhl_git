package cn.mhl.controller;

import cn.mhl.entity.Bill;
import cn.mhl.entity.DiningTable;
import cn.mhl.entity.Employee;
import cn.mhl.entity.Menu;
import cn.mhl.service.BillService;
import cn.mhl.service.DiningTableService;
import cn.mhl.service.EmployeeService;
import cn.mhl.service.MenuService;
import cn.mhl.utils.Utility;

import java.util.List;

/**
 * @author: 康中孝
 * @create: 2022/1/29 13:12
 * @version: 1.0
 * @description: 这是主界面
 */
public class MHLView {
    /**
     * 控制是否退出菜单
     */
    private boolean loop = true;
    private String key = ""; //接受用户的选择
    private EmployeeService employeeService = new EmployeeService();
    private DiningTableService diningTableService = new DiningTableService();
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }

    /**
     * 显示主菜单
     */
    public void mainMenu() {
        while (loop) {
            System.out.println("=======满汉楼=======");
            System.out.println("\t\t1、登录满汉楼");
            System.out.println("\t\t2、退出满汉楼");
            System.out.print("请输入你的选择：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.print("请输入员工编号：");
                    String empId = Utility.readString(20);
                    System.out.print("请输入密   码：");
                    String password = Utility.readString(20);
                    Employee employee = employeeService.getEmployeeByIdAndPassword(empId, password);
                    if (employee!=null){
                        System.out.println("=======登录成功[" + employee.getName() + "]=======\n");

                        while (loop) {
                            System.out.println("==========满汉楼二级菜单=========");
                            System.out.println("\t\t1、显示餐桌状态");
                            System.out.println("\t\t2、预定餐桌");
                            System.out.println("\t\t3、显示所有菜品");
                            System.out.println("\t\t4、点餐服务");
                            System.out.println("\t\t5、查看账单");
                            System.out.println("\t\t6、结账");
                            System.out.println("\t\t8、xxx");
                            System.out.println("\t\t8、yyy");
                            System.out.println("\t\t9、退出满汉楼");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    listDiningTable();
                                    break;
                                case "2":
                                    orderDiningTable();
                                    break;
                                case "3":
                                    listMenu();
                                    break;
                                case "4":
                                    orderMenu();
                                    break;
                                case "5":
                                    listBill();
                                    break;
                                case "6":
                                    payBill();
                                    break;
                                case "7":
                                    System.out.println("xxx");
                                    break;
                                case "8":
                                    System.out.println("yyy");
                                    break;
                                case "9":
                                    System.out.println("退出满汉楼系统~~");
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("你输入错误，请重新输入");
                            }
                        }
                    } else {
                        System.out.println("=======账号或者密码错误=======");
                    }
                    break;
                case "2":
                    loop = false;
                    break;
                default:
                    System.out.println("你输入错误，请重新输入");
            }
        }
    }

    //显示所有餐桌状态
    public void listDiningTable() {
        List<DiningTable> list = diningTableService.list();
        System.out.println("餐桌编号\t\t餐桌状态");
        for (DiningTable diningTable : list) {
            System.out.println(diningTable);
        }
        System.out.println("============显示完毕=============\n");
    }

    //完成订座
    public void orderDiningTable() {
        System.out.println("============预定餐桌=============");
        System.out.print("请选择需要预定的餐桌编号（-1退出）：");
        int orderId = Utility.readInt();
        if (orderId == -1) {
            System.out.println("============放弃预定餐桌=============");
            return;
        }
        //该方法得到的是Y或者N
        char key = Utility.readConfirmSelection();
        if (key == 'Y') { //要预定
            //根据orderId 返回 对应的DiningTable对象，如果为null 说明该对象不存在
            DiningTable diningTable = diningTableService.getDiningTableById(orderId);
            if (diningTable == null) {
                System.out.println("============预定餐桌不存在============");
                return;
            }
            //判断该餐桌的状态是否为“空”
            if (!("空".equals(diningTable.getState()))) {
                System.out.println("============该餐桌也被预定============");
                return;
            }
            //可以预定餐桌，更新数据
            System.out.print("请输入预定人的名字：");
            String orderName = Utility.readString(20);
            System.out.print("请输入预订人的电话：");
            String orderTel = Utility.readString(20);

            if (diningTableService.orderDiningTable(orderId, orderName, orderTel)) {
                System.out.println("============餐桌预定成功============");
            } else {
                System.out.println("============餐桌预定失败============");
            }
        } else {
            System.out.println("============放弃预定餐桌=============");
            return;
        }
    }

    //显示所有菜品
    public void listMenu() {
        List<Menu> list = menuService.list();
        System.out.println("菜品编号\t\t菜品名\t\t类别\t\t价格");
        for (Menu menu : list) {
            System.out.println(menu);
        }
        System.out.println("============显示完毕=============\n");
    }

    //完成点餐
    public void orderMenu() {
        System.out.println("============点餐服务=============");
        System.out.print("请输入点餐桌号(-1退出)：");
        int orderDiningTableId = Utility.readInt();
        if (orderDiningTableId == -1) {
            System.out.println("============取消点餐=============");
            return;
        }
        //验证餐桌号是否存在
        DiningTable diningTableById = diningTableService.getDiningTableById(orderDiningTableId);
        if (diningTableById == null) {
            System.out.println("============餐桌号不存在============");
            return;
        }
        System.out.print("请输入菜品编号(-1退出)：");
        int orderMenuId = Utility.readInt();
        if (orderMenuId == -1) {
            System.out.println("============取消点餐=============");
            return;
        }
        //验证菜品是否存在
        Menu menuById = menuService.getMenuById(orderMenuId);
        if (menuById == null) {
            System.out.println("============菜品不存在============");
            return;
        }
        System.out.print("请输入菜品数量(-1退出)：");
        int orderNums = Utility.readInt();
        if (orderNums == -1) {
            System.out.println("============取消点餐=============");
            return;
        }
        //点餐
        if (billService.orderMenu(orderMenuId, orderNums, orderDiningTableId)) {
            System.out.println("============点餐成功============");
        } else {
            System.out.println("============点餐失败============");
        }
    }

    //显示账单
    public void listBill() {
        List<Bill> bills = billService.list();
        System.out.println("编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
        for (Bill bill : bills) {
            System.out.println(bill);
        }
        System.out.println("============显示完毕=============\n");
    }

    //完成结账
    public void payBill() {
        System.out.println("============结账服务=============");
        System.out.print("请输入结账餐桌编号(-1退出)：");
        int diningTableId = Utility.readInt();
        if (diningTableId == -1) {
            System.out.println("============取消结账=============");
            return;
        }
        //验证餐桌是否存在
        DiningTable diningTableById = diningTableService.getDiningTableById(diningTableId);
        if (diningTableById == null) {
            System.out.println("============结账餐桌不存在=============");
            return;
        }
        //验证是否有结账账单
        if (!billService.hasPayBillByDiningTableId(diningTableId)) {
            System.out.println("============结账账单不存在=============");
            return;
        }
        System.out.print("结账方式(现金/微信/支付宝)：");
        String payMode = Utility.readString(20, "");
        if ("".equals(payMode)) {
            System.out.println("============取消结账=============");
            return;
        }
        char key = Utility.readConfirmSelection();
        if (key == 'Y') {
            if (billService.payBill(payMode, diningTableId)) {
                System.out.println("============结账成功=============");
            } else {
                System.out.println("============结账失败=============");
            }
        } else {
            System.out.println("============取消结账=============");
        }


    }
    }
