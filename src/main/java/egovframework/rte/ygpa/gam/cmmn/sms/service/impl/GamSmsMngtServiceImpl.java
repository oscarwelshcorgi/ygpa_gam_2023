package egovframework.rte.ygpa.gam.cmmn.sms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtService;
import egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtVO;

/**
 * @Class Name : GamSmsMngtServiceImpl.java
 * @Description : SMS 관리 목록 ServiceImpl
 * @Modification Information
 *
 * @author 김종민
 * @since 2014-04-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("gamSmsMngtService")
public class GamSmsMngtServiceImpl extends AbstractServiceImpl
		implements GamSmsMngtService {

    @Resource(name="gamSmsMngtDAO")
    private GamSmsMngtDAO gamSmsMngtDAO;

    protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * SMS 관리 목록 서비스
	 * @param searchVO - SMS 관리 목록 조회 정보 VO
	 * @return SMS 관리 목록
	 * @exception Exception
	 */
    public List selectSmsMngtList(GamSmsMngtVO searchVO) throws Exception {
		return gamSmsMngtDAO.selectSmsMngtList(searchVO);
	}

	/**
	 * SMS 관리 목록 수 서비스
	 * @param searchVO - SMS 관리 목록 조회 정보 VO
	 * @return 매출액 통계 생성 항목
	 * @exception Exception
	 */
	public int selectSmsMngtListTotCnt(GamSmsMngtVO searchVO) throws Exception {
		return gamSmsMngtDAO.selectSmsMngtListTotCnt(searchVO);
	}

	/**
	 * SMS 재전송 요청 서비스
	 * @param createVO - 재전송 데이터 생성 정보가 담긴 VO
	 * @return 
	 * @exception Exception
	 */
    public String smsRetransmit(GamSmsMngtVO createVO) throws Exception {
		return gamSmsMngtDAO.smsRetransmit(createVO);
	}
}
