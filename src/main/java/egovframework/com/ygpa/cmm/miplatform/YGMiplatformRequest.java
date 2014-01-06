package egovframework.com.ygpa.cmm.miplatform;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tobesoft.platform.PlatformRequest;
import com.tobesoft.platform.data.Dataset;
import com.tobesoft.platform.data.DatasetList;
import com.tobesoft.platform.data.VariableList;

import egovframework.com.utl.fcc.service.EgovStringUtil;

public class YGMiplatformRequest {

    private static final Logger logger = Logger.getLogger(YGMiplatformRequest.class);

    protected VariableList miVariableList;
    protected DatasetList miDatasetList;
    protected Map<String, String> voMap;
    protected Map mapVariableList;

    
    public YGMiplatformRequest(HttpServletRequest request) throws IOException {
    	
        voMap = new HashMap<String, String>();
        PlatformRequest platformRequest = null;
        platformRequest = new PlatformRequest(request, PlatformRequest.CHARSET_UTF8);
        platformRequest.receiveData();

        miDatasetList = platformRequest.getDatasetList();
        miVariableList = platformRequest.getVariableList();
//        miVariableList.printVariables();
        setMapVariableList(miVariableList);

        Dataset ds_voInfo = miDatasetList.get("ds_voInfo");

        if (ds_voInfo != null) {
            // ds_voInfo.printDataset();
            // if ( ds_voInfo.getRowCount() > 0 )
            // if ( ds_voInfo.getColumnAsString(0,"voClass") != null )

            for (int rows = 0; rows < ds_voInfo.getRowCount(); rows++) {
                voMap.put(ds_voInfo.getColumnAsString(rows, "InputDs"), ds_voInfo.getColumnAsString(rows, "voClass"));
            }
        }

        // if (logger.isDebugEnabled()) {
        //            logger.debug("ISMiPlatformRequest(HttpServletRequest) - end"); //$NON-NLS-1$
        // }
        
	}
    
    /**
     * VariableList Map 생성
     * 
     * @param vList
     */
    public void setMapVariableList(VariableList vList) {
        this.mapVariableList = new HashMap<String, String>();

        for (int i = 0; i < vList.size(); i++) {
            this.mapVariableList.put(vList.get(i).getId(), vList.getValueAsString(vList.get(i).getId()));
        }
    }

    /**
     * VariableList Map 리턴
     * 
     * @return
     */
    public Map getMapVariableList() {
        return mapVariableList;
    }

    /**
     * Dataset 리턴
     * @param dname
     * @return
     */
    public Dataset getDataset(String dname) {
        return miDatasetList.get(dname);
    }

    /**
     * DatasetList 리턴
     * @return
     */
    public DatasetList getDatasetList() {
        return miDatasetList;
    }

    /**
     * VariableList 리턴
     * @return
     */
    public VariableList getVariableList() {
        return miVariableList;
    }

    /**
     * Vo Map 리턴
     * @return
     */
    public Map getVoMap() {
        return voMap;
    }

