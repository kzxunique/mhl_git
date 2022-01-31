package cn.mhl.service;

import cn.mhl.dao.MenuDao;
import cn.mhl.entity.Menu;

import java.util.List;

/**
 * @author: 康中孝
 * @create: 2022/1/29 17:29
 * @version: 1.0
 * @description:
 */
public class MenuService {
    //定义MenuDao属性
    private MenuDao menuDao = new MenuDao();
    //返回所有菜品，返回给界面使用
    public List<Menu> list() {
        return menuDao.queryMulti("select * from menu", Menu.class);
    }

    //根据id，返回menu对象
    public Menu getMenuById(int id) {
        return menuDao.querySingle("select * from menu where id = ?", Menu.class, id);
    }
}
