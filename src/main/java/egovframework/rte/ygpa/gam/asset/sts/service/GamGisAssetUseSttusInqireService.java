/**
 *
 */
package egovframework.rte.ygpa.gam.asset.sts.service;

import java.util.List;
import java.util.Map;


/**
 * 자산GIS통계정보 사용현황 조회 서비스
 * @author EUNSUNGJ
 * @since 2014. 11. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 01. 21.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2015 by LFIT  All right reserved.
 * </pre>
 */

public interface GamGisAssetUseSttusInqireService {
    /**
	 * GIS자산통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetUseSttusList(Map searchVO) throws Exception;

    /**
     * GIS자산통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetUseSttusListTotCnt(Map searchVO) throws Exception;

    /**
     * GIS자산통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetUseSttusListTotSum(Map searchVO) throws Exception;

    /**
     * 자산 코드에 대한 감가상각 정보 리스트를 조회 한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectAssetUseSttusInfoByCode(Map searchVO) throws Exception;

    /**
     * 자산코드에 대한 분포 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectAssetUseSttusInfoListByCode(Map searchVO) throws Exception;

}
