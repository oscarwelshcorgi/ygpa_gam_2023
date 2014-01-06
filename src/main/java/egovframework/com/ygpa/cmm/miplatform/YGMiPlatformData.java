package egovframework.com.ygpa.cmm.miplatform;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tobesoft.platform.data.ColumnInfo;
import com.tobesoft.platform.data.Dataset;
import com.tobesoft.platform.data.DatasetList;
import com.tobesoft.platform.data.PlatformData;
import com.tobesoft.platform.data.VariableList;

public class YGMiPlatformData {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(YGMiPlatformData.class);

    protected VariableList miVariableList = new VariableList();
    protected DatasetList miDatasetList = new DatasetList();
    protected PlatformData platformData = new PlatformData();

    public static final String JAVA_LANG_STRING = "java.lang.String";
    public static final String JAVA_MATH_BIGDECIMAL = "java.math.BigDecimal";
    public static final String JAVA_LANG_DOUBLE = "java.lang.Double";
    public static final String JAVA_LANG_INTEGER = "java.lang.Integer";
    public static final String JAVA_INT = "int";
    public static final String JAVA_UTIL_DATE = "java.util.Date";
    public static final String JAVA_ETC = "";

    public YGMiPlatformData() {
	}
    
    /**
     * 생성자
     * @param code
     * @param Msg
     */
    public YGMiPlatformData(String code, String Msg) {
        platformData = new PlatformData(miVariableList, miDatasetList);
        setMiResultMessage(code, Msg);
    }

    public PlatformData getPlatformData() {
        return platformData;
    }

    /**
     * 마이플랫폼 코드, 메세지 추가
     * @param code
     * @param Msg
     */
    public void setMiResultMessage(String code, String Msg) {
        miVariableList.add("ErrorCode", code);
        miVariableList.add("ErrorMsg", Msg);
        
    }

//    public void setMiResultMessage2(String code, String Msg) {
//        miVariableList.addVariable("ErrorCode", new Variant(code));
//        miVariableList.addVariable("ErrorMsg", new Variant(code));
//    }
    
    /**
     * 마이플랫폼 변수 (이름, 값) 추가
     * @param name
     * @param value
     */
    public void setVariableList(String name, String value) {
        miVariableList.add(name, value);
    }

    /**
     * Object 기반 데이타 셋 생성
     * @param dsname
     * @param vo
     * @throws Exception
     */
    public void addDataset(String dsname, Object vo) throws Exception {

        if (vo != null) {

            Class cls = vo.getClass();

            Field[] field = cls.getDeclaredFields();

            Dataset dataset = new Dataset(dsname);

            for (int i = 0; i < field.length; i++) {
                if ("rowStatus".equals(field[i].getName())) continue;
                if ("serialVersionUID".equals(field[i].getName())) continue;
                    
                addColumn(dataset, field[i]);
            }

            int row = dataset.appendRow();
            for (int i = 0; i < field.length; i++) {
                if (!"serialVersionUID".equals(field[i].getName())) {
                    if ("rowStatus".equals(field[i].getName())) continue;
                    
                    String memberName = field[i].getName();
                    String methodeName = "get" + memberName.substring(0, 1).toUpperCase()
                            + memberName.substring(1, memberName.length());
                    Method m = cls.getMethod(methodeName, null);
                    
                    Class<?> retcls  = m.getReturnType();
                    
                    
                    Object memberValue = (Object) m.invoke(vo, null);

                    
                    setColumn(row, dataset, field[i], memberValue);
                    
                    
                }
            }
            
//            if (dataset != null) {
//                dataset.printDataset();
//            }     
            miDatasetList.add(dataset);
       
        }

    }


    /**
     * 데이타셋 추가
     * @param dataset
     * @throws Exception
     */
    public void addDataset(Dataset dataset) throws Exception {
            miDatasetList.add(dataset);
    }

