package jin.membership.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.membership.control.Controller;
import jin.membership.dao.MembershipDAO;
import jin.membership.dto.MembershipDTO;
import jin.membership.handler.MembershipHandlerAdapter;

public class MembershipSelectDetailController implements Controller {
    private static Log log = LogFactory.getLog(MembershipSelectDetailController.class);

    
    @Override
    public MembershipHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
        String user_id = request.getParameter("user_id");
        
        MembershipDTO membershipDTO = new MembershipDTO();
        
        // 사용자 아이디 설정
        membershipDTO.setUser_id(user_id);

        MembershipDAO membershipDAO = new MembershipDAO();
        
        // 멤버십 상세 조회
        membershipDTO = membershipDAO.membershipSelect(membershipDTO);
        
        log.info(membershipDTO);
        
        // MembershipHandlerAdapter 객체 생성
        MembershipHandlerAdapter membershipHandlerAdapter = new MembershipHandlerAdapter();

        // 조회된 멤버십 정보를 JSP 파일에 전달
        request.setAttribute("membershipDTO", membershipDTO);
        
        // 멤버십 정보 페이지로 이동
        membershipHandlerAdapter.setPath("/WEB-INF/view/membership_select_detail_view.jsp"); 
        
        return membershipHandlerAdapter;
    }
}
