package egovframework.rte.ygpa.gam.port_mis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.port_mis.service.GamFcltyUseSttusInqireService;
import egovframework.rte.ygpa.gam.port_mis.service.GamFcltyUseSttusInqireVO;

/**
 * @Class Name : GamFcltyUseSttusInqireServiceImpl.java
 * @Description : 항만시설사용현황조회(포트미스정보) Implement class
 * @Modification Information
 *   수정일          수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.01.14  domh          최초 생성
  *  2014.04.14  lsl          선석별 사용현황 조회처리 -- 기존 파일은 _처리 백업
  *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamFcltyUseSttusInqireService")
public class GamFcltyUseSttusInqireServiceImpl extends AbstractServiceImpl implements GamFcltyUseSttusInqireService {

	@Resource(name="gamFcltyUseSttusInqireDao")
    private GamFcltyUseSttusInqireDao gamFcltyUseSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectFcltyUseSttusInqireList(GamFcltyUseSttusInqireVO searchVO) throws Exception {
        return gamFcltyUseSttusInqireDao.selectFcltyUseSttusInqireList(searchVO);
    }

    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectFcltyUseSttusInqireListTotCnt(GamFcltyUseSttusInqireVO searchVO) throws Exception {
		return gamFcltyUseSttusInqireDao.selectFcltyUseSttusInqireListTotCnt(searchVO);
	}

    
}
