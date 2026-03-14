# Mini Project QA Automation

## 📌 Deskripsi
Project ini adalah framework automation testing berbasis **Java + TestNG + RestAssured + Selenium** yang dijalankan di **IntelliJ IDEA**.  
Tujuan utama: menguji fitur **Login, Register, dan Reset Password** baik dari sisi **API** maupun **UI**, dengan reporting menggunakan **Allure**.

---

## ⚙️ Teknologi & Library
- **Java JDK 21 (Temurin)**
- **Gradle** sebagai build tool
- **TestNG** untuk test runner
- **RestAssured** untuk API testing
- **Selenium WebDriver** untuk UI testing
- **WebDriverManager** untuk setup driver otomatis
- **Allure TestNG** untuk reporting
- **Log4j** untuk logging
- **Hamcrest** untuk flexible assertion

---

## 📂 Struktur Project
Mini-Project-QA3-Heru/   
    ├── src/main/java/        # Core framework & utilities  
├── src/test/java/        # Test cases (API & UI  
├── src/test/resources/   # Data JSON, config  
├── build.gradle          # Gradle dependencies  
├── README.md             # Dokumentasi project  


---

## 🚀 Cara Menjalankan di IntelliJ
1. Clone repository:
   ```bash
   git clone https://github.com/username/Mini-Project-QA3-Heru.git
    ```
2. Buka project di IntelliJ IDEA.
3. Pastikan JDK 21 sudah terpasang.
4. Sync Gradle untuk download semua dependency.
5. Jalankan test:
- API test: src/test/java/api/... 
- UI test: src/test/java/ui/... 
6. Generate report Allure:
```bash
./gradlew clean test
```
---
# 🧪 Test Coverage

## API
- Login (valid, invalid, duplicate, error handling)
- Register (valid, duplicate email)
- Send Verification Token (valid, invalid, skip karena token via email)

## UI
- Login form
- Register form
- Reset Password form

---

# 📊 Test Execution Summary
- **Total test case:** 38 (15 manual + 23 automation)
- **Pass:** 35
- **Fail:** 2
- **Skip:** 1
- **Success rate:** 92%

---

# 🐞 Bug Disclosure

## API
- BUG-API-LOGIN-04 → Invalid body balikin 500 (harusnya 400)
- BUG-API-CREATE-02 → Duplicate email tetap dibuat (harusnya 409)
- BUG-API-SENDTOKEN-02 → Email tidak terdaftar balikin 500 (harusnya 404)

## UI
- BUG-UI-REG-02 → Register duplikat toast salah (“Something Went Wrong!” bukan “Email already exists”)
- BUG-REG-04 (Manual) → Email duplikat tidak muncul error, sistem tetap membuat akun baru

---

# ✨ Catatan Pribadi
- Awalnya hanya bisa manual testing, lalu belajar automation step by step.
- Tantangan besar: translate test case manual ke automation.
- Automation pertama berhasil mencakup 3 fitur utama: **Login, Register, Reset Password**.
- Pencerahan: mulai kecil tapi stabil lebih penting daripada memaksakan semua fitur sekaligus.

---

# 📌 Kesimpulan
Framework ini sudah modular, maintainable, dan evidence-driven.  
Cocok untuk regression run, bisa dikembangkan lebih lanjut dengan CI/CD dan parallel execution.
