# UserService

Микросервис UserService это один из двух микросервисов.

Предоставляет пользователю функционал: 
- создание пользователя, 
- редактирование пользователя, 
- смена роли для пользователя, 
- удаление пользователя, 
- получение списка пользователей, 
- получение пользователя по id.

### Как пользоваться:
1) Запускаем AuthService и UserService. В БД уже есть 2 юзера, один с "ADMIN" ролью,
   другой с ролью "USER"!
###### ADMIN:
{
"login": "pablito@mail.ru",
"password": "1234"
}
###### USER:
{
"login": "andrey@mail.ru",
"password": "1234"
}

2) В POSTMAN отправляем POST запрос по адресу: http://localhost:8080/login.
   Указав в теле запроса логин и пароль:
   {
   "login": "pablito@mail.ru",
   "password": "1234"
   }
  img_2.png
   Получаем TOKEN для дальнейших CRUD операций для роли "ADMIN".

3) Копируем "accessToken" и вставляем его в Authorization
 img_3.png

4) Далее можем пользоваться программой с правами "ADMIN"
- (POST) http://localhost:8080/create - Создание Юзера
- (PUT) http://localhost:8080/update/{id}  - Обговление Юзера по ID
- (GET) http://localhost:8080/all - Получение всех Юзеров из БД
- (GET) http://localhost:8080/user/{id} - Получение Юзера по ID
- (DELETE) http://localhost:8080/user/{id} - Удаление Юзера по ID
- (PUT) http://localhost:8080//rolechange/{id} - Изменение роли Юзера по ID
