package jin.membership.service;

import java.util.ArrayList;

import jin.membership.dto.MembershipDTO;


public interface MembershipService {

//	전체 멤버십 조회
	public ArrayList<MembershipDTO> membershipSelectAll();
	
//	멤버십 상세 조회
	public MembershipDTO membershipSelect(MembershipDTO membershipDTO);

//	멤버십 가입
	public MembershipDTO membershipInsert(MembershipDTO membershipDTO);
	
//	멤버십 수정
	public MembershipDTO membershipUpdate(MembershipDTO membershipDTO);
	
//	멤버십 삭제
	public MembershipDTO membershipDelete(MembershipDTO membershipDTO);
}
