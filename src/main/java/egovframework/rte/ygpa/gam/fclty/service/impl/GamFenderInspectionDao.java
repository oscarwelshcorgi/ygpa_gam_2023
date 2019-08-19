/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMngGroupVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionVO;

/**
*
* @author LFIT
* @since 2019. 6. 19.
* @version 1.0
* @see
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일 		 수정자		 수정내용
*  -------		--------	---------------------------
*  2019.6.19.		LFIT		최초 생성
*
* </pre>
*/

@Repository("gamFenderInspectionDao")
public class GamFenderInspectionDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFenderInspectionList(GamFenderInspectionVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamFenderInspectionDao.selectFenderInspectionList", searchVO);
	}

	/**
	 * @param deleteVO
	 */
	public void deleteFenderInspection(GamFenderInspectionVO deleteVO) {
		// TODO Auto-generated method stub
		delete("gamFenderInspectionDao.deleteFenderInspection", deleteVO);
	}

	/**
	 * @param inputVO
	 */
	public String gamInsertFenderInspection(Map<?,?> inputVO) {
		// TODO Auto-generated method stub
		return (String) insert("gamFenderInspectionDao.gamInsertFenderInspection", inputVO);
	}

	/**
	 * @param inputVO
	 */
	public void gamUpdateFenderInspection(Map<?,?> inputVO) {
		// TODO Auto-generated method stub
		update("gamFenderInspectionDao.gamUpdateFenderInspection", inputVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFenderMngGroupList(GamFenderInspectionVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamFenderInspectionDao.selectFenderMngGroupList", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFenderInspectionDetailList(GamFenderInspectionVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamFenderInspectionDao.selectFenderInspectionDetailList", searchVO);
	}

	/**
	 * @param insertObj
	 */
	public void gamInsertFenderInspectionDetail(Map insertObj) {
		// TODO Auto-generated method stub
		insert("gamFenderMaintenanceDao.gamInsertFenderInspectionDetail", insertObj);
	}

	/**
	 * @param deleteObj
	 */
	public void gamDeleteFenderInspectionDetail(Map deleteObj) {
		// TODO Auto-generated method stub
		delete("gamFenderMaintenanceDao.gamDeleteFenderInspectionDetail", deleteObj);

	}


}
