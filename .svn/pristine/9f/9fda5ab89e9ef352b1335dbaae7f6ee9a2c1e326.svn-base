package com.jd.project.system.sysorg.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jd.framework.web.domain.BasisEntity;

/**
 * 组织机构表 sysorg
 * 
 * @author xml
 * @date 2018-11-27
 */
public class SysorgEntity extends BasisEntity {
	private static final long serialVersionUID = 1L;

	/** 机构id(系统生成,唯一关键字) */
	private String orgid;
	/** 机构名称 */
	private String orgname;
	/** 机构简称 */
	private String shortname;
	/** 机构描述 */
	private String orgdesc;
	/** 父机构id */
	private String parent;
	/** 机构地址 */
	private String address;
	/** 机构负责人 */
	private String principal;
	/** 联系人 */
	private String linkman;
	/** 电话 */
	private String tel;
	/** 机构类别 */
	private String orgkind;
	/** 机构代码 */
	private String orgcode;
	/** 序号 */
	private Long orderno;
	/** 地区 */
	private String districtid;
	/** 删除标记 */
	private String isdel;
	/** 创建时间 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date crtTime;
	/** 创建人 */
	private Long crtUser;
	/** 更新时间 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date updTime;
	/** 更新人 */
	private Long updUser;
	/** 自己瞎想的机构代码 */
	private String org_code;

	private List<SysorgEntity> children;

	public Date getCrtTime() {
		return crtTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public List<SysorgEntity> getChildren() {
		return children;
	}

	public void setChildren(List<SysorgEntity> children) {
		this.children = children;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getOrgdesc() {
		return orgdesc;
	}

	public void setOrgdesc(String orgdesc) {
		this.orgdesc = orgdesc;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOrgkind() {
		return orgkind;
	}

	public void setOrgkind(String orgkind) {
		this.orgkind = orgkind;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public Long getOrderno() {
		return orderno;
	}

	public void setOrderno(Long orderno) {
		this.orderno = orderno;
	}

	public String getDistrictid() {
		return districtid;
	}

	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public Long getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(Long crtUser) {
		this.crtUser = crtUser;
	}

	public Long getUpdUser() {
		return updUser;
	}

	public void setUpdUser(Long updUser) {
		this.updUser = updUser;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public SysorgEntity(String parent) {
		this.parent = parent;
	}

	public SysorgEntity() {
	}
}
