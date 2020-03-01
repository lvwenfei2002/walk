package org.walkframework.base.mvc.service.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.walkframework.base.mvc.entity.TdSParam;
import org.walkframework.data.bean.PageData;
import org.walkframework.data.bean.Pagination;
import org.walkframework.data.util.DataMap;
import org.walkframework.data.util.IData;


@Service("commonService")
public class CommonService extends AbstractBaseService {

	/**
	 * 获取静态参数列表
	 * 
	 * @param param
	 * @param pagination
	 * @return
	 * @throws Exception
	 */

	public List<TdSParam> queryStaticList(String typeId, String dataId){
		if(StringUtils.isEmpty(typeId)){
			return null;
		}
		IData<String, Object> param = new DataMap<String, Object>();
		param.put("typeId", typeId);
		param.put("dataId", dataId);
		return dao().selectList("CommonSQL.selectStaticList", param);
	}
	
	/**
	 * 获取静态参数列表
	 * 
	 * @param param
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	
	public List<TdSParam> queryStaticListByLike(String typeId, String dataId){
		if(StringUtils.isEmpty(typeId) || StringUtils.isEmpty(dataId)){
			return null;
		}
		IData<String, Object> param = new DataMap<String, Object>();
		param.put("typeId", typeId);
		param.put("dataId", dataId);
		param.put("like", true);
		return dao().selectList("CommonSQL.selectStaticList", param);
	}

	/**
	 * 从静态表根据ID翻译名称
	 * @param typeId
	 * @param dataId
	 * @return
	 * @throws Exception
	 */
	public String convertCode2Name(String typeId, String dataId){
		String dataName = null;
		if (StringUtils.isNotEmpty(typeId) && StringUtils.isNotEmpty(dataId)) {
			List<TdSParam> list = queryStaticList(typeId, dataId);
			if (list != null && list.size() > 0) {
				dataName = list.get(0).getDataName();
			}
		}
		return dataName;
	}

	/**
	 * 根据传入表、编码、值转换名称
	 * @param tableName
	 * @param colCode
	 * @param colName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String convertCode2Name(String tableName, String colCode, String colName, String value){
		String convertName = null;
		try {
			if (StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(colCode) && StringUtils.isNotEmpty(colName) && StringUtils.isNotEmpty(value)) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("tableName", tableName);
				param.put("colCode", colCode);
				param.put("colName", colName);
				param.put("value", value);
				
				Pagination pagination = new Pagination();
				pagination.setRange(0, 1);
				pagination.setCurrPage(1);
				pagination.setNeedCount(false);
				PageData<Map> pageData = dao().selectList("CommonSQL.selectCodeName", param, pagination);
				List<Map> lst = pageData.getRows();
				if (CollectionUtils.isNotEmpty(lst)) {
					Map obj = lst.get(0);
					if(obj != null) {
						convertName = obj.get("CODE_NAME") == null ? null : obj.get("CODE_NAME").toString();
					}
				}
			}
		} catch (BadSqlGrammarException e) {
			log.error(e.getCause().getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return convertName;
	}
	
}
