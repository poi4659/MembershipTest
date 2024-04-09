package jin.membership.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jin.membership.control.Controller;
import jin.membership.dao.MembershipDAO;
import jin.membership.dto.MembershipDTO;
import jin.membership.handler.MembershipHandlerAdapter;

public class MembershipSelectController implements Controller {

    @Override
    public MembershipHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("user_id");
        
        MembershipDAO membershipDAO = new MembershipDAO();
//      사용자 아이디 조회
        MembershipDTO membershipDTO = membershipDAO.getMembershipByUserId(userId);

        MembershipHandlerAdapter membershipHandlerAdapter = new MembershipHandlerAdapter();

        if (membershipDTO == null) {
            // 멤버십에 가입하지 않은 경우
            membershipHandlerAdapter.setPath("./MembershipInsert.mes"); // 가입 페이지로 이동
        } else {
        	// 조회된 멤버십 정보를 JSP 파일에 전달
            request.setAttribute("membershipDTO", membershipDTO);
            membershipHandlerAdapter.setPath("/WEB-INF/view/membership_select_view.jsp"); // 멤버십 정보 페이지로 이동
//            membershipHandlerAdapter.setPath("/WEB-INF/view/membership_info.jsp"); // 멤버십 정보 페이지로 이동
        }


        return membershipHandlerAdapter;
    }
}
