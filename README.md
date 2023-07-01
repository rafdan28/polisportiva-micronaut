[//]: # (## Micronaut 3.9.3 Documentation)

[//]: # ()
[//]: # (- [User Guide]&#40;https://docs.micronaut.io/3.9.3/guide/index.html&#41;)

[//]: # (- [API Reference]&#40;https://docs.micronaut.io/3.9.3/api/index.html&#41;)

[//]: # (- [Configuration Reference]&#40;https://docs.micronaut.io/3.9.3/guide/configurationreference.html&#41;)

[//]: # (- [Micronaut Guides]&#40;https://guides.micronaut.io/index.html&#41;)

[//]: # (---)

[//]: # ()
[//]: # (- [Micronaut Maven Plugin documentation]&#40;https://micronaut-projects.github.io/micronaut-maven-plugin/latest/&#41;)

[//]: # (## Feature test-resources documentation)

[//]: # ()
[//]: # (- [Micronaut Test Resources documentation]&#40;https://micronaut-projects.github.io/micronaut-test-resources/latest/guide/&#41;)

[//]: # ()
[//]: # ()
[//]: # (## Feature http-client documentation)

[//]: # ()
[//]: # (- [Micronaut HTTP Client documentation]&#40;https://docs.micronaut.io/latest/guide/index.html#httpClient&#41;)

[//]: # ()
[//]: # ()
[//]: # (## Feature openapi documentation)

[//]: # ()
[//]: # (- [Micronaut OpenAPI Support documentation]&#40;https://micronaut-projects.github.io/micronaut-openapi/latest/guide/index.html&#41;)

[//]: # ()
[//]: # (- [https://www.openapis.org]&#40;https://www.openapis.org&#41;)

[//]: # ()
[//]: # ()
[//]: # (## Feature jdbc-tomcat documentation)

[//]: # ()
[//]: # (- [Micronaut Tomcat JDBC Connection Pool documentation]&#40;https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc&#41;)

[//]: # ()
[//]: # ()

# Applicazione REST API Polisportiva

<p align="center">
  <a href="https://micronaut.io/" target="blank"><img src="https://objectcomputing.com/files/8216/2275/4539/sally_micronaut_mascot.svg" width="200" alt="Micronaut Logos" /></a>
</p>
<p align="center"><a href="https://micronaut.io/" target="_blank">Micronaut</a> is a modern, JVM-based, full-stack framework for building modular, easily testable microservice and serverless applications</p> <p align="center">

## Prerequisiti

<img src="https://www.docker.com/wp-content/uploads/2022/03/Docker-Logo-White-RGB_Horizontal.png" alt="Logo Docker" width="300" height="100">

Per avviare l'applicazione, è necessario eseguire il seguente comando:

```shell
> docker-compose up --detach
```
Questo comando utilizzerà Docker, in particolare Docker Compose per creare e avviare i container necessari per l'applicazione.

## Monitoraggio del container

Per monitorare il container, in cui l'applicazione è in esecuzione, avviare lo script che esegue il seguente comando:

```shell
> docker stats
```

In particolare, aprire Windows PowerShell con privilegi di amministratore e lanciare il seguente comando:


```shell
> Set-ExecutionPolicy RemoteSigned
```

che permetterà di concedere i permessi di esecuzione di script alla shell.

Dopodichè, lanciare lo script docker-stats.ps1 con il seguente comando:

```shell
> .\docker-stats.ps1
```

Questo script genererà un file "stats.csv" in cui saranno scritti dati riguardo l'uso della CPU, della Memoria etc.. dei container in esecuzione.


## Documentazione

- [Guida utente di Micronaut](https://docs.micronaut.io/3.9.3/guide/index.html)
- [Riferimento API di Micronaut](https://docs.micronaut.io/3.9.3/api/index.html)
- [Riferimento di configurazione di Micronaut](https://docs.micronaut.io/3.9.3/guide/configurationreference.html)
- [Guide di Micronaut](https://guides.micronaut.io/index.html)

[//]: # (## Risorse di test)

[//]: # ()
[//]: # (- [Documentazione delle risorse di test di Micronaut]&#40;https://micronaut-projects.github.io/micronaut-test-resources/latest/guide/&#41;)

[//]: # ()
[//]: # (## Client HTTP)

[//]: # ()
[//]: # (- [Documentazione del client HTTP di Micronaut]&#40;https://docs.micronaut.io/latest/guide/index.html#httpClient&#41;)

[//]: # ()
[//]: # (## JDBC Tomcat)

[//]: # ()
[//]: # (- [Documentazione del pool di connessione JDBC Tomcat di Micronaut]&#40;https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc&#41;)
