# Desafio Ptang

ESTÓRIAS DE USUÁRIO

1. [x] - API de Usuário

  <blockquote>Criar os Endpoints para a api de Usuário</blockquote>
            1.1. /api/signin - Esta rota espera um objeto com os campos login e password, e deve retornar o token
                 de acesso da API (JWT) com as informações do usuário logado.
            <br>1.2. /api/users - Listar todos os carros do usuário logado 
            <br>1.3. /api/users - Cadastrar um novo usuário
            <br>1.4. /api/users/{id} - Buscar um usuário pelo id
            <br>1.5. /api/users/{id} - Remover um usuário pelo id
            <br>1.6. /api/users/{id} - Atualizar um usuário pelo id <br><br>

  <blockquote>Tratamento de Erros</blockquote>
            1.1. Login inexistente ou senha inválida: retornar um erro com a mensagem “Invalid login or password”;
            <br>1.2. E-mail já existente: retornar um erro com a mensagem “Email already exists”;
            <br>1.3. Login já existente: retornar um erro com a mensagem “Login already exists”;
            <br>1.4. Campos inválidos: retornar um erro com a mensagem “Invalid fields”;
            <br>1.5. Campos não preenchidos: retornar um erro com a mensagem “Missing fields.<br><br>


2. [x] - API de Carros

  <blockquote>Criar os Endpoints para a api de Usuário</blockquote>
            1.1. /api/me - Retornar as informações do usuário logado (firstName, lastName, email, birthday,
                 login,phone, cars) + createdAt (data da criação do usuário) + lastLogin (data da última vez 
                 que o usuário realizou login).
            <br>1.2. /api/cars - Listar todos os carros do usuário logado 
            <br>1.3. /api/cars - Cadastrar um novo carro para o usuário logado 
            <br>1.4. /api/cars/{id} - Buscar um carro do usuário logado pelo id 
            <br>1.5. /api/cars/{id} - Remover um carro do usuário logado pelo id 
            <br>1.6. /api/cars/{id} - Atualizar um carro do usuário logado pelo id<br><br>

  <blockquote>Tratamento de Erros</blockquote>
            1. Token não enviado: retornar um erro com a mensagem “Unauthorized”;
            <br>2. Token expirado: retornar um erro com a mensagem “Unauthorized - invalid session”;
            <br>3. Placa já existente: retornar um erro com a mensagem “License plate already exists”;
            <br>4. Campos inválidos: retornar um erro com a mensagem “Invalid fields”;
            <br>5. Campos não preenchidos: retornar um erro com a mensagem “Missing fields<br><br>




3. [x] - Disponibilizar a API rodando em algum host (Heroku)<br>

4. [x] - Criar um repositório público git (Github)<br>

5. [x] - Criação de rota para upload da fotografia do usuário e do carro<br>

6. [x] - Senha deve ser criptografada
