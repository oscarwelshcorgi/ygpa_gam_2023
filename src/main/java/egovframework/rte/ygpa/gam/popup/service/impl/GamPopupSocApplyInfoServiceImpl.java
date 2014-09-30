/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocApplyInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocApplyInfoVO;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 29.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupSocApplyInfoService")
public class GamPopupSocApplyInfoServiceImpl extends AbstractServiceImpl implements GamPopupSocApplyInfoService {

	@Resource(name="gamPopupSocApplyInfoDao")
    private GamPopupSocApplyInfoDao gamPopupSocApplyInfoDao;

	/**
	 * 시설 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectSocApplyInfoList(GamPopupSocApplyInfoVO searchVO) throws Exception {
        return gamPopupSocApplyInfoDao.selectSocApplyInfoList(searchVO);
    }

    /**
	 * 시설 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectSocApplyInfoListTotCnt(GamPopupSocApplyInfoVO searchVO) throws Exception {
		return gamPopupSocApplyInfoDao.selectSocApplyInfoListTotCnt(searchVO);
	}
}
