# Utilisez une image Node.js comme base
FROM node:latest
# Définissez le répertoire de travail dans le conteneur
WORKDIR /app
# Copiez le fichier package.json et le fichier package-lock.json
COPY package*.json ./
# Installez les dépendances de l'application
RUN npm install --force
# Copiez tout le contenu de votre application dans le conteneur
COPY . .
# Exposez le port sur lequel votre application Angular s'exécute (par exemple, 4200)
EXPOSE 4200
# Commande pour démarrer l'application Angular
CMD ["npm", "start"]