    /**
     * Map 기반 데이타 셋 생성  
     * @param dsname
     * @param list
     * @throws Exception
     */
    public void addDataset(String dsname, Map map) throws Exception {
//        if (logger.isDebugEnabled()) {
//            logger.debug("setDataset(String, List) - start"); //$NON-NLS-1$
//        }

        Dataset dataset = new Dataset(dsname);

        Iterator<String> si = map.keySet().iterator();
        while (si.hasNext()) {
            String key = si.next();

            Object obj = (Object) map.get(key);
            addColumn(dataset, key, obj);

        }

        int row = dataset.appendRow();
        Iterator<String> si2 = map.keySet().iterator();
        while (si2.hasNext()) {
            String key = si2.next();
            Object obj = (Object) map.get(key);
            setColumn(row, dataset, key, obj);
        }
        
//        if (dataset != null) {
//            dataset.printDataset();
//        }       
        miDatasetList.add(dataset);
//        if (logger.isDebugEnabled()) {
//            logger.debug("setDataset(String, List) - end" + "총 카운트 " + dataset.getRowCount()); //$NON-NLS-1$
//        }        
    }
    
    /**
     * List<Map> 기반 데이타 셋 생성  
     * @param dsname
     * @param list
     * @throws Exception
     */
    public void addDataset(String dsname, List<Map> list) throws Exception {
//        if (logger.isDebugEnabled()) {
//            logger.debug("setDataset(String, List) - start"); //$NON-NLS-1$
//        }

        Dataset dataset = new Dataset(dsname);

        if (list != null) {
//            logger.debug("iterator.next()[" + list.iterator()+"]");
            Iterator<Map> iterator = list.iterator();
            // Iterator<Map> dataIterator = list.iterator();
//            logger.debug("list.iterator().getClass()[" + list.iterator().getClass()+"]");
            
            boolean isFirst = true;

            // egovframework.rte.psl.dataaccess.util.EgovMap
            while (iterator.hasNext()) {
                // Header 세팅

                Map<String, Object> record = iterator.next();

                Iterator<String> si = record.keySet().iterator();
                if (isFirst) {

                    while (si.hasNext()) {
                        String key = si.next();
                        // dataset.addColumn(key, ColumnInfo.COLUMN_TYPE_STRING,
                        // (short) 255);
                        /*
                         * ColumnInfo.COLUMN_TYPE_BLOB;
                         * //ColumnInfo.COLUMN_TYPE_CHAR;
                         * ColumnInfo.COLUMN_TYPE_CURRENCY;
                         * ColumnInfo.COLUMN_TYPE_DATE;
                         * ColumnInfo.COLUMN_TYPE_DECIMAL;
                         * //ColumnInfo.COLUMN_TYPE_FILE;
                         * ColumnInfo.COLUMN_TYPE_INT;
                         * //ColumnInfo.COLUMN_TYPE_LONG;
                         * ColumnInfo.COLUMN_TYPE_STRING;
                         * //ColumnInfo.COLUMN_TYPE_UNKNOWN;
                         * //ColumnInfo.COLUMN_TYPE_URL;
                         */
                        
                        //addColumn(Dataset dataset, Field field)
                        
                        Object obj = (Object) record.get(key);
                        addColumn(dataset, key, obj);

                    }
                    isFirst = false;
                }

                // Value 세팅
                int row = dataset.appendRow();
                Iterator<String> si2 = record.keySet().iterator();
                while (si2.hasNext()) {
                    String key = si2.next();
                    Object obj = (Object) record.get(key);
                    setColumn(row, dataset, key, obj);
                }
            }

        }
//        if (dataset != null) {
//            dataset.printDataset();
//        }       
        miDatasetList.add(dataset);
//        if (logger.isDebugEnabled()) {
//            logger.debug("setDataset(String, List) - end" + "총 카운트 " + dataset.getRowCount()); //$NON-NLS-1$
//        }        
    }
 
