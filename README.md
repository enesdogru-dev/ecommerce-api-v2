# 🚀 Kurumsal E-Ticaret REST API (V2)

Bu proje, modern Java backend geliştirme standartlarına uygun olarak tasarlanmış, **Spring Boot 3** ve **PostgreSQL** altyapısını kullanan ilişkisel bir REST API projesidir. Temel CRUD işlemlerinin ötesine geçilerek sektörel standartlar (DTO, Validation, Exception Handling) uygulanmıştır.

---

## 🛠️ Kullanılan Teknolojiler & Mimari

* **Language:** Java 17 (Record yapıları aktif kullanıldı)
* **Framework:** Spring Boot 3
* **Database:** PostgreSQL (Docker)
* **ORM:** Spring Data JPA / Hibernate
* **Boilerplate Reduction:** Lombok (`@RequiredArgsConstructor` ile temiz DI)
* **Data Validation:** Jakarta Bean Validation (`@Valid`, `@NotBlank`, `@Min`)

---

## 🌟 Öne Çıkan Kurumsal Özellikler

### 1. Veritabanı İlişkileri (Relational Mapping)
Entity'ler arası kopukluk giderilmiş, `Category` (Kategori) ve `Product` (Ürün) arasında **@OneToMany** ve **@ManyToOne** (LAZY Fetch) ilişkileri kurularak gerçek dünya veri modellemesi yapılmıştır.

### 2. DTO (Data Transfer Object) Deseni
Veritabanı nesneleri (Entity) doğrudan dışarı açılmamıştır. Veri güvenliği ve performans (Infinite Recursion önleme) amacıyla Java 14 `record` yapıları kullanılarak Request ve Response DTO'ları tasarlanmıştır.

### 3. Veri Doğrulama (Validation)
İş mantığına (Service) inmeden önce kapıda (Controller) güvenlik sağlanmıştır. Negatif fiyat girilmesi veya ürün adının boş bırakılması gibi durumlar `@Valid` anotasyonu ile engellenmiştir.

### 4. Global Exception Handling (Merkezi Hata Yönetimi)
Kullanıcıya veya Frontend takımına karmaşık Java "Stack Trace" (500 Internal Server Error) dönmek yerine, `@RestControllerAdvice` ve `@ExceptionHandler` kullanılarak hatalar havada yakalanmış ve standartlaştırılmış şık JSON formatlarına (400 Bad Request) dönüştürülmüştür.

---

## 📌 API Uç Noktaları (Endpoints)

### 1. Kategorileri Listeleme
* **GET** `/api/v1/categories`
* Sistemdeki tüm kategorileri DTO formatında döner.

### 2. Yeni Ürün Ekleme (Validasyonlu)
* **POST** `/api/v1/products`
* Belirli bir kategori ID'sine bağlı yeni bir ürün ekler.

**Örnek Başarılı İstek (Request Body):**
```json
{
  "name": "MacBook Pro M3",
  "price": 75000.0,
  "stock": 10,
  "categoryId": 1
}
```

**Örnek Validasyon Hatası Yanıtı (Response):**
```json
{
  "price": "Fiyat 0'dan küçük olamaz!",
  "name": "Ürün adı boş bırakılamaz!",
  "stock": "Stok 0'dan küçük olamaz!"
}
```

---

## 🚀 Projeyi Çalıştırma

**1. Veritabanını Ayağa Kaldırma:**
```bash
docker run --name postgres-db -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_DB=ecommercedb -p 5432:5432 -d postgres
```
**2. Uygulamayı Başlatma:**
Maven veya IDE üzerinden Spring Boot uygulamasını çalıştırdığınızda Tomcat varsayılan olarak `8080` portunda ayağa kalkacaktır.
