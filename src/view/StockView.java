package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.HavingVO;
import model.MemVO;
import model.StockVO;

public class StockView {
   Scanner sc = new Scanner(System.in);
   
   public int printMenu1() { // 메뉴판1
      System.out.println("         ◈ 메뉴 ◈         ");
      System.out.println("1. 회원가입하기");
      System.out.println("2. 로그인하기");
      System.out.println("3. 종료");
      System.out.print("번호 입력 >> ");
      int act = sc.nextInt();
      return act;
   }
   
   public String print_add_name() { // 회원 이름 추가
      System.out.println("이름을 입력하세요(실명 입력)");
      System.out.print(" >> ");
      String mname = sc.next();
      return mname;
   }
   
   public String print_add_id() { // 회원 아이디 추가
	   System.out.println("     ◈ 웹뽀증권 회원가입 ◈     ");
      System.out.println("아이디를 입력하세요");
      System.out.print(" >> ");
      String mid = sc.next();
      return mid;
   }
   
   public void add_suc() { // 가입 성공 메세지
      System.out.println();
      System.out.println("회원가입을 축하드립니다!");
      System.out.println("예수금을 넣고 거래를 시작하시길 바랍니다!");
      System.out.println();
   }
   
   public void add_fail() { // 가입 실패 메시지
      System.out.println();
      System.out.println("회원가입을 실패하였습니다.");
      System.out.println("※ 중복된 아이디, 확인 후 재가입 부탁드립니다.");
      System.out.println();
   }
   
   public String view_start() {//로그인 할 아이디 입력
      System.out.println("     ◈ 웹뽀증권 로그인 ◈      ");
      System.out.println("아이디를 입력하세요");
      System.out.print(" >> ");
      String mid = sc.next();
      return mid;
   } 
   
   public void view_suc_start(MemVO vo) {
      System.out.println(vo.getMname() + "님 어서오세요!");
   }
   
   public void view_no_start() { // 로그인 실패 메세지
      System.out.println("로그인에 실패하였습니다.");
      System.out.println("아이디 확인 후 재시도 부탁드립니다.");
   }
   
   public void print_bye() { // 바로 종료하기인지 로그인화면으로 돌아가서 종료하기 누를건지
      System.out.println("        종료합니다!        ");
      System.out.println(" 행복한 하루 되세요! <(＿　＿)> ");
   }
   
   public int printMenu2() { // 메뉴판 2
      System.out.println("         ◈ 메뉴 ◈         ");
      System.out.println("1. 장 전 주식 목록 보기");
      System.out.println("2. 주식 구매");
      System.out.println("3. 주식 판매");
      System.out.println("4. 장 후 주식 목록");
      System.out.println("5. 주식 잔고");
      System.out.println("6. 예수금 입금 및 출금");
      System.out.println("7. 회원 탈퇴");
      System.out.println("8. 로그아웃");
      System.out.print("번호 입력 >> ");
      int act = sc.nextInt();
      return act;
   }

   public void print_y1_list(ArrayList<StockVO> datas) { // 장 전 주식목록(전일 거래량 Top5)
	   System.out.println("     ◈ 장 전 주식 전일 거래량 Top5 목록 ◈     ");
	   for(StockVO v : datas) {
		   System.out.print(String.format("%-3s", v.getSpk())+"\t");
		   System.out.print(String.format("%-27s", v.getSname())+"\t");
		   System.out.print("시가: "+String.format("%-6s", v.getSmkprice())+"\t");
		   System.out.print("등락률: "+ v.getSudpercent()+"%\t");
		   System.out.print("전일 거래량: "+ v.getSytrade()+"\n");
	   }
	   System.out.println();
   }

   public void print_y2_list(ArrayList<StockVO> datas) { // 장 전 주식목록(전체)
	   System.out.println("     ◈ 장 전 주식 전체 목록 ◈     ");
	   for(StockVO v : datas) {
		   System.out.print(String.format("%-3s", v.getSpk())+"\t");
		   System.out.print(String.format("%-27s", v.getSname())+"\t");
		   System.out.print("시가: "+String.format("%-6s", v.getSmkprice())+"\t");
		   System.out.print("등락률: "+ v.getSudpercent()+"%\t");
		   System.out.print("전일 거래량: "+ v.getSytrade()+"\n");
	   }
   }

   public int buy_stock() { // 매수할 주식 선택(pk)
      System.out.println("매수 할 주식 번호를 입력하세요");
      System.out.print(" >> ");
      int spk = sc.nextInt();
      return spk;
   }
   
   public int buy_num_stock() { // 매수할 수량 선택
      System.out.println("매수 할 수량 입력하세요");
      System.out.print(" >> ");
      int snum = sc.nextInt();
      return snum;
   }
   
