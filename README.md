# Tinkoff Backend Academy Project

## Данный проект был реализован в рамках Академии Бэкенд разработки в Тинькофф. 
Это приложение под названием "Осведомленный студент" позволяет студентам находить какие-либо новости по предметам. В данном приложении есть ролевая модель, личные кабинеты, возможность создания постов, их комментирования, а также создание тредов к комментариям.\
На основой странице приложения есть новостная лента, которая реализована как бесконечный скроллинг. 
Скриншоты приложения и ссылка на front-end часть прикреплены ниже:

[Front-end](https://github.com/kalin11/tinkoff-pet-project-front-end)

### Скриншоты

![ЛК админа](https://github.com/kalin11/tinkoff-pet-project/blob/main/screenshots/photo_1_2023-12-17_22-16-50.jpg)
![Фото поста](https://github.com/kalin11/tinkoff-pet-project/blob/main/screenshots/photo_2_2023-12-17_22-16-50.jpg)
![Создание топика](https://github.com/kalin11/tinkoff-pet-project/blob/main/screenshots/photo_3_2023-12-17_22-16-50.jpg)
![ЛК юзера](https://github.com/kalin11/tinkoff-pet-project/blob/main/screenshots/photo_4_2023-12-17_22-16-50.jpg)
![Добаление нового поста админом](https://github.com/kalin11/tinkoff-pet-project/blob/main/screenshots/photo_5_2023-12-17_22-16-50.jpg)
![Авторизация](https://github.com/kalin11/tinkoff-pet-project/blob/main/screenshots/photo_6_2023-12-17_22-16-50.jpg)
![Выбор предметов на курсе](https://github.com/kalin11/tinkoff-pet-project/blob/main/screenshots/photo_7_2023-12-17_22-16-50.jpg)
![Выбор курса](https://github.com/kalin11/tinkoff-pet-project/blob/main/screenshots/photo_8_2023-12-17_22-16-50.jpg)
![Фото тредов к комментарию](https://github.com/kalin11/tinkoff-pet-project/blob/main/screenshots/photo_9_2023-12-17_22-16-50.jpg)

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
mvn clean package
``



</li>
<li>

``
docker compose up --build -d   
``

</li>

</ol>

Теперь наше приложение работает в докере на порту `8080`. 
