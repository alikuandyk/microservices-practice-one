

`UserController`

`GET /users/{id}` - получение пользователя по id.
Если пользователь не найден, возвращаем вот такой json со статусом `404`
```json
{
    "error": "Пользователь с id=%d не найден",
    "timestamp": "2025-02-28T15:53:47"
}
```

`ProductController`

`GET /products/{id}` - получение товара по id.
Если товар не найден, возвращаем вот такой json со статусом `404`
```json
{
    "error": "Товар с id=%d не найден",
    "timestamp": "2025-02-28T15:53:47"
}
```

<br>
<br>

`OrderController`

`POST /orders`
```json
{
    "error": "Пользователь с id=%d не найден",
    "timestamp": "2025-02-28T15:53:47"
}
```

```json
{
    "error": "Товар с id=%d не найден",
    "timestamp": "2025-02-28T15:53:47"
}
```