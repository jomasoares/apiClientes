package dev.joao.desafioUnimed.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.joao.desafioUnimed.builder.ClienteDTOBuilder;
import dev.joao.desafioUnimed.dto.ClienteDTO;
import dev.joao.desafioUnimed.exceptions.CnpjNotUniqueException;
import dev.joao.desafioUnimed.services.ClienteService;
import dev.joao.desafioUnimed.util.JsonConverterUtils;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    private static final String API_URL_PATH = "/api/v1/clientes";

    private MockMvc mockMvc;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPostIsCalledAClienteIsCreated() throws Exception {
        //given
        ClienteDTO clienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();

        //when
        when(clienteService.createCliente(clienteDTO)).thenReturn(clienteDTO);

        //then
        mockMvc.perform(
            post(API_URL_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonConverterUtils.asJsonString(clienteDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.razaoSocial", is(clienteDTO.getRazaoSocial())))
        .andExpect(jsonPath("$.cnpj", is(clienteDTO.getCnpj())))
        .andExpect(jsonPath("$.regimeTributario", is(clienteDTO.getRegimeTributario().name())))
        .andExpect(jsonPath("$.email", is(clienteDTO.getEmail())));
    }

    @Test
    void whenPostIsCalledWithCnpjInWrongFormat() throws Exception {
        //given
        ClienteDTO clienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();
        clienteDTO.setCnpj("invalid string");

        //then
        mockMvc.perform(post(API_URL_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonConverterUtils.asJsonString(clienteDTO)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenPostIsCalledWithEmailInWrongFormat() throws Exception {
        //given
        ClienteDTO clienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();
        clienteDTO.setEmail("invalid string");

        //then
        mockMvc.perform(post(API_URL_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonConverterUtils.asJsonString(clienteDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPostIsCalledWithCnpjAlreadyRegistredThrowException() throws Exception {
        //given
        ClienteDTO clienteDTO = ClienteDTOBuilder.builder().build().toClienteDTO();

        //when
        when(clienteService.createCliente(clienteDTO)).thenThrow(CnpjNotUniqueException.class);

        //then
        mockMvc.perform(post(API_URL_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonConverterUtils.asJsonString(clienteDTO)))
                .andExpect(status().isBadRequest());
    }
    
}
