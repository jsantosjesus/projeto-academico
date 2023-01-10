package br.com.academico.endereco;

import br.com.academico.config.AutoScanIoCFeature;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.server.ServerProperties;

import javax.json.Json;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import br.com.academico.exception.AcademicoExceptionMapper;

public class EnderecoResourceTest extends JerseyTest {

        @Override
        protected Application configure() {
                enable(TestProperties.LOG_TRAFFIC);
                enable(TestProperties.DUMP_ENTITY);
                return new ResourceConfig(EnderecoResource.class)
                .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
                .register(AcademicoExceptionMapper.class)
                .register(AutoScanIoCFeature.class);

        }

        @Test
        public void teste_recuperar_lista_enderecos() {

                // construuindo requisição para teste
                Response response = target("/enderecos").request().get();

                // recuperar corpo da resposta

                List<Endereco> listEndereco = response.readEntity(new GenericType<List<Endereco>>() {
                });

                // verificando dados da resposta
                assertEquals("O codigo de tatus HTTP da respota deve ser 200: ", Status.OK.getStatusCode(),
                                response.getStatus());
                assertEquals("O tipo de conteudo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON,
                                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
                assertTrue("O conteudo da resposta deve ser uma lista: ", listEndereco instanceof List<?>);

        }

        @Test
        public void teste_recuperar_endereco_por_id() {

                // construuindo requisição para teste
                Response response = target("/enderecos/100").request().get();

                // recuperar corpo da resposta

                Endereco endereco = response.readEntity(new GenericType<Endereco>() {
                });

                // verificando dados da resposta
                assertEquals("O codigo de tatus HTTP da respota deve ser 200: ", Status.OK.getStatusCode(),
                                response.getStatus());
                assertEquals("O tipo de conteudo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON,
                                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
                assertTrue("O conteudo da resposta deve ser um endereco: ", endereco instanceof Endereco);

        }

        @Test
        public void teste_criar_endereco() {

                // construuindo requisição para teste
                String enderecoJSON = Json.createObjectBuilder()
                                .add("cep", 493000000)
                                .add("rua", "antonio silvino i filho")
                                .add("bairro", "maria do carmo")
                                .add("cidade", "Tobias Barreto")
                                .add("estado", "SE")
                                .add("numero", 467)
                                .build()
                                .toString();

                Response response = target("/enderecos").request().post(Entity.json(enderecoJSON));

                // recuperar corpo da resposta

                Endereco endereco = response.readEntity(new GenericType<Endereco>() {
                });

                // verificando dados da resposta
                assertEquals("O codigo de tatus HTTP da respota deve ser 201: ", Status.CREATED.getStatusCode(),
                                response.getStatus());
                assertEquals("O tipo de conteudo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON,
                                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
                assertTrue("O conteudo da resposta deve ser um endereco: ", endereco instanceof Endereco);

        }

        @Test
        public void teste_atualizar_endereco_por_id() {

                // construuindo requisição para teste
                String enderecoJSON = Json.createObjectBuilder()
                                .add("cep", 493000)
                                .add("rua", "jose abreu")
                                .add("bairro", "centro")
                                .add("cidade", "Tobias Barreto")
                                .add("estado", "SE")
                                .add("numero", 407)
                                .build()
                                .toString();

                Response response = target("/enderecos/99").request().put(Entity.json(enderecoJSON));

                // recuperar corpo da resposta

                Endereco endereco = response.readEntity(new GenericType<Endereco>() {
                });

                // verificando dados da resposta
                assertEquals("O codigo de tatus HTTP da respota deve ser 204: ", Status.NO_CONTENT.getStatusCode(),
                                response.getStatus());

        }

        @Test
        public void teste_deletar_endereco_por_id() {

                // construindo requisição para teste
                Response response = target("/enderecos/99").request().delete();

                // verificando dados da resposta
                assertEquals("O codigo de tatus HTTP da respota deve ser 204: ", Status.NO_CONTENT.getStatusCode(),
                                response.getStatus());

        }

        @Test
        public void teste_criar_endereco_sem_rua() {
                String enderecoJSON = Json.createObjectBuilder()
                                .add("cep", 493000000)
                                .add("rua", "antonio silvino filho")
                                .add("bairro", "maria do carmo")
                                .add("cidade", "Tobias Barreto")
                                .add("estado", "SE")
                                .add("numero", 467)
                                .build()
                                .toString();

                Response response = target("/enderecos").request().post(Entity.json(enderecoJSON));
                String msg = response.readEntity(String.class);

                assertEquals("O codigo de status http da resposta deve ser400: ", 422, response.getStatus());
                assertEquals("O tipo do conteudo da resposta deve ser texto plano: ", MediaType.TEXT_PLAIN,
                                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
                assertTrue("O conteúdo http da resposta deve conter uma mensagem de validação pré-definida: ",
                                msg.contains("O atributo rua não pode ser nulo nem vazio. "));
        }

        @Test
        public void teste_criar_endereco_rua_tamanho_invalido() {
            String enderecoJSON = Json.createObjectBuilder()
                .add("CEP", 49000000)
                .add("bairro", "Centro")
                .add("cidade", "Aracaju")
                .add("estado", "Sergipe")
                .add("rua", "Rua abc")
                .build()
                .toString();
    
            Response response = target("/enderecos").request().post(Entity.json(enderecoJSON));
            String msg = response.readEntity(String.class);
    
            assertEquals("O codigo de status HTTP da resposta deve ser 422: ", 422, response.getStatus());
            assertEquals("O tipo de conteúdo HTTP da resposta deve ser texto plano: ", MediaType.TEXT_PLAIN, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
            assertTrue("O conteúdo da resposta deve conter uma mensagem de validação pré-definida: ", msg.contains("O atributo rua deve conter no mínimo 5 e no máximo 50 caracteres."));
        }
    
        @Test
        public void teste_criar_endereco_com_cep_invalido() {
            String enderecoJSON = Json.createObjectBuilder()
                .add("CEP", 49300000)
                .add("bairro", "Centro")
                .add("cidade", "Aracaju")
                .add("estado", "Sergipe")
                .add("rua", "Rua Treze")
                .build()
                .toString();
    
            Response response = target("/enderecos").request().post(Entity.json(enderecoJSON));
            String msg = response.readEntity(String.class);
    
            assertEquals("O codigo de status HTTP da resposta deve ser 422: ", 422, response.getStatus());
            assertEquals("O tipo de conteúdo HTTP da resposta deve ser texto plano: ", MediaType.TEXT_PLAIN, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
            assertTrue("O conteúdo da resposta deve conter uma mensagem de validação pré-definida: ", msg.contains("O atributo CEP deve ser inteiro e ter no mínimo 8 algarismos."));
        }
}
