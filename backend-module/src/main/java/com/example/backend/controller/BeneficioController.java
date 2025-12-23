package com.example.backend.controller;

import com.example.backend.models.Beneficio;
import com.example.backend.models.request.BeneficioRequest;
import com.example.backend.models.request.TransferenciaRequest;
import com.example.backend.service.BeneficioEjbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "Benefícios",
        description = "Gerenciamento de benefícios e transferência de saldo"
)
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/beneficios")
public class BeneficioController {

    private final BeneficioEjbService service;

    public BeneficioController(BeneficioEjbService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar benefícios",
            description = "Retorna todos os benefícios cadastrados"
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<Beneficio> list() {
        return service.findAll();
    }

    @Operation(
            summary = "Buscar benefício por ID",
            description = "Retorna um benefício específico pelo seu identificador"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Benefício encontrado"),
            @ApiResponse(responseCode = "404", description = "Benefício não encontrado")
    })
    @GetMapping("/{id}")
    public Beneficio get(
            @PathVariable Long id
    ) {
        return service.findById(id);
    }

    @Operation(
            summary = "Criar benefício",
            description = "Cria um novo benefício com valor inicial"
    )
    @ApiResponse(responseCode = "201", description = "Benefício criado com sucesso")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beneficio create(
            @RequestBody BeneficioRequest beneficio
    ) {
        return service.create(beneficio);
    }

    @Operation(
            summary = "Atualizar benefício",
            description = "Atualiza nome e valor de um benefício existente"
    )
    @ApiResponse(responseCode = "200", description = "Benefício atualizado")
    @PutMapping("/{id}")
    public Beneficio update(
            @PathVariable Long id,
            @RequestBody Beneficio beneficio
    ) {
        return service.update(id, beneficio);
    }

    @Operation(
            summary = "Excluir benefício",
            description = "Remove um benefício do sistema"
    )
    @ApiResponse(responseCode = "204", description = "Benefício removido")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ) {
        service.delete(id);
    }

    @Operation(
            summary = "Transferir saldo",
            description = """
            Transfere valor entre dois benefícios.
            - Valida saldo
            - Valida IDs
            - Usa optimistic locking
            - Rollback automático em caso de erro
        """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Transferência realizada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Conflito de concorrência")
    })
    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(
            @RequestBody TransferenciaRequest transferencia
    ) {
        service.transfer(
                transferencia.getFromId(),
                transferencia.getToId(),
                transferencia.getAmount()
        );
    }
}
