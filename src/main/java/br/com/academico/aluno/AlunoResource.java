package br.com.academico.aluno;

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

@Path("/alunos")
@Tag(name = "Alunos")
public class AlunoResource {

    private Aluno aluno;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Pegar Aluno", description = "Mostra a lista de todos os alunos")
    public Response recuperar() {
        List<Aluno> listaAlunos = new ArrayList<Aluno>();
        listaAlunos.add(new Aluno(01, "Jadson", "Santos", 22, "Brasileiro", "Masculino", "055", "TI", true, 6.5, 6.75,
                true, "aprovado"));
        listaAlunos.add(new Aluno(01, "Jeferson", "Santos", 22, "Brasileiro", "Masculino", "053", "TI", true, 6.5, 6.75,
                true, "aprovado"));
        return Response.ok(listaAlunos, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Pegar Aluno por id", description = "Mostra um aluno especifico por id")
    public Response recuperar(@PathParam("id") int id) {
        aluno = new Aluno(01, "Jeferson", "Santos", 22, "Brasileiro", "Masculino", "053", "TI", true, 6.5, 6.75, true,
                "aprovado");
        aluno.setId(id);
        return Response.ok(aluno, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Cadastra Aluno", description = "Cadastra um novo aluno")
    public Response criar(Aluno aluno) {
        aluno.setId(200);
        return Response
                .status(Response.Status.CREATED)
                .entity(aluno)
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "atualizar Endereço", description = "atualiza atributos de alunos")
    public Response atualizar(@PathParam("id") int id, Aluno aluno) {
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Deletar Aluno", description = "deleta um aluno por Id")
    public Response deletar(@PathParam("id") int id) {
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
