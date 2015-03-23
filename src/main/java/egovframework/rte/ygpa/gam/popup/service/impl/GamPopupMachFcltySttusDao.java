/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySttusMngVO;

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

@Repository("gamPopupMachFcltySttusDao")
public class GamPopupMachFcltySttusDao extends YGPAAbstractDAO {

	/**
	 * @name selectMachFcltySttusPk
	 * @param gamMachFcltySttusMngVO
	 * @return EgovMap
	 */
	public EgovMap selectMachFcltySttusPk(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) {
		return (EgovMap)selectByPk("PopupMachFcltySttusDao.selectMachFcltySttusPk_S", gamMachFcltySttusMngVO);
	}

	/**
	 * @name insertMachFcltySttus
	 * @param gamMachFcltySttusMngVO
	 * @return void
	 */
	public void insertMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) {
		update("PopupMachFcltySttusDao.insertMachFcltySttus_S", gamMachFcltySttusMngVO);
	}

	/**
	 * @name updateMachFcltySttus
	 * @param gamMachFcltySttusMngVO
	 * @return void
	 */
	public void updateMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) {
		update("PopupMachFcltySttusDao.updateMachFcltySttus_S", gamMachFcltySttusMngVO);
	}

	/**
	 * @name deleteMachFcltySttus
	 * @param gamMachFcltySttusMngVO
	 * @return void
	 */
	public void deleteMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) {
		update("PopupMachFcltySttusDao.deleteMachFcltySttus_S", gamMachFcltySttusMngVO);
	}

}
