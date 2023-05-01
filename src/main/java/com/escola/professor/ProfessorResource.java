package com.escola.professor;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/professores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfessorResource {

    @GET
    public Response obterTodos() {
        List<Professor> professores = Professor.listAll();
        return Response.ok(professores).build();
    }

    @GET
    @Path("/{id}")
    public Response obterPeloId(@PathParam("id") Long id) {
        Professor professor = Professor.findById(id);
        return Response.ok(professor).build();
    }

    @POST
    @Transactional
    public Response cadastrar(Professor professor) {
        professor.matricula = "p" + professor.matricula;
        Professor.persist(professor);
        return Response.ok(professor).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response alterar(@PathParam("id")Long id, Professor atualizacao) {
        Professor professor = Professor.findById(id);
        professor.disciplina = atualizacao.disciplina;
        professor.matricula = "p" + atualizacao.matricula;
        professor.nome = atualizacao.nome;
        professor.persist();
        return Response.ok(professor).build();
    }

    @PUT
    @Path("/{matricula}")
    @Transactional
    public Response alterar(@PathParam("matricula")String matricula, Professor atualizacao) {
        Professor professor = Professor.find("matricula", matricula).firstResult();
        if (professor == null) {
            // Se o profesor é nulo, quer dizer que ele não existe no cadastro(banco).
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        professor.disciplina = atualizacao.disciplina;
        professor.matricula = atualizacao.matricula;
        professor.matricula = "p" + atualizacao.matricula;
        professor.nome = atualizacao.nome;
        professor.persist();
        return Response.ok(professor).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response remover(@PathParam("id") Long id) {
        Professor.deleteById(id);
        return Response.ok().build();
    }

}