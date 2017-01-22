package ${etypkg.NAME};
import com.ydtf.oms.common.bizplatform.appengine.Aem_Annotation;
import java.io.Serializable;
<#list fldList as fld><#if fld.DATATYPE == "Clob">import java.sql.Clob;
<#break></#if></#list>  
/**
 * 此文件由应用引擎生成，严禁修改，如重复生成时，该文件将被覆盖。
 * ${ety.NAME} 实体信息 
 * <#if ety.COMM??>${ety.COMM}
	<#else></#if> 实体
 * @author 应用引擎自动生成
 * @date ${nowDate}
 *	
 */
  
public class ${ety.NAME} implements Serializable {
	private static final long serialVersionUID = 1L;
<#list fldList as fld>
<#if fld.COMM??>	//${fld.COMM}
<#else></#if>
<#if fld.PRIMARY_FLAG=='1'>	@Aem_Annotation(isPrimary=true)
<#else></#if>
	private <#if fld.DATATYPE=="String_Clob">String<#else>${fld.DATATYPE}</#if> ${fld.NAME};
</#list>

	//需要转换名称的列，存在多列需要转换时，使用|分割。
	private String needTranslateColumns;
	
	//Get/Set 方法
	<#list fldList as fld>
<#if fld.DATATYPE =="String_Clob" && fld.TRANSIENT_FLAG == "1" && fld.NULL_FLAG == "0">	@Aem_Annotation(isStringClob=true,isTransient=true,isNull=false)
<#elseif fld.DATATYPE !="String_Clob" && fld.TRANSIENT_FLAG == "1" && fld.NULL_FLAG == "0">	@Aem_Annotation(isTransient=true,isNull=false)
<#elseif fld.DATATYPE =="String_Clob" && fld.TRANSIENT_FLAG != "1" && fld.NULL_FLAG == "0">	@Aem_Annotation(isStringClob=true,isNull=false)
<#elseif fld.DATATYPE =="String_Clob" && fld.TRANSIENT_FLAG == "1" && fld.NULL_FLAG != "0">	@Aem_Annotation(isStringClob=true,isTransient=true)
<#elseif fld.DATATYPE !="String_Clob" && fld.TRANSIENT_FLAG != "1" && fld.NULL_FLAG == "0">	@Aem_Annotation(isNull=false)
<#elseif fld.DATATYPE !="String_Clob" && fld.TRANSIENT_FLAG == "1" && fld.NULL_FLAG != "0">	@Aem_Annotation(isTransient=true)
<#elseif fld.DATATYPE =="String_Clob" && fld.TRANSIENT_FLAG != "1" && fld.NULL_FLAG != "0">	@Aem_Annotation(isStringClob=true)
<#else></#if>
	public  <#if fld.DATATYPE=="String_Clob">String<#else>${fld.DATATYPE}</#if> get${fld.NAME?cap_first}() {
		return ${fld.NAME};
	} 
	
<#if fld.DATATYPE =="String_Clob" && fld.TRANSIENT_FLAG == "1" && fld.NULL_FLAG == "0">	@Aem_Annotation(isStringClob=true,isTransient=true,isNull=false)
<#elseif fld.DATATYPE !="String_Clob" && fld.TRANSIENT_FLAG == "1" && fld.NULL_FLAG == "0">	@Aem_Annotation(isTransient=true,isNull=false)
<#elseif fld.DATATYPE =="String_Clob" && fld.TRANSIENT_FLAG != "1" && fld.NULL_FLAG == "0">	@Aem_Annotation(isStringClob=true,isNull=false)
<#elseif fld.DATATYPE =="String_Clob" && fld.TRANSIENT_FLAG == "1" && fld.NULL_FLAG != "0">	@Aem_Annotation(isStringClob=true,isTransient=true)
<#elseif fld.DATATYPE !="String_Clob" && fld.TRANSIENT_FLAG != "1" && fld.NULL_FLAG == "0">	@Aem_Annotation(isNull=false)
<#elseif fld.DATATYPE !="String_Clob" && fld.TRANSIENT_FLAG == "1" && fld.NULL_FLAG != "0">	@Aem_Annotation(isTransient=true)
<#elseif fld.DATATYPE =="String_Clob" && fld.TRANSIENT_FLAG != "1" && fld.NULL_FLAG != "0">	@Aem_Annotation(isStringClob=true)
<#else></#if>
	public void set${fld.NAME?cap_first} ( <#if fld.DATATYPE=="String_Clob">String<#else>${fld.DATATYPE}</#if> _${fld.NAME?uncap_first} ) {
		${fld.NAME} = _${fld.NAME?uncap_first};
	}
	
	</#list>
	
	public void resetClass(){
<#list fldList as fld>
		${fld.NAME} = null;
</#list>
		needTranslateColumns = null;
	}
	
	@Aem_Annotation(isTransient=true)
	public String getNeedTranslateColumns() {
		return needTranslateColumns;
	}
	@Aem_Annotation(isTransient=true)
	public void setNeedTranslateColumns(String needTranslateColumns) {
		this.needTranslateColumns = needTranslateColumns;
	}

}