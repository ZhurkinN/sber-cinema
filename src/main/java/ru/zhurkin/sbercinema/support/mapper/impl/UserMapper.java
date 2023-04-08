package ru.zhurkin.sbercinema.support.mapper.impl;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;
import ru.zhurkin.sbercinema.dto.UserDTO;
import ru.zhurkin.sbercinema.model.GenericEntity;
import ru.zhurkin.sbercinema.model.User;
import ru.zhurkin.sbercinema.repository.OrderRepository;
import ru.zhurkin.sbercinema.repository.RoleRepository;
import ru.zhurkin.sbercinema.support.mapper.GenericMapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.zhurkin.sbercinema.support.constants.ErrorMessageKeeper.ROLE_NOT_FOUND;

@Component
public class UserMapper extends GenericMapper<User, UserDTO> {

    private final OrderRepository orderRepository;
    private final RoleRepository roleRepository;

    public UserMapper(ModelMapper modelMapper,
                      OrderRepository orderRepository,
                      RoleRepository roleRepository) {
        super(modelMapper, User.class, UserDTO.class);
        this.orderRepository = orderRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void setupMapper() {

        modelMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO::setOrderIds)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(UserDTO::setRoleId)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(UserDTO.class, User.class)
                .addMappings(m -> m.skip(User::setOrders)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(User::setRole)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(UserDTO source, User destination) {

        if (!Objects.isNull(source.getOrderIds())) {
            destination.setOrders(new HashSet<>(orderRepository.findAllById(source.getOrderIds())));
        } else {
            destination.setOrders(Collections.emptySet());
        }

        destination.setRole(roleRepository.findById(source.getRoleId())
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND)));
    }

    @Override
    protected void mapSpecificFields(User source, UserDTO destination) {

        destination.setOrderIds(getIds(source));
        destination.setRoleId(source.getRole().getId());
    }

    private Set<Long> getIds(User entity) {

        if (Objects.isNull(entity) || Objects.isNull(entity.getOrders())) {
            return null;
        }
        return entity.getOrders()
                .stream()
                .map(GenericEntity::getId)
                .collect(Collectors.toSet());
    }
}
