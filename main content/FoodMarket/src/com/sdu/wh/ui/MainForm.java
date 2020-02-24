package com.sdu.wh.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import com.sdu.wh.common.MDIDesktopPane;
import com.sdu.wh.ui.boss.Query;
import com.sdu.wh.ui.factory.Produced;
import com.sdu.wh.ui.factory.Producing;
import com.sdu.wh.ui.finance.Gathering;
import com.sdu.wh.ui.finance.Refund;
import com.sdu.wh.ui.manage.CreateManager;
import com.sdu.wh.ui.marketing.CancelOrder;
import com.sdu.wh.ui.marketing.CreateCustomer;
import com.sdu.wh.ui.marketing.CreateOrder;
import com.sdu.wh.ui.marketing.SalesStrategy;
import com.sdu.wh.ui.plan.ProductionSchedule;
import com.sdu.wh.ui.plan.QueryStorage;
import com.sdu.wh.ui.plan.UnfinishedOrder;
import com.sdu.wh.ui.warehouse.ConfirmIn;
import com.sdu.wh.ui.warehouse.ConfirmOut;
import com.sdu.wh.ui.warehouse.Pending;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.xml.stream.events.StartDocument;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.swing.border.EtchedBorder;

/**
 * 主窗口类
 * 
 * @author Gale
 *
 */
public class MainForm extends JFrame {

	private static String sId = "1";
	private static String sName = "猫野";
	public static boolean sLogined = false;
	
	private JPanel contentPane;
	private JButton CxjlBtn;
	private JButton XsbBtn;
	private JButton CwbBtn;
	private JButton ScjhkBtn;
	private JButton SccjBtn;
	private JButton CpkBtn;
	private JButton XtglBtn;
	private JButton QyglBtn;
	private JButton CjkhBtn;
	private JButton CjddBtn;
	private JButton QxddBtn;
	private JButton YxclBtn;
	public JButton Admin;
	
	
	public static MDIDesktopPane desktop = new MDIDesktopPane();
	
	public static String getPersonId() {
		return sId;
	}

	public static String getPersonName() {
		return sName;
	}

	public static void setId(String id) {
		sId = id;
	}

