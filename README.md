## 📘 Tasca S4.01 – Introducció a Spring

## 📄 Descripció - Enunciat de l'exercici

Aquesta tasca té com a objectiu fer una primera presa de contacte amb el **framework Spring Boot**, el protocol **HTTP**, la creació d’**APIs REST**, i l’ús de **gestors de dependències** com **Maven** i **Gradle**.

La tasca està dividida en **tres nivells**:

- **Nivell 1:** Spring Boot amb Maven  
- **Nivell 2:** Spring Boot amb Gradle  
- **Nivell 3:** Proves de les APIs amb Postman  

---

## ⭐ Nivell 1 – Spring Boot amb Maven

### 🎯 Objectiu

Crear una aplicació Spring Boot utilitzant **Maven** com a gestor de dependències i exposar una API REST senzilla amb dues rutes GET.

---

### ⚙️ Configuració del projecte

Projecte generat amb **Spring Initializr** amb les següents característiques:

- **Project:** Maven  
- **Language:** Java  
- **Spring Boot:** Darrera versió estable  
- **Packaging:** Jar  
- **Java:** 11 o superior  

**Project Metadata:**

- Group: `cat.itacademy.s04.t01.n01`  
- Artifact: `S04T01N01`  
- Name: `S04T01N01`  
- Description: `S04T01N01`  
- Package name: `cat.itacademy.s04.t01.n01`  

**Dependències utilitzades:**

- Spring Web  
- Spring Boot DevTools  

---

### 🌐 Configuració del servidor

```properties
server.port=9000
```

---

### 🧩 API REST

S’ha creat el package `controller` amb la classe `HelloWorldController`, que conté dos mètodes:

#### 🔹 Endpoint amb RequestParam

- **Ruta:** `/HelloWorld`
- **Mètode:** GET
- **Paràmetre:** `nom` (opcional, valor per defecte `UNKNOWN`)

Exemples:
```
http://localhost:9000/HelloWorld
http://localhost:9000/HelloWorld?nom=El meu nom
```

Resposta:
```
Hola, El meu nom. Estàs executant un projecte Maven
```

---

#### 🔹 Endpoint amb PathVariable

- **Ruta:** `/HelloWorld2/{nom}`
- **Mètode:** GET
- **Paràmetre:** `nom` (opcional)

Exemples:
```
http://localhost:9000/HelloWorld2
http://localhost:9000/HelloWorld2/elmeunom
```

---

### 🛠️ Comandes Maven utilitzades

```bash
mvn clean
mvn compile
mvn package
mvn spring-boot:run
```

---

## ⭐⭐ Nivell 2 – Spring Boot amb Gradle

### 🎯 Objectiu

Repetir l’exercici del nivell 1 utilitzant **Gradle** com a gestor de dependències.

---

### ⚙️ Configuració del projecte

Projecte generat amb **Spring Initializr** amb les següents característiques:

- **Project:** Gradle  
- **Language:** Java  
- **Spring Boot:** Darrera versió estable  
- **Packaging:** Jar  
- **Java:** 11 o superior  

**Project Metadata:**

- Group: `cat.itacademy.s04.t01.n02`  
- Artifact: `S04T01N02`  
- Name: `S04T01N02`  
- Description: `S04T01N02`  
- Package name: `cat.itacademy.s04.t01.n02`  

**Dependències utilitzades:**

- Spring Web  
- Spring Boot DevTools  

---

### 🌐 Configuració del servidor

```properties
server.port=9001
```

---

### 🧩 API REST

La classe `HelloWorldController` conté els mateixos dos endpoints que al nivell 1, però amb el missatge adaptat a Gradle.

Resposta:
```
Hola, nom. Estàs executant un projecte Gradle
```

---

### 🛠️ Comandes Gradle utilitzades

```bash
gradle clean
gradle build
gradle assemble
gradle bootRun
```

---

## ⭐⭐⭐ Nivell 3 – Postman

### 🎯 Objectiu

Provar les APIs creades als nivells anteriors utilitzant **Postman**, fent servir variables d’entorn.

---

### 🌍 Entorns creats

S’han creat dos entorns a Postman:

#### 🔹 Projecte Maven
- `Servidor`: http://localhost  
- `Port`: 9000  

#### 🔹 Projecte Gradle
- `Servidor`: http://localhost  
- `Port`: 9001  

---

### ▶️ Proves realitzades

Exemple d’URL utilitzant variables:

```
{{Servidor}}:{{Port}}/HelloWorld2/elmeunom
```

S’han adjuntat:
- 2 captures de pantalla (una per cada entorn)
- 2 arxius JSON amb els entorns exportats

---

## 📦 Què s’entrega

- Enllaç al repositori Git amb els projectes Maven i Gradle  
- Enllaços als recursos utilitzats  
- Arxius JSON dels entorns Postman  
- Captures de pantalla de les execucions  

---

## 📚 Recursos utilitzats

- https://www.baeldung.com/spring-boot-change-port  
- https://www.baeldung.com/spring-request-param  
- https://www.baeldung.com/spring-optional-path-variables  
- https://www.baeldung.com/spring-requestparam-vs-pathvariable  
- https://www.chakray.com/es/gradle-vs-maven-definiciones-diferencias/  
- https://programmerclick.com/article/47131530173/  

---

## 🤝 Autor

Projecte desenvolupat per **Jordi**  
IT Academy – Especialització Back-End amb Java
