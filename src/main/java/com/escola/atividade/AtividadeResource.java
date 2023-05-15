package com.escola.atividade;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/atividades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AtividadeResource {

    @GET
    public Response obterTodas() {
        List<Atividade> atividades = Atividade.listAll();
        return Response.ok(atividades).build();
    }

    @GET
    @Path("/{id}")
    public Response obterPeloId(@PathParam("id") Long id) {
        Atividade atividade = Atividade.findById(id);
        return Response.ok(atividade).build();
    }

    @POST
    @Transactional
    public Response cadastrar(Atividade atividade) {
        Atividade.persist(atividade);
        return Response.ok(atividade).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response alterar(@PathParam("id")Long id, Atividade atualizacao) {
        Atividade atividade = Atividade.findById(id);
        if (atividade == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        atividade.nome = atualizacao.nome;
        atividade.nota = atualizacao.nota;
        atividade.persist();
        return Response.ok(atividade).build();
    }
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response remover(@PathParam("id") Long id) {
        Atividade.deleteById(id);
        return Response.ok().build();
    }

}