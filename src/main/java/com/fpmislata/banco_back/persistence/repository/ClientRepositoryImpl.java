package com.fpmislata.banco_back.persistence.repository;

import com.fpmislata.banco_back.domain.repository.ClientRepository;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.mapper.ClientMapper;
import com.fpmislata.banco_back.persistence.dao.jpa.ClientJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;

import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {

    private final ClientJpaDao clientJpaDao;

    public ClientRepositoryImpl(ClientJpaDao clientJpaDao) {
        this.clientJpaDao = clientJpaDao;
    }

    @Override
    public List<ClientDto> findAllClients() {
        return clientJpaDao.findAllClients().stream().map(ClientMapper.getInstance()::fromClientJpaEntityToClientDto)
                .toList();
    }

    @Override
    public Optional<ClientDto> findClientByDni(String dni) {
        return clientJpaDao.findClientByDni(dni).map(ClientMapper.getInstance()::fromClientJpaEntityToClientDto);
    }

    @Override
    public Optional<ClientDto> getClientByDni(String dni) {
        return clientJpaDao.findClientByDni(dni)
                .map(ClientMapper.getInstance()::fromClientJpaEntityToClientDto);
    }

    @Override
    public Optional<ClientDto> findClientByUserName(String userName) {
        return clientJpaDao.findClientByUserName(userName)
                .map(ClientMapper.getInstance()::fromClientJpaEntityToClientDto);
    }

    @Override
    public void delete(String dni) {
        clientJpaDao.delete(dni);
    }

    @Override
    public ClientDto create(ClientDto clientDto) {
        ClientJpaEntity entity = ClientMapper.getInstance().fromClientDtoToClientJpaEntity(clientDto);
        ClientJpaEntity createdEntity = clientJpaDao.create(entity);
        return ClientMapper.getInstance().fromClientJpaEntityToClientDto(createdEntity);
    }

    @Override
    public ClientDto update(ClientDto clientDto) {
        String dni = clientDto.dni();
        ClientJpaEntity existingEntity = clientJpaDao.findClientByDni(dni).orElseThrow(
                () -> new RuntimeException("Cliente con DNI " + dni + " no encontrado para actualizar."));
        existingEntity.setUserName(clientDto.userName());
        existingEntity.setPassword(clientDto.password());
        existingEntity.setName(clientDto.name());
        existingEntity.setSurname1(clientDto.surname1());
        existingEntity.setSurname2(clientDto.surname2());
        existingEntity.setApiToken(clientDto.apiToken());

        ClientJpaEntity updatedEntity = clientJpaDao.update(existingEntity);
        return ClientMapper.getInstance().fromClientJpaEntityToClientDto(updatedEntity);
    }
}
