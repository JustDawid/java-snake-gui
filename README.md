Markdown
# Gra Snake 2D w Javie (Swing) 

## Opis Projektu
W pełni grywalna, klasyczna gra Snake posiadająca interfejs graficzny (GUI). 
Projekt został napisany w języku **Java** z wykorzystaniem wbudowanych bibliotek `javax.swing` oraz `java.awt`. Celem projektu było stworzenie płynnej animacji 2D, obsługi zdarzeń klawiatury w czasie rzeczywistym oraz logiki kolizji.

##  Główne cechy kodu
* **Interfejs Graficzny (GUI):** Użycie `JFrame` do stworzenia okna aplikacji oraz nadpisanie metody `paintComponent` w `JPanel` do renderowania grafiki.
* **Pętla Gry (Game Loop):** Kontrolowana za pomocą klasy `javax.swing.Timer`, która odświeża logikę i ekran co 100 milisekund.
* **Obsługa Zdarzeń:** Wykorzystanie interfejsu `KeyListener` do płynnego sterowania wężem.
* **Wizualizacja:** Dynamiczne rysowanie ogona węża z użyciem naprzemiennych kolorów w pętli.

##  Sterowanie
* `W` - Góra
* `A` - Lewo
* `S` - Dół
* `D` - Prawo
* `X` - Natychmiastowe zakończenie gry

##  Kompilacja i Uruchomienie

Aby uruchomić grę, musisz mieć zainstalowane środowisko Java (JDK).

### 1. Kompilacja kodu:
Otwórz terminal w folderze z grą i wpisz:
```bash
javac Snake.java
