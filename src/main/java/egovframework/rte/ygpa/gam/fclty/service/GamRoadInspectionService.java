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

public interface GamRoadInspectionService {

	/**
	 * @param searchVO
	 * @return
	 */
	List selectRoadInspectionList(GamRoadInspectionVO searchVO) throws Exception;

	/**
	 * @param inputVO
	 */
	void gamInsertRoadInspection(Map insertRoadInspection, List roadInspectionListOne, List roadInspectionListTwo, List roadInspectionListThree) throws Exception;

	/**
	 * @param inputVO
	 */
	void gamUpdateRoadInspection(Map insertRoadInspection, List roadInspectionListOne, List roadInspectionListTwo, List roadInspectionListThree) throws Exception;

	/**
	 * @param deleteVO
	 */
	void deleteRoadInspection(GamRoadInspectionVO deleteVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectRoadMngGroupList(GamRoadInspectionVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectRoadInspectionDetailList(GamRoadInspectionVO searchVO) throws Exception;


}
