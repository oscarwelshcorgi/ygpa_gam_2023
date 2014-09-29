/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocPayCdService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocPayCdVO;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 25.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupSocPayCdService")
public class GamPopupSocPayCdServiceImpl extends AbstractServiceImpl implements GamPopupSocPayCdService {

	@Resource(name="gamPopupSocPayCdDao")
    private GamPopupSocPayCdDao gamPopupSocPayCdDao;

	/**
	 * 요금종류정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectSocPayCdList(GamPopupSocPayCdVO searchVO) throws Exception {
        return gamPopupSocPayCdDao.selectSocPayCdList(searchVO);
    }

    /**
	 * 요금종류정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectSocPayCdTotCnt(GamPopupSocPayCdVO searchVO) throws Exception {
		return gamPopupSocPayCdDao.selectSocPayCdTotCnt(searchVO);
	}
}
