/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadMngGroupVO;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadIncidentMngVO;

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

@Repository("gamRoadIncidentMngDao")
public class GamRoadIncidentMngDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectRoadIncidentMngList(GamRoadMngGroupVO searchVO) {
		// TODO Auto-generated method stub
		return list("GamRoadIncidentMngDao.selectRoadIncidentMngList", searchVO);
	}
}
