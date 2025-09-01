package com.fatec.loja;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository 
extends JpaRepository<Produto, Integer> {
    // Vitrine
    @Query(value="select * from produto where destaque > 0 ORDER BY destaque ASC", nativeQuery = true)
    public List<Produto> listarVitrine();

    // Busca
    @Query(value = "SELECT * FROM produto WHERE nome ILIKE %:termo% OR descritivo ILIKE %:termo%", nativeQuery = true)
    public List<Produto> fazerBusca(@Param("termo") String termo);
}
