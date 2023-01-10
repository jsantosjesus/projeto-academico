package br.com.academico.disciplina;

import java.util.ArrayList;
import java.util.List;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;

@Path("/disciplinas")
@Tag(name = "Disciplinas")
public class DisciplinaResource {

    private Disciplina disciplina;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Pegar Disciplina", description = "Mostra a lista de todas as disciplinas")
    public Response recuperar() {
        List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
        listaDisciplinas.add(new Disciplina("Programacao", 40));
        listaDisciplinas.add(new Disciplina("Topicos", 20));
        return Response.ok(listaDisciplinas, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Pegar Disciplina por id", description = "Mostra uma disciplina especifica por id")
    public Response recuperar(@PathParam("id") int id) {
        disciplina = new Disciplina("Construcao de sites", 40);
        disciplina.setId(id);
        return Response.ok(disciplina, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Cadastra Disciplina", description = "Cadastra uma nova disciplina")
    public Response criar(@Valid Disciplina disciplina) {
        disciplina.setId(200);
        return Response
                .status(Response.Status.CREATED)
                .entity(disciplina)
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "atualizar Disciplina", description = "atualiza atributos de disciplina")
    public Response atualizar(@PathParam("id") int id, Disciplina disciplina) {
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Deletar Disicplina", description = "deleta uma disciplina por Id")
    public Response deletar(@PathParam("id") int id) {
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
