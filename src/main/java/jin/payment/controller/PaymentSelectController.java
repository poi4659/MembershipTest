package jin.payment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.payment.control.Controller;
import jin.payment.handler.PaymentHandlerAdapter;

public class PaymentSelectController implements Controller {
    private static Log log = LogFactory.getLog(PaymentSelectController.class);

	@Override
	public PaymentHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}


}
