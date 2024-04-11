package jin.payment.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jin.membership.handler.MembershipHandlerAdapter;

public interface Controller {
	
	public MembershipHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response);
}
