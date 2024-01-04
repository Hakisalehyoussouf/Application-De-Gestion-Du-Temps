# Utilisez une image Java
FROM openjdk:17
# Définissez le répertoire de travail
WORKDIR /app
# Copiez le fichier JAR de l'application (assurez-vous de construire le JAR au préalable)
COPY target/projet*.jar ./app.jar
# Exposez le port (par exemple, 8080 pour Spring Boot)
EXPOSE 8080
# Commande de démarrage pour l'application Spring Boot
CMD ["java", "-jar", "app.jar","--spring.profiles.active=prod"]
