package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAO {


   Connection conn;
   PreparedStatement pstmt;


   //전체 목록 보기 [ 회사 이름, 전일 거래량[top5], 시가]
   final String stock_y_select = "select spk, sname, sytrade, smkprice, sudpercent from (SELECT * FROM stock ORDER BY sytrade DESC) where rownum <=5"; 
   final String stock_y_All_select = "select spk, sname, sytrade, smkprice, sudpercent from stock"; 
   //전체 목록 보기 [ 회사 이름, 현재가, 등락률, 거래량]
   final String stock_all_select = "select spk, sname, snprice, sudpercent, sntrade from stock"; 
   //상승 종목 보기 [ 등락률 desc]
   final String stock_up_select = "select * from (SELECT * FROM stock ORDER BY sudpercent DESC) where rownum <=5"; 
   //하락 종목 보기 [ 등락률 asc]
   final String stock_down_select = "select * from (SELECT * FROM stock ORDER BY sudpercent asc) where rownum <=5"; 
   // 종목 하나 선택 [pk받고 모든 정보]
   final String stock_one_select="select * from stock where spk=?";

   //   전체 (전일가,시가)목록 보기 > 회사이름, 전일 거래량, 시가.
   public ArrayList<StockVO> stock_y_select(StockVO vo){
      ArrayList<StockVO> datas=new ArrayList<StockVO>();
      conn=JDBCUtil.connect();

      try {
         pstmt=conn.prepareStatement(stock_y_select);
         ResultSet rs=pstmt.executeQuery();
         while(rs.next()) {
            StockVO data=new StockVO();
            data.setSpk(rs.getInt("spk"));
            data.setSname(rs.getString("sname"));
            data.setSytrade(rs.getInt("sytrade"));
            data.setSmkprice(rs.getInt("smkprice"));
            data.setSudpercent(rs.getDouble("sudpercent"));
            datas.add(data);
         }
         rs.close();
      } 
      catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         JDBCUtil.disconnect(pstmt, conn);
      }
      return datas;
   }
   
   public ArrayList<StockVO> stock_y_All_select(StockVO vo){
      ArrayList<StockVO> datas=new ArrayList<StockVO>();
      conn=JDBCUtil.connect();
      
      try {
         pstmt=conn.prepareStatement(stock_y_All_select);
         ResultSet rs=pstmt.executeQuery();
         while(rs.next()) {
            StockVO data=new StockVO();
            data.setSpk(rs.getInt("spk"));
            data.setSname(rs.getString("sname"));
            data.setSytrade(rs.getInt("sytrade"));
            data.setSmkprice(rs.getInt("smkprice"));
            data.setSudpercent(rs.getDouble("sudpercent"));
            datas.add(data);
         }
         rs.close();
      } 
      catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         JDBCUtil.disconnect(pstmt, conn);
      }
      return datas;
   }

   //   전체 목록 보기 [ 회사 이름, 현재가, 등락률, 거래량]
   public ArrayList<StockVO> stock_all_select(StockVO vo){
      ArrayList<StockVO> datas=new ArrayList<StockVO>();
      conn=JDBCUtil.connect();

      try {
         pstmt=conn.prepareStatement(stock_all_select);
         ResultSet rs=pstmt.executeQuery();
         while(rs.next()) {
            StockVO data=new StockVO();
            data.setSpk(rs.getInt("spk"));
            data.setSname(rs.getString("sname"));
            data.setSnprice(rs.getInt("snprice"));
            data.setSudpercent(rs.getDouble("sudpercent"));
            data.setSntrade(rs.getInt("sntrade"));
            datas.add(data);
         }
         rs.close();
      } 
      catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         JDBCUtil.disconnect(pstmt, conn);
      }
      return datas;
   }

   //   상승 종목 보기 회사 이름, 현재가, 등락률, 거래량 top 5
   public ArrayList<StockVO> stock_up_select(StockVO vo){
      ArrayList<StockVO> datas=new ArrayList<StockVO>();
      conn=JDBCUtil.connect();

      try {
         pstmt=conn.prepareStatement(stock_up_select);
         ResultSet rs=pstmt.executeQuery();
         while(rs.next()) {
            StockVO data=new StockVO();
            data.setSpk(rs.getInt("spk"));
            data.setSname(rs.getString("sname"));
            data.setSnprice(rs.getInt("snprice"));
            data.setSudpercent(rs.getDouble("sudpercent"));
            data.setSntrade(rs.getInt("sntrade"));
            datas.add(data);
         }
         rs.close();
      } 
      catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         JDBCUtil.disconnect(pstmt, conn);
      }
      return datas;
   }

   //   하락 종목 보기 회사 이름, 현재가, 등락률, 거래량  top 5
   public ArrayList<StockVO> stock_down_select(StockVO vo){
      ArrayList<StockVO> datas=new ArrayList<StockVO>();
      conn=JDBCUtil.connect();

      try {
         pstmt=conn.prepareStatement(stock_down_select);
         ResultSet rs=pstmt.executeQuery();
         while(rs.next()) {
            StockVO data=new StockVO();
            data.setSpk(rs.getInt("spk"));
            data.setSname(rs.getString("sname"));
            data.setSnprice(rs.getInt("snprice"));
            data.setSudpercent(rs.getDouble("sudpercent"));
            data.setSntrade(rs.getInt("sntrade"));
            datas.add(data);
         }
         rs.close();
      } 
      catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         JDBCUtil.disconnect(pstmt, conn);
      }
      return datas;
   
   }
   
   
   //종목 하나 검색 pk받아서 모든 정보
    public StockVO stock_one_select(StockVO vo) {
       StockVO data = null;
       conn=JDBCUtil.connect();
       try {
         pstmt=conn.prepareStatement(stock_one_select);
         pstmt.setInt(1, vo.getSpk());
         ResultSet rs=pstmt.executeQuery();
         if(rs.next()) {
            data=new StockVO();
            data.setSpk(rs.getInt("spk"));
            data.setSname(rs.getString("sname"));
            data.setSmkprice(rs.getInt("smkprice"));
            data.setSnprice(rs.getInt("snprice"));
            data.setSudpercent(rs.getDouble("sudpercent"));
            data.setSytrade(rs.getInt("sytrade"));
            data.setSntrade(rs.getInt("sntrade"));
            
         }
         rs.close();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         JDBCUtil.disconnect(pstmt, conn);
      }
       return data;
    }


}
