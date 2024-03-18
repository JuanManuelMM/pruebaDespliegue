# Utiliza una imagen base con Java y Maven preinstalados
FROM maven:3.8.1-openjdk-17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml al directorio de trabajo
COPY pom.xml .

# Descarga las dependencias del proyecto sin copiar el código fuente
RUN mvn dependency:go-offline

# Copia el resto del código fuente al directorio de trabajo
COPY src ./src

# Compila el proyecto
RUN mvn package -DskipTests

# Etapa de construcción final
FROM openjdk:17-jre-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado en la etapa de compilación anterior al contenedor
COPY --from=build /app/target/BasketBuddy-0.0.1-SNAPSHOT.jar .

# Expone el puerto en el que se ejecutará tu aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "BasketBuddy-0.0.1-SNAPSHOT.jar"]
