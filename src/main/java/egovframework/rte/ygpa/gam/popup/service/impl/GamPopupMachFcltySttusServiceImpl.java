/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySttusMngVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupMachFcltySttusService;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 21.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 21.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupMachFcltySttusService")
public class GamPopupMachFcltySttusServiceImpl extends AbstractServiceImpl implements GamPopupMachFcltySttusService {

	@Resource(name="gamPopupMachFcltySttusDao")
	private GamPopupMachFcltySttusDao gamPopupMachFcltySttusDao;

	/**
	 * @name selectMachFcltySttusPk
	 * @param gamMachFcltySttusMngVO
	 * @return EgovMap
	 * @exception Exception
	 */
	public EgovMap selectMachFcltySttusPk(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception {
		return gamPopupMachFcltySttusDao.selectMachFcltySttusPk(gamMachFcltySttusMngVO);
	}

	/**
	 * @name insertMachFcltySttus
	 * @param gamMachFcltySttusMngVO
	 * @return void
	 * @exception Exception
	 */
	public void insertMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception {
		gamPopupMachFcltySttusDao.insertMachFcltySttus(gamMachFcltySttusMngVO);
	}

	/**
	 * @name updateMachFcltySttus
	 * @param gamMachFcltySttusMngVO
	 * @return void
	 * @exception Exception
	 */
	public void updateMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception {
		gamPopupMachFcltySttusDao.updateMachFcltySttus(gamMachFcltySttusMngVO);
	}

	/**
	 * @name deleteMachFcltySttus
	 * @param gamMachFcltySttusMngVO
	 * @return void
	 * @exception Exception
	 */
	public void deleteMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception {
		gamPopupMachFcltySttusDao.deleteMachFcltySttus(gamMachFcltySttusMngVO);
	}

}