    /**
     * 이름에 해당하는 데이타 셋을 insert, update Status 에 해당하는 List<Object> 로 변환하여 리턴
     * 
     * @param dsname
     * @return
     * @throws Exception
     */
    public List getDataSetOnInsertUpdateList(String dsname) throws Exception {
        List list = new ArrayList<Object>();
        Dataset ds = miDatasetList.get(dsname);
        String voClass = (String) voMap.get(dsname);
        if (!("").equals(EgovStringUtil.nullConvert(voClass))) {
            if (ds != null) {
                for (int rows = 0; rows < ds.getRowCount(); rows++) {
                    Class<?> cls = Class.forName(voClass);
                    Object vo = cls.newInstance();
    
                    if ("insert".equals(ds.getRowStatus(rows)) || "update".equals(ds.getRowStatus(rows))) {
                        invokeMethod(vo, ds,cls, rows);
    //                    try {
    //                        Method mstatus = cls.getMethod("setRowStatus", new String().getClass());
    //                        mstatus.invoke(vo, ds.getRowStatus(rows));
    //                    } catch (Exception e) {
    //                        // No JOB pdk ship
    //                        logger.warn("getDataSetOnInsertUpdateList(String) - exception ignored", e); //$NON-NLS-1$
    //                    }
    //                    for (int cols = 0; cols < ds.getColumnCount(); cols++) {
    //                        String memberName = ds.getColumnId(cols);
    //                        Object memberValue = ds.getColumnAsObject(rows, cols);
    //
    //                        String methodeName = "set" + memberName.substring(0, 1).toUpperCase()
    //                                + memberName.substring(1, memberName.length());
    //
    //                        invokeMethod(vo, methodeName, memberValue);
    //                    }
                        list.add(vo);
                    }
                }
            }
        }
        return list;
    }

    
    // /////////////////////////////LIST////////////////////////////////////
    /**
     * 이름에 해당하는 데이타 셋을 해당 Status 값에 해당 하는 List<Object> 로 변환하여 리턴
     * 
     * @param dsname
     * @param status
     * @return
     * @throws Exception
     */
    public List getDataSetByStatusList(String dsname, String status) throws Exception {
        
        String[] statuses  = new String[1];
        statuses[0] = status;
               
        return getDataSetByStatusList(dsname, statuses);
        
//        List list = new ArrayList<Object>();
//        Dataset ds = miDatasetList.get(dsname);
//        String voClass = (String) voMap.get(dsname);
//        if (!("").equals(EgovStringUtil.nullConvert(voClass))) {
//            if ("delete".equals(status)) {
//                List deleteList = getDataSetOnDeleteList(dsname);
//                list.addAll(deleteList);
//            } else {
//                if (ds != null) {
//                    for (int rows = 0; rows < ds.getRowCount(); rows++) {
//    
//                        Class<?> cls = Class.forName(voClass);
//                        Object vo = cls.newInstance();
//    
//                        if (status.equals(ds.getRowStatus(rows))) {
//                            invokeMethods(vo, ds,cls, rows);
////                            
////                            try {
////                                Method mstatus = cls.getMethod("setRowStatus", new String().getClass());
////                                mstatus.invoke(vo, ds.getRowStatus(rows));
////                            } catch (Exception e) {
////                                // No JOB pdk ship
////                                logger.warn("getDataSetByStatusList(String) - exception ignored", e); //$NON-NLS-1$
////                            }
////                            for (int cols = 0; cols < ds.getColumnCount(); cols++) {
////                                String memberName = ds.getColumnId(cols);
////                                Object memberValue = ds.getColumnAsObject(rows, cols);
////    
////                                String methodeName = "set" + memberName.substring(0, 1).toUpperCase()
////                                        + memberName.substring(1, memberName.length());
////                                invokeMethod(vo, methodeName, memberValue);
////                            }
//                            list.add(vo);
//                        }
//                    }
//                }
//            }
//        }
//        return list;
    }
    public List getDataSetByStatusList(String dsname, String[] status) throws Exception {
        List list = new ArrayList<Object>();
        Dataset ds = miDatasetList.get(dsname);
        String voClass = (String) voMap.get(dsname);
        if (!("").equals(EgovStringUtil.nullConvert(voClass))) {
            if ("delete".equals(status)) {
                List deleteList = getDataSetOnDeleteList(dsname);
                list.addAll(deleteList);
            } else {
                if (ds != null) {
                    for (int rows = 0; rows < ds.getRowCount(); rows++) {
    
                        Class<?> cls = Class.forName(voClass);
                        Object vo = cls.newInstance();
    
                        boolean isStatus = false;
                        for(int i=0; i<status.length; i++){
                            if (status[i].equals(ds.getRowStatus(rows))) {
                                isStatus = true;
                                break;
                            }
                        }
                        
                        if (isStatus) {
                            invokeMethod(vo, ds,cls, rows);

                            list.add(vo);
                        }
                    }
                }
            }
        }
        return list;
    }
        
