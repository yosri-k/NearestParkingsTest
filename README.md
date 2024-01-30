
# Test Technique Java Back-end      

Développer une application qui exposant une API REST pour permettre à une application mobile, ou un site Web, d’afficher la liste des parkings à proximité.


## Documentation

[Documentation](https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/html/boot-features-webclient.html)

[Documentation](https://howtodoinjava.com/spring-webflux/webclient-get-post-example/)

[Documentation](https://reflectoring.io/spring-webclient/)

[Documentation](https://spring.io/blog/2023/07/13/new-in-spring-6-1-restclient/)

[Documentation](https://springjavatutorial.medium.com/how-to-use-lombok-in-spring-boot-project-bb1bf0b4dc83)

[Documentation](
https://towardsdev.com/data-transfer-object-dto-in-spring-boot-c00678cc5946)

[Documentation](https://www.baeldung.com/java-reactor-flux-vs-mono#:~:text=In%20simple%20terms%2C%20we%20can,then%20we%20should%20use%20Mono.)

[Documentation](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html)

[Documentation](https://www.baeldung.com/reactive-streams-step-verifier-test-publisher)







## Choix technique

**Spring Boot 3.2.2 .** 

**Java 17 .** 

**Maven 4.0.0 .** 


**WebClient :** J'ai utilisé WebClient pour effectuer des requêtes Web au lieu de RestClient car j'ai compris que le contexte de travail est basé sur la programmation réactive, aussi c'est une classe fournie par Spring Framework (Spring WebFlux).

**Jackson :**  est souvent utilisé pour gérer la sérialisation et la désérialisation JSON, Il simplifie le processus de conversion des objets Java en JSON et vice versa

**DTO :** (Data Transfer Object) est un modèle de conception utilisé pour transférer des données entre différentes couches d'une application,

**Lombok :** pour réduire le code dans les classes. Lombok est souvent utilisé pour simplifier la création d'objets, d'entités et d'autres classes en générant des méthodes couramment utilisées, telles que les getters, les setters, les égaux, le hashCode et toString, lors de la compilation.

**La formule de Haversine :** est une formule mathématique utilisée pour calculer la distance entre deux points sur une sphère, comme la Terre

**Spring WebFlux :** est un framework de programmation réactif . C'est pour gérer des opérations asynchrones et non bloquantes, ce qui le rend bien adapté à la création d'applications Web évolutives et efficaces.



## Installation



```bash
  git clone https://github.com/yosri-k/NearestParkingsTest.git
```
    
## Probléme non traité
* La gestion des erreurs des APIs externe.

* Le calcul de distance a l'instant T si la liste des places disponibilité change à même instant.

* Eviter les block() dans des environnements réactifs.

* Le traitement des tests unitaires n'est pas optimal en raison de changements dans la liste des objets.