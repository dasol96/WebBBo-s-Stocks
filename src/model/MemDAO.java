package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemDAO {
      Connection conn;
      PreparedStatement pstmt;
      final String id_insert="insert into mem (mid,mname) values(?,?)";
      final String id_select="select * from mem where mid like ? ";
      final String money_update="update mem set mwallet=mwallet+? where mid=?";
      final String mem_update1="update mem set mwallet=mwallet-?,mplwallet =mplwallet+?,mtotal =mtotal+?,mpltotal =mpltotal+?,mplpercent=? where mid=?";
      final String mem_update2="update mem set mwallet=mwallet+?,mplwallet =mplwallet-?,mtotal =mtotal-?,mpltotal =mpltotal-?,mplpercent=? where mid=?";
      final String id_delete="delete from mem where mid=? ";
      final String money_update2="update mem set mwallet=mwallet-? where mid=?";
      
      public void mem_money_update2(MemVO vo) {
          conn=JDBCUtil.connect();
             try {
                pstmt=conn.prepareStatement(money_update2);
               
                
                pstmt.setInt(1, vo.getMwallet());
                pstmt.setString(2, vo.getMid());
                pstmt.executeUpdate();
                
                
             
             } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
               
             } finally {
                JDBCUtil.disconnect(pstmt, conn);
             }
      }
      public boolean mem_id_insert(MemVO  vo) {
            conn=JDBCUtil.connect();
            try {
               pstmt=conn.prepareStatement(id_insert);
               pstmt.setString(1, vo.getMid());
               pstmt.setString(2, vo.getMname());
               pstmt.executeUpdate();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               return false;
            } finally {
               JDBCUtil.disconnect(pstmt, conn);
            }
            return true;
         }
   
      public MemVO mem_one_select(MemVO vo) {
         MemVO data=null;

            conn=JDBCUtil.connect();
            try {
               pstmt=conn.prepareStatement(id_select);
               pstmt.setString(1, vo.getMid());
               ResultSet rs=pstmt.executeQuery();

               if(rs.next()) {
                  data=new MemVO();
                  data.setMid(rs.getString("mid"));
                  data.setMname(rs.getString("mname"));
                  data.setMwallet(rs.getInt("mwallet"));
                  data.setMplwallet(rs.getInt("mplwallet"));
                  data.setMplpercent(rs.getDouble("mplpercent"));
                  data.setMtotal(rs.getInt("mtotal"));
                  data.setMpltotal(rs.getInt("mpltotal"));
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
      public boolean mem_money_update(MemVO vo) {
         conn=JDBCUtil.connect();
            try {
               pstmt=conn.prepareStatement(money_update);
               if(vo.getMwallet()<=0) {
                  return false;
               }else {
               pstmt.setInt(1, vo.getMwallet());
               pstmt.setString(2, vo.getMid());
               pstmt.executeUpdate();
                 return true;
               }

            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
               return false;
            } finally {
               JDBCUtil.disconnect(pstmt, conn);
            }
      }
            public boolean mem_update1(MemVO vo) {
               conn=JDBCUtil.connect();
                  try {
                     pstmt=conn.prepareStatement(mem_update1);
                     
                     pstmt.setInt(1, vo.getMwallet());
                     pstmt.setInt(2, vo.getMplwallet());
                     pstmt.setInt(3, vo.getMtotal());
                     pstmt.setDouble(4, vo.getMpltotal());
                     pstmt.setDouble(5, vo.getMplpercent());
                     pstmt.setString(6, vo.getMid());
                     pstmt.executeUpdate();
                      
                     

                  } catch (SQLException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                     return false;
                  } finally {
                     JDBCUtil.disconnect(pstmt, conn);
                  } 
            return true;
      }
            public boolean mem_update2(MemVO vo) {
               conn=JDBCUtil.connect();
                  try {
                     pstmt=conn.prepareStatement(mem_update2);
                     
                     pstmt.setInt(1, vo.getMwallet());
                     pstmt.setInt(2, vo.getMplwallet());
                     pstmt.setInt(3, vo.getMtotal());
                     pstmt.setDouble(4, vo.getMpltotal());
                     pstmt.setDouble(5, vo.getMplpercent());
                     pstmt.setString(6, vo.getMid());
                     pstmt.executeUpdate();
                      
                     

                  } catch (SQLException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                     return false;
                  } finally {
                     JDBCUtil.disconnect(pstmt, conn);
                  } 
            return true;
      }
            
            public boolean mem_id_delete(MemVO vo) {
               conn=JDBCUtil.connect();
               try {
                  pstmt=conn.prepareStatement(id_delete);
                  pstmt.setString(1, vo.getMid());
                  pstmt.executeUpdate();
                  
               } catch (SQLException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                  return false;
               } finally {
                  JDBCUtil.disconnect(pstmt, conn);
               }
               return true;
            }      
            
            
            
            
            
            
}