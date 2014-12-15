/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocVsslCdService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocVsslCdVO;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 26.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 26.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamPopupSocVsslCdService")
public class GamPopupSocVsslCdServiceImpl extends AbstractServiceImpl implements GamPopupSocVsslCdService {

	@Resource(name="gamPopupSocVsslCdDao")
    private GamPopupSocVsslCdDao gamPopupSocVsslCdDao;

	/**
	 * 선박정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List<?> selectSocVsslCdList(GamPopupSocVsslCdVO searchVO) throws Exception {
        return gamPopupSocVsslCdDao.selectSocVsslCdList(searchVO);
    }

    /**
	 * 선박정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectSocVsslCdTotCnt(GamPopupSocVsslCdVO searchVO) throws Exception {
		return gamPopupSocVsslCdDao.selectSocVsslCdTotCnt(searchVO);
	}

}
