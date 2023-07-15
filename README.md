# SpringBoot Blog API Application
`Spring Boot 3.1.1`, `Spring Data JPA`,  `JsonWebToken`, `MySql`, `Spring Security`, `Lombok`,

## API DOC
### Authentication
- `POST /api/auth/register` -> Kullanıcının sisteme kayıt olmasını sağlar
- `POST /api/auth/authenticate` -> Kullanıcının sisteme giriş yapmasını sağlar

### Comment
- `POST /api/comments/add/{postId}` -> Gönderiye yeni bir yorum ekler
- `GET /api/comments/get/all/{postId}` -> Bir gönderideki tüm yorumları listeler
- `DELETE /api/comments/delete/{commentId}` -> Yorumu siler

### Post
- `POST /api/posts/save` -> Yeni bir gönderi oluşturur.
- `GET /api/posts/get/all` -> Bütün gönderileri listeler
- `GET /api/posts/get/user/id/{userId}` -> Bir kullanıcıya ait tüm gönderileri listeler
- `GET /api/posts/get/user/my-post` -> Sisteme giriş yapan kullanıcıya ait kendi gönderilerini listeler
- `DELETE /api/posts/delete/{postId}` -> Gönderiyi siler
- `PATCH /api/posts/update/{postId}` -> Gönderiyi düzenler