   public void buy_suc() { // 매수 성공 메세지
      System.out.println("매수에 성공했습니다!");
      System.out.println("구매한 주식은 '주식잔고'에서 확인하실 수 있습니다!");
   }
   
   public void buy_suc_out(int money) { // 매수 성공시 출금금액
      System.out.println("매수금 : "+money);
   }
   
   public void buy_suc_money(MemVO vo) { // 매수 성공시 잔액
      System.out.println("잔액 : " + vo.getMwallet());
   }
   
   public void buy_suc_money_ment(MemVO vo) {
      System.out.println(vo.getMname() + " | 예수금: " + vo.getMwallet() + " | 투자금: " + vo.getMplwallet() + " | 평가총액: " + vo.getMtotal() + " | 손익금액: "+vo.getMpltotal()+"원 | 손익퍼센트: " +vo.getMplpercent()+"%");
   }
   
   
   public void wrong_pk() { // 매수 실패(잘못된 pk) 메세지
       System.out.println("매수에 실패하였습니다!");
       System.out.println("회사 번호를 다시 확인하세요!");
   }
   public void wrong_cnt() { // 매수 실패(잘못된 cnt) 메세지
	   System.out.println("매수에 실패하였습니다!");
	   System.out.println("매수할 수량을 다시 확인하세요!");
   }

   public void no_money() { // 매수 실패(잔고부족) 메세지
       System.out.println("매수에 실패하였습니다!");
       System.out.println("잔액 부족입니다!");
   }
   
   public int sell_stock() { // 매도 할 주식 선택
       System.out.println("매도 할 회사의 번호를 입력하세요");
       System.out.print(" >> ");
       int spk = sc.nextInt();
       return spk;
   }
   
   public int sell_num_stock() { // 매도 할 수량 선택
       System.out.println("매도 할 수량을 입력하세요");
       System.out.print(" >> ");
       int snum = sc.nextInt();
       return snum;
   }
   
   public void sell_wrong_cnt() { // 매도 실패(잘못된 cnt) 메세지
	   System.out.println("매도에 실패하였습니다!");
	   System.out.println("매도할 수량을 다시 확인하세요!");
   }
   public void sell_fail() { // 입력 수량 > 가지고있는 수량
      System.out.println("매도에 실패하였습니다!");
      System.out.println("현재 보유한 주식 수량을 초과하였습니다!");
   }
   
   public void sell_fail_pk() { // 
	   System.out.println("매도에 실패하였습니다!");
	   System.out.println("회사 번호를 다시 확인하세요!");
   }
   
   public void sell_suc() { // 매도 성공 메세지
      System.out.println("매도에 성공하였습니다!");
      System.out.println("수익/손익금은 '주식잔고'에서 확인하실 수 있습니다!");
   }

   public void sell_suc_in(int money) { // 입금(매도) 성공 메세지
      System.out.println("매도금 : "+ money);
   }

   public int print_all_list_act() { // 장 후 주식 목록
      System.out.println("     ◈ 장 후 주식 목록 ◈     ");
      System.out.println("1. 전체 목록 보기");
      System.out.println("2. 상승 종목 보기");
      System.out.println("3. 하락 종목 보기");
      System.out.print("번호 입력 >> ");
      int act = sc.nextInt();
      return act;
   }
 
   public void print_n_list(ArrayList<StockVO> datas) { // 장 후 전체목록
      System.out.println("     ◈ 전체 목록 보기 ◈     ");
      for(StockVO v : datas) {
         System.out.print(String.format("%-3s", v.getSpk())+"\t");
         System.out.print(String.format("%-27s", v.getSname())+"\t");
         System.out.print("현재가: "+String.format("%-6s", v.getSnprice())+"\t");
         System.out.print("등락률: "+ v.getSudpercent()+"%\t");
         System.out.print("현재 거래량: " + v.getSntrade()+"\n");
       }
   }
   
   public void print_up_list(ArrayList<StockVO> datas) { // 장 후 상승목록
      System.out.println("     ◈ 상승 종목 보기 ◈     ");
      for(StockVO v : datas) {
         System.out.print(String.format("%-3s", v.getSpk())+"\t");
         System.out.print(String.format("%-27s", v.getSname())+"\t");
         System.out.print("현재가: "+String.format("%-6s", v.getSnprice())+"\t");
         System.out.print("등락률: "+ v.getSudpercent()+"%\t");
         System.out.print("현재 거래량: " + v.getSntrade()+"\n");
      }
   }
   