    /**
     * 이름에 해당하는 데이타 셋을 Status 에 상관없이 모두 List<Object> 로 변환하여 리턴
     * 
     * @param dsname
     * @return
     * @throws Exception
     */
    public List getDataSetALLList(String dsname) throws Exception {
        List list = new ArrayList<Object>();
        // Dataset ds = miDatasetList.get(dsname);

        // for (int rows = 0; rows < ds.getRowCount(); rows++) {
        List rowsList = getDataSetOnRowList(dsname);
        list.addAll(rowsList);
        // }

        // for (int drows = 0; drows < ds.getDeleteRowCount(); drows++) {

        List deleteList = getDataSetOnDeleteList(dsname);
        list.addAll(deleteList);
        // }
        return list;
    }

    /**
     * 이름에 해당하는 삭제 버퍼 데이타 셋을 List<Object> 로 변환하여 리턴
     * 
     * @param dsname
     * @return
     * @throws Exception
     */
    public List getDataSetOnDeleteList(String dsname) throws Exception {
        List list = new ArrayList<Object>();
        Dataset ds = miDatasetList.get(dsname);
        String voClass = (String) voMap.get(dsname);
        if (!("").equals(EgovStringUtil.nullConvert(voClass))) {
            if (ds != null) {
                //System.out.print("-------------->>>>>>>>>>>>>>"+ds.getDeleteRowCount());
                for (int drows = 0; drows < ds.getDeleteRowCount(); drows++) {
    
                    Class<?> cls = Class.forName(voClass);
                    Object vo = cls.newInstance();
                    
                    invokeMethodsOnDelete(vo, ds, cls, drows);
                    
    //                try {
    //                    Method mstatus = cls.getMethod("setRowStatus", new String().getClass());
    //                    mstatus.invoke(vo, ds.getRowStatus(drows)); // delete status
    //                    // 가 없다면
    //                    // "delete"로
    //                    // 하드코딩 해야 함.
    //                } catch (Exception e) {
    //                    logger.warn("getDataSetOnDeleteList(String) - exception ignored", e); //$NON-NLS-1$
    //
    //                    // No JOB pdk ship
    //                }
    //                for (int cols = 0; cols < ds.getColumnCount(); cols++) {
    //                    String memberName = ds.getColumnId(cols);
    //                    // String memberValue = ds.getColumnAsString(drows, cols);
    //                    Object memberValue = ds.getDeleteColumn(drows, ds.getColumnId(cols)).getObject();
    //
    //                    String methodeName = "set" + memberName.substring(0, 1).toUpperCase()
    //                            + memberName.substring(1, memberName.length());
    //
    //                    invokeMethod(vo, methodeName, memberValue);
    //                }
                    list.add(vo);
                }
            }
        }
        return list;
    }

    
    /**
     * 이름에 해당하는 데이타 셋을 List<Object> 로 변환하여 리턴
     * 
     * @param dsname
     * @return
     * @throws Exception
     */
    public List getDataSetOnRowList(String dsname) throws Exception {
        List list = new ArrayList<Object>();
        Dataset ds = miDatasetList.get(dsname);
        String voClass = (String) voMap.get(dsname);
        if (!("").equals(EgovStringUtil.nullConvert(voClass))) {
            if (ds != null) {
                logger.debug("ds.getRowCount() = "+ ds.getRowCount());
                for (int rows = 0; rows < ds.getRowCount(); rows++) {
                    Class<?> cls = Class.forName(voClass);
                    Object vo = cls.newInstance();
                    invokeMethod(vo, ds,cls, rows);
    //                try {
    //                    Method mstatus = cls.getMethod("setRowStatus", new String().getClass());
    //                    mstatus.invoke(vo, ds.getRowStatus(rows));
    //                } catch (Exception e) {
    //                    // No JOB pdk ship
    //                    logger.warn("getDataSetOnRowList(String) - exception ignored", e); //$NON-NLS-1$
    //                }
    //                for (int cols = 0; cols < ds.getColumnCount(); cols++) {
    //                    String memberName = ds.getColumnId(cols);
    //                    Object memberValue = ds.getColumnAsObject(rows, cols);
    //
    //                    String methodeName = "set" + memberName.substring(0, 1).toUpperCase()
    //                            + memberName.substring(1, memberName.length());
    //                    invokeMethod(vo, methodeName, memberValue);
    //                }
                    list.add(vo);
                }
            }
        }
        return list;
    }


    
//    public Object getClassToObject(Class<?> cls){
//        
//        
//    }
    
