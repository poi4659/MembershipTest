package jin.membership.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.membership.control.Controller;
import jin.membership.dao.MembershipDAO;
import jin.membership.dto.MembershipDTO;
import jin.membership.handler.MembershipHandlerAdapter;

public class MembershipUpdateController implements Controller {
    private static Log log = LogFactory.getLog(MembershipUpdateController.class);

    @Override
    public MembershipHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
        // 사용자가 입력한 폼 데이터 받아오기
        String user_id = request.getParameter("user_id");
        String new_membership_grade = request.getParameter("new_membership_grade");
        String new_payment_method = request.getParameter("new_payment_method");

        // 새로운 멤버십 등급에 따른 결제 금액 설정
        int new_payment_price = 0;
        if ("Gold".equals(new_membership_grade)) {
            new_payment_price = 10000;
        } else if ("Silver".equals(new_membership_grade)) {
            new_payment_price = 7000;
        }

        // 현재 날짜와 시간 가져오기 (Java 8 이상)
        LocalDateTime payment_date = LocalDateTime.now();

        // LocalDateTime을 문자열로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = payment_date.format(formatter);

        // 멤버십 정보를 멤버십 DTO 객체에 저장
        MembershipDTO membershipDTO = new MembershipDTO();
        membershipDTO.setUser_id(user_id);
        membershipDTO.setMembership_grade(new_membership_grade);
        membershipDTO.setPayment_date(formattedDate);
        membershipDTO.setPayment_method(new_payment_method);
        membershipDTO.setPayment_price(new_payment_price);

        log.info("MembershipDTO to be updated: " + membershipDTO);

        // 멤버십 정보를 데이터베이스에 업데이트
        MembershipDAO membershipDAO = new MembershipDAO();
        
        MembershipDTO updatedMembershipDTO = membershipDAO.membershipUpdate(membershipDTO);

        log.info("Updated MembershipDTO: " + updatedMembershipDTO);

        // 업데이트된 멤버십 정보를 JSP 파일에 전달
        request.setAttribute("updatedMembershipDTO", updatedMembershipDTO);

        // MembershipHandlerAdapter 객체 생성
        MembershipHandlerAdapter membershipHandlerAdapter = new MembershipHandlerAdapter();

        // 멤버십 업데이트 완료 페이지로 이동
        membershipHandlerAdapter.setPath("/WEB-INF/view/membership/membership_update_view.jsp");

        return membershipHandlerAdapter;
    }
}
