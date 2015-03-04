/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeDefaultVO;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeVO;
import egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateDefaultVO;
import egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateVO;

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

@Repository("gamCofixIntrrateMngtDao")
public class GamCofixIntrrateMngtDao extends YGPAAbstractDAO {

	/**
	 * 업체정보관리 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectCofixIntrrateList(GamCofixIntrrateDefaultVO vo) throws Exception{
		return list("gamCofixIntrrateMngtDao.selectCofixIntrrateList_D", vo);
	}

	public String insertCofixIntrrate(GamCofixIntrrateVO vo) throws Exception {
		return (String) insert("gamCofixIntrrateMngtDao.insertCofixIntrrate_S", vo);
	}

	public void updateCofixIntrrate(GamCofixIntrrateVO vo) throws Exception {
		delete("gamCofixIntrrateMngtDao.updateCofixIntrrate_S", vo);
	}

	public void deleteCofixIntrrate(GamCofixIntrrateVO vo) throws Exception {
		delete("gamCofixIntrrateMngtDao.deleteCofixIntrrate_S", vo);
	}
}
