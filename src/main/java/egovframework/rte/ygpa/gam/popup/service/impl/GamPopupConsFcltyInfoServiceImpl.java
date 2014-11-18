/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.popup.service.GamPopupConsFcltyInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupConsFcltyInfoVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 18.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 18.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupConsFcltyInfoService")
public class GamPopupConsFcltyInfoServiceImpl extends AbstractServiceImpl implements GamPopupConsFcltyInfoService {
	@Resource(name="gamPopupConsFcltyInfoDao")
	private GamPopupConsFcltyInfoDao gamPopupConsFcltyInfoDao;
	
    /**
	 * 시설물 분류 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectConsFcltyInfoList(GamPopupConsFcltyInfoVO searchVO) throws Exception {
    	return gamPopupConsFcltyInfoDao.selectConsFcltyInfoList(searchVO);
    }

    /**
	 * 시설물 분류 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectConsFcltyInfoListTotCnt(GamPopupConsFcltyInfoVO searchVO) throws Exception {
    	return gamPopupConsFcltyInfoDao.selectConsFcltyInfoListTotCnt(searchVO);
    }
	
}
