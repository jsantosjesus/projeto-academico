package br.com.academico.endereco;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import javax.inject.Named;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/enderecos")
@Tag(name = "Endereços")
public class EnderecoResource {

    private Endereco endereco;

    @Inject
    @Named("enderecoservicedefault")
    private IEnderecoService enderecoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Pegar Endereço", description = "Mostra a lista de todos os endereços")
    public Response recuperar() {
        List<Endereco> listaEnderecos = new ArrayList<Endereco>();
        try {
            listaEnderecos = enderecoService.listar();
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response.ok(listaEnderecos, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Pegar Endereço por id", description = "Mostra um endereço especifico por id")
    public Response recuperar(@PathParam("id") int id) {
        Endereco endereco;
        try {
            endereco = enderecoService.recuperar(id);
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type("text/plain")
                    .build();
        }
        return Response.ok(endereco, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Cadastrar Endereço", description = "Cadastra um novo endereço")
    public Response criar(@Valid Endereco endereco) {
        int id;
        try {
            id = enderecoService.criar(endereco);
            endereco.setId(id);
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type("text/plain")
                    .build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(endereco)
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "atualizar Endereço", description = "atualiza atributos de endereço")
    public Response atualizar(@PathParam("id") int id, Endereco endereco) {
        try {
            endereco = enderecoService.atualizar(id, endereco);
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type("text/plain")
                    .build();
        }
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Deletar Endereço", description = "deleta um endereço por Id")
    public Response deletar(@PathParam("id") int id) {
        try {
            enderecoService.deletar(id);
        } catch (Exception e) {
           return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type("text/plain")
                    .build();
        }
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    // Operações não CRUD//

    @PUT
    @Path("{id}/status")
    @Consumes(MediaType.TEXT_PLAIN)
    @Operation(summary = "Exclusão lógica de Endereço", description = "Muda atributo Status de um endereço", requestBody = @RequestBody(description = "Status do endereço", required = true, content = @Content(schema = @Schema(implementation = StatusEndereco.class))))
    public Response mudarStatus(@PathParam("id") int id, String status) {
        try {
            endereco = enderecoService.mudarStatus(id, StatusEndereco.fromString(status));
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type("text/plain")
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(endereco)
                .build();
    }

}
