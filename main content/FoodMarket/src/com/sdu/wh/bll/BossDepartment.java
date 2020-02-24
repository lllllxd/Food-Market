package com.sdu.wh.bll;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import com.sdu.wh.dal.FinanceDAL;
import com.sdu.wh.dal.Manager;

/**
 * ��ҵ�������߼���
 * 
 * @author Gale
 *
 */
public class BossDepartment {

	private Object[][] mData = null;

	private String[] str1 = new String[] { "�������", "�ͻ����", "���׽��", "��������", "������" };
	private String[] str2 = new String[] { "�������", "�ͻ����", "�������", "��������", "������" };
	private String[] str3 = new String[] { "�ƻ����", "��Ʒ����", "��������", "��������", "������", "��Ʒ���" };
	private String[] str4 = new String[] { "�����", "��Ʒ����", "��������", "��Ʒ���", "������" };
	private String[] str5 = new String[] { "������", "��Ʒ����", "��������", "��Ʒ���", "������" };
	private String[] str6 = new String[] { "�ƻ����", "����״̬", "������" };
	private String[] str7 = new String[] { "���ʱ��", "��Ʒ���", "��Ʒ����", "��������" };

	/**
	 * ������ѡ����ˢ�±��
	 * 
	 * @param table
	 * @param selectStr
	 * @return
	 */
	public JTable refreashTable(JTable table, String selectStr) {

		String[] tableHeader = str1;

		if (selectStr.equals("������¼")) {
			tableHeader = str1;
			Object[][] edata = null;
			try {
				edata = FinanceDAL.getFinanceTableData();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "��ȡʧ��");
				e1.printStackTrace();
			}
			try {
				mData = Manager.getOrderForm();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "��ȡʧ��");
				e.printStackTrace();
			}
			int row = edata.length;
			for (int i = 0; i < row; i++) {
				mData[i][2] = edata[i][4];
			}

		}
		if (selectStr.equals("�������¼")) {
			tableHeader = str2;
			try {
				mData = Manager.getBillOfLading();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "��ȡʧ��");
				e.printStackTrace();
			}
		}
		if (selectStr.equals("�����ƻ���¼")) {
			tableHeader = str3;
			try {
				mData = Manager.getProductionSchedule();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "��ȡʧ��");
				e.printStackTrace();
			}
		}
		if (selectStr.equals("����¼")) {
			tableHeader = str4;
			try {
				mData = Manager.getInTable();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "��ȡʧ��");
				e.printStackTrace();
			}
		}
		if (selectStr.equals("�����¼")) {
			tableHeader = str5;
			try {
				mData = Manager.getOutTable();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "��ȡʧ��");
				e.printStackTrace();
			}
		}
		if (selectStr.equals("������¼")) {
			tableHeader = str6;
			try {
				mData = Manager.getProductRecord();
				String[] s = {"δ����","��������","�������","�����"};
				int row=mData.length;
				for(int i=0;i<row;i++) {
					mData[i][1]=s[Integer.parseInt((String)mData[i][1])];
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "��ȡʧ��");
				e.printStackTrace();
			}
		}
		if (selectStr.equals("���")) {
			tableHeader = str7;
			try {
				mData = Manager.getInventory();
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "��ȡʧ��");
				e.printStackTrace();
			}
		}

		if (mData == null || mData.length == 0) {
			mData = new Object[][] {};
		}

		table.setModel(new DefaultTableModel(mData, tableHeader) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		return table;
	}
}
