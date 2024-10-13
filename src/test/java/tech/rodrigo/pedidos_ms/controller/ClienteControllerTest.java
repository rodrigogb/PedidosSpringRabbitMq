package tech.rodrigo.pedidos_ms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.rodrigo.pedidos_ms.controller.dto.ClienteDTO;
import tech.rodrigo.pedidos_ms.handler.MessageHandler;
import tech.rodrigo.pedidos_ms.service.ClienteService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ClienteControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private MessageHandler messageHandler;

    private ClienteDTO clienteDTO;

    @Autowired
    private ObjectMapper objectMapper;  // Injetando ObjectMapper

    @BeforeEach
    void setUp() {
        clienteDTO = new ClienteDTO("Rodrigo", "123456");
        logger.info("ClienteDTO criado: {}", clienteDTO);
    }

    @Test
    void inserirCliente_DeveRetornarStatus201_QuandoClienteValido() throws Exception {
        Mockito.doNothing().when(clienteService).save(any());

        logger.info("Executando o teste: inserirCliente_DeveRetornarStatus201_QuandoClienteValido");

        String jsonRequest = objectMapper.writeValueAsString(clienteDTO);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))  // Usando o JSON gerado a partir do objeto
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(clienteDTO.nome()))
                .andExpect(jsonPath("$.documento").value(clienteDTO.documento()));

        logger.info("Teste completo: inserirCliente_DeveRetornarStatus201_QuandoClienteValido");
    }

    @Test
    void inserirCliente_DeveRetornarStatus500_QuandoOcorreErro() throws Exception {
        Mockito.doThrow(new RuntimeException("Erro interno")).when(clienteService).save(any());
        Mockito.when(messageHandler.getMessage("erro.interno.criar.cliente"))
                .thenReturn("Erro interno ao criar cliente. Por favor, tente novamente mais tarde.");

        logger.info("Executando o teste: inserirCliente_DeveRetornarStatus500_QuandoOcorreErro");

        String jsonRequest = objectMapper.writeValueAsString(clienteDTO);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))  // Usando o JSON gerado a partir do objeto
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Erro interno ao criar cliente. Por favor, tente novamente mais tarde."));

        logger.info("Teste completo: inserirCliente_DeveRetornarStatus500_QuandoOcorreErro");
    }
}