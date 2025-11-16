# 📱 MyFirstAndroidApp

Application Android Kotlin développée avec **Jetpack Compose + MVVM + Firebase + Room + Retrofit + WorkManager**.
Projet réalisé dans le cadre du module Android avancé (2025).

---

# 📌 Présentation

**MyFirstAndroidApp** est une application Android moderne construite avec **Kotlin + Jetpack Compose**, présentant une architecture propre et scalable (MVVM + Repository).
Elle intègre un ensemble complet de modules obligatoires :

* Authentification
* API REST
* Navigation
* Room
* Firestore
* Notifications (Local & FCM)
* WorkManager
* Internationalisation
* Tests unitaires

L’objectif : simuler un **vrai projet d’entreprise** avec une architecture professionnelle et des bonnes pratiques.

---

# 🎯 Fonctionnalités

### 🔐 Authentification (Firebase Email/Password)

* Inscription
* Connexion
* Déconnexion
* Redirection automatique si non connecté

### 🧭 Navigation

* **BottomBar**
* 4 écrans : Home, Produits (API), Users (API), Users DB (Room), Settings
* Restauration d’état & Scaffold

### 🌍 Internationalisation

* `values/strings.xml` (FR)
* `values-en/strings.xml` (EN)
* Écran Settings pour changer la langue
* Langue persistée via **DataStore**

### 🗃 Données locales : Room

* Entité `UserEntity`
* DAO + Database
* Repository + ViewModel
* Ajout d’un utilisateur
* Suppression
* Liste en temps réel via Flow

### 🔥 Firestore

* Lecture & écriture de documents
* Logs et gestion erreurs

### 🌐 API REST (Retrofit)

* Appel API publique (EscuelaJS)
* Liste d’items
* Pagination
* Pull-to-refresh
* États Loading / Empty / Error / Success

### 🔔 Notifications

* **Notification locale** via bouton
* **FCM** : réception en foreground & background

### ⚙️ WorkManager

* Tâche différée + Notification automatique
* Tâche avec contraintes (WiFi + chargement)

### 🧪 Tests unitaires

* Tests ViewModel
* Tests Repository
* Tests DAO (in-memory Room)
* Tests Coroutines (runTest)

---

# 🏗 Architecture du projet

> 🧱 **MVVM + Repository + UseCases (optionnel)**
> 💉 Préparé pour DI (Hilt), ou création manuelle selon configuration.
> 🪝 Flows + StateFlow pour une UI 100% réactive.

```
presentation/
   ├── screens/
   ├── viewmodels/

data/
   ├── local/ (Room)
   ├── remote/ (API)
   ├── firestore/
   ├── repository/

domain/
   ├── models/
   ├── usecases/   (optionnel)

app/
   ├── navigation/
   ├── ui/
   ├── workers/
```

---

# 🧰 Technologies utilisées

| Module          | Techno                                    |
| --------------- | ----------------------------------------- |
| UI              | Jetpack Compose + Material 3              |
| Navigation      | Navigation Compose                        |
| Auth            | Firebase Authentication                   |
| API             | Retrofit + Moshi + OkHttp Logging         |
| Local DB        | Room (DAO + Database + Flow)              |
| Cloud DB        | Firebase Firestore                        |
| Notifications   | NotificationManager + FCM                 |
| Background      | WorkManager                               |
| Stockage config | DataStore Preferences                     |
| Logs            | Log.d / Log.i / Log.e                     |
| Tests           | JUnit4 + Coroutines Test + Room in-memory |

---

# 🖼 Screenshots

> À compléter (tu peux m’envoyer des captures, je les insère dans le README)

* Login
* Register
* Bottom Bar
* Liste produits (API)
* Swipe-to-refresh + pagination
* Liste locale Room
* Settings (FR/EN)
* Notifications

---

# 📦 Modules détaillés

## 🔐 1) Auth Firebase

* Vérification email formaté
* Mot de passe min 6 car.
* États : loading, error, success
* Redirection automatique vers Home ou Login

## 🌐 2) API EscuelaJS (Produits & Utilisateurs)

* Endpoint Retrofit
* Repository avec gestion erreurs
* Pagination simulée (par blocs de 10)
* Swipe-to-refresh sans lib dépréciée

## 🗃 3) Room (Local Database)

Fichiers :

* `UserEntity.kt`
* `UserDao.kt`
* `AppDatabase.kt`
* `UserRepository.kt`
* `UserDbViewModel.kt`

Utilisation : ajout + suppression + liste en temps réel.

## 🔥 4) Firestore

* Collection `users`
* Ajout document
* Lecture
* Log des erreurs

## 🔔 5) Notifications

* Canal “default_channel”
* Notification immédiate avec bouton
* FCM : réception background & foreground

## ⚙️ 6) WorkManager

* Worker OneTime
* Worker avec contraintes WiFi + Charging
* Notification “Tâche exécutée !” lors du run

## 🗺 7) Internationalisation FR / EN

* Deux fichiers `strings.xml`
* Changement de langue sans redémarrer app
* Persisté avec DataStore

---

# 🧪 Tests unitaires

Les tests couvrent :

### ✔ ViewModel (ex: `AuthViewModelTest`)

* États
* Login
* Logout

### ✔ Repository

* Fakes + Mockk (si utilisé)

### ✔ DAO Room (In-memory)

Exemple :

```kotlin
@RunWith(AndroidJUnit4::class)
class UserDaoTest { ... }
```

### ✔ Coroutines

* `runTest`
* `advanceUntilIdle()`

---

# ⚙ Installation & exécution

### 1. Cloner le projet

```bash
git clone https://github.com/hamidadj13/MyFirstAndroidApp.git
```

### 2. Ouvrir dans Android Studio (Hedgehog ou plus)

Gradle installe automatiquement :

* Firebase
* Retrofit
* Room
* Coil
* Compose BOM

### 3. Lancer sur un émulateur API 26+

ou appareil physique.

---

# 📁 Structure du code

```
app/
│
├── data/
│   ├── local/ (Room)
│   ├── remote/ (Retrofit)
│   ├── firestore/
│   └── repository/
│
├── ui/
│   ├── screens/
│   ├── components/
│   ├── theme/
│   └── viewmodels/
│
├── workers/
├── navigation/
└── MyApp.kt
```

---

# 🌐 API utilisée

API publique : **EscuelaJS**

* `https://api.escuelajs.co/api/v1/products`
* `https://api.escuelajs.co/api/v1/users`

Endpoints utilisés :

* `GET /products`
* `GET /users`

---

# 👤 Auteur

**Hamid Adj**
2025 — Projet Android
Développé en Kotlin + Jetpack Compose
