package jin.membership.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.membership.control.Controller;
import jin.membership.dao.MembershipDAO;
import jin.membership.dto.MembershipDTO;
import jin.membership.frontcontroller.MembershipDispatcherServlet;
import jin.membership.handler.MembershipHandlerAdapter;
import oracle.net.aso.m;

public class MembershipInsertController implements Controller{
	private static Log log = LogFactory.getLog(MembershipInsertController.class);

	@Override
	public MembershipHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 사용자가 입력한 폼 데이터 받아오기
        String user_id = request.getParameter("user_id");
        String membership_grade = request.getParameter("membership_grade");
        String payment_method = request.getParameter("payment_method");
        int payment_price = 0;
        
        // 멤버십 등급에 따른 결제 금액 설정
        if ("Gold".equals(membership_grade)) {
            payment_price = 10000;
        } else if ("Silver".equals(membership_grade)) {
        	payment_price = 7000;
        }
        
        // 현재 날짜와 시간 가져오기 (Java 8 이상)
        LocalDateTime payment_date = LocalDateTime.now();

//      멤버십 정보를 멤버십 DTO 객체에 저장 (사용자 아이디는 나중에 로그인 연동 시 처리할 예정)
        MembershipDTO membershipDTO = new MembershipDTO();
        membershipDTO.setUser_id(user_id);
        membershipDTO.setMembership_grade(membership_grade);
        membershipDTO.setPayment_date(payment_date);
        membershipDTO.setPayment_method(payment_method);
        membershipDTO.setPayment_price(payment_price);
        
        log.info(membershipDTO);
        
        // 멤버십 정보를 데이터베이스에 저장
        MembershipDAO membershipDAO = new MembershipDAO();
        membershipDTO = membershipDAO.membershipInsert(membershipDTO);
        request.setAttribute("membershipDTO", membershipDTO);
        
		MembershipHandlerAdapter membershipHandlerAdapter = new MembershipHandlerAdapter();
		
		// 가입 완료 페이지로 이동
//		JSP 파일의 경로를 설정한 후 DeptHandlerAdapter 객체 생성하여 반환
		membershipHandlerAdapter.setPath("/WEB-INF/view/membership_insert_view.jsp");
		return membershipHandlerAdapter;

	}


}
