package egovframework.rte.ygpa.erp.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetHistService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetHistDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetHistVO;
import egovframework.rte.ygpa.erp.code.service.impl.ErpAssetHistDAO;

/**
 * @Class Name : ErpAssetHistServiceImpl.java
 * @Description : ErpAssetHist Business Implement class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("erpAssetHistService")
public class ErpAssetHistServiceImpl extends AbstractServiceImpl implements
        ErpAssetHistService {

    @Resource(name="erpAssetHistDAO")
    private ErpAssetHistDAO erpAssetHistDAO;
    
    /** ID Generation */
    //@Resource(name="{egovErpAssetHistIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * ERP_ASSET_HIST을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetHistVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpAssetHist(ErpAssetHistVO vo) throws Exception {
    	log.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	log.debug(vo.toString());
    	
    	erpAssetHistDAO.insertErpAssetHist(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * ERP_ASSET_HIST을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetHistVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpAssetHist(ErpAssetHistVO vo) throws Exception {
        erpAssetHistDAO.updateErpAssetHist(vo);
    }

    /**
	 * ERP_ASSET_HIST을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetHistVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteErpAssetHist(ErpAssetHistVO vo) throws Exception {
        erpAssetHistDAO.deleteErpAssetHist(vo);
    }

    /**
	 * ERP_ASSET_HIST을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetHistVO
	 * @return 조회한 ERP_ASSET_HIST
	 * @exception Exception
	 */
    public ErpAssetHistVO selectErpAssetHist(ErpAssetHistVO vo) throws Exception {
        ErpAssetHistVO resultVO = erpAssetHistDAO.selectErpAssetHist(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * ERP_ASSET_HIST 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_HIST 목록
	 * @exception Exception
	 */
    public List selectErpAssetHistList(ErpAssetHistDefaultVO searchVO) throws Exception {
        return erpAssetHistDAO.selectErpAssetHistList(searchVO);
    }

    /**
	 * ERP_ASSET_HIST 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_HIST 총 갯수
	 * @exception
	 */
    public int selectErpAssetHistListTotCnt(ErpAssetHistDefaultVO searchVO) {
		return erpAssetHistDAO.selectErpAssetHistListTotCnt(searchVO);
	}
    
}
