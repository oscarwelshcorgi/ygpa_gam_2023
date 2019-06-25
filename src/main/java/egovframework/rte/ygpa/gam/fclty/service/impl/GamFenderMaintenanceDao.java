/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceVO;

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

@Repository("gamFenderMaintenanceDao")
public class GamFenderMaintenanceDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFenderMaintenanceList(GamFenderMaintenanceVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamFenderMaintenanceDao.selectFenderMaintenanceList", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFenderMaintenanceDetailList(GamFenderMaintenanceVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamFenderMaintenanceDao.selectFenderMaintenanceDetailList", searchVO);
	}

	/**
	 * @param inputVO
	 * @return
	 */
	public String gamInsertFenderMaintenance(Map<?,?> inputVO) {
		// TODO Auto-generated method stub
		return (String)insert("gamFenderMaintenanceDao.gamInsertFenderMaintenance", inputVO);
	}

	/**
	 * @param inputVO
	 * @return
	 */
	void gamUpdateFenderMaintenance(Map<?,?> inputVO) {
		// TODO Auto-generated method stub
		update("gamFenderMaintenanceDao.gamUpdateFenderMaintenance", inputVO);
	}

	/**
	 * @param deleteVO
	 * @return
	 */
	void gamDeleteFenderMaintenance(GamFenderMaintenanceVO deleteVO) {
		// TODO Auto-generated method stub
		delete("gamFenderMaintenanceDao.gamDeleteFenderMaintenance", deleteVO);
	}
	
	/**
	 * @param insertObj
	 */
	public void gamInsertFenderMaintenanceDetail(Map insertObj) {
		// TODO Auto-generated method stub
		insert("gamFenderMaintenanceDao.gamInsertFenderMaintenanceDetail", insertObj);
		
	}

	/**
	 * @param deleteVO
	 * @return
	 */
	void gamDeleteFenderMaintenanceDetail(GamFenderMaintenanceVO deleteVO) {
		// TODO Auto-generated method stub
		delete("gamFenderMaintenanceDao.gamDeleteFenderMaintenanceDetail", deleteVO);
	}

	/**
	 * @param printVo
	 * @return
	 */
	public List selectFenderMaintenanceDetailPrint(GamFenderMaintenanceVO printVo) {
		// TODO Auto-generated method stub
		return list("gamFenderMaintenanceDao.selectFenderMaintenanceDetailPrint", printVo);
	}
}
