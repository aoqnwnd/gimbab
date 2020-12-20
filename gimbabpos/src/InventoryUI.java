import java.awt.Container;
import java.awt.Panel;

import javax.swing.JFrame;

import dao.gimbabdao;

import javax.swing.JPanel;
import javax.swing.JTable;

import bean.Stock;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class InventoryUI extends JFrame {
	
	Container container = getContentPane();
	Panel panelSouth = new Panel();
	Panel panelCenter = new Panel();
	Panel panelStock = new Panel();
	Panel panelOrder = new Panel();
	Panel panelAmount = new Panel();
	gimbabdao dao = new gimbabdao();
	Stock stock = new Stock();
	private JTable table;
	Object columnNames[] = {"크림치즈","멸치고추","참치","제육","돈가스","닭갈비",
							"랍스타","불고기","새우튀김","장아찌","아몬드"};
	int num;
	String stockname;
	private JTextField textField;
	JLabel lblNewLabel;
	
	JButton btnNewButton = new JButton("크림치즈");
	JButton btnNewButton_1 = new JButton("멸치고추");
	JButton btnNewButton_2 = new JButton("참치");
	JButton btnNewButton_3 = new JButton("제육");
	JButton btnNewButton_4 = new JButton("돈가스");
	JButton btnNewButton_5 = new JButton("닭갈비");
	JButton btnNewButton_6 = new JButton("랍스타");
	JButton btnNewButton_7 = new JButton("불고기");
	JButton btnNewButton_8 = new JButton("새우튀김");
	JButton btnNewButton_9 = new JButton("장아찌");
	JButton btnNewButton_10 = new JButton("아몬드");
	
	
	JButton btnNewButton_11 = new JButton("POS");
	JButton btnNewButton_12 = new JButton("주문");//주문
	JButton btnNewButton_13 = new JButton("판매현황");
	JButton btnNewButton_14 = new JButton("초기화");
	
	public void stockName(String stock) {
		lblNewLabel.setText(stockname+"를 주문하실건가요? 수량을 입력하세요.");
	}
	
	public InventoryUI(){
		num = 0;//초기화
		stock = dao.getstock();
		
		setSize(550, 700);
		setVisible(true);
		setLocation(900, 100);
		setResizable(false);
		getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(-7, 0, 555, 198);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable(dao.makestocklistArr(stock),columnNames);//재고 표
		table.setBounds(6, 25, 552, 100);
		panel.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);			//재고 표 깔끔스
		scrollPane.setBounds(6, 57, 552, 120);
		panel.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("재고관리");					//재고관리 라벨
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_1.setBounds(30, 26, 100, 20);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 202, 534, 341);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("물품 주문");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_2.setBounds(30, 0, 100, 20);
		panel_1.add(lblNewLabel_2);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				stockName(stockname="크림치즈");
			}
		});
		btnNewButton.setBounds(30, 20, 100, 60);
		panel_1.add(btnNewButton);
		
		
		btnNewButton_1.setBounds(153, 20, 100, 60);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				stockName(stockname="멸치고추");
			}
		});
		
		
		btnNewButton_2.setBounds(276, 20, 100, 60);
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				stockName(stockname="참지");
			}
		});
		
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockName(stockname="제육");
			}
		});
		btnNewButton_3.setBounds(399, 20, 100, 60);
		panel_1.add(btnNewButton_3);
		
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockName(stockname="돈가스");
			}
		});
		btnNewButton_4.setBounds(30, 90, 100, 60);
		panel_1.add(btnNewButton_4);
		
		
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockName(stockname="닭갈비");
			}
		});
		btnNewButton_5.setBounds(153, 90, 100, 60);
		panel_1.add(btnNewButton_5);
		
		
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockName(stockname="랍스타");
			}
		});
		btnNewButton_6.setBounds(276, 90, 100, 60);
		panel_1.add(btnNewButton_6);
		
		
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockName(stockname="불고기");
			}
		});
		btnNewButton_7.setBounds(399, 90, 100, 60);
		panel_1.add(btnNewButton_7);
		
		
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockName(stockname="새우튀김");
			}
		});
		btnNewButton_8.setBounds(30, 160, 100, 60);
		panel_1.add(btnNewButton_8);
		
		
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockName(stockname="장아찌");
			}
		});
		btnNewButton_9.setBounds(153, 160, 100, 60);
		panel_1.add(btnNewButton_9);
		
		
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockName(stockname="아몬드");
			}
		});
		btnNewButton_10.setBounds(276, 160, 100, 60);
		panel_1.add(btnNewButton_10);
		
		
		
		
		JLabel lblNewLabel_3 = new JLabel("주문 수량 입력 :");
		
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_3.setBounds(30, 237, 100, 20);
		panel_1.add(lblNewLabel_3);
		
		textField = new JTextField();		
		textField.setBounds(137, 237, 116, 21);
		panel_1.add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 17));
		lblNewLabel.setBounds(30, 275, 450, 30);
		panel_1.add(lblNewLabel);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 541, 534, 120);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
		btnNewButton_11.setBounds(26, 23, 100, 50);
		panel_3.add(btnNewButton_11);
		
		
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				if(stockname.equals("크림치즈")){// 주문하기
					stock.setCreamcheese(stock.getCreamcheese()+(Integer.parseInt(textField.getText())));
				}else if (stockname.equals("멸치고추")){
					stock.setMuelchoo (stock.getMuelchoo()+(Integer.parseInt(textField.getText())));					
				}else if (stockname.equals("참지")){
					stock.setTuna(stock.getTuna()+(Integer.parseInt(textField.getText())));
				}else if (stockname.equals("제육")){
					stock.setJae6(stock.getJae6()+(Integer.parseInt(textField.getText())));
				}else if (stockname.equals("돈가스")){
					stock.setDonggas(stock.getDonggas()+(Integer.parseInt(textField.getText())));
				}else if (stockname.equals("닭갈비")){
					stock.setChicken (stock.getChicken()+(Integer.parseInt(textField.getText())));
				}else if (stockname.equals("랍스타")){
					stock.setLabster(stock.getLabster()+(Integer.parseInt(textField.getText())));
				}else if (stockname.equals("불고기")){
					stock.setBulgogi(stock.getBulgogi()+(Integer.parseInt(textField.getText())));
				}else if (stockname.equals("새우튀김")){
					stock.setShrimp(stock.getShrimp()+(Integer.parseInt(textField.getText())));
				}else if (stockname.equals("장아찌")){
					stock.setJangajji(stock.getJangajji()+(Integer.parseInt(textField.getText())));
				}else if (stockname.equals("아몬드")){
					stock.setAlmonds(stock.getAlmonds()+(Integer.parseInt(textField.getText())));
				}
				
				System.out.println(stock);
				dao.stockorder(stock);
				stockname="";
				//Integer.parseInt(textField.getText());//입력값 숫자로변환
				textField.setText("");
				lblNewLabel.setText("");
				JOptionPane.showMessageDialog(null, "주문이 성공하였습니다.");
			}
		});
		btnNewButton_12.setBounds(156, 23, 100, 50);
		panel_3.add(btnNewButton_12);
		
		
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SellListUI();
			}
		});
		btnNewButton_13.setBounds(281, 23, 100, 50);
		panel_3.add(btnNewButton_13);
		
		
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stock=dao.getstock();
				
				JPanel panel = new JPanel();
				panel.setBounds(-7, 0, 555, 198);
				getContentPane().add(panel);
				panel.setLayout(null);
							
				table = new JTable(dao.makestocklistArr(stock),columnNames);
				table.setBounds(6, 25, 552, 100);
				panel.add(table);
							
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(6, 57, 552, 120);
				panel.add(scrollPane);
				
								
			}
		});
		btnNewButton_14.setBounds(409, 23, 100, 50);
		panel_3.add(btnNewButton_14);
			
	}
}
