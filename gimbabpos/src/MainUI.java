import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import bean.Gimbab;
import bean.Stock;
import dao.gimbabdao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.print.DocFlavor.STRING;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

//재고창을 만들면 완전히 다른걸 만들어야 한다. 그래서 그걸 어떻게 만들지 고민해봐야될듯
//일단 메인에서 만들어 줘야 함 입력데이터는 여기서 전부 받아서 서버로 어넣어주는 ㅈ쪽으로 만들자
public class MainUI extends JFrame{
   Vector<Gimbab> list =new Vector<Gimbab>();
   
   Gimbab gimbab = null; 
   Stock stock =null;
   gimbabdao dao = null;
   JLabel lblNewLabel_1;
   String menu="";
   
   static boolean cream = true;
   static boolean meulchoo= true;
   static boolean tuna= true;
   static boolean jea6= true;
   static boolean donggas= true;
   static boolean chicken= true;
   static boolean labster= true;
   static boolean bulgogi= true;
   static boolean shrimp= true;
   static boolean jangajji= true;
   static boolean almonds= true;
   
   int count=0;
   int stockCount=0;
   int sameCount=0;
   
   boolean flag_drink=false;//한번이라도 클릭하면 true
   boolean scroll_plus=false;
   private JFrame frame;
   private final ButtonGroup buttonGroup = new ButtonGroup();
   private final ButtonGroup buttonGroup_1 = new ButtonGroup();
   private final ButtonGroup buttonGroup_2 = new ButtonGroup();
   
   static JPanel panel=new JPanel() {
		Image background=new ImageIcon("gimbap/바르다김선생.png").getImage();
		public void paint(Graphics g) {//그리는 함수
				g.drawImage(background, 0, 0, null);//background를 그려줌		
		}
	};
	
   public static void main(String[] args) {
	   idpass();
   }
   
