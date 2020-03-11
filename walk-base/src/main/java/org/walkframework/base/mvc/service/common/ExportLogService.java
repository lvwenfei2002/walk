package org.walkframework.base.mvc.service.common;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.walkframework.base.mvc.entity.TlMExportlog;
import org.walkframework.data.bean.PageData;
import org.walkframework.data.bean.Pagination;
import org.walkframework.data.util.IData;

/**
 * 导出日志服务
 *
 */
@Service("exportLogService")
public class ExportLogService extends AbstractBaseService {

	/**
	 * 查询导出日志列表
	 * 
	 * @param param
	 * @param pagination
	 * @return
	 */
	public PageData<TlMExportlog> queryExportList(IData<String, Object> param, Pagination pagination) {
		if(StringUtils.isNotEmpty(param.getString("beginDate"))) {
			param.put("beginDate", dao().getDialect().getToDate(param.getString("beginDate")));
		}
		
		if(StringUtils.isNotEmpty(param.getString("endDate"))) {
			Date date = addDay(param.getDate("endDate", "yyyy-MM-dd"), 1);
			param.put("endDate", dao().getDialect().getToDate(common.decodeTimestamp("yyyy-MM-dd", date)));
		}

		return dao().selectList("CommonSQL.queryExportList", param, pagination);

	}

	/**
	 * 获取导出文件信息
	 * 
	 * @param exportId
	 * @return
	 */
	public TlMExportlog queryExportInfo(String exportId) {
		TlMExportlog export = new TlMExportlog();
		export.setLogId(exportId).asCondition();
		return dao().selectOne(export);
	}

	/**
	 * 日期加天数
	 * 
	 * @param date
	 * @return
	 */
	private Date addDay(Date date, int n) {
		try {
			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			cd.add(Calendar.DATE, n);// 增加一天
			return cd.getTime();
		} catch (Exception e) {
			return null;
		}
	}
}
