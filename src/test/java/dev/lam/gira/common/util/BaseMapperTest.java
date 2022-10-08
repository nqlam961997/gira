package dev.lam.gira.common.util;

import dev.lam.gira.user.dto.UserDTO;
import dev.lam.gira.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BaseMapperTest {

    BaseMapper baseMapper = new BaseMapper();

    @Test
    void testUserMapper() {
        UserDTO userDTO = UserDTO.builder()
                .username("abc")
                .email("abc@gmail.com")
                .build();

        User user = baseMapper.map(userDTO, User.class);

        Assertions.assertEquals(userDTO.getUsername(), user.getUsername());
    }

}