    /**
     * List<Object> 기반 데이타 셋 생성  
     * @param dsname
     * @param list
     * @throws Exception
     */
    public void addDatasetByListOnVO(String dsname, List<Object> list) throws Exception {
        addDataset2(dsname, list);
    }
    public void addDataset2(String dsname, List<Object> list) throws Exception {
//        if (logger.isDebugEnabled()) {
//            logger.debug("setDataset(String, List) - start"); //$NON-NLS-1$
//        }

        Dataset dataset = new Dataset(dsname);

        if (list != null) {
            boolean isFirst = true;
            
            for(int t=0; t<list.size(); t++){
                Object vo = list.get(t);
                Class cls = vo.getClass();
                Field[] field = cls.getDeclaredFields();
                Field[] field1 = cls.getFields();
                
                Method[] methods = cls.getDeclaredMethods();
                
//                for(int i = 0; i < methods.length; i++){
//                    logger.debug("===>"+ methods[i].getName() );
//
//                }
                
//                for (int i = 0; i < field1.length; i++) {
//                    logger.debug("--"+field1[i].getName());
//                }
                if (isFirst) {
                    for (int i = 0; i < field.length; i++) {
                        if ("rowStatus".equals(field[i].getName())) continue;
                        if ("serialVersionUID".equals(field[i].getName())) continue;
                            
                        addColumn(dataset, field[i]);
                    }
                    isFirst = false;
                }
                
                int row = dataset.appendRow();
                for (int i = 0; i < field.length; i++) {
                    if (!"serialVersionUID".equals(field[i].getName())) {
                        if ("rowStatus".equals(field[i].getName())) continue;
                        
                        String memberName = field[i].getName();
                        
                        String methodeName = "get" + memberName.substring(0, 1).toUpperCase()
                                + memberName.substring(1, memberName.length());
                        Method m = cls.getMethod(methodeName, null);
                        
                        Class<?> retcls  = m.getReturnType();
                        
                        
                        Object memberValue = (Object) m.invoke(vo, null);

                        
                        setColumn(row, dataset, field[i], memberValue);
                        
                        
                    }
                }
            }
        }
//        if (dataset != null) {
//            dataset.printDataset();
//        }       
        miDatasetList.add(dataset);
//        if (logger.isDebugEnabled()) {
//            logger.debug("setDataset(String, List) - end" + "총 카운트 " + dataset.getRowCount()); //$NON-NLS-1$
//        }        
    }
    
    
    /**
     * String[][] 기반 빈 데이타 셋 생성
     * @param dsname
     * @param data
     * @throws Exception
     */
    public void addEmptyDataset(String dsname, String[][] data) throws Exception {
//      if (logger.isDebugEnabled()) {
//          logger.debug("setDataset(String, List) - start"); //$NON-NLS-1$
//      }

      Dataset dataset = new Dataset(dsname);
      
      if ( data != null )
      for(int i=0; i<data.length; i++){
          addColumn(dataset, data[i]);
      }
      
      
//      if (dataset != null) {
//          dataset.printDataset();
//      }       
      miDatasetList.add(dataset);
//      if (logger.isDebugEnabled()) {
//          logger.debug("setDataset(String, List) - end" + "총 카운트 " + dataset.getRowCount()); //$NON-NLS-1$
//      }        
  }
    
