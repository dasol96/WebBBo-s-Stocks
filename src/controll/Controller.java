package controll;

import java.util.ArrayList;

import model.HavingDAO;
import model.HavingVO;
import model.MemDAO;
import model.MemVO;
import model.StockDAO;
import model.StockVO;
import view.StockView;

public class Controller {

	MemDAO mdao;
	StockDAO sdao;
	HavingDAO hdao;
	StockView view;

	public Controller() {
		mdao = new MemDAO();
		sdao = new StockDAO();
		hdao = new HavingDAO();
		view = new StockView();
	}

	public void startApp() {

		while(true) {
			int act_Menu1 = view.printMenu1(); // 메뉴판1
			// 1번 회원가입
			if(act_Menu1 == 1) {
				MemVO mvo = new MemVO();
				String mid = view.print_add_id(); // 유저 아이디 입력 받기
				String mname = view.print_add_name(); // 유저 명 입력 받기
				mvo.setMid(mid);
				mvo.setMname(mname);

				if(mdao.mem_id_insert(mvo)) { // 회원 추가
					view.add_suc(); // 가입 성공 메세지
				} else {
					view.add_fail(); // 가입 실패 메세지
				}
			}
			// 2번 로그인
			else if(act_Menu1 == 2) {
				while(true) {
					MemVO mvo = new MemVO();
					String mid = view.view_start(); // 로그인할 아이디 입력 받기
					mvo.setMid(mid);

					mvo = mdao.mem_one_select(mvo); // 회원 선택
					if(mvo == null) {
						view.view_no_start(); // 로그인 실패 메세지
						break;
					}
					view.view_suc_start(mvo); // 로그인 성공 메세지

					// 로그인 성공
					while(true) {
						int act_Menu2 = view.printMenu2(); // 메뉴판2
						// 장 전 주식 목록 보기
						if(act_Menu2 == 1) {
							StockVO svo = new StockVO();
							ArrayList<StockVO> datas1 = sdao.stock_y_select(svo); // 장 전 top5 목록 보기 
							view.print_y1_list(datas1); // 장 전 전일 거래량 top5 목록 출력
							ArrayList<StockVO> datas2 =sdao.stock_y_All_select(svo); // 장 전 전체 목록 보기 				
							view.print_y2_list(datas2); // 장전 전체 주식 목록
						}
						// 주식 매수
						else if(act_Menu2 == 2) {
							StockVO svo = new StockVO();
							HavingVO hvo = new HavingVO();
							int pk = view.buy_stock(); // 매수할 주식 PK 입력 받기
							int cnt = view.buy_num_stock(); // 매수할 주식 수량 입력 받기
							svo.setSpk(pk);
							svo = sdao.stock_one_select(svo); // 종목 하나 선택

							if(svo == null) { // 없는 PK일 때
								view.wrong_pk(); // 잘못된 PK 입력 메세지
								continue;
							}
							// PK 입력이 올바를 때
							if(cnt <= 0) { // 잘못된 수량
								view.wrong_cnt(); // 잘못된 수량 입력 메세지
								continue;
							}
							// 매수할 수량이 올바를 때
							if(mvo.getMwallet() < svo.getSmkprice() * cnt) { // 사려는 주식이 소유한 돈보다 클 때
								view.no_money(); // 잔고 부족 메세지
								continue;
							}
							// 소유한 돈이 충분할 때
							hvo.setPk(svo.getSpk());
							hvo.setHname(mvo.getMname());
							hvo.setHstock(svo.getSname());
							hvo.setHnprice(svo.getSnprice());
							hvo.setHcnt(cnt);
							hvo.setHtotal(svo.getSnprice() * cnt);
							hvo.setHplpercent(svo.getSudpercent());//수정
							hvo.setHpltotal((svo.getSnprice() - svo.getSmkprice()) * cnt);
							// 주식 보유 유무 확인
							HavingVO check = new HavingVO();
							check.setHname(mvo.getMname());
							check.setHstock(svo.getSname());
							check = hdao.having_one_select(check);
							if(check == null){ // 가지고 있는 않은 주식일 때
								hdao.having_insert(hvo); // 새로운 데이터 추가
							} else { // 가지고 있는 주식일 때
								hdao.having_update_add(hvo); // 기존 데이터에 업데이트
							}
							
							double pct = (double)(mvo.getMtotal()  + svo.getSnprice() * cnt)
									/ (double)(mvo.getMplwallet() + svo.getSmkprice() * cnt) * 100 - 100;
							mvo.setMplpercent(Math.round(pct * 100) / 100.0); // 둘째자리까지
							mvo.setMwallet(svo.getSmkprice() * cnt);
							mvo.setMplwallet(svo.getSmkprice() * cnt);
							mvo.setMtotal(svo.getSnprice() * cnt);
							mvo.setMpltotal((svo.getSnprice() - svo.getSmkprice()) * cnt);
							if(mdao.mem_update1(mvo)) { // 소유한 돈 - 주식 매수 금액
								mvo = mdao.mem_one_select(mvo);
								view.buy_suc_money_ment(mvo);
								int use_money = svo.getSmkprice() * cnt;
								view.buy_suc_out(use_money); // 출금
								view.buy_suc_money(mvo); // 잔액
							}
							view.buy_suc(); // 매수 성공 메세지
						}
						// 주식 매도
						else if(act_Menu2 == 3) {
							StockVO svo = new StockVO();
							HavingVO hvo = new HavingVO();
							int pk = view.sell_stock(); // 매도할 주식 PK 입력 받기
							int cnt = view.sell_num_stock(); // 매도할 주식 수량 입력 받기
							svo.setSpk(pk);
							svo = sdao.stock_one_select(svo); // 종목 하나 선택
							// 주식 유무 확인
							hvo.setHname(mvo.getMname());
							if(svo==null) {
                                view.sell_fail_pk();
                            }else {
                            hvo.setHstock(svo.getSname());
                            }
							hvo = hdao.having_one_select(hvo);
							if(hvo == null) { // 가지고 있는 않은 주식일 때
								view.sell_fail_pk(); // 잘못된 PK 입력 메세지
								continue;
							}
							// 가지고 있는 주식일 때
							if(cnt <= 0) { // 입력한 수량이 0 이하일 때
								view.sell_wrong_cnt(); // 잘못된 수량에 대한 메세지
								continue;
							}
							if(cnt > hvo.getHcnt()) { // 입력한 수량이 내가 가진 주식 개수보다 많을 때
								view.sell_fail(); // 잘못된 수량에 대한 메세지
								continue;
							}
							// 올바른 수량 입력일 때
							double pct =(double)(mvo.getMtotal()  - svo.getSnprice() * cnt) 
									/ (double)(mvo.getMplwallet() - svo.getSmkprice() * cnt) * 100 - 100;
							mvo.setMplpercent(Math.round(pct * 100) / 100.0); //mvo에 손익 퍼센트
							mvo.setMwallet(svo.getSnprice() * cnt); // mvo.내 지갑에 주식 현재가 입력
							mvo.setMplwallet(svo.getSmkprice() * cnt); // mvo.손익지갑에 주식 시가 입력
							mvo.setMtotal(svo.getSnprice() * cnt); // mvo.총금액에 주식 현재가 입력
							mvo.setMpltotal((svo.getSnprice() - svo.getSmkprice()) * cnt);
							if(mdao.mem_update2(mvo)) { // 소유한 돈 + 주식 매도 금액
								int get_money = hvo.getHnprice() * cnt;  // 돈 입력 = 
								mvo = mdao.mem_one_select(mvo);
								view.sell_suc_in(get_money); // 입금
								view.buy_suc_money(mvo); // 잔액
							} 
							if(cnt == hvo.getHcnt()) { // 기존 데이터를 삭제 or 업데이트
								hdao.having_delete(hvo); // 기존 데이터 삭제
							} else { // 기존 데이터 업데이트(수량, 보유 주식 금액, 손익 금액 변경)
								hvo.setHcnt(cnt);
								hvo.setHtotal(svo.getSnprice() * cnt);
								hvo.setHpltotal((svo.getSnprice() - svo.getSmkprice()) * cnt);
								hdao.having_update_del(hvo); // 매도 후 기존 데이터에 업데이트
							}
							view.sell_suc(); // 매도 성공 메세지
						}
						// 장 후 주식 목록 보기
						else if(act_Menu2 == 4) {
							while(true) {
								int act_Menu3 = view.print_all_list_act(); // 메뉴판3
								// 전체 목록 보기
								if(act_Menu3 == 1) {
									StockVO svo = new StockVO();
									ArrayList<StockVO> datas = sdao.stock_all_select(svo); // 장 후 전체 목록 보기
									view.print_n_list(datas); // 장 후 전체 목록 출력
								}
								// 상승 종목 보기
								else if(act_Menu3 == 2) {
									StockVO svo = new StockVO();
									ArrayList<StockVO> datas = sdao.stock_up_select(svo); // 상승 종목 목록 보기
									view.print_up_list(datas); // 상승 종목 목록 출력
								}
								// 하락 종목 보기
								else if(act_Menu3 == 3) {
									StockVO svo = new StockVO();
									ArrayList<StockVO> datas = sdao.stock_down_select(svo); // 하강 종목 목록 보기
									view.down_list(datas); // 하강 종목 목록 출력
								}
								// 메뉴3 유효성 검사
								else {
									view.fail_ment(); // 유효성 멘트
									continue;
								}
								break;
							}
						}
						// 주식 잔고 보기
						else if(act_Menu2 == 5) {
							mvo = mdao.mem_one_select(mvo);// 회원 정보 가져오기
							view.my_money(mvo); // 평가 총액, 손익 금액, ... 출력
							ArrayList<HavingVO> datas =hdao.having_select_all(mvo); // 보유 주식 목록 받아오기
							if(datas.size()!=0) {
								view.my_stock(datas); // 보유 주식 목록 출력
							}
						}
						// 입출금
						else if(act_Menu2 == 6) {
							int act_Menu4=view.printMenu3();
							
							if(act_Menu4==1) { // 입금
								int money = view.add_money(); // 추가할 돈 입력 받기
								mvo.setMwallet(money);
								if(mdao.mem_money_update(mvo)) { // 돈 추가하기
									view.add_suc_money(); // 돈 추가 성공 메세지
								} else {
									view.add_fail_money(); // 돈 추가 실패 메세지
								}
								
							}else if(act_Menu4==2){ // 출금
								int money=view.out_money(); // 출금할 돈 입력받기
								mvo = mdao.mem_one_select(mvo);
								if(money<=0 || money>mvo.getMwallet()) {
									view.out_fail_money(); // 출금 실패
									continue;
								}
								mvo.setMwallet(money);
								mdao.mem_money_update2(mvo);
								view.out_suc_money(); // 출금 성공

							}else { // 메뉴4 유효성 검사
								view.fail_ment(); // 유효성 검사 멘트
								continue;
							}
						}
						// 회원 탈퇴
						else if(act_Menu2 == 7) {
							view.delete_mystock(); // 회원 탈퇴 안내 문구
							mvo=mdao.mem_one_select(mvo);
							ArrayList<HavingVO> datas = hdao.having_select_all(mvo); // 보유 주식 받아오기
							if(datas.size() != 0) { // 주식을 가지고 있을 때
								view.delete_fail(); // 회원 삭제 실패 메세지
								continue;
							}else if(mvo.getMwallet()!=0) { // 잔고가 남아있을 때
								view.delete_fail2();
								continue;
							}
							// 탈퇴 가능한 상태
							if(mdao.mem_id_delete(mvo)) { // 회원 삭제
								view.delete_suc(); // 회원 삭제 성공 메세지
								break; // 메뉴2 나가기
							}
						}
						// 로그아웃
						else if(act_Menu2 == 8) {
							view.logout(); // 로그아웃 멘트
							break;
						}
						// 메뉴2 유효성 검사
						else {
							view.fail_ment(); // 유효성 검사 멘트
							continue;
						}
				
					}
					break;
				}

			}
			// 3번 프로그램 종료
			else if(act_Menu1 == 3) {
				view.print_bye(); // 종료 멘트
				break;
			}
			// 메뉴1 유효성 검사
			else {
				view.fail_ment(); // 유효성 검사 멘트
				continue;
			}
		}






















	}
}
