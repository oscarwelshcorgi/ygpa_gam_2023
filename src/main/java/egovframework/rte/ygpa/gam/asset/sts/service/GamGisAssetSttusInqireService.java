/**
 *
 */
package egovframework.rte.ygpa.gam.asset.sts.service;

import java.util.List;
import java.util.Map;


/**
 * GIS자산통계 조회 서비스
 * @author EUNSUNGJ
 * @since 2014. 11. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 24.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamGisAssetSttusInqireService {
    /**
	 * GIS자산통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetSttusAssetList(Map searchVO) throws Exception;

    /**
     * GIS자산통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetSttusAssetListTotCnt(Map searchVO) throws Exception;

    /**
     * GIS자산통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetSttusAssetListTotSum(Map searchVO) throws Exception;

}