   public static void idpass() {
	   JFrame frame = new JFrame();
       JLabel label = new JLabel("ID : ");
       JLabel pswrd = new JLabel("PassWord : ");
       JTextField txtID= new JTextField(10);
       JPasswordField txtPass = new JPasswordField(10);
       JButton logBtn = new JButton("Log in");
       frame.setVisible(true);
       frame.setSize( 620 , 390);
       frame.setLocation(600, 300);
//       frame.setResizable(false);
       frame.add(panel);
       panel.add(label);
       panel.add(txtID);
       panel.add(pswrd);
       panel.add(txtPass);
       panel.add(logBtn);
       frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

       logBtn.addActionListener( new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               String id = "bareuda"; 		// 바르다
               String pass = "rlatjstod"; 	// 김선생
               
               if(id.equals(txtID.getText()) &&  pass.equals(txtPass.getText())) {
               	JOptionPane.showMessageDialog( null, "로그인 성공" );
               	txtID.setText("");
               	txtPass.setText("");
               	EventQueue.invokeLater(new Runnable() {
                    public void run() {
                       try {
                           MainUI window = new MainUI();
                           window.frame.setVisible(true);
                       } catch (Exception e) {
                          e.printStackTrace();
                       }
                    }
                 });
               	frame.dispose();
               } else {
               	JOptionPane.showMessageDialog( null , "로그인 실패");
               	txtID.setText("");
               	txtPass.setText("");
               }  
             }
       } );
}
   
   
   public MainUI() {
	   initialize();
   }
   
   public void initialize() {
     
	  gimbab = new Gimbab();
      dao = new gimbabdao();
      stock = new Stock();
      stock = dao.getstock();   //서버에서 재고 데이터 넣어주기   
      System.out.println(stock); // 재고 데이터 확인
      
      frame = new JFrame();
      frame.setTitle("바르다 김선생 POS기");
      frame.setBounds(450, 150, 800, 750);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      JPanel panel = new JPanel();
      panel.setBackground(new Color(233, 213, 198));
      panel.setBounds(0, 0, 784, 400);
      frame.getContentPane().add(panel);
      panel.setLayout(new GridLayout(3,5,20,20));
      ImageIcon icon1= new ImageIcon("gimbap/매운 쌈닭.png");
      Image smallicon1 = icon1.getImage();    
      Image Finalicon1 = smallicon1.getScaledInstance(180, 120, Image.SCALE_SMOOTH);     //  
      ImageIcon icon01= new ImageIcon(Finalicon1);
      JButton btnNewButton = new JButton(icon01);
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 if (stock.getChicken()<0) {
             	JOptionPane.showMessageDialog(null,"재료가 떨어졌습니다. 재료를 주문하세요.");
         		return;
             }
            count++;
            if(count > 1){
               stock.setChicken(stock.getChicken()+1);
            }
            stock.setChicken(stock.getChicken()-1); // 원두재고 관리            
            showlabel("매운 쌈닭 김밥",4000);
            
         }
      });
      btnNewButton.setBounds(50, 37, 100, 70);
      panel.add(btnNewButton);
      
      ImageIcon icon2= new ImageIcon("gimbap/돈가스.png");
      Image smallicon2 = icon2.getImage();
      Image Finalicon2 = smallicon2.getScaledInstance(180, 120, Image.SCALE_SMOOTH); 
      ImageIcon icon02= new ImageIcon(Finalicon2);
      JButton btnNewButton_1 = new JButton(icon02);
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count++;
            if (stock.getDonggas()<0) {
            	JOptionPane.showMessageDialog(null,"재료가 떨어졌습니다. 재료를 주문하세요.");
        		return;
            }
            if(count > 1){
               stock.setDonggas(stock.getDonggas()+1);
            }
            
            stock.setDonggas(stock.getDonggas()-1);
            showlabel("돈가스 김밥",3500);
         }
      });
      btnNewButton_1.setBounds(200, 37, 100, 70);
      panel.add(btnNewButton_1);
      
      ImageIcon icon3= new ImageIcon("gimbap/매운 제육 쌈.png");
      Image smallicon3 = icon3.getImage(); 
      Image Finalicon3 = smallicon3.getScaledInstance(180, 120, Image.SCALE_SMOOTH); 
      ImageIcon icon03= new ImageIcon(Finalicon3);
      JButton btnNewButton_2 = new JButton(icon03);
      btnNewButton_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count++;
            if (stock.getJae6()<0) {
            	JOptionPane.showMessageDialog(null,"재료가 떨어졌습니다. 재료를 주문하세요.");
        		return;
            }
            if(count > 1){
               stock.setJae6(stock.getJae6()+1);
            }
            stock.setJae6(stock.getJae6()-1);
            showlabel("매운 제육 쌈김밥",4000);
         }
      });
      btnNewButton_2.setBounds(350, 37, 100, 70);
      panel.add(btnNewButton_2);
      
      ImageIcon icon4= new ImageIcon("gimbap/매운 멸추.png");
      Image smallicon4 = icon4.getImage();    
      Image Finalicon4 = smallicon4.getScaledInstance(180, 120, Image.SCALE_SMOOTH);//  
      ImageIcon icon04= new ImageIcon(Finalicon4);
      JButton btnNewButton_3 = new JButton(icon04);
      btnNewButton_3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count++;
            if ( stock.getMuelchoo()<0) {
            	JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다. 재료를 주문하세요.");
        		return;
            }
            if(count > 1){
               stock.setMuelchoo(stock.getMuelchoo()+1);
            }
            stock.setMuelchoo(stock.getMuelchoo()-1);
            showlabel("매운 멸추 김밥",4200);
         }
      });
      btnNewButton_3.setBounds(500, 37, 100, 70);
      panel.add(btnNewButton_3);
      
      ImageIcon icon5= new ImageIcon("gimbap/랍스터.png");
      Image smallicon5 = icon5.getImage();    
      Image Finalicon5 = smallicon5.getScaledInstance(180, 120, Image.SCALE_SMOOTH);     //  
      ImageIcon icon05= new ImageIcon(Finalicon5);
      JButton btnNewButton_4 = new JButton(icon05);
      btnNewButton_4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count++;
            if (stock.getLabster()<0) {
            	JOptionPane.showMessageDialog(null,"재료가 떨어졌습니다. 재료를 주문하세요.");
        		return;
            }
            if(count > 1){
               stock.setLabster(stock.getLabster()+1);
            }
            stock.setLabster(stock.getLabster()-1);
            showlabel("랍스터 김밥",4500);
         }
      });
      btnNewButton_4.setBounds(650, 37, 100, 70);
      panel.add(btnNewButton_4);
      
      ImageIcon icon6= new ImageIcon("gimbap/새우튀김.png");
      Image smallicon6 = icon6.getImage();    
      Image Finalicon6 = smallicon6.getScaledInstance(180, 120, Image.SCALE_SMOOTH);     //  
      ImageIcon icon06= new ImageIcon(Finalicon6);
      JButton button = new JButton(icon06);
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count++;
            if (stock.getShrimp()<0) {
            	JOptionPane.showMessageDialog(null,"재료가 떨어졌습니다. 재료를 주문하세요.");
        		return;
            }
            if(count > 1){
               stock.setShrimp(stock.getShrimp()+1);
            }
            stock.setShrimp(stock.getShrimp()-1);
            showlabel("새우튀김 김밥",4300);
         }
      });
      button.setBounds(50, 148, 100, 70);
      panel.add(button);
      
      ImageIcon icon7= new ImageIcon("gimbap/크림치즈 호두.png");
      Image smallicon7 = icon7.getImage();    
      Image Finalicon7 = smallicon7.getScaledInstance(180, 120, Image.SCALE_SMOOTH);     //  
      ImageIcon icon07= new ImageIcon(Finalicon7);
      JButton button_1 = new JButton(icon07);
      button_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count++;
            if (stock.getCreamcheese()<0) {
            	JOptionPane.showMessageDialog(null,"재료가 떨어졌습니다. 재료를 주문하세요.");
        		return;
            }
            if(count > 1){
               stock.setCreamcheese(stock.getCreamcheese()+1);
            }
            stock.setCreamcheese(stock.getCreamcheese()-1);
            showlabel("크림치즈 호두 김밥",4100);
         }
      });
      button_1.setBounds(200, 148, 100, 70);
      panel.add(button_1);
      
      ImageIcon icon8= new ImageIcon("gimbap/불고기.png");
      Image smallicon8 = icon8.getImage();    
      Image Finalicon8 = smallicon8.getScaledInstance(180, 120, Image.SCALE_SMOOTH);     //  
      ImageIcon icon08= new ImageIcon(Finalicon8);
      JButton button_2 = new JButton(icon08);
      button_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count++;
            if (stock.getBulgogi()<0) {
            	JOptionPane.showMessageDialog(null,"재료가 떨어졌습니다. 재료를 주문하세요.");
        		return;
            }
            if(count > 1){
               stock.setBulgogi(stock.getBulgogi()+1);
            }
            stock.setBulgogi(stock.getBulgogi()-1);
            showlabel("불고기 김밥",3800);
         }
      });
      button_2.setBounds(350, 148, 100, 70);
      panel.add(button_2);
      
      ImageIcon icon9= new ImageIcon("gimbap/참치.png");
      Image smallicon9 = icon9.getImage();    
      Image Finalicon9 = smallicon9.getScaledInstance(180, 120, Image.SCALE_SMOOTH);     //  
      ImageIcon icon09= new ImageIcon(Finalicon9);
      JButton button_3 = new JButton(icon09);
      button_3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count++;
            if (stock.getTuna()<0) {
            	JOptionPane.showMessageDialog(null,"재료가 떨어졌습니다. 재료를 주문하세요.");
        		return;
            }
            if(count > 1){
               stock.setTuna(stock.getTuna()+1);
            }
            stock.setTuna(stock.getTuna()-1);
            showlabel("참치 김밥",3800);
         }
      });
      button_3.setBounds(500, 148, 100, 70);
      panel.add(button_3);
      
      ImageIcon icon10= new ImageIcon("gimbap/바른.png");
      Image smallicon10 = icon10.getImage();    
      Image Finalicon10 = smallicon10.getScaledInstance(180, 120, Image.SCALE_SMOOTH);     //  
      ImageIcon icon010= new ImageIcon(Finalicon10);
      JButton button_4 = new JButton(icon010);
      button_4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count++;

            showlabel("바른 김밥",3000);
         }
      });
      button_4.setBounds(650, 148, 100, 70);
      panel.add(button_4);
      
      ImageIcon icon11= new ImageIcon("gimbap/매콤 장아찌.png");
      Image smallicon11 = icon11.getImage();    
      Image Finalicon11 = smallicon11.getScaledInstance(180, 120, Image.SCALE_SMOOTH);     //  
      ImageIcon icon011= new ImageIcon(Finalicon11);
      JButton button_5 = new JButton(icon011);
      button_5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count++;
            if (stock.getJangajji()<0) {
            	JOptionPane.showMessageDialog(null,"재료가 떨어졌습니다. 재료를 주문하세요.");
        		return;
            }
            if(count > 1){
               stock.setJangajji(stock.getJangajji()+1);
            }
            stock.setJangajji(stock.getJangajji()-1);
            showlabel("매콤 장아찌 김밥",4400);
         }
      });
      button_5.setBounds(50, 266, 100, 70);
      panel.add(button_5);
      
      ImageIcon icon12= new ImageIcon("gimbap/어린이 아몬드.png");
      Image smallicon12 = icon12.getImage();    
      Image Finalicon12 = smallicon12.getScaledInstance(180, 120, Image.SCALE_SMOOTH);     //  
      ImageIcon icon012= new ImageIcon(Finalicon12);
      JButton button_6 = new JButton(icon012);
      button_6.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count++;
            if (stock.getAlmonds()<0) {
            	JOptionPane.showMessageDialog(null,"재료가 떨어졌습니다. 재료를 주문하세요.");
        		return;
            }
            if(count > 1){
               stock.setAlmonds(stock.getAlmonds()+1);
            }
            stock.setAlmonds(stock.getAlmonds()-1);
            showlabel("어린이 아몬드 김밥",3000);
         }
      });
      button_6.setBounds(200, 266, 100, 70);
      panel.add(button_6);
 
      //버튼
      
      
      JPanel panel_1 = new JPanel();
      panel_1.setBounds(0, 396, 784, 225);
      frame.getContentPane().add(panel_1);
      panel_1.setLayout(null);
      panel_1.setBackground(Color.WHITE);
      
      
      JCheckBox rdbtnCreamcheese= new JCheckBox("  크림치즈");
      
      rdbtnCreamcheese.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	 sameCount++;
             if(sameCount > 1){
                 stock.setCreamcheese(stock.getCreamcheese()+1);
              }
              stock.setCreamcheese(stock.getCreamcheese()-1);
              
            if(cream == true){
            	if (gimbab.getStock() == null) {
            		gimbab.setStock("크림치즈");
				}else {
					gimbab.setStock(gimbab.getStock()+" 크림치즈");
				}
               stockCount++;
               cream = false;
            } else if(cream == false){
            	gimbab.setStock(gimbab.getStock().replace("크림치즈", ""));
            	cream = true;
            	stockCount--;
            }
            
              showlabel(gimbab.getName(),gimbab.getPrice());
         }
      });
      rdbtnCreamcheese.setBounds(145, 42, 100, 26);
      panel_1.add(rdbtnCreamcheese);
      rdbtnCreamcheese.setBackground(Color.WHITE);
      
      
      JCheckBox rdbtnMuelchoo= new JCheckBox("  멸치고추");
      rdbtnMuelchoo.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	 sameCount++;
             if(sameCount > 1){
                 stock.setMuelchoo(stock.getMuelchoo()+1);
              }
              stock.setMuelchoo(stock.getMuelchoo()-1);
              
              if(meulchoo == true){
            	  if (gimbab.getStock() == null) {
              		  gimbab.setStock("멸치고추");
  			      } else {
  				      gimbab.setStock(gimbab.getStock()+" 멸치고추");
  			      }
                  stockCount++;
                  meulchoo = false;
              } else if(meulchoo == false){
               	  gimbab.setStock(gimbab.getStock().replace("멸치고추", ""));
               	  meulchoo = true;
               	  stockCount--;
              }
            
              showlabel(gimbab.getName(),gimbab.getPrice());
         }
      });
      rdbtnMuelchoo.setBounds(259, 42, 100, 26);
      panel_1.add(rdbtnMuelchoo);
      rdbtnMuelchoo.setBackground(Color.WHITE);
      
      JCheckBox rdbtnTuna= new JCheckBox("  참치");   
      rdbtnTuna.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	 sameCount++;
            if(sameCount > 1){
                stock.setTuna(stock.getTuna()+1);
             }
             stock.setTuna(stock.getTuna()-1);
             
             if(tuna == true){
            	 if (gimbab.getStock() == null) {
               		gimbab.setStock("참치");
   				}else {
   					gimbab.setStock(gimbab.getStock()+" 참치");
   				}
                 stockCount++;
                 tuna= false;
              }  else if(tuna == false){
              	gimbab.setStock(gimbab.getStock().replace("참치", ""));
              	tuna = true;
              	stockCount--;
              }
              showlabel(gimbab.getName(),gimbab.getPrice());
         }
      });
      rdbtnTuna.setBounds(370, 42, 80, 26);
      panel_1.add(rdbtnTuna);
      rdbtnTuna.setBackground(Color.WHITE);
      

      JCheckBox rdbtnJae6= new JCheckBox("  제육");   
      rdbtnJae6.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	 sameCount++;
             if(sameCount > 1){
                 stock.setJae6(stock.getJae6()+1);
              }
              stock.setJae6(stock.getJae6()-1);
              
              if(jea6 == true){
            	if (gimbab.getStock() == null) {
                 	gimbab.setStock("제육");
     			}else {
     				gimbab.setStock(gimbab.getStock()+" 제육");
     			}
                  stockCount++;
                  jea6 = false;
               }  else if(jea6 == false){
               	gimbab.setStock(gimbab.getStock().replace("제육", ""));
               	jea6 = true;
               	stockCount--;
               }
            
              showlabel(gimbab.getName(),gimbab.getPrice());
         }
      });
      rdbtnJae6.setBounds(470, 42, 80, 26);
      panel_1.add(rdbtnJae6);
      rdbtnJae6.setBackground(Color.WHITE);
      
      JCheckBox rdbtnDonggas= new JCheckBox("  돈가스");   
      rdbtnDonggas.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	 sameCount++;
             if(sameCount > 1){
                 stock.setDonggas(stock.getDonggas()+1);
              }
              stock.setDonggas(stock.getDonggas()-1);
              
              if(donggas == true){
            	  if (gimbab.getStock() == null) {
                   	gimbab.setStock("돈가스");
       			}else {
       				gimbab.setStock(gimbab.getStock()+" 돈가스");
       			}
                  stockCount++;
                  donggas = false;
               }  else if(donggas == false){
               	gimbab.setStock(gimbab.getStock().replace("돈가스", ""));
               	donggas = true;
               	stockCount--;
               }
            
              showlabel(gimbab.getName(),gimbab.getPrice());
         }
      });
      rdbtnDonggas.setBounds(570, 42, 100, 26);
      panel_1.add(rdbtnDonggas);
      rdbtnDonggas.setBackground(Color.WHITE);
      
      JCheckBox rdbtnChicken= new JCheckBox("  닭갈비");   
      rdbtnChicken.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	 sameCount++;
             if(sameCount > 1){
                 stock.setChicken(stock.getChicken()+1);
              }
              stock.setChicken(stock.getChicken()-1);
              
              if(chicken == true){
            	  if (gimbab.getStock() == null) {
                     	gimbab.setStock("닭갈비");
         			}else {
         				gimbab.setStock(gimbab.getStock()+" 닭갈비");
         			}
                  stockCount++;
                  chicken = false;
               }  else if(chicken == false){
               	gimbab.setStock(gimbab.getStock().replace("닭갈비", ""));
               	chicken = true;
               	stockCount--;
               }
            
              showlabel(gimbab.getName(),gimbab.getPrice());
         }
      });
      rdbtnChicken.setBounds(145, 70, 80, 26);
      panel_1.add(rdbtnChicken);
      rdbtnChicken.setBackground(Color.WHITE);
      
      
      JCheckBox rdbtnLabster= new JCheckBox("  랍스타");   
      rdbtnLabster.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	 sameCount++;
             if(sameCount > 1){
                 stock.setLabster(stock.getLabster()+1);
              }
              stock.setLabster(stock.getLabster()-1);
              
              if(labster == true){
            	  if (gimbab.getStock() == null) {
                   	gimbab.setStock("랍스타");
       			}else {
       				gimbab.setStock(gimbab.getStock()+" 랍스타");
       			}
                  stockCount++;
                  labster = false;
               }  else if(labster == false){
               	gimbab.setStock(gimbab.getStock().replace("랍스타", ""));
               	labster = true;
               	stockCount--;
               }
