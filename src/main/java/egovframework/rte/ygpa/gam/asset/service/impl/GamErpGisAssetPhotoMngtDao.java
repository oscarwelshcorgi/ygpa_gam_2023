package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetSttusInqireVO;

/**
 * @Class Name : GamAssetSttusInqireDao.java
 * @Description : 자산정보현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamErpGisAssetPhotoMngtDao")
public class GamErpGisAssetPhotoMngtDao extends YGPAAbstractDAO {

	public Integer selectGamAssetPhotoMaxSeq(Map vo) throws Exception {
    	return (Integer) selectByPk("gamGisAssetPhotoDao.selectGetNewAssetPhotoSeq_S", vo);
    }

	public List mergeGisAssetPhoto(Map map) throws Exception {
		return this.merge(map, "gamGisAssetPhotoDao.insertAssetPhoto_S", "gamGisAssetPhotoDao.updateAssetPhoto_S", "gamGisAssetPhotoDao.deleteAssetPhoto_S");
	}

}
