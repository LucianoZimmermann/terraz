# Usando uma imagem oficial do Node.js como base
FROM node:18 AS build

WORKDIR /app

# Copiando os arquivos de dependências
COPY package.json package-lock.json ./

# Instalando dependências
RUN npm install

# Copiando o restante do código
COPY . .

# Gerando o build
RUN npm run build

# Usando Nginx para servir os arquivos
FROM nginx:alpine

# Copiando os arquivos gerados para o Nginx
COPY --from=build /app/dist /usr/share/nginx/html

# Expondo a porta 3000
EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]
