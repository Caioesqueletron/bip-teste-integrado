# 🏗️ Desafio Fullstack Integrado

Este projeto implementa uma solução completa em camadas, conforme proposto no desafio.

---

## 🎯 Objetivo

- Corrigir bug em serviço EJB
- Implementar backend Spring Boot
- Desenvolver frontend Angular
- Integrar todas as camadas
- Garantir consistência transacional
- Documentar e testar a solução

---

## 📦 Estrutura do Projeto

db/
├── schema.sql
└── seed.sql


backend-module/
├── controller/
├── service/ -> ejbModuleIncluido
├── models/
└── pom.xml

frontend/
└── Angular application

docs/
└── README.md

yaml
Copiar código

---

## 🐞 Bug Corrigido (EJB)

### Problema identificado
- Transferência sem validação de saldo
- Ausência de locking
- Possibilidade de saldo negativo
- Lost update em concorrência

### Solução aplicada
- Validação de IDs e valor
- Validação de saldo
- Uso de `@Transactional`
- Controle de concorrência com `@Version`
- Rollback automático em `OptimisticLockException`

---

## 🔄 Funcionalidades Implementadas

### Backend
- CRUD completo de Benefícios
- Transferência segura de saldo
- API REST documentada via Swagger

### Frontend
- Listagem de benefícios
- Criação e edição
- Exclusão
- Transferência de saldo
- Integração total com backend

---

## 🧪 Testes

- Testes unitários de Service (JUnit + Mockito)
- Testes de Controller (MockMvc)
- Cobertura de casos de sucesso e erro

---

## 📚 Documentação

- Swagger UI disponível em:
http://localhost:8080/swagger-ui.html

yaml
Copiar código

- README detalhado
- Comentários em código crítico

---

## 🚀 Execução

### Backend
```bash
mvn clean spring-boot:run
```

### Frontend
```bash
npm install
ng serve
Acesse:
http://localhost:4200
```
✅ Critérios Atendidos
✔ Arquitetura em camadas
✔ Correção do EJB
✔ CRUD + Transferência
✔ Testes
✔ Documentação
✔ Frontend funcional