	public static void setPName(String name) {
		sName = name;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 760);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//最大的panel
		JPanel panel_0 = new JPanel();
		//panel_0.setBackground(new Color(97,183,207));
		panel_0.setPreferredSize(new Dimension(800,170));
		panel_0.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel_0, BorderLayout.NORTH);
		
		//导航栏panel
		JPanel panel = new JPanel();
		panel.setBackground(new Color(97,183,207));
		panel.setPreferredSize(new Dimension(800,95));
		panel.setLayout(new BorderLayout(0, 0));
		panel_0.add(panel,BorderLayout.NORTH);
		
		//导航栏panel的west里放大标题的panel
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(97,183,207));
		panel_1.setPreferredSize(new Dimension(320,95));
		panel.add(panel_1,BorderLayout.WEST);
		panel_1.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setRows(2);
		textArea.setBackground(new Color(97,183,207));
		textArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 22));
		textArea.setForeground(Color.WHITE);
		textArea.setText("食品企业销售管理系统\n"+"     Food   Market");
		textArea.setBounds(30, 20, 260, 64);
		panel_1.add(textArea);
		
		//导航栏east放登录登出按钮，最大最小关闭按钮的panel的panel
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.EAST);
		panel_3.setBackground(new Color(97,183,207));
		panel_3.setPreferredSize(new Dimension(330,90));
		panel_3.setLayout(new BorderLayout(1,6));
		
		//上方panel的north放登录登出按钮，最大最小关闭按钮的panel
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2, BorderLayout.NORTH);
		panel_2.setBackground(new Color(97,183,207));
		panel_2.setPreferredSize(new Dimension(330,40));
		panel_2.setLayout(new FlowLayout());
		
		Admin=new JButton();
		Admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sLogined == false) {
					desktop.add(new Login(MainForm.this));
				}
			}
		});
		Admin.setForeground(new Color(255, 255, 255));
		Admin.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		Admin.setContentAreaFilled(false);
		Admin.setBorderPainted(false); 
		Admin.setText("<html><u>Administrator<html><u>");
		panel_2.add(Admin);
		
		JLabel Gap=new JLabel();
		Gap.setForeground(new Color(255, 255, 255));
		Gap.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		Gap.setPreferredSize(new Dimension(10,20));
		Gap.setText("|");
		panel_2.add(Gap);
		
		JButton Logout=new JButton();
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sLogined = false;
				changeButton(false);
				Admin.setText("<html><u>Administrator<html><u>");
			}
		});
		Logout.setHorizontalAlignment(JButton.LEFT);
		Logout.setForeground(new Color(255, 255, 255));
		Logout.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		Logout.setContentAreaFilled(false);
		Logout.setBorderPainted(false); 
		Logout.setText("<html><u>Log out<html><u>");
		panel_2.add(Logout);
		
		//放七个管理部门的panel
		JPanel panel_4 = new JPanel();
		panel_0.add(panel_4, BorderLayout.CENTER);
		panel_4.setBackground(new Color(237,246,248));
		panel_4.setPreferredSize(new Dimension(330,40));
		panel_4.setLayout(new GridLayout(1,7));
		
		XsbBtn = new JButton();
		XsbBtn.setText("销售部");
		XsbBtn.setBackground(new Color(2, 81, 103));
		XsbBtn.setForeground(Color.WHITE);
		XsbBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		panel_4.add(XsbBtn);
		
		CwbBtn = new JButton();
		CwbBtn.setText("财务部");
		CwbBtn.setBackground(new Color(2, 81, 103));
		CwbBtn.setForeground(new Color(255, 255, 255));
		CwbBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		panel_4.add(CwbBtn);
		
		ScjhkBtn = new JButton();
		ScjhkBtn.setText("生产计划科");
		ScjhkBtn.setBackground(new Color(2, 81, 103));
		ScjhkBtn.setForeground(Color.WHITE);
		ScjhkBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		panel_4.add(ScjhkBtn);
		
		SccjBtn = new JButton();
		SccjBtn.setText("生产车间");
		SccjBtn.setBackground(new Color(2, 81, 103));
		SccjBtn.setForeground(Color.WHITE);
		SccjBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		panel_4.add(SccjBtn);
		
		CpkBtn = new JButton();
		CpkBtn.setText("成品库");
		CpkBtn.setBackground(new Color(2, 81, 103));
		CpkBtn.setForeground(Color.WHITE);
		CpkBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		panel_4.add(CpkBtn);
		
		XtglBtn = new JButton();
		
		XtglBtn.setText("系统管理");
		XtglBtn.setBackground(new Color(2, 81, 103));
		XtglBtn.setForeground(Color.WHITE);
		XtglBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		panel_4.add(XtglBtn);
		
		QyglBtn = new JButton();
		QyglBtn.setText("企业管理");
		QyglBtn.setBackground(new Color(2, 81, 103));
		QyglBtn.setForeground(Color.WHITE);
		QyglBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		panel_4.add(QyglBtn);
		
		//放每个部门功能的底层panel
		JPanel panel_5 = new JPanel();
		panel_0.add(panel_5, BorderLayout.SOUTH);
		panel_5.setBackground(new Color(183,223,234));
		panel_5.setPreferredSize(new Dimension(330,40));
		panel_5.setLayout(new GridLayout(1,1));
		//panel_5.setVisible(false);
		
		//分别放每个部门功能的panel
		//销售部
		JPanel Xsbpanel = new JPanel();
		panel_5.add(Xsbpanel);
		Xsbpanel.setBackground(new Color(183,223,234));
		Xsbpanel.setPreferredSize(new Dimension(330,40));
		Xsbpanel.setLayout(new FlowLayout());
		
		XsbBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.removeAll();
				panel_5.add(Xsbpanel);
				panel_5.revalidate();
				panel_5.repaint();
			}
		});
		
		CjkhBtn=new JButton();
		CjkhBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.add(new CreateCustomer());
			}
		});
		CjkhBtn.setForeground(new Color(2, 81, 103));
		CjkhBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		CjkhBtn.setContentAreaFilled(false);
		CjkhBtn.setBorderPainted(false); 
		CjkhBtn.setText("<html><u>创建客户</u></html>");
		CjkhBtn.setPreferredSize(new Dimension(134,30));
		CjkhBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//desktop.add(new CreateManager());
			}
		});
		Xsbpanel.add(CjkhBtn);
		
		JLabel Gap1=new JLabel();
		Gap1.setForeground(new Color(2, 81, 103));
		Gap1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		Gap1.setPreferredSize(new Dimension(10,30));
		Gap1.setText("|");
		Xsbpanel.add(Gap1);
		
		CjddBtn=new JButton();
		CjddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Text co=new Text();
				CreateOrder co=new CreateOrder();
				desktop.add(co);
				
			}
		});
		CjddBtn.setForeground(new Color(2, 81, 103));
		CjddBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		CjddBtn.setContentAreaFilled(false);
		CjddBtn.setBorderPainted(false); 
		CjddBtn.setText("<html><u>创建订单</u></html>");
		CjddBtn.setPreferredSize(new Dimension(134,30));
		Xsbpanel.add(CjddBtn);
		
		JLabel Gap2=new JLabel();
		Gap2.setForeground(new Color(2, 81, 103));
		Gap2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		Gap2.setPreferredSize(new Dimension(10,30));
		Gap2.setText("|");
		Xsbpanel.add(Gap2);
		
		QxddBtn=new JButton();
		QxddBtn.setForeground(new Color(2, 81, 103));
		QxddBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		QxddBtn.setContentAreaFilled(false);
		QxddBtn.setBorderPainted(false); 
		QxddBtn.setText("<html><u>取消订单</u></html>");
		QxddBtn.setPreferredSize(new Dimension(134,30));
		QxddBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					desktop.add(new CancelOrder());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Xsbpanel.add(QxddBtn);
		
		JLabel Gap3=new JLabel();
		Gap3.setForeground(new Color(2, 81, 103));
		Gap3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		Gap3.setPreferredSize(new Dimension(10,30));
		Gap3.setText("|");
		Xsbpanel.add(Gap3);
		
		YxclBtn=new JButton();
		YxclBtn.setForeground(new Color(2, 81, 103));
		YxclBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		YxclBtn.setContentAreaFilled(false);
		YxclBtn.setBorderPainted(false); 
		YxclBtn.setText("<html><u>营销策略</u></html>");
		YxclBtn.setPreferredSize(new Dimension(134,30));
		YxclBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new SalesStrategy());
			}
		});
		Xsbpanel.add(YxclBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		desktop.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(desktop);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		//财务部
		JPanel Cwbpanel = new JPanel();
		//panel_0.add(Xsbpanel, BorderLayout.SOUTH);
		Cwbpanel.setBackground(new Color(183,223,234));
		Cwbpanel.setPreferredSize(new Dimension(330,40));
		Cwbpanel.setLayout(new FlowLayout());
		//Cwbpanel.setVisible(false);
		
		CwbBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.removeAll();
				panel_5.add(Cwbpanel);
				panel_5.revalidate();
				panel_5.repaint();
				
			}
		});
		
		JButton SkBtn=new JButton();
		SkBtn.setForeground(new Color(2, 81, 103));
		SkBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		SkBtn.setContentAreaFilled(false);
		SkBtn.setBorderPainted(false); 
		SkBtn.setText("<html><u>收款</u></html>");
		SkBtn.setPreferredSize(new Dimension(134,30));
		SkBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					desktop.add(new Gathering());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Cwbpanel.add(SkBtn);
		
		JLabel Gap4=new JLabel();
		Gap4.setForeground(new Color(2, 81, 103));
		Gap4.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		Gap4.setPreferredSize(new Dimension(10,30));
		Gap4.setText("|");
		Cwbpanel.add(Gap4);
		
		JButton TkBtn=new JButton();
		TkBtn.setForeground(new Color(2, 81, 103));
		TkBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		TkBtn.setContentAreaFilled(false);
		TkBtn.setBorderPainted(false); 
		TkBtn.setText("<html><u>退款</u></html>");
		TkBtn.setPreferredSize(new Dimension(134,30));
		TkBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new Refund());
			}
		});
		Cwbpanel.add(TkBtn);
		
		//生产计划科
				JPanel Scjhpanel = new JPanel();
				Scjhpanel.setBackground(new Color(183,223,234));
				Scjhpanel.setPreferredSize(new Dimension(330,40));
				Scjhpanel.setLayout(new FlowLayout());
				
				ScjhkBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel_5.removeAll();
						panel_5.add(Scjhpanel);
						panel_5.revalidate();
						panel_5.repaint();
					}
				});
				
				JButton WwcddBtn=new JButton();
				WwcddBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Text co=new Text();
						UnfinishedOrder uo=new UnfinishedOrder();
						desktop.add(uo);
					}
				});
				WwcddBtn.setForeground(new Color(2, 81, 103));
				WwcddBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
				WwcddBtn.setContentAreaFilled(false);
				WwcddBtn.setBorderPainted(false); 
				WwcddBtn.setText("<html><u>未完成订单</u></html>");
				WwcddBtn.setPreferredSize(new Dimension(134,30));
				Scjhpanel.add(WwcddBtn);
				
				JLabel Gap5=new JLabel();
				Gap5.setForeground(new Color(2, 81, 103));
				Gap5.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
				Gap5.setPreferredSize(new Dimension(10,30));
				Gap5.setText("|");
				Scjhpanel.add(Gap5);
				
				JButton CkkcBtn=new JButton();
				CkkcBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Text co=new Text();
						QueryStorage qs=new QueryStorage();
						desktop.add(qs);
					}
				});
				CkkcBtn.setForeground(new Color(2, 81, 103));
				CkkcBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
				CkkcBtn.setContentAreaFilled(false);
				CkkcBtn.setBorderPainted(false); 
				CkkcBtn.setText("<html><u>查看库存</u></html>");
				CkkcBtn.setPreferredSize(new Dimension(134,30));
				Scjhpanel.add(CkkcBtn);
				
				JLabel Gap50=new JLabel();
				Gap50.setForeground(new Color(2, 81, 103));
				Gap50.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
				Gap50.setPreferredSize(new Dimension(10,30));
				Gap50.setText("|");
				Scjhpanel.add(Gap50);
				
				JButton ScjhBtn=new JButton();
				ScjhBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Text co=new Text();
						ProductionSchedule ps=new ProductionSchedule();
						desktop.add(ps);
					}
				});
				ScjhBtn.setForeground(new Color(2, 81, 103));
				ScjhBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
				ScjhBtn.setContentAreaFilled(false);
				ScjhBtn.setBorderPainted(false); 
				ScjhBtn.setText("<html><u>制定生产计划</u></html>");
				ScjhBtn.setPreferredSize(new Dimension(134,30));
				Scjhpanel.add(ScjhBtn);
		
		//生产车间
		JPanel Sccjpanel = new JPanel();
		Sccjpanel.setBackground(new Color(183,223,234));
		Sccjpanel.setPreferredSize(new Dimension(330,40));
		Sccjpanel.setLayout(new FlowLayout());
		
		SccjBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.removeAll();
				panel_5.add(Sccjpanel);
				panel_5.revalidate();
				panel_5.repaint();
			}
		});
		
		JButton DclBtn=new JButton();
		DclBtn.setForeground(new Color(2, 81, 103));
		DclBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		DclBtn.setContentAreaFilled(false);
		DclBtn.setBorderPainted(false); 
		DclBtn.setText("<html><u>待处理订单</u></html>");
		DclBtn.setPreferredSize(new Dimension(134,30));
		DclBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				desktop.add(new Producing());
			}
		});
		Sccjpanel.add(DclBtn);
		
		JLabel Gap6=new JLabel();
		Gap6.setForeground(new Color(2, 81, 103));
		Gap6.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		Gap6.setPreferredSize(new Dimension(10,30));
		Gap6.setText("|");
		Sccjpanel.add(Gap6);
		
		JButton SczBtn=new JButton();
		SczBtn.setForeground(new Color(2, 81, 103));
		SczBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		SczBtn.setContentAreaFilled(false);
		SczBtn.setBorderPainted(false); 
		SczBtn.setText("<html><u>生产中订单</u></html>");
		SczBtn.setPreferredSize(new Dimension(134,30));
		SczBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new Produced());
			}
		});
		Sccjpanel.add(SczBtn);
		
		//成品部
				JPanel Cpbpanel = new JPanel();
				Cpbpanel.setBackground(new Color(183,223,234));
				Cpbpanel.setPreferredSize(new Dimension(330,40));
				Cpbpanel.setLayout(new FlowLayout());
				
				CpkBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel_5.removeAll();
						panel_5.add(Cpbpanel);
						panel_5.revalidate();
						panel_5.repaint();
					}
				});
				
				JButton QrrkBtn=new JButton();
				QrrkBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ConfirmIn ci=new ConfirmIn();
						desktop.add(ci);
					}
				});
				QrrkBtn.setForeground(new Color(2, 81, 103));
				QrrkBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
				QrrkBtn.setContentAreaFilled(false);
				QrrkBtn.setBorderPainted(false); 
				QrrkBtn.setText("<html><u>确认入库</u></html>");
				QrrkBtn.setPreferredSize(new Dimension(134,30));
				Cpbpanel.add(QrrkBtn);
				
				JLabel Gap7=new JLabel();
				Gap7.setForeground(new Color(2, 81, 103));
				Gap7.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
				Gap7.setPreferredSize(new Dimension(10,30));
				Gap7.setText("|");
				Cpbpanel.add(Gap7);
				
				JButton DthBtn=new JButton();
				DthBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Pending pd=new Pending();
						desktop.add(pd);
					}
				});
				DthBtn.setForeground(new Color(2, 81, 103));
				DthBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
				DthBtn.setContentAreaFilled(false);
				DthBtn.setBorderPainted(false); 
				DthBtn.setText("<html><u>待提货订单</u></html>");
				DthBtn.setPreferredSize(new Dimension(134,30));
				Cpbpanel.add(DthBtn);
				
				JLabel Gap8=new JLabel();
				Gap8.setForeground(new Color(2, 81, 103));
				Gap8.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
				Gap8.setPreferredSize(new Dimension(10,30));
				Gap8.setText("|");
				Cpbpanel.add(Gap8);
				
				JButton QrckBtn=new JButton();
				QrckBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ConfirmOut cfo=new ConfirmOut();
						desktop.add(cfo);
					}
				});
				QrckBtn.setForeground(new Color(2, 81, 103));
				QrckBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
				QrckBtn.setContentAreaFilled(false);
				QrckBtn.setBorderPainted(false); 
				QrckBtn.setText("<html><u>确认出库</u></html>");
				QrckBtn.setPreferredSize(new Dimension(134,30));
				Cpbpanel.add(QrckBtn);
		
		//系统管理
		JPanel Xtglpanel = new JPanel();
		Xtglpanel.setBackground(new Color(183,223,234));
		Xtglpanel.setPreferredSize(new Dimension(330,40));
		Xtglpanel.setLayout(new FlowLayout());
		
		XtglBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.removeAll();
				panel_5.add(Xtglpanel);
				panel_5.revalidate();
				panel_5.repaint();
			}
		});
		
		JButton GlbmglyBtn=new JButton();
		GlbmglyBtn.setForeground(new Color(2, 81, 103));
		GlbmglyBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		GlbmglyBtn.setContentAreaFilled(false);
		GlbmglyBtn.setBorderPainted(false); 
		GlbmglyBtn.setText("<html><u>管理部门管理员</u></html>");
		GlbmglyBtn.setPreferredSize(new Dimension(160,30));
		GlbmglyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new CreateManager());
			}
		});
		Xtglpanel.add(GlbmglyBtn);
		
		//企业管理
		JPanel Qyglpanel = new JPanel();
		Qyglpanel.setBackground(new Color(183,223,234));
		Qyglpanel.setPreferredSize(new Dimension(330,40));
		Qyglpanel.setLayout(new FlowLayout());
		
		QyglBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.removeAll();
				panel_5.add(Qyglpanel);
				panel_5.revalidate();
				panel_5.repaint();
			}
		});
		
		CxjlBtn=new JButton();
		CxjlBtn.setForeground(new Color(2, 81, 103));
		CxjlBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		CxjlBtn.setContentAreaFilled(false);
		CxjlBtn.setBorderPainted(false); 
		CxjlBtn.setText("<html><u>查询记录</u></html>");
		CxjlBtn.setPreferredSize(new Dimension(134,30));
		CxjlBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new Query());
			}
		});
		Qyglpanel.add(CxjlBtn);
		
		changeButton(false);
	}
	
	public void changeButton(boolean b) {
		XsbBtn.setEnabled(b);
		CwbBtn.setEnabled(b);
		ScjhkBtn.setEnabled(b);
		SccjBtn.setEnabled(b);
		CpkBtn.setEnabled(b);
		XtglBtn.setEnabled(b);
		QyglBtn.setEnabled(b);
		CjkhBtn.setEnabled(b);
		CjddBtn.setEnabled(b);
		QxddBtn.setEnabled(b);
		YxclBtn.setEnabled(b);
	}
	
}
