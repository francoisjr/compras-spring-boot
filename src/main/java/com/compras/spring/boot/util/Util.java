package com.compras.spring.boot.util;

import com.compras.spring.boot.entities.Compra;
import com.compras.spring.boot.entities.ItemCompra;
import com.compras.spring.boot.entities.dto.CompraDTO;
import com.compras.spring.boot.entities.enums.CategoriaProduto;
import com.compras.spring.boot.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Util {

    @Autowired
    private CompraService compraService;


    public static LocalDate parseData(String data) {
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static int idade(final LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public static Double aplicarDesconto(Double valor, int categoria) {

        CategoriaProduto categoriaProduto = CategoriaProduto.valueOf(categoria);
        double desconto;
        DecimalFormat formato = new DecimalFormat("#,##");

        switch (categoriaProduto.getCode()) {
            case 1:
                desconto = valor - (valor * 0.1);
                System.out.println("Valor do produto: " + valor);
                System.out.println("Aplicado desconto de 10%: " + desconto);
                return desconto;
            case 2:
                desconto = valor - (valor * 0.05);
                System.out.println("Valor do produto: " + valor);
                System.out.println("Aplicado desconto de 5%: " + desconto);
                return Double.valueOf(formato.format(desconto));
            default:
                throw new IllegalStateException("Unexpected value: " + categoriaProduto.getCode());
        }
    }

    public static List<CompraDTO> parseCompra(List<Compra> compras) {
        List<CompraDTO> comprasDto = new ArrayList<>();
        double total = 0.0;
        Integer quantidade = 0;

        for (Compra compra : compras) {
            CompraDTO compraDTO = new CompraDTO();
            Set<ItemCompra> itemsCompras = compra.getItems();
            String nomeProduto = "";
            for (ItemCompra itemCompra : itemsCompras) {
                nomeProduto = itemCompra.getProduto().getNome();
                compraDTO.setNomeItem(nomeProduto);
                compraDTO.setValor(itemCompra.getPreco());
                quantidade = itemCompra.getQuantidade();
                total = itemCompra.getPreco() * quantidade;
            }
            compraDTO.setCpf(compra.getCliente().getCpf());
            compraDTO.setTotal(total);
            compraDTO.setQuantidade(quantidade);
            comprasDto.add(compraDTO);
        }
        return comprasDto;
    }
}