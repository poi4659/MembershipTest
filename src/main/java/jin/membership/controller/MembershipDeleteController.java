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


public class MembershipDeleteController implements Controller{
	private static Log log = LogFactory.getLog(MembershipDeleteController.class);

	@Override
	public MembershipHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		
		return null;

	}


}
