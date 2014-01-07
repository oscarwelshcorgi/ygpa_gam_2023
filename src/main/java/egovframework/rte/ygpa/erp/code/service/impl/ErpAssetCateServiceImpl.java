package egovframework.rte.ygpa.erp.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCateService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCateDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCateVO;
import egovframework.rte.ygpa.erp.code.service.impl.ErpAssetCateDAO;

/**
 * @Class Name : ErpAssetCateServiceImpl.java
 * @Description : ErpAssetCate Business Implement class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("erpAssetCateService")
public class ErpAssetCateServiceImpl extends AbstractServiceImpl implements
        ErpAssetCateService {

    @Resource(name="erpAssetCateDAO")
    private ErpAssetCateDAO erpAssetCateDAO;
    
    /** ID Generation */
    //@Resource(name="{egovErpAssetCateIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * ERP_ASSET_CATE을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetCateVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpAssetCate(ErpAssetCateVO vo) throws Exception {
    	log.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	log.debug(vo.toString());
    	
    	erpAssetCateDAO.insertErpAssetCate(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * ERP_ASSET_CATE을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetCateVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpAssetCate(ErpAssetCateVO vo) throws Exception {
        erpAssetCateDAO.updateErpAssetCate(vo);
    }

    /**
	 * ERP_ASSET_CATE을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetCateVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteErpAssetCate(ErpAssetCateVO vo) throws Exception {
        erpAssetCateDAO.deleteErpAssetCate(vo);
    }

    /**
	 * ERP_ASSET_CATE을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetCateVO
	 * @return 조회한 ERP_ASSET_CATE
	 * @exception Exception
	 */
    public ErpAssetCateVO selectErpAssetCate(ErpAssetCateVO vo) throws Exception {
        ErpAssetCateVO resultVO = erpAssetCateDAO.selectErpAssetCate(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * ERP_ASSET_CATE 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_CATE 목록
	 * @exception Exception
	 */
    public List selectErpAssetCateList(ErpAssetCateDefaultVO searchVO) throws Exception {
        return erpAssetCateDAO.selectErpAssetCateList(searchVO);
    }

    /**
	 * ERP_ASSET_CATE 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_CATE 총 갯수
	 * @exception
	 */
    public int selectErpAssetCateListTotCnt(ErpAssetCateDefaultVO searchVO) {
		return erpAssetCateDAO.selectErpAssetCateListTotCnt(searchVO);
	}
    
}
