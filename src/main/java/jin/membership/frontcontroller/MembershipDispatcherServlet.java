package jin.membership.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.membership.control.Controller;
import jin.membership.controller.MembershipDeleteController;
import jin.membership.controller.MembershipInsertController;
import jin.membership.controller.MembershipSelectController;
import jin.membership.controller.MembershipSelectDetailController;
import jin.membership.controller.MembershipUpdateController;
import jin.membership.handler.MembershipHandlerAdapter;


/**
 * Servlet implementation class MembershipDispatcherServlet
 */
@WebServlet("/MembershipDispatcherServlet")
public class MembershipDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(MembershipDispatcherServlet.class);
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		
//		서블릿이 클라이언트로부터 받은 HTTP 요청 URL 처리
		
		String requestURI = request.getRequestURI();

		String contextPath = request.getContextPath();
		
		/*
		 * 요청 URI에서 컨텍스트 경로를 제외한 부분을 추출함
		 * ->실제 서블릿 매핑명을 얻을 수 있음
		 */
		String pathURL = requestURI.substring(contextPath.length());
		
//		서블릿 매핑명을 로그에 출력
		log.info("매핑명 조회 - " + pathURL);
		
//		서블릿에서 사용할 DeptHandlerAdapter 객체를 초기화
		MembershipHandlerAdapter membershipHandlerAdapter = null;
		
//		서블릿에서 사용할 Controller 객체를 초기화
		Controller controller = null;
		
//		멤버십 가입
		if (pathURL.equals("/MembershipInsert.mes")) {
			controller = new MembershipInsertController();

			membershipHandlerAdapter = controller.execute(request, response);
			
//			멤버십 가입 확인-서버의 로그 파일에 기록됨
			log.info("멤버십 가입 확인 - " + membershipHandlerAdapter);
			
		}	else if (pathURL.equals("/MembershipInsertView.mes")) {
			membershipHandlerAdapter = new MembershipHandlerAdapter();

//			멤버십 가입 성공 확인-서버의 로그 파일에 기록됨
			membershipHandlerAdapter.setPath("/WEB-INF/view/membership/membership_insert.jsp");
			log.info("멤버십 가입 확인 - " + membershipHandlerAdapter);

		}	else if (pathURL.equals("/MembershipSelect.mes")) {
			controller = new MembershipSelectController();

			membershipHandlerAdapter = controller.execute(request, response);

//			멤버십 조회 성공 확인-서버의 로그 파일에 기록됨
			log.info("멤버십 조회 성공 확인 - " + membershipHandlerAdapter);

		}	else if (pathURL.equals("/MembershipSelectDetail.mes")) {
			controller = new MembershipSelectDetailController();

			membershipHandlerAdapter = controller.execute(request, response);

//			멤버십 상세 조회 성공 확인-서버의 로그 파일에 기록됨
			log.info("멤버십 상세 조회 성공 확인 - " + membershipHandlerAdapter);

		}	else if (pathURL.equals("/MembershipUpdateView.mes")) {
			membershipHandlerAdapter = new MembershipHandlerAdapter();

			membershipHandlerAdapter.setPath("/WEB-INF/view/membership/membership_update.jsp");

//			멤버십 수정 뷰 확인-서버의 로그 파일에 기록됨
			log.info("멤버십 수정 뷰 확인 - " + membershipHandlerAdapter);

		}	else if (pathURL.equals("/MembershipUpdate.mes")) {
			controller = new MembershipUpdateController();

			membershipHandlerAdapter = controller.execute(request, response);

//			멤버십 수정 성공 확인-서버의 로그 파일에 기록됨
			log.info("멤버십 수정 성공 확인 - " + membershipHandlerAdapter);

		}	else if (pathURL.equals("/MembershipDeleteView.mes")) {
			membershipHandlerAdapter = new MembershipHandlerAdapter();

			membershipHandlerAdapter.setPath("/WEB-INF/view/membership/membership_delete.jsp");

//			멤버십 해지 성공 확인-서버의 로그 파일에 기록됨
			log.info("멤버십 해지 뷰 확인 - " + membershipHandlerAdapter);

		}	else if (pathURL.equals("/MembershipDelete.mes")) {
			controller = new MembershipDeleteController();

			membershipHandlerAdapter = controller.execute(request, response);

//			멤버십 해지 성공 확인-서버의 로그 파일에 기록됨
			log.info("멤버십 해지 성공 확인 - " + membershipHandlerAdapter);

		}
	
	
//		isRedirect 메서드 값이 false이면 포워드 방식으로 처리하고 true면 리다이렉트로 처리
		if (membershipHandlerAdapter != null) {
			if (membershipHandlerAdapter.isRedirect()) {
				response.sendRedirect(membershipHandlerAdapter.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(membershipHandlerAdapter.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
		
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
