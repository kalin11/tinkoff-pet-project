# Tinkoff Backend Academy Project

## Build

### Есть 2 сценария запуска

### 1. Для тестирования и запуска из IDE ###
Чтобы запустить приложение в IDE нужно:
<ol>
  <li>Выбираем профиль dev, чтобы приложение верно нашло БД</li>
  <li>

``
docker compose up --build -d postgres
``

С помощью этой команды мы поднимаем БД в контейнере


</li>
  <li>Запускаем приложение через IDE и работаем</li>
</ol>

### 2. Для запуска в одном контейнере и БД и приложения
Чтобы собрать проект в контейнере нужно</br>
<ol>
<li>

``
mvn clean package -DskipTests
``

Мы пропускаем тесты для того, чтобы не возникло ошибки с подключением к БД

</li>
<li>

``
docker compose up --build -d   
``

</li>

</ol>