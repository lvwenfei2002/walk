package org.walkframework.activiti.system.process;import java.io.Serializable;  /** * 任务节点配置 * * @author shf675 */public class NodeConfigEntity implements Serializable {		private static final long serialVersionUID = 1L;	/**		 * 流程定义ID	 */		private String procDefId;		/**		 * 流程定义KEY		 */		private String procDefKey;	/**		 * 流程版本号		 */		private String procDevVer;	/**		 * 流程节点定义key		 */		private String nodeKey;		/**		 * 流程节点定义名称	 */		private String nodeName;	/**		 * 流程节点定义对应业务状态		 */		private String nodeStateValue;	/**		 * 页面url		 */		private String pageUrl;		public String getProcDefId() {		return procDefId;	}	public void setProcDefId(String procDefId) {		this.procDefId = procDefId;	}		public String getProcDefKey() {		return procDefKey;	}	public void setProcDefKey(String procDefKey) {		this.procDefKey = procDefKey;	}	public String getProcDevVer() {		return procDevVer;	}	public void setProcDevVer(String procDevVer) {		this.procDevVer = procDevVer;	}	public String getNodeKey() {		return nodeKey;	}	public void setNodeKey(String nodeKey) {		this.nodeKey = nodeKey;	}	public String getPageUrl() {		return pageUrl;	}	public void setPageUrl(String pageUrl) {		this.pageUrl = pageUrl;	}		public String getNodeName() {		return nodeName;	}	public void setNodeName(String nodeName) {		this.nodeName = nodeName;	}		public String getNodeStateValue() {		return nodeStateValue;	}	public void setNodeStateValue(String nodeStateValue) {		this.nodeStateValue = nodeStateValue;	}}