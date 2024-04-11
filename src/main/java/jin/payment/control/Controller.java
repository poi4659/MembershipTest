package jin.payment.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jin.membership.handler.MembershipHandlerAdapter;
import jin.payment.handler.PaymentHandlerAdapter;

public interface Controller {
	
	public PaymentHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response);
}
