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

public class MembershipDeleteController implements Controller {
	private static Log log = LogFactory.getLog(MembershipDeleteController.class);

	@Override
	public MembershipHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		log.info(user_id);
		MembershipDAO membershipDAO = new MembershipDAO();
		MembershipDTO membershipDTO = new MembershipDTO();

// 		입력한 정보를 DeptDTO 클래스의 인스턴스에 저장한다.
		membershipDTO.setUser_id(user_id);
		
// 		속성에 DTO 객체를 속성값으로 저장한다.
		request.setAttribute("membershipDTO", membershipDTO);
		
// 		삭제한 데이터를 반환하여 deptDTO 인스턴스로 할당하고 할당하지 않으면 null 값이 반환된다.
		membershipDTO = membershipDAO.membershipDelete(membershipDTO);
		log.info(membershipDTO);
		
		MembershipHandlerAdapter membershipHandlerAdapter = new MembershipHandlerAdapter();
		
// 		포워드로 파라미터를 전송한다.
		membershipHandlerAdapter.setPath("/WEB-INF/view/membership/membership_delete_view.jsp");
		return membershipHandlerAdapter;

	}

}
