/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;
import java.util.Map;

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

public interface GamRoadMaintenanceService {

	/**
	 * @param searchVO
	 * @return
	 */
	List selectRoadMaintenanceList(GamRoadMaintenanceVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectRoadMaintenanceDetailList(GamRoadMaintenanceVO searchVO) throws Exception;

	/**
	 * @param inputVO
	 */
	void gamInsertRoadMaintenance(Map insertRoadMaintenance, List insertObjList) throws Exception;

	/**
	 * @param inputVO
	 */
	void gamUpdateRoadMaintenance(Map insertRoadMaintenance, List insertObjList) throws Exception;

	/**
	 * @param deleteVO
	 */
	void gamDeleteRoadMaintenance(GamRoadMaintenanceVO deleteVO) throws Exception;

	/**
	 * @param printVo
	 * @return
	 */
	List selectRoadMaintenanceDetailPrint(GamRoadMaintenanceVO printVo) throws Exception;;

}
