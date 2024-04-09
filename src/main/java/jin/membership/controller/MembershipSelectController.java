package jin.membership.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.membership.control.Controller;
import jin.membership.dao.MembershipDAO;
import jin.membership.dto.MembershipDTO;
import jin.membership.handler.MembershipHandlerAdapter;

public class MembershipSelectController implements Controller {
    private static Log log = LogFactory.getLog(MembershipSelectController.class);

    @Override
    public MembershipHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("user_id");
        
        MembershipDAO membershipDAO = new MembershipDAO();
//      사용자 아이디 조회
        MembershipDTO membershipDTO = membershipDAO.getMembershipByUserId(userId);
        
        
        MembershipHandlerAdapter membershipHandlerAdapter = new MembershipHandlerAdapter();

        if (membershipDTO == null) {
            // 멤버십에 가입하지 않은 경우
            membershipHandlerAdapter.setPath("/WEB-INF/view/membership_form.jsp"); // 가입 페이지로 이동
        } else {
        	// 조회된 멤버십 정보를 JSP 파일에 전달
            request.setAttribute("membershipDTO", membershipDTO);
            membershipHandlerAdapter.setPath("/WEB-INF/view/membership_select_view.jsp"); // 멤버십 정보 페이지로 이동
            
//          NullPointerException 방지
            if (membershipDTO != null) {
                request.setAttribute("user_id", membershipDTO.getUser_id());
                request.setAttribute("membership_grade", membershipDTO.getMembership_grade());
                request.setAttribute("payment_date", membershipDTO.getPayment_date());
                request.setAttribute("payment_method", membershipDTO.getPayment_method());
                request.setAttribute("payment_price", membershipDTO.getPayment_price());
            }

            
            log.info(membershipDTO);
        
        }


        
        return membershipHandlerAdapter;
    }
}
