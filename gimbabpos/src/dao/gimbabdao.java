package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import bean.Gimbab;
import bean.Stock;


public class gimbabdao {
	private String driver = "oracle.jdbc.driver.OracleDriver" ;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe" ;
	private String username = "javaexam" ;
	private String password = "m1234" ;
	private Connection conn = null ;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public gimbabdao() {
		try {
			Class.forName(driver) ;			
		} catch (ClassNotFoundException e) {
			System.out.println("클래스가 발견되지 않습니다(jar 파일 누락)"); 
			e.printStackTrace();		
		}
	}

	private Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password) ;
		} catch (SQLException e) {
			System.out.println("커넥션 생성 오류");
			e.printStackTrace();
		}
		return conn ;
	}


	private void closeConnection() {
		try {
			if(rs != null) {rs.close(); }
			if(pstmt != null) {pstmt.close(); }
			if(conn != null) {conn.close(); }
		} catch (Exception e2) {
			e2.printStackTrace(); 
		}		
	}

	public int gimbabadd(Gimbab bean){//서버에 판매정보 저장
		int result =-1;
		
		String sql = "insert into gimbab(gimbabname,drink,gimbabstock,price)values(?,?,?,?)";
	
		try {
								
			conn = getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDrink());
			pstmt.setString(3, bean.getStock());
			pstmt.setInt(4, bean.getPrice());
						
			result = pstmt.executeUpdate();
			conn.commit();	
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback(); 
			} catch (Exception e2) {
				e2.printStackTrace();  
			}
		}finally{
			try {
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		
		return result;
	}//gimbabadd
	
	
	public int stockorder(Stock stock){// 이건재고 데이터 업데이트
		int result = 0;
		
		String sql = "update stock set creamcheese=?, muelchoo=?, tuna=?, jae6=?, donggas=?, chicken=?,"
				+ "labster=?, bulgogi=?, shrimp=?, jangajji=?, almonds=?";
		
		try {
			conn = getConnection() ;
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql); 
						
			pstmt.setInt(1, stock.getCreamcheese());
			pstmt.setInt(2, stock.getMuelchoo());
			pstmt.setInt(3, stock.getTuna());
			pstmt.setInt(4, stock.getJae6());
			pstmt.setInt(5, stock.getDonggas());
			pstmt.setInt(6, stock.getChicken());
			pstmt.setInt(7, stock.getLabster());
			pstmt.setInt(8, stock.getBulgogi());
			pstmt.setInt(9, stock.getShrimp());
			pstmt.setInt(10, stock.getJangajji());
			pstmt.setInt(11, stock.getAlmonds());
								
			result = pstmt.executeUpdate();
			conn.commit();
//			fireTableDataChanged();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback(); 
			} catch (Exception e2) {
				e2.printStackTrace();  
			}
		}finally{
			try {
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		
		return result;
	}//gimbabstock
	
	
	public  Vector<Gimbab> Getsellcount(){// 오늘 판매된 김밥
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select gimbabname , count(*)  from gimbab group by  gimbabname   order by count(*) desc" ;
		Vector<Gimbab> lists = new Vector<Gimbab>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 
			
			rs = pstmt.executeQuery() ;
			
			while(rs.next()){
				Gimbab gimbab = new Gimbab() ;
				gimbab.setName(rs.getString("gimbabname"));
				gimbab.setPrice( rs.getInt("count(*)") ); 
				
				lists.add( gimbab ) ;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			try {
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists;
	}//Getsellcount
	
	
	public Stock getstock(){//서버에서 재고데이터 가져오기
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from stock" ;		
		Stock stock=null;
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 
			
			rs = pstmt.executeQuery() ;
			
			while(rs.next()){
				stock = new Stock() ;
				
				stock.setCreamcheese(rs.getInt("creamcheese"));
				stock.setMuelchoo(rs.getInt("muelchoo"));
				stock.setTuna(rs.getInt("tuna"));
				stock.setJae6(rs.getInt("jae6"));
				stock.setDonggas(rs.getInt("donggas"));
				stock.setChicken(rs.getInt("chicken"));
				stock.setLabster(rs.getInt("labster"));
				stock.setBulgogi(rs.getInt("bulgogi"));
				stock.setShrimp(rs.getInt("shrimp"));
				stock.setJangajji(rs.getInt("jangajji"));
				stock.setAlmonds(rs.getInt("almonds"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return stock ;
	}//getstock

	
	public Vector<Gimbab> GetAllSellList() {//db에서 데이터를 받아서 벡터로 반환하는 메소드
		//모든 상품 목록들을 리턴한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from gimbab" ;
		Vector<Gimbab> lists = new Vector<Gimbab>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 
			
			rs = pstmt.executeQuery() ;
			
			while(rs.next()){
				Gimbab gimbab = new Gimbab() ;
				gimbab.setName(rs.getString("gimbabname"));
				gimbab.setDrink(rs.getString("drink"));
				gimbab.setStock(rs.getString("gimbabstock"));
				gimbab.setPrice(rs.getInt("price")); 
				
				lists.add( gimbab ) ;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			try {
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//GetAllSellList
		
	
	public Object[][] makeArr(Vector<Gimbab> lists){//벡터를 받아서 전체를 2차원 배열로 만들어주는 메소드
		
		Object [][] gimbabarr = new Object [lists.size()][4]; 
								
			for(int i=0; i<lists.size();i++){
				gimbabarr[i][0]=lists.get(i).getName();
				gimbabarr[i][1]=lists.get(i).getDrink();
				gimbabarr[i][2]=lists.get(i).getStock();
				gimbabarr[i][3]=lists.get(i).getPrice();
			}	
		
		return gimbabarr;
		
	}//makeArr
	
	
	public Object[][] makelistArr(Vector<Gimbab> lists){//벡터를 받아서 판대량을 2차원 배열로 만들어주는 메소드
		
		Object [][] gimbabarr = new Object [lists.size()][2]; 
				
			for(int i=0; i<lists.size();i++){
				gimbabarr[i][0]=lists.get(i).getName();
				gimbabarr[i][1]=lists.get(i).getPrice();
			}	
		return gimbabarr;
		
	}//makeArr

	
	public Object[][] makestocklistArr(Stock lists){//스탁을 받아서 오브젝트로 만들어줌
		
		Object [][] gimbabarr = new Object [1][11]; 
				
			gimbabarr[0][0]=lists.getCreamcheese();
			gimbabarr[0][1]=lists.getMuelchoo();
			gimbabarr[0][2]=lists.getTuna();
			gimbabarr[0][3]=lists.getJae6();
			gimbabarr[0][4]=lists.getDonggas();
			gimbabarr[0][5]=lists.getChicken();
			gimbabarr[0][6]=lists.getLabster();
			gimbabarr[0][7]=lists.getBulgogi();
			gimbabarr[0][8]=lists.getShrimp();
			gimbabarr[0][9]=lists.getJangajji();
			gimbabarr[0][10]=lists.getAlmonds();
		return gimbabarr;
	}//makestocklistArr
	
}
