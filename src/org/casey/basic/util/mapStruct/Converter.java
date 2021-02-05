package org.casey.basic.util.mapStruct;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @InterfaceName PersonConverter
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 转换的接口 定义各个属性的映射
 * @Date 2020/8/12
 */

@Mapper
public interface Converter {
    /**
     * 获取Mapper
     */
    Converter converter = Mappers.getMapper(Converter.class);

    /**
     * <p>将PersonDo转换成PersonDto</p>
     * <p>属性名相同自动映射, 属性名不同就配置属性名的映射</p>
     * <p>自动映射: </p>
     * <p>- 基本类型和基本类型的包装类型之间</p>
     * <p>- 基本类型的包装类型和String类型之间</p>
     * <p>- String类型和枚举类型之间</p>
     *
     * @param personDo personDo
     * @return personDto
     */
    @Mappings({
            @Mapping(target = "username", source = "name"),
            @Mapping(target = "age", source = "age"),
            @Mapping(target = "birthday", source = "birthday", dateFormat = "yyyy/MM/dd HH:mm:ss"),
            @Mapping(target = "sex", constant = "true"),
            @Mapping(target = "address", expression = "java(homeAddressToString(personDo.getHomeAddress()))"),
            @Mapping(target = "weight", constant = "1000")
    })
    PersonDto personDo2personDto(PersonDo personDo);


    default String homeAddressToString(HomeAddress homeAddress) {
        return JSONUtil.parse(homeAddress).toStringPretty();
    }
}