    // /////////////////////////////ONE(VO)//////////////////////////////////
    /**
     * 이름에 해당하는 데이타 셋을 Object 로 변환하여 리턴
     * 
     * @param dsname
     * @return
     * @throws Exception
     */
    public Object getDataSetOnRow(String dsname) throws Exception {
         if (logger.isDebugEnabled()) {
                    logger.debug("getDataSetOnRow(String) - start"); //$NON-NLS-1$
         }
        Dataset ds = miDatasetList.get(dsname);
        String voClass = (String) voMap.get(dsname);
         if (logger.isDebugEnabled()) {
                    logger.debug("voClass[" + voClass + "]"); //$NON-NLS-1$
         }
        Object vo = null;
        if (!("").equals(EgovStringUtil.nullConvert(voClass))) {
            Class<?> cls = Class.forName(voClass);

            vo = cls.newInstance();
             
            //List list = new ArrayList<Object>();
            
            if (ds != null) {
                // ds.printDataset();
                
                invokeMethods(vo, ds, cls);
                
//                try {
//                    Method mstatus = cls.getMethod("setRowStatus", new String().getClass());
//                    mstatus.invoke(vo, ds.getRowStatus(0));
//                } catch (Exception e) {
//                    logger.warn("getDataSetOnRow(String) - exception ignored", e.toString()); //$NON-NLS-1$
//                    // NO JOB pdk ship
//                }
//                for (int cols = 0; cols < ds.getColumnCount(); cols++) {
//                    String memberName = ds.getColumnId(cols);
//                    Object memberValue = ds.getColumnAsObject(0, cols);
//
//                    String methodeName = "set" + memberName.substring(0, 1).toUpperCase()
//                            + memberName.substring(1, memberName.length());
//
//                    invokeMethod(vo, methodeName, memberValue);
//
//                }
            }
        }
        // if (logger.isDebugEnabled()) {
        //            logger.debug("getDataSetOnRow(String) - end"); //$NON-NLS-1$
        // }
        return vo;
    }

    
    public void invokeMethods(Object vo, Dataset ds, Class<?> cls) throws Exception {
        invokeMethod(vo, ds, cls, 0);
    }
    public void invokeMethodsOnDelete(Object vo, Dataset ds, Class<?> cls, int drows) throws Exception {
        invokeMethod(vo, ds, cls, drows, true);
    }   
    public void invokeMethod(Object vo, Dataset ds, Class<?> cls, int rows) throws Exception {
        invokeMethod(vo, ds, cls, rows, false);
    }
    public void invokeMethod(Object vo, Dataset ds, Class<?> cls, int rows, boolean idDelete) throws Exception {
//        ds.printDataset();
        if ( "java.util.HashMap".equals(cls.getName()) ){
            logger.debug("input class hash map : "+ ds.getRowStatus(rows));
            
            ((HashMap) vo).put("setRowStatus", ds.getRowStatus(rows));
            for (int cols = 0; cols < ds.getColumnCount(); cols++) {
                String memberName = ds.getColumnId(cols);
                Object memberValue;
                if (idDelete) memberValue = ds.getDeleteColumn(rows, ds.getColumnId(cols)).getObject();
                else {
                    if ("BINARY".equals(ds.getColumn(rows, cols).getTypeName()) ){
                        memberValue = ds.getColumnAsByteArray(rows, cols);
                    }else {
                        memberValue = ds.getColumnAsObject(rows, cols);
                    }
                }
//                if (logger.isDebugEnabled()) {
//                    logger.debug("memberName=[" + memberName+"]"); //$NON-NLS-1$
//                    logger.debug("memberValue=[" + memberValue+"]"); //$NON-NLS-1$
//                }
                
//                System.out.println(ds.getColumn(rows, cols).getTypeName());
                
                
                
                ((HashMap) vo).put(memberName, memberValue);
            }
        }else{
            try {
                Method mstatus = cls.getMethod("setRowStatus", new String().getClass());
                mstatus.invoke(vo, ds.getRowStatus(rows));
            } catch (NoSuchMethodException ne){ 
                logger.warn("getDataSetOnRowList(String) - exception ignored", ne.toString()); //$NON-NLS-1$
            } catch (Exception e) {
                logger.warn("getDataSetOnRowList(String) - exception ignored", e); //$NON-NLS-1$
            }
            for (int cols = 0; cols < ds.getColumnCount(); cols++) {
                String memberName = ds.getColumnId(cols);
                Object memberValue;
                if (idDelete) memberValue = ds.getDeleteColumn(rows, ds.getColumnId(cols)).getObject();
                else memberValue = ds.getColumnAsObject(rows, cols);
//                 if (logger.isDebugEnabled()) {
//                     logger.debug("memberName=[" + memberName+"]"); //$NON-NLS-1$
//                     logger.debug("memberValue=[" + memberValue+"]"); //$NON-NLS-1$
//                 }
                String methodeName = "set" + memberName.substring(0, 1).toUpperCase()
                        + memberName.substring(1, memberName.length());
                invokeMethod(vo, methodeName, memberValue);
            }
        }
        

    }    
    /**
     * Object 에 이름에 해당하는 멤버변수에 Object Type 에 따라 값을 Invoke
     * 
     * @param vo
     * @param methodeName
     * @param memberValue
     * @throws Exception
     */
    public void invokeMethod(Object vo, String methodeName, Object memberValue) throws Exception {
        // if (logger.isDebugEnabled()) {
        // logger.debug("getDataSetByStatusList(String, String) : methodeName="
        // + methodeName + " The class of " + memberValue);
        //            //logger.debug(" is " + memberValue.getClass().getName()); //$NON-NLS-1$
        //            
        // }

        if (memberValue instanceof java.lang.String) {
            try {
                Method m = vo.getClass().getMethod(methodeName, new String().getClass());
                // m.getParameterTypes()
                m.invoke(vo, memberValue.toString());
            } catch (NoSuchMethodException e) {
                logger.warn("invokeMethod- exception ignored", e.toString()); //$NON-NLS-1$
            }
        } else if (memberValue instanceof java.math.BigDecimal) {
            try {
                Method m = vo.getClass().getMethod(methodeName, ((BigDecimal) memberValue).getClass());
                m.invoke(vo, new BigDecimal(memberValue.toString()));
            } catch (NoSuchMethodException e) {
                logger.warn("invokeMethod- exception ignored", e.toString()); //$NON-NLS-1$
            }
        } else if (memberValue instanceof java.lang.Double) {
            try {
                Method m = vo.getClass().getMethod(methodeName, ((Double) memberValue).getClass());
                m.invoke(vo, new Double(memberValue.toString()));
            } catch (NoSuchMethodException e) {
                logger.warn("invokeMethod- exception ignored", e.toString()); //$NON-NLS-1$
            }
        } else if (memberValue instanceof java.lang.Integer) {
            try {
                Method m = vo.getClass().getMethod(methodeName, int.class);
                m.invoke(vo, ((java.lang.Integer) memberValue).intValue());
            } catch (NoSuchMethodException e) {
                logger.warn("invokeMethod- exception ignored", e.toString()); //$NON-NLS-1$
            }
        } else if (memberValue instanceof java.util.Date) {
            try {
                Method m = vo.getClass().getMethod(methodeName, int.class);
                m.invoke(vo, memberValue.toString());
            } catch (NoSuchMethodException e) {
                logger.warn("invokeMethod- exception ignored", e.toString()); //$NON-NLS-1$
            }
        } else {
            try {
                Method m = vo.getClass().getMethod(methodeName, new String().getClass());
                m.invoke(vo, memberValue);
                //m.invoke(vo, memberValue.toString());
            } catch (NoSuchMethodException e) {
                logger.warn("invokeMethod- exception ignored", e.toString()); //$NON-NLS-1$
            }
        }
    }

}
