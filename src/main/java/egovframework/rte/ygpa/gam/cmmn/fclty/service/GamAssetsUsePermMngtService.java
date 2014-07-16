/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service;

import java.util.Map;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 14.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamAssetsUsePermMngtService {

	/**
	 * 자산에 대해서 사용 승낙을 한다.
	 * @param vo (prtAtCode:항코드, mngYear:관리번호, mngNo:관리 순번, mngCnt:관리 횟수, chrgeKnd: 요금종류, regUsr: 등록자 아이디)
	 * @throws Exception
	 */
	public void confirmAssetsRentUsePerm(Map<String, Object> vo) throws Exception;

	/**
	 * 자산에 대해서 사용 승낙을 취소 한다.
	 * @param vo (prtAtCode:항코드, mngYear:관리번호, mngNo:관리 순번, mngCnt:관리 횟수, regUsr: 등록자 아이디)
	 * @throws Exception
	 */
	public void cancelAssetsRentUsePerm(Map<String, Object> vo) throws Exception;

	/**
	 * 항만시설에 대해서 사용승낙을 한다.
	 * @param vo (prtAtCode:항코드, mngYear:관리번호, mngNo:관리 순번, mngCnt:관리 횟수, chrgeKnd: 요금종류)
	 * @throws Exception
	 */
	public void confirmPrtFcltyUsePerm(Map<String, Object> vo) throws Exception;

	/**
	 * 항만시설에 대해서 사용 승낙을 취소 한다.
	 * @param vo (prtAtCode:항코드, mngYear:관리번호, mngNo:관리 순번, mngCnt:관리 횟수)
	 * @throws Exception
	 */
	public void cancelPrtFcltyUsePerm(Map<String, Object> vo) throws Exception;

	/**
	 * 항만시설 사용분에 대해 추가고지한다
	 * @param vo (prtAtCode:항코드, mngYear:관리번호, mngNo:관리 순번, mngCnt:관리 횟수, chrgeKnd: 요금종류, payTmlmt: 납부기한, fee: 사용료, vat: 부가세, vatYn: 부가세 여부, rm: 주석, regUsr: 등록자)
	 * @throws Exception
	 */
	public void insertBillCreateAdit(Map<String, Object> vo) throws Exception;
}