//            gimbab.setStock(menu+ " / " +"랍스타");
            
              showlabel(gimbab.getName(),gimbab.getPrice());
         }
      });
      rdbtnLabster.setBounds(239, 70, 80, 26);
      panel_1.add(rdbtnLabster);
      rdbtnLabster.setBackground(Color.WHITE);
      
      JCheckBox rdbtnBulgogi= new JCheckBox("  불고기");
      rdbtnBulgogi.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	 sameCount++;
             if(sameCount > 1){
                 stock.setBulgogi(stock.getBulgogi()+1);
              }
              stock.setBulgogi(stock.getBulgogi()-1);
              
              if(bulgogi == true){
            	  if (gimbab.getStock() == null) {
                     	gimbab.setStock("불고기");
         			}else {
         				gimbab.setStock(gimbab.getStock()+" 불고기");
         			}
                  stockCount++;
                  bulgogi = false;
               }  else if(bulgogi == false){
               	gimbab.setStock(gimbab.getStock().replace("불고기", ""));
               	bulgogi = true;
               	stockCount--;
               }
//            gimbab.setStock(menu+ " / " +"불고기");
              showlabel(gimbab.getName(),gimbab.getPrice());
           
         }
      });
      rdbtnBulgogi.setBounds(330, 70, 80, 26);
      panel_1.add(rdbtnBulgogi);
      rdbtnBulgogi.setBackground(Color.WHITE);
      
      JCheckBox rdbtnShrimp= new JCheckBox("  새우튀김");
      rdbtnShrimp.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	 sameCount++;
             if(sameCount > 1){
                 stock.setShrimp(stock.getShrimp()+1);
              }
              stock.setShrimp(stock.getShrimp()-1);
              
              if(shrimp == true){
            	  if (gimbab.getStock() == null) {
                   	gimbab.setStock("새우튀김");
       			}else {
       				gimbab.setStock(gimbab.getStock()+" 새우튀김");
       			}
                  stockCount++;
                  shrimp = false;
               }  else if(shrimp == false){
               	gimbab.setStock(gimbab.getStock().replace("새우튀김", ""));
               	shrimp = true;
               	stockCount--;
               }
