package br.com.academico.professor;

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

@Path("/professores")
@Tag(name = "Professores")
public class ProfessorResource {

    private Professor professor;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Pegar Professor", description = "Mostra a lista de todos o professores")
    public Response recuperar() {
        List<Professor> listaProfessores = new ArrayList<Professor>();
        listaProfessores.add(new Professor(01, "Diego", "armando", 35, "Brasileiro", "Masculino", "058", 3500, 40));
        listaProfessores.add(new Professor(03, "Diego", "Silveira", 39, "Brasileiro", "Masculino", "090", 3500, 40));
        return Response.ok(listaProfessores, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Pegar Professor por id", description = "Mostra um professor especifico por id")
    public Response recuperar(@PathParam("id") int id) {
        professor = new Professor(03, "Diego", "Silveira", 39, "Brasileiro", "Masculino", "090", 3500, 40);
        professor.setId(id);
        return Response.ok(professor, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Cadastra Professor", description = "Cadastra um novo professores")
    public Response criar(Professor professor) {
        professor.setId(53);
        return Response
                .status(Response.Status.CREATED)
                .entity(professor)
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "atualizar Professor", description = "atualia atributos de professor")
    public Response atualizar(@PathParam("id") int id, Professor professor) {
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Deletar Professor", description = "deleta um professor por Id")
    public Response deletar(@PathParam("id") int id) {
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
