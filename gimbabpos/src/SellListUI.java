import java.awt.Container;
import java.awt.Panel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bean.Gimbab;
import dao.gimbabdao;

import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;

public class SellListUI extends JFrame{

	Container container = getContentPane();
	Panel pal =new Panel();
	gimbabdao dao=null;
	//Vector<String> columnNames = null;
	Vector<Gimbab> rowData = null;
	Object columnNames[] = {"이름","음료","토핑","최종가격"};
	Object sellNames[] = {"이름","판매량"};
	JLabel lblNewLabel_1;
	private JTable table;
	private JTable table_1;
	
	public SellListUI() {
	
		dao = new gimbabdao();
				
		rowData =dao.GetAllSellList();
		
		setSize(550, 700);
		setVisible(true);
		setResizable(false);
		setLocation(900, 100);
		getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 41, 500, 215);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable(dao.makeArr(dao.GetAllSellList()),columnNames);
		table.setBounds(1, 27, 450, 288);
		panel.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(13, 5, 469, 200);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane);
			
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(25, 300, 500, 215);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		table_1 = new JTable(dao.makelistArr(dao.Getsellcount()),sellNames);
		table_1.setBounds(26, 0, 450, 215);
		panel_3.add(table_1);
		
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(12, 0,  469, 215);
		panel_3.add(scrollPane_1);
		
				
		//scrollPane.add(table);
		
		
		//////////////////////////////////////////////
		/*
		  
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		table = new JTable(rowData, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane);
		
		*/				
		//////////////////////////////////////
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 577, 534, 75);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("POS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//창닫기
				setVisible(false);
			}
		});
		btnNewButton.setBounds(12, 10, 100, 50);
		panel_1.add(btnNewButton);
		
		JButton button = new JButton("매출");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//테이블에 전체 매출 리스트 출력
				rowData =dao.GetAllSellList();
				int sum=0;
				for(int i=0; i<rowData.size();i++){					
					sum += rowData.get(i).getPrice();
				}
				
				lblNewLabel_1.setText("오늘의 매출은 "+Integer.toString(sum)+"원 입니다.");
				
			}
		});
		button.setBounds(145, 10, 100, 50);
		panel_1.add(button);
		
		JButton button_1 = new JButton("메뉴별판매");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//테이블에 메뉴별 판매리스트
				/*
				for(Object o:dao.makelistArr(dao.Getsellcount())){
					System.out.println(o);
				}
				*/
				rowData =dao.GetAllSellList();
				JPanel panel_3 = new JPanel();
				panel_3.setBounds(25, 300, 500, 215);
				getContentPane().add(panel_3);
				panel_3.setLayout(null);
				
				table_1 = new JTable(dao.makelistArr(dao.Getsellcount()),sellNames);
				table_1.setBounds(26, 0, 450, 215);
				panel_3.add(table_1);
				
				JScrollPane scrollPane_1 = new JScrollPane(table_1);
				scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane_1.setBounds(12, 0,  469, 215);
				panel_3.add(scrollPane_1);
				
			}
		});
		button_1.setBounds(279, 10, 100, 50);
		panel_1.add(button_1);
		
		JButton btnNewButton_1 = new JButton("판매리스트");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rowData =dao.GetAllSellList();
				
				table = new JTable(dao.makeArr(dao.GetAllSellList()),columnNames);
				table.setBounds(1, 27, 450, 288);
				panel.add(table);
				
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(13, 5, 469, 200);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				panel.add(scrollPane);
				
			}
		});
		btnNewButton_1.setBounds(411, 10, 100, 50);
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 524, 534, 43);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(50, 0, 400, 40);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("메뉴별 판매량");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel.setBounds(25, 275, 100, 20);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("오늘 판매리스트");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_2.setBounds(25, 16, 100, 20);
		getContentPane().add(lblNewLabel_2);
		
		
	}
}
