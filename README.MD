Заметки на Java с использованием Spring и Thymeleaf
=====================
Простое веб-приложение, для сохранения и редактирования пользовательских заметок.
  Создано с использованием Spring Boot и Thymeleaf

Установка и запуск
----------------------

## Требования
- Java 21
- PostgreSQL с созданной базой
- Docker
- Maven
## Демо работы
https://github.com/user-attachments/assets/f92f62f8-ca1e-4e35-86ab-f98810dcd1d4

### Установка
1. Скачать репозиторий:

```bash
git clone https://github.com/FalseTheory/Java_Notes.git
```
2. Создать базу PostgreSQL и обновить файл application.properties
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/database
spring.datasource.username=username
spring.datasource.password=password
```
Заменить соответствующие записи на данные вашей бд.

## 2 Способ (находясь в корневой директории):
```bash
mvn clean package
```
И после
```bash
docker compose up -d
```
3. Вход в приложение:
Открыть ссылку http://localhost:8080/notes

# Функционал

- Создание заметок
- Редактирование и удаление заметок
- Вывод всех заметок
- Заметки сохраняются в базе данных
- При первом запуске будет создана тестовая заметка

# TODO LIST
-----------
- Добавить вставку картинок в текст заметки
- Добавить редактирование текста с помощью языка разметки 
- Поиск заметок
- Покрытие тестами
