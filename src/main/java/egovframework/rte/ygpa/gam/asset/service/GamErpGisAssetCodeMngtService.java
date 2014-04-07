package egovframework.rte.ygpa.gam.asset.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;

/**
 * @Class Name : GamErpGisAssetCodeMngtService.java
 * @Description : 자산정보 관리 서비스
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-04-07
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamErpGisAssetCodeMngtService {

	List mergeErpGisAssetCodeMngt(Map mergeMap) throws Exception;

	List mergeErpGisAssetPhotoMngt(Map mergeMap) throws Exception;

}
