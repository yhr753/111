package mapper;

import Dto.ItemsDto;
import pojo.Items;

import java.util.List;

public interface ItemsMapper {
    /**
     * 根据id进行删除
     * @param ids, 要删除的所有编号
     * @return
     */
    int deleteByIds(int[] ids);


    /**
     * 组合查询
     * @param dto
     * @return
     */
    List<Items> selectByDto(ItemsDto dto);


    /**
     * 按条件统计总记录数
     * @param dto
     * @return
     */
    Long countByDto(ItemsDto dto);



    List<Items> queryAll();

    Items queryOne(int id);

    int add(Items items);


    int pdt(Items items);


    int del(int id);

}
