package io.choerodon.base.infra.asserts;

import io.choerodon.core.exception.CommonException;
import io.choerodon.base.infra.dto.MenuDTO;
import io.choerodon.base.infra.mapper.MenuMapper;
import org.springframework.stereotype.Component;

/**
 * 菜单断言帮助类
 *
 * @author superlee
 * @since 0.16.0
 */
@Component
public class MenuAssertHelper extends AssertHelper {

    private MenuMapper menuMapper;

    public MenuAssertHelper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public void codeExisted(String code) {
        MenuDTO dto = new MenuDTO();
        dto.setCode(code);
        if (!menuMapper.select(dto).isEmpty()) {
            throw new CommonException("error.menu.code.existed");
        }
    }

    public MenuDTO menuNotExisted(Long id) {
        return menuNotExisted(id, "error.menu.not.exist");
    }

    public MenuDTO menuNotExisted(Long id, String message) {
        MenuDTO dto = menuMapper.selectByPrimaryKey(id);
        if (dto == null) {
            throw new CommonException(message, id);
        }
        return dto;
    }
}
