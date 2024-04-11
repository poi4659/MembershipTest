package jin.payment.service;

import java.util.ArrayList;

import jin.payment.dto.paymentDTO;

public interface PaymentService {
//	전체 결제 조회
	public ArrayList<paymentDTO> paymentSelectAll();
}
