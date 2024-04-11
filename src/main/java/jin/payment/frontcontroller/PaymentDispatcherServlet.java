package jin.payment.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.payment.control.Controller;
import jin.payment.controller.PaymentSelectController;
import jin.payment.handler.PaymentHandlerAdapter;


/**
 * Servlet implementation class PaymentDispatcherServlet
 */
@WebServlet("/PaymentDispatcherServlet")
public class PaymentDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Log log = LogFactory.getLog(PaymentDispatcherServlet.class);

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
		PaymentHandlerAdapter paymentHandlerAdapter = null;
		
//		서블릿에서 사용할 Controller 객체를 초기화
		Controller controller = null;
		
//		멤버십 가입
		if (pathURL.equals("/PaymentHistory.pay")) {
			controller = new PaymentSelectController();

			paymentHandlerAdapter = controller.execute(request, response);
			
//			결제 내역 조회 확인-서버의 로그 파일에 기록됨
			log.info("결제 내역 조회 확인 - " + paymentHandlerAdapter);
			
		}
	
//		isRedirect 메서드 값이 false이면 포워드 방식으로 처리하고 true면 리다이렉트로 처리
		if (paymentHandlerAdapter != null) {
			if (paymentHandlerAdapter.isRedirect()) {
				response.sendRedirect(paymentHandlerAdapter.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(paymentHandlerAdapter.getPath());
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

