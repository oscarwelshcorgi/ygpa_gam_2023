package egovframework.rte.ygpa.gam.code.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface GamGisAssetPhotoMngtService {

	String insertAssetPhoto(Map vo) throws Exception;

    void updateAssetPhoto(Map vo) throws Exception;

    void deleteAssetPhoto(Map vo) throws Exception;

    EgovMap selectAssetPhoto(Map vo) throws Exception;

    void updateAssetPhotoGis(Map vo) throws Exception;

	List selectAssetPhotoList(GamGisAssetPhotoVO searchOpt);

	int selectAssetPhotoListTotCnt(GamGisAssetPhotoVO searchOpt);

	List mergeAssetPhotoMngt(Map mergeMap) throws Exception;

}
