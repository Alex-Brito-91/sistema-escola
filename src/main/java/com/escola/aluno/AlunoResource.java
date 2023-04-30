package com.escola.aluno;

import com.escola.professor.Professor;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoResource {

    @GET
    public Response obterTodos() {
        List<Aluno> alunos = Aluno.listAll();
        return Response.ok(alunos).build();
    }

    @GET
    @Path("/{id}")
    public Response obterPeloId(@PathParam("id") Long id) {
        Aluno aluno = Aluno.findById(id);
        return Response.ok(aluno).build();
    }

    @POST
    @Transactional
    public Response cadastrar(Aluno aluno) {
        Aluno.persist(aluno);
        return Response.ok(aluno).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response alterar(@PathParam("id")Long id, Aluno atualizacao) {
        Aluno aluno = Aluno.findById(id);
        aluno.matricula = atualizacao.matricula;
        aluno.nome = atualizacao.nome;
        aluno.nota = atualizacao.nota;
        aluno.persist();
        return Response.ok(aluno).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response remover(@PathParam("id") Long id) {
        Aluno.deleteById(id);
        return Response.ok().build();
    }

}