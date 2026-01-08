package com.markteplace.bazan.markteplace_web.dto;

import com.markteplace.bazan.markteplace_web.dto.responses.VendasResponses;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.VendaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterVendas {


    public VendasResponses paraVendaResponse(VendaEntity entity){

        VendasResponses venda = new VendasResponses(entity.getId(), entity.getData(), entity.getItensVenda());

        return venda;
    }

    public List<VendasResponses> paraListaVendasResponse(List<VendaEntity> vendaEntities){

        List<VendasResponses> lista = new ArrayList<>();

        for (VendaEntity venda : vendaEntities){

            lista.add(paraVendaResponse(venda));
        }

        return lista;

    }
}
