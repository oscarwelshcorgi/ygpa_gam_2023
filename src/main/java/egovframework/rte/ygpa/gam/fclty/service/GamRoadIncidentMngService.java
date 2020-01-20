/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;

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

public interface GamRoadIncidentMngService {
	/**
	 * @param searchVO
	 * @return
	 */
	List selectRoadIncidentMngList(GamRoadIncidentMngVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectRoadIncidentMngDetailList(GamRoadIncidentMngVO searchVO) throws Exception;



}
