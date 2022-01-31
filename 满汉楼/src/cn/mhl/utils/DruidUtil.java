package cn.mhl.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author: 康中孝
 * @create: 2022/1/27 21:02
 * @version: 1.0
 * @description: 基于Druid 数据库连接池的工具类
 */
public class DruidUtil {

    private static DataSource dataSource;

    //在静态代码块完成 ds的初始化
    static {
        try {
            //1.获取配置文件路径
            //根据配置文件的相对路径，获取输入流
            InputStream in = DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            //2.创建properties类
            Properties properties = new Properties();
            //3.根据properties.load(path)  加载配置文件
            properties.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            //1.将编译异常转为运行异常
            //2.调用者可以捕获该异常 也可以默认处理该异常
            throw new RuntimeException(e);
        }
    }

    /**
     * 连接数据库，返回Connection
     */
    public static Connection getConnection() {
        try {
            //创建连接
            return dataSource.getConnection();
        } catch (SQLException e) {
            //1.将编译异常转为运行异常
            //2.调用者可以捕获该异常 也可以默认处理该异常
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭连接
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        //判断是否为空
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            //1.将编译异常转为运行异常
            //2.调用者可以捕获该异常 也可以默认处理该异常
            throw new RuntimeException(e);
        }
    }

}