//            gimbab.setStock(menu+ " / " +"새우튀김");
            
              showlabel(gimbab.getName(),gimbab.getPrice()); 
         }
      });
      rdbtnShrimp.setBounds(420, 70, 100, 26);
      panel_1.add(rdbtnShrimp);
      rdbtnShrimp.setBackground(Color.WHITE);
      
      
      JCheckBox rdbtnJangajji= new JCheckBox("  장아찌");
      rdbtnJangajji.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	 sameCount++;
             if(sameCount > 1){
                 stock.setJangajji(stock.getJangajji()+1);
              }
              stock.setJangajji(stock.getJangajji()-1);
              if(jangajji == true){
            	  if (gimbab.getStock() == null) {
                     	gimbab.setStock("장아찌");
         			}else {
         				gimbab.setStock(gimbab.getStock()+" 장아찌");
         			}
                  stockCount++;
                  jangajji = false;
               } else if(jangajji == false){
               	gimbab.setStock(gimbab.getStock().replace("장아찌", ""));
               	jangajji = true;
               	stockCount--;
               }
//            gimbab.setStock(menu+ " / " +"장아찌");
            showlabel(gimbab.getName(),gimbab.getPrice());
         }
      });
      rdbtnJangajji.setBounds(530, 70, 80, 26);
      panel_1.add(rdbtnJangajji);
      rdbtnJangajji.setBackground(Color.WHITE);
      
      
      JCheckBox rdbtnAlmonds= new JCheckBox("  아몬드");
      rdbtnAlmonds.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	 sameCount++;
             if(sameCount > 1){
                 stock.setAlmonds(stock.getAlmonds()+1);
              }
              stock.setAlmonds(stock.getAlmonds()-1);
              if(almonds == true){
            	  if (gimbab.getStock() == null) {
                   	gimbab.setStock("아몬드");
       			}else {
       				gimbab.setStock(gimbab.getStock()+" 아몬드");
       			}
                  stockCount++;
                  almonds = false;
               } else if(almonds == false){
               	gimbab.setStock(gimbab.getStock().replace("아몬드", ""));
               	almonds = true;
               	stockCount--;
               }
