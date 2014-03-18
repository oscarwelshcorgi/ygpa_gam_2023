package egovframework.rte.ygpa.gam.domain.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ygpa.gam.domain.code.service.GisAssetsCdService;
import egovframework.rte.ygpa.gam.domain.code.service.GisAssetsCdDefaultVO;
import egovframework.rte.ygpa.gam.domain.code.service.GisAssetsCdVO;
import egovframework.rte.ygpa.gam.domain.code.service.impl.GisAssetsCdDAO;

/**
 * @Class Name : GisAssetsCdFServiceImpl.java
 * @Description : GisAssetsCdF Business Implement class
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-01-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("gisAssetsCdFService")
public class GisAssetsCdServiceImpl extends AbstractServiceImpl implements
        GisAssetsCdService {

    @Resource(name="gisAssetsCdFDAO")
    private GisAssetsCdDAO gisAssetsCdFDAO;
    
    /** ID Generation */
    //@Resource(name="{egovGisAssetsCdFIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * GIS_ASSETS_CD_F을 등록한다.
	 * @param vo - 등록할 정보가 담긴 GisAssetsCdFVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertGisAssetsCdF(GisAssetsCdVO vo) throws Exception {
    	log.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	log.debug(vo.toString());
    	
    	gisAssetsCdFDAO.insertGisAssetsCdF(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * GIS_ASSETS_CD_F을 수정한다.
	 * @param vo - 수정할 정보가 담긴 GisAssetsCdFVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateGisAssetsCdF(GisAssetsCdVO vo) throws Exception {
        gisAssetsCdFDAO.updateGisAssetsCdF(vo);
    }

    /**
	 * GIS_ASSETS_CD_F을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 GisAssetsCdFVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteGisAssetsCdF(GisAssetsCdVO vo) throws Exception {
        gisAssetsCdFDAO.deleteGisAssetsCdF(vo);
    }

    /**
	 * GIS_ASSETS_CD_F을 조회한다.
	 * @param vo - 조회할 정보가 담긴 GisAssetsCdFVO
	 * @return 조회한 GIS_ASSETS_CD_F
	 * @exception Exception
	 */
    public GisAssetsCdVO selectGisAssetsCdF(GisAssetsCdVO vo) throws Exception {
        GisAssetsCdVO resultVO = gisAssetsCdFDAO.selectGisAssetsCdF(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * GIS_ASSETS_CD_F 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return GIS_ASSETS_CD_F 목록
	 * @exception Exception
	 */
    public List selectGisAssetsCdFList(GisAssetsCdDefaultVO searchVO) throws Exception {
        return gisAssetsCdFDAO.selectGisAssetsCdFList(searchVO);
    }

    /**
	 * GIS_ASSETS_CD_F 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return GIS_ASSETS_CD_F 총 갯수
	 * @exception
	 */
    public int selectGisAssetsCdFListTotCnt(GisAssetsCdDefaultVO searchVO) {
		return gisAssetsCdFDAO.selectGisAssetsCdFListTotCnt(searchVO);
	}
    
}
