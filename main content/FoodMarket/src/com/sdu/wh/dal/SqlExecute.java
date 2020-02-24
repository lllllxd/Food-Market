package com.sdu.wh.dal;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.sdu.wh.dao.*;

/**
 * @author ��Ϊ
 * ����Ϊ���ݷ��ʲ�����ݿ�����࣬������һЩ���ݿ�Ĳ�������
 * ��Щ��������������ݿ������������Ӧ����Ϣ
 * �ڱ��ֲ�ֻ����ø����еķ���ֱ�ӻ�ý������
 * �ڷ������ݿ�֮ǰ��Ӧ���ȵ��� getStatement() ������ʼ��ִ��������
 * ִ�������ݿ������������Ӧ���ݺ�Ӧ���� closeAll()�����ر�Statement��Connection
 * ע�����ʹ���� ResultSet�����Լ��ֶ��ر�
 */

/**
 * ���ڶ�����������޸ģ�������Ϊһ�����õ����ݿ�����࣬
 * ���Թ����ݲ�ĸ�������ý������ݿ����
 * ���Խ����ԡ�����������һЩ�޸�
 */
public class SqlExecute {
	//����ĳ�Ա���ĳ� public ���ˣ��������������
	//�����ϸ���˵Ӧ��ʹ�����Է���
	public Connection conn;  	//���ݿ����Ӷ���
	public Statement stmt;   	//���ִ�ж���
	public ResultSet rset;		//�����

	/**
	 * ���������ݿ����Ӳ����һ��Statement���������øö���ֱ�ӽ������ݿ����
	 * ���Ƿ������ݿ⣬Ӧ������ͨ���÷������һ�� Statement
	 * stmt Ϊ��Ա������Ϊ�丳ֵ���ɣ����践��ֵ
	 * @throws Exception
	 */
	public void getStatement() throws Exception {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");   
			//����Ҫ�����Լ�ʵ�����ݿ��������޸�����������ַ���
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://192.168.43.71:1433;DatabaseName=FoodMarket;user=sa;password=123456;");
			stmt = conn.createStatement();
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	//�ر�ʹ��������ݿ����ӣ���������ݿ������Ӧ�õ��ô˷����ر�����
	public void closeAll() throws SQLException {
		try {
			//��������Ƿ��ѹرգ����û�йرգ���ر�
			//û��ʹ�� isClosed()�����жϣ�ʹ��������
			//����Ѷ����ӵ��� close �����������һЩ���������ӹرա����ڵ����� close ��������ô˷���ʱ�����ŷ��� true��
			
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
	 * ִ�зǲ�ѯ��䣬���Ƿǲ�ѯ������ݿ���������Ե��ø÷���
	 * @param sql ������Ҫִ�е�sql ���
	 * @return ���أ���Ӱ��ļ�¼�������Ը��ݸ÷��ؽ���ж�ִ�����
	 * 		   �����0������û���κ����ݿ��¼�����仯
	 * @throws Exception ����������쳣�����쳣�����׳�������ڳ�����ô�Ӧ�ô����쳣
	 */
	public int executeSql(String sql) throws Exception {
		//��¼��Ӱ��ļ�¼��
		int recoders = 0;
		//���� Statement���͵ĳ�Ա���� stmt
		getStatement();
		//ִ�����ݿ����
		recoders = stmt.executeUpdate(sql);
		//�ر����д򿪵����ݿ����
		closeAll();
		
		return recoders;
	}
	
	/**
	 * ִ�����ݿ��ѯ��䣬���һ�� ResultSet�����ô���� JTable��Ȼ�󷵻�JTable����
	 * ������Ҫ�������ݿ���Ϣ���������ʾ��һ�� JTable �У�����ֱ�ӵ��ø÷���
	 * ʹ�õ�ʵ���� StudentList ��Ĺ��췽������Ӧ����
	 * @param sql: ���������ݿ��ѯ�ַ���
	 * @return ���أ�һ�����������ݵ� JTable
	 * @throws Exception
	 */
	public JTable getTable(String sql) throws Exception{
		//���� Statement���͵ĳ�Ա���� stmt
		getStatement();
		//ִ�����ݿ��ѯ����ý����
		rset = stmt.executeQuery(sql);
		
		ResultSetMetaData rsmd=rset.getMetaData();   //����Ԫ���ݶ���
        
		//���������ע�����ֵ����ദ�õ�
		int columns = rsmd.getColumnCount();   
		
        String columntitle[] = new String[columns];        //������������
        for (int j=1; j<=columns; j++)
            columntitle[j-1] = rsmd.getColumnLabel(j);     //�������������������
        
        /**
         * ʹ���˱��ģ�������������������ĺô��ǿ��԰������ֱ���ӱ������
         * 
         * ����������û�õı�������columntitle ����һ�����ģ�壬Ϊ��ָ�����У��ñ���ж�Ӧ����
         */
        DefaultTableModel tm = new DefaultTableModel(columntitle, 0); 

        //���ݽ�����������������飬�������ݽ�����е�һ����¼
        String results[]= new String[columns]; 
        
        //���������������ÿ�ν�һ�м�¼��ӵ� table ��
        //��������ʹ����ģ�壬ֻ������һ�α���
        while (rset.next()) {                               
            for(int i=0; i<columns; i++){
            	//����ǰ�е�ֵ�浽���飬���ﶼ�����ַ�����ʽ��ע�� rset �ļ����Ǵ� 1 ��ʼ
            	//��������ֱ�������ֶε�˳������ֵ��û�����ֶ����ķ�ʽ���������Ժ;����ṹ�޹�
            	results[i] = rset.getString(i+1);	
            }
            
            //��һ�м�¼��ӵ���ģ���У�ע���Ǽӵ���ģ��������Ǳ��
            tm.addRow(results);
        }
        
        JTable tb = new JTable();
        //��ģ���������ѡ�����е����ݾ���ģ���е�
        tb.setModel(tm);

        //��Ҫ�Լ��ֶ��ر� ResultSet
        rset.close();
		//�ر����������򿪵����ݿ����
		closeAll();
		//���ر��
		return tb;
	}
	
	/**
	 * ���������ݲ�ѯ���������ݿ⣬���һ����¼���ɴ������ַ������鲢����
	 * ��ʱ�����ǵĲ�ѯֻ����һ����¼����û��Ҫʹ�ñ���ResultSet
	 * ��ʱ���ǿ���ֻ����һ������������¼��Ӧ�ֶ�ֵ�����飬�ڱ��ֲ���д�����ʾ
	 * @return 
	 * @throws Exception 
	 */
	public String[] getClass(String sql) throws Exception{
		//�Ȳ�ѯ���ݿ�
		getStatement();
		rset = stmt.executeQuery(sql);
		//����Ԫ���ݶ���
		ResultSetMetaData rsmd=rset.getMetaData();   
		//�������
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
	 * ����ķ���������һ�� ResultSet������ֱ���ɷ����׳��쳣���ɵ��ô������쳣
	 * ���ֹر�Ҳ�ڵ��ô�����
	 */
	public ResultSet getResultSet(String sql) throws Exception{
		getStatement();
		//ִ�����ݿ��ѯ����ý����
		rset = stmt.executeQuery(sql);
		return rset;
	}
	
	/**
	 * ��ѯ���ݿ������һ��Object[][]
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
			//cellData�ǵ�һ�е�һ�е�����ֵ
			//getValueAT(a,b)���ص���Object���͵Ķ���
			data[i][j]=cellData;
			}
		}
		return data;
	}

	/**
	 * ���ô����ͻ��洢���̣������ؿͻ����
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