   public void down_list(ArrayList<StockVO> datas) { // 장 후 하락목록
      System.out.println("     ◈ 하락 종목 보기 ◈     ");
      for(StockVO v : datas) {
         System.out.print(String.format("%-3s", v.getSpk())+"\t");
         System.out.print(String.format("%-27s", v.getSname())+"\t");
         System.out.print("현재가: "+String.format("%-6s", v.getSnprice())+"\t");
         System.out.print("등락률: "+ v.getSudpercent()+"%\t");
         System.out.print("현재 거래량: " + v.getSntrade()+"\n");
      }
   }
   
   public void my_money(MemVO vo) { // 내 잔고보기
      System.out.println("     ◈ 내 잔고 보기 ◈     ");
      System.out.println("평가 총액 : \t\t\t"+vo.getMtotal());
      System.out.println("손익금액  손익률 : "+ "\t\t"+vo.getMpltotal() +"\t"+ vo.getMplpercent()+"%");
      System.out.println("소유한 돈 : \t\t\t" + vo.getMwallet());
   } 

   public void my_stock(ArrayList<HavingVO> datas) { // 내 수익률 보기
	   System.out.println("=================================================================================================");
	   System.out.println(String.format("%-10s","회사번호")+String.format("%-30s", "회사명   \t")
	   +String.format("%-10s","소유량")+" "+String.format("%-10s","현재가")+String.format("%-15s"," 손익금액")+"손익퍼센트");

	   for(int i = 0 ; i < datas.size() ; i++ ) {

		   System.out.print(String.format("%-10s",datas.get(i).getPk()));
		   System.out.print(String.format("%-30s"," "+datas.get(i).getHstock())+"\t");
		   System.out.print(String.format("%-7s",datas.get(i).getHcnt())+"\t");
		   System.out.print(String.format("%-10s","   "+datas.get(i).getHnprice())+"\t");
		   System.out.print(datas.get(i).getHpltotal()+"원\t\t");
		   System.out.print(datas.get(i).getHplpercent()+"%\t");
		   System.out.println();
	   }
	   System.out.println("=================================================================================================");
	   System.out.println();
   }



   public int printMenu3() {
         System.out.println("         ◈ 예수금 입금 및 출금 ◈         ");
         System.out.println("1. 입금");
         System.out.println("2. 출금");
         System.out.print("번호 입력 >> ");
         int act = sc.nextInt();
         return act;
   }
   
   public int add_money() { // 예수금 입금하기
      System.out.println("     ◈ 예수금 입금하기 ◈     ");
      System.out.println("입금할 금액을 입력하세요(단위 제외)");
      System.out.print(" >> ");
      int act = sc.nextInt();
      return act;
   }
   
   public void add_fail_money() {// 예수금 입금 실패
      System.out.println("예수금 입금에 실패하였습니다.");
      System.out.println("다시 시도 부탁드립니다!");
   }
   
   public void add_suc_money() { // 예수금 입금 성공
	   System.out.println("예수금 입금에 성공하였습니다.");
	   System.out.println("추가로 넣은 예수금은 잔고에서 확인 가능합니다!");
   }

   public int out_money() { 
	   System.out.println("     ◈ 예수금 출금하기 ◈     ");
	   System.out.println("출금할 금액을 입력하세요(단위 제외)");
	   System.out.print(" >> ");
	   int act = sc.nextInt();
	   return act;
   }

   public void out_fail_money() {
	   System.out.println("예수금 출금에 실패하였습니다.");
	   System.out.println("다시 시도 부탁드립니다!");
   }
      
   public void out_suc_money() { 
         System.out.println("예수금 출금에 성공하였습니다.");
      }
   
   public void delete_mystock() { // 회원 탈퇴 안내
      System.out.println("       ◈ 회원 탈퇴 ◈       ");
      System.out.println("탈퇴시 원복이 불가능합니다.");
   } 

   public void delete_fail() {   // 회원 탈퇴 실패
      System.out.println("탈퇴 실패하였습니다.");
      System.out.println("보유한 주식이 있습니다!");
      System.out.println("매도 후 탈퇴 가능합니다!");
   }
   
   public void delete_fail2() {   // 회원 탈퇴 실패
         System.out.println("탈퇴 실패하였습니다.");
         System.out.println("예수금이 남아있습니다!");
         System.out.println("잔액 출금 후 탈퇴 가능합니다!");
      }

   public void delete_suc() {  // 회원 탈퇴 성공
      System.out.println("탈퇴 성공하였습니다!");
      System.out.println("주식을 구매하고싶다면 재가입 후 이용가능합니다.");
   }
   
   public void logout() { // 로그아웃
      System.out.println("로그아웃 성공!");
      System.out.println("이용 하시려면 재로그인 부탁드립니다!");
   }

   //다시 입력해주세요
   public void fail_ment() {// 유효성 검사
      System.out.println("잘못된 입력입니다!");
      System.out.println("확인 후 다시 입력부탁드립니다!");
   }
   
   
   
   
   
}