# ReadLab

## 1. Deskripsi Singkat Proyek

ReadLab merupakan aplikasi berbasis Java Console yang dikembangkan menggunakan Apache Maven. Aplikasi ini menyediakan platform sederhana untuk membaca dan mengelola e-book dengan dua jenis pengguna, yaitu **Reader** dan **Author**. Reader dapat melihat katalog buku, membeli buku premium, membaca buku, mengunduh buku, mengelola catatan, serta bookmark. Author dapat menerbitkan buku baru dan melihat daftar buku yang telah dipublikasikan. Seluruh data aplikasi disimpan secara in-memory sehingga akan kembali ke kondisi awal setiap aplikasi dijalankan.

---

## 2. Arsitektur Pipeline CI/CD

Pipeline CI/CD diimplementasikan menggunakan GitHub Actions dengan empat tahapan utama.

```
Push / Pull Request
        │
        ▼
Continuous Integration
(Build Project)
        │
        ▼
Continuous Testing
(Unit Testing)
        │
        ▼
Continuous Inspection
(SonarCloud Analysis)
        │
        ▼
Continuous Deployment
(GitHub Packages)
```

Penjelasan setiap tahapan:

* **Continuous Integration (CI)**
  Melakukan proses checkout source code, setup Java 21, mengunduh dependency Maven, serta melakukan build project.

* **Continuous Testing (CT)**
  Menjalankan seluruh unit test secara otomatis menggunakan Maven Surefire dan mengunggah hasil pengujian sebagai artifact GitHub Actions.

* **Continuous Inspection**
  Melakukan analisis kualitas kode menggunakan SonarCloud. Pipeline akan berhenti apabila Quality Gate tidak terpenuhi.

* **Continuous Deployment (CD)**
  Melakukan publish artifact Maven ke GitHub Packages setelah seluruh tahapan sebelumnya berhasil dijalankan.

---

## 3. Tabel Pembagian Tugas Anggota

| Nama      | Tanggung Jawab                                                   |
| --------- | ---------------------------------------------------------------- |
| M. hafizh Al kautsar | Continuous Inspection (SonarCloud, Quality Gate, GitHub Secrets) |
| Bintang Anugrah Pratama | Continuous Integration (Build Workflow, Maven Configuration)     |
| Albert Febrian | Continuous Testing (Unit Testing dan Test Report Workflow)                |
| Hizkia Nicander Budiyanto | Continuous Deployment (GitHub Packages dan Deployment Workflow)  |

---

## 4. Daftar Tools dan Teknologi

| Tools / Teknologi     | Kegunaan                                   |
| --------------------- | ------------------------------------------ |
| Java 21               | Bahasa pemrograman utama                   |
| Apache Maven          | Build automation dan dependency management |
| GitHub Actions        | Implementasi pipeline CI/CD                |
| JUnit 5               | Unit Testing                               |
| Maven Surefire Plugin | Menjalankan unit test                      |
| SonarCloud            | Static Code Analysis dan Quality Gate      |
| GitHub Packages       | Maven Package Registry                     |
| Git                   | Version Control                            |

---

## 5. Panduan Menjalankan Proyek Secara Lokal

### Clone Repository

```bash
git clone https://github.com/HapisPro/mkepl_tugas_besar/
cd ReadLab
```

### Compile Project

```bash
mvn clean compile
```

### Menjalankan Unit Test

```bash
mvn test
```

### Build Project

```bash
mvn clean package
```

### Menjalankan Aplikasi

```bash
mvn exec:java
```
