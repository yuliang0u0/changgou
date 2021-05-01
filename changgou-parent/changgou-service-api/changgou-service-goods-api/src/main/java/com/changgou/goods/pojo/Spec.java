package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 规格实体类
 */
@ApiModel(description = "Spec",value = "Spec")
@Table(name="tb_spec")
public class Spec implements Serializable{

	@ApiModelProperty(value = "ID",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id; // ID

	@ApiModelProperty(value = "名称",required = false)
    @Column(name = "name")
	private String name; // 名称

	@ApiModelProperty(value = "规格选项",required = false)
    @Column(name = "options")
	private String options; // 规格选项

	@ApiModelProperty(value = "排序",required = false)
    @Column(name = "seq")
	private Integer seq; // 排序

	@ApiModelProperty(value = "模板ID",required = false)
    @Column(name = "template_id")
	private Integer templateId; // 模板ID

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

}
