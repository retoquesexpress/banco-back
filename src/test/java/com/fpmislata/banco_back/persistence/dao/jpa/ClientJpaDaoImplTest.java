package com.fpmislata.banco_back.persistence.dao.jpa;

import com.fpmislata.banco_back.persistence.TestConfig;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientJpaDaoImplTest {

    @Autowired
    private ClientJpaDao clientJpaDao;

    @Test
    @DisplayName("Should find all clients")
    void shouldFindAllClients() {
        List<ClientJpaEntity> clients = clientJpaDao.findAllClients();
        assertFalse(clients.isEmpty());
    }

    @Test
    @DisplayName("Should find client by DNI")
    void shouldFindClientByDni() {
        Optional<ClientJpaEntity> client = clientJpaDao.findClientByDni("12345678A");
        assertTrue(client.isPresent());
        assertEquals("12345678A", client.get().getDni());
    }

    @Test
    @DisplayName("Should find client by username")
    void shouldFindClientByUserName() {
        Optional<ClientJpaEntity> client = clientJpaDao.findClientByUserName("jdoe");
        assertTrue(client.isPresent());
        assertEquals("jdoe", client.get().getUserName());
    }

    @Test
    @DisplayName("Should create a new client")
    void shouldCreateClient() {
        ClientJpaEntity newClient = new ClientJpaEntity("87654321B", "newuser", "pass", "Jane", "Doe", "Smith",
                "newtok");
        ClientJpaEntity savedClient = clientJpaDao.create(newClient);

        assertNotNull(savedClient);
        assertEquals("87654321B", savedClient.getDni());

        Optional<ClientJpaEntity> retrieved = clientJpaDao.findClientByDni("87654321B");
        assertTrue(retrieved.isPresent());
    }

    @Test
    @DisplayName("Should update an existing client")
    void shouldUpdateClient() {
        Optional<ClientJpaEntity> clientOpt = clientJpaDao.findClientByDni("12345678A");
        assertTrue(clientOpt.isPresent());
        ClientJpaEntity client = clientOpt.get();

        client.setName("UpdatedName");
        ClientJpaEntity updatedClient = clientJpaDao.update(client);

        assertEquals("UpdatedName", updatedClient.getName());

        Optional<ClientJpaEntity> retrieved = clientJpaDao.findClientByDni("12345678A");
        assertTrue(retrieved.isPresent());
        assertEquals("UpdatedName", retrieved.get().getName());
    }

    @Test
    @DisplayName("Should delete a client")
    void shouldDeleteClient() {

        ClientJpaEntity newClient = new ClientJpaEntity("99999999Z", "todelete", "pass", "Del", "Ete", "Me", "deltok");
        clientJpaDao.create(newClient);

        clientJpaDao.delete("99999999Z");

        Optional<ClientJpaEntity> retrieved = clientJpaDao.findClientByDni("99999999Z");
        assertFalse(retrieved.isPresent());
    }
}
