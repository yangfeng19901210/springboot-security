package com.yf.mapper;

import java.util.List;

import com.yf.entity.Permission;
import org.apache.ibatis.annotations.Select;


public interface PermissionMapper {

	// 查询苏所有权限
	@Select(" select * from sys_permission ")
	List<Permission> findAllPermission();

}
