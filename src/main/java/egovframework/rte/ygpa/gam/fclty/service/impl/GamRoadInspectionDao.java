/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadMngGroupVO;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadInspectionVO;

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

@Repository("gamRoadInspectionDao")
public class GamRoadInspectionDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectRoadInspectionList(GamRoadInspectionVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamRoadInspectionDao.selectRoadInspectionList", searchVO);
	}

	/**
	 * @param deleteVO
	 */
	public void deleteRoadInspection(GamRoadInspectionVO deleteVO) {
		// TODO Auto-generated method stub
		delete("gamRoadInspectionDao.deleteRoadInspection", deleteVO);
	}

	/**
	 * @param inputVO
	 */
	public String gamInsertRoadInspection(Map<?,?> inputVO) {
		// TODO Auto-generated method stub
		return (String) insert("gamRoadInspectionDao.gamInsertRoadInspection", inputVO);
	}

	/**
	 * @param inputVO
	 */
	public void gamUpdateRoadInspection(Map<?,?> inputVO) {
		// TODO Auto-generated method stub
		update("gamRoadInspectionDao.gamUpdateRoadInspection", inputVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectRoadMngGroupList(GamRoadInspectionVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamRoadInspectionDao.selectRoadMngGroupList", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectRoadInspectionDetailList(GamRoadInspectionVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamRoadInspectionDao.selectRoadInspectionDetailList", searchVO);
	}

	/**
	 * @param insertObj
	 */
	public void gamInsertRoadInspectionDetail(Map insertObj) {
		// TODO Auto-generated method stub
		insert("gamRoadMaintenanceDao.gamInsertRoadInspectionDetail", insertObj);
	}

	/**
	 * @param deleteObj
	 */
	public void gamDeleteRoadInspectionDetail(Map deleteObj) {
		// TODO Auto-generated method stub
		delete("gamRoadMaintenanceDao.gamDeleteRoadInspectionDetail", deleteObj);

	}


}
