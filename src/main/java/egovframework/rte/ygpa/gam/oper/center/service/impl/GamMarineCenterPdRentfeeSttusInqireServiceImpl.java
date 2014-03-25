package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterPdRentfeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterPdRentfeeSttusInqireVO;

/**
 * @Class Name : GamMarineCenterPdRentfeeSttusInqireServiceImpl.java
 * @Description : 마린센터기간별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamMarineCenterPdRentfeeSttusInqireService")

public class GamMarineCenterPdRentfeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamMarineCenterPdRentfeeSttusInqireService {

	@Resource(name="gamMarineCenterPdRentfeeSttusInqireDao")
    private GamMarineCenterPdRentfeeSttusInqireDao gamMarineCenterPdRentfeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 마린센터기간별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterPdRentfeeSttusInqireList(GamMarineCenterPdRentfeeSttusInqireVO searchVO) throws Exception {
        return gamMarineCenterPdRentfeeSttusInqireDao.selectMarineCenterPdRentfeeSttusInqireList(searchVO);
    }
    
    /**
   	 * 마린센터기간별사용료현황 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
       public int selectMarineCenterPdRentfeeSttusInqireListTotCnt(GamMarineCenterPdRentfeeSttusInqireVO searchVO) throws Exception {
   		//return gamAssetRentMngtDao.selectAssetRentListTotCnt(searchVO);
    	   return gamMarineCenterPdRentfeeSttusInqireDao.selectMarineCenterPdRentfeeSttusInqireListTotCnt(searchVO);
   	}
    
}
