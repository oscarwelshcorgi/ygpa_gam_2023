package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetPhotoMngtService;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetPhotoVO;

@Service("gamGisAssetPhotoMngtService")
public class GamGisAssetPhotoMngtServiceImpl extends AbstractServiceImpl implements GamGisAssetPhotoMngtService {

    @Resource(name="gamGisAssetPhotoMngtDao")
    private GamGisAssetPhotoMngtDao gamGisAssetPhotoMngtDao;


	@Override
	public String insertAssetPhoto(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamGisAssetPhotoMngtDao.insertAssetPhoto(vo);
	}

	@Override
	public void updateAssetPhoto(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisAssetPhotoMngtDao.updateAssetPhoto(vo);
	}

	@Override
	public void deleteAssetPhoto(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisAssetPhotoMngtDao.deleteAssetPhoto(vo);
	}

	@Override
	public EgovMap selectAssetPhoto(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamGisAssetPhotoMngtDao.selectAssetPhoto(vo);
	}

	@Override
	public void updateAssetPhotoGis(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisAssetPhotoMngtDao.updateAssetPhoto(vo);
	}

	@Override
	public List selectAssetPhotoList(GamGisAssetPhotoVO searchOpt) {
		// TODO Auto-generated method stub
		return gamGisAssetPhotoMngtDao.selectGamAssetPhotoList(searchOpt);
	}

	@Override
	public int selectAssetPhotoListTotCnt(GamGisAssetPhotoVO searchOpt) {
		// TODO Auto-generated method stub
		return gamGisAssetPhotoMngtDao.selectGamAssetPhotoListTotCnt(searchOpt);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.service.GamErpGisAssetCodeMngtService#mergeErpGisAssetPhotoMngt(java.util.Map)
	 */
	@Override
	public List mergeAssetPhotoMngt(Map mergeMap) throws Exception {
		// TODO Auto-generated method stub

        ArrayList arraylistCU = (ArrayList)mergeMap.get("CU");
        HashMap[] hmCU = (HashMap[])arraylistCU.toArray(new HashMap[arraylistCU.size()]);
        Map result;
        Integer photoSeq=1;

		if(hmCU.length>0) photoSeq=gamGisAssetPhotoMngtDao.selectGamAssetPhotoMaxSeq(hmCU[0]);
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

		return gamGisAssetPhotoMngtDao.mergeGisAssetPhoto(mergeMap);
	}

}
