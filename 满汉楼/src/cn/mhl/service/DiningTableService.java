package cn.mhl.service;


import cn.mhl.dao.DiningTableDao;
import cn.mhl.entity.DiningTable;

import java.util.List;

/**
 * @author: KangZhongXiao
 */
public class DiningTableService {
    //定义一个DiningTableDao对象
    private DiningTableDao diningTableDao = new DiningTableDao();

    //返回餐桌信息
    public List<DiningTable> list() {
        List<DiningTable> diningTables = diningTableDao.queryMulti("select id,state from diningtable", DiningTable.class);
        return diningTables;
    }

    //根据id，查询对应的餐桌DiningTable对象
    //如果返回null，表示id对应得餐桌不存在
    public DiningTable getDiningTableById(int id) {
        return diningTableDao.querySingle("select * from diningtable where id = ?", DiningTable.class, id);
    }

    //如果餐桌可以预定，调用方法，对其状态更新（包括预订人名字，电话）
    public boolean orderDiningTable(int id,String orderName,String orderTel) {
        int update = diningTableDao.update("update diningtable set state='已预定',order_name = ?,order_tel = ? where id = ?", orderName, orderTel, id);
        return update > 0;
    }

    //更新餐桌状态的方法
    public boolean updateDiningTableState(int id, String state) {
        int update = diningTableDao.update("update diningtable set state = ? where id = ?", state, id);
        return update > 0;
    }

    public boolean updateDiningTableToFree(int id, String state) {
        int update = diningTableDao.update("update diningtable set state = ?,order_name = '',order_tel = '' where id = ?", state, id);
        return update > 0;
    }
}
