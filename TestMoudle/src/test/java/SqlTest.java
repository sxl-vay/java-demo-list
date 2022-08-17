import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author shxl
 * @data 2022/7/12 20:59
 **/
public class SqlTest {
    public static void main(String[] args) throws Exception {
        //注册驱动  在mysql5之后的版本可以省略不写
        // Class.forName("com.mysql.jdbc.Driver");
        /**
         *  获取连接池对象
         *  三个参数分别是
         *  url连接的路径：【jdbc：mysql：//】  为固定写法  再跟上指定的ip端口号和数据库名称
         *  user：为数据库的用户名
         *  password：为数据库密码
         */
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.11:3306/computer", "root", "root");
        //通过连接池来获取执行SQL的对象
        Statement statement = conn.createStatement();
        //定义要执行了SQL语句
        String sql = "insert into index_study (name,age,create_time,a,b,c)";
        //执行SQL语句并获取返回值【此处返回值为影响语句数目】



    }
}
