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

public interface GamFenderMaintenanceService {

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFenderMaintenanceList(GamFenderMaintenanceVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFenderMaintenanceDetailList(GamFenderMaintenanceVO searchVO) throws Exception;

	/**
	 * @param inputVO
	 */
	void gamInsertFenderMaintenance(Map insertFenderMaintenance, List insertObjList) throws Exception;

	/**
	 * @param inputVO
	 */
	void gamUpdateFenderMaintenance(Map insertFenderMaintenance, List insertObjList) throws Exception;

	/**
	 * @param deleteVO
	 */
	void gamDeleteFenderMaintenance(GamFenderMaintenanceVO deleteVO) throws Exception;

	/**
	 * @param printVo
	 * @return
	 */
	List selectFenderMaintenanceDetailPrint(GamFenderMaintenanceVO printVo) throws Exception;;

}
