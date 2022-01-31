package cn.mhl.service;

import cn.mhl.dao.BillDao;
import cn.mhl.entity.Bill;

import java.util.List;
import java.util.UUID;

/**
 * @author: 康中孝
 * @create: 2022/1/29 18:11
 * @version: 1.0
 * @description:
 */
public class BillService {
    //定义一个BillDao对象
    private BillDao billDao = new BillDao();
    //定义MenuService属性
    private MenuService menuService = new MenuService();
    //定义DiningTableService属性
    private DiningTableService diningTableService = new DiningTableService();
    //编写点餐的方法
    //1、生成账单
    //2、需要更新对应得餐桌状态
    //3、如果成功返回true,否则false
    public boolean orderMenu(int menuId,int nums,int diningTablesId) {
        //1、生成一个账单号 UUID
        String billId = UUID.randomUUID().toString();
        //账单金额
        double price = (double) menuService.getMenuById(menuId).getPrice();

        //将账单生成到bill表
        int update = billDao.update("insert into bill values(null,?,?,?,?,?,now(),'未结账')", billId, menuId, nums, price * nums, diningTablesId);
        if (update <= 0) {
            return false;
        }
        //更新对应餐桌状态
        return diningTableService.updateDiningTableState(diningTablesId, "就餐中");
    }

    //返回所有账单
    public List<Bill> list() {
        return billDao.queryMulti("select * from bill", Bill.class);
    }

    //查看某餐桌是否结账
    public boolean hasPayBillByDiningTableId(int diningTableId) {
        Bill bill = billDao.querySingle("select * from bill where dining_table_id = ? and state = '未结账' limit 0,1", Bill.class, diningTableId);
        return bill != null;
    }
    //完成结账
    public boolean payBill(String payMode,int diningTableId) {
        //1、修改bill表
        int update = billDao.update("update bill set state = ? where dining_table_id = ? and state = '未结账'",payMode, diningTableId);
        if (update <= 0) {
            return false;
        }
        //2、修改diningTable表
        if (!diningTableService.updateDiningTableToFree(diningTableId, "空")) {
            return false;
        }
        return true;
    }
}
