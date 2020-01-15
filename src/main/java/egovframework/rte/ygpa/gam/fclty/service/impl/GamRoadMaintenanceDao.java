/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadMaintenanceVO;

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

@Repository("gamRoadMaintenanceDao")
public class GamRoadMaintenanceDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectRoadMaintenanceList(GamRoadMaintenanceVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamRoadMaintenanceDao.selectRoadMaintenanceList", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectRoadMaintenanceDetailList(GamRoadMaintenanceVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamRoadMaintenanceDao.selectRoadMaintenanceDetailList", searchVO);
	}

	/**
	 * @param inputVO
	 * @return
	 */
	public String gamInsertRoadMaintenance(Map<?,?> inputVO) {
		// TODO Auto-generated method stub
		return (String)insert("gamRoadMaintenanceDao.gamInsertRoadMaintenance", inputVO);
	}

	/**
	 * @param inputVO
	 * @return
	 */
	void gamUpdateRoadMaintenance(Map<?,?> inputVO) {
		// TODO Auto-generated method stub
		update("gamRoadMaintenanceDao.gamUpdateRoadMaintenance", inputVO);
	}

	/**
	 * @param deleteVO
	 * @return
	 */
	void gamDeleteRoadMaintenance(GamRoadMaintenanceVO deleteVO) {
		// TODO Auto-generated method stub
		delete("gamRoadMaintenanceDao.gamDeleteRoadMaintenance", deleteVO);
	}
	
	/**
	 * @param insertObj
	 */
	public void gamInsertRoadMaintenanceDetail(Map insertObj) {
		// TODO Auto-generated method stub
		insert("gamRoadMaintenanceDao.gamInsertRoadMaintenanceDetail", insertObj);
		
	}

	/**
	 * @param deleteVO
	 * @return
	 */
	void gamDeleteRoadMaintenanceDetail(GamRoadMaintenanceVO deleteVO) {
		// TODO Auto-generated method stub
		delete("gamRoadMaintenanceDao.gamDeleteRoadMaintenanceDetail", deleteVO);
	}

	/**
	 * @param printVo
	 * @return
	 */
	public List selectRoadMaintenanceDetailPrint(GamRoadMaintenanceVO printVo) {
		// TODO Auto-generated method stub
		return list("gamRoadMaintenanceDao.selectRoadMaintenanceDetailPrint", printVo);
	}
}
