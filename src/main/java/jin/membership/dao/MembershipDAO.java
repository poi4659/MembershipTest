package jin.membership.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.membership.dto.MembershipDTO;
import jin.membership.service.MembershipService;

public class MembershipDAO implements MembershipService{
    private static final Log log = LogFactory.getLog(MembershipDAO.class);

//  멤버십 전체 조회
    @Override
    public ArrayList<MembershipDTO> membershipSelectAll() {
        // TODO Auto-generated method stub
        return null;
    }

//  멤버십 상세 조회
    @Override
    public MembershipDTO membershipSelect(MembershipDTO membershipDTO) {
    	Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            // 데이터베이스 연결
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
            connection = dataSource.getConnection();

            String sql = "select user_id, membership_grade, to_char(payment_date, 'yyyy-mm-dd') payment_date, payment_method, payment_price from membership";
            sql += " where user_id = ? ";

        	preparedStatement = connection.prepareStatement(sql);
        	preparedStatement.setString(1, membershipDTO.getUser_id( ));
            
            // 쿼리 실행
            resultSet = preparedStatement.executeQuery();

            // 조회 결과 처리
            if (resultSet.next()) {
            	// 멤버십 정보 생성 및 설정
            	log.info("아이디 확인 - " + resultSet.getString("user_id"));
            	membershipDTO.setUser_id(resultSet.getString("user_id"));
                membershipDTO.setMembership_grade(resultSet.getString("membership_grade"));
                membershipDTO.setPayment_date(resultSet.getString("payment_date"));
                membershipDTO.setPayment_method(resultSet.getString("payment_method"));
                membershipDTO.setPayment_price(resultSet.getInt("payment_price"));
                
            }
        } catch (Exception e) {
            log.error("멤버십 정보 상세 조회 실패: " + e.getMessage());
        } finally {
            // 리소스 해제
            try {
            	resultSet.close( );
				preparedStatement.close( );
				connection.close( );
            } catch (SQLException e) {
                log.error("리소스 해제 실패: " + e.getMessage());
            }
        }

        return membershipDTO;
    }

//  사용자 아이디 데이터베이스에서 조회
    public MembershipDTO getMembershipByUserId(String user_id) {
        MembershipDTO membershipDTO = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 데이터베이스 연결
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
            connection = dataSource.getConnection();

            // SQL 쿼리 작성
            String sql = "SELECT * FROM membership WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user_id);

            // 쿼리 실행
            resultSet = preparedStatement.executeQuery();

            // 조회 결과 처리
            if (resultSet.next()) {
            	// 멤버십 정보 생성 및 설정
            	membershipDTO = new MembershipDTO();
            	membershipDTO.setUser_id(resultSet.getString("user_id"));
            	membershipDTO.setMembership_grade(resultSet.getString("membership_grade"));
//            	그냥 TimeStamp로 하면 오류나서 .toLocalDateTime()을 붙여주니 해결
//            	DTO에서는 LocalDateTime으로 저장해서 생긴 문제
//            	sysdate로 저장하기 때문에 이제 setPayment_date를 호출 안해도 됨
//            	membershipDTO.setPayment_date(resultSet.getTimestamp("payment_date").toLocalDateTime()); // payment_date 값 설정
            	membershipDTO.setPayment_method(resultSet.getString("payment_method")); // payment_method 값 설정
            	membershipDTO.setPayment_price(resultSet.getInt("payment_price")); // payment_price 값 설정
                
            }
        } catch (Exception e) {
            log.error("멤버십 정보 조회 실패: " + e.getMessage());
        } finally {
            // 리소스 해제
            try {
            	resultSet.close( );
				preparedStatement.close( );
				connection.close( );
            } catch (SQLException e) {
                log.error("리소스 해제 실패: " + e.getMessage());
            }
        }

        return membershipDTO;
    }
    
    // 멤버십 가입
    @Override
    public MembershipDTO membershipInsert(MembershipDTO membershipDTO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // JNDI를 사용하여 데이터 소스 가져오기
            Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
            connection = dataSource.getConnection();

            String sql = "INSERT INTO membership (user_id, membership_grade, payment_date, payment_method, payment_price ) ";
            sql += "VALUES (?, ?, sysdate, ?, ?)";

            log.info("SQL 확인 - " + sql);

            preparedStatement = connection.prepareStatement(sql);

//          결제 날짜는 sysdate여서 setString 안해줘도 됨
            preparedStatement.setString(1, membershipDTO.getUser_id());
            preparedStatement.setString(2, membershipDTO.getMembership_grade());
            preparedStatement.setString(3, membershipDTO.getPayment_method());
            preparedStatement.setInt(4, membershipDTO.getPayment_price());

            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                // 자동 커밋 해제
                connection.setAutoCommit(false);
                connection.commit();
                log.info("커밋되었습니다.");
            } else {
                connection.rollback();
                log.info("롤백되었습니다.");
            }

        } catch (Exception e) {
            log.info("멤버십 가입 실패 - " + e);
        } finally {
            try {
				preparedStatement.close( );
				connection.close( );
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return membershipDTO;
    }

 // 멤버십 수정
    @Override
    public MembershipDTO membershipUpdate(MembershipDTO membershipDTO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // JNDI를 사용하여 데이터 소스 가져오기
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
            connection = dataSource.getConnection();

            // SQL 쿼리 작성-디벨로퍼에서 확인했는데 문제 없음
            String sql = "UPDATE membership SET membership_grade = ?, payment_method = ?, payment_price = ? WHERE user_id = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, membershipDTO.getMembership_grade());
            preparedStatement.setString(2, membershipDTO.getPayment_method());
            preparedStatement.setInt(3, membershipDTO.getPayment_price());
            preparedStatement.setString(4, membershipDTO.getUser_id());

            // 쿼리 실행
            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                // 자동 커밋 해제
                connection.setAutoCommit(false);
                connection.commit();
                log.info("커밋되었습니다.");
            } else {
                connection.rollback();
                log.info("롤백되었습니다.");
            }

        } catch (Exception e) {
            log.error("멤버십 수정 실패 - " + e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return membershipDTO;
    }


    @Override
    public MembershipDTO membershipDelete(MembershipDTO membershipDTO) {
    	Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // JNDI를 사용하여 데이터 소스 가져오기
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
            connection = dataSource.getConnection();

            String sql = "delete from membership ";
			sql += " where user_id = ? ";
			log.info("SQL - " + sql);
			
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, membershipDTO.getUser_id());

            // 쿼리 실행
            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                // 자동 커밋 해제
                connection.setAutoCommit(false);
                connection.commit();
                log.info("커밋되었습니다.");
            } else {
                connection.rollback();
                log.info("롤백되었습니다.");
            }

        } catch (Exception e) {
            log.error("멤버십 수정 실패 - " + e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return membershipDTO;
    }


}