//               gimbab.setStock(menu+ " / " +"아몬드");
                showlabel(gimbab.getName(),gimbab.getPrice());
         }
      });
      rdbtnAlmonds.setBounds(670, 42, 80, 26);
      panel_1.add(rdbtnAlmonds);
      rdbtnAlmonds.setBackground(Color.WHITE);
      
      
      
      
      JCheckBox rdbtnnone= new JCheckBox("  없음");
      rdbtnnone.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
            gimbab.setStock(null);
            clearOptions();
            showlabel(gimbab.getName(),gimbab.getPrice());
            
         }

      public void clearOptions() {
         
         //체크박스 선택 취소
         rdbtnCreamcheese.setSelected(false);
         rdbtnMuelchoo.setSelected(false);
         rdbtnTuna.setSelected(false);
         rdbtnJae6.setSelected(false);
         rdbtnDonggas.setSelected(false);
         rdbtnChicken.setSelected(false);
         rdbtnLabster.setSelected(false);
         rdbtnBulgogi.setSelected(false);
         rdbtnShrimp.setSelected(false);
         rdbtnJangajji.setSelected(false);
         rdbtnAlmonds.setSelected(false);
         rdbtnnone.setSelected(false);
         stockCount = 0;
         cream = true;  
         meulchoo= true;
         tuna= true;    
         jea6= true;    
         donggas= true; 
         chicken= true; 
         labster= true; 
         bulgogi= true; 
         shrimp= true;  
         jangajji= true;
         almonds= true;
         sameCount = 0;
       }
      });
      
      rdbtnnone.setBounds(630, 70, 80, 26);
      panel_1.add(rdbtnnone);
      rdbtnnone.setBackground(Color.WHITE);
      
      //-----------------------
      JRadioButton rdbtnjucycool = new JRadioButton("  주시쿨");
      buttonGroup_1.add(rdbtnjucycool);
      rdbtnjucycool.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
            
            
        clickdrink(gimbab.getDrink(),"주시쿨",flag_drink);
         gimbab.setDrink("주시쿨");
         showlabel(gimbab.getName(),gimbab.getPrice());
         flag_drink=true;
            
         }
      });
      rdbtnjucycool.setBounds(145, 115, 100, 26);
      panel_1.add(rdbtnjucycool);
      rdbtnjucycool.setBackground(Color.WHITE);

      
      JRadioButton rdbtncidar = new JRadioButton("  콜라or사이다");
      buttonGroup_1.add(rdbtncidar);
      rdbtncidar.addActionListener(new ActionListener() {
            
         public void actionPerformed(ActionEvent e) {
                           
        	 clickdrink(gimbab.getDrink(),"콜라or사이다",flag_drink);
            gimbab.setDrink("콜라or사이다");
            showlabel(gimbab.getName(),gimbab.getPrice());
            flag_drink=true;
            
         }
      });
      rdbtncidar.setBounds(250, 115, 120, 26);
      panel_1.add(rdbtncidar);
      rdbtncidar.setBackground(Color.WHITE);
      
      JRadioButton rdbtnBeer = new JRadioButton("  맥주");
      buttonGroup_1.add(rdbtnBeer);
      rdbtnBeer.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	 clickdrink(gimbab.getDrink(),"맥주",flag_drink);
            gimbab.setDrink("맥주");
            showlabel(gimbab.getName(),gimbab.getPrice());
            flag_drink=true;
         }
      });
      rdbtnBeer.setBounds(390, 115, 121, 26);
      panel_1.add(rdbtnBeer);
      rdbtnBeer.setBackground(Color.WHITE);
      
      //---------------
      
      
      JLabel lblNewLabel = new JLabel("토핑추가");
      lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
      lblNewLabel.setBounds(45, 45, 99, 18);
      panel_1.add(lblNewLabel);
      
      JLabel label = new JLabel("음료");
      label.setFont(new Font("Dialog", Font.BOLD, 15));
      label.setBounds(45, 120, 99, 18);
      panel_1.add(label);

      
      //라디오버튼
      JPanel panel_2 = new JPanel();
      panel_2.setBounds(0, 512, 784, 217);
      frame.getContentPane().add(panel_2);
      panel_2.setLayout(null);
      panel_2.setBackground(Color.WHITE);
      
      lblNewLabel_1 = new JLabel();
      lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 15));
      lblNewLabel_1.setText("");
      
      lblNewLabel_1.setBounds(50, 85, 700, 26);
      panel_2.add(lblNewLabel_1);
      //스트링에 문자열을 넣고 액션이 나올떄마다 스트링에 값을 넣어주고 마지막으로 값을 넣어준다.
      lblNewLabel_1.setText(menu);
            
      
      JButton btnNewButton_5 = new JButton("결제");//결제 누르면 서버로 입력
      btnNewButton_5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count=0;
            sameCount = 0;
            if (lblNewLabel_1.getText()=="") {
            	JOptionPane.showMessageDialog(null, "메뉴를 선택하세요.");
				return;
			}
            
            if(checkOrder() == 0){
               checkOrder();
                              
               dao.gimbabadd(gimbab);//판매정보 데이터에 저장
               System.out.println(gimbab);
               //재고줄어들게
               dao.stockorder(stock);//재고 서버에 저장
               System.out.println(stock);
               buttonGroup.clearSelection();
               buttonGroup_1.clearSelection();
               lblNewLabel_1.setText("");
               JOptionPane.showMessageDialog(null, "결제 되었습니다");
               //재고가 자동관리 메소드
               gimbab.setName(null);
               gimbab.setDrink(null);
               gimbab.setStock(null);
               gimbab.setPrice(0);
               menu= "";
               stockCount = 0;
               flag_drink=false;
               scroll_plus=false;
            }else{
               buttonGroup.clearSelection();
               buttonGroup_1.clearSelection();
               lblNewLabel_1.setText("");
               gimbab.setName(null);
               gimbab.setDrink(null);
               gimbab.setStock(null);
               gimbab.setPrice(0);
               menu= "";
               stockCount = 0;
               JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다. 재료를 주문하세요.");
               flag_drink=false;
               scroll_plus=false;
            }
            
         
         }
      });
      btnNewButton_5.setBounds(598, 121, 123, 38);
      panel_2.add(btnNewButton_5);
      
      JButton button_10 = new JButton("선택 취소");
      button_10.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count=0;
            sameCount = 0;
            //flag_shot=false;
            scroll_plus=false;
            flag_drink=false;
            gimbab.setName(null);
            gimbab.setDrink(null);
            gimbab.setStock(null);
            gimbab.setPrice(0);
            lblNewLabel_1.setText("");
            menu= "";
            stockCount = 0;
            // 버튼 초기화
            buttonGroup.clearSelection();
            buttonGroup_1.clearSelection();
            buttonGroup_2.clearSelection(); 
            System.out.println(gimbab);
            JOptionPane.showMessageDialog(null, "선택 취소되었습니다.");
            
         }
      });
      button_10.setBounds(431, 121, 123, 38);
      panel_2.add(button_10);
      
      JButton button_11 = new JButton("판매보기");//판매확인
      button_11.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
            new SellListUI();
            System.out.println(dao.GetAllSellList());  
         }
      });
      button_11.setBounds(57, 121, 123, 38);
      panel_2.add(button_11);
      
      JButton btnNewButton_6 = new JButton("재고관리");
      btnNewButton_6.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new InventoryUI();
         }
      });
      btnNewButton_6.setBounds(192, 121, 123, 38);
      panel_2.add(btnNewButton_6);
   }
   
   
   
   public void showlabel(String name, int price){//판매 데이터 입력
	      
	      gimbab.setName(name);
	      gimbab.setPrice(price);
	      String stockPlus = "";
	      String menuStock="";
	      if(gimbab.getName() != null && gimbab.getPrice() != 0 ){
	         menu = gimbab.getName()+ " / " + gimbab.getPrice();
	         
	         if(gimbab.getName() != null && gimbab.getPrice() != 0 ){
	            menu = gimbab.getName()+ " / " + gimbab.getPrice();
	         }
	         if(gimbab.getStock() != null){
	        	 stockPlus =" / "+gimbab.getStock();
	        	 
	        	if (stockCount <= 1) {
	        		menu = gimbab.getName()+ " / " + (gimbab.getPrice()+500);
	        		menu += stockPlus;
				}else if (stockCount > 1) {
					menu = gimbab.getName()+ " / " + (gimbab.getPrice()+stockCount*500);
					gimbab.setPrice(gimbab.getPrice()+stockCount*500);
					menuStock = menu + stockPlus;
	        		lblNewLabel_1.setText(menuStock);
				}
			}
	         if(gimbab.getDrink() != null){
	        	 if (stockCount <= 1) {
	        		 menu = menu + " / " +gimbab.getDrink();
	        	 } else {
	        		 menuStock = menuStock+ " / " + gimbab.getDrink();
	        		 lblNewLabel_1.setText(menuStock);
				 }
	         }
	      }      
	      
	      if (stockCount<=1) {
	    	  lblNewLabel_1.setText(menu);
	      }
	   }//showlabel


   
   //주문할때 재고 확인 하는 메소드
   public int checkOrder(){
      int result=0;
      
      if(  stock.getCreamcheese() == -1|| 
           stock.getMuelchoo() == -1   || 
           stock.getTuna() == -1 ||
           stock.getJae6() == -1 ||
           stock.getDonggas() == -1 ||
           stock.getChicken() == -1 ||
           stock.getLabster() == -1 ||
           stock.getBulgogi()== -1 ||
           stock.getShrimp() == -1 ||
           stock.getJangajji() == -1 ||
           stock.getAlmonds() == -1
      ){
         result=-1;   
      }   
         return result;
   }//checkOrder
   
   
   public void clickdrink(String befor_drink,String size,boolean flag_drink){// 사이즈관련된 클릭정보관리 메소드
      
      if(flag_drink){
         //두번째 이상
         switch (size) {
         
         case "주시쿨":
            switch (befor_drink) {
            case "주시쿨":
               break;
            case "콜라or사이다":
               gimbab.setPrice(gimbab.getPrice()-500);
               break;
            case "맥주":
               gimbab.setPrice(gimbab.getPrice()-1000);
               break;
            default:
               break;
            }            
            break;
            
         case "콜라or사이다":
            switch (befor_drink) {
            case "주시쿨":
               gimbab.setPrice(gimbab.getPrice()+500);
               break;
            case "콜라or사이다":
               break;
            case "맥주":
               gimbab.setPrice(gimbab.getPrice()-500);
               break;
            default:
               break;
            }
            break;
            
         case "맥주":
            switch (befor_drink) {
            case "주시쿨":
               gimbab.setPrice(gimbab.getPrice()+1000);
               break;
            case "콜라or사이다":
               gimbab.setPrice(gimbab.getPrice()+500);
               break;
            case "맥주":
               break;
            default:
               break;
            }
            break;
            
         default:
            break;
         }
      }else{
         //처음누를떄
         switch (size) {
         
         case "주시쿨":
            
            break;
         case "콜라or사이다":
            gimbab.setPrice(gimbab.getPrice()+500);
            break;
         case "맥주":
            gimbab.setPrice(gimbab.getPrice()+1000);
            break;   
            
         default:
            break;
         }
         
      }
      
   }
}