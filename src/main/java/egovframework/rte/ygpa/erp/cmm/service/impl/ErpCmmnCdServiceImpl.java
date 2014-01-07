package egovframework.rte.ygpa.erp.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdVO;
import egovframework.rte.ygpa.erp.cmm.service.impl.ErpCmmnCdDAO;

/**
 * @Class Name : ErpCmmnCdServiceImpl.java
 * @Description : ErpCmmnCd Business Implement class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("erpCmmnCdService")
public class ErpCmmnCdServiceImpl extends AbstractServiceImpl implements
        ErpCmmnCdService {

    @Resource(name="erpCmmnCdDAO")
    private ErpCmmnCdDAO erpCmmnCdDAO;
    
    /** ID Generation */
    //@Resource(name="{egovErpCmmnCdIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * ERP_CMMN_CD을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpCmmnCdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpCmmnCd(ErpCmmnCdVO vo) throws Exception {
    	log.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	log.debug(vo.toString());
    	
    	erpCmmnCdDAO.insertErpCmmnCd(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * ERP_CMMN_CD을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpCmmnCdVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpCmmnCd(ErpCmmnCdVO vo) throws Exception {
        erpCmmnCdDAO.updateErpCmmnCd(vo);
    }

    /**
	 * ERP_CMMN_CD을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpCmmnCdVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteErpCmmnCd(ErpCmmnCdVO vo) throws Exception {
        erpCmmnCdDAO.deleteErpCmmnCd(vo);
    }

    /**
	 * ERP_CMMN_CD을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpCmmnCdVO
	 * @return 조회한 ERP_CMMN_CD
	 * @exception Exception
	 */
    public ErpCmmnCdVO selectErpCmmnCd(ErpCmmnCdVO vo) throws Exception {
        ErpCmmnCdVO resultVO = erpCmmnCdDAO.selectErpCmmnCd(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * ERP_CMMN_CD 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_CMMN_CD 목록
	 * @exception Exception
	 */
    public List selectErpCmmnCdList(ErpCmmnCdDefaultVO searchVO) throws Exception {
        return erpCmmnCdDAO.selectErpCmmnCdList(searchVO);
    }

    /**
	 * ERP_CMMN_CD 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_CMMN_CD 총 갯수
	 * @exception
	 */
    public int selectErpCmmnCdListTotCnt(ErpCmmnCdDefaultVO searchVO) {
		return erpCmmnCdDAO.selectErpCmmnCdListTotCnt(searchVO);
	}
    
}
