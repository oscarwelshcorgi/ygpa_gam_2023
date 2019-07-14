/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.code.service.GamBjdOlnlpMngtVO;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeDefaultVO;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeVO;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 12.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamBjdOlnlpMngtDao")
public class GamBjdOlnlpMngtDao extends YGPAAbstractDAO {

	/**
	 * 공시지가 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectBjdOlnlpList(GamBjdOlnlpMngtVO vo) throws Exception{
		return list("gamBjdOlnlpMngtDao.selectBjdOlnlpList_D", vo);
	}

	public int selectBjdOlnlpListToTCnt(GamBjdOlnlpMngtVO vo) throws Exception{
		return (Integer) selectByPk("gamBjdOlnlpMngtDao.selectBjdOlnlpListTotCnt_S", vo);
	}

	/**
	 * 공시지가 목록 저장
	 * @param mergeMap
	 * @return
	 */
	public List mergeOlnlpMngt(Map mergeMap) throws Exception {
		return this.merge(mergeMap, "gamBjdOlnlpMngtDao.insertOlnlpMngt", "gamBjdOlnlpMngtDao.updateOlnlpMngt", "gamBjdOlnlpMngtDao.deleteOlnlpMngt");
	}

	public void deleteOlnlpExists() throws Exception {
		delete("gamBjdOlnlpMngtDao.deleteOlnlpExists", null);
	}

	public void addOlnlpAll() throws Exception {
		insert("gamBjdOlnlpMngtDao.addOlnlpAll", null);
	}

	/**
	 * @return
	 */
	public List selectBupjungdongCd() {
		// TODO Auto-generated method stub
		return list("gamBjdOlnlpMngtDao.selectBupjungdongCd", null);
	}

	/**
	 * @param bjdOlnlpLinkVO
	 */
	public void insertBupjungdongOlnlpF(Map bjdOlnlpLinkVO) {
		// TODO Auto-generated method stub
		insert("gamBjdOlnlpMngtDao.insertBupjungdongOlnlpF", bjdOlnlpLinkVO);
	}
}