    /**
     * Object 기반 빈 데이타 셋 생성
     * @param dsname
     * @param data
     * @throws Exception
     */
    public void addEmptyDataset(String dsname, Object data) throws Exception {
//      if (logger.isDebugEnabled()) {
//          logger.debug("setDataset(String, List) - start"); //$NON-NLS-1$
//      }

      Dataset dataset = new Dataset(dsname);
      
      if ( data != null ){
          Class cls = data.getClass();
          Field[] field = cls.getDeclaredFields();
          for (int i = 0; i < field.length; i++) {
              if ("rowStatus".equals(field[i].getName())) continue;
              if ("serialVersionUID".equals(field[i].getName())) continue;
                  
              addColumn(dataset, field[i]);
          }
      }
      
      
//      if (dataset != null) {
//          dataset.printDataset();
//      }       
      miDatasetList.add(dataset);
//      if (logger.isDebugEnabled()) {
//          logger.debug("setDataset(String, List) - end" + "총 카운트 " + dataset.getRowCount()); //$NON-NLS-1$
//      }        
  }
    /**
     * Field Type 기반 데이타셋의 해당 칼럼에 데이타 추가
     * @param row
     * @param dataset
     * @param field
     * @param memberValue
     * @throws Exception
     */
    public void setColumn(int row, Dataset dataset, Field field, Object memberValue) throws Exception {

//        logger.debug("memberValue" + memberValue);
        if (  "java.lang.String".equals(field.getType().getName()) ) {
            dataset.setColumn(row, field.getName(), memberValue == null ? "" : (String) memberValue);
        } else if ( "java.math.BigDecimal".equals(field.getType().getName()) ) {
            dataset.setColumn(row, field.getName(), memberValue == null ? 0 : Double.valueOf(((java.math.BigDecimal) memberValue).doubleValue()));
        } else if ( "java.lang.Double".equals(field.getType().getName()) ) {
            dataset.setColumn(row, field.getName(), memberValue == null ? 0 : ((java.lang.Double ) memberValue).doubleValue());
        } else if ( "java.lang.Integer".equals(field.getType().getName()) ) {
            dataset.setColumn(row, field.getName(), memberValue == null ? 0 : ((java.lang.Integer) memberValue).intValue());
        } else if ( "int".equals(field.getType().getName()) ) {
            dataset.setColumn(row, field.getName(), memberValue == null ? 0 : ((java.lang.Integer) memberValue).intValue());
        } else if ( "java.util.Date".equals(field.getType().getName()) ) {
            dataset.setColumn(row, field.getName(), memberValue == null ? null : (java.util.Date) memberValue);
        } else if ( "[B".equals(field.getType().getName()) ) {
            dataset.setColumn(row, field.getName(), memberValue == null ? null : (byte[]) memberValue);
        } else{
            dataset.setColumn(row, field.getName(), memberValue == null ? "" : memberValue.toString());
        }
        
    }    

    
    /**
     * String 기반 데이타셋의 해당 칼럼에 데이타 추가
     * @param row
     * @param dataset
     * @param key
     * @param obj
     * @throws Exception
     */
    public void setColumn(int row, Dataset dataset, String key, Object obj) throws Exception {
        
        if (obj instanceof java.lang.String) {
            dataset.setColumn(row, key, obj == null ? "" : (String) obj);
        } else if (obj instanceof java.math.BigDecimal) {
            dataset.setColumn(row, key, obj == null ? 0 : Double.valueOf(((java.math.BigDecimal) obj).doubleValue()));
        } else if (obj instanceof java.lang.Double) {
            dataset.setColumn(row, key, obj == null ? 0 : ((java.lang.Double) obj).doubleValue());
        } else if (obj instanceof java.lang.Integer) {
            dataset.setColumn(row, key, obj == null ? 0 : ((java.lang.Integer) obj).intValue());
        } else if (obj instanceof java.util.Date) {
            dataset.setColumn(row, key, obj == null ? null : (java.util.Date)obj );
        } else if (obj instanceof byte[]) {
//            logger.debug("byte[]");
            dataset.setColumn(row, key, obj == null ? null : (byte[])obj);
        } else {
//            logger.debug("etc");
            dataset.setColumn(row, key, obj == null ? "" : obj.toString());
        }


    }        
    
