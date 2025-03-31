# GithubRepos

Aplikacja oparta na Spring Boot, która umożliwia pobieranie repozytoriów użytkownika GitHub wraz z informacjami o gałęziach (nazwa repozytorium, login właściciela, nazwa gałęzi oraz SHA ostatniego commita). Repozytoria będą filtrowane – zwracane są tylko te, które nie są forkami.

## Spis treści

- [Wymagania](#wymagania)
- [Instalacja](#instalacja)
- [Uruchomienie](#uruchomienie)
- [Endpoint API](#endpoint-api)
- [Obsluga-bledow](#obsluga-bledow)
- [Uwagi-dotyczace-limitow-api](#uwagi-dotyczace-limitow-api)

## Wymagania

- **Java:** Wersja 21
- **Maven:** do budowania projektu
- **Spring Boot:** wersja 3.4.4 (lub zgodna)
- Dostęp do internetu w celu komunikacji z API GitHub

## Instalacja

1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/PiotrBra/GithubRepos.git
   ```
2. Przejdź do katalogu projektu:
    ```bash
    cd githubrepos
    ```
3. Zbuduj projektu przy uzyciu Maven:
    ```bash
    mvn clean install
    ```
## Uruchomienie
Aplikację możesz uruchomić na kilka sposobów:
* Z poziomu IDE - uruchom klasę GithubReposApplication
* Za pomocą Maven:
    ```bash
    mvn spring-boot:run
    ```
Po uruchomieniu aplikacja będzie dostępna na porcie 8080.

## Endpoint API
### GET /repositories/{username}
Pobiera repozytoria użytkownika GitHub, które nie są forkami, wraz z informacjami o gałęziach.

Przykładowe zapytanie:
```bash
curl http://localhost:8080/repositories/PiotrBra
```
Przykładowa odpowiedź:
```json
[
  {
    "name": "repo1",
    "ownerLogin": "PiotrBra",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "abc123"
      }
    ]
  }
]
```
## Obsluga bledow
- Nieistniejący użytkownik:

  Jeśli użytkownik GitHub nie istnieje, aplikacja zwróci odpowiedź 404:
    ```json
    {
        "status": 404,
        "message": "User not found"
    }
    ```
- Przekroczony limit zapytań (Rate Limit):
  
  Jeśli przekroczony zostanie limit zapytań do API GitHub, aplikacja zwróci odpowiedź 403 z informacją:
    ```json
    {
    "status": 403,
    "message": "API rate limit exceeded. { ... }"
    }
    ```
## Uwagi dotyczace limitow API
GitHub ogranicza nieautoryzowane zapytania do 60 zapytań na godzinę. Po przekroczeniu tego limitu otrzymasz błąd 403 wraz z komunikatem:

***API rate limit exceeded for [adres IP]. (But here's the good news: Authenticated requests get a higher rate limit...)***

Aby uzyskać wyższy limit, rozważ autoryzację zapytań do API GitHub (np. przy użyciu tokena). Szczegóły dostępne są w dokumentacji GitHub.

## Podsumowanie
Projekt realizuje następujące zadania:

- Pobieranie repozytoriów użytkownika z GitHub.
- Filtrowanie repozytoriów (wykluczenie forków).
- Pobieranie informacji o gałęziach repozytorium.
- Obsługa błędów (użytkownik nie istnieje, przekroczony limit zapytań).

Celem projektu jest spełnienie wymagań API konsumenta z czystym, dobrze zorganizowanym kodem.