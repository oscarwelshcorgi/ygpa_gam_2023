package egovframework.com.sec.security.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import egovframework.com.cmm.LoginVO;
import egovframework.com.sec.security.userdetails.EgovUserDetails;
import egovframework.com.sec.security.userdetails.jdbc.EgovUsersByUsernameMapping;

/**
 * mapRow 결과를 사용자 EgovUserDetails Object 에 정의한다.
 *
 * @author ByungHun Woo
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *   2009.03.10  ByungHun Woo    최초 생성
 *   2009.03.20  이문준          UPDATE
 *
 * </pre>
 */

public class EgovSessionMapping extends EgovUsersByUsernameMapping {

	/**
	 * 사용자정보를 테이블에서 조회하여 EgovUsersByUsernameMapping 에 매핑한다.
	 * @param ds DataSource
	 * @param usersByUsernameQuery String
	 */
	public EgovSessionMapping(DataSource ds, String usersByUsernameQuery) {
        super(ds, usersByUsernameQuery);
    }

	/**
	 * mapRow Override
	 * @param rs ResultSet 결과
	 * @param rownum row num
	 * @return Object EgovUserDetails
	 * @exception SQLException
	 */
	@Override
    protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
    	logger.debug("## EgovUsersByUsernameMapping mapRow ##");

        String strUserId    = rs.getString("user_id");
        String strPassWord  = rs.getString("password");
        boolean strEnabled  = rs.getBoolean("enabled");

        String strUserNm    = rs.getString("user_nm");
        String strEmplNo    = rs.getString("empl_no");	// 2014-03-19 사원번호 추가
        String strUserSe    = rs.getString("user_se");
        String strUserEmail = rs.getString("user_email");
        String strOrgnztId  = rs.getString("orgnzt_id");
        String strUniqId    = rs.getString("esntl_id");
        /**2010.06.30 *이용   *조직명 추가  */
        String strOrgnztNm    = rs.getString("orgnzt_nm");
        String strDeptCd = rs.getString("dept_cd");
        String strQuayGroupCd = rs.getString("quay_group_cd");
        String strMngFcltyCd = rs.getString("mng_fclty_cd");



        // 세션 항목 설정
        LoginVO loginVO = new LoginVO();
        loginVO.setId(strUserId);
        loginVO.setPassword(strPassWord);
        loginVO.setName(strUserNm);
        loginVO.setUserSe(strUserSe);
        loginVO.setEmplNo(strEmplNo);	// 2014-03-19 사원번호 추가
        loginVO.setEmail(strUserEmail);
        loginVO.setOrgnztId(strOrgnztId);
        loginVO.setUniqId(strUniqId);
        /**2010.06.30 *이용   *조직명 추가  */
        loginVO.setOrgnztNm(strOrgnztNm);
        loginVO.setDeptCd(strDeptCd);
        loginVO.setQuayGroupCd(strQuayGroupCd);
        loginVO.setMngFcltyCd(strMngFcltyCd);

        return new EgovUserDetails(strUserId, strPassWord, strEnabled, loginVO);
    }
}
