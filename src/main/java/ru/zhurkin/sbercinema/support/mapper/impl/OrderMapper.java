package ru.zhurkin.sbercinema.support.mapper.impl;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;
import ru.zhurkin.sbercinema.dto.OrderDTO;
import ru.zhurkin.sbercinema.model.Order;
import ru.zhurkin.sbercinema.repository.FilmRepository;
import ru.zhurkin.sbercinema.repository.UserRepository;
import ru.zhurkin.sbercinema.support.mapper.GenericMapper;

import static ru.zhurkin.sbercinema.support.constants.ErrorMessageKeeper.FILM_NOT_FOUND;
import static ru.zhurkin.sbercinema.support.constants.ErrorMessageKeeper.USER_NOT_FOUND;

@Component
public class OrderMapper extends GenericMapper<Order, OrderDTO> {

    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    public OrderMapper(ModelMapper modelMapper,
                       FilmRepository filmRepository,
                       UserRepository userRepository) {
        super(modelMapper, Order.class, OrderDTO.class);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Order.class, OrderDTO.class)
                .addMappings(m -> m.skip(OrderDTO::setFilmId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDTO::setOwnerId)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(OrderDTO.class, Order.class)
                .addMappings(m -> m.skip(Order::setFilm)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Order::setOwner)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(OrderDTO source, Order destination) {

        destination.setFilm(filmRepository.findById(source.getFilmId())
                .orElseThrow(() -> new NotFoundException(FILM_NOT_FOUND)));
        destination.setOwner(userRepository.findById(source.getOwnerId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND)));
    }

    @Override
    protected void mapSpecificFields(Order source, OrderDTO destination) {
        destination.setFilmId(source.getFilm().getId());
        destination.setOwnerId(source.getOwner().getId());
    }

}
