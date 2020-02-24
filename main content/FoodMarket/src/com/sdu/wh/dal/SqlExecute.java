package com.sdu.wh.dal;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.sdu.wh.dao.*;

/**
 * @author 王为
 * 本类为数据访问层的数据库操作类，集成了一些数据库的操作方法
 * 这些方法负责访问数据库操作并返回相应的信息
 * 在表现层只需调用该类中的方法直接获得结果即可
 * 在访问数据库之前，应该先调用 getStatement() 方法初始化执行语句对象
 * 执行完数据库操作、处理相应数据后，应调用 closeAll()方法关闭Statement和Connection
 * 注意如果使用了 ResultSet，需自己手动关闭
 */

/**
 * 现在对这个类做了修改，把它作为一个公用的数据库操作类，
 * 可以供数据层的各个类调用进行数据库操作
 * 所以将属性、方法等做了一些修改
 */
public class SqlExecute {
	//下面的成员都改成 public 的了，便于其他类访问
	//但是严格来说应该使用属性方法
	public Connection conn;  	//数据库连接对象
	public Statement stmt;   	//语句执行对象
	public ResultSet rset;		//结果集

	/**
	 * 创建了数据库连接并获得一个Statement，可以利用该对象直接进行数据库操作
	 * 凡是访问数据库，应该首先通过该方法获得一个 Statement
	 * stmt 为成员变量，为其赋值即可，无需返回值
	 * @throws Exception
	 */
	public void getStatement() throws Exception {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");   
			//你需要根据自己实际数据库的情况，修改这里的连接字符串
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://192.168.43.71:1433;DatabaseName=FoodMarket;user=sa;password=123456;");
			stmt = conn.createStatement();
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	//关闭使用完的数据库连接，当完成数据库操作后应该调用此方法关闭连接
	public void closeAll() throws SQLException {
		try {
			//检查连接是否已关闭，如果没有关闭，则关闭
			//没有使用 isClosed()方法判断，使用受限制
			//如果已对连接调用 close 方法，或出现一些错误，则连接关闭。仅在调用了 close 方法后调用此方法时，它才返回 true。
			
			if( stmt != null ) {
				stmt.close();
				stmt = null;				
			}
			if( conn != null ) { 
				conn.close();
				conn = null;
			}
		}
		catch(SQLException se) {
			throw se;
		}
	}
	
	/**
	 * 执行非查询语句，凡是非查询类的数据库操作，可以调用该方法
	 * @param sql 参数：要执行的sql 语句
	 * @return 返回：受影响的记录数，可以根据该返回结果判断执行情况
	 * 		   如果是0，表明没有任何数据库记录发生变化
	 * @throws Exception 如果出现了异常，将异常继续抛出，因此在程序调用处应该处理异常
	 */
	public int executeSql(String sql) throws Exception {
		//记录受影响的记录数
		int recoders = 0;
		//设置 Statement类型的成员变量 stmt
		getStatement();
		//执行数据库操作
		recoders = stmt.executeUpdate(sql);
		//关闭所有打开的数据库对象
		closeAll();
		
		return recoders;
	}
	
	/**
	 * 执行数据库查询语句，获得一个 ResultSet，并用此填充 JTable，然后返回JTable对象
	 * 凡是需要访问数据库信息并将结果显示在一个 JTable 中，可以直接调用该方法
	 * 使用的实例见 StudentList 类的构造方法中相应代码
	 * @param sql: 参数，数据库查询字符串
	 * @return 返回：一个已填充好数据的 JTable
	 * @throws Exception
	 */
	public JTable getTable(String sql) throws Exception{
		//设置 Statement类型的成员变量 stmt
		getStatement();
		//执行数据库查询，获得结果集
		rset = stmt.executeQuery(sql);
		
		ResultSetMetaData rsmd=rset.getMetaData();   //返回元数据对象
        
		//获得列数，注意这个值后面多处用到
		int columns = rsmd.getColumnCount();   
		
        String columntitle[] = new String[columns];        //创建列名数组
        for (int j=1; j<=columns; j++)
            columntitle[j-1] = rsmd.getColumnLabel(j);     //获得列名填充表格标题数组
        
        /**
         * 使用了表格模板对象来创建表格，这样的好处是可以按行来分别添加表格数据
         * 
         * 下面语句先用获得的标题数组columntitle 定义一个表的模板，为其指定了列，该表具有对应的列
         */
        DefaultTableModel tm = new DefaultTableModel(columntitle, 0); 

        //根据结果集的列数创建数组，保存数据结果集中的一条记录
        String results[]= new String[columns]; 
        
        //迭代遍历结果集，每次将一行记录添加到 table 中
        //这里由于使用了模板，只进行了一次遍历
        while (rset.next()) {                               
            for(int i=0; i<columns; i++){
            	//将当前行的值存到数组，这里都用了字符串格式，注意 rset 的计数是从 1 开始
            	//另外这里直接用了字段的顺序来赋值。没有用字段名的方式，这样可以和具体表结构无关
            	results[i] = rset.getString(i+1);	
            }
            
            //将一行记录添加到表模板中，注意是加到了模板里，而不是表格
            tm.addRow(results);
        }
        
        JTable tb = new JTable();
        //用模板填充表格，则选择表格中的数据就是模板中的
        tb.setModel(tm);

        //需要自己手动关闭 ResultSet
        rset.close();
		//关闭所有其它打开的数据库对象
		closeAll();
		//返回表格
		return tb;
	}
	
	/**
	 * 本方法根据查询语句访问数据库，获得一条记录并由此生成字符串数组并返回
	 * 有时候我们的查询只返回一条记录，就没必要使用表格或ResultSet
	 * 这时我们可以只返回一个保存这条记录相应字段值的数组，在表现层进行处理、显示
	 * @return 
	 * @throws Exception 
	 */
	public String[] getClass(String sql) throws Exception{
		//先查询数据库
		getStatement();
		rset = stmt.executeQuery(sql);
		//返回元数据对象
		ResultSetMetaData rsmd=rset.getMetaData();   
		//获得列数
		int columns = rsmd.getColumnCount();
		String[] colData = new String[columns];
		rset.next();
		for(int i=0;i<columns;i++) {
			colData[i]=rset.getString(i+1);
		}
		closeAll();
		return colData;
	}
	
	
	/**
	 * 补充的方法，返回一个 ResultSet，这里直接由方法抛出异常，由调用处处理异常
	 * 各种关闭也在调用处进行
	 */
	public ResultSet getResultSet(String sql) throws Exception{
		getStatement();
		//执行数据库查询，获得结果集
		rset = stmt.executeQuery(sql);
		return rset;
	}
	
	/**
	 * 查询数据库表，返回一个Object[][]
	 * @throws Exception 
	 */
	public static Object[][] getObject(String sql) throws Exception{
		SqlExecute se= new SqlExecute();
		JTable t=new JTable();
		t = se.getTable(sql);
		Object[][] data = new Object[t.getRowCount()][t.getColumnCount()];
		for(int i=0;i<t.getRowCount();i++) {
			for(int j=0;j<t.getColumnCount();j++) {
			DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
			String cellData=(String) tableModel.getValueAt(i,j);
			//cellData是第一行第一列的数据值
			//getValueAT(a,b)返回的是Object类型的对象
			data[i][j]=cellData;
			}
		}
		return data;
	}

	/**
	 * 调用创建客户存储过程，并返回客户编号
	 * @throws Exception 
	 */
	public String getProcReturn(String name,int level,String detail) throws Exception{ 
		getStatement();
		CallableStatement proc = null;  
		proc = conn.prepareCall("{ call  pr_CreateCustomer(?,?,?,?)}");  
		proc.setString(1,name);  
		proc.setInt(2, level);
		proc.setString(3, detail);
		proc.registerOutParameter(4, Types.INTEGER);  
		proc.execute();  
		int mReturn = proc.getInt(4); 
		return Integer.toString(mReturn);
	}

	/*
	public static void main(String[] arg) throws Exception {
		SqlExecute se=new SqlExecute();
		se.getStatement();
		se.closeAll();
	}
	*/
}
