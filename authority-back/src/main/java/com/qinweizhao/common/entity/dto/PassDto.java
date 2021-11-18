package com.qinweizhao.common.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PassDto implements Serializable {

	@NotBlank(message = "旧密码不能为空")
	private String oldPassword;

	@NotBlank(message = "新密码不能为空")
	private String newPassword;
}
