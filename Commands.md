# Comandos Úteis
**Backend**
1. **Construir as imagens e iniciar os containers**

   ```bash
   docker compose up --build
   ```

2. **Executar o Checkstyle**

   - Utilizando a fase de validação:
     
     ```bash
     mvn validate
     ```
     
   - Ou diretamente:
     
     ```bash
     mvn checkstyle:check
     ```

3. **Construir o pacote do projeto sem executar os testes**

   ```bash
   mvn clean package -DskipTests
   ```
**Frontend**
1. **Instalar dependências no Front com o NPM**
   ```bash
      npm i
   ```