    /**
     * Field Type 기반 데이타셋의 해당 칼럼 추가
     * @param dataset
     * @param field
     * @throws Exception
     */
    public void addColumn(Dataset dataset, Field field) throws Exception {
//        logger.debug(field.getName() + ":field.getType().getName())" + field.getType().getName());
        if (  "java.lang.String".equals(field.getType().getName()) ) {
            dataset.addColumn(field.getName(), ColumnInfo.COLUMN_TYPE_STRING, (short) 255);
        } else if ( "java.math.BigDecimal".equals(field.getType().getName()) ) {
            dataset.addDecimalColumn(field.getName());
        } else if ( "java.lang.Double".equals(field.getType().getName()) ) {
            dataset.addDecimalColumn(field.getName());
        } else if ( "java.lang.Integer".equals(field.getType().getName()) ) {
            dataset.addIntegerColumn(field.getName());
        } else if ( "int".equals(field.getType().getName()) ) {
            dataset.addIntegerColumn(field.getName());
        } else if ( "java.util.Date".equals(field.getType().getName()) ) {
            dataset.addDateColumn(field.getName());
        } else if ("[B".equals(field.getType().getName()) ) {
            dataset.addBlobColumn(field.getName());
        } else{
            dataset.addColumn(field.getName(), ColumnInfo.COLUMN_TYPE_STRING, (short) 255);
        }
    }    
    

    
    /**
     * Object 기반 데이타셋의 String key 이름으로 칼럼 추가
     * @param dataset
     * @param key
     * @param obj
     * @throws Exception
     */
    public void addColumn(Dataset dataset, String key, Object obj) throws Exception {
//        if (logger.isDebugEnabled()) {
//            logger.debug(obj);
//        }
        if (obj instanceof java.lang.String) {
//            logger.debug("java.lang.String");
            dataset.addColumn(key, ColumnInfo.COLUMN_TYPE_STRING, (short) 255);
        } else if (obj instanceof java.math.BigDecimal) {
//            logger.debug("java.math.BigDecimal");
            dataset.addDecimalColumn(key);
        } else if (obj instanceof java.lang.Double) {
//            logger.debug("java.lang.Integer");
            dataset.addDecimalColumn(key);
        } else if (obj instanceof java.lang.Integer) {
//            logger.debug("java.lang.Integer");
            dataset.addIntegerColumn(key);
        } else if (obj instanceof java.util.Date) {
//            logger.debug("java.util.Date");
            dataset.addDateColumn(key);
        } else if (obj instanceof byte[]) {
//            logger.debug("byte[]");
            dataset.addBlobColumn(key);
        } else {
//            logger.debug("etc");
            dataset.addColumn(key, ColumnInfo.COLUMN_TYPE_STRING, (short) 255);
        }

    }
    
    /**
     * String[] 기반 데이타셋의 [0] 이름으로 칼럼 추가
     * @param dataset
     * @param array
     * @throws Exception
     */
    public void addColumn(Dataset dataset, String[] array) throws Exception {
//      logger.debug("field.getName()" + field.getName());
        if (array != null && array.length > 1) {
            if ("java.lang.String".equals(array[1])) {
                dataset.addColumn(array[0], ColumnInfo.COLUMN_TYPE_STRING, (short) 255);
            } else if ("java.math.BigDecimal".equals(array[1])) {
                dataset.addDecimalColumn(array[0]);
            } else if ("java.lang.Double".equals(array[1])) {
                dataset.addDecimalColumn(array[0]);
            } else if ("java.lang.Integer".equals(array[1])) {
                dataset.addIntegerColumn(array[0]);
            } else if ("int".equals(array[1])) {
                dataset.addIntegerColumn(array[0]);
            } else if ("java.util.Date".equals(array[1])) {
                dataset.addDateColumn(array[0]);
            } else if ("[B".equals(array[1])) {
                dataset.addColumn(array[0], ColumnInfo.COLUMN_TYPE_BLOB, (short) 255);
            } else {
                dataset.addColumn(array[0], ColumnInfo.COLUMN_TYPE_STRING, (short) 255);
            }
            
        }
  }        
    
}
