package DAO;

import java.sql.*;

public class MenuInsert {
	
	public void MenuIns(String menu, String price) {
		final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "mydb";
        final String DB_USER = "root";
        final String DB_PASS = "1234";
        final String DB_URL = "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
        Connection conn = null;		//db 연결
        PreparedStatement pstmt = null;		//쿼리 생성
        
        String sql = "INSERT INTO `tb_menu` (`menu`,`price`) VALUES (?,?);";
        try {
            Class.forName(driver);		//jdbc 라이브러리
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            
            if (conn != null) {
                System.out.println("DB 접속 성공");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, menu);
                pstmt.setString(2, price);
                
                int result = pstmt.executeUpdate();
                if(result == 1){
                	System.out.print("데이터 삽입 성공");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("데이터 삽입 실패");
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
	}

}
