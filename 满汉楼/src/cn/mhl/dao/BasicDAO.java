package cn.mhl.dao;

import cn.mhl.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: 康中孝
 * @create: 2022/1/28 21:19
 * @version: 1.0
 * @description:
 */
public class BasicDAO<T> {
    private QueryRunner qr = new QueryRunner();

    public int update(String sql, Object... parameters) {
        Connection conn = null;

        try {
            conn = DruidUtil.getConnection();
            int update = qr.update(conn, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.close(null,null,conn);
        }
    }

    //返回多个对象
    public List<T> queryMulti(String sql,Class<T> clazz,Object... parameters) {
        Connection conn = null;
        try {
            conn = DruidUtil.getConnection();
            return qr.query(conn, sql, new BeanListHandler<T>(clazz), parameters);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.close(null,null,conn);
        }
    }

    //查询单行结果的方法
    public T querySingle(String sql,Class<T> clazz,Object... parameters) {
        Connection conn = null;
        try {
            conn = DruidUtil.getConnection();
            return qr.query(conn, sql, new BeanHandler<T>(clazz), parameters);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.close(null,null,conn);
        }
    }

    //查询单行单列的方法
    public Object queryScalar(String sql,Object... parameters) {
        Connection conn = null;
        try {
            conn = DruidUtil.getConnection();
            return qr.query(conn, sql, new ScalarHandler(), parameters);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.close(null,null,conn);
        }
    }
}
