### Запуск
#### Вариант 1

1) Запуск базы данных (с тестовыми данными) в контейнере: docker-compose -f docker-compose-dev.yml up
2) Запуск приложения с помощью maven: mvn clean compile spring-boot:run

#### Вариант 2

1) Сборка и запуск приложения в контейнере вместе с запуском базы данных в контейнере: docker-compose up

### Демонстрация работы

1) Тестовые запросы в файле src/test/resources/request.http