package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.service.GamAssetSttusInqireService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetSttusInqireVO;
import egovframework.rte.ygpa.gam.asset.service.GamErpGisAssetCodeMngtService;
import egovframework.rte.ygpa.gam.code.service.impl.GamGisAssetCodeMngtDao;

/**
 * @Class Name : GamErpGisAssetCodeMngtServiceImpl.java
 * @Description : 자산정보코드관리 서비스 구현
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-04-07
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamErpGisAssetCodeMngtService")

public class GamErpGisAssetCodeMngtServiceImpl  extends AbstractServiceImpl implements GamErpGisAssetCodeMngtService {

	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name="gamErpGisAssetCodeMngtDao")
	GamErpGisAssetCodeMngtDao gamErpGisAssetCodeMngtDao;

	@Resource(name="gamGisAssetCodeMngtDao")
	GamGisAssetCodeMngtDao gamGisAssetCodeMngtDao;


	@Resource(name="gamErpGisAssetPhotoMngtDao")
	GamErpGisAssetPhotoMngtDao gamErpGisAssetPhotoMngtDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.service.GamErpGisAssetCodeMngtService#mergeErpGisAssetCodeMngt(java.util.Map)
	 */
	@Override
	public List mergeErpGisAssetCodeMngt(Map mergeMap) throws Exception {
        ArrayList arraylistCU = (ArrayList)mergeMap.get("CU");
        HashMap[] hmCU = (HashMap[])arraylistCU.toArray(new HashMap[arraylistCU.size()]);
        Map result;

        //수정처리 & 입력처리
        for (int i=0; i<hmCU.length; i++) {
        	if ("I".equals(hmCU[i].get("_updtId"))) {
        		result=gamGisAssetCodeMngtDao.selectGetNewAssetCode(hmCU[i]);
        		hmCU[i].put("gisAssetsCd", result.get("gisAssetsCd"));
        		hmCU[i].put("gisAssetsSubCd", result.get("gisAssetsSubCd"));
            	log.debug("gisAssetscd : "+result.get("gisAssetsCd")+"-"+result.get("gisAssetsSubCd"));
            }
            else {
            	log.debug("unknown RowStatus ["+i+"] : "+hmCU[i].get("_updtId"));
            }
        }

		return gamErpGisAssetCodeMngtDao.mergeGisAssetCode(mergeMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.service.GamErpGisAssetCodeMngtService#mergeErpGisAssetPhotoMngt(java.util.Map)
	 */
	@Override
	public List mergeErpGisAssetPhotoMngt(Map mergeMap) throws Exception {

        ArrayList arraylistCU = (ArrayList)mergeMap.get("CU");
        HashMap[] hmCU = (HashMap[])arraylistCU.toArray(new HashMap[arraylistCU.size()]);
        Map result;
        Integer photoSeq=1;

		if(hmCU.length>0) photoSeq=gamErpGisAssetPhotoMngtDao.selectGamAssetPhotoMaxSeq(hmCU[0]);
        //수정처리 & 입력처리
        for (int i=0; i<hmCU.length; i++) {
        	if ("I".equals(hmCU[i].get("_updtId"))) {
            	log.debug("#photoeq : "+photoSeq.toString());
            	hmCU[i].put("photoSeq", photoSeq++);
            }
        	else if("U".equals(hmCU[i].get("_updtId"))){
        	}
            else {
            	log.debug("unknown RowStatus ["+i+"] : "+hmCU[i].get("_updtId"));
            }
        }
		// TODO Auto-generated method stub
		return gamErpGisAssetPhotoMngtDao.mergeGisAssetPhoto(mergeMap);
	}

}
