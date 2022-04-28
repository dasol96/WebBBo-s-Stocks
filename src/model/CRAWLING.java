package model;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;





public class CRAWLING {
	 Connection conn;
	 PreparedStatement pstmt;
	final String sql="insert into stock  values((select nvl(max(spk),0)+1 from stock),?,?,?,?,?,?)";
	public void startdb() {
	

		conn=JDBCUtil.connect();
		final String url=" https://finance.naver.com/sise/field_submit.naver?menu=quant&returnUrl=http%3A%2F%2Ffinance.naver.com%2Fsise%2Fsise_quant.naver&fieldIds=quant&fieldIds=open_val&fieldIds=prev_quant\r\n";
		ArrayList<StockVO> datas=new ArrayList<StockVO>();
		Document doc=null;
		try {
			doc=Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Elements eles=doc.select("tbody>tr"); 
		Iterator<Element> itr=eles.iterator();
		Iterator<Element> itr2=eles.select("a").iterator();
		Iterator<Element> itr3=eles.select("td.number").iterator();
		
		while(itr3.hasNext()) {
		 StockVO svo=new StockVO();
		 
		 
		 svo.setSname(itr2.next().text());
		 int a=Integer.parseInt(itr3.next().text().replace(",", ""));
		 svo.setSnprice(a);
		 itr3.next().text();
		 itr3.next().text();
		
		 svo.setSntrade(Integer.parseInt(itr3.next().text().replace(",", "")));
		 svo.setSytrade(Integer.parseInt(itr3.next().text().replace(",", "")));
		int b=Integer.parseInt(itr3.next().text().replace(",", ""));
		 svo.setSmkprice(b);
		 double pct =(double)a/b*100.0-100;
		 svo.setSudpercent(Math.round(pct * 100) / 100.0);
		 datas.add(svo);
		}
		System.out.println("로그: 크롤링 성공");
		
		
		try {
		
			pstmt=conn.prepareStatement(sql);
			for(StockVO vo:datas){
				pstmt.setString(1, vo.getSname());
				pstmt.setInt(2, vo.getSmkprice());
				pstmt.setInt(3, vo.getSnprice());
				pstmt.setDouble(4, vo.getSudpercent());
				pstmt.setInt(5,vo.getSytrade());
				pstmt.setInt(6,vo.getSntrade());
				
				pstmt.executeUpdate(); 
			}
			System.out.println("로그 db 저장성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
	         JDBCUtil.disconnect(pstmt, conn);
	      }

	}
		
		
		
}




