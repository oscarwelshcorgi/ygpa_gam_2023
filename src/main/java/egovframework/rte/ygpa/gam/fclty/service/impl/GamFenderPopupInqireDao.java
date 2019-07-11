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
* @since 2019. 7. 11.
* @version 1.0
* @see
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일 		 수정자		 수정내용
*  -------		--------	---------------------------
*  2019.7.11.		LFIT		최초 생성
*
* </pre>
*/

@Repository("gamFenderPopupInqireDao")
public class GamFenderPopupInqireDao extends YGPAAbstractDAO {

	/**
	 * @return
	 */
	public Map selectFenderPopupInqire() {
		// TODO Auto-generated method stub
		return (Map)selectByPk("gamFenderPopupInqireDao.selectFenderPopupInqire", null);
	}

}
