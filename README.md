# rekrutacja
The application requires Java version 21 installed. You must provide the github token in application.yml. I recommend running it via intellij. Starts on port 8082.

Comments to the reviewer:
This is a POC of a solution to the task problem that works, is tested, but not production ready.
Since I couldn't ask about the content of the task, I didn't know at what stage to complete it, below are the steps I would take to make the written code work as a tool/microservice.

1. Adding a login, with division into files corresponding to a given day.
2. Creating a Dockerfile, Java and Maven in the appropriate versions installed on the image.
3. Reworking application.yml so that the token is accepted as an environment variable.

PL:
Aplikacja wymaga zainstalowanej Javy w wersji 21. Należy podać token githuba w application.yml. Polecam uruchamiać przez intellij. Uruchamia się na porcie 8082.

Uwagi do sprawdzającego:
To jest POC rozwiązania problemu z zadania, który działa, jest przetestowany, ale nie gotowy na produkcję.
Z racji iż nie mogłem pytać o treść zadania to nie wiedziałem do jakiego etapu je robic, poniżej są kroki jakie bym wykonał by napisany kod działał jako taki tool/mikroserwis.

1. Dodanie logowania, wraz z podziałem na pliki odpowiadające danemu dniu.
2. Stworzenie dockerfila, na obrazie zainstalowana java oraz maven w odpowiednich wersjach.
3. Przerobienie application.yml tak by token był przyjmowany jako zmienna środowiskowa.
