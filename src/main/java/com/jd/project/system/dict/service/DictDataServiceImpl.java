package com.jd.project.system.dict.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jd.common.support.Convert;
import com.jd.common.utils.security.ShiroUtils;
import com.jd.project.system.dict.domain.DictData;
import com.jd.project.system.dict.domain.DictType;
import com.jd.project.system.dict.mapper.DictDataMapper;

/**
 * 字典 业务层处理
 * 开启了二级缓存，对字典的操作需要在DictDataMapper.xml中操作，否则会出现缓存和数据库不一致问题
 */
@Service("dict")
@Transactional(rollbackFor = Exception.class)
public class DictDataServiceImpl implements IDictDataService {
    @Autowired
    private DictDataMapper dictDataMapper;

    @Autowired
    private IDictTypeService dictTypeService;
    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<DictData> selectDictDataList(DictData dictData) {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<DictData> selectDictDataByType(String dictType) {
        return dictDataMapper.selectDictDataByType(dictType);
    }

    /**
     * 移动端根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<HashMap<String,Object>> selectDictDataByTypeForMobile(String dictType) {
        return dictDataMapper.selectDictDataByTypeForMobile(dictType);
    }

    @Override
    public List<DictData> selectDictDataListByType(String[] dictType) {
        if(dictType!=null && dictType.length>0){
            return dictDataMapper.selectDictDataListByType(dictType);
        }else
            return null;
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public DictData selectDicByTypeAndValue(String dictType, String dictValue) {
        return dictDataMapper.selectDicByTypeAndValue(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public DictData selectDictDataById(Long dictCode) {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 通过字典ID删除字典数据信息
     *
     * @param dictCode 字典数据ID
     * @return 结果
     */
    @Override
    public int deleteDictDataById(Long dictCode) {
        return dictDataMapper.deleteDictDataById(dictCode);
    }

    /**
     * 批量删除字典数据
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int deleteDictDataByIds(String ids) {
        return dictDataMapper.deleteDictDataByIds(Convert.toStrArray(ids));
    }

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(DictData dictData) {
        dictData.setCreateBy(ShiroUtils.getLoginName());
        return dictDataMapper.insertDictData(dictData);
    }

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(DictData dictData) {
        dictData.setUpdateBy(ShiroUtils.getLoginName());
        return dictDataMapper.updateDictData(dictData);
    }

	@Override
	public List<Map<String, Object>> selectDictDataTree(String dictType) {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();

		DictType dt = dictTypeService.selectDictType(dictType);
		if(dt == null) {
			return trees;
		}

		Map<String, Object> deptMap = new HashMap<String, Object>();
		deptMap.put("id", dt.getDictType());
		deptMap.put("pId", "");
		deptMap.put("name", dt.getDictName());
		deptMap.put("title", dt.getDictName());
		trees.add(deptMap);

		List<DictData> dictData = selectDictDataByType(dictType);

		for (DictData dict : dictData) {
			deptMap = new HashMap<String, Object>();
			deptMap.put("id", dict.getDictValue());
			deptMap.put("pId", dict.getDictType());
			deptMap.put("name", dict.getDictLabel());
			deptMap.put("title", dict.getDictLabel());
			trees.add(deptMap);
		}

		return trees;
	}
}
